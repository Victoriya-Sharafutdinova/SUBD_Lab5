package form;

import java.awt.EventQueue;

import javafx.scene.control.ComboBox;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JMenu;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;



import com.sun.glass.events.WindowEvent;

import Entities.Customer;
import Entities.Order;
import Entities.Master;
import Entities.Device;
import Entities.Accessories;
import Entities.Device_accessories;
import form.FormNewConnection;


public class FormMain {
	JComboBox ComboBox;
	private JFrame frame;
	private JTable table;
	Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMain window = new FormMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		String[] str = { "Заказчик", "Заказ", "Мастер", "Устройство", "Комплектующее", "Связь устройства и комплектующего" };
		frame = new JFrame();
		frame.setBounds(100, 100, 679, 312);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

	
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menuConnection = new JMenu("\u041F\u043E\u0434\u043A\u043B\u044E\u0447\u0435\u043D\u0438\u0435");
		menuBar.add(menuConnection);
		
		JMenuItem menuItemNew = new JMenuItem("\u041D\u043E\u0432\u043E\u0435 \u043F\u043E\u0434\u043A\u043B\u044E\u0447\u0435\u043D\u0438\u0435");
		menuItemNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FormNewConnection form = new FormNewConnection(frame);
				connection = form.getConnection();
				if (connection != null) {
					
				}
			}
		});
		menuConnection.add(menuItemNew);
		
		
		JMenuItem menuItemShutdown = new JMenuItem("\u041E\u0442\u043A\u043B\u044E\u0447\u0435\u043D\u0438\u0435");
		menuItemShutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Disconnect();
				for( int i=0; i< table.getRowCount(); i++){
					
				}
			}
		});
		
		menuConnection.add(menuItemShutdown);
		
		JMenuItem menuItemExit = new JMenuItem("\u0412\u044B\u0445\u043E\u0434");
		menuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		menuConnection.add(menuItemExit);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 661, 250);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.setBounds(12, 13, 523, 218);
		panel.add(table);
		
		ComboBox = new JComboBox(str);
		ComboBox.setBounds(547, 9, 102, 22);
		ComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
		});
		panel.add(ComboBox);
		
		JButton buttonAdd = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		buttonAdd.setBounds(547, 61, 97, 25);
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (connection != null) {
					switch ((String) ComboBox.getSelectedItem()) {
					case "Заказчик":
						try {
							CustomerForm form1 = new CustomerForm(connection);
							form1.setVisible(true);
							Customer cu = new Customer();
							table.setModel(cu.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Заказ":
						try {
							OrderForm form2 = new OrderForm(connection);
							form2.setVisible(true);
							Order or = new Order();
							table.setModel(or.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Мастер":
						try {
							MasterForm form3 = new MasterForm(connection);
							form3.setVisible(true);
							Master m = new Master();
							table.setModel(m.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Устройство":
						try {
							DeviceForm form4 = new DeviceForm(connection);
							form4.setVisible(true);
							Device d = new Device();
							table.setModel(d.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Комплектующее":
						try {
							AccessoriesForm form5 = new AccessoriesForm(connection);
							form5.setVisible(true);
							Accessories ac = new Accessories();
							table.setModel(ac.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Связь устройства и комплектующего":
						try {
							Device_accessoriesForm form6 = new Device_accessoriesForm(connection);
							form6.setVisible(true);
							Device_accessories da = new Device_accessories();
							table.setModel(da.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					}
				}
			}
		});
		panel.add(buttonAdd);
		
		JButton buttonDel = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		buttonDel.setBounds(547, 99, 97, 25);
		buttonDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() >= 0 && connection != null) {
					int idEl = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					switch ((String) ComboBox.getSelectedItem()) {
					case "Заказчик":
						try {
							Customer cu = new Customer();
							cu.removeElement(idEl, connection);
							table.setModel(cu.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Заказ":
						try {
							Order or = new Order();
							or.removeElement(idEl, connection);
							table.setModel(or.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Мастер":
						try {
							Master m = new Master();
							m.removeElement(idEl, connection);
							table.setModel(m.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Устройство":
						try {
							Device d = new Device();
							d.removeElement(idEl, connection);
							table.setModel(d.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Комплектующее":
						try {
							Accessories ac = new Accessories();
							ac.removeElement(idEl, connection);
							table.setModel(ac.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Связь устройства и комплектующего":
						try {
							Device_accessories da = new Device_accessories();
							da.removeElement(idEl, connection);
							table.setModel(da.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					}
				} else if (connection != null) {
					JOptionPane.showMessageDialog(null, "Выберите элемент");
				}
			}
		});
		panel.add(buttonDel);
		
		JButton buttonCh = new JButton("\u0418\u0437\u043C\u0435\u043D\u0438\u0442\u044C");
		buttonCh.setBounds(547, 137, 97, 25);
		buttonCh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() >= 0 && connection != null) {
					int idEl = (table.getSelectedRow());
					switch ((String) ComboBox.getSelectedItem()) {
					case "Заказчик":
						try {
							Customer cu = new Customer();
							int i = cu.getTable(connection).get(idEl).getId();
							CustomerForm form1 = new CustomerForm(i, connection);
							form1.setVisible(true);
							Customer g = new Customer();
							table.setModel(g.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Заказ":
						try {
							Order or = new Order();
							int i = or.getTable(connection).get(idEl).getId();
							OrderForm form2 = new OrderForm(i, connection);
							form2.setVisible(true);
							Order o = new Order();
							table.setModel(o.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Мастер":
						try {
							Master m = new Master();
							int i = m.getTable(connection).get(idEl).getId();
							MasterForm form3 = new MasterForm(i, connection);
							form3.setVisible(true);
							Master ma = new Master();
							table.setModel(ma.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Устройство":
						try {
							Device d = new Device();
							int i = d.getTable(connection).get(idEl).getId();
							DeviceForm form4 = new DeviceForm(i, connection);
							form4.setVisible(true);
							Device de = new Device();
							table.setModel(de.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Комплектующее":
						try {
							Accessories ac = new Accessories();
							int i = ac.getTable(connection).get(idEl).getId();
							AccessoriesForm form5 = new AccessoriesForm(i, connection);
							form5.setVisible(true);
							Accessories a = new Accessories();
							table.setModel(a.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					case "Связь устройства и комплектующего":
						try {
							Device_accessories da = new Device_accessories();
							int i = da.getTable(connection).get(idEl).getId();
							Device_accessoriesForm form6 = new Device_accessoriesForm(i, connection);
							form6.setSize(400, 400);
							form6.setVisible(true);
							Device_accessories ad = new Device_accessories();
							table.setModel(ad.TableModel(connection));
						} catch (SQLException ex) {
							Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					}
				} else if (connection != null) {
					JOptionPane.showMessageDialog(null, "Выберите элемент");
				}
			}
		});
		
		panel.add(buttonCh);
		
	
				
		
		JButton buttonUpd = new JButton("\u041E\u0431\u043D\u043E\u0432\u0438\u0442\u044C");
		buttonUpd.setBounds(547, 175, 97, 25);
		buttonUpd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
		});
		
		panel.add(buttonUpd);
		
		

		
		}
		private void Disconnect() {
			if (connection != null) {
				connection = null;
				JOptionPane.showMessageDialog(null, "Соеденение закрыто");
			}
		}

		private void refresh() {
			if (connection != null) {
				switch ((String) ComboBox.getSelectedItem()) {
				case "Заказчик":
					
					try {
						Customer cu = new Customer();
						table.setModel(cu.TableModel(connection));
					} catch (SQLException ex) {
						Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				case "Заказ":
					try {
						Order or = new Order();
						table.setModel(or.TableModel(connection));
					} catch (SQLException ex) {
						Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				case "Мастер":
					try {
						Master m = new Master();
						table.setModel(m.TableModel(connection));
					} catch (SQLException ex) {
						Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				case "Устройство":
					try {
						Device d = new Device();
						table.setModel(d.TableModel(connection));
					} catch (SQLException ex) {
						Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				case "Комплектующее":
					try {
						Accessories ac = new Accessories();
						table.setModel(ac.TableModel(connection));
					} catch (SQLException ex) {
						Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				case "Связь устройства и комплектующего":
					try {
						Device_accessories da = new Device_accessories();
						table.setModel(da.TableModel(connection));
					} catch (SQLException ex) {
						Logger.getLogger(FormMain.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				}
			}
		
	}
}
