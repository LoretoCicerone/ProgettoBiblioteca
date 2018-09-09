package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import VIEW._FINITO_profiloUtenteVIEW;
import VIEW._FINITO_sezioneAmministratoreVIEW;
import VIEW._FINITO_sezioneManagerVIEW;

public class _FINITO_UtenteProfiloGUI extends JFrame
{
	// ---------VARIABILI----------------------------------------------------------------------------//
	private _FINITO_UtenteProfiloGUI	soggetto	= this;
	private String						nik;
	boolean								utente_vip;
	boolean								utente_trascrittore;
	boolean								utente_manager;
	boolean								utente_uploader;

	String	nome;
	String	cognome;
	String	data_nascita;
	String	email;
	String	password;
	String	titolo_studio;
	String	professione;


	public static JLabel Foto = new JLabel();

	// LAYOUT
	private GridLayout griglia = new GridLayout(2, 2);

	// PANNELLI
	private pannelloModificaDatiUtente	pannUtente	= null;
	private pannelloCheUtenteSei		pannInfo	= null;
	private pannelloDiventaTrascrittore	pannDiventa	= new pannelloDiventaTrascrittore();
	private pannelloWorkbench			pannWork	= new pannelloWorkbench();

	// -----------COSTRUTTORE-------------------------------------------------------------------------------//

	public _FINITO_UtenteProfiloGUI(String nickname, String nome, String cognome, String data_nascita, String email, String password, String titolo_studio, String professione, boolean vip, boolean manager, boolean uploader, boolean trascrittore)
	{
		// settaggio nickname utile per compiere azioni riferite al nickname con cui si è loggati
		this.nik = nickname;
		this.nome = nome;
		this.cognome = cognome;
		this.data_nascita = data_nascita;
		this.email = email;
		this.password = password;
		this.titolo_studio = titolo_studio;
		this.professione = professione;



		inizializza_frame();
		inizializza_interfaccia(nickname, nome, cognome, data_nascita, email, password, titolo_studio, professione, vip, manager, uploader, trascrittore);
		inizializza_immagine();

		this.setMinimumSize(new Dimension(1200, 932));
		this.setVisible(true);
	}

	// -----------METODI -----------------------------------------------------------------------------------//

