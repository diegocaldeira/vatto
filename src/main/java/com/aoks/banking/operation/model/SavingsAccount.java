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
@Table(name="SavingsAccount", schema="banking")
public class SavingsAccount extends BankAccount {

	private static final long serialVersionUID = 1L;

}
