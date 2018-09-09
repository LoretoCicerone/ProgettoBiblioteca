package CONTROLLER;

import DAO._FINITO_loginDAO;
import MODEL.Utente;

public class _FINITO_loginCONTROLLER
{
	// metodo per il controllo del nickname nella fase di login
	public boolean controlloNicknameLogin(String nick)
	{
		System.out.println("passaggio al controller per il check del nickname");
		boolean esito = new _FINITO_loginDAO().controlloNicknameLogin(nick);

		return esito;
	}

	// metodo per ricavarsi l'utente una volta specificato il nickname, utile a livello di debugging e per la creazione di un entita
	public Utente getUtente(String user)
	{
		Utente utente = new _FINITO_loginDAO().getUtente(user);

		return utente;
	}

	// metodo per ricavarmi l'Amministratore, si attiva quando nel pannello login utilizzo come nickname e password: Administrator
	public Utente getAdministrator(String user)
	{
		Utente utente = new _FINITO_loginDAO().getAdministrator(user);

		return utente;
	}


}
