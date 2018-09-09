package GUI;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class _FINITO_APPOGGIO_popupOkConArray
{
	public _FINITO_APPOGGIO_popupOkConArray(String titolo, String testo, ArrayList<String> lista)
	{
		String testoLocale = testo;

		for (int i = 0; i < lista.size(); i++)
		{
			testoLocale = testoLocale + lista.get(i) + ", ";
		}

		JOptionPane popup = new JOptionPane(testoLocale, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialogo = popup.createDialog(titolo);
		dialogo.setVisible(true);
	}

	public static void main(String[] args)
	{
		_FINITO_APPOGGIO_popupOkConArray prova = new _FINITO_APPOGGIO_popupOkConArray("Bravo", "Tutto ok", null);

	}
}
