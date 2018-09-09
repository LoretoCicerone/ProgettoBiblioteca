package GUI;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class _FINITO_APPOGGIO_popupOk
{
	public _FINITO_APPOGGIO_popupOk(String titolo, String testo)
	{
		JOptionPane popup = new JOptionPane(testo, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialogo = popup.createDialog(titolo);
		dialogo.setVisible(true);
	}

	public static void main(String[] args)
	{
		_FINITO_APPOGGIO_popupOk prova = new _FINITO_APPOGGIO_popupOk("Bravo", "Tutto ok");

	}
}
