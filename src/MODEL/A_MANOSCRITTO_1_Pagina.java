package MODEL;

public class A_MANOSCRITTO_1_Pagina
{

	// ------------------------------ATTRIBUTI----------------------------------------------//

	private int idManoscritto;

	private int		idImmagine;
	private int		numeroPagina;
	private String	statoImmagine;
	private String	pathImmagine;

	private String	trascrizione;
	private int		idTrascrizione;
	private String	statoTrascrizione;

	// -----------------------------COSTRUTTORE------------------------------------------------//

	public A_MANOSCRITTO_1_Pagina(int idManoscritto, String pathImmagine, String statoImmagine, int idImmagine)
	{
		this.idManoscritto = idManoscritto;
		this.pathImmagine = pathImmagine;
		this.statoImmagine = statoImmagine;
		this.idImmagine = idImmagine;

	}

	public A_MANOSCRITTO_1_Pagina(int idManoscritto, String pathImmagine, String statoImmagine, int idImmagine,
			String trascrizione, int idTrascrizione, String stato_trascrizione)
	{
		this.idManoscritto = idManoscritto;
		this.pathImmagine = pathImmagine;
		this.statoImmagine = statoImmagine;
		this.idImmagine = idImmagine;
		this.trascrizione = trascrizione;
		this.idTrascrizione = idTrascrizione;
		this.statoTrascrizione = stato_trascrizione;
	}

	// ------------------------------GETTERS-----------------------------------------------------//

	public int getIdManoscritto()
	{
		return idManoscritto;
	}

	public int numeroPagina()
	{
		return numeroPagina;
	}

	public String getPathImmagine()
	{
		return pathImmagine;
	}

	public String getStatoImmagine()
	{
		return statoImmagine;
	}

	public int getIdImmagine()
	{
		return idImmagine;
	}

	public String getTrascrizione()
	{
		return this.trascrizione;
	}

	public int getIDTrascrizione()
	{
		return this.idTrascrizione;
	}

	public String getStatoTrascrizione()
	{
		return this.statoTrascrizione;
	}

	// -------------------------------SETTERS----------------------------------------------------//

	public void setIdManoscritto(int idManoscritto)
	{
		this.idManoscritto = idManoscritto;
	}

	public void numeroPagina(int numero)
	{
		this.numeroPagina = numero;
	}

	public void setPathImmagine(String path)
	{

	}

	public void setIdImmagine(int idImmagine)
	{
		this.idImmagine = idImmagine;
	}

	public void setTrascrizione(String trascrizione)
	{
		this.trascrizione = trascrizione;
	}

	public void setIDTrascrizione(int IDtrascrizione)
	{
		this.idTrascrizione = IDtrascrizione;
	}

}
