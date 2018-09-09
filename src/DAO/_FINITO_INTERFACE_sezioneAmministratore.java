package DAO;

import java.util.ArrayList;
import java.util.LinkedList;

import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.Utente;

public interface _FINITO_INTERFACE_sezioneAmministratore
{
	// metodo che serve per prendersi tutte le caratteristiche dell'utente dato il suo nickname
	public Utente getUtente(String nick);

	// metodo per returnare la lista di tutti gli Utenti
	public LinkedList<Utente> getListaUtenti();

	// metodo per ricavarsi la lista di tutti i manoscritti presenti nel DB
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscritti();

	// metodo per eliminare un manoscritto dato il suo titolo
	public boolean rimuoviManoscritto(String titolo);

	// metodo per eliminare un utente dato il suo nickname
	public boolean rimuoviUtente(String nickname);

	// metodo per promuovere l'utente a utente vip
	public boolean promuoviUtenteInVip(String nickname);

	// metodo per promuovere l'utente a utente trascrittore
	public boolean promuoviUtenteInTrascrittore(String nickname);

	// metodo per promuovere l'utente a utente uploader
	public boolean promuoviUtenteInUploader(String nickname);

	// metodo per promuovere l'utente a utente manager
	public boolean promuoviUtenteInManager(String nickname);
}
