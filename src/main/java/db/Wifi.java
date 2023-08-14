package db;

import java.util.Date;

public class Wifi {
    private double distance;
    private String number;
    private String borough;
    private String name;
    private String street_address;
    private String detail_address;
    private String installation_location;
    private String installation_type;
    private String installation_agency;
    private String service;
    private String net;
    private int year_of_installation;
    private String in_and_out;
    private String connection_environment;
    private double x_coordinate;
    private double y_coordinate;
    private Date working_date;
    
    
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getBorough() {
		return borough;
	}
	public void setBorough(String borough) {
		this.borough = borough;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet_address() {
		return street_address;
	}
	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}
	
	
	public String getDetail_address() {
		return detail_address;
	}
	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}
	public String getInstallation_location() {
		return installation_location;
	}
	public void setInstallation_location(String installation_location) {
		this.installation_location = installation_location;
	}
	public String getInstallation_type() {
		return installation_type;
	}
	public void setInstallation_type(String installation_type) {
		this.installation_type = installation_type;
	}
	public String getInstallation_agency() {
		return installation_agency;
	}
	public void setInstallation_agency(String installation_agency) {
		this.installation_agency = installation_agency;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	
	
	public String getNet() {
		return net;
	}
	public void setNet(String net) {
		this.net = net;
	}
	public int getYear_of_installation() {
		return year_of_installation;
	}
	public void setYear_of_installation(int year_of_installation) {
		this.year_of_installation = year_of_installation;
	}
	public String getIn_and_out() {
		return in_and_out;
	}
	public void setIn_and_out(String in_and_out) {
		this.in_and_out = in_and_out;
	}
	public String getConnection_environment() {
		return connection_environment;
	}
	public void setConnection_environment(String connection_environment) {
		this.connection_environment = connection_environment;
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
	public Date getWorking_date() {
		return working_date;
	}
	public void setWorking_date(Date working_date) {
		this.working_date = working_date;
	}
}
