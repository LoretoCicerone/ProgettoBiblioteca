package DAO;

import java.sql.SQLException;

import DATABASE.Database;
import MODEL.Utente;

public class _FINITO_loginDAO implements _FINITO_INTERFACE_login
{
	// metodo per il controllo del nickname nella fase di login
	public boolean controlloNicknameLogin(String nick)
	{
		System.out.println("passaggio al dao per il relativo controllo nickname dal login");

		boolean esito = false;
		String query = ("select nickname from utente_loggato where nickname = '" + nick + "'");

		try
		{
			esito = new Database().checkNicknameLogin(query);
			System.out.println("risposta dal database ricevuta");
		}
		catch (SQLException e)
		{
			System.out.println("errore nella risposta dal DB");
			e.printStackTrace();
		}

		return esito;
	}

	// metodo per ricavarsi l'utente una volta specificato il nickname, utile a livello di debugging e per la creazione di un entita
	public Utente getUtente(String user)
	{
		Utente utente = null;
		try
		{
			utente = new Database().getUtente(user);
			System.out.println("risposta dal database ricevuta");
			return utente;
		}
		catch (SQLException e)
		{
			System.out.println("errore nella risposta dal DB");
			e.printStackTrace();
			return utente;
		}
	}

	// metodo per ricavarmi l'Amministratore, si attiva quando nel pannello login utilizzo come nickname e password: Administrator
	public Utente getAdministrator(String user)
	{
		Utente utente = null;
		try
		{
			utente = new Database().getAdministrator(user);
			System.out.println("risposta dal database ricevuta");
			return utente;
		}
		catch (SQLException e)
		{
			System.out.println("errore nella risposta dal DB");
			e.printStackTrace();
			return utente;
		}
	}
}
