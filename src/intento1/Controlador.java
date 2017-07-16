package intento1;

import java.sql.ResultSet;

public class Controlador {
	private VistaRegistro vista;
	private Modelo modelo;
	private VistaBuscar vistaBuscar;
	private VistaLogin vistaLogin;
	
	public Controlador(VistaRegistro vista,Modelo modelo, VistaBuscar vistaBuscar,VistaLogin vistaLogin) {
		this.modelo=modelo;
		this.vista=vista;
		this.vistaBuscar=vistaBuscar;
		this.vistaLogin=vistaLogin;
	}
	public void registroLogin(){
		vista.setVisible(false);
		vistaLogin.setVisible(true);
	}
	
	public void LoginRegistro(){
		if(modelo.verificacion(vistaLogin.obtenerUSr(), vistaLogin.obtenerClave())){
			vistaLogin.setVisible(false);
			vista.setVisible(true);
		}
	}
	
	public void registarBuscar(){
		vista.setVisible(false);
		vistaBuscar.setVisible(true);
	}
	public void buscarRegistrar(){
		vista.setVisible(true);
		vistaBuscar.setVisible(false);
	}
	
	public void registrar(){
		String NOMBRE=vista.Nombre();
		String UID=vista.UID();
		String RANGO=vista.Rango();
		String FECHA=vista.Fecha();
		String ASCENSO=vista.Ascenso();
		String NOTAS =vista.Notas();
		
		modelo.insert(UID, NOMBRE,  RANGO, FECHA, ASCENSO,NOTAS);
	}
	

}
