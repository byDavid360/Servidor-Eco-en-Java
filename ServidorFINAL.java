import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorFINAL {

	static int puerto = 25552;				//declaramos el puerto de la conexion
	
	public static void main(String args[]) {
		
		try {
			
			ServerSocket Conexion = new ServerSocket(puerto);		//creamos el socket del servidor en el puerto deseado
			System.out.println("Servidor creado en el puerto "+puerto);
			
			//Creamos tres hebras que se ocuparán de atender a 3 clientes de forma simultánea pasando como parámetros el socket, el nombre del host (estrá en args[0]) y el número con el que identificaremos a la hebra
			ServerThread T1 = new ServerThread(Conexion, args[0], 1);	
			ServerThread T2 = new ServerThread(Conexion, args[0], 2);
			ServerThread T3 = new ServerThread(Conexion, args[0], 3);
			
		} catch (IOException e) {
			System.out.println("ERROR EN EL SERVER RELACIONADO CON EL SOCKET: "+e);
		}	
	}
}


/*La clase hebra que atenderá los clientes*/
class ServerThread extends Thread {
	
	ServerSocket MiServidor;
	Socket Conexion;
	String NombreMiServer;
	int MiNumero;
	
	ServerThread(ServerSocket Server, String NombreServer, int NumeroHebra){
	
		MiServidor = Server;
		NombreMiServer = NombreServer;
		MiNumero = NumeroHebra;
		this.start();
	}
	
	public void run() {
		
		while(true) {											//Con este while buscamos que la hebra atienda a un cliente siempre que ésta esté vacía-
			try {
				Conexion = MiServidor.accept();							// Aceptamos la conexión con el servidor
				PrintWriter Mostrar = new PrintWriter(Conexion.getOutputStream(), true);	//
	
				System.out.println("Connecction accepted from:  " + Conexion.getInetAddress()+ " at port "+ Conexion.getLocalPort());	//mostramos por consola la IP y el puerto de la conexion actual
				LineNumberReader LlegaDeCliente = new LineNumberReader(new InputStreamReader(Conexion.getInputStream()));		//Datos que llegan del cliente
		
		
				Mostrar.println("Escribe algo: ");						//Esto se mostrará en la consola del cliente ya que se pasa a través del PrintWriter de salida
				String Milinea = LlegaDeCliente.readLine().toString();


				//siempre que se haya escrito algo desde el cliente
				while(Milinea!= null ) {			
				
					if(Milinea.equals(".")) {	
						//cerramos conexion si el cliente escribe un 
						System.out.println("Cerrando conexion...");
						Conexion.close();
						LlegaDeCliente.close();
						break;
					}
				
					Date fecha = new Date();					
					String MensajeParaCliente = fecha +" "+ NombreMiServer + " (Hebra T"+ MiNumero +"): " + Milinea;
				
			        	Mostrar.println(MensajeParaCliente);						//enviamos a través de Mostrar el mensaje que aparecerá en la consola del cliente
		        		Mostrar.println("Escribe algo: ");
		        
		        		try {
						Milinea = LlegaDeCliente.readLine().toString();
	
					} catch (Exception e) {
		
					}	
				}
	
			} catch (IOException e) {
				e.printStackTrace();
		
			}
	
		}
	}
}
		
		
