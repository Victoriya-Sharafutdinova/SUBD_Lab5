package Entities;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Order {
	private int id;
	private String breakdown;
	private String date;
	private int sum;
	private int customerid;
	private int deviceid;
	private int masterid;
	
	public Order(int id, String breakdown, String date, int sum, int customerid, 
			 int masterid, int deviceid){
		this.id = id;
		this.breakdown = breakdown;
		this.date = date;
		this.sum = sum;
		this.customerid = customerid;
		this.deviceid = deviceid;
		this.masterid = masterid;
	}
	
	public Order(String breakdown, String date, int sum, int customerid, 
			int masterid, int deviceid){
		this.breakdown = breakdown;
		this.date = date;
		this.sum = sum;
		this.customerid = customerid;
		this.deviceid = deviceid;
		this.masterid = masterid;
	}
	
	public Order(){
		
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getBreakdown(){
		return breakdown;
	}
	
	public void setBreakdown(String breakdown){
		this.breakdown = breakdown;
	}
	
	public String getDate(){
		return date;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public int getSum(){
		return sum;
	}
	
	public void setSum(int sum){
		this.sum = sum;
	}
	
	public int getCustomerid(){
		return customerid;
	}
	
	public void setCustomerid(int customerid){
		this.customerid = customerid;
	}
	
	public int getDeviceid(){
		return deviceid;
	}
	
	public void setDeviceid(int deviceid){
		this.deviceid = deviceid;
	}
	
	public int getMasterid(){
		return masterid;
	}
	
	public void setMasterid(int masterid){
		this.masterid = masterid;
	}
	
	public Vector<Object> setData(Connection connection) throws SQLException {
		Vector<Object> data = new Vector<Object>();
		data.add(id);
		data.add(breakdown);
		data.add(date);
		data.add(sum);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select fullname from customer where customerid = " + customerid + ";");
		while (rs.next()) {
			data.add(rs.getString("fullname"));		
		}
		rs = statement.executeQuery("select fullname from master where masterid = " + masterid + ";");
		while (rs.next()) {
			data.add(rs.getString("fullname"));
		}
		rs = statement.executeQuery("select type from device where deviceid = " + deviceid + ";");
		while (rs.next()) {
			data.add(rs.getString("type"));
		}
		return data;

	}

	public void addElement(String breakdown, String date, int sum, int customerid, int masterid, int deviceid, Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("insert into orders values(nextval('seq_orders'),'" + breakdown + "', '" + date
				+ "', " + sum + ", " + customerid + ", " + masterid + ", " + deviceid + ");");
	}

	public void removeElement(int id, Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("delete from orders where orderid = " + id + ";");
	}

	public void refreshElement(int id, String breakdown, String date, int sum, int customerid,
			int masterid, int deviceid, Connection connection) throws SQLException {		
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("update orders set breakdown = '" + breakdown + "',date = '" + date
				+ "', sum = " + sum + ", customerid = " + customerid + ", masterid = " + masterid + ", deviceid = " + deviceid + " where orderid = " + id + ";");
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
		columnNames.add("Вид поломки");
		columnNames.add("Дата");
		columnNames.add("Сумма");
		columnNames.add("Заказчик");
		columnNames.add("Мастер");
		columnNames.add("Техника");
		return columnNames;
	}

	public ArrayList<Order> getTable(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from orders");
		ArrayList<Order> res = new ArrayList<>();
		while (rs.next()) {
			res.add(new Order((int) rs.getObject(1), rs.getObject(2).toString(), rs.getObject(3).toString(),
					(int) rs.getObject(4), (int) rs.getObject(5), (int) rs.getObject(6), (int) rs.getObject(7)));
		}
		return res;
	}


	
}
