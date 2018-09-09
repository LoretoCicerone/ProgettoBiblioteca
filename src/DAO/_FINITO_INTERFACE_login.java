package DAO;

import MODEL.Utente;

public interface _FINITO_INTERFACE_login
{
	// metodo per il controllo del nickname nella fase di login
	public boolean controlloNicknameLogin(String nick);

	// metodo per ricavarsi l'utente una volta specificato il nickname, utile a livello di debugging e per la creazione di un entita
	public Utente getUtente(String user);
}
