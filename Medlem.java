import java.time.LocalDate;
import java.util.ArrayList;

public class Medlem {
	    private String navn;
	    private LocalDate foedselsdato;
	    private String koen;
	    private String adresse;
	    private String email;
	    private String medlemstype;
	    private String aktivitetstype;
	    private ArrayList<LocalDate> betalingsHistorik;
	    private LocalDate udloebsdato;
	    private double pris;
	    
	    
	   

	    Medlem(String navn, int aar, int maaned, int dag, String koen, String adresse, String email, String medlemstype,
	           String aktivitetstype, ArrayList<LocalDate> betalingsHistorik) {

	       this(navn, LocalDate.of(aar, maaned, dag), koen, adresse, email, medlemstype,
                   aktivitetstype, betalingsHistorik);

	    }
	    
	    Medlem(String navn, LocalDate foedelsdato, String koen, String adresse, String email, String medlemstype,
		           String aktivitetstype, ArrayList<LocalDate> betalingsHistorik) {
		        this.navn = navn;
		        this.foedselsdato = foedelsdato;
		        this.koen = koen;
		        this.adresse = adresse;
		        this.email = email;
		        this.medlemstype = medlemstype;
		        this.aktivitetstype = aktivitetstype;
		        this.pris = Kontingent.getPris(this.foedselsdato, medlemstype);
                this.betalingsHistorik = betalingsHistorik;
		    }
	    
	 /* public LocalDate addUdloebsDato(){
        
        if(betalingsHistorik != null){
            LocalDate nuvaerende = betalingsHistorik.get(betalingsHistorik.size() - 1)
    }else{
                nuvaerende = LocalDate.now();
            }
        

            LocalDate udloebsdato = LocalDate.now();

	    	if(nuvaerende.isEqual(LocalDate.now())){
                udloebsdato = LocalDate.now().plusYears(1);
            }else if(nuvaerende.isAfter(LocalDate.now())){
                udloebsdato = nuvaerende.plusYears(1);
            }else if(nuvaerende.isBefore(LocalDate.now())){
                udloebsdato = LocalDate.now().plusYears(1);
            }
	    	this.udloebsdato = udloebsdato;	

            System.out.println(udloebsdato);

            return udloebsdato;
	    }*/
	    
	    

	    // Override af toString. Bare lavet noget som et eksempel på hvordan vi kunne gøre det.
	    @Override
	    public String toString() {
	        
	        return navn + ";" + foedselsdato +";"+ adresse +";"+email+";"+koen+";"+medlemstype+";"+aktivitetstype+";";
	               
	    }
	}
