package controller;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import controller.generic.GenericController;
import controller.generic.iCommand;
import controller.server.MyServer;
import controller.server.SokobanClientHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.iModel;
import view.ScoreController;
import view.iView;

public class SokobanController implements Observer {
	
	private GenericController gc;
	private iModel theModel;
	private iView theView;
	private SokobanClientHandler TheClientHendler;
	private MyServer theServer;
	private ScoreController scoreTable;
	
	private HashMap<String, iCommand> hmCommands;
	private StringProperty countSteps;

	
	
	//Ctor
	public SokobanController(iModel model,iView view, ScoreController scoreTable) {
		this.scoreTable=scoreTable;
		this.gc=new GenericController();
		this.theModel=model;
		this.theView=view;
		this.countSteps=new SimpleStringProperty();
		this.hmCommands= new HashMap<String, iCommand>();
		initHashMap();
		this.gc.start();
		this.theView.createBindSteps(this.countSteps);
		this.theServer=null;
		this.gc=null;
	}
	public SokobanController(iModel model,iView view,ScoreController scoreTable, SokobanClientHandler ClientHendler, int port) {
		this.scoreTable=scoreTable;
		this.gc=new GenericController();
		this.theModel=model;
		this.theView=view;
		this.countSteps=new SimpleStringProperty();
		this.hmCommands= new HashMap<String, iCommand>();
		this.gc.start();
		this.theView.createBindSteps(this.countSteps);
		this.TheClientHendler=ClientHendler;
		this.theServer=new MyServer(port, this.TheClientHendler);
		this.theServer.start();
		initHashMap();
		
	}
	
	private void initHashMap(){
		CommandLoad cl = new CommandLoad(theModel);
		this.hmCommands.put("load",cl );
		//this.hmCommands.put("Load",cl );
		CommandSave cs =new CommandSave(theModel);
		//this.hmCommands.put("Save", cs);
		this.hmCommands.put("save", cs);
		CommandDisplayLevel cd = new CommandDisplayLevel(theView, theModel);
		//this.hmCommands.put("DisplayLevel", cd);
		this.hmCommands.put("displayLevel", cd);
		CommandMove cm =new CommandMove(theModel, countSteps);
		//this.hmCommands.put("Move", cm);
		this.hmCommands.put("move", cm);
		CommandExit ce =new CommandExit(gc,theServer);
		this.hmCommands.put("exit", ce);
		//this.hmCommands.put("Exit", ce);
		CommandDisplayMassege cdm = new CommandDisplayMassege(theView, TheClientHendler);
		//this.hmCommands.put("DisplayMassege", cdm);
		this.hmCommands.put("displayMassege", cdm);
		CommandDisplayCli cdc= new CommandDisplayCli(theModel, TheClientHendler);
		//this.hmCommands.put("Display", cdc);
		this.hmCommands.put("display", cdc);
		CommandMassegeCli cmc = new CommandMassegeCli(theView, TheClientHendler);
		this.hmCommands.put("displayMassegeCli", cmc);
		//this.hmCommands.put("DisplayMassegeCli", cmc);
		CommandDB cdb= new CommandDB(theModel, scoreTable);
		this.hmCommands.put("db", cdb);
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		String[] input = ((String)arg).split(" ",2);
		String commandName = input[0];
		String params = null;	
		if(input.length > 1)
			params = input[1];
		iCommand cm = hmCommands.get(commandName.toLowerCase());
		if (cm==null)
		{
			cm=hmCommands.get("displayMassegeCli");
			params="Wrong input";
		}
		cm.setParams(params);
		gc.insertCommand(cm);	
	}
	


}
