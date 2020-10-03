package transaction;

public class Saving extends Account{
	private boolean isLoyal;

	@Override
	public double monthlyInterest() {
		
		return 0;
	}

	@Override
	public double monthlyFee() {
		
		return 0;
	}
	
}
