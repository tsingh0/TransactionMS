package transaction;

public class Checking extends Account {
	private boolean directDeposit;

	public Checking(Profile holder, double balance, Date dateOpen, boolean directDeposit) {

		super(holder, balance, dateOpen);
		this.directDeposit = directDeposit;
	}

	@Override
	public double monthlyInterest() {

		return 0;
	}

	@Override
	public double monthlyFee() {

		return 0;
	}
	// IDk if were supposed to compare the get date strings or compare if the date
	// is less than or shit

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Checking) {
			Checking comparison = (Checking) obj;
			/*
			 * return this.getHolder().equals(comparison.getHolder()) && this.getBalance()
			 * == comparison.getBalance()&&
			 * this.getDateOpen().toString().equals(comparison.getDateOpen().toString()) &&
			 * this.directDeposit==comparison.directDeposit;
			 */
			return this.getHolder().equals(comparison.getHolder());
		}
		return false;
	}

	@Override
	public String toString() {

		return super.toString() + " " + this.directDeposit;

	}

}
