package DAO;

import java.util.ArrayList;

import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.A_MANOSCRITTO_1_Pagina;
import MODEL.Utente;

public interface _FINITO_INTERFACE_sezioneManager
{
	// metodo per ricavarsi la lista di tutti i manoscritti
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscritti();

	// metodo per ricavarsi la lista di tutte le pagine di un manoscritto
	public ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagine(int ID);

	// metodo che modifica lo stato dei una pagina in base all'input
	public boolean modificaStatoPagina(int idImmagine, int idManager, int scelta);

	// metodo che dato il nickname di un trascrittore ne ricava l'ID
	public int getIdTrascrittore(String nick);

	// metodo che ricava la lista di tutti i trascrittori a cui è stata assegnata l'immagine specificata
	public ArrayList<Utente> getListaTrascrittoriImmagineAssegnata(int ID_immagine);

	// metodo per ricavarsi la lista di tutti gli utenti trascrittori
	public ArrayList<Utente> getListaTrascrittori();

	// metodo per ricavarsi la lista di tutti gli utenti trascrittori con anche il dato esperienza
	public ArrayList<Utente> getListaTrascrittoriExp();

	// metodo per ricavarsi la lista di tutte le pagine accettate di un manoscritto dato il suo titolo
	public ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineACCETTATE(String titolo);

	// metodo che restituisce una lista di tutte le pagine di un manoscritto con la trascrizione, per poter revisionare tale trascrizione
	public ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineConTrascrizioniDaRevisionare(int ID_manoscritto);

	// metodo che mi da lo stato della trascrizione che gli passo tramite id
	public String getStatoTrascrizione(int ID_trascrizione);

	// metodo che si attiva quando si sceglie un trascrittore per assegnarli un'immagine e si preme su assegna
	public boolean premutoAssegna(int ID_immagine, int ID_trascrittore);

	// metodo che si attiva quando si preme accetta nella sezione revisione trascrizioni
	public boolean premutoAccettaTrascrizione(int idTrascrizione, int idManager, int scelta);

	// metodo che si attiva quando si preme rifiuta nella sezione revisione trascrizioni
	public boolean premutoRifiutaTrascrizione(int idTrascrizione, int idManager);

	// metodo che si attiva quando si sceglie un nuovo trascrittore a cui riassegnare l'immagine da trascrivere
	public boolean riassegnaImmagineTrascrittore(int ID_immagine, int ID_trascrittore);

	// metodo che si attiva quando si preme salva dal TEI, aggiorna la trascrizione gia esistente
	public boolean premutoSalvaTEI(int idTrascrizione, String testo);

	// metodo che aumenta di un livello il trascrittore specificato
	public boolean aumentaLivelloTrascrittore(int ID_trascrittore, int livelloTrascrittore);

}
