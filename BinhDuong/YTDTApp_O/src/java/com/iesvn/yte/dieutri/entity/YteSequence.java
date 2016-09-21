/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "YTE_SEQUENCE")
@NamedQueries({})
public class YteSequence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "YTE_SEQUENCE_SEQUENCE")
    @SequenceGenerator(name = "YTE_SEQUENCE_SEQUENCE", sequenceName = "YTE_SEQUENCE_SEQUENCE_ID_SEQ", allocationSize = 1)
    @Column(name = "SEQUENCE_ID", nullable = false)
    private Long sequenceId;
    @Column(name = "SEQUENCE_NAME_MA")
    private String sequenceNameMa;
    @Column(name = "SEQUENCE_CURRENT_VALUE")
    private Integer sequenceCurrentValue;
    @Column(name = "SEQUENCE_CURRENT_NEXT")
    private Integer sequenceCurrentNext;
    @Column(name = "SEQUENCE_STEP")
    private Integer sequenceStep;
    @Column(name = "SEQUENCE_RESET_YEAR")
    private Boolean sequenceResetYear;

    public YteSequence() {
    }

    public YteSequence(Long sequenceId) {
        this.sequenceId = sequenceId;
    }

    public Long getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(Long sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getSequenceNameMa() {
        return sequenceNameMa;
    }

    public void setSequenceNameMa(String sequenceNameMa) {
        this.sequenceNameMa = sequenceNameMa;
    }

    public Integer getSequenceCurrentValue() {
        return sequenceCurrentValue;
    }

    public void setSequenceCurrentValue(Integer sequenceCurrentValue) {
        this.sequenceCurrentValue = sequenceCurrentValue;
    }

    public Integer getSequenceCurrentNext() {
        return sequenceCurrentNext;
    }

    public void setSequenceCurrentNext(Integer sequenceCurrentNext) {
        this.sequenceCurrentNext = sequenceCurrentNext;
    }

    public Integer getSequenceStep() {
        return sequenceStep;
    }

    public void setSequenceStep(Integer sequenceStep) {
        this.sequenceStep = sequenceStep;
    }

    public Boolean getSequenceResetYear() {
        return sequenceResetYear;
    }

    public void setSequenceResetYear(Boolean sequenceResetYear) {
        this.sequenceResetYear = sequenceResetYear;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sequenceId != null ? sequenceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof YteSequence)) {
            return false;
        }
        YteSequence other = (YteSequence) object;
        if ((this.sequenceId == null && other.sequenceId != null) || (this.sequenceId != null && !this.sequenceId.equals(other.sequenceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.YteSequence[sequenceId=" + sequenceId + "]";
    }

}


