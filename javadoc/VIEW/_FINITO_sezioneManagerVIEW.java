package VIEW;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import CONTROLLER._FINITO_sezioneManagerCONTROLLER;
import GUI.ManagerWorkbenchGUI;
import GUI._FINITO_APPOGGIO_elencoTrascrittoriFinestra;
import GUI._FINITO_APPOGGIO_finestraAnteprimaImmagine;
import GUI._FINITO_APPOGGIO_popupOk;
import GUI._FINITO_APPOGGIO_popupOkConArray;
import GUI._FINITO_APPOGGIO_popupSiNo;
import GUI._FINITO_ManagerAssegnaImmaginiGUI;
import GUI._FINITO_ManagerRevisioneImmaginiGUI;
import GUI._FINITO_ManagerRevisioneTrascrizioniGUI;
import GUI._FINITO_funzione_ricercaOpera;
import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.A_MANOSCRITTO_1_Pagina;
import MODEL.Utente;

public class _FINITO_sezioneManagerVIEW
{
	// -----------------------METODI-------------------------------------------------------//


	// metodo che dato il nickname di un trascrittore ne ricava l'ID
	public int getIdTrascrittore(String nick)
	{
		int id = new _FINITO_sezioneManagerCONTROLLER().getIdTrascrittore(nick);

		return id;
	}

	// metodo per ricavarsi la lista di tutti gli utenti trascrittori
	public ArrayList<Utente> getListaTrascrittori()
	{
		ArrayList<Utente> localTrascrittori = new _FINITO_sezioneManagerCONTROLLER().getListaTrascrittori();

		return localTrascrittori;
	}

	// metodo per ricavarsi la lista di tutti gli utenti trascrittori con anche il dato esperienza
	public ArrayList<Utente> getListaTrascrittoriExp()
	{
		ArrayList<Utente> localList = new _FINITO_sezioneManagerCONTROLLER().getListaTrascrittoriExp();

		return localList;
	}

	// metodo per ricavarsi la lista di tutti i manoscritti
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscritti()
	{
		ArrayList<A_MANOSCRITTO_0_Manoscritto> localManoscritti = new _FINITO_sezioneManagerCONTROLLER().getListaManoscritti();

		return localManoscritti;
	}

	// metodo che dato l'ID di un Manoscritto ritorna una lista di tutte le pagine di quel manoscritto
	public ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineManoscrittoTUTTE(int ID)
	{
		ArrayList<A_MANOSCRITTO_0_Manoscritto> listaManoscritti = getListaManoscritti();

		ArrayList<A_MANOSCRITTO_1_Pagina> localTUTTE = null;

		for (int i = 0; i < listaManoscritti.size(); i++)
		{
			if (listaManoscritti.get(i).getID() == ID)
			{
				localTUTTE = new _FINITO_sezioneManagerCONTROLLER().getListaPagine(ID);
				break;
			}
		}


		return localTUTTE;
	}

	// metodo che ritorna una lista delle pagine ACCETTATE di tutti i manoscritti
	public ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineACCETTATE(String titoloManoscritto)
	{
		ArrayList<A_MANOSCRITTO_1_Pagina> localACCETTATE = new _FINITO_sezioneManagerCONTROLLER().getListaPagineACCETTATE(titoloManoscritto);

		return localACCETTATE;
	}

	// metodo che ritorna una lista delle pagine IN ATTESA di tutti i manoscritti
	public ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineATTESA()
	{
		ArrayList<A_MANOSCRITTO_0_Manoscritto> listaManoscritti = getListaManoscritti();
		ArrayList<A_MANOSCRITTO_1_Pagina> localATTESA = new ArrayList();

		return localATTESA;
	}

	// metodo che ritorna una lista delle pagine RIFIUTATE di tutti i manoscritti
	public ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineRIFIUTATE()
	{
		ArrayList<A_MANOSCRITTO_0_Manoscritto> listaManoscritti = getListaManoscritti();
		ArrayList<A_MANOSCRITTO_1_Pagina> localRIFIUTATE = new ArrayList();


		return localRIFIUTATE;
	}

