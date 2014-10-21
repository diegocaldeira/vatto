package com.aoks.budget.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
@Entity
@Table(name="TransferInfo", schema="budget")
public class TransferInfo extends FlowItemInfo {

	private static final long serialVersionUID = 1L;

}
