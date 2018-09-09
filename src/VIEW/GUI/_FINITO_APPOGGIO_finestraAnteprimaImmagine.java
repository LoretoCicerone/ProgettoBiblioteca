package GUI;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class _FINITO_APPOGGIO_finestraAnteprimaImmagine extends JFrame
{
	// -----------------------ATTRIBUTI-----------------------------------//

	JLabel	immagine;
	String	pathImmagine;

	JPanel pannello = new JPanel();

	GridLayout griglia = new GridLayout(1, 1);

	// -----------------------COSTRUTTORE---------------------------------//

	public _FINITO_APPOGGIO_finestraAnteprimaImmagine(String pathImmagine)
	{
		this.pathImmagine = pathImmagine;
		inizializzaFrame();

		ImageIcon immagineIcona = new ImageIcon(this.pathImmagine);

		immagine = new JLabel(immagineIcona);

		pannello.add(immagine);
		JScrollPane scrollBar = new JScrollPane(pannello);

		add(scrollBar);

		this.pack();
		this.setVisible(true);
	}

	// -------------------------METODI------------------------------------//

	public void inizializzaFrame()
	{
		this.setTitle("ANTEPRIMA IMMAGINE: " + pathImmagine);
		this.setSize(500, 500);
		this.setLocation(800, 0);
		this.setResizable(true);
		this.setLayout(griglia);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	// -------------------------------------------------------------------//
	public static void main(String[] args)
	{
		_FINITO_APPOGGIO_finestraAnteprimaImmagine bubba = new _FINITO_APPOGGIO_finestraAnteprimaImmagine("E:\\Immagini\\6E4dIXW.jpg");
	}
}
