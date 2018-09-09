package DAO;

import MODEL.Utente;

public interface _FINITO_INTERFACE_profiloUtente
{
	// metodo per l'update delle modifiche fatte dall'utente nel suo profilo utente (la query la prepariamo nel database)
	public boolean confermaModifiche(String nickname, String nome, String cognome, String dataNascita, String professione, String titolo);

	// Metodo per modificare l'immagine del profilo dell'utente
	public boolean modificaImmagineProfilo(String nickname, String path);

	// Metodo per ricavarsi l'immagine del profilo appena l'utente accede
	public String getImmagineProfilo(String nick);

	// Metodo per ricavarsi l'utente come entità dal DB dato il suo nickname
	public Utente getUtente(String nick);

	// metodo che fa diventare l'utente specificato in trascrittore
	public boolean diventaTrascrittore(String nik);
}
