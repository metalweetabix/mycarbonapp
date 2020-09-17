/*
 * @author Cian O Cathasaigh R00176824
 */

package view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Optional;

import controller.handler;
import model.actObject;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class main extends Application {
	int pointsValue = 0;
	ArrayList<actObject> actList = new ArrayList<actObject>();
	Button btnAdd = new Button("Add");
    HBox hbBtnAdd = new HBox(10);
    Button btnRemove = new Button("Remove");
    HBox hbBtnRemove = new HBox(10);
    Button btnSave = new Button("Save");
    HBox hbBtnSave = new HBox(10);
    Button btnLoad = new Button("Load");
    HBox hbBtnLoad = new HBox(10);
    Label week = new Label("Week:");
    TextField weekBox = new TextField();
      
    Label date = new Label("Date:");
    DatePicker dateBox = new DatePicker();
      
    Label activity = new Label("Activity:");
      
    TextField actBox = new TextField();        
      
    TextArea outputField = 
    new TextArea("Activities to be displayed");
      
    Label pointsLabel = new Label("Points: ");
      
    Label points = new Label("Points: " + pointsValue);
    TextField pointsBox = new TextField();
	
	public static void main(String[] args) {
	      launch(args);
	  }

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub		
		primaryStage.setTitle("Check Your Carbon Footprint");
	    Group root = new Group();
	    Scene scene = new Scene(root, 700, 450);
	    	    
	    hbBtnAdd.setAlignment(Pos.BOTTOM_RIGHT);
	    hbBtnAdd.getChildren().add(btnAdd);
	      	    
	    hbBtnRemove.setAlignment(Pos.BOTTOM_LEFT);
	    hbBtnRemove.getChildren().add(btnRemove);

	    hbBtnSave.setAlignment(Pos.BOTTOM_LEFT);
	    hbBtnSave.getChildren().add(btnSave);
	      	    
	    hbBtnLoad.setAlignment(Pos.BOTTOM_LEFT);
	    hbBtnLoad.getChildren().add(btnLoad); 
	      
	    TabPane introPane = new TabPane();
	    Tab tab1 = new Tab();
	    tab1.setText("new tab");
	    tab1.setContent(new Rectangle(200,200, Color.LIGHTSTEELBLUE));
	    introPane.getTabs().add(tab1);
	            
	    final Text actiontarget = new Text();
	    final Text actiontarget3 = new Text();
	    final Text actiontarget4 = new Text();
	    final Text actiontarget5 = new Text();
	    
	    /*
	     *  @param these set up all the buttons
	     */
	    
	    btnAdd.setOnAction(new EventHandler<ActionEvent>() {	      	
	          @Override
	          public void handle(ActionEvent e) {
	          	actObject newAct = controller.handler.handleAdd(weekBox, dateBox, actBox, pointsBox);
	          	pointsValue += newAct.getPoints();
	          	points.setText("Points: " + pointsValue);
	          	actList.add(newAct);
	          	outputField.setText(actList.toString());
	          }          
	      });
	    
	    btnRemove.setOnAction(new EventHandler<ActionEvent>() {
	      	Object item;
	      	int pointsToTake;
	      	public ArrayList comboBox(ActionEvent e) {
	      		ArrayList<actObject> options = new ArrayList<>(
	      				actList);
	      		return options;
	      	}
	      	
	          @Override
	          public void handle(ActionEvent e) {
	          	controller.handler.handleRemove(e, actList, pointsToTake, item, points, pointsValue, outputField);
	          }
	      });
	    
	    btnSave.setOnAction(new EventHandler<ActionEvent>() {
	          @Override
	          public void handle(ActionEvent e) {
	        	  controller.handler.handleSave(actList);
				
	          }
	      });
	    
	    btnLoad.setOnAction(new EventHandler<ActionEvent>() {
	      	
	          @Override
	          public void handle(ActionEvent e) {
	          	controller.handler.handleLoad(actList, pointsValue, points, outputField);
	          }
	      });
	    
	    /*
	     *  @param these setOnAction functions call methods from the controller class, which is handler.java
	     * 
	     */
	    
	    TabPane tabPane = new TabPane();
	    BorderPane mainPane = new BorderPane();
	    
	    Tab introTab = new Tab();
	    introTab.setText("Intro");
	    Label welcomeText = new Label("Welcome! Check your carbon footprint.");
	    introTab.setContent(welcomeText);
	    tabPane.getTabs().add(introTab);
	    
	    Tab manageTab = new Tab();
	    manageTab.setText("Manage");
	    
	    /*
	     * @param sets up the tabs
	     */
	    
	    GridPane grid = new GridPane();
	    grid.setAlignment(Pos.BASELINE_LEFT);
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(25, 25, 25, 25));
	    grid.add(welcomeText, 0, 0, 2, 1);
	    grid.add(week, 0, 1);
	    grid.add(weekBox, 1, 1);
	    grid.add(dateBox, 1, 2);
	    grid.add(activity, 0, 3);
	    grid.add(date, 0, 2);      
	    grid.add(actBox, 1, 3);
	    grid.add(hbBtnAdd, 1, 5);
	    grid.add(pointsLabel, 0, 4);
	    grid.add(pointsBox, 1,4);
	    grid.add(actiontarget, 1, 5);
	    grid.add(hbBtnRemove, 0, 5);
	    grid.add(actiontarget3, 1, 5);
	    grid.add(hbBtnSave, 0, 8);
	    grid.add(actiontarget4, 1, 8);
	    grid.add(hbBtnLoad, 1, 8);
	    grid.add(actiontarget5, 2, 8);
	    VBox manageBox = new VBox();
	    manageBox.getChildren().add(grid);
	    manageTab.setContent(manageBox);
	    tabPane.getTabs().add(manageTab);
	    
	    Tab sumTab = new Tab();
	    sumTab.setText("Summary");
	    VBox sumBox = new VBox();
	    GridPane sumGrid = new GridPane();
	    sumGrid.setAlignment(Pos.BASELINE_LEFT);
	    sumGrid.setHgap(10);
	    sumGrid.setVgap(10);
	    sumGrid.setPadding(new Insets(25, 25, 25, 25));
	    sumGrid.add(points, 0, 4);
	      
	    sumGrid.add(outputField, 1, 7);
	    sumBox.getChildren().add(sumGrid);
	    sumTab.setContent(sumBox);
	    tabPane.getTabs().add(sumTab);
	    
	    mainPane.setCenter(tabPane);
	    
	    mainPane.prefHeightProperty().bind(scene.heightProperty());
	    mainPane.prefWidthProperty().bind(scene.widthProperty());
	    
	    root.getChildren().add(mainPane);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    
	    /*
	     * @param aligns everything on the grid and in the tabs correctly, then launches the gui
	     */
		
		
	}
	
}
