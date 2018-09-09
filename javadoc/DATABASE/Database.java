package DATABASE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.A_MANOSCRITTO_1_Pagina;
import MODEL.Utente;

public class Database
{
	// METODO PER LA CONNESSIONE AL DATABASE, VIENE RICHIAMATO PER OGNI AZIONE

	public static Connection connessioneDB()
	{
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/BibliotecaDigitaleOOSD?autoReconnect=true&useSSL=false", "root", "17051996");
		}
		catch (SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("ERROR:" + ex.getErrorCode());
			ex.printStackTrace();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return conn;
	}


	// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//


	// metodo per inserire un nuovo utente dal pannello della registrazione
	public static int inserimentoNuovoUtente(String nickname, String nome, String cognome, String data_nascita, String email, String password, String titolo_studio, String professione) throws SQLException
	{
		int id = 0;
		String query = "Insert into utente_loggato (nickname, nome, cognome, data_nascita, email, pass_word, titolo_studio, professione) values(?,?,?,?,?,?,?,?) ;";

		Connection connessione = connessioneDB();
		PreparedStatement cmd = connessione.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

		cmd.setString(1, nickname);
		cmd.setString(2, nome);
		cmd.setString(3, cognome);
		cmd.setString(4, data_nascita);
		cmd.setString(5, email);
		cmd.setString(6, password);
		cmd.setString(7, titolo_studio);
		cmd.setString(8, professione);

		cmd.executeUpdate();

		ResultSet risultato = cmd.getGeneratedKeys();

		while (risultato.next())
		{
			id = risultato.getInt(1);
		}

		risultato.close();
		cmd.close();

		return id;
	}

	// metodo per inserire nel DB un nuovo manoscritto dal pannello Uploader
	public static int inserimentoNuovoManoscritto(String titolo, int anno, int numPag, String genere, String autore) throws SQLException
	{
		int id = 0;
		String query = "INSERT INTO Manoscritto (titolo, anno_pubblicazione, numero_pagine, genere, nome_autore) values(?,?,?,?,?);";

		Connection connessione = connessioneDB();
		PreparedStatement cmd = connessione.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

		cmd.setString(1, titolo);
		cmd.setInt(2, anno);
		cmd.setInt(3, numPag);
		cmd.setString(4, genere);
		cmd.setString(5, autore);

		cmd.executeUpdate();

		ResultSet risultato = cmd.getGeneratedKeys();

		while (risultato.next())
		{
			id = risultato.getInt(1);
		}

		risultato.close();
		cmd.close();

		return id;
	}

	// metodo per inserire nel DB l'immagine di copertina di un manoscritto, dal pannello Uploader
	public static boolean inserimentoNuovaCopertinaManoscritto(String path, int ID_manoscritto)
	{
		try
		{
			Connection conn = connessioneDB();

			String query = "UPDATE Manoscritto SET immagine_copertina = ? WHERE ID = ?";

			PreparedStatement interrogazione = conn.prepareStatement(query);
			interrogazione.setString(1, path);
			interrogazione.setInt(2, ID_manoscritto);

			interrogazione.executeUpdate();
			conn.close();

			return true;
		}
		catch (Exception e)
		{
			System.out.println("Errore nell'inserimento della copertina nel DB");

			return false;
		}

	}

	// metodo per inserire nel DB un immagine sotto forma di path, dal pannello Uploader
	public static int inserimentoNuovaImmagine(String path, int ID_uploader, int ID_manoscritto) throws SQLException
	{
		String stato = "In Attesa";
		int id = 0;

		String query = "INSERT INTO immagine (path_immagine,ID_uploader,ID_manoscritto,stato) values (?,?,?,?);";

		Connection connessione = connessioneDB();
		PreparedStatement cmd = connessione.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

		cmd.setString(1, path);
		cmd.setInt(2, ID_uploader);
		cmd.setInt(3, ID_manoscritto);
		cmd.setString(4, stato);

		cmd.executeUpdate();

		ResultSet risultato = cmd.getGeneratedKeys();

		while (risultato.next())
		{
			id = risultato.getInt(1);
		}

		risultato.close();
		cmd.close();

		return id;
	}

