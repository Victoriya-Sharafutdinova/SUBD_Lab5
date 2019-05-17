package Entities;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Master {
	private int id;
	private String fullname;
	private int phone;
	private String position;
	private ArrayList<Order> orders;
	
	public Master(int id, String fullname, int phone, String position ){
		this.id = id;
		this.fullname = fullname;
		this.phone = phone;
		this.position = position;
		this.orders = new ArrayList<Order>();
	}
	
	public Master(String fullname, int phone, String position ){
		this.fullname = fullname;
		this.phone = phone;
		this.position = position;
		this.orders = new ArrayList<Order>();
	}
	
	public Master(){
		
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getFullName(){
		return fullname;
	}
	
	public void setFullName(String fullname){
		this.fullname = fullname;
	}
	
	public int getPhone(){
		return phone;
	}
	
	public void setPhone(int phone){
		this.phone = phone;
	}
	
	public String getPosition(){
		return position;
	}
	
	public void setPosition(String position){
		this.position = position;
	}
	
	public Vector<Object> setData(Connection conection) throws SQLException {
		Vector<Object> data = new Vector<Object>();
		data.add(id);
		data.add(fullname);
		data.add(phone);
		data.add(position);
		return data;
	}

	public void addElement(String fullname, int phone, String position, Connection connection) throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("insert into master values(nextval('seq_master'),'" + fullname+ "', " + phone + ", '" + position + "');");
	}

	public void removeElement(int id, Connection connection) throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("delete from master where masterid = " + id + ";");
	}

	public void refreshElement(int id, String fullname, int phone, String position, Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("update master set fullname = '" + fullname + "', phone = " + phone + ", position = '" + position + "' where masterid = " + id + ";");
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
		columnNames.add("Название");
		columnNames.add("Номер");
		columnNames.add("Должность");
		return columnNames;
	}

	public ArrayList<Master> getTable(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from master;");
		ArrayList<Master> res = new ArrayList<>();
		while (rs.next()) {
			res.add(new Master((int) rs.getObject(1), rs.getObject(2).toString(), (int) rs.getObject(3), rs.getObject(4).toString()));
		}
		return res;
	}

	
}
