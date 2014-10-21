package com.aoks.banking.operation.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.aoks.banking.bank.model.Bank;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
public abstract class BankAccount extends OperationAccount {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The account bank.
	 */
	@ManyToOne
	@JoinColumn(name="cBank")
	protected Bank bank;

	/**
	 * That bank unit code where this account is registered.
	 */
	@Column(name="cBankUnit")
	protected String bankUnit;
	
	/**
	 * The bank unit verifier digit
	 */
	@Column(name="cBankUnitDigit")
	protected String bankUnitDigit;
	
	/**
	 * The date in which that account started its operation <b>in the system</b>,
	 * to contrast with the date of the account opening in the Bank.
	 */
	@Temporal(TemporalType.DATE)
	@Column(name="cStart")
	protected Calendar start;
	
	/**
	 * The opening date of the account in the Bank.
	 */
	@Temporal(TemporalType.DATE)
	@Column(name="cOpening")
	protected Calendar opening;
	
	/**
	 * The start balance for this account.
	 */
	@Column(name="cStartBalance")
	protected BigDecimal startBalance;
	
	/**
	 * The account number as registered in the Bank.
	 */
	@Column(name="cNumber")
	protected String number;
	
	/**
	 * The account verifier digit as registered in the Bank.
	 */
	@Column(name="cDigit")
	protected String digit;

	
	public Bank getBank() 									{ return bank; }
	public void setBank(Bank bank)							{ this.bank = bank; }
	
	public String getBankUnit() 							{ return bankUnit; }
	public void setBankUnit(String bankUnit)				{ this.bankUnit = bankUnit; }
	
	public String getBankUnitDigit() 						{ return bankUnitDigit; }
	public void setBankUnitDigit(String bankUnitDigit)		{ this.bankUnitDigit = bankUnitDigit; }
	
	public Calendar getStart() 								{ return start; }
	public void setStart(Calendar start) 					{ this.start = start; }
	
	public Calendar getOpening() 							{ return opening; }
	public void setOpening(Calendar opening) 				{ this.opening = opening; }
	
	public BigDecimal getStartBalance() 					{ return startBalance; }
	public void setStartBalance(BigDecimal startBalance)	{ this.startBalance = startBalance; }
	
	public String getNumber() 								{ return number; }
	public void setNumber(String number) 					{ this.number = number; }
	
	public String getDigit() 								{ return digit; }
	public void setDigit(String digit) 						{ this.digit = digit; }
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof CheckingAccount)) return false;
		CheckingAccount that = (CheckingAccount) obj;
		if ((this.number == null ? that.number == null : this.number.equals(that.number)) &&
				(this.digit == null ? that.digit == null : this.digit.equals(that.digit))) return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 13;
		result = result * 31 + (number != null ? number.hashCode() : 0);
		result = result * 31 + (digit != null ? digit.hashCode() : 0);
		return result;
	}
	
}
