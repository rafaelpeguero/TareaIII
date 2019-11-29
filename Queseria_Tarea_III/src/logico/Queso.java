package logico;

public class Queso {

	protected float precio_base = 75; //PRECIO BASE GENERAL;
	protected float precio_unitario;
	protected float radio;
	protected  String nombre;
	protected  int cantidad;
	
	public Queso(float precio_base, float precio_unitario, float radio, String nombre, int cantidad) {
		super();
		this.precio_base = precio_base;
		this.precio_unitario = precio_unitario;
		this.radio = radio;
		this.nombre = nombre;
		this.cantidad = cantidad;
	}
	

	
	public float getPrecio_base() {
		return precio_base;
	}

	public void setPrecio_base(float precio_base) {
		this.precio_base = precio_base;
	}

	public float getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(float precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public float getRadio() {
		return radio;
	}

	public void setRadio(float radio) {
		this.radio = radio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	
	public float Volumen() {
		return (float) ( ((Math.PI) * (Math.pow(getRadio(), 2))  ) );
	}
	
	public float Precio_Total() {
		return (float) (getCantidad() * ( getPrecio_base()+getPrecio_unitario() ) * (Volumen()/100) );
	}
	

}
