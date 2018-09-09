package CONTROLLER;

import java.util.ArrayList;

import DAO.funzionalitaVarieDAO;
import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.A_MANOSCRITTO_1_Pagina;

public class funzionalitaVarieCONTROLLER
{
	// --------------------RICERCA OPERA-------------------------------//

	// metodo che restituisce un entita manoscritto quando dal pannello Ricerca Opera, per poterlo poi visualizzarlo
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> premutoCerca(String titolo, String genere, String autore)
	{
		ArrayList<A_MANOSCRITTO_0_Manoscritto> operaCercata = new funzionalitaVarieDAO().premutoCerca(titolo, genere, autore);

		return operaCercata;
	}

	// metodo ch si prende la lista di tutte le pagine di un manoscritto
	public ArrayList<A_MANOSCRITTO_1_Pagina> getPagineManoscritto(int ID_manoscritto)
	{
		ArrayList<A_MANOSCRITTO_1_Pagina> risultato = new funzionalitaVarieDAO().getPagineManoscritto(ID_manoscritto);


		return risultato;
	}

	// metodo che traccia il fatto che un utente ha visualizzato un manoscritto
	public boolean inserisciConsultazione(int ID_utente, int ID_manoscritto)
	{
		boolean esito = new funzionalitaVarieDAO().inserisciConsultazione(ID_utente, ID_manoscritto);

		return esito;
	}

	// metodo che inserisce nella tabella download l'avvenuto download
	public boolean inserisciDownload(int ID_utente, int ID_immagine)
	{
		boolean esito = new funzionalitaVarieDAO().inserisciDownload(ID_utente, ID_immagine);

		return esito;
	}

	// -----------------------HOME PAGE-------------------------------------//

	// metodo che rida la lista di tutti i manoscritti
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscritti()
	{
		ArrayList<A_MANOSCRITTO_0_Manoscritto> risultato = new funzionalitaVarieDAO().getListaManoscritti();

		return risultato;
	}

}
