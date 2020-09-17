/*
 * @author Cian O Cathasaigh R00176824
 */

package controller;

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

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.actObject;

public class handler {
	public static actObject handleAdd(TextField weekBox, DatePicker dateBox, TextField actBox, TextField pointsBox) {
        	String readWeek = weekBox.getText();
        	int newWeek = Integer.parseInt(readWeek);
        	LocalDate readDate = dateBox.getValue();
        	String newDate = readDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        	String newName = actBox.getText();
        	String readPoints = pointsBox.getText();
        	int newPoints = Integer.parseInt(readPoints);
        	actObject newAct = new actObject(newWeek, newName, newDate, newPoints);	        	
        	return newAct;	
	}
	/*
	 * @param adds activities to the list
	 */
	
	public static void handleRemove(ActionEvent e, ArrayList<actObject> actList, 
			int pointsToTake, Object item, Label points, int pointsValue, TextArea outputField) {
		ArrayList<actObject> options = comboBox(e, actList);
      	ChoiceDialog<actObject> dialog = new ChoiceDialog<>(options.get(0), options);
      	dialog.setTitle("Remove Activity");
      	dialog.setContentText("Choose activity");

      	Optional<actObject> result = dialog.showAndWait();
      	if (result.isPresent()){
      		int index = actList.indexOf(result);
      		if (index < 0) {
      			index = 0;
      		}
      		pointsToTake = actList.get(index).getPoints();
      	    actList.remove(result.get());
      	    outputField.setText(actList.toString());
      	}
          pointsValue -= pointsToTake;
          points.setText("Points: " + pointsValue);
      }
		private static ArrayList<actObject> comboBox(ActionEvent e, ArrayList<actObject> actList) {
			ArrayList<actObject> options = new ArrayList<>(
	  				actList);
	  		return options;
	}
	/*
	 * @param removes acivities from the list
	 */

	
	public static void handleSave(ArrayList<actObject> actList) {
		try {
      		FileOutputStream filestream;
				filestream = new FileOutputStream("activities.json");			
            	ObjectOutputStream objectstream = new ObjectOutputStream(filestream);            	
				objectstream.writeObject(actList);					
           		objectstream.close();
      	} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	/*
	 * @param saves activities to a file
	 */
	
	public static void handleLoad(ArrayList<actObject> actList, int pointsValue, Label points, TextArea outputField) {
		pointsValue = 0;
      	for (int i = 0; i < actList.size(); i++) {
      		actList.remove(i);
      	}
      	try {
      		FileInputStream filestream = new FileInputStream("activities.json");
      		ObjectInputStream objectstream = new ObjectInputStream(filestream);
      		ArrayList tempList = (ArrayList<actObject>) objectstream.readObject();
      		objectstream.close();
      		actList.addAll(tempList);
      	} catch (IOException | ClassNotFoundException e1) {
      		// TODO Auto-generated catch block
      		e1.printStackTrace();
      	}
      	outputField.setText(actList.toString());
      	for (int i = 0; i < actList.size(); i++) {
      		pointsValue += actList.get(i).getPoints();
      	}
      	points.setText("Points: " + pointsValue);
	}
	/*
	 * @param clears current acitivity list and loads new one from a file
	 */
}