	private void inizializza_frame()
	{
		this.setTitle("PROFILO UTENTE");
		this.setSize(1200, 932);
		this.setResizable(true);
		this.setLayout(griglia);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void inizializza_interfaccia(String nickname, String nome, String cognome, String data_nascita, String email, String password, String titolo_studio, String professione, boolean vip, boolean manager, boolean uploader, boolean trascrittore)
	{
		utente_vip = vip;
		utente_trascrittore = trascrittore;
		utente_manager = manager;
		utente_uploader = uploader;

		pannUtente = new pannelloModificaDatiUtente(nickname, nome, cognome, data_nascita, email, password, titolo_studio, professione);
		pannInfo = new pannelloCheUtenteSei(vip, manager, uploader, trascrittore);

		this.add(pannUtente);
		this.add(pannInfo);
		this.add(pannDiventa);
		this.add(pannWork);
	}

	private void inizializza_immagine()
	{
		// settagio immagine di profilo che l'utente ha scelto in precedenza, se è la prima volta che si accede si gestisce l'eventualità
		String pathImmagineProfilo = new _FINITO_profiloUtenteVIEW().getImmagineProfilo(nik);
		if (pathImmagineProfilo == null)
		{
			// primo accesso dopo l'iscrizione, perciò non si è mai scelta l'immagine
			System.out.println("Primo accesso al profilo, nessuna immagine selezionata");

		}
		else
		{
			// dato che è stata già scelta, si imposta l'immagine associata al proprio profilo dal database
			System.out.println("Immagine del profilo CORRETTAMENTE caricata");
			Foto.setIcon(new ImageIcon(pathImmagineProfilo));

		}
	}

	// ----------CLASSI COMPONENTI--------------------------------------------------------------------------//

	public class pannelloModificaDatiUtente extends JPanel
	{
		// VARIABILI
		GridBagLayout		grid	= new GridBagLayout();
		GridBagConstraints	ca		= new GridBagConstraints();
		// ETICHETTE
		JLabel	titoloSezione	= new JLabel("SEZIONE MODIFICA DATI DEL PROFILO", SwingConstants.CENTER);
		JLabel	Nickname		= new JLabel("NICKNAME:", SwingConstants.LEFT);
		JLabel	Nome			= new JLabel("NOME:", SwingConstants.LEFT);
		JLabel	Cognome			= new JLabel("COGNOME:", SwingConstants.LEFT);
		JLabel	Email			= new JLabel("E-MAIL:", SwingConstants.LEFT);
		JLabel	Password		= new JLabel("PASSWORD:", SwingConstants.LEFT);
		JLabel	Professione		= new JLabel("PROFESSIONE:", SwingConstants.LEFT);
		JLabel	Titolo_Studio	= new JLabel("TITOLO DI STUDIO:", SwingConstants.LEFT);
		JLabel	Data_Nascita	= new JLabel("<html>DATA DI NASCITA: YYYY-MM-dd</html>", SwingConstants.LEFT);
		JLabel	vect[]			= { titoloSezione, Nickname, Nome, Cognome, Email, Password, Professione, Titolo_Studio, Data_Nascita };
		// SEZIONE TESTO
		JTextField	testo_nickname		= new JTextField();
		JTextField	testo_nome			= new JTextField();
		JTextField	testo_cognome		= new JTextField();
		JTextField	testo_email			= new JTextField();
		JTextField	testo_password		= new JTextField();
		JTextField	testo_data_nascita	= new JTextField();
		// BOTTONI
		JButton	modifica	= new JButton("MODIFICA");
		JButton	ok			= new JButton("OK");
		// LIST BOX
		String		professioni[]		= { "Studente", "Professore", "Professore Universitario", "Storico", "Altro" };
		JComboBox	listaProfessioni	= new JComboBox(professioni);
		JScrollPane	sp					= new JScrollPane(listaProfessioni);
		String		professioneSelezionata;

		String		titoloStudio[]	= { "Scuole Medie", "Scuole Superiori", "Laurea" };
		JComboBox	listaTitoli		= new JComboBox(titoloStudio);
		JScrollPane	sp2				= new JScrollPane(listaTitoli);
		String		titoloScelto;

		// COSTRUTTORE
		public pannelloModificaDatiUtente(String nickname, String nome, String cognome, String data_nascita, String email, String password, String titolo_studio, String professione)
		{
			settaggioLabel(vect, Color.WHITE);
			// POSIZIONAMENTO OGGETTI
			setLayout(grid);
			ca.weightx = 1;
			ca.weighty = 1;
			ca.fill = GridBagConstraints.HORIZONTAL;								// CREARE IL TITOLO DELLA SEZIONE//


			ca.ipady = 20;
			addC(Nickname, 0, 0);
			addC(Nome, 0, 1);
			addC(Cognome, 0, 2);
			addC(Email, 0, 3);
			addC(Password, 0, 4);
			addC(Professione, 0, 5);
			addC(Titolo_Studio, 0, 6);
			addC(Data_Nascita, 0, 7);

			ca.ipadx = 150;
			addC(testo_nickname, 1, 0);
			addC(testo_nome, 1, 1);
			addC(testo_cognome, 1, 2);
			addC(testo_email, 1, 3);
			addC(testo_password, 1, 4);
			addC(sp, 1, 5);
			addC(sp2, 1, 6);
			addC(testo_data_nascita, 1, 7);

			ca.fill = GridBagConstraints.NONE;
			ca.weightx = 0;
			ca.weighty = 0;
			ca.ipadx = 100;
			ca.ipady = 30;
			ca.insets.left = 30;
			ca.insets.right = 30;
			addC(modifica, 0, 8);
			addC(ok, 1, 8);

			testo_nickname.setText(nickname);
			testo_nome.setText(nome);
			testo_cognome.setText(cognome);
			testo_email.setText(email);
			for (int i = 0; i < professioni.length; i++)
			{
				if (professione == professioni[i])
				{
					listaProfessioni.setSelectedIndex(i);
				}
			}
			for (int i = 0; i < titoloStudio.length; i++)
			{
				if (professione == titoloStudio[i])
				{
					listaProfessioni.setSelectedIndex(i);
				}
			}
			testo_password.setText(password);
			testo_data_nascita.setText(data_nascita);

			testo_nickname.setEditable(false);
			testo_nome.setEditable(false);
			testo_cognome.setEditable(false);
			testo_email.setEditable(false);
			testo_password.setEditable(false);
			listaProfessioni.setEnabled(false);
			listaTitoli.setEnabled(false);
			testo_data_nascita.setEditable(false);

			inizializzaActionListner();

		}

		// METODI
		public void inizializzaActionListner()
		{
			modifica.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_profiloUtenteVIEW().modificaUtente(testo_nome, testo_cognome, listaProfessioni, listaTitoli, testo_data_nascita);
				}
			});

