package Entities;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Accessories {
	private int id;
	private String type;
	private String manufacturer;
	private String model;
	private ArrayList<Device_accessories> dev_acc;

	
	public Accessories(int id, String type, String manufacturer, String model ){
		this.id = id;
		this.type = type;
		this.manufacturer = manufacturer;
		this.model = model;
		this.dev_acc = new ArrayList<Device_accessories>();
	}
	
	public Accessories(String type, String manufacturer, String model ){
		this.type = type;
		this.manufacturer = manufacturer;
		this.model = model;
		this.dev_acc = new ArrayList<Device_accessories>();

	}
	
	public Accessories(){
		
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
		statement.executeUpdate("insert into accessories values(nextval('seq_accessories'),'" + type + "', '" + manufacturer + "', '" + model + "');");
	}

	public void removeElement(int id, Connection connection) throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("delete from accessories where accessoriesid = " + id + ";");
	}

	public void refreshElement(int id, String type, String manufacturer, String model, Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("update accessories set type = '" + type + "', manufacturer = '" + manufacturer + "', model = '" + model + "' where accessoriesid = " + id + ";");
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

	public ArrayList<Accessories> getTable(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from accessories;");
		ArrayList<Accessories> res = new ArrayList<>();
		while (rs.next()) {
			res.add(new Accessories((int) rs.getObject(1), rs.getObject(2).toString(), rs.getObject(3).toString(), rs.getObject(4).toString()));
		}
		return res;
	}
	
}
