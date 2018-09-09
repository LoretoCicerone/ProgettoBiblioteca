package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.A_MANOSCRITTO_1_Pagina;
import VIEW.funzionalitaVarieVIEW;

public class _FINITO_funzione_visualizzaOpera extends JFrame
{
	// ----------ELEMENTI----------------------//

	private pannelloTrascrizione		trascrizione	= new pannelloTrascrizione();
	private A_MANOSCRITTO_0_Manoscritto	operaDaVisualizzareL;
	private A_MANOSCRITTO_1_Pagina		PaginaDaVisualizzareL;
	private JFrame						soggetto		= this;
	private String						trascrizioneTesto;

	// LAYOUT
	GridBagLayout		griglia	= new GridBagLayout();
	GridBagConstraints	ca		= new GridBagConstraints();

	// ---------COSTRUTTORE--------------------//

	public _FINITO_funzione_visualizzaOpera(A_MANOSCRITTO_0_Manoscritto operaDaVisualizzare, A_MANOSCRITTO_1_Pagina PaginaDaVisualizzare)
	{
		operaDaVisualizzareL = operaDaVisualizzare;
		PaginaDaVisualizzareL = PaginaDaVisualizzare;


		trascrizioneTesto = PaginaDaVisualizzareL.getTrascrizione();



		inizializzaFrame();
		inizializzaPannelloSuperiore();
		inizializzaImmagine();
		inizializzaSeparatore();
		inizializzaTrascrizione();
		this.setVisible(true);
	}

	// ----------METODI-----------------------//

