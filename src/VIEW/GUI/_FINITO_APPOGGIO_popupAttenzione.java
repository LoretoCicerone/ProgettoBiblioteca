package GUI;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class _FINITO_APPOGGIO_popupAttenzione extends JFrame
{
	public _FINITO_APPOGGIO_popupAttenzione(String titolo, String testo)
	{
		JOptionPane popup = new JOptionPane(testo, JOptionPane.ERROR_MESSAGE);
		JDialog dialogo = popup.createDialog(titolo);
		dialogo.setVisible(true);
	}

	public static void main(String[] args)
	{
		_FINITO_APPOGGIO_popupAttenzione prova = new _FINITO_APPOGGIO_popupAttenzione("Attenzione", "Sei sicuro di volerlo eliminare per davvero?");

	}
}
