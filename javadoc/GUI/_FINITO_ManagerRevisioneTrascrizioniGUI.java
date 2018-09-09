package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.A_MANOSCRITTO_1_Pagina;
import VIEW._FINITO_sezioneTrascriberVIEW;
import VIEW._FINITO_sezioneManagerVIEW;

public class _FINITO_ManagerRevisioneTrascrizioniGUI extends JFrame
{
	// -----------VARIABILI--------------------------------//
	int												IDtrascrizioneInModifica;
	JFrame											soggettoPrimario	= this;
	JTextField										testo_pagina		= new JTextField();
	private JTextArea								txtInput			= new JTextArea(10, 20);
	private String									nickname;
	private int										ID;
	private ArrayList<A_MANOSCRITTO_0_Manoscritto>	manoscrittiTutti;
	private JTabbedPane								menuTrascrittore	= new JTabbedPane();

	// LAYOUT
	private GridLayout grid = new GridLayout(1, 1);

	private GridBagLayout		griglia	= new GridBagLayout();
	private GridBagConstraints	ca		= new GridBagConstraints();

	// -----------COSTRUTTORE-------------------------------//

	public _FINITO_ManagerRevisioneTrascrizioniGUI(String nick)
	{
		this.nickname = nick;
		this.ID = new _FINITO_sezioneTrascriberVIEW().getID(nickname);

		manoscrittiTutti = new _FINITO_sezioneManagerVIEW().getListaManoscritti();

		inizializzaFrame();
		inizializzaMenu();

		this.setMinimumSize(new Dimension(1600, 1000));
		this.setVisible(true);
	}

	// -----------METODI-----------------------------------//

