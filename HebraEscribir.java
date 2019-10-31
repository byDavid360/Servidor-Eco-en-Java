import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.Socket;

/*Esta clase se ocupará de la escritura del contenido por parte del cliente hacia el servidor*/

public class HebraEscribir extends Thread {
	
	Socket MiSocket;
	String Linea;
	LineNumberReader  sysIn = new LineNumberReader(new InputStreamReader(System.in));		//con el LineNumberReader leeremos la entrada de datos del cliente
	PrintWriter netOut;
	BufferedReader LLegaDeServer;									//con el BufferedReader leeremos el contenido que llega del servidor a
													//modo de respuesta
	public HebraEscribir(Socket socket) {
		MiSocket = socket;
		this.start();
	}
	
	
	public void run() {
		
		try {
			sleep(2000);
			netOut =  new PrintWriter(MiSocket.getOutputStream(),true);			//con el PrintWriter escribiremos los datos del cliente
			HebraLeer Lee = new HebraLeer(MiSocket);
			
			while(true) {
				
				Linea = sysIn.readLine();						//leemos lo que escribe el cliente
				netOut.println(Linea);							//alamcenamos lo que escribe el cliente
				
				if(Linea.equals(".")) {							//si el cliente escribe un punto se cerrará la conexion
					
					System.out.println("Has escrito el caracter prohibido. Cerrando Conexion...");
					break;
				}
	
			}
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
