package DAO;

import java.util.ArrayList;

import DATABASE.Database;
import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.A_MANOSCRITTO_1_Pagina;

public class _FINITO_sezioneTrascriberDAO implements _FINITO_INTERFACE_sezioneTrascriber
{
	// metodo che dato l'ID del trascrittore e l'ID del manoscritto restituisce una lista di tutte le pagine di quel manoscritto assegnate al trascrittore
	public ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineDelManoscrittoAssegnate(int ID_trascrittore, int ID_manoscritto)
	{
		try
		{
			ArrayList<A_MANOSCRITTO_1_Pagina> localPagine = Database.getListaPagineDelManoscrittoAssegnate(ID_trascrittore, ID_manoscritto);
			return localPagine;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel ricavare la lista delle pagine assegnate al trascrittore di ID: " + ID_trascrittore + " relative al manoscritto di ID: " + ID_manoscritto);
			e.printStackTrace();
			return null;
		}
	}

	// metodo che data una lista di pagine restituisce una lista di manoscritti che contengono almeno una delle pagine nella lista data
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscrittiAssegnatiTrascrittore(int ID_trascrittore)
	{
		try
		{
			ArrayList<A_MANOSCRITTO_0_Manoscritto> localManoscritti = Database.getListaManoscrittiAssegnatiTrascrittore(ID_trascrittore);
			return localManoscritti;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel ricavare la lista dei manoscritti assegnati al trascrittore");
			e.printStackTrace();
			return null;
		}
	}

	// metodo che dato il nickname restituisce l'ID
	public int getID(String nick)
	{
		try
		{
			int ID = Database.getIdUtente(nick);
			return ID;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel ricavarsi l'ID dell'utente: " + nick);
			e.printStackTrace();
			return 0;
		}
	}

	// metodo che inserisce nel database la trascrizione quando il trascriber fa click sul bottone salva
	public boolean inserimentoTrascrizione(String testo, int ID_manoscritto, int ID_immagine)
	{
		try
		{
			boolean risposta = Database.inserimentoTrascrizione(testo, ID_manoscritto, ID_immagine);

			return risposta;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nell'inserire la trascrizione per il manoscritto di ID: " + ID_manoscritto);
			e.printStackTrace();
			return false;
		}
	}



}
