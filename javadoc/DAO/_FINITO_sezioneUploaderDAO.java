package DAO;

import java.sql.SQLException;

import DATABASE.Database;

public class _FINITO_sezioneUploaderDAO implements _FINITO_INTERFACE_sezioneUploader
{
	// metodo per controllare che non esista un manoscritto con il titolo specificato
	public boolean checkTitoloManoscrittoGiaPresente(String titolo)
	{
		boolean risposta;
		try
		{
			risposta = new Database().checkTitoloManoscrittoGiaPresente(titolo);
			return risposta;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	// metodo per ricavarsi l'ID dei un Manoscritto passandogli come parametro il titolo
	public int getIdManoscritto(String titolo)
	{
		int id = new Database().getIdManoscritto(titolo);
		return id;
	}

	// metodo che ricava l'id di un utente dato il suo nickname
	public int getIdUtente(String nick)
	{
		int id = new Database().getIdUtente(nick);
		return id;
	}

	// metodo per inserire un nuovo manoscritto nel DB
	public boolean inserisciManoscritto(String titolo, int anno, int numPag, String genere, String autore)
	{
		int id = 0;
		System.out.println("passaggio al DAO per inserire nuovo manoscritto nel DB");

		try
		{
			id = new Database().inserimentoNuovoManoscritto(titolo, anno, numPag, genere, autore);
			System.out.println("risposta dal DB ricevuta per l'inserimento di un nuovo manoscritto");
		}
		catch (SQLException e)
		{
			System.out.println("ERRORE nel chiamare il metodo per la registrazione di un nuovo manoscritto");
			e.printStackTrace();
		}

		if (id != 0)
		{
			System.out.println("risposta dal DB per inserimento nuovo manoscritto CORRETTA");
			return true;
		}
		else
		{
			System.out.println("risposta dal DB per inserimento nuovo manoscritto NON corretta");
			return false;
		}
	}

	// metodo per inserire una nuova immagine nel DB assegnata all'ID di un manoscritto
	public boolean inserisciImmagine(String path, int ID_uploader, int ID_manoscritto)
	{
		int id = 0;
		System.out.println("passaggio al DAO per inserire una nuova immagine nel DB");

		try
		{
			id = new Database().inserimentoNuovaImmagine(path, ID_uploader, ID_manoscritto);
			System.out.println("risposta dal DB ricevuta per l'inserimento di una nuova immagine");
		}
		catch (SQLException e)
		{
			System.out.println("ERRORE nel chiamare il metodo per l'inserimento di una nuova immagine");
			e.printStackTrace();
		}

		if (id != 0)
		{
			System.out.println("risposta dal DB per inserimento di una nuova immagine CORRETTA");
			return true;
		}
		else
		{
			System.out.println("risposta dal DB per inserimento di una nuova immagine NON corretta");
			return false;
		}
	}

	// metodo per inserire nel DB l'immagine di copertina di un manoscritto, dal pannello Uploader
	public boolean inerisciCopertina(String path, int ID_manoscritto)
	{
		System.out.println("passaggio al DAO per inserire la copertina del manoscritto nel DB");
		boolean risposta = new Database().inserimentoNuovaCopertinaManoscritto(path, ID_manoscritto);

		return risposta;
	}
}
