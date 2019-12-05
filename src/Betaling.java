import java.time.LocalDate;

public class Betaling {
        private double beloeb;
        private LocalDate betalingsDato;
        private String bankNummer;

        Betaling(double beloeb, LocalDate betalingsDato, String bankNummer){
            this.beloeb = beloeb;
            this.betalingsDato = betalingsDato;
            this.bankNummer = bankNummer;
        }

        public double getBeloeb(){
            return beloeb;
        }

        public LocalDate getBetalingsDato(){
            return betalingsDato;
        }

        public String bankNummer(){
            return bankNummer;
        }

        public String toString(){
            return "" + beloeb +", "+ betalingsDato +", "+ bankNummer + "";
        }
    }
 