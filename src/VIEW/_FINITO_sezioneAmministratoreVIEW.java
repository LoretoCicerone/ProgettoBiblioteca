package VIEW;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

import CONTROLLER._FINITO_sezioneAmministratoreCONTROLLER;
import DATABASE.Database;
import GUI._FINITO_APPOGGIO_popupAttenzione;
import GUI._FINITO_APPOGGIO_popupOk;
import GUI._FINITO_APPOGGIO_popupSiNo;
import GUI._FINITO_AmministratoreEliminaOpereGUI;
import GUI._FINITO_AmministratoreWorkbenchGUI;
import GUI._FINITO_funzione_ricercaOpera;
import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.Utente;

public class _FINITO_sezioneAmministratoreVIEW
{
	// metodo che serve per prendersi tutte le caratteristiche dell'utente dato il suo nickname
	public Utente getUtente(String nick)
	{
		Utente utenteLocale = new _FINITO_sezioneAmministratoreCONTROLLER().getUtente(nick);

		return utenteLocale;
	}

	// inizializzazione delle liste di utenti, conteggio degli utenti
	public int getNumUtenti()
	{
		System.out.println("dalla VIEW si cerca la lista di tutti gli utenti");

		try
		{
			LinkedList<Utente> localUtenti = Database.getListaUtenti();
			int numUtenti = localUtenti.size();
			return numUtenti;
		}
		catch (SQLException e)
		{
			System.out.println("errore nel ricavare la lista degli utenti");
		}

		return 0;
	}

	// metodo per returnare la lista di tutti gli Utenti
	public LinkedList<Utente> getListaUtenti()
	{
		System.out.println("dalla VIEW si prende la lista di tutti gli utenti");
		LinkedList<Utente> localUtenti = new _FINITO_sezioneAmministratoreCONTROLLER().getListaUtenti();

		return localUtenti;
	}

	// metodo per ricavarsi la lista di tutti i manoscritti presenti nel DB
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscritti()
	{
		System.out.println("dalla VIEW si prende la lista di tutti i manoscritti");
		ArrayList<A_MANOSCRITTO_0_Manoscritto> localManoscritti = new _FINITO_sezioneAmministratoreCONTROLLER().getListaManoscritti();

		return localManoscritti;
	}

	// metodo per aprire la sezione ricerca opere dalla workbench dell'amministratore
	public void premutoRicercaOpera(int ID)
	{
		new _FINITO_funzione_ricercaOpera(ID);
	}

	// metodo per aprire la sezione elimina manoscritti dalla workbench dell'amministratore
	public void premutoEliminaOpera()
	{
		new _FINITO_AmministratoreEliminaOpereGUI();
	}

	// metodo per aprire la sezione di lavoro dell'amministratore dal profilo
	public void premutoApriWorkbnch(int ID)
	{
		new _FINITO_AmministratoreWorkbenchGUI(ID);
	}

	// metodo che si attiva quando dalla sezione promuovi utente della workbench dell'amministratore si preme su conferma
	public void premutoConferma(String nickname, JCheckBox utenteVip, JRadioButton trascrittore, JRadioButton uploader, JRadioButton manager)
	{
		if (utenteVip.isSelected())
		{
			promuoviUtenteInVip(nickname);
		}

		if (trascrittore.isSelected())
		{
			promuoviUtenteInTrascrittore(nickname);
		}
		if (uploader.isSelected())
		{
			promuoviUtenteInUploader(nickname);
		}
		if (manager.isSelected())
		{
			promuoviUtenteInManager(nickname);
		}
	}

	// metodo per eliminare un manoscritto dato il suo titolo
	public boolean rimuoviManoscritto(String titolo)
	{
		_FINITO_APPOGGIO_popupSiNo conferma = new _FINITO_APPOGGIO_popupSiNo("CONFERMA ELIMINAZIONE", "<html>Sei sicuro di voler eliminare questa Opera, l'azione è irreversibile</html>");

		if (conferma.getValore() == 0)
		{
			boolean risposta = new _FINITO_sezioneAmministratoreCONTROLLER().rimuoviManoscritto(titolo);
			if (risposta == false)
			{
				System.out.println("ERRORE Manoscritto " + titolo + " non eliminato");

				return false;
			}
			else
			{
				System.out.println("Manoscritto " + titolo + " eliminato CORRETTAMENTE");
				new _FINITO_APPOGGIO_popupOk("ELIMINAZIONE MANOSCRITTO COMPLETATO", "Il Manoscritto è stato eliminato correttamente!");

				return true;
			}
		}
		else
		{
			return false;
		}
	}

