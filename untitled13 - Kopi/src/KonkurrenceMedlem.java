import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class KonkurrenceMedlem extends Medlem {

    private String træner;
    private String[] discipliner;
    private ArrayList<Svømmedisciplin> rekorder = new ArrayList<>();

    KonkurrenceMedlem(String træner, String[] discipliner, ArrayList<Svømmedisciplin> newList, String navn, LocalDate dato, String koen, String adresse, String email, String medlemstype, String aktivitetstype, ArrayList<LocalDate> betalingsHistorik) {
        super(navn, dato, koen, adresse, email, medlemstype, aktivitetstype, betalingsHistorik);
    }


    public Svømmedisciplin getTopResultat(String disciplinNavn) {
        ArrayList<Svømmedisciplin> listClone = new ArrayList<>();

        for (Svømmedisciplin svøm : rekorder) {
            if(rekorder.contains(disciplinNavn)){

                    listClone.add(svøm);
            }
        }
        Collections.sort(listClone);
        System.out.println(listClone);

    }


}
