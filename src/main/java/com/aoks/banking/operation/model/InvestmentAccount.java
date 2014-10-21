package com.aoks.banking.operation.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="InvestmentAccount", schema="banking")
public class InvestmentAccount extends BankAccount {

	private static final long serialVersionUID = 1L;

}
