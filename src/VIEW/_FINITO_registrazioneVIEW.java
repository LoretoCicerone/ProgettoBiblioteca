package VIEW;

import javax.swing.JComboBox;

import CONTROLLER._FINITO_registrazioneCONTROLLER;
import GUI._FINITO_APPOGGIO_popupAttenzione;
import GUI._FINITO_APPOGGIO_popupOk;
import GUI._FINITO_UtenteProfiloGUI;

public class _FINITO_registrazioneVIEW
{
	// metodo per controllare i dati in input, per questioni di praticit‡, si Ë scelto di suddivifere il metodo
	// in tanti sottometodi presenti nella sezione sottostante METODI PER CONTROLLO DELL'INPUT
	public boolean controllaDatiInseriti(String username, String nome, String cognome, String email, String confEmail, JComboBox professione, String data, String passw, String confPassw, JComboBox titoli)
	{
		// SEZIONE DATI LOCALI
		String nickL = username;
		String nomeL = nome;
		String cognomeL = cognome;
		String emailL = email;
		String confermaEmailL = confEmail;
		String passwordL = passw;
		String confermaPasswordL = confPassw;
		String titoloL = (String) titoli.getSelectedItem();
		String professioneL = (String) professione.getSelectedItem();
		String dataL = data;


		// ----------------------CONTROLLO DELL'ORTOGRAFIA DELL'INPUT--------------------------//

		// NICKNAME
		// controllo che il nickname sia presente
		boolean user = controlloNonNull(nickL);
		if (user == false)
		{
			String errore = "Il campo immesso Ë troppo corto o assente";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE NICKNAME", errore);
			return false;
		}
		// controllo che il nickname non abbia caratteri illegali
		user = controlloCaratteri(nickL);
		if (user == false)
		{
			String errore = "Non sono ammessi tali caratteri: / * - + ! \" £ $ % & / ( ) = ? ^ [ ] ß # ∞ @";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE NICKNAME", errore);
			return false;
		}
		// controllo lunghezza massima del nickname
		user = controlloLunghezza(nickL);
		if (user == false)
		{
			String errore = "Campo immesso troppo lungo, massimo 30 caratteri";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE NICKNAME", errore);
			return false;
		}
		// controllo sul nickname che non sia gia in uso
		user = controlloNicknameOccupato(nickL);
		if (user == false)
		{
			String errore = "Errore, " + nickL + " Ë gi‡ esistente";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE NICKNAME", errore);
			return false;
		}

		// NOME
		// controllo che il nome non sia vuoto o troppo corto
		boolean name = controlloNonNull(nomeL);
		if (name == false)
		{
			String errore = "Il campo immesso Ë troppo corto o assente";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE NOME", errore);
			return false;
		}
		// controllo sul nome che non contenga caratteri illegali
		name = controlloCaratteri(nomeL);
		if (name == false)
		{
			String errore = "Non sono ammessi tali caratteri: / * - + ! \" £ $ % & / ( ) = ? ^ [ ] ß # ∞ @";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE NOME", errore);
			return false;
		}
		// controllo lunghezza massima del nome
		name = controlloLunghezza(nomeL);
		if (name == false)
		{
			String errore = "Campo immesso troppo lungo, massimo 30 caratteri";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE NOME", errore);
			return false;
		}

		// COGNOME
		// controllo che il cognome non sia vuoto o troppo corto
		boolean surname = controlloNonNull(cognomeL);
		if (surname == false)
		{
			String errore = "Il campo immesso Ë troppo corto o assente";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE COGNOME", errore);
			return false;
		}
		// controllo che il cognome non contenga caratteri illegali
		surname = controlloCaratteri(cognomeL);
		if (surname == false)
		{
			String errore = "Non sono ammessi tali caratteri: / * - + ! \" £ $ % & / ( ) = ? ^ [ ] ß # ∞ @";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE COGNOME", errore);
			return false;
		}
		// controllo lunghezza massima del cognome
		surname = controlloLunghezza(cognomeL);
		if (surname == false)
		{
			String errore = "Campo immesso troppo lungo, massimo 30 caratteri";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE COGNOME", errore);
			return false;
		}

		// EMAIL
		// controllo che l'email non sia vuota o troppo corta
		boolean emaill = controlloNonNull(emailL);
		if (emaill == false)
		{
			String errore = "Il campo immesso Ë troppo corto o assente";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE EMAIL", errore);
			return false;
		}
		// controllo che l'email abbia almeno la @
		emaill = controlloEmail(emailL);
		if (emaill == false)
		{
			String errore = "Inserisci un email valida, ricordati di mettere la @";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE EMAIL", errore);
			return false;
		}
		// controllo che le email siano uguali
		emaill = controlloEmailConferma(emailL, confermaEmailL);
		if (emaill == false)
		{
			String errore = "Le email immesse non sono uguali";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE EMAIL", errore);
			return false;
		}

		// PASSWORD
		// controllo che la password non sia vuota o troppo corta
		boolean matchPass = controlloNonNull(passwordL);
		if (matchPass == false)
		{
			String errore = "Il campo immesso Ë troppo corto o assente";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE PASSWORD", errore);
			return false;
		}
		// controllo che le password siano uguali
		matchPass = controlloPassword(passwordL, confermaPasswordL);
		if (matchPass == false)
		{
			String errore = "Le password immesse non sono uguali";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE PASSWORD", errore);
			return false;
		}

		// DATA DI NASCITA
		boolean dataDiNascita = controlloNonNull(dataL);
		if (dataDiNascita == false)
		{
			String errore = "Il campo immesso Ë troppo corto o assente";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE DATA", errore);
			return false;
		}
		// controllo sulla data di nascita
		dataDiNascita = controlloData(dataL);

		// ---------------------PASSAGGIO DATI CORRETTI AL CONTROLLER-------------------------//
		if (user && name && surname && emaill && matchPass && dataDiNascita)
		{
			if (new _FINITO_registrazioneCONTROLLER().registraUtente(nickL, nomeL, cognomeL, dataL, confermaEmailL, confermaPasswordL, titoloL, professioneL))
			{
				System.out.println("UTENTE REGISTRATO");
				new _FINITO_APPOGGIO_popupOk("REGISTRAZIONE COMPLETATA", "Congratulazioni, ti sei registrato correttamente, ora puoi iniziare a utilizzare la piattaforma.");
				registraUtente(username, nomeL, cognomeL, dataL, confermaEmailL, confermaPasswordL, titoloL, professioneL);

				return true;
			}
			else
			{
				System.out.println("UTENTE NON REGISTRATO");
				System.out.println("errore dopo la chiamata del metodo per creare un nuovo utente");
				new _FINITO_APPOGGIO_popupAttenzione("REGISTRAZIONE FALLITA", "La registrazione non Ë andata a buon fine, riprova");
				return false;
			}
		}
		else
		{
			System.out.println("UTENTE NON REGISTRATO");
			System.out.println("errore per il check dell'input dell'iscrizione");
			new _FINITO_APPOGGIO_popupAttenzione("REGISTRAZIONE FALLITA", "La registrazione non Ë andata a buon fine, riprova");
			return false;
		}
	}

