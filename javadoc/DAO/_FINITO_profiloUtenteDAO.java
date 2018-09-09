package DAO;

import java.sql.SQLException;

import DATABASE.Database;
import MODEL.Utente;

public class _FINITO_profiloUtenteDAO implements _FINITO_INTERFACE_profiloUtente
{
	// metodo per l'update delle modifiche fatte dall'utente nel suo profilo utente (la query la prepariamo nel database)
	public boolean confermaModifiche(String nickname, String nome, String cognome, String dataNascita, String professione, String titolo)
	{
		System.out.println("passaggio al DAO per update dati utente");
		boolean esito = false;

		try
		{
			esito = new Database().confermaModifiche(nickname, nome, cognome, dataNascita, professione, titolo);
			System.out.println("Risposta ricevuta dal DB");
		}
		catch (SQLException e)
		{
			System.out.println("Errore nella risposta dal DB");
		}

		return esito;
	}

	// Metodo per modificare l'immagine del profilo dell'utente
	public boolean modificaImmagineProfilo(String nickname, String path)
	{
		System.out.println("passaggio al DAO per settaggio immagine profilo");
		try
		{
			new Database().modificaImmagineProfilo(nickname, path);
			System.out.println("Risposta ricevuta dal DB");

			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Errore nella risposta dal DB");

			return false;
		}
	}

	// Metodo per ricavarsi l'immagine del profilo appena l'utente accede
	public String getImmagineProfilo(String nick)
	{
		System.out.println("passaggio al DAO per ricavarsi il path dell'immagine del profilo che l'utente ha scelto");
		String path = new Database().getImmagineProfilo(nick);
		return path;
	}

	// Metodo per ricavarsi l'utente come entità dal DB dato il suo nickname
	public Utente getUtente(String nick)
	{
		System.out.println("passaggio al DAO per ricavarsi l'entità utente dal DB");
		try
		{
			Utente utenteLocale = new Database().getUtente(nick);
			return utenteLocale;
		}
		catch (SQLException e)
		{
			System.out.println("ERRORE nella risposta al database, ERRORE nel ricavare l'utente");
			e.printStackTrace();
			return null;
		}
	}

	// metodo che fa diventare l'utente specificato in trascrittore
	public boolean diventaTrascrittore(String nik)
	{
		try
		{
			boolean esito = Database.promuoviUtenteInTrascrittore(nik);

			return esito;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel promuovere " + nik + " in un trascrittore");
			e.printStackTrace();

			return false;
		}
	}
}
