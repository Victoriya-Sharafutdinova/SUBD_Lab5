package Entities;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Customer {
	private int id;
	private String fullname;
	private int phone;
	private ArrayList<Order> orders;
	
	public Customer(int id, String fullname, int phone){
		this.id = id;
		this.fullname = fullname;
		this.phone = phone;
		this.orders = new ArrayList<Order>();
	}
	
	public Customer(String fullname, int phone){
		this.fullname = fullname;
		this.phone = phone;
		this.orders = new ArrayList<Order>();
	}
	
	public Customer(){
		
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
	
	public void setFullName(String name){
		this.fullname = name;
	}
	
	public int getPhone(){
		return phone;
	}
	
	public void setPhone(int phone){
		this.phone = phone;
	}
	
	@Override
	public String toString(){
		return fullname;
	}
	
	public Vector <Object> setData(Connection connection) throws SQLException{
		Vector<Object> data = new Vector<Object>();
		data.add(id);
		data.add(fullname);
		data.add(phone);
		
		return data;
	}
	
	public void addElement(String fullname, int phone, Connection connection) throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("insert into customer values( nextval('seq_customer'), '" + fullname + "', " + phone + ");");
	}

	public void removeElement(int id, Connection connection) throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("delete from customer where customerid = " + id + ";");
	}
	
	public void refreshElement(int id, String fullname, int phone, Connection connection) throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("update customer set fullname = '" + fullname + "', phone = " + phone + " where customerid = " + id + ";");
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
		columnNames.add("ФИО");
		columnNames.add("Номер");
		return columnNames;
	}

	public ArrayList<Customer> getTable(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from customer;");
		ArrayList<Customer> res = new ArrayList<>();
		while (rs.next()) {
			res.add(new Customer((int) rs.getObject(1), rs.getObject(2).toString(), (int) rs.getObject(3)));
		}
		return res;
	}



}
