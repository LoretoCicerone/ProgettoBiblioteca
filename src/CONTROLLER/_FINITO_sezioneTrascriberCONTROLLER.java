package CONTROLLER;

import java.util.ArrayList;

import DAO._FINITO_sezioneTrascriberDAO;
import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.A_MANOSCRITTO_1_Pagina;

public class _FINITO_sezioneTrascriberCONTROLLER
{
	// metodo che dato l'ID del trascrittore e l'ID del manoscritto restituisce una lista di tutte le pagine di quel manoscritto assegnate al trascrittore
	public ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineDelManoscrittoAssegnate(int ID_trascrittore, int ID_manoscritto)
	{
		ArrayList<A_MANOSCRITTO_1_Pagina> localPagine = new _FINITO_sezioneTrascriberDAO().getListaPagineDelManoscrittoAssegnate(ID_trascrittore, ID_manoscritto);

		return localPagine;
	}

	// metodo che data una lista di pagine restituisce una lista di manoscritti che contengono almeno una delle pagine nella lista data
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscrittiAssegnatiTrascrittore(int ID_trascrittore)
	{
		ArrayList<A_MANOSCRITTO_0_Manoscritto> localManoscritti = new _FINITO_sezioneTrascriberDAO().getListaManoscrittiAssegnatiTrascrittore(ID_trascrittore);

		return localManoscritti;
	}

	// metodo che dato il nickname restituisce l'ID
	public int getID(String nick)
	{
		int ID = new _FINITO_sezioneTrascriberDAO().getID(nick);

		return ID;
	}

	// metodo che inserisce nel database la trascrizione quando il trascriber fa click sul bottone salva
	public boolean inserimentoTrascrizione(String testo, int ID_manoscritto, int ID_immagine)
	{
		boolean risposta = new _FINITO_sezioneTrascriberDAO().inserimentoTrascrizione(testo, ID_manoscritto, ID_immagine);

		return risposta;
	}

}
