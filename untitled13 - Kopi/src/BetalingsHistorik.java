 import java.time.LocalDate;

    public class BetalingsHistorik{
        private double pris;
        private LocalDate betalingsDato;
        private String bankNummer;

        BetalingsHistorik(double pris, String betalingsDato, String bankNummer){
            this(pris, LocalDate.parse(betalingsDato), bankNummer);
        }
        BetalingsHistorik(double pris, LocalDate betalingsDato, String bankNummer){
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
