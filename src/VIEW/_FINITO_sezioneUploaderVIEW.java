package VIEW;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import CONTROLLER._FINITO_sezioneUploaderCONTROLLER;
import GUI._FINITO_APPOGGIO_finestraAnteprimaImmagini;
import GUI._FINITO_APPOGGIO_popupAttenzione;
import GUI._FINITO_APPOGGIO_popupOk;

public class _FINITO_sezioneUploaderVIEW
{
	// -------------------------DATI IN LOCALE----------------------------------------------------------------------//

	static String[] pathImmaginiLocale;										// array da passare a qualche altra classe

	static String pathImmagineCopertinaLocale;								// path singolo dell'immagine di copertina del manoscritto

	static boolean immaginiAcquisite = false;								// flag che verrà settato a true se le immagini sono state caricate

	static boolean copertinaAcquisita = false;								// flag che verrà settato a true se l'immagine di copertina viene selezionata

	static int numPag = 0;													// inizializzazione delle pagine del Manoscritto di default a 0

	// -------------------------------------------------------------------------------------------------------------//

	// metodo che si attiva quando l'utente preme il tasto di submit
	public boolean submit(JTextField titolo, JTextField anno, JTextField autore, JComboBox generi, String nick)
	{
		// SEZIONE DATI LOCALI
		int ID_utente = new _FINITO_sezioneUploaderCONTROLLER().getIdUtente(nick);
		String titoloL = titolo.getText();
		String autoreL = autore.getText();
		String genereL = (String) generi.getSelectedItem();
		String annoL = anno.getText();

		// ----------------------CONTROLLO DELL'ORTOGRAFIA DELL'INPUT--------------------------//

		// TITOLO
		// controllo che il titolo non sia vuoto o troppo corto
		boolean title = controlloNonNull(titoloL);
		if (title == false)
		{
			String errore = "Il campo immesso è troppo corto o assente";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE TITOLO", errore);
			return false;
		}
		// controllo che il titolo non contenga caratteri illegali
		title = controlloCaratteri(titoloL);
		if (title == false)
		{
			String errore = "Non sono ammessi tali caratteri: / * - + ! \" £ $ % & / ( ) = ? ^ [ ] § # ° @";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE TITOLO", errore);
			return false;
		}
		// controllo che non esista già un manoscritto con qul titolo
		title = new _FINITO_sezioneUploaderCONTROLLER().checkTitoloManoscrittoGiaPresente(titoloL);
		if (title == false)
		{
			String errore = "Esiste già un Manoscritto con il titolo " + titoloL;
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE TITOLO", errore);
			return false;
		}

		// AUTORE
		// controllo che il campo autore non sia vuoto o troppo corto
		boolean author = controlloNonNull(autoreL);
		if (author == false)
		{
			String errore = "Il campo immesso è troppo corto o assente";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE AUTORE", errore);
			return false;
		}

		// ANNO
		// controllo che il campo immesso abbia solo numeri
		boolean year = controlloAnnoFormato(annoL);
		if (year == false)
		{
			String errore = "L'anno che hai immesso non è valido";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE ANNO", errore);
			return false;
		}
		// controllo che il campo immesso abbia esattamente 4 cifre
		year = controlloAnno4Cifre(annoL);
		if (year == false)
		{
			String errore = "L'anno che hai immesso non è valido";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE ANNO", errore);
			return false;
		}

		// ---------------------PASSAGGIO DATI CORRETTI AL CONTROLLER-------------------------//

		if (title && year)
		{
			// i parametri specificati dall'utente sono accettati, inizia la fase di registrazione del Manoscritto nel DB
			System.out.println("tutti i parametri sono corretti, acquisizione delle immagini selezionate in corso");

			if (immaginiAcquisite == false || copertinaAcquisita == false)
			{
				System.out.println("Impossibile registrare il manoscritto se non si sono selezionate le immagini da uplodare o la copertina");
				new _FINITO_APPOGGIO_popupAttenzione("UPLOAD MANOSCRITTO FALLITA", "Impossibile registrare il manoscritto se non si sono selezionate le immagini da uplodare");
				return false;
			}
			else
			{
				System.out.println("acquisizione delle immagini andate a buon fine è possibile la registrazione nel DB");
				int annoLL = Integer.parseInt(annoL);
				immaginiAcquisite = false;																										// risetto il flag a false per future iterazioni
				copertinaAcquisita = false;

				if (new _FINITO_sezioneUploaderCONTROLLER().registraManoscritto(titoloL, annoLL, numPag, genereL, autoreL))
				{
					// in questo punto è avvenuto l'inserimento del manoscritto nel DB, ora tocca alle immagini relative
					System.out.println("MANOSCRITTO INSERITO NELLA BASE DI DATI");

					System.out.println("si avvia la fase di immagazzinamento delle immagini nel database");
					int idManoscritto = new _FINITO_sezioneUploaderCONTROLLER().getIdManoscritto(titoloL);

					if (idManoscritto == 0)
					{
						System.out.println("ERRORE nel ricavarsi l'id del manoscritto dal titolo " + titoloL);
						System.out.println("immagini non registrate nel database");
						return false;
					}
					else
					{
						// è stato trovato l'id del manoscritto con il titolo specificato, adesso si effettuano gli inserimenti delle immagini
						System.out.println("-----------INSERIMENTO IMMAGINI DB-----------------");
						for (int i = 0; i < numPag; i++)
						{
							String path = pathImmaginiLocale[i];
							if (new _FINITO_sezioneUploaderCONTROLLER().inserisciImmagine(path, ID_utente, idManoscritto))
							{
								System.out.println("immagine " + i + " correttamente inserita nel DB");
								System.out.println("---------------------------------------------------");
							}
							else
							{
								System.out.println("ERRORE NEL CARICARE IMMAGINE " + i);
								return false;
							}

						}
						// sono state inserite tutte le immagini delle pagine, ora si inserisce l'immagine di copertina
						System.out.println("-----------INSERIMENTO COPERTINA DB-----------------");
						String path = pathImmagineCopertinaLocale;

						if (new _FINITO_sezioneUploaderCONTROLLER().inserisciCopertina(path, idManoscritto))
						{
							System.out.println("immagine di copertina correttamente inserita nel DB");
						}
						else
						{
							System.out.println("ERRORE NEL CARICARE L'IMMAGINE DI COPERTINA");
							return false;
						}


					}
					// inserimento del manoscritto e delle immagini a esso associate riuscito, il manoscritto e le imm. sono nel DB
					new _FINITO_APPOGGIO_popupOk("INSERIMENTO MANOSCRITTO COMPLETATO", "L'inserimento del Manoscritto è andato a buon fine!");
					return true;
				}
				else
				{
					System.out.println("MANOSCRITTO NON INSERITO NEL DB");
					System.out.println("errore nella chiamata del metodo per inserire un nuovo manoscritto nel DB");
					new _FINITO_APPOGGIO_popupAttenzione("INSERIMENTO MANOSCRITTO FALLITO", "L'inserimento del Manoscrito non è riuscito, riprova!");

					return false;
				}
			}
		}
		else
		{
			System.out.println("MANOSCRITTO NON UPLOADATO");
			System.out.println("errore nel check dell'input dell'upload");
			new _FINITO_APPOGGIO_popupAttenzione("UPLOAD FALLITA", "L'upload del manoscritto non è andata a buon fine, riprova");
			return false;
		}
	}



