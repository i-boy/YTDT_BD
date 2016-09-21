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
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DM_HOAT_CHAT")
@NamedQueries({})
public class DmHoatChat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_HOAT_CHAT")
    @SequenceGenerator(name = "DM_HOAT_CHAT", sequenceName = "DM_HOAT_CHAT_DMHOATCHAT_MASO_S", allocationSize = 1)
    @Column(name = "DMHOATCHAT_MASO", nullable = false)
    private Integer dmhoatchatMaso;
    @Column(name = "DMHOATCHAT_MA")
    private String dmhoatchatMa;
    @Column(name = "DMHOATCHAT_ATCCODE")
    private String dmhoatchatAtccode;
    @Column(name = "DMHOATCHAT_TEN", nullable = false)
    private String dmhoatchatTen;
    @Column(name = "DMHOATCHAT_MAPHU")
    private String dmhoatchatMaphu;
    @Column(name = "DMHOATCHAT_NHOM")
    private String dmhoatchatNhom;
    @Column(name = "DMHOATCHAT_MALOAI")
    private String dmhoatchatMaloai;
    @Column(name = "DMHOATCHAT_PHLOAI")
    private String dmhoatchatPhloai;
    @Column(name = "DMHOATCHAT_GHICHU")
    private String dmhoatchatGhichu;
    @Column(name = "NHANVIEN_CN", nullable = false)
    private String nhanvienCn;
    @Column(name = "DMHOATCHAT_NGAYGIOCN")
    private Double dmhoatchatNgaygiocn;
    @Column(name = "DMHOATCHAT_DT")
    private Boolean dmhoatchatDt;
    @Column(name = "DMHOATCHAT_QL")
    private Boolean dmhoatchatQl;
    @Column(name = "DMHOATCHAT_DP")
    private Boolean dmhoatchatDp;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmhoatchatMaso")
//    private Collection<DmThuocHoatChat> dmThuocHoatChatCollection;

    public DmHoatChat() {
    }

    public DmHoatChat(Integer dmhoatchatMaso) {
        this.dmhoatchatMaso = dmhoatchatMaso;
    }

    public DmHoatChat(Integer dmhoatchatMaso, String dmhoatchatTen, String nhanvienCn) {
        this.dmhoatchatMaso = dmhoatchatMaso;
        this.dmhoatchatTen = dmhoatchatTen;
        this.nhanvienCn = nhanvienCn;
    }

    public Integer getDmhoatchatMaso() {
        return dmhoatchatMaso;
    }

    public void setDmhoatchatMaso(Integer dmhoatchatMaso) {
        this.dmhoatchatMaso = dmhoatchatMaso;
    }

    public String getDmhoatchatMa() {
        return dmhoatchatMa;
    }

    public void setDmhoatchatMa(String dmhoatchatMa) {
        this.dmhoatchatMa = dmhoatchatMa;
    }

    public String getDmhoatchatAtccode() {
        return dmhoatchatAtccode;
    }

    public void setDmhoatchatAtccode(String dmhoatchatAtccode) {
        this.dmhoatchatAtccode = dmhoatchatAtccode;
    }

    public String getDmhoatchatTen() {
        return dmhoatchatTen;
    }

    public void setDmhoatchatTen(String dmhoatchatTen) {
        this.dmhoatchatTen = dmhoatchatTen;
    }

    public String getDmhoatchatMaphu() {
        return dmhoatchatMaphu;
    }

    public void setDmhoatchatMaphu(String dmhoatchatMaphu) {
        this.dmhoatchatMaphu = dmhoatchatMaphu;
    }

    public String getDmhoatchatNhom() {
        return dmhoatchatNhom;
    }

    public void setDmhoatchatNhom(String dmhoatchatNhom) {
        this.dmhoatchatNhom = dmhoatchatNhom;
    }

    public String getDmhoatchatMaloai() {
        return dmhoatchatMaloai;
    }

    public void setDmhoatchatMaloai(String dmhoatchatMaloai) {
        this.dmhoatchatMaloai = dmhoatchatMaloai;
    }

    public String getDmhoatchatPhloai() {
        return dmhoatchatPhloai;
    }

    public void setDmhoatchatPhloai(String dmhoatchatPhloai) {
        this.dmhoatchatPhloai = dmhoatchatPhloai;
    }

    public String getDmhoatchatGhichu() {
        return dmhoatchatGhichu;
    }

    public void setDmhoatchatGhichu(String dmhoatchatGhichu) {
        this.dmhoatchatGhichu = dmhoatchatGhichu;
    }

    public String getNhanvienCn() {
        return nhanvienCn;
    }

    public void setNhanvienCn(String nhanvienCn) {
        this.nhanvienCn = nhanvienCn;
    }

    public Double getDmhoatchatNgaygiocn() {
        return dmhoatchatNgaygiocn;
    }

    public void setDmhoatchatNgaygiocn(Double dmhoatchatNgaygiocn) {
        this.dmhoatchatNgaygiocn = dmhoatchatNgaygiocn;
    }

    public Boolean getDmhoatchatDt() {
        return dmhoatchatDt;
    }

    public void setDmhoatchatDt(Boolean dmhoatchatDt) {
        this.dmhoatchatDt = dmhoatchatDt;
    }

    public Boolean getDmhoatchatQl() {
        return dmhoatchatQl;
    }

    public void setDmhoatchatQl(Boolean dmhoatchatQl) {
        this.dmhoatchatQl = dmhoatchatQl;
    }

    public Boolean getDmhoatchatDp() {
        return dmhoatchatDp;
    }

    public void setDmhoatchatDp(Boolean dmhoatchatDp) {
        this.dmhoatchatDp = dmhoatchatDp;
    }

//    public Collection<DmThuocHoatChat> getDmThuocHoatChatCollection() {
//        return dmThuocHoatChatCollection;
//    }
//
//    public void setDmThuocHoatChatCollection(Collection<DmThuocHoatChat> dmThuocHoatChatCollection) {
//        this.dmThuocHoatChatCollection = dmThuocHoatChatCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmhoatchatMaso != null ? dmhoatchatMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmHoatChat)) {
            return false;
        }
        DmHoatChat other = (DmHoatChat) object;
        if ((this.dmhoatchatMaso == null && other.dmhoatchatMaso != null) || (this.dmhoatchatMaso != null && !this.dmhoatchatMaso.equals(other.dmhoatchatMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmHoatChat[dmhoatchatMaso=" + dmhoatchatMaso + "]";
    }

}