	private void inizializzaFrame()
	{
		this.setTitle("SEZIONE VISUALIZZA MANOSCRITTO: " + operaDaVisualizzareL.getTitolo());
		this.setSize(1500, 1000);
		this.setMinimumSize(new Dimension(1500, 1000));
		this.setResizable(true);
		this.setLayout(griglia);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void inizializzaPannelloSuperiore()
	{
		ca.weightx = 0;
		ca.weighty = 0;

		ca.fill = GridBagConstraints.BOTH;

		ca.ipady = 50;

		ca.insets.left = 0;
		ca.insets.right = 0;

		set(ca, 0, 0, 3, 1);
		addC(new pannelloSuperiore());
	}

	private void inizializzaImmagine()
	{
		ca.weightx = 1;
		ca.weighty = 1;

		ca.fill = GridBagConstraints.BOTH;

		ca.insets.left = 0;
		ca.insets.right = 0;

		set(ca, 0, 1, 1, 1);
		addC(new pannelloImmagine());
	}

	private void inizializzaSeparatore()
	{
		ca.weightx = 0;
		ca.weighty = 0;

		ca.fill = GridBagConstraints.VERTICAL;

		ca.insets.left = 16;
		ca.insets.right = 16;

		set(ca, 1, 1, 1, 1);
		addC(new pannelloSeparatore());
	}

	private void inizializzaTrascrizione()
	{
		ca.weightx = 1;
		ca.weighty = 1;

		ca.fill = GridBagConstraints.BOTH;

		ca.insets.left = 0;
		ca.insets.right = 0;

		set(ca, 2, 1, 1, 1);
		addC(new pannelloTrascrizione());
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


	// ----------CLASSI COMPONENTI------------//

	private class pannelloSuperiore extends JPanel
	{
		// ELEMENTI
		JButton download = new JButton("Scarica questa immagine");

		// LAYOUT
		GridBagLayout		griglia	= new GridBagLayout();
		GridBagConstraints	ca		= new GridBagConstraints();

		// COSTRUTTORE
		public pannelloSuperiore()
		{
			setLayout(griglia);
			ca.weightx = 1;
			ca.weighty = 1;

			// -----------------------//

			ca.ipadx = 100;
			ca.ipady = 20;

			ca.anchor = GridBagConstraints.CENTER;
			set(ca, 0, 0, 1, 1);
			addC(download);

			inizializzaActionListner();
		}

		// METODI
		private void inizializzaActionListner()
		{
			download.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new funzionalitaVarieVIEW().downloadImmagine(PaginaDaVisualizzareL.getIdImmagine(), PaginaDaVisualizzareL.getPathImmagine());
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

	private class pannelloImmagine extends JPanel
	{
		// ELEMENTI
		JLabel immagineLabel;

		// LAYOUT
		GridBagLayout		griglia	= new GridBagLayout();
		GridBagConstraints	ca		= new GridBagConstraints();

		// BOTTONI
		JButton indietro = new JButton("<-");

		// COSTRUTTORE
		public pannelloImmagine()
		{
			// -----------------------RIDIMENSIONAMENTO IMMAGINE----------------------------//

			ImageIcon immagineIcona = new ImageIcon(PaginaDaVisualizzareL.getPathImmagine());
			Image immagine = immagineIcona.getImage();
			Image nuovaImmagine = immagine.getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH);
			immagineIcona = new ImageIcon(nuovaImmagine);

			immagineLabel = new JLabel(immagineIcona);
			// -----------------------------------------------------------------------------//



			setLayout(griglia);
			ca.weightx = 1;
			ca.weighty = 1;

			// -----------------------//

			ca.fill = GridBagConstraints.BOTH;
			ca.insets.left = 16;
			ca.insets.right = 16;
			ca.insets.bottom = 16;
			ca.insets.top = 16;

			ca.ipadx = 600;
			ca.ipady = 600;

			set(ca, 1, 0, 1, 1);
			addC(immagineLabel);

			// -------------------------//
			ca.weightx = 0;
			ca.weighty = 0;

			ca.fill = GridBagConstraints.HORIZONTAL;
			ca.insets.left = 16;
			ca.insets.right = 0;
			ca.ipadx = 30;
			ca.ipady = 20;

			set(ca, 0, 0, 1, 1);
			addC(indietro);

			// -------------------------//

			inizializzaActionListner();
		}

		// METODI
		private void inizializzaActionListner()
		{
			indietro.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new funzionalitaVarieVIEW().paginaIndietro(soggetto);
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

	private class pannelloSeparatore extends JPanel
	{
		// ELEMENTI
		JSeparator separatore = new JSeparator(SwingConstants.VERTICAL);

		// LAYOUT
		GridBagLayout		griglia	= new GridBagLayout();
		GridBagConstraints	ca		= new GridBagConstraints();

		// COSTRUTTORE
		public pannelloSeparatore()
		{
			setLayout(griglia);

			ca.weightx = 1;
			ca.weighty = 1;

			// --------------------------------//

			ca.fill = GridBagConstraints.VERTICAL;
			ca.anchor = GridBagConstraints.CENTER;
			ca.insets.left = 16;
			ca.insets.right = 16;

			ca.ipadx = 0;
			ca.ipady = 0;

			set(ca, 0, 0, 1, 1);
			addC(separatore);
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

	private class pannelloTrascrizione extends JPanel
	{
		// ELEMENTI
		JTextArea trascrizione = new JTextArea();

		// LAYOUT
		GridBagLayout		griglia	= new GridBagLayout();
		GridBagConstraints	ca		= new GridBagConstraints();

		// BOTTONI
		JButton avanti = new JButton("->");

		// COSTRUTTORE
		public pannelloTrascrizione()
		{
			setLayout(griglia);
			ca.weightx = 1;
			ca.weighty = 1;

			// -----------------------------//

			ca.fill = GridBagConstraints.BOTH;
			ca.insets.left = 16;
			ca.insets.right = 16;
			ca.insets.bottom = 16;
			ca.insets.top = 16;

			ca.ipadx = 600;
			ca.ipady = 600;

			set(ca, 0, 0, 1, 1);
			trascrizione.setText(trascrizioneTesto);
			trascrizione.setEditable(false);
			addC(trascrizione);

			// -------------------------//
			ca.weightx = 0;
			ca.weighty = 0;

			ca.fill = GridBagConstraints.HORIZONTAL;
			ca.insets.left = 0;
			ca.insets.right = 16;
			ca.ipadx = 30;
			ca.ipady = 20;

			set(ca, 1, 0, 1, 1);
			addC(avanti);

			// -------------------------//

			inizializzaActionListner();
		}

		// METODI
		private void inizializzaActionListner()
		{
			avanti.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new funzionalitaVarieVIEW().paginaAvanti(soggetto);
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

	// ---------------------------------------//
	//
	//
	//
	//
	// ----------MAIN-------------------------//

	public static void main(String[] args)
	{
		_FINITO_funzione_visualizzaOpera visualizza= new _FINITO_funzione_visualizzaOpera(null, null);
	}

}
