package DAO;

import java.sql.SQLException;

import DATABASE.Database;

public class _FINITO_registrazioneDAO implements _FINITO_INTERFACE_registrazione
{
	// metodo che controlla se esiste gi‡ il nickname nel DB
	public boolean controllaDatiGi‡Esistenti(String nickname)
	{
		System.out.println("passaggio al dao");
		boolean esito = false;
		String query = ("select  nickname from utente_loggato where nickname = '" + nickname + "'");

		try
		{
			esito = new Database().checkNickname(query);
			System.out.println("risposta dal DB ricevuta");
		}
		catch (SQLException e)
		{
			System.out.println("errore nella risposta dal DB");
			e.printStackTrace();
		}

		return esito;
	}

	// metodo per registrare un nuovo utente nel DB
	public boolean InserimentoUtente(String nickname, String nome, String cognome, String data_nascita, String email, String password, String titolo_studio, String professione)
	{
		int id = 0;
		System.out.println("passaggio al dao");

		try
		{
			id = new Database().inserimentoNuovoUtente(nickname, nome, cognome, data_nascita, email, password, titolo_studio, professione);
			System.out.println("risposta dal dbms in relazione alla registrazione dell'utente");
		}
		catch (SQLException e)
		{
			System.out.println("ERRORE nella chiamata del dbms in relazione alla registrazione dell'utente");
			e.printStackTrace();
		}

		if (id != 0)
		{
			System.out.println("risposta dal metodo del dbms per registrazione utente");
			return true;
		}
		else
		{
			System.out.println("nessun risultato dal dbms per registrazione utente");
			return false;
		}
	}





}


