package CONTROLLER;

import java.util.ArrayList;
import java.util.LinkedList;

import DAO._FINITO_sezioneAmministratoreDAO;
import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.Utente;

public class _FINITO_sezioneAmministratoreCONTROLLER
{
	// metodo che serve per prendersi tutte le caratteristiche dell'utente dato il suo nickname
	public Utente getUtente(String nick)
	{
		Utente utenteLocale = new _FINITO_sezioneAmministratoreDAO().getUtente(nick);

		return null;
	}

	// metodo per returnare la lista di tutti gli Utenti
	public LinkedList<Utente> getListaUtenti()
	{
		System.out.println("dal CONTROLLER si prende la lista di tutti gli utenti");
		LinkedList<Utente> localList = new _FINITO_sezioneAmministratoreDAO().getListaUtenti();

		return localList;
	}

	// metodo per ricavarsi la lista di tutti i manoscritti presenti nel DB
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscritti()
	{
		System.out.println("dal CONTROLLER si prende la lista di tutti i Manoscritti");
		ArrayList<A_MANOSCRITTO_0_Manoscritto> localList = new _FINITO_sezioneAmministratoreDAO().getListaManoscritti();

		return localList;
	}

	// metodo per eliminare un manoscritto dato il suo titolo
	public boolean rimuoviManoscritto(String titolo)
	{
		System.out.println("dal CONTROLLER si vuole eliminare il Manoscritto " + titolo);
		boolean risposta = new _FINITO_sezioneAmministratoreDAO().rimuoviManoscritto(titolo);

		return risposta;
	}

	// metodo per eliminare un utente dato il suo nickname
	public boolean rimuoviUtente(String nickname)
	{
		System.out.println("dal CONTROLLER si vuole eliminare l'utente " + nickname);
		boolean risposta = new _FINITO_sezioneAmministratoreDAO().rimuoviUtente(nickname);

		return risposta;
	}

	// metodo per promuovere l'utente a utente vip
	public boolean promuoviUtenteInVip(String nickname)
	{
		System.out.println("dal CONTROLLER si vuole promuovere l'utente " + nickname + " in utente VIP");
		boolean risposta = new _FINITO_sezioneAmministratoreDAO().promuoviUtenteInVip(nickname);

		return risposta;
	}

	// metodo per promuovere l'utente a utente trascrittore
	public boolean promuoviUtenteInTrascrittore(String nickname)
	{
		System.out.println("dal CONTROLLER si vuole promuovere l'utente " + nickname + " in utente TRASCRITTORE");
		boolean risposta = new _FINITO_sezioneAmministratoreDAO().promuoviUtenteInTrascrittore(nickname);

		return risposta;
	}

	// metodo per promuovere l'utente a utente uploader
	public boolean promuoviUtenteInUploader(String nickname)
	{
		System.out.println("dal CONTROLLER si vuole promuovere l'utente " + nickname + " in utente UPLOADER");
		boolean risposta = new _FINITO_sezioneAmministratoreDAO().promuoviUtenteInUploader(nickname);

		return risposta;
	}

	// metodo per promuovere l'utente a utente manager
	public boolean promuoviUtenteInManager(String nickname)
	{
		System.out.println("dal CONTROLLER si vuole promuovere l'utente " + nickname + " in utente MANAGER");
		boolean risposta = new _FINITO_sezioneAmministratoreDAO().promuoviUtenteInManager(nickname);

		return risposta;
	}


}