	// metodo che si attiva quando l'utente preme il tasto carica immagini
	public void caricaImmagini()
	{
		System.out.println("premuto il tasto per caricare le immagini");

		int numPagine;
		FileFilter jpeg = new FileNameExtensionFilter("File JPG", "jpg");

		JFileChooser jfc = new JFileChooser();
		jfc.addChoosableFileFilter(jpeg);
		jfc.setMultiSelectionEnabled(true);

		int returnVal = jfc.showOpenDialog(null);
		File[] immagini = jfc.getSelectedFiles();						// ho un array di absolute path di tutte le immagini selezionate
		ImageIcon[] immaginiIcone = new ImageIcon[immagini.length];		// mi creo un array per mettere le immagini convertite da File a ImageIcon
		pathImmaginiLocale = new String[immagini.length];				// inizializzo l'array locale di immagini che metterò da passare poi ad altre classi

		System.out.println("------------------------CARICAMENTO IMMAGINI---------------------------------------------");
		for (int i = 0; i < immagini.length; i++)
		{
			System.out.println("immagine numero: " + i + " path: 				" + immagini[i].getAbsolutePath());
			String path = immagini[i].getAbsolutePath();
			pathImmaginiLocale[i] = path;
			System.out.println("path immagine numero " + i + " è stata salvata con il path: 	" + pathImmaginiLocale[i]);
	
		}
		immaginiAcquisite = true;							// setto il flag a true, lo sarà fino ad una nuova invocazione di tale metodo
		numPag = immaginiIcone.length;
	}

