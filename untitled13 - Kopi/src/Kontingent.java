import java.time.LocalDate;
import java.time.Period;

public class Kontingent {
	
	private final static double JUNIOR = 1000;
	private final static double SENIOR = 1600;
	private final static double PENSIONIST = SENIOR*0.75;
	private final static double PASSIV = 500;
	
	

	public static double getPris(LocalDate foedselsdato, String medlemstype) {
		LocalDate nu = LocalDate.now();
		int age = Period.between(foedselsdato, nu).getYears();
		
		if(medlemstype.equals("passiv")) {
			return PASSIV;
		}
		
		if(age > 60) {
			
		return PENSIONIST;	
		}else if(age <= 60 && age >= 18) {
		
		return SENIOR;	
			
		}else if(age < 18) {
			
		return JUNIOR;	
		}
		
	return 0;
	}




}