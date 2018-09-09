package GUI;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class _FINITO_APPOGGIO_finestraAnteprimaImmagini extends JFrame
{
	// -----------------------ATTRIBUTI-----------------------------------//

	String pathImmaginiLocale[];

	int	numPag;
	int	colonne;
	int	righe;

	JFrame		frame		= this;
	JPanel		contenitore	= new JPanel();
	JScrollPane	scrollBar	= new JScrollPane(contenitore);

	// -----------------------COSTRUTTORE---------------------------------//

	public _FINITO_APPOGGIO_finestraAnteprimaImmagini(String pathImmagini[])
	{
		pathImmaginiLocale = pathImmagini;

		numPag = pathImmaginiLocale.length;

		if ((numPag % 2) == 0)
		{
			this.setSize(600, 600);
			colonne = 2;
			righe = numPag / 2;
		}
		else
		{
			this.setSize(800, 600);
			colonne = 3;
			righe = (numPag / 3) + 1;
		}

		inizializzaFrame();
		inizializzaInterfaccia();

		setVisible(true);
	}

	// -------------------------METODI------------------------------------//

	public void inizializzaFrame()
	{
		this.setTitle("ANTEPRIMA IMMAGINI SELEZIONATE");
		this.setResizable(false);
		this.setLayout(new GridLayout(1, 1));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void inizializzaInterfaccia()
	{
		contenitore.setLayout(new GridLayout(righe, colonne));

		for (int i = 0; i < pathImmaginiLocale.length; i++)
		{
			String pathUtile = pathImmaginiLocale[i];

			contenitore.add(new pannello(pathUtile, i));
		}

		this.add(scrollBar);

	}

	// --------------------CLASSI COMPONENTI-------------------------------//

	public class pannello extends JPanel
	{
		// ------------VARIABILI----------------------//

		GridBagLayout		grid	= new GridBagLayout();
		GridBagConstraints	ca		= new GridBagConstraints();

		int numPagina;

		// -----------COSTRUTTORE---------------------//

		public pannello(String path, int i)
		{
			numPagina = i;

			this.setLayout(grid);

			ImageIcon immagine = new ImageIcon(path);


			// -------------------------RIDIMENSIONAMENTO IMMAGINE--------------------------//

			ImageIcon immagineIcona = new ImageIcon(path);
			Image immagine2 = immagineIcona.getImage();
			Image nuovaImmagine = immagine2.getScaledInstance(200, 300, java.awt.Image.SCALE_SMOOTH);
			immagineIcona = new ImageIcon(nuovaImmagine);

			JLabel zonaImmagine = new JLabel(immagineIcona);

			// -----------------------------------------------------------------------------//





			zonaImmagine.setOpaque(true);

			JLabel label = new JLabel("pagina " + i, SwingConstants.CENTER);

			ca.insets.left = 15;
			ca.insets.right = 15;
			ca.insets.bottom = 15;
			ca.insets.top = 15;

			set(ca, 0, 0, 1, 1);
			addC(zonaImmagine);

			set(ca, 0, 1, 1, 1);
			addC(label);

		}

		// -----------METODI---------------------------//

		public void addC(Component componente)
		{
			add(componente, ca);
		}

		public void set(GridBagConstraints lim, int gridx, int gridy, int gridwidth, int gridheight)
		{
			lim.gridx = gridx;
			lim.gridy = gridy;
			lim.gridwidth = gridwidth;
			lim.gridheight = gridheight;
		}

	}

	// ---------------------------------------------------------------------//
	//
	//
	//
	//
	// ----------------------MAIN-----------------------------------------//
	public static void main(String[] args)
	{
		String path[] = { "E:\\Immagini\\immagini prova proggetto OOP\\Aeroina.jpg", "E:\\Immagini\\immagini prova proggetto OOP\\Balcone.jpg", "E:\\Immagini\\immagini prova proggetto OOP\\Cagneti.jpeg", "E:\\Immagini\\immagini prova proggetto OOP\\Cane.jpg", "E:\\Immagini\\immagini prova proggetto OOP\\Cose.jpg" };

		_FINITO_APPOGGIO_finestraAnteprimaImmagini Anteprimaimg = new _FINITO_APPOGGIO_finestraAnteprimaImmagini(path);
	}

}
