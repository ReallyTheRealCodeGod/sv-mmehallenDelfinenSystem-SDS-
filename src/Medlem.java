import java.time.LocalDate;
import java.util.ArrayList;

public class Medlem {

	private String navn;
	    private LocalDate fodselsdato;
	    private String gender;
	    private String adresse;
		private String husNr;
	    private String postNr;
	    private String email;
	    private String medlemstype;
	    private String aktivitetstype;
	    private String udloebsdato;
	    private boolean restanceMedlem;
	    private ArrayList<Betaling> betalinger;
	    private double pris;

		Medlem(String navn, LocalDate foedelsdato, String gender, String adresse, String husNr, String postNr, String email, String medlemstype,
		   String aktivitetstype) {
			this(navn, foedelsdato, gender, adresse, husNr, postNr, email, medlemstype, aktivitetstype, new ArrayList<>());
	}

	    Medlem(String navn, LocalDate fodselsdato, String gender, String adresse, String husNr, String postNr, String email, String medlemstype,
		           String aktivitetstype, ArrayList<Betaling> betalinger) {
                this.navn = navn;
		        this.fodselsdato = fodselsdato;
		        this.gender = gender;
		        this.adresse = adresse;
		        this.husNr = husNr;
		        this.postNr = postNr;
		        this.email = email;
		        this.medlemstype = medlemstype;
		        this.aktivitetstype = aktivitetstype;
		        this.pris = Kontingent.udregnPris(this.fodselsdato, medlemstype);
                //initiasere betalingshistorikken
				if(betalinger.isEmpty()){
					restanceMedlem = true;
				}
                this.betalinger = new ArrayList<>();
                //kopiere listen fra variablen til attributen
                for(Betaling b: betalinger){
                    opretBetaling(b.getBeloeb(), b.getBetalingsDato(), b.getBankNummer());
              }
		    }

		public void opretBetaling(double pris, LocalDate dato, String bankNr){
			betalinger.add(new Betaling(pris, dato, bankNr));
			lavUdloebsdato();
        }

	  private void lavUdloebsdato() {
			LocalDate udloebsdato;
		  if (betalinger.isEmpty()) {
			  this.udloebsdato = "N/A";
		  } else {
			  udloebsdato = betalinger.get(0).getBetalingsDato();
			  this.udloebsdato = udloebsdato.plusYears(betalinger.size()).toString();
		  }
		  lavRestanceMedlem();
		}

	private void lavRestanceMedlem(){
		if(LocalDate.now().isAfter(LocalDate.parse(udloebsdato))){
			restanceMedlem = true;
		}else{restanceMedlem = false;}
	}

	public String getUdloebsdato() {
		return udloebsdato;
	}

	public String getNavn() {
		return navn;
	}
	public ArrayList<Betaling> getBetalingsHistorik(){
	    return betalinger;
    }
	public LocalDate getFodselsdato() {
		return fodselsdato;
	}
	public String getGender() {
		return gender;
	}
	public String getAdresse() {
		return adresse;
	}
	public String getHusNr() { return husNr; }
	public String getPostNr() { return postNr; }
	public String getEmail() {
		return email;
	}
	public String getMedlemstype() {
		return medlemstype;
	}
	public String getAktivitetstype() {
		return aktivitetstype;
	}
	public boolean getRestanceMedlem() {
		return restanceMedlem;
	}

	public double getPris() {
		return pris;
	}


	// Override af toString. Bare lavet noget som et eksempel p� hvordan vi kunne g�re det.
	    @Override
	    public String toString() {
	        return navn + ";" + fodselsdato +";"+ adresse + ";" + husNr + ";" + postNr + ";" +email+";"+gender+";"+medlemstype+";"+aktivitetstype+";"+betalinger;
	    }
	}
