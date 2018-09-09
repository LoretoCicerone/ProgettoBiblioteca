package CONTROLLER;

import DAO._FINITO_sezioneUploaderDAO;

public class _FINITO_sezioneUploaderCONTROLLER
{
	// metodo per controllare che non esista un manoscritto con il titolo specificato
	public boolean checkTitoloManoscrittoGiaPresente(String titolo)
	{
		System.out.println("passaggio al CONTROLLER per controllare che non esista già un manoscritto con quel titolo");
		boolean risposta = new _FINITO_sezioneUploaderDAO().checkTitoloManoscrittoGiaPresente(titolo);

		return risposta;
	}

	// metodo per ricavarsi l'ID dei un Manoscritto passandogli come parametro il titolo
	public int getIdManoscritto(String titolo)
	{
		int id = new _FINITO_sezioneUploaderDAO().getIdManoscritto(titolo);
		return id;
	}

	// metodo che ricava l'id di un utente dato il suo nickname
	public int getIdUtente(String nick)
	{
		int id = new _FINITO_sezioneUploaderDAO().getIdUtente(nick);
		return id;
	}

	// metodo per registrare un nuovo manoscritto nel DB
	public boolean registraManoscritto(String titolo, int anno, int numPag, String genere, String autore)
	{
		System.out.println("passaggio al CONTROLLER per inserire un nuovo manoscritto nel DB");
		boolean risposta = new _FINITO_sezioneUploaderDAO().inserisciManoscritto(titolo, anno, numPag, genere, autore);

		return risposta;
	}

	// metodo per inserire una nuova immagine nel DB assegnata all'ID di un manoscritto
	public boolean inserisciImmagine(String path, int ID_uploader, int ID_manoscritto)
	{
		System.out.println("passaggio al CONTROLLER per inserire una nuova immagine nel DB");
		boolean risposta = new _FINITO_sezioneUploaderDAO().inserisciImmagine(path, ID_uploader, ID_manoscritto);

		return risposta;
	}

	// metodo per inserire nel DB l'immagine di copertina di un manoscritto, dal pannello Uploader
	public boolean inserisciCopertina(String path, int ID_manoscritto)
	{
		System.out.println("passaggio al CONTROLLER per inserire la copertina del manoscritto nel DB");
		boolean risposta = new _FINITO_sezioneUploaderDAO().inerisciCopertina(path, ID_manoscritto);

		return risposta;
	}

}
