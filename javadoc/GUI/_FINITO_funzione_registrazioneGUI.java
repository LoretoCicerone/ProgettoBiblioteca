package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import VIEW._FINITO_registrazioneVIEW;

public class _FINITO_funzione_registrazioneGUI extends JFrame
{
	// --------------VARIABILI-----------------------------------------------//
	JFrame finestra = this;
	// LAYOUT
	private GridLayout griglia = new GridLayout(1, 2);

	// --------------COSTRUTTORE---------------------------------------------//

	public _FINITO_funzione_registrazioneGUI()
	{
		inizializzaFrame();
		creazionePannello();
		this.setMinimumSize(new Dimension(800, 550));
		this.setVisible(true);

	}

	// ---------------METODI-------------------------------------------------//
	public void inizializzaFrame()
	{
		this.setTitle("CREAZIONE ACCOUNT BIBLIOTECA DIGITALE");
		this.setSize(1000, 900);
		this.setResizable(false);
		this.setLayout(griglia);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void creazionePannello()
	{
		pannelloRegistrazione pannello = new pannelloRegistrazione();

		this.add(new pannelloRegistrazione());
	}

	// --------------CLASSI COMPONENTI---------------------------------------//
	private class pannelloRegistrazione extends JLabel
	{
		// VARIABILI

		// ETICHETTE
		private JLabel titolo = new JLabel("CREA IL TUO ACCOUNT", SwingConstants.CENTER);

		private JLabel	Nickname			= new JLabel("INSERISCI UN NICKNAME", SwingConstants.CENTER);
		private JLabel	Nome				= new JLabel("INSERISCI UN NOME", SwingConstants.CENTER);
		private JLabel	Cognome				= new JLabel("INSERISCI UN COGNOME", SwingConstants.CENTER);
		private JLabel	Email				= new JLabel("INSERISCI UNA E-MAIL", SwingConstants.CENTER);
		private JLabel	ConfermaEmail		= new JLabel("CONFERMA E-MAIL", SwingConstants.CENTER);
		private JLabel	Professione			= new JLabel("INSERISCI UNA PROFESSIONE", SwingConstants.CENTER);
		private JLabel	TitoloStudio		= new JLabel("INSERISCI UN TITOLO DI STUDIO", SwingConstants.CENTER);
		private JLabel	DataNascita			= new JLabel("<html>INSERISCI UNA DATA DI NASCITA<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;YYYY-MM-dd</html>", SwingConstants.CENTER);
		private JLabel	Password			= new JLabel("INSERISCI UNA PASSWORD", SwingConstants.CENTER);
		private JLabel	ConfermaPassword	= new JLabel("CONFERMA LA PASSWORD", SwingConstants.CENTER);
		private JLabel	scegliFoto			= new JLabel("<html>SCEGLI UNA IMMAGINE<br>PER IL PROFILO</html>", SwingConstants.CENTER);

		// TESTO
		private JTextField	testo_nickname			= new JTextField();
		private JTextField	testo_nome				= new JTextField();
		private JTextField	testo_cognome			= new JTextField();
		private JTextField	testo_email				= new JTextField();
		private JTextField	testo_conferma_email	= new JTextField();
		private JTextField	testo_data_nascita		= new JTextField();
		private JTextField	testo_password			= new JTextField();
		private JTextField	testo_conferma_password	= new JTextField();

		// LAYOUT
		private GridBagLayout		gridBag	= new GridBagLayout();
		private GridBagConstraints	ca		= new GridBagConstraints();

		// JCOMBOBOX PER PROFESSIONE
		private String		vectProfessione[]	= { "studente", "professore", "professore Universitario", "storico", "altro" };
		private JComboBox	professione			= new JComboBox(vectProfessione);

		String		titoloStudio[]	= { "Scuole Medie", "Scuole Superiori", "Laurea" };
		JComboBox	listaTitoli		= new JComboBox(titoloStudio);
		JScrollPane	sp2				= new JScrollPane(listaTitoli);

		// BOTTONI
		private JButton	bottone_iscriviti		= new JButton("ISCRIVITI");
		private JButton	bottone_indietro		= new JButton("INDIETRO");
		private JButton	bottone_sceltaImmagine	= new JButton("SCEGLI UNA IMMAGINE");

		// FOTO
		private JLabel fotoProfilo = new JLabel("IMMAGINE", SwingConstants.CENTER);

		// COSTRUTTORE
		public pannelloRegistrazione()
		{
			setLayout(gridBag);
			ca.weightx = 1;
			ca.weighty = 1;

			ca.fill = GridBagConstraints.HORIZONTAL;
			ca.ipady = 40;
			set(ca, 0, 0, 4, 1);
			addC(titolo);

			// -----------------//
			ca.fill = GridBagConstraints.NONE;
			ca.anchor = GridBagConstraints.CENTER;
			ca.ipady = 20;

			set(ca, 0, 1, 1, 1);
			addC(Nickname);

			set(ca, 0, 2, 1, 1);
			addC(Nome);

			set(ca, 0, 3, 1, 1);
			addC(Cognome);

			set(ca, 0, 4, 1, 1);
			addC(Email);

			set(ca, 0, 5, 1, 1);
			addC(ConfermaEmail);

			set(ca, 0, 6, 1, 1);
			addC(Password);

			set(ca, 0, 7, 1, 1);
			addC(ConfermaPassword);

			set(ca, 2, 1, 1, 1);
			addC(Professione);

			set(ca, 0, 9, 1, 1);
			addC(TitoloStudio);

			set(ca, 0, 10, 1, 1);
			addC(DataNascita);

			// -----------------//
			ca.fill = GridBagConstraints.HORIZONTAL;
			ca.insets.left = 16;
			ca.insets.right = 16;
			ca.ipady = 20;

			set(ca, 1, 1, 1, 1);
			addC(testo_nickname);

			set(ca, 1, 2, 1, 1);
			addC(testo_nome);

			set(ca, 1, 3, 1, 1);
			addC(testo_cognome);

			set(ca, 1, 4, 1, 1);
			addC(testo_email);

			set(ca, 1, 5, 1, 1);
			addC(testo_conferma_email);

			set(ca, 1, 6, 1, 1);
			addC(testo_password);

			set(ca, 1, 7, 1, 1);
			addC(testo_conferma_password);

			set(ca, 3, 1, 1, 1);
			addC(professione);

			set(ca, 1, 9, 1, 1);
			addC(sp2);

			set(ca, 1, 10, 1, 1);
			addC(testo_data_nascita);

			// -------------------------//

			set(ca, 1, 11, 1, 1);
			addC(bottone_iscriviti);

			set(ca, 2, 11, 1, 1);
			addC(bottone_indietro);

			// --------------------------//

			inizializzaActionListner();
		}

		// METODI
		public void inizializzaActionListner()
		{
			bottone_iscriviti.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					boolean registrato = new _FINITO_registrazioneVIEW().controllaDatiInseriti(testo_nickname.getText(), testo_nome.getText(), testo_cognome.getText(), testo_email.getText(), testo_conferma_email.getText(), professione, testo_data_nascita.getText(), testo_password.getText(), testo_conferma_password.getText(), listaTitoli);
					if (registrato)
					{
						finestra.setVisible(false);
						finestra.dispose();

					}
				}
			});

			bottone_indietro.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					finestra.setVisible(false);
					finestra.dispose();
					new _FINITO_funzione_loginGUI();
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

		public void settaggioEtichetta(JLabel etichetta, Color colore, String testo)
		{
			etichetta.setOpaque(true);
			etichetta.setBackground(colore);
			if (testo != null)
			{
				etichetta.setText(testo);
			}
		}
	}

	// ----------------------------------------------------------------------//
	//
	//
	//
	//
	// -------------------MAIN-----------------------------------------------//

	public static void main(String[] args)
	{
		_FINITO_funzione_registrazioneGUI prova = new _FINITO_funzione_registrazioneGUI();
	}

}
