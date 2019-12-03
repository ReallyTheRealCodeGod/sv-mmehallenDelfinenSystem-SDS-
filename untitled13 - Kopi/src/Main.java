import java.util.ArrayList;

class Main {
	public static void main(String[] args) {
		MedlemsListe m = new MedlemsListe();
		ArrayList<Medlem> al = m.getListe();



		for(int i = 0; i < al.size(); i++){
			al.get(i).addUdloebsDato();
		}
	}
}