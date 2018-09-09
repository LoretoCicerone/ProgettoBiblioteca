package DAO;

public interface _FINITO_INTERFACE_registrazione
{
	public boolean controllaDatiGi‡Esistenti(String nickname);

	public boolean InserimentoUtente(String nickname, String nome, String cognome, String data_nascita, String email, String password, String titolo_studio, String professione);
}
