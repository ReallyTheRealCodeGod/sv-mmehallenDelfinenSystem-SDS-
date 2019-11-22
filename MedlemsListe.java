import java.io.*;
import java.io.IOException.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

class MedlemsListe{
	//danner liste som en attribute i MedlemsListe
	ArrayList<Medlem> liste = new ArrayList<Medlem>();
	//etablere filen der bliver redigeret
	File f = new File("medlemsliste.txt");

	MedlemsListe(){

		try{
			// Vælger filen der skal læses fra og variablerne den læses
			ArrayList<LocalDate> betalingsHistorik = new ArrayList<LocalDate>();
			String navn, adresse, email, koen, medlemsskabstype, aktivitetstype;
			LocalDate foedselsdato;
			Scanner line = new Scanner(f);	
			//Skan gennem filen, indtil der ikke er flere linjer
		while(line.hasNext()){
			betalingsHistorik.clear();
			Scanner sc = new Scanner(line.nextLine());
			sc.useDelimiter(";");

			//nulstil variabler
			navn = adresse = email = koen = medlemsskabstype = aktivitetstype = "";

			navn = sc.next();
			foedselsdato = LocalDate.parse(sc.next());
			adresse = sc.next();
			email = sc.next();
			koen = sc.next();
			medlemsskabstype = sc.next();
			aktivitetstype = sc.next();

			//scanner betalings historiken
			sc.useDelimiter(",");
			sc.next();
			while(sc.hasNext()){
				betalingsHistorik.add(LocalDate.parse(sc.next()));
			}
			//lav medlem og adder til arraylisten
			liste.add(new Medlem(navn, foedselsdato, koen, adresse, email, medlemsskabstype, aktivitetstype, betalingsHistorik));}
		}catch(FileNotFoundException e){System.out.println("Fejl i dannelse af medlemsliste");}
	
	}
	//tilføjer et nyt medlems element til arraylisten og kalder derefter for opdateringen af data filen
	public void addMedlem(String navn,  int aar, int maaned, int dag, String koen, String adresse, String email, String medlemstype,
           String aktivitetstype, ArrayList<LocalDate> betalingsHistorik){
		liste.add(new Medlem(navn, aar, maaned, dag, koen, adresse, email, medlemstype, aktivitetstype, betalingsHistorik));
		updateList();
	}
	
	public void sletMedlem(int medlemsIndex){
		liste.remove(medlemsIndex);
		updateList();
	}

	public void redigerMedlem(int medlemsIndex, String navn,  int aar, int maaned, int dag, String koen, String adresse, String email, String medlemstype,
           String aktivitetstype, ArrayList<LocalDate> betalingsHistorik){
		liste.set(medlemsIndex, new Medlem(navn, aar, maaned, dag, koen, adresse, email, medlemstype, aktivitetstype, betalingsHistorik));
		updateList();

	}
	
	public Medlem getMedlem(int medlemsIndex){
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
	private void updateList(){
		try{
			//sletter den eksisterende fil og erstatter den med en ny tom fil med samme navn
			f.delete();
			f = new File("Medlemsliste.txt");

			//skriver arraylisten ind i den nye fil
			PrintStream ps = new PrintStream(f);
			for(int i = 0; i < liste.size(); i++){
				ps.println(liste.get(i));
			}
		}catch(FileNotFoundException e){
			System.out.println("Fejl i opdatering");
		}
	}

}