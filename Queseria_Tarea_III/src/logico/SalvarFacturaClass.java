package logico;


import java.io.*;
import java.net.*;

public class SalvarFacturaClass {

	@SuppressWarnings("unused")
	private static Factura factura;	
	public SalvarFacturaClass(Factura aux) {
		factura = aux;
	}
	
	public static void main(String[] args) {
		try {
			int puerto = 9003;
			Socket s = new Socket(InetAddress.getLocalHost(),puerto);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			
			//Factura f = new Factura("aba212", null, null);
			oos.writeObject(factura);
			//oos.writeObject(new String("Otro Objeto"));
			oos.close();
			os.close();
			s.close();
			
			}catch(Exception e) {
				System.out.println(e);
			}

	}
	
	/*public  SalvarFacturaClass (Factura factura) {
		try {
			int puerto = 9003;
			Socket s = new Socket(InetAddress.getLocalHost(),puerto);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			
			//Factura f = new Factura("aba212", null, null);
			oos.writeObject(factura);
			//oos.writeObject(new String("Otro Objeto"));
			oos.close();
			os.close();
			s.close();
			
			}catch(Exception e) {
				System.out.println(e);
			}

	}
	*/

}
