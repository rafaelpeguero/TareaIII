package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.LineBorder;

import logico.Cliente;
import logico.Fabrica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("unused")
public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	//DECLARACIONES
	private JFrame frmQueseria;
	private Dimension dim;
	private Fabrica fabrica;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
					//extras
					Rectangle vlrect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds(); // Almacena la dimension de la pantalla
					frame.setSize(vlrect.width, vlrect.height);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setUndecorated(false); //Disables or enables decorations for this frame.
		
		setTitle("Queseria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1621, 21);
		contentPane.add(menuBar);
		
		JMenu mnRegistros = new JMenu("Registros");
		menuBar.add(mnRegistros);
		
		JMenuItem mntmRegistroDeCliente = new JMenuItem("Registro de Cliente");
		mntmRegistroDeCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CREANDO UN OBJETO CLIENTE
				ObjCliente obj_cliente = new ObjCliente();
				obj_cliente.setVisible(true);
				obj_cliente.setLocationRelativeTo(null);
			}
		});
		mnRegistros.add(mntmRegistroDeCliente);
		
		JMenuItem mntmRegistroDeQueso = new JMenuItem("Registro de Queso");
		mntmRegistroDeQueso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// CREANDO UN OBJETO QUESO
				ObjQueso obj_queso = new ObjQueso();
				obj_queso.setVisible(true);
				obj_queso.setLocationRelativeTo(null);
			}
		});
		mnRegistros.add(mntmRegistroDeQueso);
		
		JMenu mnFacturas = new JMenu("Facturas");
		menuBar.add(mnFacturas);
		
		JMenuItem mntmFacturacion = new JMenuItem("Facturacion ");
		mntmFacturacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// CREANDO UN OBJETO DE FACTURACION
				ObjFactura obj_factura  = new ObjFactura();
				obj_factura.setVisible(true);
				obj_factura.setLocationRelativeTo(null);
			}
		});
		mnFacturas.add(mntmFacturacion);
		
		JMenuItem mntmVisorDeFacturas = new JMenuItem("Visor de Facturas");
		mntmVisorDeFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// CREANDO UN OBJETO DE
				ObjVisorFactura obj_visor_factura = new ObjVisorFactura();
				obj_visor_factura.setVisible(true);
				obj_visor_factura.setLocationRelativeTo(null);
			}
		});
		mnFacturas.add(mntmVisorDeFacturas);
		
		JMenu mnReporte = new JMenu("Reporte");
		menuBar.add(mnReporte);
		
		JMenuItem mntmGenerarReporte = new JMenuItem("Generar Reporte");
		mntmGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObjReporte obj_reporte = new ObjReporte();
				obj_reporte.setVisible(true);
				obj_reporte.setLocationRelativeTo(null);
			}
		});
		mnReporte.add(mntmGenerarReporte);
	}
}
