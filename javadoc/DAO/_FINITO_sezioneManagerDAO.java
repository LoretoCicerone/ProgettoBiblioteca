package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import DATABASE.Database;
import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.A_MANOSCRITTO_1_Pagina;
import MODEL.Utente;

public class _FINITO_sezioneManagerDAO implements _FINITO_INTERFACE_sezioneManager
{
	// metodo che dato il nickname di un trascrittore ne ricava l'ID
	public int getIdTrascrittore(String nick)
	{
		try
		{
			Utente utenteTrascrittore = Database.getUtente(nick);

			return utenteTrascrittore.getID();
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel ricavare l'ID del trascrittore");
			e.printStackTrace();
			return 555555555;
		}
	}

	// metodo che ricava la lista di tutti i trascrittori a cui è stata assegnata l'immagine specificata
	public ArrayList<Utente> getListaTrascrittoriImmagineAssegnata(int ID_immagine)
	{
		try
		{
			ArrayList<Utente> localList = Database.getListaTrascrittoriImmagineAssegnata(ID_immagine);
			return localList;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel ricavarsi la lista di tutti i trascrittori a cui è stata assegnata l'immagine di ID: " + ID_immagine);
			e.printStackTrace();
			return null;
		}
	}

	// metodo per ricavarsi la lista di tutti gli utenti trascrittori
	public ArrayList<Utente> getListaTrascrittori()
	{
		try
		{
			ArrayList<Utente> localList = Database.getListaTrascrittori();
			return localList;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel ricavarsi la lista di tutti i trascrittori");
			e.printStackTrace();
			return null;
		}
	}

	// metodo per ricavarsi la lista di tutti gli utenti trascrittori con anche il dato esperienza
	public ArrayList<Utente> getListaTrascrittoriExp()
	{
		try
		{
			ArrayList<Utente> localList = Database.getListaTrascrittoriExp();
			return localList;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel ricavarsi la lista di tutti i trascrittori");
			e.printStackTrace();
			return null;
		}
	}

	// metodo per ricavarsi la lista di tutti i manoscritti
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscritti()
	{
		try
		{
			ArrayList<A_MANOSCRITTO_0_Manoscritto> localManoscritti = Database.getListaManoscritti();
			return localManoscritti;
		}
		catch (SQLException e)
		{
			System.out.println("errore nel ricavare la lista dei Manoscritti");
			return null;
		}
	}

	// metodo per ricavarsi la lista di tutte le pagine di un manoscritto
	public ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagine(int ID)
	{
		try
		{
			ArrayList<A_MANOSCRITTO_1_Pagina> localPagine = Database.getListaPagine(ID);
			return localPagine;
		}
		catch (SQLException e)
		{
			System.out.println("errore nel ricavare la lista di tutte le pagine del manoscritto di id:" + ID);
			e.printStackTrace();
			return null;
		}
	}

	// metodo per ricavarsi la lista di tutte le pagine accettate di un manoscritto dato il suo titolo
	public ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineACCETTATE(String titolo)
	{
		try
		{
			ArrayList<A_MANOSCRITTO_1_Pagina> localPagine = Database.getListaPagineAccettate(titolo);
			return localPagine;
		}
		catch (Exception e)
		{
			System.out.println("errore nel ricavare la lista di tutte le pagine ACCETTATE del manoscritto di titolo:" + titolo);
			e.printStackTrace();
			return null;
		}

	}

	// metodo che restituisce una lista di tutte le pagine di un manoscritto con la trascrizione, per poter revisionare tale trascrizione
	public ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineConTrascrizioniDaRevisionare(int ID_manoscritto)
	{
		try
		{
			ArrayList<A_MANOSCRITTO_1_Pagina> localPagine = Database.getListaPagineConTrascrizioneManoscritto(ID_manoscritto);

			return localPagine;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel ricavarsi la lista di tutte le pagine con trascrizione del manoscritto di ID: " + ID_manoscritto);
			e.printStackTrace();
			return null;
		}
	}

	// metodo che mi da lo stato della trascrizione che gli passo tramite id
	public String getStatoTrascrizione(int ID_trascrizione)
	{
		try
		{
			String stato = Database.getStatoTrascrizione(ID_trascrizione);

			return stato;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel ricavare lo stato della trascrizione di ID: " + ID_trascrizione);
			e.printStackTrace();
			return null;
		}
	}

	// metodo che modifica lo stato dei una pagina in base all'input
	public boolean modificaStatoPagina(int idImmagine, int idManager, int scelta)
	{
		try
		{
			boolean esito = Database.modificaStatoImmagine(idImmagine, idManager, scelta);

			return esito;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel cambiare lo stato dell'immagine di ID: " + idImmagine);
			e.printStackTrace();

			return false;
		}
	}

	// metodo che si attiva quando si sceglie un trascrittore per assegnarli un'immagine e si preme su assegna
	public boolean premutoAssegna(int ID_immagine, int ID_trascrittore)
	{
		try
		{
			boolean esito = Database.assegnaImmagine(ID_immagine, ID_trascrittore);

			return esito;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nell'assegnare una pagina ad un trascrittore");
			e.printStackTrace();

			return false;
		}
	}

	// metodo che si attiva quando si preme accetta nella sezione revisione trascrizioni
	public boolean premutoAccettaTrascrizione(int idTrascrizione, int idManager, int scelta)
	{
		try
		{
			boolean esito = Database.accettaTrascrizione(idTrascrizione, idManager, scelta);

			return esito;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nell'accettare la trascrizione di ID: " + idTrascrizione);
			e.printStackTrace();

			return false;
		}
	}

	// metodo che si attiva quando si preme rifiuta nella sezione revisione trascrizioni
	public boolean premutoRifiutaTrascrizione(int idTrascrizione, int idManager)
	{
		try
		{
			boolean esito = Database.rifiutaTrascrizione(idTrascrizione, idManager);

			return esito;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	// metodo che si attiva quando si sceglie un nuovo trascrittore a cui riassegnare l'immagine da trascrivere
	public boolean riassegnaImmagineTrascrittore(int ID_immagine, int ID_trascrittore)
	{
		try
		{
			boolean esito = Database.riassegnaTrascrizione(ID_immagine, ID_trascrittore);

			return esito;
		}
		catch (Exception e)
		{

			System.out.println("ERRORE nel riassegnare l'immagine di ID: " + ID_immagine + " al trascrittore di ID: " + ID_trascrittore);
			e.printStackTrace();

			return false;
		}
	}

	// metodo che si attiva quando si preme salva dal TEI, aggiorna la trascrizione gia esistente
	public boolean premutoSalvaTEI(int idTrascrizione, String testo)
	{
		try
		{
			boolean esito = Database.modificaTrascrizioneEsistente(idTrascrizione, testo);

			return esito;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel aggiornare la trascrizione di ID: " + idTrascrizione);
			e.printStackTrace();

			return false;
		}
	}

	// metodo che aumenta di un livello il trascrittore specificato
	public boolean aumentaLivelloTrascrittore(int ID_trascrittore, int livelloTrascrittore)
	{
		try
		{
			boolean esito = Database.assegnaEsperienza(ID_trascrittore, livelloTrascrittore);

			return esito;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel aumentare di livello il trascrittore di ID: " + ID_trascrittore);
			e.printStackTrace();

			return false;
		}
	}


}