	// metodo che restituisce una lista di tutte le pagine di un manoscritto con la trascrizione, per poter revisionare tale trascrizione
	public ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineConTrascrizioniDaRevisionare(int ID_manoscritto)
	{
		ArrayList<A_MANOSCRITTO_1_Pagina> localRevisione = new _FINITO_sezioneManagerCONTROLLER().getListaPagineConTrascrizioniDaRevisionare(ID_manoscritto);

		return localRevisione;
	}

	// metodo che restituisce un array di stringhe contenenti tutti i titoli dei manoscritti presenti nel DB utili alla GUI
	public String[] getTitoliManoscritti()
	{
		ArrayList<A_MANOSCRITTO_0_Manoscritto> listaManoscritti = getListaManoscritti();
		String titoliManoscritti[] = new String[listaManoscritti.size()];

		for (int i = 0; i < listaManoscritti.size(); i++)
		{
			titoliManoscritti[i] = listaManoscritti.get(i).getTitolo();
		}

		return titoliManoscritti;
	}

	// metodo che mi da lo stato della trascrizione che gli passo tramite id
	public String getStatoTrascrizione(int ID_trascrizione)
	{
		String stato = new _FINITO_sezioneManagerCONTROLLER().getStatoTrascrizione(ID_trascrizione);

		return stato;
	}


	// ------------------------------------------------------------------------------------//


	// metodo che mostra l'anteprima dell'immagine della pagina, si attiva quando si preme il bottone anteprima nella sezione revisiona immagini
	public void premutoAnteprimaImmagine(String pathImmagine)
	{
		new _FINITO_APPOGGIO_finestraAnteprimaImmagine(pathImmagine);
	}

	// metodo che si attiva quando si preme accetta nella sezione revisiona immagini
	public boolean premutoAccettaImmagine(int ID_pagina, int ID_manager)
	{
		boolean esito = new _FINITO_sezioneManagerCONTROLLER().modificaStatoPagina(ID_pagina, ID_manager, 1);
		if (esito)
		{
			new _FINITO_APPOGGIO_popupOk("MODIFICA COMPLETATA", "L'immagine è stata registrata come ACCETTATA");
		}
		else
		{
			new _FINITO_APPOGGIO_popupOk("MODIFICA FALLITA", "IMPOSSIBILE accettare questa immagine");
		}
		return esito;
	}

	// metodo che si attiva quando si preme rifiuta nella sezione revisiona immagini
	public boolean premutoRifiutaImmagine(int ID_pagina, int ID_manager)
	{
		boolean esito = new _FINITO_sezioneManagerCONTROLLER().modificaStatoPagina(ID_pagina, ID_manager, 0);
		if (esito)
		{
			// popup con la conferma
			new _FINITO_APPOGGIO_popupOk("MODIFICA COMPLETATA", "L'immagine è stata registrata come RIFIUTATA");
		}
		else
		{
			// popup con l'errore
			new _FINITO_APPOGGIO_popupOk("MODIFICA FALLITA", "IMPOSSIBILE rifiutare questa immagine");
		}
		return esito;
	}

	// metodo che si attiva quando si sceglie un trascrittore per assegnarli un'immagine e si preme su assegna nella sezione revisiona immagini
	public void premutoAssegnaImmagine(int ID_immagine, int ID_trascrittore)
	{
		boolean esito = new _FINITO_sezioneManagerCONTROLLER().premutoAssegna(ID_immagine, ID_trascrittore);

		if (esito)
		{
			new _FINITO_APPOGGIO_popupOk("ASSEGNAZIONE COMPLETATA", "L'immagine è stata assegnata con successo");
		}
		else
		{
			new _FINITO_APPOGGIO_popupOk("ASSEGNAZIONE FALLITA", "errore nell'assegnare l'immagine al trascrittore");
		}
	}

