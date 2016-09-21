/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.entity;

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
@Table(name = "DM_TON_GIAO")
@NamedQueries({})
public class DmTonGiao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_TON_GIAO")
    @SequenceGenerator(name = "DM_TON_GIAO", sequenceName = "DM_TON_GIAO_TONGIAO_MASO_SEQ", allocationSize = 1)
    @Column(name = "TONGIAO_MASO", nullable = false)
    private Integer tongiaoMaso;
    @Column(name = "TONGIAO_MA", nullable = false)
    private String tongiaoMa;
    @Column(name = "TONGIAO_TEN")
    private String tongiaoTen;
    @Column(name = "TONGIAO_NGAYGIOCN")
    private Double tongiaoNgaygiocn;
    @Column(name = "TONGIAO_CHON")
    private Boolean tongiaoChon;

    public DmTonGiao() {
    }

    public DmTonGiao(Integer tongiaoMaso) {
        this.tongiaoMaso = tongiaoMaso;
    }

    public DmTonGiao(Integer tongiaoMaso, String tongiaoMa) {
        this.tongiaoMaso = tongiaoMaso;
        this.tongiaoMa = tongiaoMa;
    }

    public Integer getTongiaoMaso() {
        return tongiaoMaso;
    }

    public void setTongiaoMaso(Integer tongiaoMaso) {
        this.tongiaoMaso = tongiaoMaso;
    }

    public String getTongiaoMa() {
        return tongiaoMa;
    }

    public void setTongiaoMa(String tongiaoMa) {
        this.tongiaoMa = tongiaoMa;
    }

    public String getTongiaoTen() {
        return tongiaoTen;
    }

    public void setTongiaoTen(String tongiaoTen) {
        this.tongiaoTen = tongiaoTen;
    }

    public Double getTongiaoNgaygiocn() {
        return tongiaoNgaygiocn;
    }

    public void setTongiaoNgaygiocn(Double tongiaoNgaygiocn) {
        this.tongiaoNgaygiocn = tongiaoNgaygiocn;
    }

    public Boolean getTongiaoChon() {
        return tongiaoChon;
    }

    public void setTongiaoChon(Boolean tongiaoChon) {
        this.tongiaoChon = tongiaoChon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tongiaoMaso != null ? tongiaoMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmTonGiao)) {
            return false;
        }
        DmTonGiao other = (DmTonGiao) object;
        if ((this.tongiaoMaso == null && other.tongiaoMaso != null) || (this.tongiaoMaso != null && !this.tongiaoMaso.equals(other.tongiaoMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmTonGiao[tongiaoMaso=" + tongiaoMaso + "]";
    }

}


