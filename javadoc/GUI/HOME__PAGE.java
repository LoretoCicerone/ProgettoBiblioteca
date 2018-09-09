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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import MODEL.A_MANOSCRITTO_0_Manoscritto;
import VIEW.funzionalitaVarieVIEW;

public class HOME__PAGE extends JFrame
{
	// -----------------------ATTRIBUTI-----------------------------------//

	ArrayList<A_MANOSCRITTO_0_Manoscritto> listaManoscrittiDaVisualizzare;

	// -----------------------COSTRUTTORE---------------------------------//

	public HOME__PAGE()
	{
		listaManoscrittiDaVisualizzare = new funzionalitaVarieVIEW().getListaManoscritti();

		inizializzaFrame();
		inizializzaInterfaccia();

		this.setVisible(true);
	}

	// -------------------------METODI------------------------------------//

	public void inizializzaFrame()
	{
		this.setTitle("HOME PAGE LIBRERIA ONLINE");
		this.setLayout(new GridLayout(1, 1));
		this.setSize(1200, 800);
		this.setLocation(0, 0);
		this.setMinimumSize(new Dimension(1200, 800));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void inizializzaInterfaccia()
	{
		this.add(new pannelloHomePage());
	}

	// -------CLASSI COMPONENTI-------------------------------------------------//

	public class pannelloHomePage extends JPanel
	{
		// VARIABILI
		GridBagLayout		grid	= new GridBagLayout();
		GridBagConstraints	ca		= new GridBagConstraints();

		private Border bordo = BorderFactory.createLineBorder(Color.BLACK, 2);

		JButton	login			= new JButton("LOGIN");
		JButton	registrati		= new JButton("REGISTRATI");
		JButton	vaiAlProfilo	= new JButton("VAI AL TUO PROFILO");
		JButton	ricercaOpera	= new JButton("RICERCA OPERA");

		JLabel zonaInfo = new JLabel(
				"<html>Benvenuto nella Libreria Online MANDADORY, in questa piattaforma potrai trovare moltissimi antichi manoscritti, tutti consultabili, per accedere alla funzione ricerca Opera, accedi o registrati alla piattafroma, tale funzione è disponibile presso il proprio profilo, in più se desideri partecipare al nostro progetto, puoi anche diventare trascrittore, per aiutarci nelle trascrizioni delle pagine, oppure un Uploader, che si occupa di inserire nella piattaforma le immagini delle pagine dei manoscritti, oppure diventare un manager che ha il compito di revisionare tutto il materiale prima della pubblicazione ufficale.</hmtl>",
				SwingConstants.CENTER);

		JLabel	opera1;
		JLabel	opera2;
		JLabel	opera3;
		JLabel	opera4;

		// COSTRUTTORE
		public pannelloHomePage()
		{
			setLayout(grid);

			ca.weightx = 0;
			ca.weighty = 0;
			ca.insets.left = 20;
			ca.insets.right = 20;

			// ------------------------------------------------------------------//

			ca.insets.top = 20;
			ca.insets.bottom = 20;

			ca.ipadx = 50;
			ca.ipady = 20;

			set(ca, 2, 0, 1, 2);
			addC(login);

			set(ca, 3, 0, 1, 2);
			addC(registrati);

			ca.weightx = 0;
			ca.weighty = 0;

			// ------------------------------------------------------------------//

			ca.fill = GridBagConstraints.BOTH;

			ca.ipady = 150;

			set(ca, 0, 1, 2, 1);
			zonaInfo.setBackground(Color.WHITE);
			zonaInfo.setBorder(bordo);
			zonaInfo.setOpaque(true);
			addC(zonaInfo);

			// ------------------------------------------------------------------//

			ca.weightx = 1;
			ca.weighty = 1;

			ca.fill = GridBagConstraints.BOTH;

			opera1 = new JLabel(settaggioImmagini(listaManoscrittiDaVisualizzare.get(0).getPathImmagineCopertina()));
			opera1.setOpaque(true);
			set(ca, 0, 2, 1, 1);
			addC(opera1);

			opera2 = new JLabel(settaggioImmagini(listaManoscrittiDaVisualizzare.get(1).getPathImmagineCopertina()));
			opera2.setOpaque(true);
			set(ca, 1, 2, 1, 1);
			addC(opera2);

			opera3 = new JLabel(settaggioImmagini(listaManoscrittiDaVisualizzare.get(2).getPathImmagineCopertina()));
			opera3.setOpaque(true);
			set(ca, 2, 2, 1, 1);
			addC(opera3);

			opera4 = new JLabel(settaggioImmagini(listaManoscrittiDaVisualizzare.get(3).getPathImmagineCopertina()));
			opera4.setOpaque(true);
			set(ca, 3, 2, 1, 1);
			addC(opera4);

			// ------------------------------------------------------------------//

			ca.weightx = 0;
			ca.weighty = 0;

			ca.insets.top = 20;
			ca.insets.bottom = 20;

			ca.ipadx = 50;
			ca.ipady = 20;

			ca.fill = GridBagConstraints.NONE;

			inizializzaActionListner();
		}

		// METODI
		public void inizializzaActionListner()
		{
			login.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new funzionalitaVarieVIEW().premutoLogin();
				}
			});

			registrati.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new funzionalitaVarieVIEW().premutoRegistrati();
				}
			});

		}

		public ImageIcon settaggioImmagini(String path)
		{
			// -------------------------RIDIMENSIONAMENTO IMMAGINE--------------------------//

			ImageIcon immagineIcona = new ImageIcon(path);
			Image immagine2 = immagineIcona.getImage();
			Image nuovaImmagine = immagine2.getScaledInstance(200, 300, java.awt.Image.SCALE_SMOOTH);
			immagineIcona = new ImageIcon(nuovaImmagine);

			// -----------------------------------------------------------------------------//

			return immagineIcona;
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

	// -------------------------------------------------------------------//

	public static void main(String[] args)
	{
		HOME__PAGE hp = new HOME__PAGE();
	}

}
