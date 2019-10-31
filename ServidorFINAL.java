import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorFINAL {

	static int puerto = 25552;
	public static void main(String args[]) {
		
		try {
			
			ServerSocket Conexion = new ServerSocket(puerto);
			System.out.println("Servidor creado en el puerto "+puerto);
			
	
			ServerThread T1 = new ServerThread(Conexion, args[0], 1);
			//T1.start();
			ServerThread T2 = new ServerThread(Conexion, args[0], 2);
			ServerThread T3 = new ServerThread(Conexion, args[0], 3);

			//Socket socket = Conexion.accept(); //acepta la solicitud que entra al socket
			//PrintWriter Mostrar = new PrintWriter(socket.getOutputStream(), true);
			//String Mensaje = "Te has conectado con exito";
			//Mostrar.println(Mensaje);
			//LineNumberReader  LLegaDeCliente = new LineNumberReader(new InputStreamReader(socket.getInputStream()));			//con esto cogemos lo que nos llega del cliente
			
			//System.out.println(Mensaje + " enviado a: "+ socket.getRemoteSocketAddress());

			
			
			
		} catch (IOException e) {
			System.out.println("ERROR EN EL SERVER RELACIONADO CON EL SOCKET: "+e);
		}
		
	}
}




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
		
		System.out.println("Soy la hebra T" + MiNumero + " y estoy en el server "+NombreMiServer);
		
		
		while(true) {		//con el while, hacemos que la hebra cuando quede libre por haberse desconectado el cliente, quede libre para otro nuevo que llegue
		try {
			Conexion = MiServidor.accept();
			PrintWriter Mostrar = new PrintWriter(Conexion.getOutputStream(), true);
			//String Mensaje = "Te has conectado con exito.";
			//Mostrar.println(Mensaje);
			//System.out.println("Enviado a: "+Conexion.getRemoteSocketAddress());
			
			System.out.println("Connecction accepted from:  " + Conexion.getInetAddress()+ " at port "+ Conexion.getLocalPort());
			LineNumberReader LlegaDeCliente = new LineNumberReader(new InputStreamReader(Conexion.getInputStream()));
		
			/*String MiLinea = Lector.readLine();
					
			while(MiLinea!=null){
				if(MiLinea.equals(".")) {

					System.out.println("Cerrando conexion...");
					Conexion.close();
				}
				
				System.out.println("El cliente ha escrito: "+ MiLinea);
				MiLinea = Lector.readLine();
			}*/
			Mostrar.println("Escribe algo: ");
			String Milinea = LlegaDeCliente.readLine().toString();


			while(Milinea!= null ) {
				//System.out.println(Milinea);		para ver en consola de server lo q escribe el user
				
				
				if(Milinea.equals(".")) {
					System.out.println("Cerrando conexion...");

					Conexion.close();
					LlegaDeCliente.close();
					break;
				}
				
				Date fecha = new Date();
				String MensajeParaCliente = fecha +" "+ NombreMiServer + " (Hebra T"+ MiNumero +"): " + Milinea;
				
		        Mostrar.println(MensajeParaCliente);
		        Mostrar.println("Escribe algo: ");
		        
		        try {
					Milinea = LlegaDeCliente.readLine().toString();

				} catch (Exception e) {
		
				}
				
			}
			
			
		
			//Conexion.close();
			//LlegaDeCliente.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	}
	
}
}
}
		
		
