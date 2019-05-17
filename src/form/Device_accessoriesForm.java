package form;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Entities.Device;
import Entities.Device_accessories;
import Entities.Accessories;


public class Device_accessoriesForm extends JFrame  {

	private JFrame frame;
	private Connection connection = null;
	public int idSelected;
	private JComboBox comboBoxDevice;
	private JComboBox comboBoxAccessories;
	private ArrayList<Device> device;
	private ArrayList<Accessories> accessories;

	/**
	 * @wbp.parser.constructor
	 */
	public Device_accessoriesForm(Connection connection) throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		initialize();
		this.connection = connection;
		this.idSelected = -1;
		

		Accessories a = new Accessories();
		accessories = new ArrayList<>(a.getTable(connection));
		comboBoxAccessories.removeAllItems();
		for (int i = 0; i < accessories.size(); i++) {
			comboBoxAccessories.addItem("" + accessories.get(i).getType());
		}
		Device d = new Device();
		device = new ArrayList<>(d.getTable(connection));
		comboBoxDevice.removeAllItems();
		for (int i = 0; i < device.size(); i++) {
			comboBoxDevice.addItem("" + device.get(i).getType());
		}
	}
	
	public Device_accessoriesForm(int id, Connection connection) throws SQLException {
		
		initialize();
		int did = 0;
		int aid = 0;
		this.connection = connection;
		this.idSelected = id;
		
		Device_accessories da = new Device_accessories();
		ArrayList<Device_accessories> dev_acc = new ArrayList<>(da.getTable(connection));
		da = null;
		for(int i = 0; i < dev_acc.size(); i++){
			if(id == dev_acc.get(i).getId()){
				da = dev_acc.get(i);
				break;
			}
		}
		Device d = new Device();
		device = new ArrayList<>(d.getTable(connection));
		comboBoxDevice.removeAllItems();
		for (int i = 0; i < device.size(); i++) {
			comboBoxDevice.addItem("" + device.get(i).getType());
			if(da.getDeviceId() == device.get(i).getId()){
				did = i;
			}
		}
		Accessories a = new Accessories();
		accessories = new ArrayList<>(a.getTable(connection));
		comboBoxAccessories.removeAllItems();
		for (int i = 0; i < accessories.size(); i++) {
			comboBoxAccessories.addItem("" + accessories.get(i).getType());
			if(da.getAccessoriesId() == accessories.get(i).getId()){
				aid = i;
			}
		}
		comboBoxDevice.setSelectedItem(device.get(did).getType());
		comboBoxAccessories.setSelectedItem(accessories.get(aid).getType());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 354, 264);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u0423\u0441\u0442\u0440\u043E\u0439\u0441\u0442\u0432\u043E");
		lblNewLabel.setBounds(12, 48, 116, 16);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("\u041A\u043E\u043C\u043F\u043B\u0435\u043A\u0442\u0443\u044E\u0449\u0435\u0435");
		label.setBounds(12, 93, 116, 16);
		panel.add(label);
		
		comboBoxDevice = new JComboBox();
		comboBoxDevice.setBounds(150, 45, 109, 22);
		panel.add(comboBoxDevice);
		
		comboBoxAccessories = new JComboBox();
		comboBoxAccessories.setBounds(150, 90, 109, 22);
		panel.add(comboBoxAccessories);
		
		JButton buttonSave = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		buttonSave.setBounds(102, 192, 97, 25);
		panel.add(buttonSave);
		
		JButton buttonCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		buttonCancel.setBounds(226, 192, 97, 25);
		panel.add(buttonCancel);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Device_accessories device_accessories = null;
				
				try {
					device_accessories = new Device_accessories( (comboBoxDevice.getSelectedIndex()),
							(comboBoxAccessories.getSelectedIndex()));
					if (idSelected < 0) {
						device_accessories.addElement(
								device.get(comboBoxDevice.getSelectedIndex()).getId(),
								accessories.get(comboBoxAccessories.getSelectedIndex())
										.getId(), connection);
					} else {
						device_accessories.refreshElement(idSelected, 
								device.get(comboBoxDevice.getSelectedIndex()).getId(),
								accessories.get(comboBoxAccessories.getSelectedIndex())
										.getId(), connection);
					}
					setVisible(false);
				} catch (SQLException ex) {
					Logger.getLogger(Device_accessoriesForm.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		});
	}
}
