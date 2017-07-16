package intento1;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class VistaRegistro extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	private Controlador controlador;
	private FondoEscritorio contenedor = new FondoEscritorio();
	private JTextField textField_5;
	private JTextField textField_2;
	private JComboBox comboBox;

	public VistaRegistro() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VistaLogin.class.getResource("/img/vive.png")));
		setContentPane(contenedor);

		contenedor.setImagen("/img/doc.png");
		
		contenedor.setBackground(Color.BLUE);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("VOM Registro");
		setSize(614, 429);
		setLocationRelativeTo(null);

		JLabel lblNombreCompleto = new JLabel("Nombre completo");
		lblNombreCompleto.setForeground(Color.WHITE);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblCorreoCuenta = new JLabel("UID");
		lblCorreoCuenta.setForeground(Color.WHITE);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblCorreoPrivado = new JLabel("Rango");
		lblCorreoPrivado.setForeground(Color.WHITE);
		
		JLabel lblLicencia = new JLabel("Fecha");
		lblLicencia.setForeground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("Notas");
		lblNewLabel.setForeground(Color.WHITE);
		
		JLabel lblContraseaPrivada = new JLabel("Ascendido por");
		lblContraseaPrivada.setForeground(Color.WHITE);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JButton btnEnviar = new JButton("ENVIAR");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEnviar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controlador.registrar();
			}
		});
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controlador.registarBuscar();
			}
		});
		
		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			controlador.registroLogin();	
			}
		});
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Fundador ", "WebMaster ", "Administrador TS3", "Donador Oro", "Donador Platino", "Donador Bronze", "Usuario Vive o Muere", "No registrado"}));
		
		JScrollPane scrollPane = new JScrollPane();
	
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(124)
					.addComponent(lblCorreoPrivado)
					.addGap(140)
					.addComponent(lblContraseaPrivada))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(234)
					.addComponent(lblNewLabel))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(139)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(55)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(textField, 146, 146, 146)
									.addComponent(lblNombreCompleto))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblCorreoCuenta)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
								.addGap(7)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblLicencia)))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnLogOut)
								.addGap(79)
								.addComponent(btnEnviar)
								.addPreferredGap(ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
								.addComponent(btnBuscar))))
					.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
						.addGap(69)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
						.addGap(47)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombreCompleto)
						.addComponent(lblCorreoCuenta)
						.addComponent(lblLicencia))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCorreoPrivado)
						.addComponent(lblContraseaPrivada))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogOut)
						.addComponent(btnEnviar)
						.addComponent(btnBuscar))
					.addGap(40))
		);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		scrollPane.setViewportView(textField_2);
		getContentPane().setLayout(groupLayout);
	}
	public String Nombre(){
		return textField.getText();
	}
	public String UID(){
		return textField_1.getText();
	}
	public String Fecha(){
		return textField_5.getText();
	}
	public String Rango(){
		return comboBox.getSelectedItem().toString();
	}
	public String Ascenso(){
		return textField_4.getText();
	}
	public String Notas(){
		return textField_2.getText();
	}
	

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
}
