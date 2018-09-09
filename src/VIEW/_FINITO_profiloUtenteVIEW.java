package VIEW;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import CONTROLLER._FINITO_profiloUtenteCONTROLLER;
import DAO._FINITO_profiloUtenteDAO;
import GUI.ManagerWorkbenchGUI;
import GUI._FINITO_APPOGGIO_finestraSceltaImmagine;
import GUI._FINITO_APPOGGIO_popupAttenzione;
import GUI._FINITO_APPOGGIO_popupOk;
import GUI._FINITO_TrascriberWorkbenchGUI;
import GUI._FINITO_UploaderGUI;
import GUI._FINITO_UtenteProfiloGUI;
import MODEL.Utente;

public class _FINITO_profiloUtenteVIEW
{

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------------//

	// metodo che permette di rendere editabile i campi per modificare i dati
	public void modificaUtente(JTextField nome, JTextField cognome, JComboBox professione, JComboBox titolo, JTextField data)
	{
		nome.setEditable(true);
		cognome.setEditable(true);
		professione.setEnabled(true);
		titolo.setEnabled(true);
		data.setEditable(true);

		System.out.println("dalla view si è usato il metodo relativo al click del bottone modifica");
	}

	// metodo per confermare le modifiche fatte dall'utente, si fa un primo controllo del nuovo input
	public boolean confermaModifiche(JTextField nick, JTextField nome, JTextField cognome, JComboBox professione, JComboBox titolo, JTextField data)
	{
		// SEZIONE DATI LOCALI
		String nickL = nick.getText();
		String nomeL = nome.getText();
		String cognomeL = cognome.getText();
		String titoloL = (String) titolo.getSelectedItem();
		String professioneL = (String) professione.getSelectedItem();
		String dataL = data.getText();


		// ----------------------CONTROLLO DELL'ORTOGRAFIA DELL'INPUT--------------------------//

		// NOME
		// controllo che il nome non sia vuoto o troppo corto
		boolean name = controlloNonNull(nomeL);
		if (name == false)
		{
			String errore = "Il campo immesso è troppo corto o assente";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE NOME", errore);
			return false;
		}
		// controllo sul nome che non contenga caratteri illegali
		name = controlloCaratteri(nomeL);
		if (name == false)
		{
			String errore = "Non sono ammessi tali caratteri: / * - + ! \" £ $ % & / ( ) = ? ^ [ ] § # ° @";
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
			String errore = "Il campo immesso è troppo corto o assente";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE COGNOME", errore);
			return false;
		}
		// controllo che il cognome non contenga caratteri illegali
		surname = controlloCaratteri(cognomeL);
		if (surname == false)
		{
			String errore = "Non sono ammessi tali caratteri: / * - + ! \" £ $ % & / ( ) = ? ^ [ ] § # ° @";
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

		// DATA DI NASCITA
		boolean dataDiNascita = controlloNonNull(dataL);
		if (dataDiNascita == false)
		{
			String errore = "Il campo immesso è troppo corto o assente";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE DATA", errore);
			return false;
		}
		// controllo sulla data di nascita
		dataDiNascita = controlloData(dataL);

		// ---------------------PASSAGGIO DATI CORRETTI AL CONTROLLER-------------------------//

		if (name && surname && dataDiNascita)
		{
			if (new _FINITO_profiloUtenteCONTROLLER().confermaModifiche(nickL, nomeL, cognomeL, dataL, titoloL, professioneL))
			{
				System.out.println("MODIFICHE UTENTE ACCETTATE");
				nome.setEditable(false);
				cognome.setEditable(false);
				professione.setEnabled(false);
				titolo.setEnabled(false);
				data.setEditable(false);
				new _FINITO_APPOGGIO_popupOk("MODIFICHE COMPLETATE", "Modifiche confermate");

				return true;
			}
			else
			{
				System.out.println("ERRORE NELLA MODIFICA DEI DATI UTENTE");
				System.out.println("Errore nella chiamata del metodo per modificare i dati utenti");
				new _FINITO_APPOGGIO_popupAttenzione("MODIFICA DATI UTENTE FALLITA", "Le modifiche dei dati dell'utente non sono andati a buon fine, riprova");

				return false;
			}
		}
		else
		{
			System.out.println("MODIFICHE UTENTE ACCETTATE");
			System.out.println("Errore nel check dell'input di modifica");
			new _FINITO_APPOGGIO_popupAttenzione("MODIFICA DATI UTENTE FALLITA", "Le modifiche dei dati dell'utente non sono andati a buon fine, ricontrolla tutti i campi");

			return false;
		}
	}

	// metodo per modificare l'immagine del profilo
	public void modificaImmagine(_FINITO_UtenteProfiloGUI soggetto, String nick)
	{
		System.out.println("dalla view si è usato il metodo relativo al click del bottone cambia immagine profilo");
		new _FINITO_APPOGGIO_finestraSceltaImmagine(soggetto, nick);
	}

	// metodo per rendere l'immagine scelta effettiva
	public void updateImmagine(_FINITO_UtenteProfiloGUI soggetto, String nick, String pathImmagine)
	{
		System.out.println("passaggio alla VIEW per settaggio immagine profilo");
		boolean risposta = new _FINITO_profiloUtenteCONTROLLER().modificaImmagineProfilo(nick, pathImmagine);

		if (risposta == true)
		{
			System.out.println("la VIEW registra l'avvenuta modifica dell'immagine del profilo");
			soggetto.Foto.setIcon(new ImageIcon(pathImmagine));
		}
		else
		{
			System.out.println("errore nella modifica dell'immagine di profilo");
		}

	}

	// metodo per quando si apre il profilo utente restituisce l'immagine scelta in precedenza
	public String getImmagineProfilo(String nick)
	{
		System.out.println("passaggio alla VIEW per ricavarsi il path dell'immagine del profilo che l'utente ha scelto");
		String path = new _FINITO_profiloUtenteDAO().getImmagineProfilo(nick);
		return path;
	}

	// metodo per richiedere di diventare trascrittore
	public void diventaTrascrittore(String nick, boolean trascrittore, boolean manager, boolean uploader, JFrame soggetto, String nome, String cognome, String data_nascita, String email, String password, String titolo_studio, String professione, boolean vip)
	{
		if (trascrittore == true || manager == true || uploader == true)
		{
			new _FINITO_APPOGGIO_popupAttenzione("ATTENZIONE", "Impossibile diventare trascrittore, controlla la tua mansione, se non sei già trascrittore, puoi avere una sola mansione alla volta!");
		}
		else
		{
			boolean esito = new _FINITO_profiloUtenteDAO().diventaTrascrittore(nick);

			if (esito)
			{
				new _FINITO_APPOGGIO_popupOk("PROMOZIONE EFFETTUATA", "Complimenti ora sei un trascrittore!");
				soggetto.dispose();
				new _FINITO_UtenteProfiloGUI(nick, nome, cognome, data_nascita, email, password, titolo_studio, professione, vip, manager, uploader, true);
			}
			else
			{
				new _FINITO_APPOGGIO_popupAttenzione("PROMOZIONE FALLITA", "La promozione a Trascrittore non è andata a buon fine");
			}
		}
	}

	// metodo per aprire la sezione di lavoro dell'uploader
	public void apriUploader(String nick)
	{
		Utente utenteLocale = new _FINITO_profiloUtenteCONTROLLER().getUtente(nick);
		boolean uploader = utenteLocale.isUtente_uploader();

		if (uploader)
		{
			new _FINITO_UploaderGUI(nick);
		}
		else
		{
			new _FINITO_APPOGGIO_popupAttenzione("ACCESSO NEGATO", "<html>Impossibile aprire la sezione di lavoro per Uploader, non si dispongono dei permessi necessari!</html>");
		}
	}

	// metodo per aprire la sezione di lavoro del trascriber
	public void apriTrascrittore(String nick)
	{
		Utente utenteLocale = new _FINITO_profiloUtenteCONTROLLER().getUtente(nick);
		boolean trascrittore = utenteLocale.isUtente_trascrittore();

		if (trascrittore)
		{
			new _FINITO_TrascriberWorkbenchGUI(nick);
		}
		else
		{
			new _FINITO_APPOGGIO_popupAttenzione("ACCESSO NEGATO", "<html>Impossibile aprire la sezione di lavoro per il Trascrittore, non si dispongono dei permessi necessari!</html>");
		}
	}

	// metodo per aprire la sezione di lavoro del manager
	public void apriManager(String nick)
	{
		Utente utenteLocale = new _FINITO_profiloUtenteCONTROLLER().getUtente(nick);
		boolean manager = utenteLocale.isUtente_manager();

		if (manager)
		{
			new ManagerWorkbenchGUI(nick);
		}
		else
		{
			new _FINITO_APPOGGIO_popupAttenzione("ACCESSO NEGATO", "<html>Impossibile aprire la sezione di lavoro per il Manager, non si dispongono dei permessi necessari!</html>");
		}
	}

	// ---------------------METODI PER CONTROLLO DELL'INPUT-------------------------//

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

	public boolean controlloData(String data)
	{
		boolean flag = true;
		if (data.length() > 10)
		{
			String erroreDataLunghezza = "La data che hai immesso è troppo lunga, ricontrolla!";
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
		// se non si sono riscontrati errori, vuol dire che la data è corretta e returno true
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

	// --------------------------------------------------------------------------------//

}
