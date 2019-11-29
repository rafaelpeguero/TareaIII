package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Fabrica;
import logico.Factura;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("unused")
public class ObjVisorFactura extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tabFacturas;
	Object[] fila;
	private DefaultTableModel tabModel;
	private JButton btnEliminar;
	private JButton btnSalir;
	private Fabrica fabrica;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ObjVisorFactura dialog = new ObjVisorFactura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ObjVisorFactura() {
		setResizable(false);
		setTitle("Visor de Facturas");
		setBounds(100, 100, 560, 476);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(10, 403, 534, 33);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				 btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int opt = tabFacturas.getSelectedRow();
						int vl = JOptionPane.showConfirmDialog(null, "Desea Eliminar esta Factura ?", "Aviso", JOptionPane.YES_NO_OPTION);
						if(vl == JOptionPane.YES_OPTION) {
							Fabrica.getInstancias().facturas.remove(tabFacturas.getSelectedRow()); // Cambiado a Estatico, Fabrica en ves de fabrica
							
							CargarFacturas();
						}
					}
				});
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
			}
			{
				 btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Salir");
				buttonPane.add(btnSalir);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 534, 381);
			contentPanel.add(scrollPane);
			{
				tabFacturas = new JTable();
				String[] colNames = {"Nombre Factura","Nombre Cliente","Total"};
				tabModel = new DefaultTableModel();
				tabModel.setColumnIdentifiers(colNames);
				tabFacturas.getTableHeader().setResizingAllowed(false);
				tabFacturas.getTableHeader().setReorderingAllowed(false);
				tabFacturas.setModel(tabModel);
				
				scrollPane.setViewportView(tabFacturas);
				CargarFacturas();
			}
		}
	}
	private void CargarFacturas() {
		tabModel.setRowCount(0);
		
		fila = new Object[tabModel.getColumnCount()];
		for(Factura aux : Fabrica.getInstancias().getFacturas()) {
			fila[0] = aux.getID();
			fila[1] = aux.getFactura_cliente().getNombre();
			fila[2] = aux.Total_factura();
			
			tabModel.addRow(fila);
		}
		tabFacturas.setModel(tabModel);
	}
	
}
