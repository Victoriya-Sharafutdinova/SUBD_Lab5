package Entities;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Device {
	private int id;
	private String type;
	private String manufacturer;
	private String model;
	
	public Device(int id, String type, String manufacturer, String model ){
		this.id = id;
		this.type = type;
		this.manufacturer = manufacturer;
		this.model = model;
	}
	
	public Device(String type, String manufacturer, String model ){
		this.type = type;
		this.manufacturer = manufacturer;
		this.model = model;
	}
	
	public Device(){
		
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getManufacturer(){
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer){
		this.manufacturer = manufacturer;
	}
	
	public String getModel(){
		return model;
	}
	
	public void setModel(String model){
		this.model = model;
	}
	
	public Vector<Object> setData(Connection conection) throws SQLException {
		Vector<Object> data = new Vector<Object>();
		data.add(id);
		data.add(type);
		data.add(manufacturer);
		data.add(model);
		return data;
	}

	public void addElement(String type, String manufacturer, String model, Connection connection) throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("insert into device values(nextval('seq_device'),'" + type + "', '" + manufacturer + "', '" + model + "');");
	}

	public void removeElement(int id, Connection connection) throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("delete from device where deviceid = " + id + ";");
	}

	public void refreshElement(int id, String type, String manufacturer, String model, Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("update device set type = '" + type + "', manufacturer = '" + manufacturer + "', model = '" + model + "' where deviceid = " + id + ";");
	}

	public DefaultTableModel TableModel(Connection connection) throws SQLException {
		Vector<String> columnNames = null;
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		DefaultTableModel tableModel = new DefaultTableModel();
		columnNames = getTitles();
		for (int i = 0; i <= getTable(connection).size() - 1; i++) {
			data.add(getTable(connection).get(i).setData(connection));
		}
		tableModel.setDataVector(data, columnNames);
		return tableModel;
	}

	public Vector<String> getTitles() {
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("id");
		columnNames.add("Тип");
		columnNames.add("Производитель");
		columnNames.add("Модель");
		return columnNames;
	}

	public ArrayList<Device> getTable(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from device;");
		ArrayList<Device> res = new ArrayList<>();
		while (rs.next()) {
			res.add(new Device((int) rs.getObject(1), rs.getObject(2).toString(), rs.getObject(3).toString(), rs.getObject(4).toString()));
		}
		return res;
	}


}