	// metodo che si attiva quando si preme il bottone info assegnazioni e mostra tutti i trascrittori a cui è assegnata quell'immagine nella sezione revisiona immagini
	public void premutoInfoImmagine(int ID_immagine)
	{
		ArrayList<String> unici = new ArrayList<String>();
		ArrayList<Utente> localListTrascrittori = new _FINITO_sezioneManagerCONTROLLER().getListaTrascrittoriImmagineAssegnata(ID_immagine);


		if (localListTrascrittori.isEmpty() == false)
		{
			// metto il primo elemento nell'insieme unici, dato che è il primo è per forza non presente nell'inisieme unici
			unici.add(localListTrascrittori.get(0).getNickname());

			// adesso mi scorro tutti gli elementi dell'insieme iniziale e vedo se sono presenti anche in unici, se sono presenti non li considero, altrimenti li metto in unici
			for (int i = 1; i < localListTrascrittori.size(); i++)
			{
				String elementoSelezionato = localListTrascrittori.get(i).getNickname();
				boolean doppione = false;

				for (int z = 0; z < unici.size(); z++)
				{
					if (elementoSelezionato.equals(unici.get(z)))
					{
						doppione = true;
						break;
					}
				}

				if (doppione == false)
				{
					unici.add(elementoSelezionato);
				}

			}

			new _FINITO_APPOGGIO_popupOkConArray("INFO TRASCRITTORI INCARICATI", "I trascrittori a cui è stata assegnata questa immagine sono: ", unici);

		}
		else
		{
			new _FINITO_APPOGGIO_popupOk("INFO TRASCRITTORI INCARICATI", "A questa immagine non sono stati assegnati trascrittori!");
		}

	}

	// metodo che si attiva quando si preme accetta nella sezione revisione trascrizioni
	public boolean premutoAccettaTrascrizione(int idTrascrizione, int idManager)
	{
		boolean esito = new _FINITO_sezioneManagerCONTROLLER().premutoAccettaTrascrizione(idTrascrizione, idManager, 1);

		if (esito)
		{
			new _FINITO_APPOGGIO_popupOk("CONFERMATE MODIFICHE", "La trascrizione è stata accettata con successo!");
			return true;
		}
		else
		{
			new _FINITO_APPOGGIO_popupOk("MODIFICA FALLITA", "La trascrizione non è stata accettata, errore nel sistema, riprova!");
			return false;
		}
	}

	// metodo che si attiva quando si preme rifiuta nella sezione revisione trascrizioni
	public boolean premutoRifiutaTrascrizione(int idTrascrizione, int idManager)
	{
		boolean esito = new _FINITO_sezioneManagerCONTROLLER().premutoRifiutaTrascrizione(idTrascrizione, idManager);

		if (esito)
		{
			new _FINITO_APPOGGIO_popupOk("CONFERMATE MODIFICHE", "La trascrizione è stata rifiutata con successo!");
			return true;
		}
		else
		{
			new _FINITO_APPOGGIO_popupOk("MODIFICA FALLITA", "La trascrizione non è stata rifiutata, errore nel sistema, riprova!");
			return false;
		}
	}

	// metodo che si attiva quando si preme revisiona nella sezione revisione trascrizioni
	public void premutoRevisionaTrascrizione(JTextArea txtOutput, JTextArea txtInput, int ID_trascrizione)
	{
		String testoTrascrizione = txtOutput.getText();
		txtInput.setText(testoTrascrizione);
	}

	// metodo che si attiva quando si preme riassegna nella sezione revisione trascrizioni
	public void premutoRiassegnaTrascrizione(int ID_immagineDaRiassegnare)
	{
		new _FINITO_APPOGGIO_elencoTrascrittoriFinestra(ID_immagineDaRiassegnare);
	}

