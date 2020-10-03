package transaction;

public class MoneyMarket extends Account {
	private int withdrawals;
	
	public MoneyMarket(Profile holder, double balance, Date dateOpen, int withdrawals) {
		super(holder, balance, dateOpen);
		this.withdrawals = withdrawals;
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
		if(obj instanceof MoneyMarket) {
		MoneyMarket comparison = (MoneyMarket)obj;
			return this.getHolder().equals(comparison.getHolder()) && this.getBalance() == comparison.getBalance()&&
					this.getDateOpen().toString().equals(comparison.getDateOpen().toString()) && this.withdrawals==comparison.withdrawals;
		}
		return false;
	}

	@Override
	public String toString() {
		
		return this.getHolder().toString() + " " + this.getBalance() +" " + this.getDateOpen().toString() + " " + this.withdrawals;
		
	}

	
}
