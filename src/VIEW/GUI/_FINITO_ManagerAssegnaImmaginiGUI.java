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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import MODEL.A_MANOSCRITTO_1_Pagina;
import MODEL.Utente;
import VIEW._FINITO_sezioneManagerVIEW;

public class _FINITO_ManagerAssegnaImmaginiGUI extends JFrame
{
	// ---------------VARIBILI----------------//

	private GridBagLayout		grid	= new GridBagLayout();
	private GridBagConstraints	ca		= new GridBagConstraints();

	private pannelloAssegnaImmagini		pannelloAssegnaImmagini		= new pannelloAssegnaImmagini();
	private pannelloElencoTrascrittori	pannelloElencoTrascrittore	= new pannelloElencoTrascrittori();

	private ArrayList<Utente> listaTrascrittori;

	// ---------------COSTRUTTORE-------------//

	public _FINITO_ManagerAssegnaImmaginiGUI()
	{
		inizializzaFrame();
		inizializzaInterfaccia();
	}

	// ---------------METODI-------------------//

	public void inizializzaFrame()
	{
		this.setTitle("SEZIONE ASSEGNA IMMAGINI");
		this.setSize(1000, 800);
		this.setLocation(800, 0);
		this.setMinimumSize(new Dimension(1000, 800));
		this.setResizable(true);
		this.setLayout(grid);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void inizializzaInterfaccia()
	{
		ca.fill = GridBagConstraints.BOTH;
		ca.weightx = 1;
		ca.weighty = 1;


		set(ca, 0, 0, 1, 1);
		addC(pannelloAssegnaImmagini);

		ca.weightx = 0;
		ca.weighty = 0;
		ca.ipadx = 50;

		set(ca, 1, 0, 1, 1);
		addC(pannelloElencoTrascrittore);

		setVisible(true);
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

	// --------------CLASSI COMPONENTI--------//

	private class pannelloAssegnaImmagini extends JPanel
	{
		// VARIBILI
		String[] titoliManoscritti;


		private GridLayout grigliaPerTabs = new GridLayout(1, 1);

		JTabbedPane tabs = new JTabbedPane();

		// COSTRUTTORE
		public pannelloAssegnaImmagini()
		{
			setLayout(grigliaPerTabs);

			titoliManoscritti = new _FINITO_sezioneManagerVIEW().getTitoliManoscritti();

			for (int i = 0; i < titoliManoscritti.length; i++)
			{

				tabsOpera tabOpera = new tabsOpera(new _FINITO_sezioneManagerVIEW().getListaPagineACCETTATE(titoliManoscritti[i]));
				JScrollPane scrollBar = new JScrollPane(tabOpera);

				tabs.addTab(titoliManoscritti[i], new ImageIcon("./res/icone/book24x24.png"), scrollBar);
			}
			add(tabs);
		}

		// CLASSI COMPONENTI
		private class tabsOpera extends JPanel
		{
			// VARIABILI
			private ArrayList<A_MANOSCRITTO_1_Pagina>	listaPagineACCETTATE_locale;
			String[]									nicknameTrascrittori;

			private BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);

			// COSTRUTTORE
			public tabsOpera(ArrayList<A_MANOSCRITTO_1_Pagina> listaPagineACCETTATE)
			{
				// acquisizioni variabili
				ArrayList<Utente> listaTrascrittori = new _FINITO_sezioneManagerVIEW().getListaTrascrittori();
				nicknameTrascrittori = new String[listaTrascrittori.size()];
				for (int i = 0; i < listaTrascrittori.size(); i++)
				{
					nicknameTrascrittori[i] = listaTrascrittori.get(i).getNickname();
				}

				this.listaPagineACCETTATE_locale = listaPagineACCETTATE;
				this.setLayout(box);

				for (int i = 0; i < listaPagineACCETTATE_locale.size(); i++)
				{
					add(new elementoAssegna(listaPagineACCETTATE_locale.get(i), nicknameTrascrittori));
				}

				if (listaPagineACCETTATE_locale.size() == 0)
				{
					JLabel noImmaginiAccettate = new JLabel("QUESTO MANOSCRITTO NON HA IMMAGINI ACCETTATE, PERCIO' NON É POSSIBILE ASSEGNARLE AD ALCUN TRASCRITTORE!", SwingConstants.CENTER);
					add(noImmaginiAccettate);
					setBackground(Color.RED);
				}
			}

		}
	}

	private class elementoAssegna extends JPanel
	{
		// VARIABILI
		String	trascrittoreSelezionato	= null;
		String	pathImmagine;
		int		idManoscritto;
		int		idImmagine;

		JComboBox	listaSelezionabileTrascrittori;
		JLabel		labelTrascrittori		= new JLabel("<html>seleziona il trascrittore<br>a cui vuoi assegnare<br>l'immagine</html>", SwingConstants.CENTER);
		JButton		bottoneAssegna			= new JButton("ASSEGNA");
		JButton		bottoneInfoAssegnazioni	= new JButton("INFO ASSEGNAZIONI");

		private GridBagLayout		grid	= new GridBagLayout();
		private GridBagConstraints	ca		= new GridBagConstraints();

		private Border bordo = BorderFactory.createLineBorder(Color.BLACK, 2);

		// COSTRUTTORE
		public elementoAssegna(A_MANOSCRITTO_1_Pagina paginaLocale, String[] nicknameTrascrittori)
		{
			pathImmagine = paginaLocale.getPathImmagine();
			idManoscritto = paginaLocale.getIdManoscritto();
			idImmagine = paginaLocale.getIdImmagine();

			listaSelezionabileTrascrittori = new JComboBox(nicknameTrascrittori);

			this.setBackground(Color.WHITE);
			this.setLayout(grid);

			// --------------CREAZIONE DEI COMPONENTI------------------------//

			JButton assegna = new JButton("ASSEGNA");

			// --------------RIDIMENSIONAMENTO IMMAGINE----------------------//

			ImageIcon immagineIcona = new ImageIcon(pathImmagine);
			Image immagine = immagineIcona.getImage();
			Image nuovaImmagine = immagine.getScaledInstance(200, 300, java.awt.Image.SCALE_SMOOTH);
			immagineIcona = new ImageIcon(nuovaImmagine);

			JLabel immaginePaginaLabel = new JLabel(immagineIcona);
			immaginePaginaLabel.setOpaque(true);

			// --------------AGGIUNTA DEGLI ELEMENTI-------------------------//

			ca.weightx = 1;
			ca.weighty = 1;

			ca.insets.left = 10;
			ca.insets.right = 10;
			ca.insets.bottom = 10;
			ca.insets.top = 10;

			ca.ipady = 30;
			ca.ipadx = 50;

			ca.fill = GridBagConstraints.BOTH;

			immaginePaginaLabel.setBorder(bordo);
			set(ca, 0, 0, 1, 2);
			addC(immaginePaginaLabel);


			set(ca, 1, 0, 1, 1);
			add(this.labelTrascrittori);

			ca.fill = GridBagConstraints.NONE;

			set(ca, 2, 0, 1, 1);
			addC(listaSelezionabileTrascrittori);


			set(ca, 3, 0, 1, 1);
			addC(bottoneAssegna);

			ca.ipadx = 30;
			ca.anchor = GridBagConstraints.NORTH;

			set(ca, 1, 1, 3, 1);
			addC(bottoneInfoAssegnazioni);


			inizializzaActionListner();
		}

		// METODI
		public void inizializzaActionListner()
		{
			listaSelezionabileTrascrittori.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					trascrittoreSelezionato = (String) listaSelezionabileTrascrittori.getSelectedItem();
					System.out.println(trascrittoreSelezionato);
				}
			});

