package DAO;

import java.util.ArrayList;

import DATABASE.Database;
import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.A_MANOSCRITTO_1_Pagina;

public class funzionalitaVarieDAO implements _INTERFACE_funzionalitaVarie
{
	// --------------------RICERCA OPERA-------------------------------//

	// metodo che restituisce un entita manoscritto quando dal pannello Ricerca Opera, per poterlo poi visualizzarlo
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> premutoCerca(String titolo, String genere, String autore)
	{
		try
		{
			ArrayList<A_MANOSCRITTO_0_Manoscritto> operaCercata = Database.ricercaOperaManoscritto(titolo, genere, autore);

			return operaCercata;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel ricavare l'opera specificata!");
			e.printStackTrace();

			return null;
		}
	}

	// metodo ch si prende la lista di tutte le pagine di un manoscritto per la sezione visualizza opera
	public ArrayList<A_MANOSCRITTO_1_Pagina> getPagineManoscritto(int ID_manoscritto)
	{
		try
		{
			ArrayList<A_MANOSCRITTO_1_Pagina> risultato = Database.getListaPagineDaVisualizzare(ID_manoscritto);

			return risultato;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel ricavare le pagine con trascrizioni e immagini accettate!");
			e.printStackTrace();

			return null;
		}
	}

	// metodo che traccia il fatto che un utente ha visualizzato un manoscritto
	public boolean inserisciConsultazione(int ID_utente, int ID_manoscritto)
	{
		try
		{
			boolean esito = Database.inserisciConsultazione(ID_utente, ID_manoscritto);

			return esito;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel registrare la consultazione del manoscritto di ID: " + ID_manoscritto + " per l'utente di ID: " + ID_utente);
			e.printStackTrace();

			return false;
		}
	}

	// metodo che inserisce nella tabella download l'avvenuto download
	public boolean inserisciDownload(int ID_utente, int ID_immagine)
	{
		try
		{
			boolean esito = Database.inserisciDownload(ID_utente, ID_immagine);

			return esito;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nell'inserire il download nel DB!");
			e.printStackTrace();

			return false;
		}
	}

	// -----------------------HOME PAGE-------------------------------------//

	// metodo che rida la lista di tutti i manoscritti
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscritti()
	{
		try
		{
			ArrayList<A_MANOSCRITTO_0_Manoscritto> risultato = Database.getListaManoscritti();

			return risultato;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel prendere la lista di tutti i manoscritti");
			e.printStackTrace();

			return null;
		}
	}



}