	// metodo che si attiva quando l'utente preme il tasto carica immagine di copertina
	public void caricaImmagineCopertina()
	{
		System.out.println("premuto il tasto per caricare l'immagine di copertina");

		FileFilter jpeg = new FileNameExtensionFilter("File JPG", "jpg");

		JFileChooser jfc = new JFileChooser();
		jfc.addChoosableFileFilter(jpeg);

		int returnVal = jfc.showOpenDialog(null);

		File immagineCopertina = jfc.getSelectedFile();

		System.out.println("------------------------CARICAMENTO IMMAGINE COPERTINA---------------------------------------------");
		System.out.println("immagine copertina path: 				" + immagineCopertina.getAbsolutePath());
		String path = immagineCopertina.getAbsolutePath();
		pathImmagineCopertinaLocale = path;
		System.out.println("immagine di copertina salvata con il path: 	" + pathImmagineCopertinaLocale);

		copertinaAcquisita = true;
	}

	// metodo che si attiva quando l'utente preme il tasto anteprima immagini
	public void anteprimaImmagini()
	{
		System.out.println("premuto il tasto per l'anteprima delle immagini caricate");

		if (immaginiAcquisite)
		{
			new _FINITO_APPOGGIO_finestraAnteprimaImmagini(pathImmaginiLocale);
		}
		else
		{
			String errore = "Ancora nessuna immagine selezionata";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE ANTEPRIMA", errore);
		}
	}

	// metodo che si attiva qanto l'utente preme il tasto anteprima immagine copertina
	public void anteprimaCopertina()
	{
		System.out.println("premuto il tasto l'anteprima della copertina");
		String[] pathImmagineCopertinaArray = { pathImmagineCopertinaLocale };

		if (copertinaAcquisita)
		{
			new _FINITO_APPOGGIO_finestraAnteprimaImmagini(pathImmagineCopertinaArray);
		}
		else
		{
			String errore = "Ancora nessuna immagine di copertina selezionata";
			new _FINITO_APPOGGIO_popupAttenzione("ERRORE ANTEPRIMA", errore);
		}
	}

	// metodo che si attiva premendo il tasto carica immagini dal pannello REupload immagini di un manoscritto
	public void premutoAggiungiPagine(String titoloManoscritto, String nick)
	{
		int idManoscritto = new _FINITO_sezioneUploaderCONTROLLER().getIdManoscritto(titoloManoscritto);			// dal titolo adesso ho l'id del manoscritto
		int ID_utente = new _FINITO_sezioneUploaderCONTROLLER().getIdUtente(nick);

		caricaImmagini();																							// nell'array locale ho tutte le nuove immagini

		// ora che ho l'id e un array di path di immagini devo inserire le nuove immagini nel database
		for (int i = 0; i < pathImmaginiLocale.length; i++)
		{
			String path = pathImmaginiLocale[i];
			new _FINITO_sezioneUploaderCONTROLLER().inserisciImmagine(path, ID_utente, idManoscritto);
		}

		new _FINITO_APPOGGIO_popupOk("UPLOAD COMPLETATO", "Tutte le nuove immagini sono state inserite nel Manoscritto con successo!");

	}

	// ------------------METODI PER CONTROLLO DELL'INPUT------------------------------------------------//

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

	public boolean controlloAnnoFormato(String input)
	{
		try
		{
			int annoL = Integer.parseInt(input);
			return true;
		}
		catch (Exception e)
		{
			System.out.println("errore nel convertire l'anno in intero");
			System.out.println(e);
			return false;
		}
	}

	public boolean controlloAnno4Cifre(String anno)
	{
		if (anno.length() != 4)
		{
			System.out.println("L'anno immesso non ha esattamente 4 cifre");
			return false;
		}

		return true;
	}

	public boolean controlloIdSoloNumeri(String id)
	{
		try
		{
			int localID = Integer.parseInt(id);
			System.out.println("l'id contiene solo numeri");
			return true;
		}
		catch (Exception e)
		{
			System.out.println("errore nel convertire l'id in integer");
			e.printStackTrace();
			return false;
		}
	}

}
