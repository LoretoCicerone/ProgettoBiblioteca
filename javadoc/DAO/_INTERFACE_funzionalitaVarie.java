package DAO;

import java.util.ArrayList;

import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.A_MANOSCRITTO_1_Pagina;

public interface _INTERFACE_funzionalitaVarie
{
	// --------------------RICERCA OPERA-------------------------------//

	// metodo che restituisce un entita manoscritto quando dal pannello Ricerca Opera, per poterlo poi visualizzarlo
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> premutoCerca(String titolo, String genere, String autore);

	// metodo ch si prende la lista di tutte le pagine di un manoscritto per la sezione visualizza opera
	public ArrayList<A_MANOSCRITTO_1_Pagina> getPagineManoscritto(int ID_manoscritto);

	// metodo che traccia il fatto che un utente ha visualizzato un manoscritto
	public boolean inserisciConsultazione(int ID_utente, int ID_manoscritto);

	// metodo che inserisce nella tabella download l'avvenuto download
	public boolean inserisciDownload(int ID_utente, int ID_immagine);

	// ---------------------HOME PAGE----------------------------------//

	// metodo che rida la lista di tutti i manoscritti
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscritti();





}