	// metodo per controllare che il nickname inserito non esista gi‡
	public boolean controlloNicknameOccupato(String nickname)
	{
		String erroreUsernameEsistente = "Username gi‡ in uso, scegline un altro";
		System.out.println("passaggio alla view");

		boolean esito = new _FINITO_registrazioneCONTROLLER().datigi‡Esistenti(nickname);

		if (esito == false)
		{
			String erroreUsername = "Errore Username in uso";
			System.out.println(erroreUsername);
		}
		else if (esito == true)
		{
			System.out.println("nickname non in uso");
		}

		return esito;
	}

	// metodo che fatti tutti i controlli va effettivamente a registrare il nuovo utente nel DB
	public void registraUtente(String nickname, String nome, String cognome, String data_nascita, String email, String password, String titolo_studio, String professione)
	{
		new _FINITO_UtenteProfiloGUI(nickname, nome, cognome, data_nascita, email, password, titolo_studio, professione, false, false, false, false);
	}

	// ---------------------METODI PER CONTROLLO DELL'INPUT-------------------------//

	public boolean controlloNonNull(String input)
	{
		if (input.length() <= 1)
		{
			String erroreLunghezza = "Il campo immesso Ë troppo corto o assente";
			System.out.println(erroreLunghezza);

			return false;
		}
		else
		{
			return true;
		}
	}

