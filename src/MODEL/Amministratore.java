package MODEL;

public class Amministratore extends Utente
{
	private int		ID;
	private String	nome;
	private String	cognome;
	private int		età;
	private String	nickname;
	private String	email;
	private String	password;
	private String	professione;
	private String	data_nascita;
	private String	titolostudio;

	public Amministratore(int ID, String nome, String cognome, int età, String nickname, String email, String password,
			String professione, String data_nascita, String titolostudio)
	{
		setID(ID);
		setNome(nome);
		setCognome(cognome);
		setEta(età);
		setNickname(nickname);
		setEmail(email);
		setPassword(password);
		setProfessione(professione);
		setData_nascita(data_nascita);
		setTitolostudio(titolostudio);
	}

	public Amministratore()
	{

	}

	public int getID()
	{
		return ID;
	}

	public void setID(int iD)
	{
		ID = iD;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getCognome()
	{
		return cognome;
	}

	public void setCognome(String cognome)
	{
		this.cognome = cognome;
	}

	public int getEtà()
	{
		return età;
	}

	public void setEtà(int età)
	{
		this.età = età;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getProfessione()
	{
		return professione;
	}

	public void setProfessione(String professione)
	{
		this.professione = professione;
	}

	public String getData_nascita()
	{
		return data_nascita;
	}

	public void setData_nascita(String data_nascita)
	{
		this.data_nascita = data_nascita;
	}

	public String getTitolostudio()
	{
		return titolostudio;
	}

	public void setTitolostudio(String titolostudio)
	{
		this.titolostudio = titolostudio;
	}

}
