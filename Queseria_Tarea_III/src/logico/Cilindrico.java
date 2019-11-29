package logico;

public class Cilindrico extends Queso {
	
	

	private float longitud;
	private String tipo = "Cilíndrico";
	
	public Cilindrico(float precio_base, float precio_unitario, float radio, String nombre, int cantidad,
			float longitud) {
		super(precio_base, precio_unitario, radio, nombre, cantidad);
		this.longitud = longitud;
	}

	
	
	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	public float Volumen() {
		double  volumen = 0;
		volumen = (((Math.PI)*(Math.pow(getRadio(), 2)) *(getLongitud())));
		return (float) volumen;
	}
	
	public float Precio_Total() {
		double total = 0;
		total = (getCantidad() * ( getPrecio_base()+getPrecio_unitario() ) * (Volumen()/100) );
		return (float) total;
		
	}

	
	
	
	

	

}