	private void inizializzaFrame()
	{
		this.setTitle("SEZIONE DI LAVORO TRASCRITTORE");
		this.setSize(1900, 1000);
		this.setResizable(true);
		this.setLayout(griglia);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void inizializzaMenu()
	{
		for (int i = 0; i < manoscrittiTutti.size(); i++)
		{
			ArrayList<A_MANOSCRITTO_1_Pagina> listaPagineManoscritto = new _FINITO_sezioneManagerVIEW().getListaPagineConTrascrizioniDaRevisionare(manoscrittiTutti.get(i).getID());
			pannelloRevisiona pannello = new pannelloRevisiona(listaPagineManoscritto, i);
			menuTrascrittore.addTab(manoscrittiTutti.get(i).getTitolo(), null, pannello);
			add(menuTrascrittore);
		}
		ca.weightx = 1;
		ca.weighty = 1;

		ca.ipadx = 10;

		set(ca, 0, 0, 1, 1);
		this.addC(menuTrascrittore);


		// ---------------------SEZIONE TEI------------------------------------//

		TEII tei = new TEII();
		ca.weightx = 1;
		ca.anchor = GridBagConstraints.WEST;
		ca.fill = GridBagConstraints.BOTH;
		ca.ipady = 600;
		ca.ipadx = 400;
		set(ca, 1, 1, 1, 1);
		this.addC(tei);

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


	// ----------CLASSI COMPONENTI--------------------------//

	private class pannelloRevisiona extends JPanel
	{
		// ATTRIBUTI
		int			paneAttuale;
		JScrollPane	scrollBar;

		ArrayList<A_MANOSCRITTO_1_Pagina> listaPagineManoscrittoLocali;

		// COSTRUTTORE
		pannelloRevisiona(ArrayList<A_MANOSCRITTO_1_Pagina> listaPagineManoscritto, int paneCorrente)
		{
			this.listaPagineManoscrittoLocali = listaPagineManoscritto;
			this.paneAttuale = paneCorrente;
			setLayout(griglia);
			ca.weightx = 1;
			ca.weighty = 1;

			ca.fill = GridBagConstraints.BOTH;

			ca.insets.bottom = 10;
			ca.insets.left = 10;
			ca.insets.right = 10;
			ca.insets.top = 10;

			// ---------------------PANNELLO IMMAGINI------------------------------//

			scrollBar = new JScrollPane(new pannelloScorriTrascrizioni(listaPagineManoscrittoLocali, txtInput, paneAttuale, scrollBar));
			scrollBar.setMinimumSize(new Dimension(800, 0));
			scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			ca.weightx = 0;
			ca.ipadx = 450;
			ca.fill = GridBagConstraints.VERTICAL;

			set(ca, 0, 0, 1, 3);
			addC(scrollBar);

		}

		// METODI
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

	}

	private class TEII extends JLabel
	{
		// ---------------VARIABILI------------------------------------//

		// LAYOUT
		private GridBagLayout		griglia	= new GridBagLayout();
		private GridBagConstraints	ca		= new GridBagConstraints();

		// MENU
		private JToolBar menu2 = new JToolBar();

		// BOTTONI PER FUNZIONALITA
		private JButton	salva				= new JButton(new ImageIcon("./res/icone/save.png"));
		private JButton	cancella			= new JButton(new ImageIcon("./res/icone/cancella 50x50.png"));
		private JButton	aumentaCarattere	= new JButton(new ImageIcon("./res/icone/aumenta testo.png"));
		private JButton	diminuisciCarattere	= new JButton(new ImageIcon("./res/icone/diminuisci testo.png"));
		private JButton	grassetto			= new JButton(new ImageIcon("./res/icone/grassetto.png"));
		private JButton	corsivo				= new JButton(new ImageIcon("./res/icone/italic 50x50.png"));

		// SEZIONE TESTO
		private int			fontSize	= 24;
		Font				f			= new Font("sansserif", 0, fontSize);
		private JScrollPane	pane		= new JScrollPane(txtInput);


		// ---------------COSTRUTTORE----------------------------------//

		public TEII()
		{
			setBackground(Color.GREEN);
			inizializzaInterfaccia();
			settaggioMenu();
			settaggioAreaTesto();
			inizializzaActionListner();
		}

		// ---------------METODI---------------------------------------//

		private void inizializzaInterfaccia()
		{
			setLayout(griglia);
			setOpaque(true);
		}

		private void settaggioMenu()
		{
			ca.weightx = 0;
			ca.weighty = 0;

			menu2.setFloatable(false);

			menu2.add(salva);
			menu2.add(cancella);
			menu2.add(aumentaCarattere);
			menu2.add(diminuisciCarattere);
			menu2.add(grassetto);
			menu2.add(corsivo);

			ca.anchor = GridBagConstraints.NORTH;
			ca.fill = GridBagConstraints.HORIZONTAL;
			set(ca, 0, 1, 1, 1);
			addC(menu2);
		}

		private void settaggioAreaTesto()
		{
			txtInput.setLineWrap(true);
			txtInput.setEditable(false);
			txtInput.setFont(f);

			ca.weightx = 1;
			ca.weighty = 1;

			ca.anchor = GridBagConstraints.NORTH;
			ca.fill = GridBagConstraints.BOTH;
			set(ca, 0, 2, 1, 1);
			addC(pane);
		}

		private void inizializzaActionListner()
		{
			salva.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_sezioneManagerVIEW().premutoSalvaTEI(IDtrascrizioneInModifica, txtInput.getText(), txtInput, menuTrascrittore, manoscrittiTutti.size());

					soggettoPrimario.dispose();
					new _FINITO_ManagerRevisioneTrascrizioniGUI(nickname);
				}
			});

			cancella.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_sezioneManagerVIEW().premutoCancellaTEI(txtInput, menuTrascrittore, manoscrittiTutti.size());
				}
			});

			aumentaCarattere.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					fontSize = new _FINITO_sezioneTrascriberVIEW().premutoAumenta(txtInput, fontSize);
				}
			});

			diminuisciCarattere.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					fontSize = new _FINITO_sezioneTrascriberVIEW().premutoDiminuisci(txtInput, fontSize);
				}
			});

			grassetto.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_sezioneTrascriberVIEW().premutoGrassetto(txtInput, fontSize);
				}
			});

			corsivo.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_sezioneTrascriberVIEW().premutoCorsivo(txtInput, fontSize);
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

	}

	private class pannelloScorriTrascrizioni extends JPanel
	{
		// VARIBILI
		private JTabbedPane	tabs	= new JTabbedPane();
		private BoxLayout	box		= new BoxLayout(this, BoxLayout.Y_AXIS);
		private Border		bordo	= BorderFactory.createLineBorder(Color.BLACK, 2);

		private ArrayList<A_MANOSCRITTO_1_Pagina> listaPagineManoscritto;

		// COSTRUTTORE
		public pannelloScorriTrascrizioni(ArrayList<A_MANOSCRITTO_1_Pagina> listaPagineManoscritto, JTextArea areaDiTesto, int paneCorrente, JScrollPane scrollBar)
		{
			setLayout(box);
			this.listaPagineManoscritto = listaPagineManoscritto;

			for (int i = 0; i < this.listaPagineManoscritto.size(); i++)
			{
				elementoRevisiona elemento = new elementoRevisiona(listaPagineManoscritto.get(i), txtInput, paneCorrente, scrollBar);
				elemento.setBorder(bordo);
				add(elemento);
			}
		}

		// CLASSI COMPONENTI
		private class elementoRevisiona extends JPanel
		{
			// VARIABILI
			private GridBagLayout		grid	= new GridBagLayout();
			private GridBagConstraints	ca		= new GridBagConstraints();
			private Border				bordo	= BorderFactory.createLineBorder(Color.BLACK, 1);

			private String	pathImmagine;
			private String	statoTrascrizioneStringa;

			private int	idImmagine;
			private int	idTrascrizione;
			private int	idManoscritto;
			private int	tabCorrente;


			private JTextArea	textAreaTestoTrascrizione	= new JTextArea(20, 10);
			private JLabel		labelImmagine;
			private JLabel		statoTrascrizioneLabel		= new JLabel("stato", SwingConstants.CENTER);
			private JButton		accetta						= new JButton("ACCETTA");
			private JButton		rifiuta						= new JButton("RIFIUTA");
			private JButton		revisona					= new JButton("REVISIONA");
			private JButton		riassegna					= new JButton("RIASSEGNA");
			private JScrollPane	scrollBarLocale;

			// COSTRUTTORE
			public elementoRevisiona(A_MANOSCRITTO_1_Pagina paginaDaVisualizzare, JTextArea areaDiTesto, int paneCorrente, JScrollPane scrollBar)
			{
				setLayout(grid);

				idImmagine = paginaDaVisualizzare.getIdImmagine();
				idManoscritto = paginaDaVisualizzare.getIdManoscritto();
				pathImmagine = paginaDaVisualizzare.getPathImmagine();
				tabCorrente = paneCorrente;
				scrollBarLocale = scrollBar;

				statoTrascrizioneStringa = paginaDaVisualizzare.getStatoTrascrizione();
				idTrascrizione = paginaDaVisualizzare.getIDTrascrizione();

				// ---------------------------SETTAGGIO COLORE STATO---------------------------//

				if (statoTrascrizioneStringa.equals("accettata"))
				{
					statoTrascrizioneLabel.setBackground(Color.GREEN);
					statoTrascrizioneLabel.setText("ACCETTATA");
				}
				else if (statoTrascrizioneStringa.equals("declinata"))
				{
					statoTrascrizioneLabel.setBackground(Color.RED);
					statoTrascrizioneLabel.setText("RIFIUTATA");
				}
				else
				{
					statoTrascrizioneLabel.setBackground(Color.YELLOW);
					statoTrascrizioneLabel.setText("IN ATTESA");
				}


				// --------------------------SETTAGGIO BOTTONI------------------------------------//

				if (paginaDaVisualizzare.getStatoTrascrizione().equals("accettata"))
				{
					accetta.setEnabled(false);
					rifiuta.setEnabled(false);
					revisona.setEnabled(false);
					riassegna.setEnabled(false);
				}
				else if (paginaDaVisualizzare.getStatoTrascrizione().equals("declinata"))
				{
					rifiuta.setEnabled(false);

					accetta.setBackground(Color.GREEN);
					accetta.setOpaque(true);
				}
				else
				{
					rifiuta.setBackground(Color.RED);
					rifiuta.setOpaque(true);

					accetta.setBackground(Color.GREEN);
					accetta.setOpaque(true);
				}





				// ----------------------SETTAGGIO IMMAGINE BOTTONE-------------------//

				ImageIcon immagineIcona = new ImageIcon(pathImmagine);
				Image immagine = immagineIcona.getImage();
				Image immagineRidimensionata = immagine.getScaledInstance(450, 600, java.awt.Image.SCALE_SMOOTH);
				immagineIcona = new ImageIcon(immagineRidimensionata);
				labelImmagine = new JLabel(immagineIcona);

				// -------------------------------------------------------------------//
				ca.weightx = 1;
				ca.weighty = 1;

				ca.insets.top = 50;
				ca.insets.bottom = 50;
				ca.insets.left = 20;
				ca.insets.right = 20;

				set(ca, 0, 0, 1, 1);
				addC(labelImmagine);

				set(ca, 0, 1, 1, 1);
				ca.ipadx = 50;
				ca.ipady = 10;
				statoTrascrizioneLabel.setBorder(bordo);
				statoTrascrizioneLabel.setOpaque(true);
				addC(statoTrascrizioneLabel);

				set(ca, 1, 0, 4, 1);
				ca.anchor = GridBagConstraints.NORTH;
				String testo = (String) paginaDaVisualizzare.getTrascrizione();
				textAreaTestoTrascrizione.setEditable(false);
				textAreaTestoTrascrizione.setText(testo);
				ca.ipadx = 50;
				ca.ipady = 20;
				addC(textAreaTestoTrascrizione);

				set(ca, 1, 1, 1, 1);
				addC(revisona);

				set(ca, 2, 1, 1, 1);
				addC(riassegna);

				set(ca, 3, 1, 1, 1);
				addC(accetta);

				set(ca, 4, 1, 1, 1);
				addC(rifiuta);


				inizializzaActionListner();
			}

			// METODI

			public void inizializzaActionListner()
			{
				revisona.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						IDtrascrizioneInModifica = idTrascrizione;

						new _FINITO_sezioneTrascriberVIEW().premutaImmagine(txtInput, tabCorrente, menuTrascrittore, manoscrittiTutti.size(), scrollBarLocale, idImmagine, idManoscritto);
						new _FINITO_sezioneManagerVIEW().premutoRevisionaTrascrizione(textAreaTestoTrascrizione, txtInput, idTrascrizione);
					}
				});

				accetta.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						boolean esito = new _FINITO_sezioneManagerVIEW().premutoAccettaTrascrizione(idTrascrizione, ID);
						if (esito)
						{
							soggettoPrimario.dispose();
							new _FINITO_ManagerRevisioneTrascrizioniGUI(nickname);
						}
					}
				});

				rifiuta.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						boolean esito = new _FINITO_sezioneManagerVIEW().premutoRifiutaTrascrizione(idTrascrizione, ID);
						if (esito)
						{
							soggettoPrimario.dispose();
							new _FINITO_ManagerRevisioneTrascrizioniGUI(nickname);
						}
					}
				});

				riassegna.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						new _FINITO_sezioneManagerVIEW().premutoRiassegnaTrascrizione(idImmagine);
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
			}

		}

	}
	// --------------------------------------------------------//
	//
	//
	//
	//
	//
	// --------------MAIN-------------------------------------//

	public static void main(String[] args)
	{
		_FINITO_ManagerRevisioneTrascrizioniGUI revTrascr = new _FINITO_ManagerRevisioneTrascrizioniGUI("lillo");

	}

}
