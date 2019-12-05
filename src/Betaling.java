import java.time.LocalDate;

public class Betaling {
        private double pris;
        private LocalDate betalingsDato;
        private String bankNummer;

        Betaling(double pris, String betalingsDato, String bankNummer){
            this(pris, LocalDate.parse(betalingsDato), bankNummer);
        }
        Betaling(double pris, LocalDate betalingsDato, String bankNummer){
            this.pris = pris;
            this.betalingsDato = betalingsDato;
            this.bankNummer = bankNummer;
        }

        public double getPris(){
            return pris;
        }

        public LocalDate getBetalingsDato(){
            return betalingsDato;
        }

        public String bankNummer(){
            return bankNummer;
        }

        public String toString(){
            return "" +pris +", "+ betalingsDato +", "+ bankNummer + "";
        }
    }
 