package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

public class _FINITO_APPOGGIO_elencoTrascrittoriFinestra extends JFrame
{
	// VARIABILI
	private GridLayout griglia = new GridLayout(1, 1);

	private int id_immagineDaRiassegnare;

	// COSTRUTTORE
	public _FINITO_APPOGGIO_elencoTrascrittoriFinestra(int ID_immagine)
	{
		this.id_immagineDaRiassegnare = ID_immagine;

		inizializzaFrame();
		this.add(new pannelloT());
		this.setVisible(true);
	}

	// METODI
	public void inizializzaFrame()
	{
		this.setTitle("RIASSEGNA TRASCRIZIONE");
		this.setSize(450, 600);
		this.setMinimumSize(new Dimension(450, 600));
		this.setResizable(true);
		this.setLayout(griglia);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	// CLASSI COMPONENTI
	private class pannelloT extends JPanel
	{
		// VARIABILI
		private JPanel		pannelloTitolo	= new JPanel();
		private JPanel		pannelloElenco	= new JPanel();
		private JLabel		titoloElenco	= new JLabel("ELENCO UTENTI TRASCRITTORI", SwingConstants.CENTER);
		private JScrollPane	scrollPane		= new JScrollPane(pannelloElenco, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		private BoxLayout			box		= new BoxLayout(pannelloElenco, BoxLayout.Y_AXIS);
		private GridBagLayout		grid	= new GridBagLayout();
		private GridBagConstraints	ca		= new GridBagConstraints();

		private ArrayList<Utente> listaTrascrittori;

		// COSTRUTTORE
		public pannelloT()
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
		}

		// CLASSI COMPONENTI
		public class elementoTrascrittore extends JPanel
		{
			// VARIABILI
			GridBagLayout		grid	= new GridBagLayout();
			GridBagConstraints	ca		= new GridBagConstraints();
			Border				bordo	= BorderFactory.createLineBorder(Color.BLACK, 1);
			JButton				nomeTrascrittore;
			int					ID_trascrittoreAttuale;

			// COSTRUTTORE
			public elementoTrascrittore(Utente trascrittore)
			{
				setLayout(grid);

				nomeTrascrittore = new JButton(trascrittore.getNickname());
				this.ID_trascrittoreAttuale = trascrittore.getID();

				ca.weightx = 1;
				ca.weighty = 1;

				ca.ipady = 25;

				ca.insets.left = 0;
				ca.insets.right = 16;
				ca.insets.bottom = 16;
				ca.insets.top = 16;

				set(ca, 0, 0, 1, 1);
				add(nomeTrascrittore, ca);

				ca.ipadx = 60;
				ca.insets.right = 10;

				inizializzaActionListner();
			}

			// METODI
			public void inizializzaActionListner()
			{
				nomeTrascrittore.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						new _FINITO_sezioneManagerVIEW().riassegnaImmagineTrascrittore(id_immagineDaRiassegnare, ID_trascrittoreAttuale);
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

	// ------------------------------------------------------------//
	//
	//
	//
	//
	//
	// ---------------MAIN-----------------------------------------//
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
