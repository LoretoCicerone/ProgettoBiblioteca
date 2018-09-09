package GUI;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import MODEL.A_MANOSCRITTO_0_Manoscritto;
import VIEW.funzionalitaVarieVIEW;

public class _FINITO_APPOGGIO_finestraSceltaOperaDaVisualizzare extends JFrame
{
	// -----------------------ATTRIBUTI-----------------------------------//

	String pathImmaginiLocale[];

	int	numPag;
	int	colonne;
	int	righe;

	JFrame		frame		= this;
	JPanel		contenitore	= new JPanel();
	JScrollPane	scrollBar	= new JScrollPane(contenitore);

	ArrayList<A_MANOSCRITTO_0_Manoscritto> risultato;

	// -----------------------COSTRUTTORE---------------------------------//

	public _FINITO_APPOGGIO_finestraSceltaOperaDaVisualizzare(ArrayList<A_MANOSCRITTO_0_Manoscritto> risultatoRicerca)
	{
		String[] arrayPath = new String[risultatoRicerca.size()];
		this.risultato = risultatoRicerca;


		for (int i = 0; i < risultatoRicerca.size(); i++)
		{
			arrayPath[i] = risultatoRicerca.get(i).getPathImmagineCopertina();
		}

		pathImmaginiLocale = arrayPath;

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
			contenitore.add(new pannello(risultato.get(i).getPathImmagineCopertina(), risultato.get(i).getTitolo(), risultato.get(i)));
		}

		this.add(scrollBar);

	}

	// --------------------CLASSI COMPONENTI-------------------------------//

	public class pannello extends JPanel
	{
		// ------------VARIABILI----------------------//

		GridBagLayout				grid	= new GridBagLayout();
		GridBagConstraints			ca		= new GridBagConstraints();
		A_MANOSCRITTO_0_Manoscritto	operaCorrente;
		JButton						zonaImmagine;
		String						nomeOpera;
		String						pathOpera;

		// -----------COSTRUTTORE---------------------//

		public pannello(String path, String nomeOpera, A_MANOSCRITTO_0_Manoscritto operaCorrente)
		{
			this.operaCorrente = operaCorrente;
			this.nomeOpera = nomeOpera;
			this.pathOpera = path;

			this.setLayout(grid);

			// -------------------------RIDIMENSIONAMENTO IMMAGINE--------------------------//

			ImageIcon immagineIcona = new ImageIcon(path);
			Image immagine = immagineIcona.getImage();
			Image nuovaImmagine = immagine.getScaledInstance(200, 300, java.awt.Image.SCALE_SMOOTH);
			immagineIcona = new ImageIcon(nuovaImmagine);

			zonaImmagine = new JButton(immagineIcona);

			// -----------------------------------------------------------------------------//


			JLabel label = new JLabel(nomeOpera, SwingConstants.CENTER);

			ca.insets.left = 15;
			ca.insets.right = 15;
			ca.insets.bottom = 15;
			ca.insets.top = 15;

			set(ca, 0, 0, 1, 1);
			addC(zonaImmagine);

			set(ca, 0, 1, 1, 1);
			addC(label);

			inizializzaActionListner();
		}

		// -----------METODI---------------------------//

		public void inizializzaActionListner()
		{
			zonaImmagine.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					// seleziono l'opera da visualizzare e apro il pannello visualizza opera
					new funzionalitaVarieVIEW().apriPannelloVisualizzaOpera(operaCorrente);
					frame.dispose();
				}
			});

		}

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




}
