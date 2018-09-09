package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.A_MANOSCRITTO_1_Pagina;
import VIEW._FINITO_sezioneManagerVIEW;

public class _FINITO_ManagerRevisioneImmaginiGUI extends JFrame
{
	// ---------------VARIBILI----------------//

	private GridLayout	griglia		= new GridLayout(1, 1);
	private pannello	contenuto	= new pannello();
	private int			idManagerLocale;

	// ---------------COSTRUTTORE-------------//

	public _FINITO_ManagerRevisioneImmaginiGUI(int ID_manager)
	{
		inizializzaFrame();
		inizializzaInterfaccia(ID_manager);
	}

	// ---------------METODI-------------------//

	public void inizializzaFrame()
	{
		this.setTitle("SEZIONE REVISIONA IMMAGINI DEGLI UPLOADER");
		this.setSize(1200, 800);
		this.setLocation(800, 0);
		this.setMinimumSize(new Dimension(1200, 800));
		this.setResizable(true);
		this.setLayout(griglia);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void inizializzaInterfaccia(int ID_manager)
	{
		this.idManagerLocale = ID_manager;
		this.add(contenuto);
		this.setVisible(true);
	}

	public void chiudiFrame()
	{
		this.dispose();
	}

	// --------------CLASSI COMPONENTI--------//

	private class pannello extends JPanel
	{
		// VARIBILI
		private JTabbedPane tabs = new JTabbedPane();

		private GridLayout						grigliaPerTabs		= new GridLayout(1, 1);
		private GridLayout						grigliaInTab		= new GridLayout();
		ArrayList<A_MANOSCRITTO_0_Manoscritto>	listaManoscritti	= new _FINITO_sezioneManagerVIEW().getListaManoscritti();

		// COSTRUTTORE
		public pannello()
		{
			setLayout(grigliaPerTabs);

			for (int i = 0; i < listaManoscritti.size(); i++)
			{
				contenutoTab tabManoscritto = new contenutoTab(listaManoscritti.get(i));
				JScrollPane scrollBar = new JScrollPane(tabManoscritto);

				// tabsOpera è il tab della singola opera, e come parametro vuole una entita opera
				tabs.addTab(listaManoscritti.get(i).getTitolo(), scrollBar);
			}

			add(tabs);
		}

	}

	private class contenutoTab extends JPanel
	{
		// VARIABILI
		private BoxLayout	box	= new BoxLayout(this, BoxLayout.Y_AXIS);
		private int			idManoscritto;

		// COSTRUTTORE
		public contenutoTab(A_MANOSCRITTO_0_Manoscritto manoscritto)
		{
			this.idManoscritto = manoscritto.getID();
			setLayout(box);
			inizializzaTab();
		}

		// METODI
		private void inizializzaTab()
		{
			ArrayList<A_MANOSCRITTO_1_Pagina> listaPagineManoscrittoLocale = new _FINITO_sezioneManagerVIEW().getListaPagineManoscrittoTUTTE(idManoscritto);

			for (int i = 0; i < listaPagineManoscrittoLocale.size(); i++)
			{
				add(new ImmagineDaRevisionare(listaPagineManoscrittoLocale.get(i)));
			}

		}

		// CLASSI COMPONENTI
		private class ImmagineDaRevisionare extends JPanel
		{
			// VARIABILI
			private GridBagLayout		grid	= new GridBagLayout();
			private GridBagConstraints	ca		= new GridBagConstraints();

			private Border bordo = BorderFactory.createLineBorder(Color.BLACK, 2);

			private JButton	accetta		= new JButton("ACCETTA");
			private JButton	rifiuta		= new JButton("RIFIUTA");
			private JButton	anteprima	= new JButton("ANTEPRIMA");

			private JLabel	immaginePaginaLabel;
			private JLabel	statoImmagineLabel;

			private String		pathImmagine;
			private String		statoImmagine;
			private final int	idImmagine;


			// COSTRUTTORE
			public ImmagineDaRevisionare(A_MANOSCRITTO_1_Pagina pagina)
			{
				// -------settaggio elementi-------------------------------------//

				idImmagine = pagina.getIdImmagine();
				pathImmagine = pagina.getPathImmagine();
				statoImmagine = pagina.getStatoImmagine();

				// --------------RIDIMENSIONAMENTO IMMAGINE----------------------//

				ImageIcon immagineIcona = new ImageIcon(pathImmagine);
				Image immagine = immagineIcona.getImage();
				Image nuovaImmagine = immagine.getScaledInstance(200, 300, java.awt.Image.SCALE_SMOOTH);
				immagineIcona = new ImageIcon(nuovaImmagine);

				// --------------------------------------------------------------//

				immaginePaginaLabel = new JLabel(immagineIcona);
				immaginePaginaLabel.setOpaque(true);

				// --------------------------------------------------------------//

				setLayout(grid);

				ca.weightx = 1;
				ca.weighty = 1;

				ca.insets.left = 10;
				ca.insets.right = 10;
				ca.insets.bottom = 10;
				ca.insets.top = 10;

				ca.ipady = 30;
				ca.ipadx = 50;

				// -------------------------IMMAGINE-----------------------------//

				set(ca, 0, 0, 1, 1);
				immaginePaginaLabel.setBorder(bordo);
				add(immaginePaginaLabel, ca);

				// --------------------------------------------------------------//

				statoImmagineLabel = new JLabel("STATO IMMAGINE: " + statoImmagine, SwingConstants.CENTER);

				if (statoImmagine.equals("accettata"))
				{
					statoImmagineLabel.setBackground(Color.GREEN);
					statoImmagineLabel.setOpaque(true);

					accetta.setEnabled(false);
					rifiuta.setEnabled(false);
				}
				else if (statoImmagine.equals("declinata"))
				{
					statoImmagineLabel.setBackground(Color.RED);
					statoImmagineLabel.setOpaque(true);

					accetta.setBackground(Color.GREEN);
					rifiuta.setBackground(Color.RED);
				}
				else if (statoImmagine.equals("In Attesa"))
				{
					statoImmagineLabel.setBackground(Color.YELLOW);
					statoImmagineLabel.setOpaque(true);

					accetta.setBackground(Color.GREEN);
					rifiuta.setBackground(Color.RED);
				}


				set(ca, 1, 0, 1, 1);
				statoImmagineLabel.setBorder(bordo);
				add(statoImmagineLabel, ca);

				// --------------------------------------------------------------//

				set(ca, 2, 0, 1, 1);
				add(anteprima, ca);

				JLabel spazio = new JLabel(" ");
				set(ca, 3, 0, 2, 1);
				add(spazio, ca);

				set(ca, 5, 0, 1, 1);
				add(accetta, ca);

				set(ca, 6, 0, 1, 1);
				add(rifiuta, ca);

				// --------------------------------------------------------------//

				inizializzaActionListner();
			}

			// METODI
			public void inizializzaActionListner()
			{
				anteprima.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e)
					{
						new _FINITO_sezioneManagerVIEW().premutoAnteprimaImmagine(pathImmagine);
					}
				});

				accetta.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e)
					{
						boolean esitoModifica = new _FINITO_sezioneManagerVIEW().premutoAccettaImmagine(idImmagine, idManagerLocale);
						if (esitoModifica)
						{
							chiudiFrame();
							new _FINITO_ManagerRevisioneImmaginiGUI(idManagerLocale);
						}
					}
				});

				rifiuta.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e)
					{
						boolean esitoModifica = new _FINITO_sezioneManagerVIEW().premutoRifiutaImmagine(idImmagine, idManagerLocale);
						if (esitoModifica)
						{
							chiudiFrame();
							new _FINITO_ManagerRevisioneImmaginiGUI(idManagerLocale);
						}
					}
				});
			}

			public void set(GridBagConstraints lim, int gridx, int gridy, int gridwidth, int gridheight)
			{
				lim.gridx = gridx;
				lim.gridy = gridy;
				lim.gridwidth = gridwidth;
			}

			private Image getScaledImage(Image srcImg, int w, int h)
			{
				BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2 = resizedImg.createGraphics();

				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g2.drawImage(srcImg, 0, 0, w, h, null);
				g2.dispose();

				return resizedImg;
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
		_FINITO_ManagerRevisioneImmaginiGUI revim = new _FINITO_ManagerRevisioneImmaginiGUI(5);

	}

}
