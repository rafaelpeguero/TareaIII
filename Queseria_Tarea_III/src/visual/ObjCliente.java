package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cliente;
import logico.Fabrica;
import logico.Factura;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ObjCliente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6597844453066971494L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTable tabClientes;
	
	//extra
	private DefaultTableModel tabModelo;
	//private Fabrica fabrica;
	Object[] fila;
	private JButton btnEditar;
	private JButton btnEliminar; 
	private JButton btnRegistrar; 
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ObjCliente dialog = new ObjCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			//extra
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ObjCliente() {
		//extra
		setResizable(false);
		
		
		setTitle("Manejador De Clientes");
		setBounds(100, 100, 852, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 527, 167);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblNombre = new JLabel("Nombre :");
			lblNombre.setBounds(10, 22, 70, 14);
			panel.add(lblNombre);
			
			JLabel lblDireccion = new JLabel("Direcci\u00F3n :");
			lblDireccion.setBounds(10, 130, 70, 14);
			panel.add(lblDireccion);
			
			JLabel lblCedula = new JLabel("C\u00E9dula :");
			lblCedula.setBounds(10, 58, 70, 14);
			panel.add(lblCedula);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(78, 17, 185, 20);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			
			txtCedula = new JTextField();
			txtCedula.setBounds(78, 54, 185, 20);
			panel.add(txtCedula);
			txtCedula.setColumns(10);
			
			txtDireccion = new JTextField();
			txtDireccion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnRegistrar.requestFocus();
					btnRegistrar.doClick();
				}
			});
			txtDireccion.setBounds(78, 128, 336, 20);
			panel.add(txtDireccion);
			txtDireccion.setColumns(10);
			
			JLabel lblTelefono = new JLabel("Telefono :");
			lblTelefono.setBounds(10, 94, 70, 14);
			panel.add(lblTelefono);
			
			txtTelefono = new JTextField();
			txtTelefono.setBounds(78, 91, 185, 20);
			panel.add(txtTelefono);
			txtTelefono.setColumns(10);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listado de Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 189, 826, 238);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 806, 183);
		panel.add(scrollPane);
		
		tabClientes = new JTable();
		tabClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnEditar.setEnabled(true);
				btnEliminar.setEnabled(true);
				btnRegistrar.setEnabled(false);
			}
		});
		
		//Extra
		
		
		
		scrollPane.setViewportView(tabClientes);
		///extras
		String[] colnStr = {"Nombre" , "Cédula", "Telefono", "Dirección"};
		tabModelo = new DefaultTableModel(); // Creando tabla 0x0
		tabModelo.setColumnIdentifiers(colnStr);
		tabClientes.getTableHeader().setResizingAllowed(false);
		tabClientes.getTableHeader().setReorderingAllowed(false);
		tabClientes.setModel(tabModelo);
		
		//Extra
		tabClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		CargarTab();
		
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			 btnRegistrar = new JButton("A\u00F1adir");
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//Verificando Los campos
					if(txtNombre.getText().equalsIgnoreCase("")||txtCedula.getText().equalsIgnoreCase("")||txtDireccion.getText().equalsIgnoreCase("")||txtTelefono.getText().equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(null, "Verifique que lo campos esten corectamente");
					}
					//Creando nuevo cliente
					else {
						Cliente cliente = new Cliente(txtNombre.getText(),txtCedula.getText(),txtDireccion.getText(),Integer.parseUnsignedInt(txtTelefono.getText()));
						Fabrica.getInstancias().AddCliente(cliente); // 
						
						LimpiarTab();
						CargarTab();
						
					}
				}
			});
			buttonPane.add(btnRegistrar);
			
			 btnEliminar = new JButton("Eliminar");
			//btnEliminar.setEnabled(false);
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//int index = tabClientes.getSelectedRow();
					int n = JOptionPane.showConfirmDialog(null, "Desea Eliminar este Cliente?", null,  JOptionPane.OK_CANCEL_OPTION);
					if( n == JOptionPane.YES_OPTION) {
						DelFactura(Fabrica.getInstancias().getClientes().get(tabClientes.getSelectedRow()));
						Fabrica.getInstancias().getClientes().remove(tabClientes.getSelectedRow());
						//
						btnEliminar.setEnabled(false);
						btnEditar.setEnabled(false);
						LimpiarTab();
						CargarTab();
						
					}
				}
			});
			buttonPane.add(btnEliminar);
			btnEliminar.setEnabled(false);
			
			 btnEditar = new JButton("Editar");
			btnEditar.setEnabled(false);
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = tabClientes.getSelectedRow();
					switch(btnEditar.getText()) {
					
					case "Editar":
						btnEliminar.setEnabled(false);
						btnRegistrar.setEnabled(false);
						txtNombre.setText(tabClientes.getValueAt(index, 0).toString());
						txtCedula.setText(tabClientes.getValueAt(index, 1).toString());
						txtTelefono.setText(tabClientes.getValueAt(index, 2).toString());
						txtDireccion.setText(tabClientes.getValueAt(index,3).toString());
						btnEditar.setText("Guardar");
						break;
						
					case "Guardar":
						Cliente client = Fabrica.getInstancias().getClientes().get(index);
						client.setNombre(txtNombre.getText());
						client.setID(txtCedula.getText());
						client.setTelefono(Integer.parseInt(txtTelefono.getText()));
						client.setDireccion(txtDireccion.getText());
						LimpiarTab();
						JOptionPane.showMessageDialog(null, "Cliente Modificado", "Aviso", JOptionPane.INFORMATION_MESSAGE, null);
						CargarTab();
						btnEditar.setText("Editar");
						btnEditar.setEnabled(false);
						btnRegistrar.setEnabled(true);
						break;
					}
					
				}
			});
			buttonPane.add(btnEditar);
			{
				 cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public void LimpiarTab() {
		txtCedula.setText("");
		txtCedula.requestFocus();
		txtTelefono.setText("");
		txtTelefono.requestFocus();
		txtDireccion.setText("");
		txtDireccion.requestFocus();
		txtNombre.setText("");
		txtNombre.requestFocus();
	}
	public void CargarTab() {
		tabModelo.setRowCount(0); //Inicializando la tabla en 0
		fila = new Object[tabModelo.getColumnCount()]; //Creando Arreglo de Objetos por la cantidad de Columnas
		
		for(Cliente cliente : Fabrica.getInstancias().getClientes()) {
			fila[0] = cliente.getNombre();
			fila[1] = cliente.getID();
			fila[2] = cliente.getTelefono();
			fila[3] = cliente.getDireccion();
			
			tabModelo.addRow(fila);
		}
		tabClientes.setModel(tabModelo);	
	}
	public void DelFactura(Cliente cliente) {
		Fabrica fabrica = Fabrica.getInstancias();
		
		for(Factura aux : fabrica.getFacturas()) {
			if(aux == fabrica.BuscarFacturaByCliente(cliente)) 
				fabrica.getFacturas().remove(fabrica.BuscarFacturaByCliente(cliente));
				
		}
	}
}
