package CONTROLLER;

import DAO._FINITO_registrazioneDAO;

public class _FINITO_registrazioneCONTROLLER
{
	// metodo che controlla se esiste gi‡ il nickname nel DB
	public boolean datigi‡Esistenti(String nickname)
	{
		System.out.println("passaggio al controller");
		boolean risposta = new _FINITO_registrazioneDAO().controllaDatiGi‡Esistenti(nickname);

		return risposta;
	}

	// metodo per registrare un nuovo utente nel DB
	public boolean registraUtente(String nickname, String nome, String cognome, String data_nascita, String email, String password, String titolo_studio, String professione)
	{
		boolean risposta = new _FINITO_registrazioneDAO().InserimentoUtente(nickname, nome, cognome, data_nascita, email, password, titolo_studio, professione);
		return risposta;
	}

}