			bottoneAssegna.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					int idTrascrittore = new _FINITO_sezioneManagerVIEW().getIdTrascrittore(trascrittoreSelezionato);
					System.out.println(idTrascrittore);

					new _FINITO_sezioneManagerVIEW().premutoAssegnaImmagine(idImmagine, idTrascrittore);
				}
			});

			bottoneInfoAssegnazioni.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_sezioneManagerVIEW().premutoInfoImmagine(idImmagine);
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
		public pannelloElencoTrascrittori()
		{
			setLayout(grid);
			listaTrascrittori = new _FINITO_sezioneManagerVIEW().getListaTrascrittori();

			pannelloTitolo.add(titoloElenco);

			set(ca, 0, 0, 1, 1);
			ca.insets.top = 15;
			ca.ipady = 10;
			addC(pannelloTitolo);

			pannelloElenco.setLayout(box);
			for (int i = 0; i < listaTrascrittori.size(); i++)
			{
				pannelloElenco.add(new elementoTrascrittore(listaTrascrittori.get(i)));
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
			Border bordo = BorderFactory.createLineBorder(Color.BLACK, 1);

			JLabel	nomeTrascrittore;
			JLabel	expTrascrittore	= new JLabel("exp: ", SwingConstants.CENTER);

			GridBagLayout		grid	= new GridBagLayout();
			GridBagConstraints	ca		= new GridBagConstraints();

			// COSTRUTTORE
			public elementoTrascrittore(Utente trascrittore)
			{
				setLayout(grid);

				nomeTrascrittore = new JLabel(trascrittore.getNickname(), SwingConstants.CENTER);

				ca.weightx = 1;
				ca.weighty = 1;

				ca.ipady = 25;

				ca.insets.left = 0;
				ca.insets.right = 16;
				ca.insets.bottom = 16;
				ca.insets.top = 16;

				set(ca, 0, 0, 1, 1);
				add(nomeTrascrittore, ca);

				set(ca, 1, 0, 1, 1);
				add(expTrascrittore, ca);

				ca.ipadx = 60;
				ca.insets.right = 10;

			}

			// METODI
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
		_FINITO_ManagerAssegnaImmaginiGUI Assegnaimm = new _FINITO_ManagerAssegnaImmaginiGUI();

	}
}
