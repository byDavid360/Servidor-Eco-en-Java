import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClienteFINAL {

	
	public static void main(String[] args) {
		String host = "localhost", Linea;
		
		if(args.length>0) {
			host = args[0];
		}
	
		try {
			
			Socket socket = new Socket(host, 25552);
			
			HebraEscribir ClienteEscribe = new HebraEscribir(socket);		//hebra para que el cliente escriba

			HebraLeer ClienteLee = new HebraLeer(socket);					//hebra para que el cliente lea
			ClienteEscribe.join();											//esperamos a que se termine el escribir para leer
			System.exit(0);
			
			/*LineNumberReader  netIn = new LineNumberReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader LLegaDeServer = new BufferedReader(new InputStreamReader(socket.getInputStream())); //para recibir de server
			PrintWriter netOut = new PrintWriter(socket.getOutputStream(),true);
			LineNumberReader  sysIn = new LineNumberReader(new InputStreamReader(System.in));		//es como un scanner
			
			
			while(true) {
				
				System.out.print("Escribe algo: ");
				Linea = sysIn.readLine();
				if(Linea.equals(".")) {
					System.out.println("Has escrito el caracter prohibido. Cerrando sesion...");
					break;
				}
				netOut.println(Linea);
				System.out.println("El servidor te responde con: "+LLegaDeServer.readLine());
				
			}
			*/
			
		}catch(IOException | InterruptedException e) {
			System.out.println("ERROR: " + e);
		}
		
	}
}
