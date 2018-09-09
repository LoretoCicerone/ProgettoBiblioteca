package DAO;

public interface _FINITO_INTERFACE_sezioneUploader
{
	// metodo per controllare che non esista un manoscritto con il titolo specificato
	public boolean checkTitoloManoscrittoGiaPresente(String titolo);

	// metodo per ricavarsi l'ID dei un Manoscritto passandogli come parametro il titolo
	public int getIdManoscritto(String titolo);

	// metodo che ricava l'id di un utente dato il suo nickname
	public int getIdUtente(String nick);

	// metodo per inserire un nuovo manoscritto nel DB
	public boolean inserisciManoscritto(String titolo, int anno, int numPag, String genere, String autore);

	// metodo per inserire una nuova immagine nel DB assegnata all'ID di un manoscritto
	public boolean inserisciImmagine(String path, int ID_uploader, int ID_manoscritto);

	// metodo per inserire nel DB l'immagine di copertina di un manoscritto, dal pannello Uploader
	public boolean inerisciCopertina(String path, int ID_manoscritto);

}
