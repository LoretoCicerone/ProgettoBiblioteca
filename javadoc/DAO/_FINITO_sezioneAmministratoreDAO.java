package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import DATABASE.Database;
import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.Utente;

public class _FINITO_sezioneAmministratoreDAO implements _FINITO_INTERFACE_sezioneAmministratore
{
	// metodo che serve per prendersi tutte le caratteristiche dell'utente dato il suo nickname
	public Utente getUtente(String nick)
	{
		try
		{
			Utente utenteLocale = Database.getUtente(nick);
			return utenteLocale;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel ricavare l'utente, errore nel DB");
			return null;
		}
	}

	// metodo per returnare la lista di tutti gli Utenti
	public LinkedList<Utente> getListaUtenti()
	{
		System.out.println("dal DAO si prende la lista di tutti gli utenti");
		try
		{
			LinkedList<Utente> localUtenti = Database.getListaUtenti();
			return localUtenti;
		}
		catch (SQLException e)
		{
			System.out.println("errore nel ricavare la lista degli utenti");
			return null;
		}
	}

	// metodo per ricavarsi la lista di tutti i manoscritti presenti nel DB
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscritti()
	{
		System.out.println("dalla VIEW si prende la lista di tutti i manoscritti");

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

	// metodo per eliminare un manoscritto dato il suo titolo
	public boolean rimuoviManoscritto(String titolo)
	{
		System.out.println("dal DAO si vuole eliminare il Manoscritto: " + titolo);
		try
		{
			Database.rimuoviManoscritto(titolo);
			System.out.println("Eliminazione del Manoscritto " + titolo + " COMPLETATA");

			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Eliminazione del Manoscritto " + titolo + " NON COMPLETATA, errore nella risposta dal DB");
			e.printStackTrace();

			return false;
		}
	}

	// metodo per eliminare un utente dato il suo nickname
	public boolean rimuoviUtente(String nickname)
	{
		System.out.println("dal DAO si vuole eliminare l'utente " + nickname);
		try
		{
			Database.rimuoviUtente(nickname);
			System.out.println("Eliminazione dell'utente " + nickname + " COMPLETATA");

			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Eliminazione dell'utente " + nickname + " NON COMPLETATA, errore nella risposta dal DB");
			e.printStackTrace();

			return false;
		}
	}

	// metodo per promuovere l'utente a utente vip
	public boolean promuoviUtenteInVip(String nickname)
	{
		System.out.println("dal DAO si vuole promuovere l'utente " + nickname + " in utente VIP");
		try
		{
			Database.promuoviUtenteInVip(nickname);
			System.out.println("Promozione dell'utente " + nickname + " in utente VIP COMPLETATA");

			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Promozione dell'utente " + nickname + " in utente VIP NON COMPLETATA, errore nella risposta dal DB");
			e.printStackTrace();

			return false;
		}
	}

	// metodo per promuovere l'utente a utente trascrittore
	public boolean promuoviUtenteInTrascrittore(String nickname)
	{
		System.out.println("dal DAO si vuole promuovere l'utente " + nickname + " in utente TRASCRITTORE");
		try
		{
			Database.promuoviUtenteInTrascrittore(nickname);
			System.out.println("Promozione dell'utente " + nickname + " in utente TRASCRITTORE COMPLETATA");

			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Promozione dell'utente " + nickname + " in utente TRASCRITTORE NON COMPLETATA, errore nella risposta dal DB");
			e.printStackTrace();

			return false;
		}
	}

	// metodo per promuovere l'utente a utente uploader
	public boolean promuoviUtenteInUploader(String nickname)
	{
		System.out.println("dal DAO si vuole promuovere l'utente " + nickname + " in utente UPLOADER");
		try
		{
			Database.promuoviUtenteInUploader(nickname);
			System.out.println("Promozione dell'utente " + nickname + " in utente UPLOADER COMPLETATA");

			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Promozione dell'utente " + nickname + " in utente UPLOADER NON COMPLETATA, errore nella risposta dal DB");
			e.printStackTrace();

			return false;
		}
	}

	// metodo per promuovere l'utente a utente manager
	public boolean promuoviUtenteInManager(String nickname)
	{
		System.out.println("dal DAO si vuole promuovere l'utente " + nickname + " in utente MANAGER");
		try
		{
			Database.promuoviUtenteInManager(nickname);
			System.out.println("Promozione dell'utente " + nickname + " in utente MANAGER COMPLETATA");

			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Promozione dell'utente " + nickname + " in utente MANAGER NON COMPLETATA, errore nella risposta dal DB");
			e.printStackTrace();

			return false;
		}
	}



}
