package transaction;

public class MoneyMarket extends Account {
	private int withdrawals;

	public MoneyMarket(Profile holder, double balance, Date dateOpen, int withdrawals) {
		super(holder, balance, dateOpen);
		this.withdrawals = withdrawals;
	}

	@Override
	public double monthlyInterest() {
		
		return this.getBalance()*(12/0.0065);
	}

	@Override
	public double monthlyFee() {
		if(this.withdrawals<=6 || this.getBalance()>=2500 ) {
		return 0;
		}
		return 12;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MoneyMarket) {
			MoneyMarket comparison = (MoneyMarket) obj;
			/*
			 * return this.getHolder().equals(comparison.getHolder()) && this.getBalance()
			 * == comparison.getBalance()&&
			 * this.getDateOpen().toString().equals(comparison.getDateOpen().toString()) &&
			 * this.withdrawals==comparison.withdrawals;
			 */
			return this.getHolder().equals(comparison.getHolder());
		}
		return false;
	}

	@Override
	public String toString() {

		return super.toString() + " " + this.withdrawals;

	}
	
	public int getWithdrawals() {
		return this.withdrawals;
	}
	
	public void setWithdrawals(int withdrawals) {
		this.withdrawals= this.withdrawals+withdrawals;
	}

}
