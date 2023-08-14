package db;

import java.util.Date;

public class History {
	private int id;
	private double x_coordinate;
	private double y_coordinate;
	private Date datetime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getX_coordinate() {
		return x_coordinate;
	}
	public void setX_coordinate(double x_coordinate) {
		this.x_coordinate = x_coordinate;
	}
	public double getY_coordinate() {
		return y_coordinate;
	}
	public void setY_coordinate(double y_coordinate) {
		this.y_coordinate = y_coordinate;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
}