	public boolean controlloCaratteri(String input)
	{
		char carateriNonAmmessi[] = { '/', '*', '-', '+', '!', '"', '£', '$', '%', '&', '(', ')', '=', '?', '^', '[', ']', '#', '∞', 'ß', '@' };
		String inputDaControllare = input;

		for (int i = 0; i < input.length(); i++)
		{
			for (int z = 0; z < carateriNonAmmessi.length; z++)
			{
				if (input.charAt(i) == carateriNonAmmessi[z])
				{
					String erroreCaratteri = "Non sono ammessi tali caratteri: / * - + ! \" £ $ % & / ( ) = ? ^ [ ] ß # ∞ @ ";

					System.out.println(erroreCaratteri);
					return false;
				}
			}
		}
		return true;
	}

	public boolean controlloLunghezza(String input)
	{
		if (input.length() > 30)
		{
			String erroreLunghezza = "Campo immesso troppo lungo, massimo 30 caratteri";

			System.out.println(erroreLunghezza);

			return false;
		}
		return true;
	}

	public boolean controlloPassword(String password, String confPassword)
	{
		if (confPassword.equals(password) != true)
		{
			String errorePassword = "Le password immesse non sono uguali";

			System.out.println(errorePassword);

			return false;
		}
		return true;
	}

