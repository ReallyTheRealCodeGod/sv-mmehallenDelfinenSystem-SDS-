import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

class MedlemsListe implements ListeInterface{
	//danner liste som en attribute i MedlemsListe
	private ArrayList<Medlem> liste = new ArrayList<Medlem>();


	//etablere filen der bliver redigeret
	String path = "./src/medlemmer.txt";
	private File f = new File(path);

	MedlemsListe(){
		try{
			// Vælger filen der skal læses fra og variablerne den læses
			ArrayList<LocalDate> betalingsHistorik = new ArrayList<LocalDate>();
			String navn, gender, adresse, husNr, postNr, email, medlemsskabstype, aktivitetstype, restancemedlem;
			LocalDate fodselsdato;
			Scanner line = new Scanner(f);
			//Skan gennem filen, indtil der ikke er flere linjer
		while(line.hasNext()){
			Scanner sc = new Scanner(line.nextLine());
			sc.useDelimiter(";");

			//nulstil variabler
			navn = gender = adresse = email = medlemsskabstype = aktivitetstype = restancemedlem = "";

			navn = sc.next();
			fodselsdato = LocalDate.parse(sc.next());
			adresse = sc.next();
            husNr = sc.next();
			postNr = sc.next();
			email = sc.next();
			gender = sc.next();
			medlemsskabstype = sc.next();
			aktivitetstype = sc.next();
			restancemedlem = sc.next();
			//scanner betalings historiken
			ArrayList<Betaling> betaling = new ArrayList<>();
			System.out.println(betaling);
			sc.useDelimiter("]");
			sc.skip(Pattern.quote(";["));

			if(sc.hasNext()){
				Scanner b = new Scanner(sc.next());
				b.useDelimiter(",");

					while(b.hasNext()){
						String pris = b.next();
						double p = Double.parseDouble(pris);
						String date = b.next();
						LocalDate d = LocalDate.parse(date);
						String bank = b.next();
						betaling.add(new Betaling(p, d, bank));
					}
			}
			//lav medlem og adder til arraylisten
			liste.add(new Medlem(navn, fodselsdato, gender, adresse, husNr, postNr, email, medlemsskabstype, aktivitetstype, restancemedlem, betaling));
			id++;
		}
		}catch(FileNotFoundException e){
			System.out.println("Fejl i dannelse af medlemsliste");
			System.out.println(e);
			// Til debug af indlæsning af fil
			System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));
			System.out.println("File absolute path: " + f.getAbsolutePath());}
	
	}
	//tilføjer et nyt medlems element til arraylisten og kalder derefter for opdateringen af data filen
	public void addMedlem(String navn, LocalDate dato, String gender, String adresse, String husNr, String postNr, String email, String medlemstype,
           String aktivitetstype){
		liste.add(new Medlem(navn, dato, gender, adresse, husNr, postNr, email, medlemstype, aktivitetstype));
		opdaterListe();
	}
	public boolean verificerAddMedlemInput(String navn, LocalDate dato, String gender, String adresse, String husNr, String postNr, String email, String medlemstype,
										   String aktivitetstype){
		ArrayList<String> medlemArray = new ArrayList<>();
		if (navn.isEmpty()) {
			medlemArray.add("Fejl i navn - ret venligst");
		}
		if (dato.isAfter(dato)) {
			medlemArray.add("Fejl i dato - ret venligst");
		}
		if (gender.isEmpty()) {
			medlemArray.add("Fejl i køn - ret venligst");
		}
		if (adresse.isEmpty()) {
			medlemArray.add("Fejl i adresse - ret venligst");
		}

		if (email.isEmpty() || !email.contains("@")) {
			medlemArray.add("Fejl i email. @ mangler");
		}
		if (medlemstype.isEmpty()) {
			medlemArray.add("Fejl i medlemstypen - ret venligst");
		}
		if (aktivitetstype.isEmpty()) {
			medlemArray.add("Fejl i aktivitetstype - ret venligst");
		}
		if (!medlemArray.isEmpty()) {
			UserInterface UI = new UserInterface();
			UI.errorDialog("Fejlbesked",medlemArray.toString());;
			return false;
		} else {
			return true;
		}
	}
	
	public void sletMedlem(int medlemsIndex){
		liste.remove(medlemsIndex);
		opdaterListe();
	}

	public void redigerMedlem(int medlemsIndex, String navn, LocalDate dato, String gender, String adresse, String husNr, String postNr, String email, String medlemstype,
							  String aktivitetstype, ArrayList<Betaling> betalingsHistorik){
		liste.set(medlemsIndex, new Medlem(navn, dato, gender, adresse, husNr, postNr, email, medlemstype, aktivitetstype, betalingsHistorik));
		opdaterListe();

	}
	
	public Medlem hentMedlem(int medlemsIndex){
		if(medlemsIndex >= 0 && medlemsIndex < liste.size()){
		return liste.get(medlemsIndex);
		}
		System.out.println("medlems index out of bounds");
		return liste.get(0);
	}

	public ArrayList<Medlem> getListe(){
		return liste;
	}


	//opdater data filen ud fra arraylisten 
	private void opdaterListe(){
		try{
			//sletter den eksisterende fil og erstatter den med en ny tom fil med samme navn
			f.delete();
			f = new File(path);

			//skriver arraylisten ind i den nye fil
			PrintStream ps = new PrintStream(f);
			for(int i = 0; i < liste.size(); i++){
				ps.println(liste.get(i));
			}
		}catch(FileNotFoundException e){
			System.out.println("Fejl i opdatering:" + e + "\n");
		}
	}

	public ArrayList<Medlem> filtrerListe(String filter) {
		ArrayList<Medlem> filterListe = new ArrayList<>();
		for(Medlem m: liste){
			if(m.getNavn().toLowerCase().contains(filter.toLowerCase())) {
				System.out.println(m.getNavn());
				filterListe.add(m);
			}
		}
		return filterListe;
	}
}