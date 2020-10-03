package transaction;

public class Saving extends Account{
	private boolean isLoyal;
	public Saving(Profile holder, double balance, Date dateOpen, boolean isLoyal) {
		super(holder, balance, dateOpen);
		this.isLoyal = isLoyal;
	}

	@Override
	public double monthlyInterest() {
		
		return 0;
	}

	@Override
	public double monthlyFee() {
		
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Saving) {
			Saving comparison = (Saving)obj;
			return this.getHolder().equals(comparison.getHolder()) && this.getBalance() == comparison.getBalance()&&
					this.getDateOpen().toString().equals(comparison.getDateOpen().toString()) && this.isLoyal==comparison.isLoyal;
		}
		return false;
	}

	@Override
	public String toString() {
		
		return this.getHolder() + " " + this.getBalance() +" " + this.getDateOpen() + " " + this.isLoyal;
		
	}
	
}
