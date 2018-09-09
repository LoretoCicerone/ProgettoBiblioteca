package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import MODEL.Utente;
import VIEW._FINITO_sezioneManagerVIEW;

public class _FINITO_APPOGGIO_finestraAssegnaEsperienza extends JFrame
{
	// VARIABILI
	GridLayout grid = new GridLayout(1, 1);

	String	nickTrascrittore;
	String	nickManager;
	Utente	trascrittore;
	JFrame	soggettoPerRefresh;

	// COSTRUTTORE
	public _FINITO_APPOGGIO_finestraAssegnaEsperienza(Utente trascrittore, JFrame soggetto, String nicknameManager)
	{
		this.nickTrascrittore = trascrittore.getNickname();
		this.trascrittore = trascrittore;
		this.nickManager = nicknameManager;
		this.soggettoPerRefresh = soggetto;

		inizializzaFrame(nickTrascrittore);
		inizializzaInterfaccia();


		setVisible(true);
	}

	// METODI
	public void inizializzaFrame(String nick)
	{
		this.setTitle("ASSEGNA ESPERIENZA A: " + nick);
		this.setSize(450, 150);
		this.setResizable(true);
		this.setLayout(grid);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void inizializzaInterfaccia()
	{
		this.add(new pannello(trascrittore));
	}

	// CLASSI COMPONENTI
	public class pannello extends JPanel
	{
		// VARIABILI
		GridBagLayout		grid	= new GridBagLayout();
		GridBagConstraints	ca		= new GridBagConstraints();

		JLabel	nomeTrascrittore	= new JLabel("", SwingConstants.CENTER);
		JButton	aumentaLivello		= new JButton("LVL +1");

		// COSTRUTTORE
		public pannello(Utente trascrittore)
		{
			nomeTrascrittore.setText(trascrittore.getNickname());

			setLayout(grid);

			ca.weighty = 1;
			ca.weightx = 1;

			ca.ipadx = 100;
			ca.ipady = 50;

			set(ca, 0, 0, 1, 1);
			nomeTrascrittore.setBackground(Color.WHITE);
			nomeTrascrittore.setOpaque(true);
			addC(nomeTrascrittore);



			set(ca, 1, 0, 1, 1);
			addC(aumentaLivello);

			inizializzaActionListner();
		}

		// METODI
		private void inizializzaActionListner()
		{
			aumentaLivello.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_sezioneManagerVIEW().aumentaLivelloTrascrittore(trascrittore.getID(), trascrittore.getExp(), soggettoPerRefresh, nickManager);
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

		// CLASSI COMPONENTI
	}

	// --------------------------------------------------------//
	//
	//
	//
	//
	//
	// --------------------MAIN-------------------------------//

	public static void main(String[] args)
	{
		_FINITO_APPOGGIO_finestraAssegnaEsperienza Assegnaexp = new _FINITO_APPOGGIO_finestraAssegnaEsperienza(null, null, null);
	}
}
