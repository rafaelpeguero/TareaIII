package logico;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;




public class Fabrica {
	
	public ArrayList <Cliente> clientes;
	public Inventario quesos;
	public ArrayList <Factura> facturas;
	static Fabrica obj_fabrica;
	
	static SalvarFacturaClass sfc;
	
	

//CONSTRUCTOR
public Fabrica() {
	// ARRAY LISTS P CLIENTES Y FACTURAS
	clientes = new ArrayList<Cliente>();
	facturas = new ArrayList<Factura>();
	
	quesos = new Inventario();  
}

public ArrayList<Cliente> getClientes() {
	return clientes;
}

public void setClientes(ArrayList<Cliente> clientes) {
	this.clientes = clientes;
}

public Inventario getQuesos() {
	return quesos;
}

public void setQuesos(Inventario quesos) {
	this.quesos = quesos;
}

public ArrayList<Factura> getFacturas() {
	return facturas;
}

public void setFacturas(ArrayList<Factura> facturas) {
	this.facturas = facturas;
}

//METODOS
public static Fabrica getInstancias(){
	if(obj_fabrica == null) {
		obj_fabrica = new Fabrica();
	}
	return obj_fabrica;
}

public void AddCliente(Cliente cliente) {
	//Annadiendo al ArrayList
	clientes.add(cliente);
}

public void AddFactura(Factura factura) {
	//Annadiendo al ArrayList 
	facturas.add(factura);
}

public boolean DelClienteByIndex(int index) {
	boolean eliminado = false;
	//Removiendo del ArrayList
	if(clientes.remove(index) != null) {
		eliminado = true;
	}
	return eliminado;
}

public Cliente BuscarClienteByNombre(String nombre) {
	for(Cliente aux : clientes) {
		if(aux.getNombre().equalsIgnoreCase(nombre))
			return aux;
	}
	return null;
}

public Factura BuscarFacturaById(String id) {
	for(Factura aux : facturas) {
		if(aux.getID().equalsIgnoreCase(id))
			return aux;
	}
	return null;
}

public Factura BuscarFacturaByCliente(Cliente client) {
	for(Factura aux : facturas) {
		if(aux.getFactura_cliente() == client) 
			return aux;
		}
	
return null;
}

public void DelClienteByNombre(String nombre) {
	clientes.remove(BuscarClienteByNombre(nombre));
}

public void ModificarCliente(int index, String nombre, String id, String direccion) {
	clientes.get(index).setNombre(nombre);
	clientes.get(index).setID(id);
	clientes.get(index).setDireccion(direccion);
}

public Factura getFactura(String id) {
	for(Factura aux : facturas) {
		if(aux.getID().equalsIgnoreCase(id))
			return aux;
	}
	return null;
}

public void DelFactura(String id) {
	facturas.remove(BuscarFacturaById(id));
}

public void DelFacturaByIndex(int index) {
	facturas.remove(index);
}

public void DelFacturaByObj(Factura fact) {
	facturas.remove(fact);
}

public void SalvarFactura(Factura factura) {
	System.out.println("Salvando....");
	try {
		
	int puerto = 9002;
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

/*public void SalvarFactura(Factura factura) {
	 Socket s = null;
	 int puerto_servidor = 9001;
	 OutputStream ops_s = null;
	 ObjectOutputStream obj_ops = null;
	 
	 Factura aux5;
	 aux5 = new Factura("fact", null,null);
	 
	try {
		s = new Socket (InetAddress.getLocalHost(),puerto_servidor);
	
	} catch (UnknownHostException e) {
		System.out.println("Error 1");
		e.printStackTrace();
	
	} catch (IOException e) {
		System.out.println("Error 2");
		e.printStackTrace();
	} //Estoy solicitando la direccion de puerto servidor
	
	
	try {
		ops_s = s.getOutputStream();
	} catch (IOException e) {
		System.out.println("Error 3");
		e.printStackTrace();
	}
	
	try {
		obj_ops = new ObjectOutputStream(ops_s);
	} catch (IOException e) {
		System.out.println("Error 4");
		e.printStackTrace();
	}
			
	System.out.println("Guardando la factura en el servidor.....");
	try {
		obj_ops.writeObject(aux5);
		s.close();
	} catch (IOException e) {
		System.out.println("Error 5");
		e.printStackTrace();
	} 
	
	
} */

}