	// metodo per eliminare un utente dato il suo nickname
	public boolean rimuoviUtente(String nickname)
	{
		_FINITO_APPOGGIO_popupSiNo conferma = new _FINITO_APPOGGIO_popupSiNo("CONFERMA ELIMINAZIONE", "<html>Sei sicuro di voler eliminare questo Utente, l'azione è irreversibile</html>");

		if (conferma.getValore() == 0)
		{
			boolean risposta = new _FINITO_sezioneAmministratoreCONTROLLER().rimuoviUtente(nickname);
			if (risposta == false)
			{
				System.out.println("ERRORE utente " + nickname + " non eliminato");

				return false;
			}
			else
			{
				System.out.println("utente " + nickname + " eliminato CORRETTAMENTE");
				new _FINITO_APPOGGIO_popupOk("ELIMINAZIONE UTENTE COMPLETATO", "L'Utente è stato eliminato correttamente!");

				return true;
			}
		}
		else
		{
			return false;
		}
	}

	// metodo per promuovere l'utente a utente vip
	public boolean promuoviUtenteInVip(String nickname)
	{
		boolean risposta = new _FINITO_sezioneAmministratoreCONTROLLER().promuoviUtenteInVip(nickname);
		if (risposta)
		{
			System.out.println("Promozione dell'utente " + nickname + " COMPLETATA");
			new _FINITO_APPOGGIO_popupOk("PROMOZIONE UTENTE COMPLETATA", "L'Utente " + nickname + " è stato promosso correttamente!");
			return true;
		}
		else
		{
			System.out.println("Promozione dell'utente " + nickname + " FALLITA");
			new _FINITO_APPOGGIO_popupAttenzione("PROMOZIONE UTENTE FALLITA", "L'Utente " + nickname + " non è stato promosso, riprova");
			return false;
		}
	}

	// metodo per promuovere l'utente a utente trascrittore
	public boolean promuoviUtenteInTrascrittore(String nickname)
	{
		boolean risposta = new _FINITO_sezioneAmministratoreCONTROLLER().promuoviUtenteInTrascrittore(nickname);
		if (risposta)
		{
			System.out.println("Promozione dell'utente " + nickname + " COMPLETATA");
			new _FINITO_APPOGGIO_popupOk("PROMOZIONE UTENTE COMPLETATA", "L'Utente " + nickname + " è stato promosso correttamente!");
			return true;
		}
		else
		{
			System.out.println("Promozione dell'utente " + nickname + " FALLITA");
			new _FINITO_APPOGGIO_popupAttenzione("PROMOZIONE UTENTE FALLITA", "L'Utente " + nickname + " non è stato promosso, riprova");
			return false;
		}
	}

	// metodo per promuovere l'utente a utente uploader
	public boolean promuoviUtenteInUploader(String nickname)
	{
		boolean risposta = new _FINITO_sezioneAmministratoreCONTROLLER().promuoviUtenteInUploader(nickname);
		if (risposta)
		{
			System.out.println("Promozione dell'utente " + nickname + " COMPLETATA");
			new _FINITO_APPOGGIO_popupOk("PROMOZIONE UTENTE COMPLETATA", "L'Utente " + nickname + " è stato promosso correttamente!");
			return true;
		}
		else
		{
			System.out.println("Promozione dell'utente " + nickname + " FALLITA");
			new _FINITO_APPOGGIO_popupAttenzione("PROMOZIONE UTENTE FALLITA", "L'Utente " + nickname + " non è stato promosso, riprova");
			return false;
		}
	}

	// metodo per promuovere l'utente a utente manager
	public boolean promuoviUtenteInManager(String nickname)
	{
		boolean risposta = new _FINITO_sezioneAmministratoreCONTROLLER().promuoviUtenteInManager(nickname);
		if (risposta)
		{
			System.out.println("Promozione dell'utente " + nickname + " COMPLETATA");
			new _FINITO_APPOGGIO_popupOk("PROMOZIONE UTENTE COMPLETATA", "L'Utente " + nickname + " è stato promosso correttamente!");
			return true;
		}
		else
		{
			System.out.println("Promozione dell'utente " + nickname + " FALLITA");
			new _FINITO_APPOGGIO_popupAttenzione("PROMOZIONE UTENTE FALLITA", "L'Utente " + nickname + " non è stato promosso, riprova");
			return false;
		}
	}

}