	// metodo che inserisce nel database la trascrizione quando il trascriber fa click sul bottone salva
	public static boolean inserimentoTrascrizione(String testo, int ID_manoscritto, int ID_immagine) throws SQLException
	{
		int id = 0;
		String query = "insert into trascrizione (testo,ID_manoscritto,ID_immagine) values (?,?,?);";
		Connection conn = connessioneDB();
		PreparedStatement cmd = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

		cmd.setString(1, testo);
		cmd.setInt(2, ID_manoscritto);
		cmd.setInt(3, ID_immagine);

		cmd.executeUpdate();
		ResultSet risultato = cmd.getGeneratedKeys();

		while (risultato.next())
		{
			id = risultato.getInt(1);
		}

		risultato.close();
		cmd.close();

		if (id == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	// metodo che registra che l'utente ha visualizzato l'opera
	public static boolean inserisciConsultazione(int ID_utente, int ID_manoscritto) throws SQLException
	{
		int id = 0;
		String query = "insert into consultazione (ID_utente,ID_manoscritto) values (?,?)";
		Connection conn = connessioneDB();
		PreparedStatement cmd = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

		cmd.setInt(1, ID_utente);
		cmd.setInt(2, ID_manoscritto);

		cmd.executeUpdate();
		ResultSet risultato = cmd.getGeneratedKeys();

		while (risultato.next())
		{
			id = risultato.getInt(1);
		}

		risultato.close();
		cmd.close();

		if (id == 0)
		{
			return false;
		}
		else
		{
			return true;
		}

	}

	// metodo che inserisce nella tabella download l'avvenuto download
	public static boolean inserisciDownload(int ID_utente, int ID_immagine) throws SQLException
	{
		int id = 0;
		String query = "insert into download (ID_utente,ID_immagine) values (?,?)";
		Connection conn = connessioneDB();
		PreparedStatement cmd = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

		cmd.setInt(1, ID_utente);
		cmd.setInt(2, ID_immagine);

		cmd.executeUpdate();
		ResultSet risultato = cmd.getGeneratedKeys();

		while (risultato.next())
		{
			id = risultato.getInt(1);
		}

		risultato.close();
		cmd.close();

		if (id == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//


	// metodo per controllare che esiste il nickname immesso
	public static boolean checkNickname(String query) throws SQLException
	{
		Connection conn = connessioneDB();
		PreparedStatement interrogazione = conn.prepareStatement(query);
		ResultSet risultato = interrogazione.executeQuery();

		risultato.last();
		int registri = risultato.getRow();

		if (registri > 0)
		{
			risultato.beforeFirst();
			while (risultato.next())
			{
				risultato.getString("nickname");
			}
			System.out.println("nickaname in uso");
			risultato.close();
			conn.close();
			return false;
		}
		else
		{
			System.out.println("nickname non in uso");
			risultato.close();
			conn.close();
			return true;
		}


	}

	// metodo per controllare che al login, quando si preme su accedi, il nicknme inserito esista
	public static boolean checkNicknameLogin(String query) throws SQLException
	{
		System.out.println("passaggio al database controllo nickname per login");
		Connection conn = connessioneDB();

		PreparedStatement interrogazione = conn.prepareStatement(query);

		ResultSet risultato = interrogazione.executeQuery();

		risultato.last();
		int registri = risultato.getRow();

		if (registri > 0)
		{
			risultato.beforeFirst();
			while (risultato.next())
			{
				risultato.getString("nickname");
			}
			System.out.println("nickaname trovato");
			risultato.close();
			conn.close();
			return true;
		}
		else
		{
			System.out.println("nickname inesistente");
			risultato.close();
			conn.close();
			return false;
		}
	}

	// metodo che dato il titolo di un manoscritto controlla che non sia già presente nel DB
	public static boolean checkTitoloManoscrittoGiaPresente(String titolo) throws SQLException
	{
		try
		{
			String query = "SELECT titolo FROM Manoscritto WHERE titolo = ?";
			int id = 0;

			Connection connessione = connessioneDB();
			PreparedStatement cmd = connessione.prepareStatement(query);
			cmd.setString(1, titolo);

			ResultSet risultato = cmd.executeQuery();

			risultato.last();
			int registri = risultato.getRow();

			if (registri > 0)
			{
				risultato.beforeFirst();
				while (risultato.next())
				{
					risultato.getString("titolo");
				}
				System.out.println("titolo gia registrato");
				risultato.close();
				connessione.close();
				return false;
			}
			else
			{
				System.out.println("titolo inesistente");
				risultato.close();
				connessione.close();
				return true;
			}
		}
		catch (SQLException e)
		{
			System.out.println("ERRORE nella connessione al DB");
			e.printStackTrace();
			return false;
		}
	}


	// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//


	// metodo per ricavarmi l'Amministratore, si attiva quando nel pannello login utilizzo come nickname e password: Administrator
	public static Utente getAdministrator(String user) throws SQLException
	{
		Utente utente = null;
		String query = "SELECT * FROM Amministratore WHERE nicknameAmministratore=? ;";

		Connection connessione = connessioneDB();
		PreparedStatement cmd = connessione.prepareStatement(query);
		cmd.setString(1, user);

		ResultSet risultato = cmd.executeQuery();

		while (risultato.next())
		{
			utente = new Utente();
			utente.setNickname(risultato.getString("nicknameAmministratore"));
			utente.setNome(risultato.getString("nome"));
			utente.setCognome(risultato.getString("cognome"));
			utente.setData_nascita(risultato.getString("data_nascita"));
			utente.setEmail(risultato.getString("email"));
			utente.setPassword(risultato.getString("pass_word"));
			utente.setProfessione(risultato.getString("professione"));
		}

		risultato.close();
		cmd.close();

		return utente;

	}

	// metodo per ricavarsi l'utente una volta specificato il nickname, utile a livello di debugging e per la creazione di un entita
	public static Utente getUtente(String user) throws SQLException
	{
		Utente utente = null;
		String query = "SELECT * FROM utente_loggato WHERE nickname=? ;";

		Connection connessione = connessioneDB();
		PreparedStatement cmd = connessione.prepareStatement(query);
		cmd.setString(1, user);

		ResultSet risultato = cmd.executeQuery();

		while (risultato.next())
		{
			utente = new Utente();
			utente.setID(risultato.getInt("ID"));
			utente.setNickname(risultato.getString("nickname"));
			utente.setNome(risultato.getString("nome"));
			utente.setCognome(risultato.getString("cognome"));
			utente.setData_nascita(risultato.getString("data_nascita"));
			utente.setEmail(risultato.getString("email"));
			utente.setPassword(risultato.getString("pass_word"));
			utente.setProfessione(risultato.getString("professione"));
			utente.setTitolostudio(risultato.getString("titolo_studio"));
			utente.setUtente_manager(risultato.getBoolean("utente_manager"));
			utente.setUtente_trascrittore(risultato.getBoolean("utente_transcriber"));
			utente.setUtente_uploader(risultato.getBoolean("utente_uploader"));
			utente.setUtente_vip(risultato.getBoolean("utente_vip"));
		}

		risultato.close();
		cmd.close();

		return utente;
	}

	// metodo che data una lista di pagine restituisce una lista di manoscritti che contengono almeno una delle pagine nella lista data
	public static ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscrittiAssegnatiTrascrittore(int ID_trascrittore) throws SQLException
	{
		ArrayList<A_MANOSCRITTO_0_Manoscritto> localList = new ArrayList<A_MANOSCRITTO_0_Manoscritto>();
		String query = "select distinct m.titolo,m.id,m.nome_autore,m.anno_pubblicazione,m.genere,m.numero_pagine,m.immagine_copertina from manoscritto m join immagine i on (m.id = i.id_manoscritto) join assegnazione a on (a.IDimmagine = i.id) join utente_loggato u on (u.id = a.IDtrascrittore) where a.IDtrascrittore = ? order by i.id";

		Connection connessione = connessioneDB();
		PreparedStatement cmd = connessione.prepareStatement(query);

		cmd.setInt(1, ID_trascrittore);
		ResultSet risultato = cmd.executeQuery();

		while (risultato.next())
		{
			A_MANOSCRITTO_0_Manoscritto localManoscritto = new A_MANOSCRITTO_0_Manoscritto(risultato.getInt("ID"), risultato.getString("nome_autore"), risultato.getString("titolo"), risultato.getString("anno_pubblicazione"), risultato.getString("genere"), risultato.getInt("numero_pagine"), risultato.getString("immagine_copertina"));
			localList.add(localManoscritto);
		}

		cmd.close();

		return localList;
	}

	// metodo che dato l'ID del trascrittore e l'ID del manoscritto restituisce una lista di tutte le pagine di quel manoscritto assegnate al trascrittore
	public static ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineDelManoscrittoAssegnate(int ID_trascrittore, int ID_manoscritto) throws SQLException
	{
		ArrayList<A_MANOSCRITTO_1_Pagina> localList = new ArrayList<A_MANOSCRITTO_1_Pagina>();
		String query = "select i.ID ,i.path_immagine,i.stato,i.ID_manoscritto from immagine i join manoscritto m on (i.ID_manoscritto = m.ID) join assegnazione a on (a.IDimmagine = i.ID) join utente_loggato u on (u.ID = a.IDtrascrittore) where i.ID_manoscritto = ? and a.IDtrascrittore = ?";

		Connection connessione = connessioneDB();
		PreparedStatement cmd = connessione.prepareStatement(query);
		cmd.setInt(1, ID_manoscritto);
		cmd.setInt(2, ID_trascrittore);

		ResultSet risultato = cmd.executeQuery();

		while (risultato.next())
		{
			A_MANOSCRITTO_1_Pagina localPagina = new A_MANOSCRITTO_1_Pagina(risultato.getInt("ID_manoscritto"), risultato.getString("path_immagine"), risultato.getString("stato"), risultato.getInt("ID"));
			localList.add(localPagina);
		}

		risultato.close();
		cmd.close();

		return localList;
	}

	// metodo che dato l'id del manoscritto ritorna una lista di tutte le trascrizioni di quel manoscritto
	public static ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineConTrascrizioneManoscritto(int ID_manoscritto) throws SQLException
	{
		ArrayList<A_MANOSCRITTO_1_Pagina> localList = new ArrayList<A_MANOSCRITTO_1_Pagina>();
		String query = "select testo,t.ID_manoscritto,ID_immagine,path_immagine,i.stato,t.ID as ID_trascrizione,t.stato as stato_trascrizione from trascrizione t join immagine i on (i.ID = t.ID_immagine) where t.ID_manoscritto = ?";

		Connection connessione = connessioneDB();
		PreparedStatement cmd = connessione.prepareStatement(query);
		cmd.setInt(1, ID_manoscritto);

		ResultSet risultato = cmd.executeQuery();

		while (risultato.next())
		{
			A_MANOSCRITTO_1_Pagina localPagina = new A_MANOSCRITTO_1_Pagina(risultato.getInt("ID_manoscritto"), risultato.getString("path_immagine"), risultato.getString("stato"), risultato.getInt("ID_immagine"), risultato.getString("testo"), risultato.getInt("ID_trascrizione"), risultato.getString("stato_trascrizione"));
			localList.add(localPagina);
		}

		risultato.close();
		cmd.close();

		return localList;
	}

	// Metodo per prendere dal Database tutti i manoscritti e restituisce una lista
	public static ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscritti() throws SQLException
	{
		ArrayList<A_MANOSCRITTO_0_Manoscritto> localList = new ArrayList<A_MANOSCRITTO_0_Manoscritto>();
		String query = "SELECT * FROM Manoscritto";

		Connection connessione = connessioneDB();
		Statement cmd = connessione.createStatement();
		ResultSet risultato = cmd.executeQuery(query);

		while (risultato.next())
		{
			A_MANOSCRITTO_0_Manoscritto localManoscritto = new A_MANOSCRITTO_0_Manoscritto(risultato.getInt("ID"), risultato.getString("nome_autore"), risultato.getString("titolo"), risultato.getString("anno_pubblicazione"), risultato.getString("genere"), risultato.getInt("numero_pagine"), risultato.getString("immagine_copertina"));
			localList.add(localManoscritto);
		}

		risultato.close();
		cmd.close();

		return localList;
	}

	// Metodo per prendere dal Database tutte le immagini del manoscritto con l'ID specificato e restituisce una lista
	public static ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagine(int ID) throws SQLException
	{
		ArrayList<A_MANOSCRITTO_1_Pagina> localList = new ArrayList<A_MANOSCRITTO_1_Pagina>();
		String query = "SELECT * FROM immagine WHERE ID_manoscritto = ?;";

		Connection connessione = connessioneDB();
		PreparedStatement cmd = connessione.prepareStatement(query);
		cmd.setInt(1, ID);

		ResultSet risultato = cmd.executeQuery();

		while (risultato.next())
		{
			// creazione in locale della pagina, specificando l'id dell'opera al quale è associata e il path ricavato dal risultato della query
			A_MANOSCRITTO_1_Pagina localPagina = new A_MANOSCRITTO_1_Pagina(ID, risultato.getString("path_immagine"), risultato.getString("stato"), risultato.getInt("ID"));

			localList.add(localPagina);
		}

		risultato.close();
		cmd.close();

		return localList;
	}

	// Metodo per prendere dal Database tutte le immagini ACCETTATE del manoscritto con il titolo specificato specificato e restituisce una lista
	public static ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineAccettate(String titoloManoscritto) throws SQLException
	{
		ArrayList<A_MANOSCRITTO_1_Pagina> localList = new ArrayList<A_MANOSCRITTO_1_Pagina>();
		String query = "select i.path_immagine, i.ID, i.stato from immagine i join manoscritto m on(i.ID_manoscritto=m.ID) where m.titolo=? and i.stato = 'accettata';";

		Connection connessione = connessioneDB();
		PreparedStatement cmd = connessione.prepareStatement(query);
		cmd.setString(1, titoloManoscritto);

		ResultSet risultato = cmd.executeQuery();

		while (risultato.next())
		{
			A_MANOSCRITTO_1_Pagina localPagina = new A_MANOSCRITTO_1_Pagina(getIdManoscritto(titoloManoscritto), risultato.getString("path_immagine"), risultato.getString("stato"), risultato.getInt("ID"));

			localList.add(localPagina);
		}

		risultato.close();
		cmd.close();

		return localList;
	}

	// Metodo per prendere dal Database tutte le immagini ACCETTATE del manoscritto con il titolo specificato specificato e restituisce una lista
	public static ArrayList<A_MANOSCRITTO_1_Pagina> getListaPagineDaVisualizzare(int ID_manoscritto) throws SQLException
	{
		ArrayList<A_MANOSCRITTO_1_Pagina> localList = new ArrayList<A_MANOSCRITTO_1_Pagina>();

		String query = "select i.path_immagine, i.ID as ID_immagine, i.ID_manoscritto, i.stato as stato_immagine,t.ID as ID_trascrizione, t.testo, t.stato as stato_trascrizione from immagine i join trascrizione t on (t.ID_immagine = i.ID) where i.stato = 'accettata' and t.stato = 'accettata' and t.ID_manoscritto=?";

		Connection connessione = connessioneDB();
		PreparedStatement cmd = connessione.prepareStatement(query);
		cmd.setInt(1, ID_manoscritto);

		ResultSet risultato = cmd.executeQuery();

		while (risultato.next())
		{
			A_MANOSCRITTO_1_Pagina localPagina = new A_MANOSCRITTO_1_Pagina(risultato.getInt("ID_manoscritto"), risultato.getString("path_immagine"), risultato.getString("stato_immagine"), risultato.getInt("ID_immagine"), risultato.getString("testo"), risultato.getInt("ID_trascrizione"), risultato.getString("stato_trascrizione"));
			localList.add(localPagina);
		}

		risultato.close();
		cmd.close();

		return localList;
	}

	// Metodo per prendere dal Database tutti gli utenti e restituisce una Lista
	public static LinkedList<Utente> getListaUtenti() throws SQLException
	{
		LinkedList<Utente> localList = new LinkedList<Utente>();
		String query = "SELECT * FROM utente_loggato";

		Connection connessione = connessioneDB();
		Statement cmd = connessione.createStatement();
		ResultSet risultato = cmd.executeQuery(query);

		while (risultato.next())
		{
			Utente localUtente = new Utente(risultato.getInt("ID"), risultato.getString("nome"), risultato.getString("cognome"), risultato.getString("data_nascita"), risultato.getString("nickname"), risultato.getString("email"), risultato.getString("pass_word"), risultato.getString("professione"), risultato.getString("data_nascita"), risultato.getString("titolo_studio"));
			localList.add(localUtente);
		}

		risultato.close();
		cmd.close();

		return localList;
	}

	// Metodo per prendere dal Database tutti gli utenti che sono trascrittori e restituisce una lista
	public static ArrayList<Utente> getListaTrascrittori() throws SQLException
	{
		ArrayList<Utente> localList = new ArrayList<Utente>();
		String query = "SELECT * FROM utente_loggato WHERE utente_transcriber = 1;";

		Connection connessione = connessioneDB();
		Statement cmd = connessione.createStatement();
		ResultSet risultato = cmd.executeQuery(query);

		while (risultato.next())
		{
			Utente localTranscriber = new Utente(risultato.getInt("ID"), risultato.getString("nome"), risultato.getString("cognome"), risultato.getString("data_nascita"), risultato.getString("nickname"), risultato.getString("email"), risultato.getString("pass_word"), risultato.getString("professione"), risultato.getString("data_nascita"), risultato.getString("titolo_studio"));
			localList.add(localTranscriber);
		}

		risultato.close();
		cmd.close();

		return localList;
	}

	// Metodo per prendere dal Database tutti gli utenti che sono trascrittori con esperienza e restituisce una lista
	public static ArrayList<Utente> getListaTrascrittoriExp() throws SQLException
	{
		ArrayList<Utente> localList = new ArrayList<Utente>();
		String query = "SELECT * FROM utente_loggato WHERE utente_transcriber = 1;";

		Connection connessione = connessioneDB();
		Statement cmd = connessione.createStatement();
		ResultSet risultato = cmd.executeQuery(query);

		while (risultato.next())
		{
			Utente localTranscriber = new Utente(risultato.getInt("ID"), risultato.getString("nome"), risultato.getString("cognome"), risultato.getString("data_nascita"), risultato.getString("nickname"), risultato.getString("email"), risultato.getString("pass_word"), risultato.getString("professione"), risultato.getString("data_nascita"), risultato.getString("titolo_studio"), true, risultato.getInt("lv_transcriber"));
			localList.add(localTranscriber);
		}

		risultato.close();
		cmd.close();

		return localList;
	}

	// metodo che restituisce una lista di tutti i trascrittori a cui è stata assegnata l'immagine di id che specifico
	public static ArrayList<Utente> getListaTrascrittoriImmagineAssegnata(int ID_immagine) throws SQLException
	{
		ArrayList<Utente> localList = new ArrayList<Utente>();
		String query = "select a.IDimmagine,u.ID,nome,cognome,data_nascita,nickname,email,pass_word,professione,titolo_studio FROM assegnazione a join utente_loggato u on (a.IDtrascrittore=u.ID) WHERE IDimmagine = ?";

		Connection connessione = connessioneDB();
		PreparedStatement cmd = connessione.prepareStatement(query);
		cmd.setInt(1, ID_immagine);

		ResultSet risultato = cmd.executeQuery();

		while (risultato.next())
		{
			Utente localTranscriber = new Utente(risultato.getInt("ID"), risultato.getString("nome"), risultato.getString("cognome"), risultato.getString("data_nascita"), risultato.getString("nickname"), risultato.getString("email"), risultato.getString("pass_word"), risultato.getString("professione"), risultato.getString("data_nascita"), risultato.getString("titolo_studio"));
			localList.add(localTranscriber);
		}

		risultato.close();
		cmd.close();

		return localList;
	}

	// metodo che mostra l'immagine del profilo dell'utente
	public String getImmagineProfilo(String nick)
	{
		try
		{
			String query = "SELECT immagine_profilo FROM utente_loggato WHERE nickname = ?";
			String path = null;

			Connection connessione = connessioneDB();
			PreparedStatement cmd = connessione.prepareStatement(query);
			cmd.setString(1, nick);

			ResultSet risultato = cmd.executeQuery();

			while (risultato.next())
			{
				path = risultato.getString("immagine_profilo");
			}

			return path;
		}
		catch (Exception e)
		{
			System.out.println("Errore nel ricavarsi l'immagine del profilo, errore nel database");
			System.out.println(e);
			return null;
		}
	}

	// metodo che rida l'id di un manoscritto dato il suo titolo
	public static int getIdManoscritto(String titolo)
	{
		try
		{
			String query = "SELECT ID FROM Manoscritto WHERE  titolo = ?";
			int id = 0;

			Connection connessione = connessioneDB();
			PreparedStatement cmd = connessione.prepareStatement(query);
			cmd.setString(1, titolo);

			ResultSet risultato = cmd.executeQuery();

			while (risultato.next())
			{
				id = risultato.getInt("ID");
			}

			return id;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel ricavarsi l'ID dato il titolo del Manoscritto");
			System.out.println(e);
			return 0;
		}
	}

	// metodo che ricava l'id di un utente dato il suo nickname
	public static int getIdUtente(String nick)
	{
		try
		{
			String query = "SELECT ID FROM utente_loggato WHERE nickname = ?";
			int id = 0;

			Connection connessione = connessioneDB();
			PreparedStatement cmd = connessione.prepareStatement(query);
			cmd.setString(1, nick);

			ResultSet risultato = cmd.executeQuery();

			while (risultato.next())
			{
				id = risultato.getInt("ID");
			}

			return id;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel ricavarsi l'ID per il nickname " + nick);
			System.out.println(e);
			return 0;
		}
	}

	// metodo che mi da lo stato della trascrizione che gli passo tramite id
	public static String getStatoTrascrizione(int ID_trascrizione) throws SQLException
	{
		String stato = null;
		String query = "select stato as stato_trascrizione from trascrizione where ID = ?";

		Connection connessione = connessioneDB();
		PreparedStatement cmd = connessione.prepareStatement(query);
		cmd.setInt(1, ID_trascrizione);

		ResultSet risultato = cmd.executeQuery();

		while (risultato.next())
		{
			stato = risultato.getString("stato_trascrizione");



			System.out.println(risultato.getString("stato_trascrizione"));




	
			System.out.println("stato trascrizione appena dopo aver fatto la queri e registrato il risultato " + risultato.getString("stato"));
		}

		risultato.close();
		cmd.close();

		return stato;
	}


	// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//


	// Metodo per l'update delle nuove modifica fatte nel profilo utente
	public static boolean confermaModifiche(String nickname, String nome, String cognome, String dataNascita, String professione, String titolo) throws SQLException
	{
		try
		{
			Connection conn = connessioneDB();

			String query = "UPDATE utente_loggato SET nome = ?, cognome = ?, data_nascita = ?, professione = ?, titolo_studio = ? WHERE nickname = ?";

			PreparedStatement interrogazione = conn.prepareStatement(query);
			interrogazione.setString(1, nome);
			interrogazione.setString(2, cognome);
			interrogazione.setString(3, dataNascita);
			interrogazione.setString(4, professione);
			interrogazione.setString(5, titolo);
			interrogazione.setString(6, nickname);

			interrogazione.executeUpdate();
			conn.close();

			return true;
		}
		catch (Exception e)
		{
			System.out.println("Errore nell'update delle modifiche utente, errore nel database");

			return false;
		}
	}

	// Metodo per modificare l'immagine del profilo dell'utente
	public static boolean modificaImmagineProfilo(String nickname, String path) throws SQLException
	{
		try
		{
			Connection conn = connessioneDB();

			String query = "UPDATE utente_loggato SET immagine_profilo = ? WHERE nickname = ?";

			PreparedStatement interrogazione = conn.prepareStatement(query);
			interrogazione.setString(1, path);
			interrogazione.setString(2, nickname);

			interrogazione.executeUpdate();
			conn.close();

			return true;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel settaggio dell'immagine del profilo, errore nel DB");
			System.out.println(e);

			return false;
		}
	}

	// Metodo per modificare lo stato di una pagina
	public static boolean modificaStatoImmagine(int idImmagine, int idManager, int scelta) throws SQLException
	{
		try
		{
			Connection conn = connessioneDB();
			String query = "UPDATE immagine SET stato = ? , ID_manager = ? WHERE ID = ?";

			PreparedStatement interrogazione = conn.prepareStatement(query);

			if (scelta == 1)
			{
				System.out.println("scelto di accettare");
				interrogazione.setString(1, "accettata");
			}
			else
			{
				System.out.println("scelto di declinare");
				interrogazione.setString(1, "declinata");
			}

			interrogazione.setInt(2, idManager);
			interrogazione.setInt(3, idImmagine);

			interrogazione.executeUpdate();
			conn.close();

			return true;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel settaggio del nuovo stato dell'immagine di ID: " + idImmagine + " errore nel DB");
			System.out.println(e);

			return false;
		}
	}


	// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//


	// Metodo per rimuovere un manoscritto dato il suo titolo
	public static boolean rimuoviManoscritto(String titolo) throws SQLException
	{
		try
		{
			Connection conn = connessioneDB();

			String query = "DELETE FROM Manoscritto WHERE titolo = ?";

			PreparedStatement interrogazione = conn.prepareStatement(query);
			interrogazione.setString(1, titolo);

			interrogazione.executeUpdate();
			conn.close();

			return true;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nell'eliminare il manoscritto, errore nel DB");
			System.out.println(e);

			return false;
		}
	}

	// metodo per eliminare un utente dato il suo nickname
	public static boolean rimuoviUtente(String nickname) throws SQLException
	{
		try
		{
			Connection conn = connessioneDB();

			String query = "DELETE FROM utente_loggato WHERE nickname = ?";

			PreparedStatement interrogazione = conn.prepareStatement(query);
			interrogazione.setString(1, nickname);

			interrogazione.executeUpdate();
			conn.close();

			return true;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nell'eliminare l'utente, errore nel DB");
			System.out.println(e);

			return false;
		}
	}


	// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//


	// metodo per promuovere l'utente a utente vip
	public static boolean promuoviUtenteInVip(String nickname) throws SQLException
	{
		try
		{
			Connection conn = connessioneDB();

			String query = "UPDATE utente_loggato SET utente_vip = true WHERE nickname = ?";

			PreparedStatement interrogazione = conn.prepareStatement(query);
			interrogazione.setString(1, nickname);

			interrogazione.executeUpdate();
			conn.close();

			return true;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel promuovere l'utente in utente VIP, errore nel DB");
			System.out.println(e);

			return false;
		}
	}

	// metodo per promuovere l'utente a utente trascrittore
	public static boolean promuoviUtenteInTrascrittore(String nickname) throws SQLException
	{
		try
		{
			Connection conn = connessioneDB();

			String query1 = "UPDATE utente_loggato SET utente_transcriber = true WHERE nickname = ?";
			PreparedStatement interrogazione = conn.prepareStatement(query1);
			interrogazione.setString(1, nickname);
			interrogazione.executeUpdate();

			String query2 = "UPDATE utente_loggato SET utente_manager = false WHERE nickname = ?";
			interrogazione = conn.prepareStatement(query2);
			interrogazione.setString(1, nickname);
			interrogazione.executeUpdate();

			String query3 = "UPDATE utente_loggato SET utente_uploader = false WHERE nickname = ?";
			interrogazione = conn.prepareStatement(query3);
			interrogazione.setString(1, nickname);
			interrogazione.executeUpdate();

			conn.close();

			return true;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel promuovere l'utente in utente TRASCRITTORE, errore nel DB");
			System.out.println(e);

			return false;
		}
	}

	// metodo per promuovere l'utente a utente uploader
	public static boolean promuoviUtenteInUploader(String nickname) throws SQLException
	{
		try
		{
			Connection conn = connessioneDB();

			String query1 = "UPDATE utente_loggato SET utente_uploader = true WHERE nickname = ?";
			PreparedStatement interrogazione = conn.prepareStatement(query1);
			interrogazione.setString(1, nickname);
			interrogazione.executeUpdate();

			String query2 = "UPDATE utente_loggato SET utente_transcriber = false WHERE nickname = ?";
			interrogazione = conn.prepareStatement(query2);
			interrogazione.setString(1, nickname);
			interrogazione.executeUpdate();

			String query3 = "UPDATE utente_loggato SET utente_manager = false WHERE nickname = ?";
			interrogazione = conn.prepareStatement(query3);
			interrogazione.setString(1, nickname);
			interrogazione.executeUpdate();

			conn.close();

			return true;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel promuovere l'utente in utente UPLOADER, errore nel DB");
			System.out.println(e);

			return false;
		}
	}

	// metodo per promuovere l'utente a utente manager
	public static boolean promuoviUtenteInManager(String nickname) throws SQLException
	{
		try
		{
			Connection conn = connessioneDB();

			String query1 = "UPDATE utente_loggato SET utente_manager = true WHERE nickname = ?";
			PreparedStatement interrogazione = conn.prepareStatement(query1);
			interrogazione.setString(1, nickname);
			interrogazione.executeUpdate();

			String query2 = "UPDATE utente_loggato SET utente_uploader = false WHERE nickname = ?";
			interrogazione = conn.prepareStatement(query2);
			interrogazione.setString(1, nickname);
			interrogazione.executeUpdate();

			String query3 = "UPDATE utente_loggato SET utente_transcriber = false WHERE nickname = ?";
			interrogazione = conn.prepareStatement(query3);
			interrogazione.setString(1, nickname);
			interrogazione.executeUpdate();

			conn.close();

			return true;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel promuovere l'utente in utente MANAGER, errore nel DB");
			System.out.println(e);

			return false;
		}
	}


	// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//


	// metodo per assegnare ad un trascrittore una immagine
	public static boolean assegnaImmagine(int ID_immagine, int ID_trascrittore) throws SQLException
	{
		try
		{
			Connection conn = connessioneDB();
			String query = "INSERT INTO assegnazione (IDimmagine,IDtrascrittore) values (?,?);";

			PreparedStatement interrogazione = conn.prepareStatement(query);
			interrogazione.setInt(1, ID_immagine);
			interrogazione.setInt(2, ID_trascrittore);
			interrogazione.executeUpdate();

			conn.close();

			return true;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nell'assegnare un immagine ad un trascrittore, errore nel DB");
			System.out.println(e);

			return false;
		}
	}

	// metodo per assegnare esperienza ad un trascrittore dal pannello workbench manager
	public static boolean assegnaEsperienza(int ID_trascrittore, int lvl) throws SQLException
	{
		String query = "update utente_loggato set lv_transcriber = ? where id = ?";

		Connection conn = connessioneDB();

		PreparedStatement interrogazione = conn.prepareStatement(query);
		interrogazione.setInt(1, lvl);
		interrogazione.setInt(2, ID_trascrittore);
		interrogazione.executeUpdate();

		conn.close();

		return true;
	}

	// Metodo per modificare lo stato di una trascrizione in accettata
	public static boolean accettaTrascrizione(int idTrascrizione, int idManager, int scelta) throws SQLException
	{
		try
		{
			// -----------------PRIMA QUERY--------------------------------------------------------------//
			// query per cambiare lo stato della trascrizione

			Connection conn = connessioneDB();
			String query = "UPDATE trascrizione t SET t.stato = ? , t.ID_manager = ? WHERE t.ID = ?";
			PreparedStatement interrogazione = conn.prepareStatement(query);

			interrogazione.setString(1, "accettata");
			interrogazione.setInt(2, idManager);
			interrogazione.setInt(3, idTrascrizione);

			interrogazione.executeUpdate();

			System.out.println("effettuato cambio stato della trascrizione, prima query andata a buon fine");

			// -----------------SECONDA QUERY--------------------------------------------------------------//
			// query per prendere l'id del trascrittore

			String query2 = "select distinct IDtrascrittore from assegnazione a join trascrizione t on (t.ID_immagine = a.IDimmagine) join utente_loggato u on (a.IDtrascrittore = u.ID) where u.utente_transcriber = 1 and t.ID = ?";
			interrogazione = conn.prepareStatement(query2);
			interrogazione.setInt(1, idTrascrizione);

			ResultSet risultato = interrogazione.executeQuery();

			System.out.println("acquisito id del trascrittore,seconda query andata a buon fine");

			while (risultato.next())
			{
				// ---------------TERZA QUERY----------------------//
				// con il risultato della seconda query, facciamo un insert nella tabella scrittura

				String query3 = "insert into scrittura (ID_trascrittore,ID_trascrizione) values (?,?)";
				interrogazione = conn.prepareStatement(query3);


				interrogazione.setInt(1, risultato.getInt("IDtrascrittore"));

				interrogazione.setInt(2, idTrascrizione);

				interrogazione.executeUpdate();

			}

			System.out.println("completate tutte le inserzioni della terza query");

			risultato.close();
			conn.close();

			return true;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel settaggio del nuovo stato della trascrizione di ID: " + idTrascrizione + " errore nel DB");
			System.out.println(e);

			return false;
		}
	}

	// Metodo per modificare lo stato di una trascrizione in rifiutata
	public static boolean rifiutaTrascrizione(int idTrascrizione, int idManager) throws SQLException
	{
		try
		{
			String query = "UPDATE trascrizione t SET t.stato = ? , t.ID_manager = ? WHERE t.ID = ?";
			Connection conn = connessioneDB();
			PreparedStatement interrogazione = conn.prepareStatement(query);

			interrogazione.setString(1, "declinata");
			interrogazione.setInt(2, idManager);
			interrogazione.setInt(3, idTrascrizione);
			interrogazione.executeUpdate();

			return true;
		}
		catch (Exception e)
		{
			System.out.println("ERRORE nel cambiare stato della trascrizione di ID: " + idTrascrizione);
			e.printStackTrace();

			return false;
		}
	}

	// metodo che va a modificare una trascrizione già esistente
	public static boolean modificaTrascrizioneEsistente(int idTrascrizione, String testo) throws SQLException
	{
		String query = "update trascrizione set testo = ? where ID = ?";

		Connection conn = connessioneDB();
		PreparedStatement interrogazione = conn.prepareStatement(query);
		interrogazione.setString(1, testo);
		interrogazione.setInt(2, idTrascrizione);
		interrogazione.executeUpdate();

		return true;
	}

	// metodo che va a riassegnare una trascrizione ad un altro trascrittore
	public static boolean riassegnaTrascrizione(int ID_immagine, int ID_trascrittore) throws SQLException
	{
		String query = "insert into assegnazione (IDimmagine, IDtrascrittore) values (?,?)";

		Connection conn = connessioneDB();
		PreparedStatement interrogazione = conn.prepareStatement(query);
		interrogazione.setInt(1, ID_immagine);
		interrogazione.setInt(2, ID_trascrittore);
		interrogazione.executeUpdate();

		return true;
	}

	// metodo che restituisce un entita manoscritto quando dal pannello Ricerca Opera, per poterlo poi visualizzarlo
	public static ArrayList<A_MANOSCRITTO_0_Manoscritto> ricercaOperaManoscritto(String titolo, String genere, String autore) throws SQLException
	{
		ArrayList<A_MANOSCRITTO_0_Manoscritto> localList = new ArrayList<A_MANOSCRITTO_0_Manoscritto>();
		A_MANOSCRITTO_0_Manoscritto manoscritto;

		Connection connessione = connessioneDB();

		String titoloL = titolo;
		String genereL = genere;
		String autoreL = autore;





		System.out.println("//------------------------------------------------//");
		System.out.println("titolo: " + titolo);
		System.out.println("genere: " + genere);
		System.out.println("autore: " + autore);
		System.out.println("//------------------------------------------------//");



		if (titoloL.equals("") && genereL.equals("ALTRO") && autoreL.equals(""))
		{
			System.out.println("tutto null");

			// errore tutti i campi sono vuoti

			return localList;
		}
		else if (!titoloL.equals("") && genereL.equals("ALTRO") && autoreL.equals(""))
		{
			System.out.println("1");

			String query = "select * from manoscritto where titolo = ?";

			PreparedStatement cmd = connessione.prepareStatement(query);
			cmd.setString(1, titolo);
			ResultSet risultato = cmd.executeQuery();

			while (risultato.next())
			{
				manoscritto = new A_MANOSCRITTO_0_Manoscritto(risultato.getInt("ID"), risultato.getString("nome_autore"), risultato.getString("titolo"), risultato.getString("anno_pubblicazione"), risultato.getString("genere"), risultato.getInt("numero_pagine"), risultato.getString("immagine_copertina"));
				localList.add(manoscritto);
			}

			risultato.close();
			cmd.close();

			return localList;
		}
		else if (titoloL.equals("") && !genereL.equals("ALTRO") && autoreL.equals(""))
		{
			System.out.println("2");
			// returna una lista

			String query = "select * from manoscritto where genere = ?";

			PreparedStatement cmd = connessione.prepareStatement(query);
			cmd.setString(1, genere);
			ResultSet risultato = cmd.executeQuery();


			while (risultato.next())
			{
				manoscritto = new A_MANOSCRITTO_0_Manoscritto(risultato.getInt("ID"), risultato.getString("nome_autore"), risultato.getString("titolo"), risultato.getString("anno_pubblicazione"), risultato.getString("genere"), risultato.getInt("numero_pagine"), risultato.getString("immagine_copertina"));
				localList.add(manoscritto);
			}

			risultato.close();
			cmd.close();

			return localList;
		}
		else if (titoloL.equals("") && genereL.equals("ALTRO") && !autoreL.equals(""))
		{
			System.out.println("3");

			String query = "select * from manoscritto where nome_autore = ?";

			PreparedStatement cmd = connessione.prepareStatement(query);
			cmd.setString(1, autore);
			ResultSet risultato = cmd.executeQuery();

			while (risultato.next())
			{
				manoscritto = new A_MANOSCRITTO_0_Manoscritto(risultato.getInt("ID"), risultato.getString("nome_autore"), risultato.getString("titolo"), risultato.getString("anno_pubblicazione"), risultato.getString("genere"), risultato.getInt("numero_pagine"), risultato.getString("immagine_copertina"));
				localList.add(manoscritto);
			}

			risultato.close();
			cmd.close();

			return localList;
		}
		else if (!titoloL.equals("") && !genereL.equals("ALTRO") && !autoreL.equals(""))
		{
			System.out.println("4");
			// return una lista

			String query = "select * from manoscritto where genere=? and nome_autore=?";

			PreparedStatement cmd = connessione.prepareStatement(query);
			cmd.setString(1, genere);
			cmd.setString(2, autore);
			ResultSet risultato = cmd.executeQuery();

			while (risultato.next())
			{
				manoscritto = new A_MANOSCRITTO_0_Manoscritto(risultato.getInt("ID"), risultato.getString("nome_autore"), risultato.getString("titolo"), risultato.getString("anno_pubblicazione"), risultato.getString("genere"), risultato.getInt("numero_pagine"), risultato.getString("immagine_copertina"));
				localList.add(manoscritto);
			}

			risultato.close();
			cmd.close();

			return localList;
		}
		else if (!titoloL.equals("") && !genereL.equals("ALTRO") && autoreL.equals(""))
		{
			System.out.println("5");
			// return una lista

			String query = "select * from manoscritto where titolo=? and genere=?";

			PreparedStatement cmd = connessione.prepareStatement(query);
			cmd.setString(1, titolo);
			cmd.setString(2, genere);
			ResultSet risultato = cmd.executeQuery();

			while (risultato.next())
			{
				manoscritto = new A_MANOSCRITTO_0_Manoscritto(risultato.getInt("ID"), risultato.getString("nome_autore"), risultato.getString("titolo"), risultato.getString("anno_pubblicazione"), risultato.getString("genere"), risultato.getInt("numero_pagine"), risultato.getString("immagine_copertina"));
				localList.add(manoscritto);
			}

			risultato.close();
			cmd.close();

			return localList;
		}
		else if (!titoloL.equals("") && genereL.equals("ALTRO") && !autoreL.equals(""))
		{
			System.out.println("6");

			String query = "select * from manoscritto where titolo=? and nome_autore=?";

			PreparedStatement cmd = connessione.prepareStatement(query);
			cmd.setString(1, titolo);
			cmd.setString(2, autore);
			ResultSet risultato = cmd.executeQuery();

			while (risultato.next())
			{
				manoscritto = new A_MANOSCRITTO_0_Manoscritto(risultato.getInt("ID"), risultato.getString("nome_autore"), risultato.getString("titolo"), risultato.getString("anno_pubblicazione"), risultato.getString("genere"), risultato.getInt("numero_pagine"), risultato.getString("immagine_copertina"));
				localList.add(manoscritto);
			}

			risultato.close();
			cmd.close();

			return localList;
		}
		else
		{
			return localList;
		}
	}


}
