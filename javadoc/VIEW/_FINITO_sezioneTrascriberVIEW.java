package VIEW;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import CONTROLLER._FINITO_sezioneTrascriberCONTROLLER;
import GUI._FINITO_APPOGGIO_popupOk;
import GUI._FINITO_APPOGGIO_popupSiNo;
import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.A_MANOSCRITTO_1_Pagina;

public class _FINITO_sezioneTrascriberVIEW
{
	public static int	ID_manoscrittoCorrente;
	public static int	ID_immagineCorrente;


	// metodo che dato l'ID del trascrittore e l'ID del manoscritto restituisce una lista di tutte le pagine di quel manoscritto assegnate al trascrittore
	public ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineDelManoscrittoAssegnate(int ID_trascrittore, int ID_manoscritto)
	{
		ArrayList<A_MANOSCRITTO_1_Pagina> localPagine = new _FINITO_sezioneTrascriberCONTROLLER().getListaPagineDelManoscrittoAssegnate(ID_trascrittore, ID_manoscritto);

		return localPagine;
	}

	// metodo che data una lista di pagine restituisce una lista di manoscritti che contengono almeno una delle pagine nella lista data
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscrittiAssegnatiTrascrittore(int ID_trascrittore)
	{
		ArrayList<A_MANOSCRITTO_0_Manoscritto> localManoscritti = new _FINITO_sezioneTrascriberCONTROLLER().getListaManoscrittiAssegnatiTrascrittore(ID_trascrittore);

		return localManoscritti;
	}

	// metodo che dato il nickname restituisce l'ID
	public int getID(String nick)
	{
		int ID = new _FINITO_sezioneTrascriberCONTROLLER().getID(nick);

		return ID;
	}

	// metodo che si attiva quando si preme aumenta carattere dal TEI
	public int premutoAumenta(JTextArea soggetto, int grandezzaFontAttuale)
	{
		int nuovaDimensione = grandezzaFontAttuale;
		soggetto.setFont(new Font("sansserif", 0, nuovaDimensione = nuovaDimensione + 1));

		return nuovaDimensione;
	}

	// metodo che si attiva quando si preme diminuisci carattere dal TEI
	public int premutoDiminuisci(JTextArea soggetto, int grandezzaFontAttuale)
	{
		int nuovaDimensione = grandezzaFontAttuale;
		soggetto.setFont(new Font("sansserif", 0, nuovaDimensione = nuovaDimensione - 1));

		return nuovaDimensione;
	}

	// metodo che si attiva quando si preme grassetto carattere dal TEI
	public void premutoGrassetto(JTextArea soggetto, int grandezzaFontAttuale)
	{
		soggetto.setFont(new Font("sansserif", Font.BOLD, grandezzaFontAttuale));
	}

	// metodo che si attiva quando si preme corsivo carattere dal TEI
	public void premutoCorsivo(JTextArea soggetto, int grandezzaFontAttuale)
	{
		soggetto.setFont(new Font("sansserif", Font.ITALIC, grandezzaFontAttuale));
	}

	// metodo che si attiva quando si preme sull'immagine per modificarla
	public void premutaImmagine(JTextArea areaTesto, int paneCorrente, JTabbedPane tabbedPane, int totTab, JScrollPane scrollBar, int idImmagine, int idManoscritto)
	{
		_FINITO_APPOGGIO_popupSiNo popup = new _FINITO_APPOGGIO_popupSiNo("ATTENZIONE", "Vuoi trascrivere la pagina selezionata?");
		int n = popup.getValore();

		// 0 = si 1 = no
		if (n == 0)
		{
			System.out.println("premuto si");
			ID_manoscrittoCorrente = idManoscritto;
			ID_immagineCorrente = idImmagine;
			areaTesto.setEditable(true);

			// rendo non editabili gli altri tab
			for (int i = 0; i < totTab; i++)
			{
				if (i != paneCorrente)
				{
					tabbedPane.setEnabledAt(i, false);
				}
			}

		}
		else
		{
			System.out.println("premuto no");
		}
	}

	// metodo che si attiva quando si preme cancella dal TEI
	public void premutoCancella(JTextArea areaTesto, JTabbedPane tabbedPane, int totTab)
	{
		_FINITO_APPOGGIO_popupSiNo popup = new _FINITO_APPOGGIO_popupSiNo("CONFERMA USCITA", "ATTENZIONE sei sicuro di voler tornare alla selezione delle immagini?");
		int n = popup.getValore();

		if (n == 0)
		{
			System.out.println("premuto si");
			areaTesto.setEditable(true);

			// rendo editabili tutti i tab
			for (int i = 0; i < totTab; i++)
			{
				tabbedPane.setEnabledAt(i, true);
			}

		}
		else
		{
			System.out.println("premuto no");
		}

	}

	// metodo che si attiva quando si preme salva dal TEI
	public void premutoSalva(String testo, JTextArea txtInput, JTabbedPane tabbedPane, int totTab)
	{
		boolean esito = new _FINITO_sezioneTrascriberCONTROLLER().inserimentoTrascrizione(testo, ID_manoscrittoCorrente, ID_immagineCorrente);

		if (esito)
		{
			new _FINITO_APPOGGIO_popupOk("SALVATAGGIO RIUSCITO!", "Salvataggio andato a buon fine");

			txtInput.setText("");
			txtInput.setEditable(false);
			// rendo editabili tutti i tab
			for (int i = 0; i < totTab; i++)
			{
				tabbedPane.setEnabledAt(i, true);
			}
		}
		else
		{
			new _FINITO_APPOGGIO_popupOk("SALVATAGGIO FALLITO!", "Errore nel salvataggio di questa trascrizione");
		}
	}



}
