package logico;

public class CilindricoHueco extends Queso {
	
	public CilindricoHueco(float precio_base, float precio_unitario, float radio, String nombre, int cantidad) {
		super(precio_base, precio_unitario, radio, nombre, cantidad);
		// TODO Auto-generated constructor stub
	}



	

	private float longitud;
	private float radio_interno;
	private String tipo = "Cilíndro Hueco";
	
	public CilindricoHueco(float precio_base, float precio_unitario, float radio, String nombre, int cantidad,
			float longitud, float radio_interno) {
		super(precio_base, precio_unitario, radio, nombre, cantidad);
		this.longitud = longitud;
		this.radio_interno = radio_interno;
	}
	


	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public float getRadio_interno() {
		return radio_interno;
	}

	public void setRadio_interno(float radio_interno) {
		this.radio_interno = radio_interno;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float Volumen() {
		double volumen = 0;
		 volumen =  ( ((Math.PI) * (Math.pow(getRadio(), 2)) * (getLongitud()) - Math.pow(getRadio_interno(), 2)) );
		return (float) volumen;
	}
	
	public float Precio_Total() {
		double total = 0;
		total = (getCantidad() * ( getPrecio_base()+getPrecio_unitario() ) * (Volumen()/100) );
		return (float) total;
	}
	
	
	
	

	
}
