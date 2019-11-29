package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
import logico.Queso;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("unused")
public class ObjQueso extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tabQueso;
	private JTextField txtNombre;
	//Extra
	private DefaultTableModel tabModelo;
	private Fabrica fabrica;
	Object[] fila;
	private JSpinner spnRadio;
	private JSpinner spnRadioMenor;
	private JSpinner spnLongitud;
	private JSpinner spnPrecioUnt;
	private JComboBox<String> cbxTipo;
	
	private JButton btnRegistrar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton cancelButton;
	private JSpinner spnCantidad ;
	private JTable table;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ObjQueso dialog = new ObjQueso();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("deprecation")
	public ObjQueso() {
		setTitle("Registro de Queso");
		//extra
		setResizable(false);
		setBounds(100, 100, 800, 523);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Caracteristicas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 10, 539, 124);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblTipo = new JLabel("Tipo : ");
				lblTipo.setBounds(10, 28, 57, 14);
				panel.add(lblTipo);
			}
			{
				JLabel lblNombre = new JLabel("Nombre :");
				lblNombre.setBounds(10, 73, 57, 14);
				panel.add(lblNombre);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setEnabled(false);
				txtNombre.setBounds(67, 70, 208, 20);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
			}
			
			 cbxTipo = new JComboBox<>();
			 cbxTipo.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		int opt = cbxTipo.getSelectedIndex();
			 		switch(opt) {
			 		case 0:
			 			txtNombre.setEnabled(false);
			 			spnRadio.setEnabled(false);
			 			spnRadioMenor.setEnabled(false);
			 			spnLongitud.setEnabled(false);
			 			spnPrecioUnt.setEnabled(false);
			 			spnCantidad.setEnabled(false);
			 			break;
			 		case 1:	
			 			txtNombre.setEnabled(true);
			 			spnRadio.setEnabled(true);
			 			spnRadioMenor.setEnabled(false);
			 			spnLongitud.setEnabled(false);
			 			spnPrecioUnt.setEnabled(true);
			 			spnCantidad.setEnabled(true);
			 			break;
			 		case 2:
			 			txtNombre.setEnabled(true);
			 			spnRadio.setEnabled(true);
			 			spnRadioMenor.setEnabled(false);
			 			spnLongitud.setEnabled(true);
			 			spnPrecioUnt.setEnabled(true);
			 			spnCantidad.setEnabled(true);
			 			break;
			 		case 3:
			 			txtNombre.setEnabled(true);
			 			spnRadio.setEnabled(true);
			 			spnRadioMenor.setEnabled(true);
			 			spnLongitud.setEnabled(true);
			 			spnPrecioUnt.setEnabled(true);
			 			spnCantidad.setEnabled(true);
			 			break;
			 			
			 		}
			 		
			 	}
			 });
			cbxTipo.setModel(new DefaultComboBoxModel<String>(new String[] {"< Seleccione >", "Esf\u00E9rico", "Cil\u00EDndrico", "Cil\u00EDndrico Hueco"}));
			cbxTipo.setBounds(67, 24, 159, 22);
			panel.add(cbxTipo);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Contabilidad", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(281, 145, 268, 120);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblPrecioUnitariord = new JLabel("Precio Unitario (RD) : $");
			lblPrecioUnitariord.setBounds(10, 30, 143, 14);
			panel.add(lblPrecioUnitariord);
			
			JLabel lblCantidad = new JLabel("Cantidad :");
			lblCantidad.setBounds(10, 74, 143, 14);
			panel.add(lblCantidad);
			
			 spnCantidad = new JSpinner();
			 spnCantidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			 spnCantidad.setEnabled(false);
			spnCantidad.setBounds(159, 72, 63, 20);
			panel.add(spnCantidad);
			
			 spnPrecioUnt = new JSpinner();
			 spnPrecioUnt.setEnabled(false);
			spnPrecioUnt.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnPrecioUnt.setBounds(159, 26, 63, 20);
			panel.add(spnPrecioUnt);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Dimensiones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 145, 261, 120);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblRadiocm = new JLabel("Radio (cm) :");
			lblRadiocm.setBounds(10, 19, 119, 14);
			panel.add(lblRadiocm);
			
			JLabel lblRadioMenorcm = new JLabel("Radio Menor (cm) :");
			lblRadioMenorcm.setBounds(10, 52, 119, 14);
			panel.add(lblRadioMenorcm);
			
			JLabel lblLongitudcm = new JLabel("Longitud (cm) :");
			lblLongitudcm.setBounds(10, 85, 119, 14);
			panel.add(lblLongitudcm);
			
			 spnRadio = new JSpinner();
			 spnRadio.setEnabled(false);
			spnRadio.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnRadio.setBounds(176, 15, 40, 18);
			panel.add(spnRadio);
			
			 spnRadioMenor = new JSpinner();
			 spnRadioMenor.setEnabled(false);
			spnRadioMenor.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnRadioMenor.setBounds(176, 50, 40, 18);
			panel.add(spnRadioMenor);
			
			 spnLongitud = new JSpinner();
			 spnLongitud.setEnabled(false);
			spnLongitud.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnLongitud.setBounds(176, 85, 40, 18);
			panel.add(spnLongitud);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 275, 774, 169);
			contentPanel.add(scrollPane);
			
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btnRegistrar.setEnabled(false);
					btnEliminar.setEnabled(true);
					btnEditar.setEnabled(true);
				}
			});
			scrollPane.setViewportView(table);
			{
				//CREANDO TABLA
				String[] stringCol = {"Cant","Nombre","Tipo de Queso","Radio","Longitud","Radio Menor","Precio Unitario"};
				tabModelo = new DefaultTableModel();
				tabModelo.setColumnIdentifiers(stringCol);
				table.getTableHeader().setResizingAllowed(false);
				table.getTableHeader().setReorderingAllowed(false);
				table.setModel(tabModelo);
				table.getColumnModel().getColumn(0).setPreferredWidth(30);//Ajustando Tamanno
				table.getColumnModel().getColumn(1).setPreferredWidth(104);
				table.getColumnModel().getColumn(2).setPreferredWidth(85);
				table.getColumnModel().getColumn(3).setPreferredWidth(50);
				table.getColumnModel().getColumn(4).setPreferredWidth(50);
				table.getColumnModel().getColumn(5).setPreferredWidth(65);
				table.getColumnModel().getColumn(6).setPreferredWidth(70);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				 btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opt = cbxTipo.getSelectedIndex();
						txtNombre.setEnabled(true);
						switch(opt) {
						case 1: 
							if(txtNombre.getText().equalsIgnoreCase("")) {
								JOptionPane.showMessageDialog(null, "Verfique los campos", null, JOptionPane.ERROR_MESSAGE);
							}else {
								//Creando nuevo objeto/queso de herencia esferico
								Queso queso = new Esferico(0,Float.valueOf(spnPrecioUnt.getValue().toString()),Float.valueOf(spnRadio.getValue().toString()),txtNombre.getText(),Integer.valueOf(spnCantidad.getValue().toString()));
								Fabrica.getInstancias().quesos.Registrar_queso(queso);
								CargarTabQ();
								LimpiarTab();
							}
							break;
						case 2: 
							if(txtNombre.getText().equalsIgnoreCase("")) {
								JOptionPane.showMessageDialog(null, "Verfique los campos", null, JOptionPane.ERROR_MESSAGE);
							}else {
								Queso quesoC = new Cilindrico(0,Float.valueOf(spnPrecioUnt.getValue().toString()),Float.valueOf(spnRadio.getValue().toString()),txtNombre.getText(),Integer.valueOf(spnCantidad.getValue().toString()),Float.valueOf(spnLongitud.getValue().toString()) );
								Fabrica.getInstancias().quesos.Registrar_queso(quesoC);
								CargarTabQ();
								LimpiarTab();
							}
							break;
						case 3:
							if(txtNombre.getText().equalsIgnoreCase("")) {
								JOptionPane.showMessageDialog(null, "Verfique los campos", null, JOptionPane.ERROR_MESSAGE);
							}else {
								Queso quesoCH = new CilindricoHueco(0,Float.valueOf(spnPrecioUnt.getValue().toString()),Float.valueOf(spnRadio.getValue().toString()),txtNombre.getText(),Integer.valueOf(spnCantidad.getValue().toString()),Float.valueOf(spnLongitud.getValue().toString()),Float.valueOf(spnRadioMenor.getValue().toString()) );
								Fabrica.getInstancias().quesos.Registrar_queso(quesoCH);
								CargarTabQ();
								LimpiarTab();
							}
							break;
						}
						cbxTipo.setSelectedIndex(0);
					}		
						
				});
				buttonPane.add(btnRegistrar);
			}
			{
				 btnEliminar = new JButton("Eliminar");
				btnEliminar.setEnabled(false);
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int n = JOptionPane.showConfirmDialog(null, "Desea eliminar este Queso ?", null, JOptionPane.OK_CANCEL_OPTION);
						if(n == JOptionPane.YES_OPTION) {
							int index = table.getSelectedRow();
							Fabrica.getInstancias().quesos.getInventario().remove(index);
							btnEliminar.setEnabled(false);
							btnEditar.setEnabled(false);
							LimpiarTab();
							CargarTabQ();
						}else {
							btnRegistrar.setEnabled(true);
							btnEliminar.setEnabled(false);
							btnEditar.setEnabled(false);
							cbxTipo.setEnabled(true);
							
						}
						
					}
				});
				buttonPane.add(btnEliminar);
				btnEliminar.setEnabled(false);
			}
			{
				 btnEditar = new JButton("Editar");
				btnEditar.setEnabled(false);
				btnEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnRegistrar.setEnabled(false);
						btnEliminar.setEnabled(false);
						cbxTipo.setEnabled(false);
						
						Queso queso = Fabrica.getInstancias().quesos.Buscar_queso_indice(table.getSelectedRow());
						
						switch(btnEditar.getText()) {
						case "Editar" :
							//MODELO TAB --> ["Cant","Nombre","Tipo de Queso", "Radio", "Longitud", "Radio Menor", "Precio Unitario" ]
							cbxTipo.setEnabled(false);
							btnRegistrar.setEnabled(false);
							btnEliminar.setEnabled(false);
							btnEditar.setText("Salvar");
							txtNombre.setText(queso.getNombre());
							spnRadio.setValue(table.getValueAt(table.getSelectedRow(), 3));
							spnRadio.setEnabled(true);
							spnCantidad.setValue(table.getValueAt(table.getSelectedRow(), 0));
							spnCantidad.setEnabled(true);
							spnPrecioUnt.setValue(table.getValueAt(table.getSelectedRow(), 6));
							spnPrecioUnt.setEnabled(true);
							cbxTipo.setSelectedItem(1);
							
							if(queso instanceof Cilindrico) {
								cbxTipo.setSelectedIndex(2);
								spnLongitud.setValue(table.getValueAt(table.getSelectedRow(), 6));
								spnLongitud.setEnabled(true);
							}
							if(queso instanceof CilindricoHueco) {
								cbxTipo.setSelectedIndex(3);
								spnLongitud.setValue(table.getValueAt(table.getSelectedRow(), 4));
								spnLongitud.setEnabled(true);
								spnRadioMenor.setValue(table.getValueAt(table.getSelectedRow(), 5));
								spnRadioMenor.setEnabled(true);
							}
							if(queso instanceof Esferico) {
								cbxTipo.setSelectedIndex(1);
								spnRadio.setValue(table.getValueAt(table.getSelectedRow(), 5));
								spnRadio.setEnabled(true);
							}
							
							break;
						case "Salvar" : 
							if(queso instanceof Cilindrico) {
								queso.setNombre(txtNombre.getText());
								queso.setRadio(Float.valueOf(spnRadio.getValue().toString()));
								queso.setPrecio_unitario(Float.valueOf(spnPrecioUnt.getValue().toString()));
								queso.setCantidad(Integer.valueOf(spnCantidad.getValue().toString()));
								((Cilindrico)queso).setLongitud(Float.valueOf(spnLongitud.getValue().toString()));
								
							}
							if(queso instanceof CilindricoHueco) {
								queso.setNombre(txtNombre.getText());
								queso.setRadio(Float.valueOf(spnRadio.getValue().toString()));
								queso.setPrecio_unitario(Float.valueOf(spnPrecioUnt.getValue().toString()));
								queso.setCantidad(Integer.valueOf(spnCantidad.getValue().toString()));
								((CilindricoHueco)queso).setLongitud(Float.valueOf(spnLongitud.getValue().toString()));
								((CilindricoHueco) queso).setRadio_interno(Float.valueOf(spnRadioMenor.getValue().toString()));
							}
							if(queso instanceof Esferico) {
								queso.setNombre(txtNombre.getText());
								queso.setRadio(Float.valueOf(spnRadio.getValue().toString()));
								queso.setPrecio_unitario(Float.valueOf(spnPrecioUnt.getValue().toString()));
								queso.setCantidad(Integer.valueOf(spnCantidad.getValue().toString()));
								
								
							}
							//SALVADO LOS CAMBIOS
							Fabrica.getInstancias().quesos.getInventario().set(table.getSelectedRow(), queso);
							LimpiarTab();
							JOptionPane.showMessageDialog(null, "Modificacion Exitosa", "Aviso",JOptionPane.INFORMATION_MESSAGE );
							CargarTabQ();
							btnRegistrar.setEnabled(true);
							btnEliminar.setEnabled(true);
							btnEditar.setText("Editar");
							btnEditar.setEnabled(false);
							btnRegistrar.setEnabled(true);
							btnEliminar.setEnabled(false);
							cbxTipo.setEnabled(true);
							
							break;
							
						}
						
					}
				});
				buttonPane.add(btnEditar);
			}
			{
				 cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Salir");
				buttonPane.add(cancelButton);
			}
		}
	}
	//Metodos
	public void LimpiarTab() {
		txtNombre.setText("");
		//cbxTipo.setSelectedIndex(0);
		spnRadio.setValue((int)1);
		spnRadioMenor.setValue((int)1);
		spnLongitud.setValue((int)1);
		spnCantidad.setValue((int)1);
		spnPrecioUnt.setValue((int)1);
		
	}
	public void CargarTabQ() {
		tabModelo.setRowCount(0); //Inicializando la tabla en 0
		fila = new Object[tabModelo.getColumnCount()]; //Creando Arreglo de Objetos por la cantidad de Columnas
		
		
		//MODELO TABLA --> [ CANTIDAD / LONGITUD / RADIO INTERNO/ PRECIO UNITARIO ] 
		for(Queso queso : Fabrica.getInstancias().quesos.getInventario()) {
			if(queso instanceof Esferico) {
			fila[0] = queso.getCantidad();
			fila[1] = queso.getNombre();
			fila[2] = ((Esferico)queso).getTipo();
			fila[3] = queso.getRadio();
			fila[4] = " N / A";
			fila[5] = "N / A";
			fila[6] = queso.getPrecio_unitario();
			}
			if(queso instanceof Cilindrico) {
				fila[0] = queso.getCantidad();
				fila[1] = queso.getNombre();
				fila[2] = ((Cilindrico)queso).getTipo();
				fila[3] = queso.getRadio();
				fila[4] = ((Cilindrico) queso).getLongitud();
				fila[5] = "N / A";
				fila[6] = queso.getPrecio_unitario();
				}
			if(queso instanceof CilindricoHueco) {
				fila[0] = queso.getCantidad();
				fila[1] = queso.getNombre();
				fila[2] = ((CilindricoHueco)queso).getTipo();
				fila[3] = queso.getRadio();
				fila[4] = ((CilindricoHueco)queso).getLongitud();
				fila[5] = ((CilindricoHueco)queso).getRadio_interno();
				fila[6] = queso.getPrecio_unitario();
				}
			tabModelo.addRow(fila);
		}
		table.setModel(tabModelo);	
	}
}
