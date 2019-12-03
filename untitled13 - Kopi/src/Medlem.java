import java.time.LocalDate;

public class Medlem {

	private String navn;
	    private LocalDate alder;
	    private String gender;
	    private String adresse;
	    private String email;
	    private String medlemstype;
	    private String aktivitetstype;
	    private double pris;

	    Medlem(String navn, int aar, int maaned, int dag, String gender, String adresse, String email, String medlemstype,
	           String aktivitetstype) {

	       this(navn, LocalDate.of(aar, maaned, dag), gender, adresse, email, medlemstype,
                   aktivitetstype);

	    }
	    
	    Medlem(String navn, LocalDate foedelsdato, String gender, String adresse, String email, String medlemstype,
		           String aktivitetstype) {
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

		    }
	    
	  /*public LocalDate addUdloebsDato(){
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
	    }*/

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


	public double getPris() {
		return pris;
	}


	// Override af toString. Bare lavet noget som et eksempel p� hvordan vi kunne g�re det.
	    @Override
	    public String toString() {
	        
	        return navn + ";" + alder +";"+ adresse +";"+email+";"+gender+";"+medlemstype+";"+aktivitetstype+";";
	               
	    }
	}
