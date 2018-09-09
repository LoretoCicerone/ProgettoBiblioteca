package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import VIEW.funzionalitaVarieVIEW;

public class _FINITO_funzione_ricercaOpera extends JFrame
{
	// ----------ELEMENTI----------------------//

	// LAYOUT
	private GridBagLayout		griglia	= new GridBagLayout();
	private GridBagConstraints	ca		= new GridBagConstraints();
	int							idUtente;


	// ---------COSTRUTTORE--------------------//

	public _FINITO_funzione_ricercaOpera(int ID_utente)
	{
		this.idUtente = ID_utente;
		inizializzaFrame();
		inizializzaPannello();
		setVisible(true);
	}

	// ----------METODI-----------------------//

	public void inizializzaFrame()
	{
		this.setTitle("PANNELLO VISUALIZZAZIONE MANOSCRITTO");
		this.setSize(700, 500);
		this.setMinimumSize(new Dimension(700, 500));
		this.setResizable(true);
		this.setLayout(griglia);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void inizializzaPannello()
	{
		pannelloPrincipale pannello = new pannelloPrincipale();

		ca.weightx = 1;
		ca.weighty = 1;

		ca.fill = GridBagConstraints.BOTH;

		set(ca, 0, 0, 1, 1);
		this.addC(pannello);
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
	}

	// ----------CLASSI COMPONENTI------------//

	private class pannelloPrincipale extends JPanel
	{
		// ELEMENTI
		private String		vectGeneri[]	= { "Fantasy", "Medievale", "Guerra", "Storico", "Saggio", "Ricerche", "Religioso", "Artistico", "Tecnico", "Letteratura", "Poesia", "ALTRO" };
		private JComboBox	generi			= new JComboBox(vectGeneri);

		JLabel	titoloManoscritto	= new JLabel("TITOLO MANOSCRITTO");
		JLabel	annoPubblicazione	= new JLabel("GENERE");
		JLabel	autoreOpera			= new JLabel("AUTORE MANOSCRITTO");

		JTextField	testoTitoloManoscritto	= new JTextField();
		JTextField	testoAutoreOpera		= new JTextField();

		JButton cerca = new JButton("CERCA");

		// LAYOUT
		GridBagLayout		griglia	= new GridBagLayout();
		GridBagConstraints	ca		= new GridBagConstraints();

		// COSTRUTTORE
		pannelloPrincipale()
		{
			testoTitoloManoscritto.setText(null);
			testoAutoreOpera.setText(null);

			setLayout(griglia);
			ca.weightx = 1;
			ca.weighty = 1;

			// -------------------------------//

			ca.ipady = 20;
			ca.ipadx = 200;
			set(ca, 0, 0, 1, 1);
			addC(titoloManoscritto);

			// -------------------------------//

			set(ca, 1, 0, 1, 1);
			addC(testoTitoloManoscritto);

			// -------------------------------//

			set(ca, 0, 1, 1, 1);
			addC(annoPubblicazione);

			// -------------------------------//

			set(ca, 1, 1, 1, 1);
			addC(generi);

			// -------------------------------//

			set(ca, 0, 2, 1, 1);
			addC(autoreOpera);

			// -------------------------------//

			set(ca, 1, 2, 1, 1);
			addC(testoAutoreOpera);

			// -------------------------------//

			set(ca, 0, 3, 2, 1);
			addC(cerca);

			// -------------------------------//

			inizializzaActionListner();
		}

		// METODI
		private void inizializzaActionListner()
		{
			cerca.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new funzionalitaVarieVIEW().premutoCerca(testoTitoloManoscritto, generi, testoAutoreOpera, idUtente);
				}
			});
		}

		private void addC(Component componente)
		{
			add(componente, ca);
		}

		private void set(GridBagConstraints lim, int gridx, int gridy, int gridwidth, int gridheight)
		{
			lim.gridx = gridx;
			lim.gridy = gridy;
			lim.gridwidth = gridwidth;
			lim.gridheight = gridheight;
		}

		private void settaggioEtichetta(JLabel etichetta, Color colore, String testo)
		{
			etichetta.setOpaque(true);
			etichetta.setBackground(colore);
			if (testo != null)
			{
				etichetta.setText(testo);
			}
		}

	}

	// ---------------------------------------//
	//
	//
	//
	//
	// ----------MAIN-------------------------//
	public static void main(String[] args)
	{
		_FINITO_funzione_ricercaOpera prova = new _FINITO_funzione_ricercaOpera(0);
	}

}
