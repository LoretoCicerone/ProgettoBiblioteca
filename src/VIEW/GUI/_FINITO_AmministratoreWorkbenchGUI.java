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
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import MODEL.Utente;
import VIEW._FINITO_profiloUtenteVIEW;
import VIEW._FINITO_sezioneAmministratoreVIEW;

public class _FINITO_AmministratoreWorkbenchGUI extends JFrame
{
	// ---------------VARIBILI----------------//

	private GridBagLayout		grigliaFrame	= new GridBagLayout();
	private GridBagConstraints	ca				= new GridBagConstraints();
	private int					id;

	// ---------------COSTRUTTORE-------------//

	public _FINITO_AmministratoreWorkbenchGUI(int ID)
	{
		this.id = ID;
		inizializzaFrame();
		inizializzaInterfaccia();

		this.setVisible(true);
	}

	// ---------------METODI-------------------//

	private void inizializzaFrame()
	{
		this.setTitle("BENVENUTO AMMINISTRATORE DEL SISTEMA");
		this.setSize(1300, 800);
		this.setLocation(0, 0);
		this.setMinimumSize(new Dimension(1300, 500));
		this.setLayout(grigliaFrame);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void inizializzaInterfaccia()
	{
		// ----------------------------------------------------//

		JPanel pannelloTitolo = new JPanel();
		JLabel titoloSezionePromuovi = new JLabel("SEZIONE PROMUOVI UTENTE", SwingConstants.CENTER);
		pannelloTitolo.add(titoloSezionePromuovi);

		ca.weightx = 0;
		ca.weighty = 0;

		ca.ipady = 10;
		ca.fill = GridBagConstraints.HORIZONTAL;
		set(ca, 0, 0, 1, 1);
		addC(pannelloTitolo);

		// ----------------------------------------------------//

		JPanel pannelloTitolo2 = new JPanel();
		JLabel titoloSezioneElimina = new JLabel("SEZIONE ELIMINA UTENTE", SwingConstants.CENTER);
		pannelloTitolo2.add(titoloSezioneElimina);

		ca.weightx = 0;
		ca.weighty = 0;


		ca.ipady = 10;
		ca.fill = GridBagConstraints.HORIZONTAL;
		set(ca, 1, 0, 1, 1);
		addC(pannelloTitolo2);

		// ----------------------------------------------------//

		pannelloPromuovi pannello1 = new pannelloPromuovi();
		JScrollPane scrollBar1 = new JScrollPane(pannello1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		ca.weightx = 0;
		ca.weighty = 0;
		ca.ipadx = 800;

		ca.fill = GridBagConstraints.BOTH;
		set(ca, 0, 1, 1, 1);
		addC(scrollBar1);

		// ----------------------------------------------------//

		pannelloElimina pannello2 = new pannelloElimina();
		JScrollPane scrollBar2 = new JScrollPane(pannello2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		ca.weightx = 1;
		ca.weighty = 1;
		ca.ipadx = 300;

		ca.fill = GridBagConstraints.BOTH;
		set(ca, 1, 1, 1, 1);
		addC(scrollBar2);

		// ----------------------------------------------------//

		pannelloUtility pannello3 = new pannelloUtility();

		ca.weightx = 0;
		ca.weighty = 0;
		ca.ipady = 50;
		ca.ipadx = 50;

		ca.fill = GridBagConstraints.BOTH;
		set(ca, 0, 2, 2, 1);
		addC(pannello3);

		// ----------------------------------------------------//

	}

	public void chiudiFrame()
	{
		this.dispose();
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

	// --------------CLASSI COMPONENTI--------//

	private class pannelloPromuovi extends JPanel
	{
		// VARIABILI E COMPONENTI
		private JLabel titoloPromuovi = new JLabel("SEZIONE PROMUOVI UTENTE");

		// LAYOUT
		private BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);

		// COSTRUTTORE
		public pannelloPromuovi()
		{
			setLayout(box);

			int numUtenti = new _FINITO_sezioneAmministratoreVIEW().getNumUtenti();
			LinkedList<Utente> localList = new _FINITO_sezioneAmministratoreVIEW().getListaUtenti();

			add(Box.createRigidArea(new Dimension(0, 30)));
			for (int i = 0; i < numUtenti; i++)
			{
				String nick = localList.get(i).getNickname();
				add(new elementoPromuovi(nick));
				add(Box.createRigidArea(new Dimension(0, 30)));
			}

			setVisible(true);
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

	private class elementoPromuovi extends JPanel
	{
		// ELEMENTI
		String nicknameLocale;

		Border bordo = BorderFactory.createLineBorder(Color.BLACK, 1);

		JLabel		nomeUtente	= new JLabel();
		JCheckBox	utenteVip	= new JCheckBox("Utente Vip");

		ButtonGroup		scelte			= new ButtonGroup();
		JRadioButton	trascrittore	= new JRadioButton("Trascrittore");
		JRadioButton	uploader		= new JRadioButton("Uploader");
		JRadioButton	manager			= new JRadioButton("Manager");

		JButton ok = new JButton("CONFERMA");

		// LAYOUT
		GridLayout griglia = new GridLayout(1, 6, 10, 0);

		// COSTRUTTORE
		public elementoPromuovi(String nickname)
		{
			nicknameLocale = nickname;
			String pathImmagineProfilo = new _FINITO_profiloUtenteVIEW().getImmagineProfilo(nickname);

			// --------------RIDIMENSIONAMENTO IMMAGINE----------------------//

			ImageIcon immagineIcona = new ImageIcon(pathImmagineProfilo);
			Image immagine = immagineIcona.getImage();
			Image nuovaImmagine = immagine.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			immagineIcona = new ImageIcon(nuovaImmagine);

			// --------------------------------------------------------------//

			JLabel immagineProfilo = new JLabel(immagineIcona);

			setLayout(griglia);
			nomeUtente.setText(nickname);
			nomeUtente.setHorizontalAlignment(SwingConstants.CENTER);

			scelte.add(trascrittore);
			scelte.add(uploader);
			scelte.add(manager);

			add(immagineProfilo);
			add(nomeUtente);
			add(utenteVip);
			add(trascrittore);
			add(uploader);
			add(manager);
			add(ok);

			inizializzaActionListner();
		}

		// METODI
		private void inizializzaActionListner()
		{
			ok.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_sezioneAmministratoreVIEW().premutoConferma(nicknameLocale, utenteVip, trascrittore, uploader, manager);
				}
			});
		}
	}

	private class pannelloElimina extends JPanel
	{
		// VARIABILI E COMPONENTI
		private JLabel titoloElimina = new JLabel("SEZIONE ELIMINA UTENTE");

		// LAYOUT
		private BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);

		// COSTRUTTORE
		public pannelloElimina()
		{
			setLayout(box);

			int numUtenti = new _FINITO_sezioneAmministratoreVIEW().getNumUtenti();
			LinkedList<Utente> localList = new _FINITO_sezioneAmministratoreVIEW().getListaUtenti();

			for (int i = 0; i < numUtenti; i++)
			{
				String nick = localList.get(i).getNickname();
				add(Box.createRigidArea(new Dimension(20, 20)));
				add(new elementoElimina(nick));
				add(Box.createRigidArea(new Dimension(20, 20)));
			}
		}
	}

	private class elementoElimina extends JPanel
	{
		// ELEMENTI
		String nicknameLocale;

		Border bordo = BorderFactory.createLineBorder(Color.BLACK, 1);

		JLabel	nomeUtente	= new JLabel();
		JButton	elimina		= new JButton("ELIMINA UTENTE");

		GridLayout grid = new GridLayout(1, 3);

		// COSTRUTTORE
		public elementoElimina(String nickname)
		{
			nicknameLocale = nickname;
			String pathImmagineProfilo = new _FINITO_profiloUtenteVIEW().getImmagineProfilo(nickname);

			// --------------RIDIMENSIONAMENTO IMMAGINE----------------------//

			ImageIcon immagineIcona = new ImageIcon(pathImmagineProfilo);
			Image immagine = immagineIcona.getImage();
			Image nuovaImmagine = immagine.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			immagineIcona = new ImageIcon(nuovaImmagine);

			// --------------------------------------------------------------//

			JLabel immagineProfilo = new JLabel(immagineIcona);

			setLayout(grid);
			nomeUtente.setText(nickname);
			nomeUtente.setHorizontalAlignment(SwingConstants.CENTER);

			add(immagineProfilo);
			add(nomeUtente);
			add(elimina);

			inizializzaActionListner();
		}

		// METODI
		private void inizializzaActionListner()
		{
			elimina.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					boolean esito = new _FINITO_sezioneAmministratoreVIEW().rimuoviUtente(nicknameLocale);
					if (esito)
					{
						chiudiFrame();
						new _FINITO_AmministratoreWorkbenchGUI(id);
					}
				}
			});
		}

	}

	private class pannelloUtility extends JPanel
	{
		// VARIABILI

		private JButton				ricerca	= new JButton("RICERCA OPERA");
		private JButton				elimina	= new JButton("ELIMINA OPERA");
		private GridBagLayout		box		= new GridBagLayout();
		private GridBagConstraints	ca		= new GridBagConstraints();

		// COSTRUTTORE

		public pannelloUtility()
		{
			setLayout(box);

			ca.weightx = 1;
			ca.weighty = 1;

			ca.ipady = 30;
			ca.ipadx = 50;

			addC(ricerca);
			addC(elimina);

			inizializzaActionListner();
		}

		// METODI

		private void inizializzaActionListner()
		{
			ricerca.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_sezioneAmministratoreVIEW().premutoRicercaOpera(id);
				}
			});

			elimina.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_sezioneAmministratoreVIEW().premutoEliminaOpera();
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

	// -----------------------------------------------------------------------------------------------------//
	//
	//
	//
	//
	//
	// ------------MAIN-------------------------------//

	public static void main(String[] args)
	{
		_FINITO_AmministratoreWorkbenchGUI Admin = new _FINITO_AmministratoreWorkbenchGUI(0);
	}


}
