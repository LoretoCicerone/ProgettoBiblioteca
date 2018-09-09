package MODEL;

public class Utente
{
	// ------------------------------ATTRIBUTI----------------------------------------------//

	private int		ID;
	private String	nickname;
	private String	nome;
	private String	cognome;
	private int		eta;
	private String	data_nascita;
	private String	email;
	private String	password;
	private String	professione;
	private String	titoloStudio;
	private String	immagine_profilo;

	private boolean	utente_vip;
	private boolean	utente_trascrittore;
	private boolean	utente_manager;
	private boolean	utente_uploader;

	private int esperienza;

	// -----------------------------COSTRUTTORE------------------------------------------------//

	public Utente(int ID, String nome, String cognome, int eta, String nickname, String email, String password,
			String professione, String data_nascita, String titoloStudio)
	{
		setID(ID);
		setNome(nome);
		setCognome(cognome);
		setEta(eta);
		setNickname(nickname);
		setEmail(email);
		setPassword(password);
		setProfessione(professione);
		setData_nascita(data_nascita);
		setTitolostudio(titoloStudio);

	}

	public Utente(int ID, String nome, String cognome, int eta, String nickname, String email, String password,
			String professione, String data_nascita, String titoloStudio, boolean trascrittore, int exp)
	{
		setID(ID);
		setNome(nome);
		setCognome(cognome);
		setEta(eta);
		setNickname(nickname);
		setEmail(email);
		setPassword(password);
		setProfessione(professione);
		setData_nascita(data_nascita);
		setTitolostudio(titoloStudio);
		setUtente_trascrittore(true);
		setEsperienza(exp);
	}

	public Utente()
	{

	}

	// ------------------------------GETTERS-----------------------------------------------------//

	public String getNickname()
	{
		return nickname;
	}

	public int getID()
	{
		return ID;
	}

	public String getNome()
	{
		return nome;
	}

	public String getCognome()
	{
		return cognome;
	}

	public int getEta()
	{
		return eta;
	}

	public String getEmail()
	{
		return email;
	}

	public String getPassword()
	{
		return password;
	}

	public String getProfessione()
	{
		return professione;
	}

	public String getData_nascita()
	{
		return data_nascita;
	}

	public String getTitolostudio()
	{
		return titoloStudio;
	}

	public String getImmagineProfilo()
	{
		return immagine_profilo;
	}

	public int getExp()
	{
		return this.esperienza;
	}

	// -------------------------------SETTERS----------------------------------------------------//

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public void setID(int iD)
	{
		ID = iD;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public void setCognome(String cognome)
	{
		this.cognome = cognome;
	}

	public void setEta(int età)
	{
		this.eta = età;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setProfessione(String professione)
	{
		this.professione = professione;
	}

	public void setData_nascita(String data_nascita)
	{
		this.data_nascita = data_nascita;
	}

	public void setTitolostudio(String titolostudio)
	{
		this.titoloStudio = titolostudio;
	}

	public void setUtente_vip(boolean utente_vip)
	{
		this.utente_vip = utente_vip;
	}

	public void setUtente_manager(boolean utente_manager)
	{
		this.utente_manager = utente_manager;
	}

	public void setUtente_uploader(boolean utente_uploader)
	{
		this.utente_uploader = utente_uploader;
	}

	public void setUtente_trascrittore(boolean utente_trascrittore)
	{
		this.utente_trascrittore = utente_trascrittore;
	}

	public void setImmagineProfilo(String pathImmagine)
	{
		this.immagine_profilo = pathImmagine;
	}

	public void setEsperienza(int exp)
	{
		this.esperienza = this.esperienza + exp;
	}

	// --------------------------------BOOLEAN-----------------------------------------------------//

	public boolean isUtente_vip()
	{
		return utente_vip;
	}

	public boolean isUtente_trascrittore()
	{
		return utente_trascrittore;
	}

	public boolean isUtente_manager()
	{
		return utente_manager;
	}

	public boolean isUtente_uploader()
	{
		return utente_uploader;
	}

	// -----------------------------------------------------------------------------------------------//
}
