import java.time.LocalDate;
import java.util.ArrayList;

public interface ListeInterface{
	public ArrayList<Medlem> getListe();
	public ArrayList<Medlem> filtrerListe();
	public Medlem getMedlem(int medlemsIndex);
	public void editMedlem(int medlemsIndex, String navn,  int aar, int maaned, int dag, String koen, String adresse, String email, String medlemstype,
						   String aktivitetstype, ArrayList<LocalDate> betalingsHistorik);
	public void deleteMedlem(int medlemsIndex);
	public void addMedlem(String navn,  int aar, int maaned, int dag, String koen, String adresse, String email, String medlemstype,
           String aktivitetstype, ArrayList<LocalDate> betalingsHistorik);

	private void updateList() {

	}
}