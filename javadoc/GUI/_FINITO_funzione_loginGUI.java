package GUI;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import VIEW._FINITO_loginVIEW;

public class _FINITO_funzione_loginGUI extends JFrame
{
	// -----------VARIABILI-----------------------------------------------------//
	JFrame finestra = this;

	// ETICHETTE
	private JLabel	nickname	= new JLabel("NICKNAME");
	private JLabel	password	= new JLabel("PASSWORD");
	// JTEXT
	private JTextField	testo_nickname	= new JTextField();
	private JTextField	testo_password	= new JTextField();
	// BOTTONI
	private JButton	bottoneAccedi		= new JButton("ACCEDI");
	private JButton	bottoneRegistrati	= new JButton("REGISTRATI");
	// VARIABILI PER LAYOUT
	private GridLayout grid = new GridLayout(1, 1);
	// SFONDO
	ImageIcon sfondo;

	// -----------COSTRUTTORE---------------------------------------------------//

	public _FINITO_funzione_loginGUI()
	{
		inizializza_frame();
		inizializza_interfaccia();
		inizializzaActionListner();
		this.setVisible(true);
	}

	// -----------METODI--------------------------------------------------------//

	private void inizializza_frame()
	{
		this.setTitle("LOGIN");
		this.setSize(600, 400);
		this.setResizable(false);
		this.setLayout(grid);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void inizializza_interfaccia()
	{
		labelPannello label = new labelPannello();
		final String path = "./res/sfondi/sfondo 600x400 LOGIN.jpg";
		ImageIcon sfondo = new ImageIcon(path);

		label.setIcon(sfondo);
		this.add(label);
	}

	public void inizializzaActionListner()
	{
		bottoneAccedi.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				boolean accesso = new _FINITO_loginVIEW().accedi(testo_nickname.getText(), testo_password.getText());
				if (accesso)
				{
					finestra.setVisible(false);
					finestra.dispose();
				}
			}
		});

		bottoneRegistrati.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				finestra.setVisible(false);
				finestra.dispose();
				new _FINITO_loginVIEW().registrati();
			}
		});

	}

	// -------CLASSI COMPONENTI-------------------------------------------------//

	private class labelPannello extends JLabel
	{
		// VARIABILI
		GridBagLayout		grid	= new GridBagLayout();
		GridBagConstraints	ca		= new GridBagConstraints();

		// COSTRUTTORE
		public labelPannello()
		{
			setLayout(grid);

			setSize(600, 400);

			ca.weightx = 1;
			ca.weighty = 1;

			set(ca, 0, 0, 1, 1);
			addC(nickname);

			set(ca, 0, 1, 1, 1);
			addC(password);


			ca.ipady = 30;
			ca.ipadx = 150;
			set(ca, 0, 2, 1, 1);
			addC(bottoneAccedi);

			set(ca, 1, 2, 1, 1);
			addC(bottoneRegistrati);

			ca.ipady = 20;
			ca.ipadx = 300;
			set(ca, 1, 0, 1, 1);
			addC(testo_nickname);

			set(ca, 1, 1, 1, 1);
			addC(testo_password);

			this.setOpaque(true);

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

	// ---------------------------------------------------------------------------//
	//
	//
	//
	//
	//
	//
	// --------------------MAIN-------------------------------------------------------//

	public static void main(String[] args)
	{
		_FINITO_funzione_loginGUI prova = new _FINITO_funzione_loginGUI();

	}




}
