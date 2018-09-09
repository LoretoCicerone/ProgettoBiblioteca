package GUI;

import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class _FINITO_APPOGGIO_popupSiNo extends JFrame
{
	// VARIABILI
	JOptionPane popup;

	// COSTRUTTORE
	public _FINITO_APPOGGIO_popupSiNo(String titolo, String testo)
	{
		this.setTitle(titolo);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contenitore = this.getContentPane();
		popup = new JOptionPane(testo, JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
		JDialog dialogo = popup.createDialog(this, titolo);
		dialogo.setVisible(true);
	}

	// METODI
	public int getValore()
	{
		int n = (Integer) popup.getValue();
		return n;
	}


	public static void main(String[] args)
	{
		_FINITO_APPOGGIO_popupSiNo bubba = new _FINITO_APPOGGIO_popupSiNo("Attenzione!", "Sei sicuro di volerlo davvero cancellare?");
		System.out.println(bubba.getValore());
	}
}
