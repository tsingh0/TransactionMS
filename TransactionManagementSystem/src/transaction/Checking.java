package transaction;

public class Checking extends Account{
	private boolean directDeposit;

	@Override
	public double monthlyInterest() {
		
		return 0;
	}

	@Override
	public double monthlyFee() {
		
		return 0;
	}
		
	
}
