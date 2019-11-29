package logico;

import java.util.ArrayList;

public class Inventario {
	
	private ArrayList<Queso>inventario;
	
	public Inventario() {
		inventario = new ArrayList<Queso>(); 
	}

	public ArrayList<Queso> getInventario() {
		return inventario;
	}

	public void setInventario(ArrayList<Queso> inventario) {
		this.inventario = inventario;
	}
	

	//Metodos
	
	public Queso Buscar_queso(String word) {
		for( Queso aux : inventario) {
			if(aux.getNombre().equalsIgnoreCase(word))
				return aux;
		}
		return null;
	}
	
	public Queso Buscar_queso_indice(int indice) {
		return inventario.get(indice);
	}
	
	public float Precio() {
		
		for(Queso aux : inventario) {
			
			if( aux instanceof Esferico) 
				return ((Esferico) aux).Precio_Total();
			
			
			if( aux instanceof Cilindrico) 
				return ((Cilindrico) aux).Precio_Total();
			
			if( aux instanceof CilindricoHueco)
				return ((CilindricoHueco) aux).Precio_Total();
		
		}
		return 0.f;
		
		}
	
	public void Registrar_queso(Queso queso) {
		inventario.add(queso);
	}
	public void Remover_queso(String nombre) {
		inventario.remove(Buscar_queso(nombre));
	}
	

}
