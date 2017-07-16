package intento1;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLayeredPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;

public class VistaBuscar extends JFrame {
	private JTable table;
	private Controlador controlador;
	private String url = "jdbc:mysql://theshadowhunters.es/mis_policia_lista";
	private String pwd = "Qwerty1231";
	private String usr = "mis";
	private Connection conexion;
	public DefaultTableModel dftm = new DefaultTableModel();
	private Statement stmt;
	private JTextField textField;
	private JComboBox comboBox;
	private JButton btnBorrar;
	private FondoEscritorio contenedor = new FondoEscritorio();
	private File fichero;
	private JScrollPane scrollPane;
	public VistaBuscar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VistaLogin.class.getResource("/img/vive.png")));
		setContentPane(contenedor);

		contenedor.setImagen("/img/computer-10.jpg");

		contenedor.setBackground(Color.BLUE);
		conectar();
		actualizar();
		scrollPane = new JScrollPane();
		
		JButton btnVolver = new JButton("volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlador.buscarRegistrar();
			}
		});

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				if (!textField.equals("") && comboBox.getSelectedItem().equals("Nombre")) {
					while (dftm.getRowCount() > 0) {
						dftm.removeRow(0);
					}
					buscarNombre();
				} else {

					if (!textField.equals("") && comboBox.getSelectedItem().equals("Rango")) {
						while (dftm.getRowCount() > 0) {
							dftm.removeRow(0);
						}
						buscarRango();
					} else {
						while (dftm.getRowCount() > 0) {
							dftm.removeRow(0);
						}
						rellenarTabla();
					}
				}
			}
		});

		textField.setColumns(10);

		JLabel lblBuscador = new JLabel("Buscador: ");
		lblBuscador.setForeground(Color.WHITE);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"---------", "Nombre", "Rango"}));

		JButton btnRefrescar = new JButton("Refrescar");
		btnRefrescar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				while (dftm.getRowCount() > 0) {
					dftm.removeRow(0);
				}
				rellenarTabla();
			}
		});

		btnBorrar = new JButton("Borrar");

		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				eliminar();
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnVolver)
							.addComponent(lblBuscador))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(btnRefrescar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBorrar))
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnVolver)
					.addGap(21)
					.addComponent(lblBuscador)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRefrescar)
						.addComponent(btnBorrar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		table = new JTable(dftm);
		table.setBackground(Color.WHITE);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		dftm.addColumn("Nombre");
		dftm.addColumn("UID");
		dftm.addColumn("Rango");
		dftm.addColumn("Fecha_ascenso");
		dftm.addColumn("Ascendido_por");
		dftm.addColumn("Notas");
	
		rellenarTabla();

		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		contenedor.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{scrollPane, table, textField, btnRefrescar, btnBorrar, comboBox, btnVolver, lblBuscador}));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("VOM Buscar");
		setSize(1038, 542);
		setLocationRelativeTo(null);
	}

	public void conectar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(url, usr, pwd);
			System.err.println("ok");

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("nop");
		}
	}

	public ResultSet listas() throws SQLException {
		String seleccion = "SELECT * FROM ViveOMuere ";

		stmt = conexion.prepareStatement(seleccion);

		ResultSet rs = stmt.executeQuery(seleccion);

		return rs;
	}

	public ResultSet nombre() throws SQLException {
		String query = "SELECT * FROM ViveOMuere  WHERE NOMBRE LIKE '%" + textField.getText() + "%'";
		stmt = conexion.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}

	public ResultSet cuenta() throws SQLException {
		String query = "SELECT * FROM ViveOMuere  WHERE RANGO LIKE '%" + textField.getText() + "%'";
		stmt = conexion.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}

	public void buscarNombre() {

		try {
			ResultSet rs = nombre();
			while (rs.next()) {
				Object[] fila = new Object[16];	
				fila[0] = rs.getString("NOMBRE"); 
				fila[1] = rs.getString("UID");
				fila[2] = rs.getString("RANGO");
				fila[3] = rs.getString("FECHA_ASCENSO");
				fila[4] = rs.getString("ASCENDIDO_POR");
				fila[5] = rs.getString("NOTAS_USUARIO");
				dftm.addRow(fila); 
			}

			table.updateUI();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void buscarRango() {

		try {
			ResultSet rs = cuenta();
			while (rs.next()) {
				Object[] fila = new Object[16];
				fila[0] = rs.getString("NOMBRE"); 
				fila[1] = rs.getString("UID");
				fila[2] = rs.getString("RANGO");
				fila[3] = rs.getString("FECHA_ASCENSO");
				fila[4] = rs.getString("ASCENDIDO_POR");
				fila[5] = rs.getString("NOTAS_USUARIO");

				dftm.addRow(fila); // Añade una fila al final del modelo de la
									// tabla
			}

			table.updateUI();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void rellenarTabla() {

		try {
			ResultSet rs = listas();
			while (rs.next()) {
				Object[] fila = new Object[6];
				fila[0] = rs.getString("NOMBRE"); 
				fila[1] = rs.getString("UID");
				fila[2] = rs.getString("RANGO");
				fila[3] = rs.getString("FECHA_ASCENSO");
				fila[4] = rs.getString("ASCENDIDO_POR");
				fila[5] = rs.getString("NOTAS_USUARIO");
				dftm.addRow(fila); 
			}

			table.updateUI();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void actualizar() {
		dftm.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				if (e.getType() == TableModelEvent.UPDATE) {
					int columna = e.getColumn();
					int fila = e.getLastRow();
					String query = "";
					if (columna == 0) {
						query = "UPDATE ViveOMuere SET NOMBRE='" + table.getValueAt(fila, columna)
								+ "' WHERE UID='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 1) {
						query = "UPDATE ViveOMuere SET UID='" + table.getValueAt(fila, columna)
								+ "' WHERE UID='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 2) {
						query = "UPDATE ViveOMuere SET RANGO='" + table.getValueAt(fila, columna)
								+ "' WHERE UID='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 3) {
						query = "UPDATE ViveOMuere SET FECHA_ASCENSO='" + table.getValueAt(fila, columna)
								+ "' WHERE UID='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 4) {
						query = "UPDATE ViveOMuere SET ASCENDIDO_POR='" + table.getValueAt(fila, columna)
								+ "' WHERE UID='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 5) {
						query = "UPDATE ViveOMuere SET NOTAS_USUARIO='" + table.getValueAt(fila, columna)
								+ "' WHERE UID='" + table.getValueAt(fila, 1) + "'";
					}
					if (columna == 6) {
						query = "UPDATE ViveOMuere SET SERVIDOR='" + table.getValueAt(fila, columna)
								+ "' WHERE UID='" + table.getValueAt(fila, 1) + "'";
					}
		
					try {
						stmt = conexion.createStatement();
						int r = stmt.executeUpdate(query);
						System.out.println("actualizado");

					} catch (SQLException e1) {
						System.out.println("error actu");
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
	}

	public void eliminar() {
		int fila = table.getSelectedRow();
		String query = "DELETE FROM ViveOMuere WHERE UID = '" + table.getValueAt(fila, 1) + "'";
		try {
			stmt = conexion.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			System.out.println("eliminado ok");
		} catch (SQLException e) {
			System.out.println("eliminado problema");
		}
	}


	

	


	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
}
