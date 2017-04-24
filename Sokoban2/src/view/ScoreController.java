package view;

import java.net.URL;
import java.util.ResourceBundle;
import model.db.SokobanDBManager;
import model.db.UserLevel;
import model.db.iDBManager;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

public class ScoreController extends Observable {
	private TableView<UserLevel> table;
	private Scene scene;
	private Stage scorePage;
	private List listResult;

	
	public ScoreController() {
		this.listResult=null;
		this.table=new TableView();
		this.scene= new Scene(new Group());
		Label label = new Label("High Score");
        label.setFont(new Font("Arial", 20));
        
        TableColumn levelNameCol2 = new TableColumn("Level Name");
        TableColumn stepsCol = new TableColumn("Steps");
        TableColumn timeCol = new TableColumn("Time");
        
        levelNameCol2.setCellValueFactory(new PropertyValueFactory<UserLevel,String>("levelName"));
        stepsCol.setCellValueFactory(new PropertyValueFactory<UserLevel,String>("levelSteps"));
        timeCol.setCellValueFactory(new PropertyValueFactory<UserLevel,String>("levelTime"));
        
        
 TableColumn userNameCol = new TableColumn<>("User Name");
        
        userNameCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<UserLevel,UserLevel>, 
                ObservableValue<UserLevel>>() {

            @Override
            public ObservableValue<UserLevel> call(TableColumn.CellDataFeatures<UserLevel, UserLevel> p) {
                return new ReadOnlyObjectWrapper<UserLevel>(p.getValue());
            	//return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        
        //Adding the Button to the cell
        userNameCol.setCellFactory(
                new Callback<TableColumn<UserLevel,UserLevel>, TableCell<UserLevel, UserLevel>>() {

            @Override
            public TableCell<UserLevel,UserLevel> call(TableColumn<UserLevel,UserLevel> userNameCol) {
            	UserButtonCell btn= new UserButtonCell();
            	//btn.updateItem(userNameCol.getCellData(0), false);
            	//btn.setButtonName(userNameCol.getCellData(0).toString());
                return btn;
            }
        
        });
 
        TableColumn levelNameCol = new TableColumn<>("level Name");
        
        
        //levelNeme col
        levelNameCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<UserLevel,UserLevel>, 
                ObservableValue<UserLevel>>() {

            @Override
            public ObservableValue<UserLevel> call(TableColumn.CellDataFeatures<UserLevel, UserLevel> p) {
                return new ReadOnlyObjectWrapper<UserLevel>(p.getValue());
            	//return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        
        //Adding the Button to the cell
        levelNameCol.setCellFactory(
                new Callback<TableColumn<UserLevel,UserLevel>, TableCell<UserLevel, UserLevel>>() {

            @Override
            public TableCell<UserLevel,UserLevel> call(TableColumn<UserLevel,UserLevel> levelNameCol) {
            	LevelButtonCell btn= new LevelButtonCell();
            	//btn.updateItem(userNameCol.getCellData(0), false);
            	//btn.setButtonName(userNameCol.getCellData(0).toString());
                return btn;
            }
        
        });
       
        table.getColumns().addAll(userNameCol, levelNameCol, stepsCol, timeCol);
        
        iDBManager dbm = new SokobanDBManager();
        ObservableList<UserLevel> data = FXCollections.observableArrayList(
        		dbm.selectScore("UserLevel")
        	);
        
        //data.clear();
        //data.addAll(dbm.selectScorebyId("userName like 'tal'"));
        
        table.setItems(data);
        table.getSortOrder();
        
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
        
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
	}
	
	public Stage getScorePage() {
		return scorePage;
	}


	public void setScorePage(Stage scorePage) {
		this.scorePage = scorePage;
	}
	
	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public List getListResult() {
		return listResult;
	}

	public void setListResult(List listResult) {
		this.listResult = listResult;
	}
    

//Define the Userbutton cell
private class UserButtonCell extends TableCell<UserLevel,UserLevel> {
    final Button cellButton = new Button("user level");
    UserLevel userLevel;
    
    UserButtonCell(){
        
    	//Action when the button is pressed
        cellButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent t) {
            	//
            	SokobanDBManager sm = new SokobanDBManager();
            	table.getItems().clear();
            	table.refresh();
            	/*
            	listResult=sm.selectScore("UserLevel where UserName like '"+userLevel.getKey().getUserName()+"'");
            	table.getItems().addAll(listResult);*/
            	
            	setChanged();
            	notifyObservers("db select UserLevel where UserName like '"+userLevel.getKey().getUserName()+"'");
            	table.getItems().addAll(listResult);
                
            }
        });
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(UserLevel userLevel, boolean empty) {
       super.updateItem(userLevel, empty);
       if (userLevel!=null){
    	   setGraphic(cellButton);
    	   cellButton.setText(userLevel.getKey().getUserName());
    	   this.userLevel=userLevel;
       }
    }
}
  
//Define the Levelbutton cell
  private class LevelButtonCell extends TableCell<UserLevel,UserLevel> {
      final Button cellButton = new Button("user level");
      UserLevel userLevel;
      
      LevelButtonCell(){
          
      	//Action when the button is pressed
          cellButton.setOnAction(new EventHandler<ActionEvent>(){

              @Override
              public void handle(ActionEvent t) {
              	//
              	SokobanDBManager sm = new SokobanDBManager();
              	table.getItems().clear();
            	table.refresh();
            	//listResult=sm.selectScore("UserLevel where LevelName like '"+userLevel.getKey().getLevelName()+"'");
            	//table.getItems().addAll(listResult);
              	setChanged();
              	notifyObservers("db select UserLevel where LevelName like '"+userLevel.getKey().getLevelName()+"'");
                table.getItems().addAll(listResult);  
              }
          });
      }

      //Display button if the row is not empty
      @Override
      protected void updateItem(UserLevel userLevel, boolean empty) {
         super.updateItem(userLevel, empty);
         if (userLevel!=null){
      	   setGraphic(cellButton);
      	   cellButton.setText(userLevel.getKey().getLevelName());
      	   this.userLevel=userLevel;
         }
      }
    
}






}

