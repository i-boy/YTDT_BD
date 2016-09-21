/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import javax.persistence.FetchType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author user01
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "KIEM_KE_PHAN_LOAI_THUOC")
@NamedQueries({})
public class KiemKePhanLoaiThuoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KIEM_KE_PHAN_LOAI_THUOC")
    @SequenceGenerator(name = "KIEM_KE_PHAN_LOAI_THUOC", sequenceName = "KIEM_KE_PHAN_LOAI_THUOC_ID_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "MAPHIEUKIEM", referencedColumnName = "MAPHIEUKIEM")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private KiemKe maphieukiem;
    @JoinColumn(name = "DMPHANLOAITHUOC_MASO", referencedColumnName = "DMPHANLOAITHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY,optional = true)
    private DmPhanLoaiThuoc dmphanloaithuocMaso;

    public KiemKePhanLoaiThuoc() {
    }

    public KiemKePhanLoaiThuoc(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public KiemKe getMaphieukiem() {
        return maphieukiem;
    }

    public void setMaphieukiem(KiemKe maphieukiem) {
        this.maphieukiem = maphieukiem;
    }

    public DmPhanLoaiThuoc getDmphanloaithuocMaso(boolean create) {
        if(create && dmphanloaithuocMaso == null) dmphanloaithuocMaso = new DmPhanLoaiThuoc();
        return dmphanloaithuocMaso;
    }
    public DmPhanLoaiThuoc getDmphanloaithuocMaso() {
        return dmphanloaithuocMaso;
    }
    public void setDmphanloaithuocMaso(DmPhanLoaiThuoc dmphanloaithuocMaso) {
        this.dmphanloaithuocMaso = dmphanloaithuocMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KiemKePhanLoaiThuoc)) {
            return false;
        }
        KiemKePhanLoaiThuoc other = (KiemKePhanLoaiThuoc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.KiemKePhanLoaiThuoc[id=" + id + "]";
    }

}
