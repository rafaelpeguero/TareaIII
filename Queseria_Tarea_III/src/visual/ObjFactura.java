package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cilindrico;
import logico.CilindricoHueco;
import logico.Cliente;
import logico.Esferico;
import logico.Fabrica;
import logico.Factura;
import logico.Inventario;
import logico.Queso;
import logico.SalvarFacturaClass;
import logico.Servidor;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.Socket;

@SuppressWarnings("unused")
public class ObjFactura extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JTable tabInventario;
	private JTable tabFactura;
	
	private JTextField txtTotal;
	private JTextField txtIdFact;
	
	private Fabrica fabrica;
	private Fabrica fab_inst = Fabrica.getInstancias();
	private Inventario inventario = new Inventario();   //INVETARIO DE FACTURA
	private Cliente cliente_aux;
	
	private JButton btnAgregar;
	private JButton btnRemover;
	private JButton btnSalvar;
	
	private JComboBox<String> cbxClientes;
	
	private DefaultTableModel tabModelInventario;
	private DefaultTableModel tabModelFactura;
	Object[] fila_inv;
	Object[] fila_fact;
	
	SalvarFacturaClass sfcc;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ObjFactura dialog = new ObjFactura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ObjFactura() {
		setResizable(false);
		
		setTitle("Punto de Venta");
		setBounds(100, 100, 1000, 525);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panelCliente = new JPanel();
		panelCliente.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCliente.setBounds(10, 10, 472, 61);
		contentPanel.add(panelCliente);
		panelCliente.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre : ");
		lblNombre.setBounds(67, 25, 70, 14);
		panelCliente.add(lblNombre);
		
		 cbxClientes = new JComboBox<String>();
		cbxClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxClientes.getSelectedIndex()!=0) {
					tabInventario.setEnabled(true);
					tabFactura.setEnabled(true);
					btnAgregar.setEnabled(true);
					btnRemover.setEnabled(true);
					cbxClientes.setEnabled(false);
					txtIdFact.setEnabled(true);
					
					
				}else {
					tabInventario.setEnabled(false);
					tabFactura.setEnabled(false);
					btnAgregar.setEnabled(false);
					btnRemover.setEnabled(false);
					txtIdFact.setEnabled(false);
				}
			}
		});
		//Extra 
		cbxClientes.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>"}));
		cbxClientes.setBounds(204, 21, 200, 22);
		panelCliente.add(cbxClientes);
		
		
		
		JPanel panelFactura = new JPanel();
		panelFactura.setBorder(new TitledBorder(null, "Factura", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFactura.setBounds(489, 11, 472, 61);
		contentPanel.add(panelFactura);
		panelFactura.setLayout(null);
		
		JLabel lblId = new JLabel("ID : ");
		lblId.setBounds(106, 24, 117, 14);
		panelFactura.add(lblId);
		
		txtIdFact = new JTextField();
		txtIdFact.setEnabled(false);
		txtIdFact.setBounds(233, 21, 133, 20);
		panelFactura.add(txtIdFact);
		txtIdFact.setColumns(10);
		
		JPanel panelProducto = new JPanel();
		panelProducto.setBounds(10, 82, 974, 335);
		contentPanel.add(panelProducto);
		panelProducto.setLayout(null);
		
		JScrollPane scrollPane_Inventario = new JScrollPane();
		scrollPane_Inventario.setBounds(10, 11, 418, 288);
		panelProducto.add(scrollPane_Inventario);
		
		tabInventario = new JTable();
		String [] colInventario = {"Cantidad", "Nombre", "Precio Unitario", "Volumen", "Total"};
		tabModelInventario = new DefaultTableModel() {
		
		/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column)
	    {
	      return false;
	    }
		};
		//extra tabla
		tabModelInventario.setColumnIdentifiers(colInventario);
		tabInventario.getTableHeader().setResizingAllowed(false);
		tabInventario.getTableHeader().setReorderingAllowed(false);
		tabInventario.setModel(tabModelInventario);

		
		
			
		scrollPane_Inventario.setViewportView(tabInventario);
		
		JScrollPane scrollPane_Factura = new JScrollPane();
		scrollPane_Factura.setBounds(535, 11, 429, 288);
		panelProducto.add(scrollPane_Factura);
		
		
		tabFactura = new JTable();
		//extra
				String []colFacutaNombres = {"Cantidad","Nombre", "Tipo","Volumne","Precio Unitario"};
				tabModelFactura = new DefaultTableModel() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				tabModelFactura.setColumnIdentifiers(colFacutaNombres);
				tabFactura.getTableHeader().setResizingAllowed(false);
				tabFactura.getTableHeader().setReorderingAllowed(false);
				tabFactura.setModel(tabModelFactura);
		
		scrollPane_Factura.setViewportView(tabFactura);
		
		 btnRemover = new JButton("<<");
		 btnRemover.setEnabled(false);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Queso queso = inventario.Buscar_queso_indice(tabFactura.getSelectedRow());
						
				fabrica.quesos.Registrar_queso(queso);
				CargarTabInventario();
				inventario.Remover_queso(queso.getNombre());
				CargarTabFactura();
				
			}
		});
		btnRemover.setBounds(435, 85, 89, 40);
		panelProducto.add(btnRemover);
		
		 btnAgregar = new JButton(">>");
		 btnAgregar.setEnabled(false);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Queso queso = fab_inst.quesos.Buscar_queso_indice(tabInventario.getSelectedRow());
				inventario.Registrar_queso(queso);
				CargarTabFactura();
				fab_inst.quesos.Remover_queso(queso.getNombre());
				CargarTabInventario();
			}
		});
		btnAgregar.setBounds(435, 210, 89, 40);
		panelProducto.add(btnAgregar);
		
		JLabel lblTotalRd = new JLabel("Total :  RD$");
		lblTotalRd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalRd.setBounds(785, 313, 79, 14);
		panelProducto.add(lblTotalRd);
		
		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		txtTotal.setEditable(false);
		txtTotal.setBounds(868, 310, 96, 20);
		panelProducto.add(txtTotal);
		txtTotal.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(-10, 428, 984, 58);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				btnSalvar = new JButton("Salvar");
				btnSalvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cbxClientes.getSelectedIndex() == 0 || txtIdFact.getText().equalsIgnoreCase("")){
							JOptionPane.showMessageDialog(null, "Compruebe que la Cactura Contiene Nombre y Cliente", "Mensaje", JOptionPane.INFORMATION_MESSAGE, null);
							}else{
											
								int valor = JOptionPane.showConfirmDialog(btnSalvar, "¿Desea Realizar la Factura?", "Confirmar", JOptionPane.YES_NO_OPTION);
								if(valor == JOptionPane.YES_OPTION){
									
								JOptionPane.showMessageDialog(null, "Se ha Generado la Factura Satisfactoriamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE, null);
								
								CargarTabFactura();
								CargarCliente();
								dispose();
						       }
							}
					}
				});
				btnSalvar.setBounds(791, 5, 83, 42);
				btnSalvar.setActionCommand("OK");
				buttonPane.add(btnSalvar);
				getRootPane().setDefaultButton(btnSalvar);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.setBounds(891, 5, 83, 42);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	
		addWindowListener(new WindowAdapter() {
			@Override
			//METODO: Cargar los Clientes al cbx al abrir la pantalla
			public void windowOpened(WindowEvent e) {
				for(Cliente cliente : Fabrica.getInstancias().getClientes()) {
					cbxClientes.addItem(cliente.getNombre());
				}
			}
		});
		CargarTabInventario();
	
	}

	public void CargarTabInventario() {
		tabModelInventario.setRowCount(0);
		fila_inv = new Object[tabModelInventario.getColumnCount()];
		
		//for(Queso queso : fabrica.quesos.getInventario()) {
		for(Queso queso : fab_inst.quesos.getInventario() ){	
			
			if(queso instanceof Esferico) {
				//MODELO :  cantidad, Nombre,tipo, volumen, precio unitario 
				fila_inv[0] = queso.getCantidad();
				fila_inv[1] = ((Esferico) queso).getNombre();
				fila_inv[2] = ((Esferico) queso).getTipo();
				fila_inv[3] = ((Esferico) queso).Volumen();
				fila_inv[4] = queso.getPrecio_unitario();
							
			}
			if(queso instanceof Cilindrico) {
				//MODELO :  cantidad, Nombre,tipo, volumen, precio unitario 
				fila_inv[0] = queso.getCantidad();
				fila_inv[1] = ((Cilindrico) queso).getNombre();
				fila_inv[2] = ((Cilindrico) queso).getTipo();
				fila_inv[3] = ((Cilindrico) queso).Volumen();
				fila_inv[4] = queso.getPrecio_unitario();
				
			}
			if(queso instanceof CilindricoHueco) {
				//MODELO :  cantidad, Nombre,tipo, volumen, precio unitario 
				fila_inv[0] = queso.getCantidad();
				fila_inv[1] = ((CilindricoHueco) queso).getNombre();
				fila_inv[2] = ((CilindricoHueco) queso).getTipo();
				fila_inv[3] = ((CilindricoHueco) queso).Volumen();
				fila_inv[4] = queso.getPrecio_unitario();
				
			}
			tabModelInventario.addRow(fila_inv); //AGREGANDO FILA NUEVA
		
		}
		tabInventario.setModel(tabModelInventario);//CREADA EL MODELO DE LA TAB  INVENTARIO
		
	}
	public void CargarTabFactura() {
		tabModelFactura.setRowCount(0);
		float totalFactura = 0;
		fila_fact = new Object[tabModelFactura.getColumnCount()];
		
		for(Queso queso : inventario.getInventario() ) {
			//MODELO : //"Cantidad" ,"Nombre", "Tipo", "Precio Por Unidad", "Total Neto"
			fila_fact[0] = queso.getCantidad();
			fila_fact[1] = queso.getNombre();
			fila_fact[3] = queso.getPrecio_unitario();
			
			if(queso instanceof Esferico) {
				fila_fact[2] = ((Esferico) queso).getTipo();
				fila_fact[4] = ((Esferico) queso).Precio_Total();
				totalFactura += (float)fila_fact[4];
			}
			if(queso instanceof Cilindrico) {
				fila_fact[2] = ((Cilindrico) queso).getTipo();
				fila_fact[4] = ((Cilindrico) queso).Precio_Total();
				totalFactura += (float)fila_fact[4];
			}
			if(queso instanceof CilindricoHueco) {
				fila_fact[2] = ((CilindricoHueco) queso).getTipo();
				fila_fact[4] = ((CilindricoHueco) queso).Precio_Total();
				totalFactura += (float)fila_fact[4];
			}
			tabModelFactura.addRow(fila_fact);
			tabInventario.setModel(tabModelFactura);//
			
			txtTotal.setText(String.valueOf(Redondear(totalFactura,2)));
		}
	
	}
	public static float Redondear(float n, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(n));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}


	public void CargarCliente() {
		for(Cliente cliente : fab_inst.getClientes() ) {
			if(cliente.getNombre().equalsIgnoreCase(cbxClientes.getSelectedItem().toString())) {
				cliente_aux  = fab_inst.BuscarClienteByNombre(cbxClientes.getSelectedItem().toString());
			}
		}
		Factura factura = new Factura(txtIdFact.getText(),cliente_aux,inventario);
		fab_inst.AddFactura(factura);
		cliente_aux.Annadir_Factura(factura);
		Servidor servidor = new Servidor();
		SalvarFacturaClass salvarfacturaclass = new SalvarFacturaClass(factura);
		//fab_inst.SalvarFactura(factura);
		

		//SalvarFactura(factura);
		
		//SalvarFacturaClass t = new SalvarFacturaClass(factura);
		//fabrica.SalvarFactura(factura);
		//sfcc = new SalvarFacturaClass(factura);
		
		//Servidor servidor = new Servidor();
		//SalvarFacturaClass salvarfacturaclass = new SalvarFacturaClass(factura);
		
	}
	public void SalvarFactura(Factura factura) {
		System.out.println("Salvando....");
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
	
}
