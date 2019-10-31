import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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
			LlegaDeServer = new BufferedReader(new InputStreamReader(MiSocket.getInputStream()));
			
			
			while(true) {
				Linea = LlegaDeServer.readLine();
				
				if(Linea == null)
					System.exit(0);
	
				
				System.out.println(Linea);

			}
			
			
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

		
		
	}
	
	
	
}
