package VIEW;

import java.util.ArrayList;

import CONTROLLER.funzionalitaVarieCONTROLLER;
import GUI._FINITO_funzione_loginGUI;
import GUI._FINITO_funzione_registrazioneGUI;
import MODEL.A_MANOSCRITTO_0_Manoscritto;
import MODEL.A_MANOSCRITTO_1_Pagina;

public class HomePageView
{
	private static A_MANOSCRITTO_0_Manoscritto			manoscrittoInVisualizzazione;
	private static ArrayList<A_MANOSCRITTO_1_Pagina>	pagineDaVisualizzareOperaInVisualizzazione;

	// metodo che rida la lista di tutti i manoscritti
	public ArrayList<A_MANOSCRITTO_0_Manoscritto> getListaManoscritti()
	{
		ArrayList<A_MANOSCRITTO_0_Manoscritto> risultato = new funzionalitaVarieCONTROLLER().getListaManoscritti();

		return risultato;
	}

	// metodo che si attiva quando si preme login
	public void premutoLogin()
	{
		new _FINITO_funzione_loginGUI();
	}

	// metodo che si attiva quando si preme registrati
	public void premutoRegistrati()
	{
		new _FINITO_funzione_registrazioneGUI();
	}

}
