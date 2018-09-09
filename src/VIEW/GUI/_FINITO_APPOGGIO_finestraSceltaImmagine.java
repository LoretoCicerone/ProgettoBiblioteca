package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import VIEW._FINITO_profiloUtenteVIEW;

public class _FINITO_APPOGGIO_finestraSceltaImmagine extends JFrame
{
	// -----------------VARIABILI--------------------------------------------------------------------------//
	JFrame				finestra	= this;
	_FINITO_UtenteProfiloGUI	soggetto;
	String				nickname;
	String				immagineSelezionata;

	JButton	bottone1	= new JButton(new ImageIcon("./res/immagini profilo/captain america 200x300.jpg"));
	JButton	bottone2	= new JButton(new ImageIcon("./res/immagini profilo/daredevil 200x300.jpg"));
	JButton	bottone3	= new JButton(new ImageIcon("./res/immagini profilo/deadpool 200x300.jpg"));
	JButton	bottone4	= new JButton(new ImageIcon("./res/immagini profilo/dottor strange 200x300.jpg"));
	JButton	bottone5	= new JButton(new ImageIcon("./res/immagini profilo/hulk 200x300.jpg"));
	JButton	bottone6	= new JButton(new ImageIcon("./res/immagini profilo/infinity 200x300.jpg"));
	JButton	bottone7	= new JButton(new ImageIcon("./res/immagini profilo/iron fist 200x300.jpg"));
	JButton	bottone8	= new JButton(new ImageIcon("./res/immagini profilo/iron man 200x300.jpg"));
	JButton	bottone9	= new JButton(new ImageIcon("./res/immagini profilo/thor 200x300.png"));

	JPanel		pannello	= new JPanel();
	JScrollPane	scrollBar	= new JScrollPane(pannello);


	// ----------------COSTRUTTORE-------------------------------------------------------------------------//

	public _FINITO_APPOGGIO_finestraSceltaImmagine(_FINITO_UtenteProfiloGUI soggetto, String nick)
	{
		this.nickname = nick;
		this.soggetto = soggetto;
		inizializzaFrame();
		inizializzaInterfaccia();
		inizializzaActionListner();
	}

	// ------------------METODI----------------------------------------------------------------------------//

	public void inizializzaFrame()
	{
		this.setTitle("SCEGLI L'IMMAGINE DEL PROFILO");
		this.setSize(750, 500);
		this.setResizable(false);
		this.setLayout(new GridLayout(1, 1));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void inizializzaInterfaccia()
	{
		pannello.setLayout(new GridLayout(3, 3));

		pannello.add(bottone1);
		pannello.add(bottone2);
		pannello.add(bottone3);
		pannello.add(bottone4);
		pannello.add(bottone5);
		pannello.add(bottone6);
		pannello.add(bottone7);
		pannello.add(bottone8);
		pannello.add(bottone9);
		this.add(scrollBar);

		this.setVisible(true);
	}

	public void inizializzaActionListner()
	{
		bottone1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				immagineSelezionata = "./res/immagini profilo/captain america 200x300.jpg";
				new _FINITO_profiloUtenteVIEW().updateImmagine(soggetto, nickname, immagineSelezionata);
				finestra.dispose();
			}
		});

		bottone2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				immagineSelezionata = "./res/immagini profilo/daredevil 200x300.jpg";
				new _FINITO_profiloUtenteVIEW().updateImmagine(soggetto, nickname, immagineSelezionata);
				finestra.dispose();
			}
		});

		bottone3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				immagineSelezionata = "./res/immagini profilo/deadpool 200x300.jpg";
				new _FINITO_profiloUtenteVIEW().updateImmagine(soggetto, nickname, immagineSelezionata);
				finestra.dispose();
			}
		});

		bottone4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				immagineSelezionata = "./res/immagini profilo/dottor strange 200x300.jpg";
				new _FINITO_profiloUtenteVIEW().updateImmagine(soggetto, nickname, immagineSelezionata);
				finestra.dispose();
			}
		});

		bottone5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				immagineSelezionata = "./res/immagini profilo/hulk 200x300.jpg";
				new _FINITO_profiloUtenteVIEW().updateImmagine(soggetto, nickname, immagineSelezionata);
				finestra.dispose();
			}
		});

		bottone6.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				immagineSelezionata = "./res/immagini profilo/infinity 200x300.jpg";
				new _FINITO_profiloUtenteVIEW().updateImmagine(soggetto, nickname, immagineSelezionata);
				finestra.dispose();
			}
		});

		bottone7.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				immagineSelezionata = "./res/immagini profilo/iron fist 200x300.jpg";
				new _FINITO_profiloUtenteVIEW().updateImmagine(soggetto, nickname, immagineSelezionata);
				finestra.dispose();
			}
		});

		bottone8.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				immagineSelezionata = "./res/immagini profilo/iron man 200x300.jpg";
				new _FINITO_profiloUtenteVIEW().updateImmagine(soggetto, nickname, immagineSelezionata);
				finestra.dispose();
			}
		});

		bottone9.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				immagineSelezionata = "./res/immagini profilo/thor 200x300.png";
				new _FINITO_profiloUtenteVIEW().updateImmagine(soggetto, nickname, immagineSelezionata);
				finestra.dispose();
			}
		});
	}

	// ------------------CLASSI COMPONENTI-------------------------------------------------------------------//





	// -----------------------------------------------------------------------------------------------------//
	//
	//
	//
	//
	//
	// ------------MAIN-------------------------------//

	public static void main(String[] args)
	{
		_FINITO_APPOGGIO_finestraSceltaImmagine scegliimmagine = new _FINITO_APPOGGIO_finestraSceltaImmagine(null, "elbow");
	}

}
