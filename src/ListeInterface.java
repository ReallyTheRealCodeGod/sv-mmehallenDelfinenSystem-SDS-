import java.time.LocalDate;
import java.util.ArrayList;

public interface ListeInterface{
	public ArrayList<Medlem> getListe();
	public ArrayList<Medlem> filtrerListe();
	public Medlem getMedlem(int medlemsIndex);
	public void editMedlem(int medlemsIndex, String navn, LocalDate dato, String koen, String adresse, String email, String medlemstype,
						   String aktivitetstype);
	public void deleteMedlem(int medlemsIndex);
	public void addMedlem(String navn, LocalDate dato, String koen, String adresse, String email, String medlemstype,
           String aktivitetstype);

	private void updateList() {

	}
}