package com.aoks.banking.operation.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Represents a current account (checking account).
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="CheckingAccount", schema="banking")
public class CheckingAccount extends BankAccount {

	private static final long serialVersionUID = 1L;

}
