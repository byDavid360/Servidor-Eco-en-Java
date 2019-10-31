import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HebraEscribir extends Thread {
	
	Socket MiSocket;
	String Linea;
	LineNumberReader  sysIn = new LineNumberReader(new InputStreamReader(System.in));		//es como un scanner
	PrintWriter netOut;
	BufferedReader LLegaDeServer;

	
	public HebraEscribir(Socket socket) {
		MiSocket = socket;
		this.start();
	}
	
	
	public void run() {
		
		
		
		try {
			sleep(2000);
			netOut =  new PrintWriter(MiSocket.getOutputStream(),true);
			HebraLeer Lee = new HebraLeer(MiSocket);
			//LLegaDeServer = new BufferedReader(new InputStreamReader(MiSocket.getInputStream())); //para recibir de server
			//System.out.println(LLegaDeServer.readLine());

			while(true) {
				
				//System.out.print("Escribe algo: ");
				Linea = sysIn.readLine();
				netOut.println(Linea);
				
				if(Linea.equals(".")) {
					System.out.println("Has escrito el caracter prohibido. Cerrando Conexion...");
					break;
				}
				//System.out.println("El servidor te responde con: "+LLegaDeServer.readLine());
				
			}
		} catch (IOException | InterruptedException e) {
		
			e.printStackTrace();
		}
	
		
		
		
	}

}
