import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class KonkurrenceMedlem extends Medlem {

    private String træner;
    private String[] discipliner;
    private ArrayList<Svømmedisciplin> rekorder = new ArrayList<>();

    KonkurrenceMedlem(String træner, String[] discipliner, ArrayList<Svømmedisciplin> newList, String navn, int aar, int maaned, int dag, String k, String adresse, String email, String medlemstype, String aktivitetstype, ArrayList<LocalDate> betalingsHistorik) {
        super(navn, aar, maaned, dag, k, adresse, email, medlemstype, aktivitetstype, betalingsHistorik);
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
