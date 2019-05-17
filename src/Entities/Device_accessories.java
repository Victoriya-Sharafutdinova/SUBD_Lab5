

package Entities;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Device_accessories {
	private int id;
	private int accessoriesid;
	private int deviceid;
	
	public Device_accessories(int id, int deviceid, int accessoriesid){
		this.id = id;
		this.deviceid = deviceid;
		this.accessoriesid = accessoriesid;

	}
	
	public Device_accessories(int deviceid, int  accessoriesid){
		this.deviceid = deviceid;
		this.accessoriesid = accessoriesid;
		
	}
	
	public Device_accessories(){
		
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(){
		this.id = id;
	}
	
	public int getAccessoriesId(){
		return accessoriesid;
	}
	
	public void setAccessoriesId(int accessoriesid){
		this.accessoriesid = accessoriesid;
	}
	
	public int getDeviceId(){
		return deviceid;
	}
	
	public void setDeviceId(int deviceid){
		this.deviceid = deviceid;
	}
	
	public Vector<Object> setData(Connection connection) throws SQLException {
		Vector<Object> data = new Vector<Object>();
		data.add(id);	
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select type from device where deviceid = " + deviceid + ";");
		while (rs.next()) {
			data.add(rs.getString("type"));
		}
		rs = statement.executeQuery("select type from accessories where accessoriesid = " + accessoriesid + ";");
		while (rs.next()) {
			data.add(rs.getString("type"));
		}
		
		return data;
	}

	public void addElement( int accessoriesid, int deviceid, Connection connection)
			throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("insert into device_accessories values(nextval('da_seq')," 
				 + deviceid + ", " +  accessoriesid+ ");");
	}

	public void removeElement(int id, Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("delete from device_accessories where id = " + id + ";");
	}

	public void refreshElement(int id, int accessoriesid, int deviceid,
			Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("update device_accessories set " + "', deviceid = " + deviceid +
				", accessoriesid = " + accessoriesid + " where id = " + id + ";");
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
		columnNames.add("Устройство");
		columnNames.add("Комплектующее");
		return columnNames;
	}

	public ArrayList<Device_accessories> getTable(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from device_accessories");
		ArrayList<Device_accessories> res = new ArrayList<>();
		while (rs.next()) {
			res.add(new Device_accessories((int) rs.getObject(1),(int) rs.getObject(2), (int) rs.getObject(3)));
		}
		return res;
	}

	
}