import java.time.LocalDate;
import java.util.ArrayList;

public interface ListeInterface{
	public ArrayList<Medlem> filtrerListe(String filter);
	public Medlem hentMedlem(int medlemsIndex);
	public void redigerMedlem(int medlemsIndex, String navn, LocalDate dato, String gender, String adresse, String husNr, String postNr, String email, String medlemstype,
							  String aktivitetstype, ArrayList<Betaling> betalingsHistorik);
	public void sletMedlem(int medlemsIndex);
	public void opretMedlem(String navn, LocalDate dato, String koen, String adresse, String husNr, String postNr, String email, String medlemstype,
                            String aktivitetstype);
	public void opdaterListe();
}