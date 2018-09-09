package VIEW;

import CONTROLLER._FINITO_loginCONTROLLER;
import GUI._FINITO_APPOGGIO_popupAttenzione;
import GUI._FINITO_AmministratoreProfiloGUI;
import GUI._FINITO_UtenteProfiloGUI;
import GUI._FINITO_funzione_registrazioneGUI;
import MODEL.Utente;

public class _FINITO_loginVIEW
{

	// l'utente ha premuto il bottone accedi
	public boolean accedi(String username, String password)
	{
		Utente utenteLocale = null;


		// ------------------------------------------------------------------------------------------------------------------------//

		// controllo subito se l'username e la password corrispondono a Administrator, se si faccio un percorso diverso di accesso
		if (username.equals("Administrator") && password.equals("Administrator"))
		{
			utenteLocale = new _FINITO_loginCONTROLLER().getAdministrator(username);

			System.out.println("");
			System.out.println("-----------DATI AMMINISTRATORE---------------------------------");
			System.out.println("username:		" + utenteLocale.getNickname());
			System.out.println("nome:			" + utenteLocale.getNome());
			System.out.println("cognome:		" + utenteLocale.getCognome());
			System.out.println("data nascita:	" + utenteLocale.getData_nascita());
			System.out.println("email:			" + utenteLocale.getEmail());
			System.out.println("password:		" + utenteLocale.getPassword());
			System.out.println("titolo studio:	" + utenteLocale.getTitolostudio());
			System.out.println("professione:	" + utenteLocale.getProfessione());
			System.out.println("---------------------------------------------------------------");
			System.out.println("");

			String nick = username;
			String nome = utenteLocale.getNome();
			String cognome = utenteLocale.getCognome();
			String data = utenteLocale.getData_nascita();
			String email = utenteLocale.getEmail();
			String passw = password;
			String titolo = utenteLocale.getTitolostudio();
			String professione = utenteLocale.getProfessione();
			int id = utenteLocale.getID();

			new _FINITO_AmministratoreProfiloGUI(nick, nome, cognome, data, email, passw, titolo, professione, id);

			return true;
		}
		else
		{
			// l'accesso non vede coinvolto l'Amministratore, perciò eseguo un percorso relativo all'accesso di un utente normale

			// USERNAME
			// controllo che il nickname non contenga caratteri non ammessi
			boolean user = controlloCaratteri(username);
			if (user == false)
			{
				String errore = "Non sono ammessi tali caratteri: / * - + ! \\\" £ $ % & / ( ) = ? ^ [ ] § # ° @";
				new _FINITO_APPOGGIO_popupAttenzione("ERRORE NICKNAME", errore);
				return false;
			}
			// controllo che il nickname non sia vuoto
			user = controlloNonNull(username);
			if (user == false)
			{
				String errore = "Il campo immesso è troppo corto o assente";
				new _FINITO_APPOGGIO_popupAttenzione("ERRORE NICKNAME", errore);
				return false;
			}

			// controllo che il nickname esista
			user = new _FINITO_loginCONTROLLER().controlloNicknameLogin(username);
			if (user == false)
			{
				String errore = "Nickname non trovato, se non sei registrato puoi sempre farlo!";
				new _FINITO_APPOGGIO_popupAttenzione("ERRORE NICKNAME", errore);
				return false;
			}
			else
			{

				// se il nickname esiste in database mi estrapolo l'entita utente per fare vari confronti
				utenteLocale = new _FINITO_loginCONTROLLER().getUtente(username);
				System.out.println("");
				System.out.println("creato una nuova entita con username " + username);
				System.out.println("");
				System.out.println("-----------DATI UTENTE---------------------------------");
				System.out.println("username:		" + utenteLocale.getNickname());
				System.out.println("nome:			" + utenteLocale.getNome());
				System.out.println("cognome:		" + utenteLocale.getCognome());
				System.out.println("data nascita:	" + utenteLocale.getData_nascita());
				System.out.println("email:			" + utenteLocale.getEmail());
				System.out.println("password:		" + utenteLocale.getPassword());
				System.out.println("titolo studio:	" + utenteLocale.getTitolostudio());
				System.out.println("professione:	" + utenteLocale.getProfessione());
				System.out.println("vip:			" + utenteLocale.isUtente_vip());
				System.out.println("trascrittore:	" + utenteLocale.isUtente_trascrittore());
				System.out.println("uploader:		" + utenteLocale.isUtente_uploader());
				System.out.println("manager:		" + utenteLocale.isUtente_manager());
				System.out.println("-------------------------------------------------------");
				System.out.println("");

			}

			// PASSWORD
			// controllo che la password non sia vuota
			boolean pass = controlloNonNull(password);
			if (pass == false)
			{
				String errore = "Il campo immesso è troppo corto o assente";
				new _FINITO_APPOGGIO_popupAttenzione("ERRORE PASSWORD", errore);
				return false;
			}
			// controllo che la password sia corretta per quell'username
			pass = controlloPassCorretta(utenteLocale, password);
			if (pass == false)
			{
				String errore = "La password che hai immesso non è corretta";
				new _FINITO_APPOGGIO_popupAttenzione("ERRORE PASSWORD", errore);
				return false;
			}

			// ------------------------------ACCESSO----------------------------------------------------------//

			if (user && pass)
			{
				String nick = username;
				String nome = utenteLocale.getNome();
				String cognome = utenteLocale.getCognome();
				String data = utenteLocale.getData_nascita();
				String email = utenteLocale.getEmail();
				String passw = password;
				String titolo = utenteLocale.getTitolostudio();
				String professione = utenteLocale.getProfessione();
				boolean utente_vip = utenteLocale.isUtente_vip();
				boolean utente_trascrittore = utenteLocale.isUtente_trascrittore();
				boolean utente_manager = utenteLocale.isUtente_manager();
				boolean utente_uploader = utenteLocale.isUtente_uploader();

				new _FINITO_UtenteProfiloGUI(nick, nome, cognome, data, email, passw, titolo, professione, utente_vip, utente_manager, utente_uploader, utente_trascrittore);
				return true;
			}
			else

			{
				System.out.println("ERRORE LOGIN");
				System.out.println("errore nel check dei campi");

				new _FINITO_APPOGGIO_popupAttenzione("LOGIN FALLITO", "Revisiona bene i campi username e password, ci sono degli errori!");
				System.out.println("");

				return false;
			}
		}
	}