			ok.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_profiloUtenteVIEW().confermaModifiche(testo_nickname, testo_nome, testo_cognome, listaProfessioni, listaTitoli, testo_data_nascita);
				}
			});

		}

		private void settaggioLabel(JComponent vettore[], Color colore)
		{
			for (int i = 0; i < vettore.length; i++)
			{
				vettore[i].setFont(new Font("TimesRoman", Font.BOLD, 18));
			}
		}

		public void addC(Component componente, int x, int y)
		{
			ca.gridx = x;
			ca.gridy = y;

			add(componente, ca);
		}

		private String getNomeUtente()
		{
			String nome = testo_nickname.getText();
			return nome;
		}

	}

	public class pannelloCheUtenteSei extends JPanel
	{
		// VARIABILI
		GridBagLayout		grid	= new GridBagLayout();
		GridBagConstraints	ca		= new GridBagConstraints();
		// ETICHETTE
		JLabel	verde_rosso_utente_vip		= new JLabel();
		JLabel	verde_rosso_uploader		= new JLabel();
		JLabel	verde_rosso_trascrittore	= new JLabel();
		JLabel	verde_rosso_manager			= new JLabel();
		JLabel	etichetteVerdeRosso[]		= { verde_rosso_utente_vip, verde_rosso_uploader, verde_rosso_trascrittore, verde_rosso_manager };

		JLabel	Utente_Vip		= new JLabel("PREMIUM", SwingConstants.CENTER);
		JLabel	Uploader		= new JLabel("UPLOADER", SwingConstants.CENTER);
		JLabel	Trascrittore	= new JLabel("TRASCRITTORE", SwingConstants.CENTER);
		JLabel	Manager			= new JLabel("MANAGER", SwingConstants.CENTER);
		JLabel	etichette[]		= { Utente_Vip, Uploader, Trascrittore, Manager, Foto };
		JLabel	foto[]			= { Foto };
		// BOTTONI
		JButton	modifica_foto_profilo	= new JButton("CAMBIA IMMAGINE PROFILO");
		JButton	bottoni[]				= { modifica_foto_profilo };

		// COSTRUTTORE
		public pannelloCheUtenteSei(boolean vip, boolean manager, boolean uploader, boolean trascrittore)
		{
			// SETTAGGIO BOOLEANI E COLORI
			settaggioVerdeRosso(utente_vip, verde_rosso_utente_vip);
			settaggioVerdeRosso(utente_trascrittore, verde_rosso_trascrittore);
			settaggioVerdeRosso(utente_manager, verde_rosso_manager);
			settaggioVerdeRosso(utente_uploader, verde_rosso_uploader);

			// SETTAGGIO BORDI E COLORE
			settaggio_bordi(etichetteVerdeRosso);

			// POSIZIONAMENTO
			setLayout(grid);
			ca.weightx = 1;
			ca.weighty = 1;

			set(ca, 0, 0, 1, 1);
			ca.ipady = 30;
			ca.anchor = GridBagConstraints.EAST;
			addC(Utente_Vip);

			set(ca, 0, 1, 1, 1);
			addC(Uploader);

			set(ca, 0, 2, 1, 1);
			addC(Trascrittore);

			set(ca, 0, 3, 1, 1);
			addC(Manager);

			set(ca, 1, 0, 1, 1);
			ca.ipadx = 30;
			ca.ipady = 30;
			ca.insets.left = 16;
			ca.anchor = GridBagConstraints.WEST;
			addC(verde_rosso_utente_vip);

			set(ca, 1, 1, 1, 1);
			addC(verde_rosso_uploader);

			set(ca, 1, 2, 1, 1);
			addC(verde_rosso_trascrittore);

			set(ca, 1, 3, 1, 1);
			addC(verde_rosso_manager);

			ca.fill = GridBagConstraints.HORIZONTAL;
			ca.ipadx = 30;
			ca.ipady = 30;
			ca.insets.right = 16;
			set(ca, 2, 4, 1, 1);
			addC(modifica_foto_profilo);

			ca.anchor = GridBagConstraints.CENTER;
			ca.fill = GridBagConstraints.NONE;
			set(ca, 2, 0, 1, 4);
			addC(Foto);


			inizializzaActionListner();

		}

		// METODI
		public void inizializzaActionListner()
		{
			modifica_foto_profilo.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_profiloUtenteVIEW().modificaImmagine(soggetto, nik);
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

		private void settaggio_bordi(JComponent vettore[])
		{
			Border bordo = BorderFactory.createLineBorder(Color.BLACK, 2);
			for (int i = 0; i < vettore.length; i++)
			{
				vettore[i].setBorder(bordo);
			}
		}

		private void settaggio_colore(JComponent vettore[], Color colore)
		{
			for (int i = 0; i < vettore.length; i++)
			{
				vettore[i].setBackground(colore);
				vettore[i].setOpaque(true);
			}
		}

		private void settaggioVerdeRosso(boolean dato, JLabel etichetta)
		{
			if (dato == true)
			{
				etichetta.setBackground(Color.GREEN);
				etichetta.setOpaque(true);
			}
			else
			{
				etichetta.setBackground(Color.RED);
				etichetta.setOpaque(true);
			}
		}



	}

	public class pannelloDiventaTrascrittore extends JPanel
	{
		// VARIABILI
		GridBagLayout		grid	= new GridBagLayout();
		GridBagConstraints	ca		= new GridBagConstraints();
		// ETICHETTE
		JLabel	titolo_sezione_diventa		= new JLabel("DIVENTA TRASCRITTORE", SwingConstants.CENTER);
		JLabel	descrizione_sezione_diventa	= new JLabel("<html>In questa sezione potrai richiedere di diventare un utente trascrittore</html>", SwingConstants.CENTER);
		JLabel	descrizione_ricerca			= new JLabel("<html>Ricerca un opera da visualizzare</html>", SwingConstants.CENTER);
		JLabel	etichette[]					= { titolo_sezione_diventa, descrizione_sezione_diventa };
		// BOTTONI
		JButton	richiedi_trascrittore	= new JButton("DIVENTA TRASCRITTORE");
		JButton	bottoni[]				= { richiedi_trascrittore };
		JButton	ricercaOpera			= new JButton("RICERCA OPERA");

		// COSTRUTTORE
		public pannelloDiventaTrascrittore()
		{
			setLayout(grid);
			ca.weightx = 1;
			ca.weighty = 1;


			ca.fill = GridBagConstraints.HORIZONTAL;
			ca.anchor = GridBagConstraints.NORTH;
			ca.ipady = 40;
			ca.ipadx = 150;

			set(ca, 0, 0, 3, 1);
			addC(titolo_sezione_diventa);

			ca.insets.left = 16;
			set(ca, 0, 1, 1, 1);
			addC(richiedi_trascrittore);

			set(ca, 0, 2, 1, 1);
			addC(ricercaOpera);

			ca.fill = GridBagConstraints.HORIZONTAL;
			ca.insets.right = 16;

			set(ca, 2, 1, 1, 2);
			addC(descrizione_sezione_diventa);

			set(ca, 2, 2, 1, 2);
			addC(descrizione_ricerca);

			inizializzaActionListner();
		}

		// METODI
		public void inizializzaActionListner()
		{
			richiedi_trascrittore.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_profiloUtenteVIEW().diventaTrascrittore(nik, utente_trascrittore, utente_manager, utente_uploader, soggetto, nome, cognome, data_nascita, email, password, titolo_studio, professione, utente_vip);
				}
			});

			ricercaOpera.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					int id = new _FINITO_sezioneManagerVIEW().getIdTrascrittore(nik);
					new _FINITO_sezioneAmministratoreVIEW().premutoRicercaOpera(id);
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

		private void settaggio_bordi(JComponent vettore[])
		{
			Border bordo = BorderFactory.createLineBorder(Color.BLACK, 2);
			for (int i = 0; i < vettore.length; i++)
			{
				vettore[i].setBorder(bordo);
			}
		}

		private void settaggio_colore(JComponent vettore[], Color colore)
		{
			for (int i = 0; i < vettore.length; i++)
			{
				vettore[i].setBackground(colore);
				vettore[i].setOpaque(true);
			}
		}
	}

	public class pannelloWorkbench extends JPanel
	{
		// VARIABILI
		GridBagLayout		grid	= new GridBagLayout();
		GridBagConstraints	ca		= new GridBagConstraints();
		// ETICHETTE
		JLabel	titolo_sezione_apri				= new JLabel("APRI LA TUA SEZIONE WORKBENCH", SwingConstants.CENTER);
		JLabel	descrizione_sezione_workbench	= new JLabel("<html>in questa sezione puoi aprire se sei il tipo di utente giusto la tua area di lavoro</html>", SwingConstants.CENTER);
		JLabel	etichette[]						= { titolo_sezione_apri, descrizione_sezione_workbench };
		// BOTTONI
		JButton	apri_sezione_uploader		= new JButton("APRI SEZIONE UPLOADER");
		JButton	apri_sezione_trascrittore	= new JButton("APRI SEZIONE TRASCRITTORE");
		JButton	apri_sezione_manager		= new JButton("APRI SEZIONE MANAGER");
		JButton	bottoni[]					= { apri_sezione_uploader, apri_sezione_trascrittore, apri_sezione_manager };


		// COSTRUTTORE
		public pannelloWorkbench()
		{
			setLayout(grid);
			ca.weightx = 1;
			ca.weighty = 1;

			ca.fill = GridBagConstraints.HORIZONTAL;
			ca.anchor = GridBagConstraints.NORTH;
			ca.ipady = 40;
			set(ca, 0, 0, 3, 1);
			addC(titolo_sezione_apri);

			ca.anchor = GridBagConstraints.CENTER;
			ca.insets.left = 16;
			ca.insets.right = 16;
			set(ca, 0, 1, 1, 1);
			addC(apri_sezione_uploader);

			set(ca, 0, 2, 1, 1);
			addC(apri_sezione_trascrittore);

			set(ca, 0, 3, 1, 1);
			addC(apri_sezione_manager);

			set(ca, 2, 1, 1, 3);
			addC(descrizione_sezione_workbench);

			inizializzaActionListner();
		}

		// METODI
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

		private void inizializzaActionListner()
		{
			apri_sezione_uploader.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_profiloUtenteVIEW().apriUploader(nik);
				}
			});

			apri_sezione_trascrittore.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_profiloUtenteVIEW().apriTrascrittore(nik);
				}
			});

			apri_sezione_manager.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_profiloUtenteVIEW().apriManager(nik);
				}
			});
		}
	}

	// -----------------------------------------------------------------------------------------------------//
	//
	//
	//
	//
	//
	// ------------MAIN-------------------------------//
	public static void main(String[] args)
	{
		_FINITO_UtenteProfiloGUI bubba = new _FINITO_UtenteProfiloGUI(null, null, null, null, null, null, null, null, false, false, false, false);

	}
}