	// metodo che si attiva quando dal TEI clicco su cancella
	public void premutoCancellaTEI(JTextArea areaTesto, JTabbedPane tabbedPane, int totTab)
	{
		_FINITO_APPOGGIO_popupSiNo popup = new _FINITO_APPOGGIO_popupSiNo("CONFERMA USCITA", "ATTENZIONE premendo cancella perderai tutte le modifiche appena fatte!");
		int n = popup.getValore();

		if (n == 0)
		{
			// premuto si

			areaTesto.setText("");
			areaTesto.setEditable(false);

			// rendo editabili tutti i tab
			for (int i = 0; i < totTab; i++)
			{
				tabbedPane.setEnabledAt(i, true);
			}

		}
	}

	// metodo che si attiva quando si preme salva dal TEI, aggiorna la trascrizione gia esistente
	public boolean premutoSalvaTEI(int idTrascrizione, String testo, JTextArea txtInput, JTabbedPane tabbedPane, int totTab)
	{
		boolean esito = new _FINITO_sezioneManagerCONTROLLER().premutoSalvaTEI(idTrascrizione, testo);

		if (esito)
		{
			new _FINITO_APPOGGIO_popupOk("CONFERMATE MODIFICHE", "La trascrizione è stata modificata con successo!");

			txtInput.setText("");
			txtInput.setEditable(false);
			// rendo editabili tutti i tab
			for (int i = 0; i < totTab; i++)
			{
				tabbedPane.setEnabledAt(i, true);
			}

			return true;
		}
		else
		{
			new _FINITO_APPOGGIO_popupOk("MODIFICA FALLITA", "La trascrizione non è stata modificata, errore nel sistema, riprova!");
			return false;
		}
	}

	// metodo che si attiva quando dalla sezione workbenchManager premo
	public void premutoRicercaOpera(int ID)
	{
		new _FINITO_funzione_ricercaOpera(ID);
	}

	// metodo che si attiva quando dalla sezione workbenchManager premo
	public void premutoRevisioneImmagini(int ID_manager)
	{
		new _FINITO_ManagerRevisioneImmaginiGUI(ID_manager);
	}

	// metodo che si attiva quando dalla sezione workbenchManager premo
	public void premutoRevisioneTrascrizioni(String nick)
	{
		new _FINITO_ManagerRevisioneTrascrizioniGUI(nick);
	}

	// metodo che si attiva quando dalla sezione workbenchManager premo
	public void premutoAssegnaImmagini()
	{
		new _FINITO_ManagerAssegnaImmaginiGUI();
	}


	// ------------------------------------------------------------------------------------//


	// metodo che si attiva quando si sceglie un nuovo trascrittore a cui riassegnare l'immagine da trascrivere
	public void riassegnaImmagineTrascrittore(int ID_immagine, int ID_trascrittore)
	{
		boolean esito = new _FINITO_sezioneManagerCONTROLLER().riassegnaImmagineTrascrittore(ID_immagine, ID_trascrittore);

		if (esito)
		{
			new _FINITO_APPOGGIO_popupOk("CONFERMATA RIASSEGNAZIONE", "La trascrizione è stata riassegnata con successo!");
		}
		else
		{
			new _FINITO_APPOGGIO_popupOk("CONFERMATA RIASSEGNAZIONE", "La trascrizione NON è stata riassegnata con successo, riprova");
		}
	}

	// metodo che aumenta di un livello il trascrittore specificato
	public boolean aumentaLivelloTrascrittore(int ID_trascrittore, int livelloTrascrittore, JFrame soggettoRefresh, String nick)
	{
		int LivelloAttuale = livelloTrascrittore;
		int nuovoLivello = LivelloAttuale + 1;

		boolean esito = new _FINITO_sezioneManagerCONTROLLER().aumentaLivelloTrascrittore(ID_trascrittore, nuovoLivello);

		if (esito)
		{
			new _FINITO_APPOGGIO_popupOk("AUMENTO DI LIVELLO", "Aumentato livello al trascrittore correttamente!");

			soggettoRefresh.dispose();
			new ManagerWorkbenchGUI(nick);

			return true;
		}
		else
		{
			new _FINITO_APPOGGIO_popupOk("ERRORE 	AUMENTO DI LIVELLO", "Aumentato livello al trascrittore fallito!");

			return false;
		}
	}

}
