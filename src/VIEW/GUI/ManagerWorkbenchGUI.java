package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import MODEL.Utente;
import VIEW._FINITO_sezioneManagerVIEW;
import VIEW._FINITO_sezioneTrascriberVIEW;

public class ManagerWorkbenchGUI extends JFrame
{
	// ---------------VARIBILI---------------------------------//
	private JFrame soggetto = this;

	private String	nickname;
	private int		ID;

	private GridBagLayout		grid	= new GridBagLayout();
	private GridBagConstraints	ca		= new GridBagConstraints();
	private ArrayList<Utente>	listaUtentiTrascriber;

	// ---------------COSTRUTTORE------------------------------//

	public ManagerWorkbenchGUI(String nik)
	{
		this.nickname = nik;
		this.ID = new _FINITO_sezioneTrascriberVIEW().getID(nickname);

		listaUtentiTrascriber = new _FINITO_sezioneManagerVIEW().getListaTrascrittoriExp();

		inizializzaFrame();
		inizializzaInterfaccia();

		this.setVisible(true);
	}

	// ---------------METODI-----------------------------------//
	private void inizializzaFrame()
	{
		this.setTitle("SEZIONE MANAGER");
		this.setSize(1000, 800);
		this.setLocation(0, 0);
		this.setMinimumSize(new Dimension(1000, 800));
		this.setResizable(true);
		this.setLayout(grid);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void inizializzaInterfaccia()
	{
		// ---------------PANNELLO ELENCO TRASCRITTORI------------------------------//
		pannelloElencoTrascrittori pannello = new pannelloElencoTrascrittori(listaUtentiTrascriber);

		ca.weightx = 1;
		ca.weighty = 1;

		ca.fill = GridBagConstraints.BOTH;
		ca.ipadx = 600;

		set(ca, 0, 0, 1, 1);
		this.add(pannello, ca);
		// ---------------PANNELLO BOTTONI WORKBENCH-------------------------------//
		pannelloBottoni bottoni = new pannelloBottoni();

		ca.weightx = 0;
		ca.weighty = 0;

		ca.ipadx = 0;

		set(ca, 1, 0, 1, 1);
		this.add(bottoni, ca);
		// --------------------------------------------------------------------------//
	}

	public void set(GridBagConstraints lim, int gridx, int gridy, int gridwidth, int gridheight)
	{
		lim.gridx = gridx;
		lim.gridy = gridy;
		lim.gridwidth = gridwidth;
	}

	// --------------CLASSI COMPONENTI-------------------------//

	private class pannelloElencoTrascrittori extends JPanel
	{
		// VARIABILI
		private JPanel		pannelloTitolo	= new JPanel();
		private JPanel		pannelloElenco	= new JPanel();
		private JLabel		titoloElenco	= new JLabel("ELENCO UTENTI TRASCRITTORI", SwingConstants.CENTER);
		private JScrollPane	scrollPane		= new JScrollPane(pannelloElenco, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		private BoxLayout			box		= new BoxLayout(pannelloElenco, BoxLayout.Y_AXIS);
		private GridBagLayout		grid	= new GridBagLayout();
		private GridBagConstraints	ca		= new GridBagConstraints();

		// COSTRUTTORE
		public pannelloElencoTrascrittori(ArrayList<Utente> listaUtenti)
		{
			setLayout(grid);

			pannelloTitolo.add(titoloElenco);

			set(ca, 0, 0, 1, 1);
			ca.insets.top = 15;
			ca.ipady = 10;
			addC(pannelloTitolo);

			pannelloElenco.setLayout(box);
			for (int i = 0; i < listaUtenti.size(); i++)
			{
				pannelloElenco.add(new elementoTrascrittore(listaUtenti.get(i)));
			}

			ca.weighty = 1;
			ca.weightx = 1;

			set(ca, 0, 1, 1, 1);
			ca.fill = GridBagConstraints.BOTH;
			addC(scrollPane);

			setVisible(true);
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

		// CLASSI COMPONENTI
		private class elementoTrascrittore extends JPanel
		{
			// VARIABILI
			Utente trascrittore;

			Border bordo = BorderFactory.createLineBorder(Color.BLACK, 1);

			private JLabel	nomeTrascrittore		= new JLabel();
			private JLabel	labelExp				= new JLabel("LIVELLO: ", SwingConstants.RIGHT);
			private JLabel	labelLVLtrascrittore	= new JLabel();
			private JButton	assegnaEsperienza		= new JButton("ASSEGNA ESPERIENZA");

			GridBagLayout		grid	= new GridBagLayout();
			GridBagConstraints	ca		= new GridBagConstraints();

			// COSTRUTTORE
			public elementoTrascrittore(Utente trascrittore)
			{
				setLayout(grid);

				this.trascrittore = trascrittore;
				nomeTrascrittore.setText(trascrittore.getNickname());

				if (trascrittore.getExp() == 0)
				{
					labelLVLtrascrittore.setText(Integer.toString(0));
				}
				else
				{
					labelLVLtrascrittore.setText(Integer.toString(trascrittore.getExp()));
				}

				ca.weightx = 0;
				ca.weighty = 0;

				ca.fill = GridBagConstraints.BOTH;
				ca.ipady = 25;

				ca.insets.left = 15;
				ca.insets.right = 15;
				ca.insets.bottom = 15;
				ca.insets.top = 15;

				set(ca, 0, 0, 1, 1);
				add(nomeTrascrittore, ca);

				set(ca, 1, 0, 1, 1);
				add(labelExp, ca);

				set(ca, 2, 0, 1, 1);
				add(labelLVLtrascrittore, ca);

				set(ca, 3, 0, 1, 1);
				add(assegnaEsperienza);

				inizializzaActionListner();

			}

			// METODI
			private void inizializzaActionListner()
			{
				assegnaEsperienza.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						new _FINITO_APPOGGIO_finestraAssegnaEsperienza(trascrittore, soggetto, nickname);
					}
				});
			}

			public void set(GridBagConstraints lim, int gridx, int gridy, int gridwidth, int gridheight)
			{
				lim.gridx = gridx;
				lim.gridy = gridy;
				lim.gridwidth = gridwidth;
			}

		}
	}

