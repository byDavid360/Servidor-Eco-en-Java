import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/* Esta hebra se ocupará de leer las respuestas eco del servidor y escribirlas en la consola del cliente*/ 

public class HebraLeer extends Thread {
	
	Socket MiSocket;
	BufferedReader LlegaDeServer;
	String Linea;
	
public HebraLeer(Socket socket) {
	
	MiSocket = socket;
	this.start();	
	}

	public void run() {
		
		try {
			LlegaDeServer = new BufferedReader(new InputStreamReader(MiSocket.getInputStream()));	//El BufferedReader recogerá lo que llega del servidor hacia el cliente
			
			
			while(true) {
				Linea = LlegaDeServer.readLine();						//leemos el contenido del BufferedReader
				
				if(Linea == null)								//cuando no haya contenido, acabaremos la conexion
					System.exit(0);
	
				System.out.println(Linea);							//escribimos por consola el contenido del BufferedReader

			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
