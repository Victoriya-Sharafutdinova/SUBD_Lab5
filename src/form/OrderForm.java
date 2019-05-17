package form;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entities.Master;
import Entities.Device;
import Entities.Customer;
import Entities.Order;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class OrderForm extends JFrame {

	private Connection connection = null;
	public int idSelected;
	private JPanel contentPane;

	private JPanel panel;
	private JTextField textFieldBreakdown;
	private JTextField textFieldDate;
	private JTextField textFieldSum;
	private JComboBox comboBoxCustomer;
	private JComboBox comboBoxDevice;
	private JComboBox comboBoxMaster;
	private ArrayList<Device> device;
	private ArrayList<Customer> customer;
	private ArrayList<Master> master;
	private JButton buttonSave;
	private JButton buttonCancel;



	public OrderForm(Connection connection) throws SQLException  {
		initialize();
		this.connection = connection;
		this.idSelected = -1;
		
		Customer cu = new Customer();
		customer = new ArrayList<>(cu.getTable(connection));
		comboBoxCustomer.removeAllItems();
		for (int i = 0; i < customer.size(); i++) {
			comboBoxCustomer.addItem("" + customer.get(i).getFullName());
		}
		Master m = new Master();
		master = new ArrayList<>(m.getTable(connection));
		comboBoxMaster.removeAllItems();
		for (int i = 0; i < master.size(); i++) {
			comboBoxMaster.addItem("" + master.get(i).getFullName());
		}
		Device d = new Device();
		device = new ArrayList<>(d.getTable(connection));
		comboBoxDevice.removeAllItems();
		for (int i = 0; i < device.size(); i++) {
			comboBoxDevice.addItem("" + device.get(i).getType());
		}
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public OrderForm(int id, Connection connection) throws SQLException  {
		initialize();
		
		int cid = 0;
		int mid = 0;
		int did = 0;
		this.connection = connection;
		this.idSelected = id;
		Order o = new Order();
		ArrayList<Order >order = new ArrayList<>(o.getTable(connection));
		o = null;
		for (int i = 0; i < order.size(); i++) {
			if (id == order.get(i).getId()) {
				o = order.get(i);
				break;
			}
		}
		
		Customer cu = new Customer();
		customer = new ArrayList<>(cu.getTable(connection));
		comboBoxCustomer.removeAllItems();
		for (int i = 0; i < customer.size(); i++) {
			comboBoxCustomer.addItem("" + customer.get(i).getFullName());
			if (o.getCustomerid() == customer.get(i).getId()) {
				cid = i;
			}
		}
		Master m = new Master();
		master = new ArrayList<>(m.getTable(connection));
		comboBoxMaster.removeAllItems();
		for (int i = 0; i < master.size(); i++) {
			comboBoxMaster.addItem("" + master.get(i).getFullName());
			if (o.getMasterid() == master.get(i).getId()) {
				mid = i;
			}
		}
		Device d = new Device();
		device = new ArrayList<>(d.getTable(connection));
		comboBoxDevice.removeAllItems();
		for (int i = 0; i < device.size(); i++) {
			comboBoxDevice.addItem("" + device.get(i).getType());
			if (o.getDeviceid() == device.get(i).getId()) {
				did = i;
			}

		}
		textFieldBreakdown.setText(o.getBreakdown());
		textFieldDate.setText(o.getDate());
		//textFieldSum.setText(o.getSum());
		comboBoxCustomer.setSelectedItem(customer.get(cid).getFullName());
		comboBoxMaster.setSelectedItem(master.get(mid).getFullName());
		comboBoxDevice.setSelectedItem(device.get(did).getType());

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		setContentPane(panel);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u0412\u0438\u0434 \u043F\u043E\u043B\u043E\u043C\u043A\u0438");
		label.setBounds(12, 13, 90, 16);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u0414\u0430\u0442\u0430");
		label_1.setBounds(12, 49, 56, 16);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u0421\u0443\u043C\u043C\u0430 \u0437\u0430\u043A\u0430\u0437\u0430");
		label_2.setBounds(12, 88, 90, 16);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u0417\u0430\u043A\u0430\u0437\u0447\u0438\u043A");
		label_3.setBounds(12, 126, 56, 16);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u0422\u0435\u0445\u043D\u0438\u043A\u0430");
		label_4.setBounds(12, 162, 56, 16);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("\u041C\u0430\u0441\u0442\u0435\u0440");
		label_5.setBounds(12, 201, 56, 16);
		getContentPane().add(label_5);
		
		textFieldBreakdown = new JTextField();
		textFieldBreakdown.setBounds(121, 10, 116, 22);
		getContentPane().add(textFieldBreakdown);
		textFieldBreakdown.setColumns(10);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(121, 46, 116, 22);
		getContentPane().add(textFieldDate);
		textFieldDate.setColumns(10);
		
		textFieldSum = new JTextField();
		textFieldSum.setBounds(121, 85, 116, 22);
		getContentPane().add(textFieldSum);
		textFieldSum.setColumns(10);
		
		comboBoxCustomer = new JComboBox();
		comboBoxCustomer.setBounds(121, 123, 116, 22);
		getContentPane().add(comboBoxCustomer);
		
		comboBoxDevice = new JComboBox();
		comboBoxDevice.setBounds(121, 159, 116, 22);
		getContentPane().add(comboBoxDevice);
		
		comboBoxMaster = new JComboBox();
		comboBoxMaster.setBounds(121, 198, 116, 22);
		getContentPane().add(comboBoxMaster);
		
		buttonSave = new JButton("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		buttonSave.setBounds(278, 109, 97, 25);
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Order order = null;
				if (textFieldBreakdown.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Îøèáêà");
				}
				try {
					order = new Order(textFieldBreakdown.getText(), textFieldDate
							.getText(),Integer.parseInt(textFieldSum.getText()), (comboBoxCustomer.getSelectedIndex()),
							(comboBoxMaster.getSelectedIndex()), (comboBoxDevice.getSelectedIndex()));
					if (idSelected < 0) {
						order.addElement(textFieldBreakdown.getText(), textFieldDate
								.getText(),Integer.parseInt(textFieldSum.getText()),
								customer.get(comboBoxCustomer.getSelectedIndex()).getId(),
								master.get(comboBoxMaster.getSelectedIndex()).getId(), 
								device.get(comboBoxDevice.getSelectedIndex()).getId(), connection);
					} else {
						order.refreshElement(idSelected,textFieldBreakdown.getText(), textFieldDate
								.getText(),Integer.parseInt(textFieldSum.getText()),
								customer.get(comboBoxCustomer.getSelectedIndex()).getId(),
								master.get(comboBoxMaster.getSelectedIndex()).getId(), 
								device.get(comboBoxDevice.getSelectedIndex()).getId(), connection);
					}
					setVisible(false);
				} catch (SQLException ex) {
					Logger.getLogger(OrderForm.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		});

		getContentPane().add(buttonSave);
		
		buttonCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		buttonCancel.setBounds(278, 158, 97, 25);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		getContentPane().add(buttonCancel);
	}

}
