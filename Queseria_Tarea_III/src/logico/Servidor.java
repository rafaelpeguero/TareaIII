package logico;

import java.io.*;
import java.net.*;


public class Servidor {

	
	/*public Servidor() {
		try {
			int puerto = 9002;
			ServerSocket ss = new ServerSocket(puerto);
			System.out.println("Servidor Mensaje : Esperando por el cliente....");
			Socket s = ss.accept();
			System.out.println("Servidor Mensaje : Conexion establecida....");

			InputStream ips = s.getInputStream(); //Obteniendo lectura del Socket
			ObjectInputStream obj_ips = new ObjectInputStream(ips); // Creando objeto de la lectura
			
			Factura factura = (Factura) obj_ips.readObject(); //Asignando el objeto al atributo de la clase
			if(factura != null)
				System.out.println(factura.getID());
				System.out.println((String) obj_ips.readObject());
			Salvar(factura);
			System.out.println("Servidor Mensaje : Factura Salvada....");
			
			ips.close();
			ss.close();
			s.close();
			System.out.println("Servidor Mensaje : Cerrando conexion....");
			
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	
	*/
	public static void Salvar(Factura aux) {
		
		try {
			ObjectOutputStream obj_ops;
			FileOutputStream fichero = new FileOutputStream("C:/txt/Factura.txt");		
			
			obj_ops = new ObjectOutputStream(fichero);
			obj_ops.writeObject(aux);
			obj_ops.close(); 
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Servidor() {
		
	}
	public static void main(String []args) throws IOException,ClassNotFoundException {
		
		try {
		int puerto = 9003;
		ServerSocket ss = new ServerSocket(puerto);
		System.out.println("Servidor Mensaje : Esperando por el cliente....");
		Socket s = ss.accept();
		System.out.println("Servidor Mensaje : Conexion establecida....");

		InputStream ips = s.getInputStream(); //Obteniendo lectura del Socket
		ObjectInputStream obj_ips = new ObjectInputStream(ips); // Creando objeto de la lectura
		
		Factura factura = (Factura) obj_ips.readObject(); //Asignando el objeto al atributo de la clase
		if(factura != null)
			System.out.println(factura.getID());
			System.out.println((String) obj_ips.readObject());
		Salvar(factura);
		System.out.println("Servidor Mensaje : Factura Salvada....");
		
		ips.close();
		ss.close();
		s.close();
		System.out.println("Servidor Mensaje : Cerrando conexion....");
		
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
}
