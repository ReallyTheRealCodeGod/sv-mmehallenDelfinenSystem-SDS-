import java.time.LocalDate;
import java.util.ArrayList;

public class Medlem {

	private String navn;
	    private LocalDate alder;
	    private String gender;
	    private String adresse;
	    private String email;
	    private String medlemstype;
	    private String aktivitetstype;
	    private ArrayList<LocalDate> betalingsHistorik;
	    private LocalDate betalingsdato;
	    private double pris;
	    
	    
	   

	    Medlem(String navn, int aar, int maaned, int dag, String gender, String adresse, String email, String medlemstype,
	           String aktivitetstype, ArrayList<LocalDate> betalingsHistorik) {

	       this(navn, LocalDate.of(aar, maaned, dag), gender, adresse, email, medlemstype,
                   aktivitetstype, betalingsHistorik);

	    }
	    
	    Medlem(String navn, LocalDate foedelsdato, String gender, String adresse, String email, String medlemstype,
		           String aktivitetstype, ArrayList<LocalDate> betalingsHistorik) {
                this.navn = navn;
		        this.alder = foedelsdato;
		        this.gender = gender;
		        this.adresse = adresse;
		        this.email = email;
		        this.medlemstype = medlemstype;
		        this.aktivitetstype = aktivitetstype;
		        this.pris = Kontingent.getPris(this.alder, medlemstype);

                //initiasere betalingshistorikken
                //kopiere listen fra variablen til attributen
                this.betalingsHistorik = new ArrayList<LocalDate>();
                for(int i = 0; i < betalingsHistorik.size(); i++){
                    this.betalingsHistorik.add(betalingsHistorik.get(i));
                 }
		    }
	    
	  public LocalDate addUdloebsDato(){
            LocalDate nuvaerende;
        if(!betalingsHistorik.isEmpty()){
            nuvaerende = betalingsHistorik.get(betalingsHistorik.size()-1);
         }else{
            nuvaerende = LocalDate.now();
            }

	    	if(nuvaerende.isEqual(LocalDate.now())){
                betalingsdato = LocalDate.now().plusYears(1);
            }else if(nuvaerende.isAfter(LocalDate.now())){
                betalingsdato = nuvaerende.plusYears(1);
            }else if(nuvaerende.isBefore(LocalDate.now())){
                betalingsdato = LocalDate.now().plusYears(1);
            }

            return betalingsdato;
	    }

	public String getNavn() {
		return navn;
	}

	public LocalDate getalder() {
		return alder;
	}

	public String getgender() {
		return gender;
	}

	public String getAdresse() {
		return adresse;
	}

	public String getEmail() {
		return email;
	}

	public String getMedlemstype() {
		return medlemstype;
	}

	public String getAktivitetstype() {
		return aktivitetstype;
	}

	public ArrayList<LocalDate> getBetalingsHistorik() {
		return betalingsHistorik;
	}

	public LocalDate getbetalingsdato() {
		return betalingsdato;
	}

	public double getPris() {
		return pris;
	}


	// Override af toString. Bare lavet noget som et eksempel p� hvordan vi kunne g�re det.
	    @Override
	    public String toString() {
	        
	        return navn + ";" + alder +";"+ adresse +";"+email+";"+gender+";"+medlemstype+";"+aktivitetstype+";";
	               
	    }
	}