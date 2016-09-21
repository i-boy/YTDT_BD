/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Thanh
 */
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "CLS_KET_QUA")
@NamedQueries({@NamedQuery(name = "ClsKetQua.findByClskqMaso", query = "SELECT c FROM ClsKetQua c WHERE c.clskqMaso = :clskqMaso"), @NamedQuery(name = "ClsKetQua.findByClskqMa", query = "SELECT c FROM ClsKetQua c WHERE c.clskqMa = :clskqMa"), @NamedQuery(name = "ClsKetQua.findByClskqTen", query = "SELECT c FROM ClsKetQua c WHERE c.clskqTen = :clskqTen"), @NamedQuery(name = "ClsKetQua.findByTestCode", query = "SELECT c FROM ClsKetQua c WHERE c.testCode = :testCode"), @NamedQuery(name = "ClsKetQua.findByOrderID", query = "SELECT c FROM ClsKetQua c WHERE c.orderID = :orderID"), @NamedQuery(name = "ClsKetQua.findByTestCodeHIS", query = "SELECT c FROM ClsKetQua c WHERE c.testCodeHIS = :testCodeHIS"), @NamedQuery(name = "ClsKetQua.findByResult", query = "SELECT c FROM ClsKetQua c WHERE c.result = :result"), @NamedQuery(name = "ClsKetQua.findByNormalRange", query = "SELECT c FROM ClsKetQua c WHERE c.normalRange = :normalRange"), @NamedQuery(name = "ClsKetQua.findByUnit", query = "SELECT c FROM ClsKetQua c WHERE c.unit = :unit"), @NamedQuery(name = "ClsKetQua.findByUnNormal", query = "SELECT c FROM ClsKetQua c WHERE c.unNormal = :unNormal"), @NamedQuery(name = "ClsKetQua.findByTimeIN", query = "SELECT c FROM ClsKetQua c WHERE c.timeIN = :timeIN")})
public class ClsKetQua implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLS_KET_QUA_CLSKQ_MASO")
    @SequenceGenerator(name = "CLS_KET_QUA_CLSKQ_MASO", sequenceName = "CLS_KET_QUA_CLSKQ_MASO_SEQ", allocationSize = 1)
    @Column(name = "CLSKQ_MASO", nullable = false)
    private Integer clskqMaso;
    @Column(name = "CLSKQ_MA")
    private String clskqMa;
    @Column(name = "CLSKQ_TEN")
    private String clskqTen;
    @Lob
    @Column(name = "CLSKQ_GHI_CHU")
    private String clskqGhiChu;
    @Column(name = "TESTCODE")
    private String testCode;
    @Column(name = "ORDERID")
    private String orderID;
    @Column(name = "TESTCODEHIS")
    private String testCodeHIS;
    @Column(name = "CLS_RESULT")
    private String result;
    @Column(name = "NORMALRANGE")
    private String normalRange;
    @Column(name = "UNIT")
    private String unit;
    @Column(name = "UNNORMAL")
    private Short unNormal;
    @Column(name = "TIMEIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeIN;
    @JoinColumn(name = "CLSKHAM_MASO", referencedColumnName = "CLSKHAM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private ClsKham clskhamMaso;
	@JoinColumn(name = "CLSMO_MASO", referencedColumnName = "CLSMO_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private ClsMo clsmoMaso;

    public ClsKetQua() {
    }

    public ClsKetQua(Integer clskqMaso) {
        this.clskqMaso = clskqMaso;
    }

    public Integer getClskqMaso() {
        return clskqMaso;
    }

    public void setClskqMaso(Integer clskqMaso) {
        this.clskqMaso = clskqMaso;
    }

    public String getClskqMa() {
        return clskqMa;
    }

    public void setClskqMa(String clskqMa) {
        this.clskqMa = clskqMa;
    }

    public String getClskqTen() {
        return clskqTen;
    }

    public void setClskqTen(String clskqTen) {
        this.clskqTen = clskqTen;
    }

    public String getClskqGhiChu() {
        return clskqGhiChu;
    }

    public void setClskqGhiChu(String clskqGhiChu) {
        this.clskqGhiChu = clskqGhiChu;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getTestCodeHIS() {
        return testCodeHIS;
    }

    public void setTestCodeHIS(String testCodeHIS) {
        this.testCodeHIS = testCodeHIS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNormalRange() {
        return normalRange;
    }

    public void setNormalRange(String normalRange) {
        this.normalRange = normalRange;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Short getUnNormal() {
        return unNormal;
    }

    public void setUnNormal(Short unNormal) {
        this.unNormal = unNormal;
    }

    public Date getTimeIN() {
        return timeIN;
    }

    public void setTimeIN(Date timeIN) {
        this.timeIN = timeIN;
    }

    public ClsKham getClskhamMaso() {
        return clskhamMaso;
    }

    public void setClskhamMaso(ClsKham clskhamMaso) {
        this.clskhamMaso = clskhamMaso;
    }

	    public ClsMo getClsmoMaso() {
        return clsmoMaso;
    }

    public void setClsmoMaso(ClsMo clsmoMaso) {
        this.clsmoMaso = clsmoMaso;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clskqMaso != null ? clskqMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClsKetQua)) {
            return false;
        }
        ClsKetQua other = (ClsKetQua) object;
        if ((this.clskqMaso == null && other.clskqMaso != null) || (this.clskqMaso != null && !this.clskqMaso.equals(other.clskqMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ClsKetQua[clskqMaso=" + clskqMaso + "]";
    }

}
