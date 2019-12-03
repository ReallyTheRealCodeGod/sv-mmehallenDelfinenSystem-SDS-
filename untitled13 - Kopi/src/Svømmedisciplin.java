import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Svømmedisciplin implements Comparable{



    private LocalDate dato;
    private MinuteTimer time;
    private String disciplinNavn;

    public Svømmedisciplin(LocalDate date, MinuteTimer time, String navn) {

        this.dato = date;
        this.time = time;
        this.disciplinNavn = navn;

    }



    public LocalDate getDato() {
        return dato;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public MinuteTimer getTime() {
        return time;
    }

    public void setTime(MinuteTimer time) {
        this.time = time;
    }

    public String getDisciplinNavn() {
        return disciplinNavn;
    }

    public void setDisciplinNavn(String disciplinNavn) {
        this.disciplinNavn = disciplinNavn;
    }

    @Override
    public int compareTo(Object o){
        return getTime().compareTo(o);
    }




}