	private class pannelloBottoni extends JPanel
	{
		// ELEMENTI
		private JButton	ricercaOpera			= new JButton("RICERCA OPERA");
		private JButton	revisioneImmagini		= new JButton("REVISIONE IMMAGINI");
		private JButton	revisioneTrascrizioni	= new JButton("REVISIONE TRASCRIZIONI");
		private JButton	assegnaImmagini			= new JButton("ASSEGNA IMMAGINI");

		// LAYOUT
		GridBagLayout		griglia	= new GridBagLayout();
		GridBagConstraints	ca		= new GridBagConstraints();

		// COSTRUTTORE
		public pannelloBottoni()
		{
			setLayout(griglia);

			ca.weightx = 0;
			ca.weighty = 0;

			ca.fill = GridBagConstraints.HORIZONTAL;
			ca.ipady = 30;
			ca.ipadx = 150;
			ca.insets.bottom = 50;
			ca.insets.top = 50;
			ca.insets.left = 50;
			ca.insets.right = 50;

			set(ca, 0, 0, 1, 1);
			add(ricercaOpera, ca);


			set(ca, 0, 1, 1, 1);
			add(revisioneImmagini, ca);

			set(ca, 0, 2, 1, 1);
			add(revisioneTrascrizioni, ca);

			set(ca, 0, 3, 1, 1);
			add(assegnaImmagini, ca);

			inizializzaActionListner();
		}

		// METODI
		public void inizializzaActionListner()
		{
			ricercaOpera.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_sezioneManagerVIEW().premutoRicercaOpera(ID);
				}
			});

			revisioneImmagini.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_sezioneManagerVIEW().premutoRevisioneImmagini(ID);
				}
			});

			revisioneTrascrizioni.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_sezioneManagerVIEW().premutoRevisioneTrascrizioni(nickname);
				}
			});

			assegnaImmagini.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_sezioneManagerVIEW().premutoAssegnaImmagini();
				}
			});


		}

		public void set(GridBagConstraints lim, int gridx, int gridy, int gridwidth, int gridheight)
		{
			lim.gridx = gridx;
			lim.gridy = gridy;
			lim.gridwidth = gridwidth;
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
		ManagerWorkbenchGUI bubba = new ManagerWorkbenchGUI("hamptap");

	}

}
