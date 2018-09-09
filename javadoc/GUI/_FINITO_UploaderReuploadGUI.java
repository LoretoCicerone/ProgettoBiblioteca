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
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import MODEL.A_MANOSCRITTO_0_Manoscritto;
import VIEW._FINITO_sezioneAmministratoreVIEW;
import VIEW._FINITO_sezioneUploaderVIEW;

public class _FINITO_UploaderReuploadGUI extends JFrame
{
	// ---------------VARIBILI----------------//
	GridLayout								griglia	= new GridLayout(1, 1);
	ArrayList<A_MANOSCRITTO_0_Manoscritto>	listaManoscritti;
	String									nick;

	// ---------------COSTRUTTORE-------------//

	public _FINITO_UploaderReuploadGUI(String nick_uploader)
	{
		this.nick = nick_uploader;

		inizializzaFrame();
		inizilizzaInterfaccia();
		setVisible(true);
	}

	// ---------------METODI-------------------//

	private void inizializzaFrame()
	{
		this.setTitle("SEZIONE UPLOAD ALTRE IMMAGINI");
		this.setSize(1100, 500);
		this.setLocation(0, 0);
		this.setMinimumSize(new Dimension(1100, 500));
		this.setLayout(griglia);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void inizilizzaInterfaccia()
	{
		// acquisizione della lista di manoscritti
		listaManoscritti = new _FINITO_sezioneAmministratoreVIEW().getListaManoscritti();
		System.out.println(listaManoscritti);

		// impostazioni del pannello
		pannelloOpera pannello = new pannelloOpera();
		JScrollPane scrollBar = new JScrollPane(pannello);
		this.add(scrollBar);
	}

	public void chiudiFrame()
	{
		this.dispose();
	}

	// --------------CLASSI COMPONENTI--------//

	private class pannelloOpera extends JPanel
	{
		// VARIABILI E COMPONENTI
		private pannelloOpera soggetto = this;

		// LAYOUT
		private BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);

		// COSTRUTTORE
		public pannelloOpera()
		{
			int numManoscritti = listaManoscritti.size();
			setLayout(box);

			for (int i = 0; i < numManoscritti; i++)
			{
				A_MANOSCRITTO_0_Manoscritto Manoscritto = listaManoscritti.get(i);
				String pathManoscritto = Manoscritto.getPathImmagineCopertina();
				String titoloManoscritto = Manoscritto.getTitolo();

				elementoOpera manoscritto = new elementoOpera(pathManoscritto, titoloManoscritto);

				add(manoscritto);
			}
		}

	}

	private class elementoOpera extends JPanel
	{
		// ELEMENTI
		String									vectPath[]	= {};
		Border									bordo		= BorderFactory.createLineBorder(Color.BLACK, 3);
		_FINITO_AmministratoreEliminaOpereGUI	soggetto;

		String	path;
		String	titolo;

		JButton aggiungiPagine = new JButton("AGGIUNGI IMMAGINI");

		// LAYOUT
		GridBagLayout		grid	= new GridBagLayout();
		GridBagConstraints	ca		= new GridBagConstraints();

		// COSTRUTTORE
		public elementoOpera(String pathCopertina, String titoloManoscritto)
		{
			// -------settaggio elementi--------------------//

			path = pathCopertina;
			titolo = titoloManoscritto;

			// --------------RIDIMENSIONAMENTO IMMAGINE----------------------//

			ImageIcon immagineIcona = new ImageIcon(path);
			Image immagine = immagineIcona.getImage();
			Image nuovaImmagine = immagine.getScaledInstance(200, 300, java.awt.Image.SCALE_SMOOTH);
			immagineIcona = new ImageIcon(nuovaImmagine);

			// --------------------------------------------------------------//

			JLabel copertina = new JLabel(immagineIcona);
			copertina.setOpaque(true);

			JLabel nomeManoscritto = new JLabel("TITOLO MANOSCRITTO: " + titolo, SwingConstants.CENTER);

			// ---------------------------------------------//

			setLayout(grid);

			ca.weightx = 1;
			ca.weighty = 1;

			ca.ipady = 25;

			ca.insets.left = 10;
			ca.insets.right = 10;
			ca.insets.bottom = 10;
			ca.insets.top = 10;

			// --------COPERTINA----------------------//


			ca.ipady = 25;
			ca.ipadx = 100;
			copertina.setOpaque(true);
			set(ca, 0, 0, 1, 1);
			add(copertina, ca);
			ca.ipady = 25;
			ca.ipadx = 0;
			// ---------------------------------------//

			set(ca, 1, 0, 1, 1);
			add(nomeManoscritto, ca);

			ca.ipadx = 60;
			ca.insets.right = 10;

			set(ca, 2, 0, 1, 1);
			add(aggiungiPagine, ca);

			// ---------BORDO--------------------------//

			setBorder(bordo);

			// ---------ACTION LISTNER-----------------//

			inizializzaActionListner();
		}

		// METODI
		private void inizializzaActionListner()
		{
			aggiungiPagine.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					new _FINITO_sezioneUploaderVIEW().premutoAggiungiPagine(titolo, nick);
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

	// -----------------------------------------------------------------------------------------------------//
	//
	//
	//
	//
	//
	// ------------MAIN-------------------------------//




	public static void main(String[] args)
	{
		_FINITO_UploaderReuploadGUI reap = new _FINITO_UploaderReuploadGUI(null);
	}

}
