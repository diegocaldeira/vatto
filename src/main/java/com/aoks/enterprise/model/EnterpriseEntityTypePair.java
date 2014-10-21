package com.aoks.enterprise.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Defines a pair of {@link EnterpriseEntityType}'s, a parent and a child, that
 * can be combined on an {@link Accountability}.
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 * 
 */
@Embeddable
public class EnterpriseEntityTypePair {

    @Enumerated(EnumType.STRING)
    @Column(name = "cParentType")
    private EnterpriseEntityType parent;

    @Enumerated(EnumType.STRING)
    @Column(name = "cChildType")
    private EnterpriseEntityType child;

    public EnterpriseEntityTypePair() {
    }

    public EnterpriseEntityTypePair(EnterpriseEntityType child, EnterpriseEntityType parent) {

        if (parent == null || child == null) {
            throw new IllegalArgumentException("Unable to construct EnterpriseEntityTypePair: one of the arguments was null.");
        }

        this.parent = parent;
        this.child = child;
    }

    public EnterpriseEntityType getParent() {
        return parent;
    }

    public EnterpriseEntityType getChild() {
        return child;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof EnterpriseEntityTypePair)) return false;
        EnterpriseEntityTypePair that = (EnterpriseEntityTypePair) obj;
        if (this.child.equals(that.child) && this.parent.equals(that.parent))  return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = 13;
        result = result * 31 + this.child.hashCode();
        result = result * 31 + this.parent.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
