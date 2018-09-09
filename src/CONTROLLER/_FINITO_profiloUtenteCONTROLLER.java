package CONTROLLER;

import DAO._FINITO_profiloUtenteDAO;
import MODEL.Utente;

public class _FINITO_profiloUtenteCONTROLLER
{
	// metodo per l'effettiva modifica dei dati, passaggio di tali dati al DAO
	public boolean confermaModifiche(String nickname, String nome, String cognome, String dataNascita, String professione, String titolo)
	{
		boolean risposta = new _FINITO_profiloUtenteDAO().confermaModifiche(nickname, nome, cognome, dataNascita, professione, titolo);
		return risposta;
	}

	// Metodo per modificare l'immagine del profilo dell'utente
	public boolean modificaImmagineProfilo(String nickname, String path)
	{
		System.out.println("passaggio al CONTROLLER per settaggio immagine profilo");
		boolean risposta = new _FINITO_profiloUtenteDAO().modificaImmagineProfilo(nickname, path);

		return risposta;
	}

	// Metodo per ricavarsi l'utente come entità dal DB dato il suo nickname
	public Utente getUtente(String nick)
	{
		Utente utenteLocale = new _FINITO_profiloUtenteDAO().getUtente(nick);

		return utenteLocale;
	}
}
