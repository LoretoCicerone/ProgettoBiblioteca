package DAO;

import java.util.ArrayList;

import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.A_MANOSCRITTO_1_Pagina;

public interface _FINITO_INTERFACE_sezioneTrascriber
{
	// metodo che dato l'ID del trascrittore e l'ID del manoscritto restituisce una lista di tutte le pagine di quel manoscritto assegnate al trascrittore
	public ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineDelManoscrittoAssegnate(int ID_trascrittore, int ID_manoscritto);

	// metodo che data una lista di pagine restituisce una lista di manoscritti che contengono almeno una delle pagine nella lista data
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscrittiAssegnatiTrascrittore(int ID_trascrittore);

	// metodo che dato il nickname restituisce l'ID
	public int getID(String nick);

	// metodo che inserisce nel database la trascrizione quando il trascriber fa click sul bottone salva
	public boolean inserimentoTrascrizione(String testo, int ID_manoscritto, int ID_immagine);

}