	public boolean controlloData(String data)
	{
		boolean flag = true;
		if (data.length() > 10)
		{
			String erroreDataLunghezza = "La data che hai immesso Ë troppo lunga, ricontrolla!";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE DATA", erroreDataLunghezza);
			System.out.println(erroreDataLunghezza);
			flag = false;
			return false;
		}
		else if (data.charAt(4) != '-' || data.charAt(7) != '-')
		{
			String erroreDataFormato = "La data che hai immesso deve contenere il carattere - tra l'anno e il mese e tra il mese e il giorno, rispetta questo formato: YYYY-MM-dd";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE DATA", erroreDataFormato);
			System.out.println(erroreDataFormato);
			flag = false;
			return false;

		}
		else if (flag)
		{
			// acquisizione del mese
			String mese = "";
			for (int i = 5; i < data.length();)
			{
				if (data.charAt(i) == '0')
				{
					mese = mese + data.charAt(i + 1);
					break;
				}
				else if (data.charAt(i) == '1')
				{
					char secondaCifra = data.charAt(i + 1);
					mese = "1" + secondaCifra;
					break;
				}
				else
				{
					mese = "" + data.charAt(i) + data.charAt(i + 1);
					break;
				}
			}
			// controllo che il mese non sia maggiore di 12
			int meseInt = Integer.parseInt(mese);
			if (meseInt > 12)
			{
				String erroreDataMese = "Errore nell'immisione del mese, il mese " + meseInt + " non esiste, aspetta che cambino il calendario per immettere questo mese :)";
				new _FINITO_APPOGGIO_popupAttenzione("ERRORE DATA", erroreDataMese);
				System.out.println(erroreDataMese);

				flag = false;
				return false;
			}
			// acquisizione del giorno
			String giorno = "";
			for (int i = 8; i < data.length();)
			{
				if (data.charAt(i) == '0')
				{
					giorno = giorno + data.charAt(i + 1);
					break;
				}
				else if (data.charAt(i) == '1' || data.charAt(i) == '2' || data.charAt(i) == '3')
				{
					char secondaCifra = data.charAt(i + 1);
					giorno = "" + data.charAt(i) + secondaCifra;
					break;
				}
				else
				{
					// mese = "" + data.charAt(i) + data.charAt(i + 1);
					String erroreData = "Errore nella specifica del giorno";
					new _FINITO_APPOGGIO_popupAttenzione("ERRORE DATA", erroreData);
					return false;
				}
			}
			// controllo che il giorno non sia maggiore di 31
			int giornoInt = Integer.parseInt(giorno);
			if (giornoInt > 31)
			{
				String erroreDataGiorno = "Errore nell'immissione del giorno, il mese " + giornoInt + " non esiste, aspetta che cambino il calendario per immettere questo giorno :)";
				new _FINITO_APPOGGIO_popupAttenzione("ERRORE DATA", erroreDataGiorno);
				System.out.println(erroreDataGiorno);

				flag = false;
				return false;
			}
			// controllo che i giorni del mese indicato non superino i giorni ammissibili dal mese
			if (flag)
			{
				switch (meseInt)
				{
					case 2:
						if (giornoInt > 28)
						{
							String erroreDataGiorno = "Hai immesso troppi giorni per Febbraio, ne ha solo 28!";
							new _FINITO_APPOGGIO_popupAttenzione("ERRORE DATA", erroreDataGiorno);
							System.out.println(erroreDataGiorno);

							flag = false;
							return false;
						}
					case 4:
						if (giornoInt > 30)
						{
							String erroreDataGiorno = "Hai immesso troppi giorni per Aprile, ne ha solo 30!";
							new _FINITO_APPOGGIO_popupAttenzione("ERRORE DATA", erroreDataGiorno);
							System.out.println(erroreDataGiorno);

							flag = false;
							return false;
						}
					case 6:
						if (giornoInt > 30)
						{
							String erroreDataGiorno = "Hai immesso troppi giorni per Giugno, ne ha solo 30!";
							new _FINITO_APPOGGIO_popupAttenzione("ERRORE DATA", erroreDataGiorno);
							System.out.println(erroreDataGiorno);

							flag = false;
							return false;
						}
					case 9:
						if (giornoInt > 30)
						{
							String erroreDataGiorno = "Hai immesso troppi giorni per Settembre, ne ha solo 30!";
							new _FINITO_APPOGGIO_popupAttenzione("ERRORE DATA", erroreDataGiorno);
							System.out.println(erroreDataGiorno);

							flag = false;
							return false;
						}
					case 11:
						if (giornoInt > 30)
						{
							String erroreDataGiorno = "Hai immesso troppi giorni per Novembre, ne ha solo 30!";
							new _FINITO_APPOGGIO_popupAttenzione("ERRORE DATA", erroreDataGiorno);
							System.out.println(erroreDataGiorno);

							flag = false;
							return false;
						}
				}
			}
		}
		// se non si sono riscontrati errori, vuol dire che la data Ë corretta e returno true
		return true;
	}

	public boolean controlloEmail(String email)
	{
		boolean trovato = false;
		for (int i = 0; i < email.length(); i++)
		{
			if (email.charAt(i) == '@')
			{
				trovato = true;
			}
		}
		if (trovato == false)
		{
			String erroreEmail = "Inserisci un email valida, ricordati di mettere la @";
			System.out.println(erroreEmail);
		}
		return trovato;
	}

	public boolean controlloEmailConferma(String email, String conEmail)
	{
		if (conEmail.equals(email) != true)
		{
			String erroreEmail = "Le email immesse non sono uguali";

			System.out.println(erroreEmail);

			return false;
		}

		return true;
	}

}


