package controller;

import java.util.HashMap;

import javax.print.DocFlavor.INPUT_STREAM;

import model.iModel;
import view.ScoreController;

public class CommandDB extends CommandA {

	private iModel model;
	private HashMap<String,Runnable> hmDB;
	private String query;
	private ScoreController scoreTable;
	
	public CommandDB(iModel model, ScoreController scoreTable) {
		this.model=model;
		this.scoreTable=scoreTable;
		hmDB=new HashMap<String, Runnable>();
		initHashMap();
	}
	private void initHashMap(){
	hmDB.put("add", new Runnable() {
		
		@Override
		public void run() {
			model.addToDB(query);
			
		}
	});
	
	hmDB.put("update", new Runnable() {
		
		@Override
		public void run() {
			model.updateDB(query);
			
		}
	});
	
	
	hmDB.put("select", new Runnable() {
		
		@Override
		public void run() {//set list in score controller
			scoreTable.setListResult( model.selectScore(query));
			
		}
	});
	}
	@Override
	public void execute() {
		String[] input = (params).split(" ",2);
		this.query=input[1];
		Runnable runnable=hmDB.get(input[0].toLowerCase());
		if (runnable==null)
			model.error();
		else
			runnable.run();
	}

}
