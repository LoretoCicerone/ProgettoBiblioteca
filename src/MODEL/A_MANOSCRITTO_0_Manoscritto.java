package MODEL;

public class A_MANOSCRITTO_0_Manoscritto
{
	// ------------------------------ATTRIBUTI----------------------------------------------//

	private int		ID;
	private int		numero_pagine;
	private String	autore;
	private String	titolo;
	private String	anno_pubblicazione;
	private String	genere;
	private String	immagine_copertina;

	// -----------------------------COSTRUTTORE------------------------------------------------//

	public A_MANOSCRITTO_0_Manoscritto(int ID, String autore, String titolo, String anno_pubblicazione, String genere, int numero_pagine, String pathImmagine)
	{
		this.ID = ID;
		this.autore = autore;
		this.titolo = titolo;
		this.anno_pubblicazione = anno_pubblicazione;
		this.genere = genere;
		this.numero_pagine = numero_pagine;
		this.immagine_copertina = pathImmagine;
	}

	// ------------------------------GETTERS-----------------------------------------------------//

	public int getID()
	{
		return ID;
	}

	public String getAutore()
	{
		return autore;
	}

	public String getTitolo()
	{
		return titolo;
	}

	public String getCategoria()
	{
		return genere;
	}

	public int getNumeroPagine()
	{
		return numero_pagine;
	}

	public String getAnno_pubblicazione()
	{
		return anno_pubblicazione;
	}

	public String getPathImmagineCopertina()
	{
		return immagine_copertina;
	}

	// -------------------------------SETTERS----------------------------------------------------//

	public void setID(int iD)
	{
		ID = iD;
	}

	public void setAutore(String autore)
	{
		this.autore = autore;
	}

	public void setTitolo(String titolo)
	{
		this.titolo = titolo;
	}

	public void setAnno_pubblicazione(String anno_pubblicazione)
	{
		this.anno_pubblicazione = anno_pubblicazione;
	}

	public void setCategoria(String categoria)
	{
		this.genere = categoria;
	}

	public void setNumeroPagine(int pagine)
	{
		this.numero_pagine = pagine;
	}

	public void setImmagineCopertina(String path)
	{
		this.immagine_copertina = path;
	}

	// -------------------------------------------------------------------------------------------------//

}
