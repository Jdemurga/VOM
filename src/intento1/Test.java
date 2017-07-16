package intento1;

public class Test {
	public static void main(String[] args) {
		VistaLogin miVistaLogin =new VistaLogin();
		VistaRegistro miVista = new VistaRegistro();
		VistaBuscar miVistaBuscar=new VistaBuscar();
		Modelo miModelo= new Modelo();
		
		Controlador miControlador= new Controlador(miVista, miModelo,miVistaBuscar,miVistaLogin);
		miModelo.setControlador(miControlador);
		miVista.setControlador(miControlador);
		miVistaBuscar.setControlador(miControlador);
		miVistaLogin.setControlador(miControlador);
		miVistaLogin.setVisible(true);
	}

}
