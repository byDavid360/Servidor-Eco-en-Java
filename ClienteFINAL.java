import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


/*En esta clase crearemos el socket para conectarnos al servidor, del eco se ocuparán las hebras HebraEsribir y HebraLeer*/

public class ClienteFINAL {

	public static void main(String[] args) {
		
		String host = "localhost";			//declaramos el host como localhost en caso de que no se haya introducido ninguno en args[]
		
		if(args.length>0) {				//el nombre del host está almacenad en args, comprobamos si args contiene el nombre
			host = args[0];
		}
	
		try {
			
			Socket socket = new Socket(host, 25552);					//creamos el socket hacia el host y elegimos el puerto 25552
			
			HebraEscribir ClienteEscribe = new HebraEscribir(socket);			//llamamos a la hebra que permitirá escribir al cliente
			HebraLeer ClienteLee = new HebraLeer(socket);					//llamamos a la hebra que leerá 
			ClienteEscribe.join();								//esperamos a que se termine de escribir el contenido de cliente para poder leerer
			System.exit(0);
			
		}catch(IOException | InterruptedException e) {
			System.out.println("ERROR: " + e);
		}
	}
}
