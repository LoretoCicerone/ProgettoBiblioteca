package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
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

import VIEW._FINITO_sezioneAmministratoreVIEW;

public class _FINITO_AmministratoreProfiloGUI extends JFrame
{
	// ---------VARIABILI----------------------------------------------------------------------------//
	private String	nik;
	private int		id;

	private GridLayout			griglia	= new GridLayout(2, 2);
	private GridBagLayout		grid	= new GridBagLayout();
	private GridBagConstraints	ca		= new GridBagConstraints();

	private pannelloModificaDatiUtente	pannUtente	= null;
	private pannelloCheUtenteSei		pannInfo	= new pannelloCheUtenteSei();
	private pannelloWorkbench			pannWork	= new pannelloWorkbench();

	// -----------COSTRUTTORE-------------------------------------------------------------------------------//

	public _FINITO_AmministratoreProfiloGUI(String nickname, String nome, String cognome, String data_nascita, String email, String password, String titolo_studio, String professione, int ID)
	{
		// settaggio nickname utile per compiere azioni riferite al nickname con cui si è loggati
		this.nik = nickname;
		this.id = ID;

		inizializza_frame();
		inizializza_interfaccia(nickname, nome, cognome, data_nascita, email, password, titolo_studio, professione);

		this.setVisible(true);
	}

	// -----------METODI -----------------------------------------------------------------------------------//

	private void inizializza_frame()
	{
		this.setTitle("BENVENUTO AMMINISTRATORE");
		this.setSize(1200, 650);
		this.setMinimumSize(new Dimension(1200, 650));
		this.setResizable(true);
		this.setLayout(grid);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void inizializza_interfaccia(String nickname, String nome, String cognome, String data_nascita, String email, String password, String titolo_studio, String professione)
	{
		pannUtente = new pannelloModificaDatiUtente(nickname, nome, cognome, data_nascita, email, password, titolo_studio, professione);

		ca.weightx = 1;
		ca.weighty = 0;


		set(ca, 0, 0, 1, 1);
		ca.fill = GridBagConstraints.BOTH;
		addC(pannUtente);




		set(ca, 1, 0, 1, 1);
		ca.fill = GridBagConstraints.BOTH;
		addC(pannInfo);




		set(ca, 0, 1, 2, 1);
		ca.fill = GridBagConstraints.HORIZONTAL;
		ca.insets.right = 15;
		ca.insets.left = 15;
		ca.insets.bottom = 15;
		ca.insets.top = 15;
		addC(pannWork);
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

	// ----------CLASSI COMPONENTI--------------------------------------------------------------------------//

	public class pannelloModificaDatiUtente extends JPanel
	{
		// VARIABILI
		GridBagLayout		grid	= new GridBagLayout();
		GridBagConstraints	ca		= new GridBagConstraints();
		// ETICHETTE
		JLabel	titoloSezione	= new JLabel("SEZIONE MODIFICA DATI DEL PROFILO", SwingConstants.CENTER);
		JLabel	Nickname		= new JLabel("NICKNAME", SwingConstants.CENTER);
		JLabel	Nome			= new JLabel("NOME", SwingConstants.CENTER);
		JLabel	Cognome			= new JLabel("COGNOME", SwingConstants.CENTER);
		JLabel	Email			= new JLabel("E-MAIL", SwingConstants.CENTER);
		JLabel	Password		= new JLabel("PASSWORD", SwingConstants.CENTER);
		JLabel	Professione		= new JLabel("PROFESSIONE", SwingConstants.CENTER);
		JLabel	Titolo_Studio	= new JLabel("TITOLO DI STUDIO", SwingConstants.CENTER);
		JLabel	Data_Nascita	= new JLabel("<html>DATA DI NASCITA<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;dd/MM/YYYY</html>", SwingConstants.CENTER);
		// SEZIONE TESTO
		JTextField	testo_nickname		= new JTextField();
		JTextField	testo_nome			= new JTextField();
		JTextField	testo_cognome		= new JTextField();
		JTextField	testo_email			= new JTextField();
		JTextField	testo_password		= new JTextField();
		JTextField	testo_data_nascita	= new JTextField();
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
			// POSIZIONAMENTO OGGETTI
			setLayout(grid);
			ca.weightx = 1;
			ca.weighty = 1;
			ca.fill = GridBagConstraints.HORIZONTAL;


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
		}

		// METODI
		public void addC(Component componente, int x, int y)
		{
			ca.gridx = x;
			ca.gridy = y;

			add(componente, ca);
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
		JLabel	verde_rosso_amministratore	= new JLabel();
		JLabel	etichetteVerdeRosso[]		= { verde_rosso_amministratore };

		JLabel	amministratore	= new JLabel("AMMINISTRATORE", SwingConstants.CENTER);
		JLabel	etichette[]		= { amministratore };

		// COSTRUTTORE
		public pannelloCheUtenteSei()
		{
			// --------------RIDIMENSIONAMENTO IMMAGINE----------------------//

			ImageIcon immagineIcona = new ImageIcon("./res/sfondi/thanos guanto.jpg");
			Image immagine = immagineIcona.getImage();
			Image nuovaImmagine = immagine.getScaledInstance(350, 350, java.awt.Image.SCALE_SMOOTH);
			immagineIcona = new ImageIcon(nuovaImmagine);

			JLabel Foto = new JLabel(immagineIcona);
			Foto.setOpaque(true);

			// --------------------------------------------------------------//

			// SETTAGGIO BORDI E COLORE
			settaggio_bordi(etichetteVerdeRosso);
			settaggio_colore(etichetteVerdeRosso, Color.WHITE);
			verde_rosso_amministratore.setBackground(Color.GREEN);
			verde_rosso_amministratore.setOpaque(true);

			// POSIZIONAMENTO
			setLayout(grid);
			ca.weightx = 1;
			ca.weighty = 1;

			set(ca, 0, 0, 2, 1);
			ca.fill = GridBagConstraints.BOTH;
			ca.insets.right = 15;
			ca.insets.top = 15;
			ca.insets.bottom = 15;
			ca.insets.top = 15;
			addC(Foto);

			ca.fill = GridBagConstraints.NONE;
			ca.weightx = 1;
			ca.weighty = 1;
			ca.ipadx = 30;
			ca.ipady = 30;

			set(ca, 0, 1, 1, 1);
			ca.anchor = GridBagConstraints.EAST;
			addC(amministratore);

			set(ca, 1, 1, 1, 1);
			ca.anchor = GridBagConstraints.WEST;
			addC(verde_rosso_amministratore);

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
		// BOTTONI
		JButton apri_sezione_AMMINISTRATORE = new JButton("APRI SEZIONE AMMINISTRATORE");


		// COSTRUTTORE
		public pannelloWorkbench()
		{
			setLayout(grid);
			ca.weightx = 1;
			ca.weighty = 1;

			set(ca, 0, 1, 1, 2);
			ca.fill = GridBagConstraints.NONE;
			ca.ipady = 40;
			ca.insets.top = 15;
			ca.insets.bottom = 15;
			addC(apri_sezione_AMMINISTRATORE);

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

		private void inizializzaActionListner()
		{
			apri_sezione_AMMINISTRATORE.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_sezioneAmministratoreVIEW().premutoApriWorkbnch(id);
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
		_FINITO_AmministratoreProfiloGUI AdminGUI = new _FINITO_AmministratoreProfiloGUI(null, null, null, null, null, null, null, null, 0);
	}
}
