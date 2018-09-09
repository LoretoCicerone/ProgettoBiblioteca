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

public class _FINITO_TrascriberWorkbenchGUI extends JFrame
{
	// -----------VARIABILI--------------------------------//
	JButton											bottone_submit		= new JButton("SUBMIT");
	JLabel											etichetta_pagina	= new JLabel("N° PAGINA IN TRASCRIZIONE", SwingConstants.RIGHT);
	JTextField										testo_pagina		= new JTextField();
	private JTextArea								txtInput			= new JTextArea(10, 20);
	private String									nickname;
	private int										ID;
	private ArrayList<A_MANOSCRITTO_0_Manoscritto>	manoscrittiAssegnati;
	private JTabbedPane								menuTrascrittore	= new JTabbedPane();

	// LAYOUT
	private GridLayout grid = new GridLayout(1, 1);

	private GridBagLayout		griglia	= new GridBagLayout();
	private GridBagConstraints	ca		= new GridBagConstraints();

	// -----------COSTRUTTORE-------------------------------//

	public _FINITO_TrascriberWorkbenchGUI(String nick)
	{
		this.nickname = nick;
		this.ID = new _FINITO_sezioneTrascriberVIEW().getID(nickname);
		manoscrittiAssegnati = new _FINITO_sezioneTrascriberVIEW().getListaManoscrittiAssegnatiTrascrittore(ID);

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
		for (int i = 0; i < manoscrittiAssegnati.size(); i++)
		{
			pannelloTrascrittore bubba = new pannelloTrascrittore(new _FINITO_sezioneTrascriberVIEW().getListaPagineDelManoscrittoAssegnate(ID, manoscrittiAssegnati.get(i).getID()), i);

			menuTrascrittore.addTab(manoscrittiAssegnati.get(i).getTitolo(), null, bubba);
			add(menuTrascrittore);
		}

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

	private class pannelloTrascrittore extends JPanel
	{
		// ATTRIBUTI
		int			paneAttuale;
		JScrollPane	scrollBar;

		ArrayList<A_MANOSCRITTO_1_Pagina> listaPagineManoscrittoLocali;

		// COSTRUTTORE
		pannelloTrascrittore(ArrayList<A_MANOSCRITTO_1_Pagina> listaPagineManoscritto, int paneCorrente)
		{
			this.listaPagineManoscrittoLocali = listaPagineManoscritto;
			this.paneAttuale = paneCorrente;
			setLayout(griglia);
			ca.weightx = 1;
			ca.weighty = 1;

			ca.insets.bottom = 10;
			ca.insets.left = 10;
			ca.insets.right = 10;
			ca.insets.top = 10;

			// ---------------------PANNELLO IMMAGINI------------------------------//

			scrollBar = new JScrollPane(new pannelloScorriImmagini(listaPagineManoscrittoLocali, txtInput, paneAttuale, scrollBar));
			scrollBar.setMinimumSize(new Dimension(350, 0));
			scrollBar.setMaximumSize(new Dimension(350, 0));

			ca.weightx = 0;
			ca.ipadx = 400;
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
					new _FINITO_sezioneTrascriberVIEW().premutoSalva(txtInput.getText(), txtInput, menuTrascrittore, manoscrittiAssegnati.size());

				}
			});

			cancella.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_sezioneTrascriberVIEW().premutoCancella(txtInput, menuTrascrittore, manoscrittiAssegnati.size());
				}
			});

			aumentaCarattere.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					fontSize = new _FINITO_sezioneTrascriberVIEW().premutoAumenta(txtInput, fontSize);
				}
			});

			diminuisciCarattere.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					fontSize = new _FINITO_sezioneTrascriberVIEW().premutoDiminuisci(txtInput, fontSize);
				}
			});

			grassetto.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_sezioneTrascriberVIEW().premutoGrassetto(txtInput, fontSize);
				}
			});

			corsivo.addActionListener(new ActionListener()
			{
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

	private class pannelloScorriImmagini extends JPanel
	{
		// VARIBILI
		private JTabbedPane	tabs	= new JTabbedPane();
		private BoxLayout	box		= new BoxLayout(this, BoxLayout.Y_AXIS);

		private ArrayList<A_MANOSCRITTO_1_Pagina> listaPagineManoscritto;

		// COSTRUTTORE
		public pannelloScorriImmagini(ArrayList<A_MANOSCRITTO_1_Pagina> listaPagineManoscritto, JTextArea areaDiTesto, int paneCorrente, JScrollPane scrollBar)
		{
			setLayout(box);
			this.listaPagineManoscritto = listaPagineManoscritto;

			for (int i = 0; i < this.listaPagineManoscritto.size(); i++)
			{
				add(new elementoImmagine(this.listaPagineManoscritto.get(i), txtInput, paneCorrente, scrollBar));
			}
		}

		// CLASSI COMPONENTI
		private class elementoImmagine extends JPanel
		{
			// VARIABILI
			private GridBagLayout		grid	= new GridBagLayout();
			private GridBagConstraints	ca		= new GridBagConstraints();

			private Border bordo = BorderFactory.createLineBorder(Color.BLACK, 2);

			private String		pathImmagine;
			private int			idImmagine;
			private int			idManoscritto;
			private JButton		bottoneImmagine;
			private int			tabCorrente;
			private JScrollPane	scrollBarLocale;

			// COSTRUTTORE
			public elementoImmagine(A_MANOSCRITTO_1_Pagina paginaDaVisualizzare, JTextArea areaDiTesto, int paneCorrente, JScrollPane scrollBar)
			{
				setLayout(grid);
				this.idImmagine = paginaDaVisualizzare.getIdImmagine();
				this.idManoscritto = paginaDaVisualizzare.getIdManoscritto();
				this.pathImmagine = paginaDaVisualizzare.getPathImmagine();
				this.tabCorrente = paneCorrente;
				this.scrollBarLocale = scrollBar;

				// ----------------------SETTAGGIO IMMAGINE BOTTONE-------------------//

				ImageIcon immagineIcona = new ImageIcon(pathImmagine);
				Image immagine = immagineIcona.getImage();
				Image immagineRidimensionata = immagine.getScaledInstance(450, 600, java.awt.Image.SCALE_SMOOTH);
				immagineIcona = new ImageIcon(immagineRidimensionata);

				bottoneImmagine = new JButton(immagineIcona);

				ca.insets.top = 50;
				ca.insets.bottom = 50;
				ca.insets.left = 20;
				ca.insets.right = 20;


				set(ca, 0, 0, 1, 1);
				addC(bottoneImmagine);

				inizializzaActionListner();
			}

			// METODI
			public void inizializzaActionListner()
			{
				bottoneImmagine.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						new _FINITO_sezioneTrascriberVIEW().premutaImmagine(txtInput, tabCorrente, menuTrascrittore, manoscrittiAssegnati.size(), scrollBarLocale, idImmagine, idManoscritto);
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

	// ------------------------------------------------------//
	//
	//
	//
	//
	//
	// -----------MAIN--------------------------------------//

	public static void main(String[] args)
	{
		_FINITO_TrascriberWorkbenchGUI prova = new _FINITO_TrascriberWorkbenchGUI("zano");

	}


}
