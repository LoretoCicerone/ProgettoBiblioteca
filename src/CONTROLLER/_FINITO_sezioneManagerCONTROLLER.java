package CONTROLLER;

import java.util.ArrayList;

import DAO._FINITO_sezioneAmministratoreDAO;
import DAO._FINITO_sezioneManagerDAO;
import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.A_MANOSCRITTO_1_Pagina;
import MODEL.Utente;

public class _FINITO_sezioneManagerCONTROLLER
{
	// metodo che dato il nickname di un trascrittore ne ricava l'ID
	public int getIdTrascrittore(String nick)
	{
		int id = new _FINITO_sezioneManagerDAO().getIdTrascrittore(nick);

		return id;
	}

	// metodo per ricavarsi la lista di tutti gli utenti trascrittori
	public ArrayList<Utente> getListaTrascrittori()
	{
		ArrayList<Utente> localList = new _FINITO_sezioneManagerDAO().getListaTrascrittori();

		return localList;
	}

	// metodo per ricavarsi la lista di tutti gli utenti trascrittori con anche il dato esperienza
	public ArrayList<Utente> getListaTrascrittoriExp()
	{
		ArrayList<Utente> localList = new _FINITO_sezioneManagerDAO().getListaTrascrittoriExp();

		return localList;
	}

	// metodo che ricava la lista di tutti i trascrittori a cui è stata assegnata l'immagine specificata
	public static ArrayList<Utente> getListaTrascrittoriImmagineAssegnata(int ID_immagine)
	{
		ArrayList<Utente> localList = new _FINITO_sezioneManagerDAO().getListaTrascrittoriImmagineAssegnata(ID_immagine);

		return localList;
	}

	// metodo per ricavarsi la lista di tutti i manoscritti
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscritti()
	{
		ArrayList<A_MANOSCRITTO_0_Manoscritto> localList = new _FINITO_sezioneAmministratoreDAO().getListaManoscritti();

		return localList;
	}

	// metodo per ricavarsi la lista di tutte le pagine di un manoscritto
	public ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagine(int ID)
	{
		ArrayList<A_MANOSCRITTO_1_Pagina> localList = new _FINITO_sezioneManagerDAO().getListaPagine(ID);

		return localList;
	}

	// metodo per ricavarsi la lista di tutte le pagine accettate di un manoscritto dato il suo titolo
	public ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineACCETTATE(String titolo)
	{
		ArrayList<A_MANOSCRITTO_1_Pagina> localList = new _FINITO_sezioneManagerDAO().getListaPagineACCETTATE(titolo);

		return localList;
	}

	// metodo che restituisce una lista di tutte le pagine di un manoscritto con la trascrizione, per poter revisionare tale trascrizione
	public ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineConTrascrizioniDaRevisionare(int ID_manoscritto)
	{
		ArrayList<A_MANOSCRITTO_1_Pagina> localList = new _FINITO_sezioneManagerDAO().getListaPagineConTrascrizioniDaRevisionare(ID_manoscritto);

		return localList;
	}

	// metodo che mi da lo stato della trascrizione che gli passo tramite id
	public String getStatoTrascrizione(int ID_trascrizione)
	{
		String stato = new _FINITO_sezioneManagerDAO().getStatoTrascrizione(ID_trascrizione);

		return stato;
	}

	// metodo che modifica lo stato dei una pagina in base all'input
	public boolean modificaStatoPagina(int idImmagine, int idManager, int scelta)
	{
		boolean risposta = new _FINITO_sezioneManagerDAO().modificaStatoPagina(idImmagine, idManager, scelta);

		return risposta;
	}

	// metodo che si attiva quando si sceglie un trascrittore per assegnarli un'immagine e si preme su assegna
	public boolean premutoAssegna(int ID_immagine, int ID_trascrittore)
	{
		boolean risposta = new _FINITO_sezioneManagerDAO().premutoAssegna(ID_immagine, ID_trascrittore);

		return risposta;
	}

	// metodo che si attiva quando si preme accetta nella sezione revisione trascrizioni
	public boolean premutoAccettaTrascrizione(int idTrascrizione, int idManager, int scelta)
	{
		boolean esito = new _FINITO_sezioneManagerDAO().premutoAccettaTrascrizione(idTrascrizione, idManager, scelta);

		return esito;
	}

	// metodo che si attiva quando si preme rifiuta nella sezione revisione trascrizioni
	public boolean premutoRifiutaTrascrizione(int idTrascrizione, int idManager)
	{
		boolean esito = new _FINITO_sezioneManagerDAO().premutoRifiutaTrascrizione(idTrascrizione, idManager);

		return esito;
	}

	// metodo che si attiva quando si sceglie un nuovo trascrittore a cui riassegnare l'immagine da trascrivere
	public boolean riassegnaImmagineTrascrittore(int ID_immagine, int ID_trascrittore)
	{
		boolean esito = new _FINITO_sezioneManagerDAO().riassegnaImmagineTrascrittore(ID_immagine, ID_trascrittore);

		return esito;
	}

	// metodo che si attiva quando si preme salva dal TEI, aggiorna la trascrizione gia esistente
	public boolean premutoSalvaTEI(int idTrascrizione, String testo)
	{
		boolean esito = new _FINITO_sezioneManagerDAO().premutoSalvaTEI(idTrascrizione, testo);

		return esito;
	}

	// metodo che aumenta di un livello il trascrittore specificato
	public boolean aumentaLivelloTrascrittore(int ID_trascrittore, int livelloTrascrittore)
	{
		boolean esito = new _FINITO_sezioneManagerDAO().aumentaLivelloTrascrittore(ID_trascrittore, livelloTrascrittore);

		return esito;
	}



}
