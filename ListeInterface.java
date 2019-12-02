Interface ListeInterface{
	public ArrayList<Medlem> getListe();
	public ArrayList<Medlem> filtrerListe();
	public Medlem getMedlem();
	public void editMedlem(int medlemsIndex);
	public void deleteMedlem(int medlemsIndex);
	public void addMedlem(String navn,  int aar, int maaned, int dag, String koen, String adresse, String email, String medlemstype,
           String aktivitetstype, ArrayList<LocalDate> betalingsHistorik);
	private void opdaterListe();
}