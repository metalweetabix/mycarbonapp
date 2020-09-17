/*
 * @author Cian O Cathasaigh R00176824
 */

package model;

public class actObject implements java.io.Serializable {
	private int week;
	private String actName;
	private String date;
	private int actPoints;
	
	public actObject(int newWeek, String newName, String newDate, int newPoints) {
		this.week = newWeek;
		this.actName = newName;
		this.date = newDate;
		this.actPoints = newPoints;
	}
	
	public String getName() {
		return actName;
	}
	
	public int getPoints() {
		return actPoints;
	}
	
	public String toString() {
		return "Week " +  week + ", " + date + ": " + actName + ", " + actPoints + " Points\n"; 	
	}
}