	// l'utente ha premuto il bottone registrati
	public void registrati()
	{
		System.out.println("dalla view si è usato il metodo relativo al click del bottone registrati");
		new _FINITO_funzione_registrazioneGUI();
	}

	// ---------------------METODI PER IL CONTROLLO DELL'INPUT------------------------------------------//

	// controlla che non ci siano caratteri non ammissibili nel campo specificato
	public boolean controlloCaratteri(String input)
	{
		char carateriNonAmmessi[] = { '/', '*', '-', '+', '!', '"', '£', '$', '%', '&', '(', ')', '=', '?', '^', '[', ']', '#', '°', '§', '@' };
		String inputDaControllare = input;

		for (int i = 0; i < input.length(); i++)
		{
			for (int z = 0; z < carateriNonAmmessi.length; z++)
			{
				if (input.charAt(i) == carateriNonAmmessi[z])
				{
					String erroreCaratteri = "Non sono ammessi tali caratteri: / * - + ! \" £ $ % & / ( ) = ? ^ [ ] § # ° @ ";

					System.out.println(erroreCaratteri);
					return false;
				}
			}
		}
		return true;
	}

	// controlla che il campo non sia vuoto
	public boolean controlloNonNull(String input)
	{
		if (input.length() <= 1)
		{
			String erroreLunghezza = "Il campo immesso è troppo corto o assente";
			System.out.println(erroreLunghezza);

			return false;
		}
		else
		{
			return true;
		}
	}

	// cotrolla che la password sia giusta una volta che si è accertato che il nickname esiste
	public boolean controlloPassCorretta(Utente utente, String passwordImmessa)
	{
		String passwordGiusta = utente.getPassword();
		System.out.println("PASSWORD IMMESSA: 	" + passwordImmessa);
		System.out.println("PASSWORD CORRETTA:	" + passwordGiusta);
		if (passwordImmessa.equals(passwordGiusta))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
