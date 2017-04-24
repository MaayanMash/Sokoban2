package view;
	

import java.util.List;
import controller.SokobanController;
import controller.generic.GenericController;
import controller.server.SokobanClientHandler;
import model.db.SokobanDBManager;
import model.db.iDBManager;
import javafx.application.Application;
import javafx.stage.Stage;
import model.SokobanModel;
import model.iModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application 
{
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));				 
			BorderPane root = (BorderPane) loader.load();
			MainWindowController view = loader.getController();
			ScoreController scoreTable=new ScoreController();
			
			iDBManager DBManager = new SokobanDBManager();
			
			SokobanModel model=new SokobanModel(DBManager);
			SokobanController sokobanController;
			
			//get args
			List<String> args=getParameters().getRaw();
			
			if (args.size()>0 && args.get(0).toLowerCase().equals("-server")){
				int port=Integer.parseInt(args.get(1));
				SokobanClientHandler clientHendler= new SokobanClientHandler();
				sokobanController=new SokobanController(model, view,scoreTable, clientHendler, port);
				clientHendler.addObserver(sokobanController);
	
			}
			else {
				sokobanController=new SokobanController(model, view,scoreTable);
			}

			model.addObserver(sokobanController);
			view.addObserver(sokobanController);
			view.setPrimaryStage(primaryStage);
			//add new
			scoreTable.addObserver(sokobanController);
			
			
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Sokoban");
			primaryStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}

