package VIEW;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

import CONTROLLER.funzionalitaVarieCONTROLLER;
import GUI._FINITO_APPOGGIO_finestraSceltaOperaDaVisualizzare;
import GUI._FINITO_APPOGGIO_popupAttenzione;
import GUI._FINITO_APPOGGIO_popupOk;
import GUI._FINITO_funzione_loginGUI;
import GUI._FINITO_funzione_registrazioneGUI;
import GUI._FINITO_funzione_visualizzaOpera;
import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.A_MANOSCRITTO_1_Pagina;

public class funzionalitaVarieVIEW
{
	private static A_MANOSCRITTO_0_Manoscritto			manoscrittoInVisualizzazione;
	private static ArrayList<A_MANOSCRITTO_1_Pagina>	pagineDaVisualizzareOperaInVisualizzazione;
	private static int									contatore;
	private static int									ID_utenteInVisualizzazione;

	// --------------------RICERCA OPERA-------------------------------//

	// metodo che fa scorrere il paginatore avanti di una pagina
	public void paginaAvanti(JFrame soggetto)
	{
		int contatorePreModifica = contatore;

		if (contatore == pagineDaVisualizzareOperaInVisualizzazione.size() - 1)
		{
			new _FINITO_APPOGGIO_popupAttenzione("AZIONE NON CONSENTITA", "Ultima pagina, impossibile andare avanti");
		}
		else
		{
			int contatorePiuUno = contatore + 1;
			contatore += 1;

			A_MANOSCRITTO_1_Pagina paginaSuccessiva = pagineDaVisualizzareOperaInVisualizzazione.get(contatorePiuUno);

			soggetto.dispose();
			new _FINITO_funzione_visualizzaOpera(manoscrittoInVisualizzazione, paginaSuccessiva);
		}
	}

	// metodo che fa scorrere il paginatore indietro di una pagina
	public void paginaIndietro(JFrame soggetto)
	{
		int contatorePreModifica = contatore;

		if (contatore == 0)
		{
			new _FINITO_APPOGGIO_popupAttenzione("AZIONE NON CONSENTITA", "Prima pagina, impossibile andare indietro");
		}
		else
		{
			int contatoreMenoUno = contatore - 1;
			contatore -= 1;

			A_MANOSCRITTO_1_Pagina paginaSuccessiva = pagineDaVisualizzareOperaInVisualizzazione.get(contatoreMenoUno);

			soggetto.dispose();
			new _FINITO_funzione_visualizzaOpera(manoscrittoInVisualizzazione, paginaSuccessiva);
		}
	}

	// metodo che restituisce un entita manoscritto quando dal pannello Ricerca Opera, per poterlo poi visualizzarlo
	public void premutoCerca(JTextField titolo, JComboBox genere, JTextField autore, int ID_utente)
	{
		ID_utenteInVisualizzazione = ID_utente;
		String titoloL = titolo.getText();
		String genereL = (String) genere.getSelectedItem();
		String autoreL = autore.getText();

		ArrayList<A_MANOSCRITTO_0_Manoscritto> operaCercata = new funzionalitaVarieCONTROLLER().premutoCerca(titoloL, genereL, autoreL);

		if (operaCercata.isEmpty())
		{
			new _FINITO_APPOGGIO_popupAttenzione("NESSUNA OPERA TROVATA", "Attenzione non esiste nessuna opera con le caratteristiche specificate!");
		}
		else
		{
			new _FINITO_APPOGGIO_popupOk("RICERCA COMPLETATA", "Ricerca andata a buon fine!");
			inizializzaVisioneOpera(operaCercata);
		}
	}

	// metodo che trovata la lista di opere che l'utente ha cercato, mostra i risultati aprendo un nuovo pannello
	public void inizializzaVisioneOpera(ArrayList<A_MANOSCRITTO_0_Manoscritto> risultatoRicerca)
	{
		ArrayList<A_MANOSCRITTO_0_Manoscritto> operaCercata = risultatoRicerca;

		new _FINITO_APPOGGIO_finestraSceltaOperaDaVisualizzare(operaCercata);
	}

	// metodo che cliccata l'opera che si desidera visualizzare, apre il pannello visualiza opera
	public void apriPannelloVisualizzaOpera(A_MANOSCRITTO_0_Manoscritto operaDaVisualizzare)
	{
		// mi procuro la lista delle pagine del manoscritto, da mettere poi nella sezione variabili di questa classe, e lasciarla li fino alla prossima assegnazione
		pagineDaVisualizzareOperaInVisualizzazione = new funzionalitaVarieCONTROLLER().getPagineManoscritto(operaDaVisualizzare.getID());
		manoscrittoInVisualizzazione = operaDaVisualizzare;
		contatore = 0;

		new _FINITO_funzione_visualizzaOpera(operaDaVisualizzare, pagineDaVisualizzareOperaInVisualizzazione.get(0));
		new funzionalitaVarieCONTROLLER().inserisciConsultazione(ID_utenteInVisualizzazione, manoscrittoInVisualizzazione.getID());
	}

	// metodo per scaricare un immagine nel pc dalla sezione visualizza Opera, fa anche in modo che dal database parta una query per registrare il download nel DB
	public void downloadImmagine(int ID_immagine, String pathImmagine)
	{
		if (true)
		{
			new funzionalitaVarieCONTROLLER().inserisciDownload(ID_utenteInVisualizzazione, ID_immagine);

			// -----------creazione dell'immagine dal path----------------------------------------------------------------------------//

			String path = pathImmagine;
			ImageIcon immagine = new ImageIcon(path);
			BufferedImage bi = new BufferedImage(immagine.getIconWidth(), immagine.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics g = bi.createGraphics();
			immagine.paintIcon(null, g, 0, 0);
			g.dispose();

			// ----------scelta path per il download con il jfilechooser----------------------------------------------------------------//

			JFileChooser chooser = new JFileChooser();
			chooser.showSaveDialog(null);
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);

			String percorso = chooser.getSelectedFile().getAbsolutePath();

			// ----------download immagine---------------------------------------------------------------------------------//

			try
			{
				ImageIO.write(bi, "png", new File(percorso + ".png"));
			}
			catch (IOException e)
			{
				System.out.println("ERRORE DOWNLOAD");
				e.printStackTrace();
			}

			// --------------------------------------------------------------------------------------------------------------------------// 
		}
	

	}

	// -----------------------HOME PAGE-------------------------------------//

	// metodo che rida la lista di tutti i manoscritti
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscritti()
	{
		ArrayList<A_MANOSCRITTO_0_Manoscritto> risultato = new funzionalitaVarieCONTROLLER().getListaManoscritti();

		return risultato;
	}

	// metodo che si attiva quando si preme login
	public void premutoLogin()
	{
		new _FINITO_funzione_loginGUI();
	}

	// metodo che si attiva quando si preme registrati
	public void premutoRegistrati()
	{
		new _FINITO_funzione_registrazioneGUI();
	}


}
