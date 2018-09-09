package GUI;

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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import VIEW._FINITO_sezioneUploaderVIEW;


public class _FINITO_UploaderGUI extends JFrame
{
	// ------------VARIABILI------------------------------------------------//
	private String	nickname;
	private JFrame	frame	= this;

	// ETICHETTE
	private JLabel	titoloSezione				= new JLabel("SEZIONE DI UPLOAD MANOSCRITTO", SwingConstants.CENTER);
	private JLabel	titoloManoscritto			= new JLabel("TITOLO MANOSCRITTO", SwingConstants.CENTER);
	private JLabel	annoPubblicazione			= new JLabel("ANNO PUBBLICAZIONE", SwingConstants.CENTER);
	private JLabel	autoreOpera					= new JLabel("AUTORE OPERA", SwingConstants.CENTER);
	private JLabel	genereOpera					= new JLabel("GENERE OPERA", SwingConstants.CENTER);
	private JLabel	selezionaImmagini			= new JLabel("SELEZIONA IMMAGINI", SwingConstants.CENTER);
	private JLabel	selezionaImmagineCopertina	= new JLabel("SELEZIONA IMMAGINE DI COPERTINA", SwingConstants.CENTER);

	// TESTO
	private JTextField	testo_titoloManoscritto	= new JTextField();
	private JTextField	testo_annoPubblicazione	= new JTextField();
	private JTextField	testo_autoreOpera		= new JTextField();

	// BOTTONI
	private JButton	bottone_carica				= new JButton("CARICA IMMAGINI");
	private JButton	bottone_caricaCopertina		= new JButton("CARICA IMMAGINE COPERTINA");
	private JButton	bottone_anteprimaImmagini	= new JButton("ANTEPRIMA IMMAGINI");
	private JButton	bottone_anteprimaCopertina	= new JButton("ANTEPRIMA COPERTINA");
	private JButton	bottone_submit				= new JButton("SUBMIT");

	// JCOMBOBOX
	private String		vectGeneri[]	= { "Fantasy", "Medievale", "Guerra", "Storico", "Saggio", "Ricerche", "Religioso", "Artistico", "Tecnico", "Letteratura", "Poesia", "ALTRO" };
	private JComboBox	generi			= new JComboBox(vectGeneri);

	// LAYOUT
	private GridLayout			griglia	= new GridLayout(1, 1);
	private GridBagLayout		grid	= new GridBagLayout();
	private GridBagConstraints	ca		= new GridBagConstraints();

	// -----------COSTRUTTORE-----------------------------------------------//

	public _FINITO_UploaderGUI(String nik)
	{
		this.nickname = nik;

		inizializzaFrame();
		inizializzaInterfaccia();

		this.setMinimumSize(new Dimension(500, 500));

		inizializzaActionListner();

		new _FINITO_UploaderReuploadGUI(nickname);
	}

	// ----------METODI-----------------------------------------------------//

	public void inizializzaActionListner()
	{
		bottone_submit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				boolean esito = new _FINITO_sezioneUploaderVIEW().submit(testo_titoloManoscritto, testo_annoPubblicazione, testo_autoreOpera, generi, nickname);
				if (esito)
				{
					frame.dispose();
				}
			}
		});

		bottone_carica.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new _FINITO_sezioneUploaderVIEW().caricaImmagini();
			}
		});

		bottone_caricaCopertina.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new _FINITO_sezioneUploaderVIEW().caricaImmagineCopertina();
			}
		});

		bottone_anteprimaImmagini.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new _FINITO_sezioneUploaderVIEW().anteprimaImmagini();
			}
		});

		bottone_anteprimaCopertina.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new _FINITO_sezioneUploaderVIEW().anteprimaCopertina();
			}
		});

	}

	public void inizializzaFrame()
	{
		this.setTitle("BENVENUTO " + nickname + " NELLA SEZIONE SUPLOAD MANOSCRITTO");
		this.setSize(800, 800);
		this.setLocation(0, 0);
		this.setResizable(true);
		this.setLayout(griglia);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void inizializzaInterfaccia()
	{
		this.add(new pannelloUpload());
	}

	// -----------CLASSI COMPONENTI-----------------------------------------//

	private class pannelloUpload extends JPanel
	{
		// COSTRUTTORE
		public pannelloUpload()
		{
			setLayout(grid);
			ca.weightx = 1;
			ca.weighty = 1;

			ca.fill = GridBagConstraints.HORIZONTAL;
			ca.ipady = 40;
			set(ca, 0, 0, 2, 1);
			addC(titoloSezione);

			// ---------------------------------------//

			ca.fill = GridBagConstraints.NONE;
			ca.anchor = GridBagConstraints.CENTER;
			ca.ipady = 20;

			set(ca, 0, 2, 1, 1);
			addC(titoloManoscritto);

			set(ca, 0, 3, 1, 1);
			addC(autoreOpera);

			set(ca, 0, 4, 1, 1);
			addC(annoPubblicazione);

			set(ca, 0, 5, 1, 1);
			addC(genereOpera);

			set(ca, 0, 6, 1, 1);
			addC(selezionaImmagini);

			set(ca, 0, 7, 1, 1);
			addC(selezionaImmagineCopertina);

			// ----------------------------------------//

			ca.fill = GridBagConstraints.HORIZONTAL;
			ca.insets.left = 16;
			ca.insets.right = 16;
			ca.ipady = 20;

			set(ca, 1, 2, 1, 1);
			addC(testo_titoloManoscritto);

			set(ca, 1, 3, 1, 1);
			addC(testo_autoreOpera);

			set(ca, 1, 4, 1, 1);
			addC(testo_annoPubblicazione);

			set(ca, 1, 5, 1, 1);
			addC(generi);

			set(ca, 1, 6, 1, 1);
			addC(bottone_carica);

			set(ca, 1, 7, 1, 1);
			addC(bottone_caricaCopertina);

			set(ca, 0, 8, 1, 1);
			addC(bottone_anteprimaCopertina);

			set(ca, 1, 8, 1, 1);
			addC(bottone_anteprimaImmagini);

			ca.fill = GridBagConstraints.NONE;
			ca.ipadx = 300;

			set(ca, 0, 9, 2, 1);
			addC(bottone_submit);
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

	}

	// ----------------------------------------------------------------------//
	//
	//
	//
	//
	// ---------------MAIN-------------------------------------------------//

	public static void main(String[] args)
	{
		_FINITO_UploaderGUI reapu = new _FINITO_UploaderGUI("rainbow");
	}

}
