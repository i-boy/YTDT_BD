/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmKhoa;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "DT_DM_NHANVIEN_KHOA")
@NamedQueries({})
public class DtDmNhanvienKhoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_NHANVIEN_KHOA")
    @SequenceGenerator(name = "DT_DM_NHANVIEN_KHOA", sequenceName = "DT_DM_NHANVIEN_KHOA_DTDMNHANVI", allocationSize = 1)
    @Column(name = "DTDMNHANVIENKHOA_MASO", nullable = false)
    private Integer dtdmnhanvienkhoaMaso;
    @JoinColumn(name = "DTDMNHANVIEN_MASO", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien dtdmnhanvienMaso;
    @JoinColumn(name = "DMKHOA_MASO", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa dmkhoaMaso;
    
    
      @Column(name = "DTDMNVKHOA_CHON")
    private Boolean  dtdmnhanvienKhoaChon;
    
     @Column(name = "DTDMNVKHOA_NGAYGIOCN")
    private Double dtdmnhanvienKhoaNgaygiocn;
     
//    @Column(name = "DTDMNHANVIENKHOA_NGAYGIOCN")
//    private Double dtdmnhanvienkhoaNgaygiocn;
//    @Column(name = "DTDMNHANVIENKHOA_CHON")
//    private Boolean dtdmnhanvienkhoaChon;

    public DtDmNhanvienKhoa() {
    }

    public DtDmNhanvienKhoa(Integer dtdmnhanvienkhoaMaso) {
        this.dtdmnhanvienkhoaMaso = dtdmnhanvienkhoaMaso;
    }

    public Integer getDtdmnhanvienkhoaMaso() {
        return dtdmnhanvienkhoaMaso;
    }

    public void setDtdmnhanvienkhoaMaso(Integer dtdmnhanvienkhoaMaso) {
        this.dtdmnhanvienkhoaMaso = dtdmnhanvienkhoaMaso;
    }

    public DtDmNhanVien getDtdmnhanvienMaso(boolean create) {
if(create && dtdmnhanvienMaso == null) dtdmnhanvienMaso = new DtDmNhanVien();
return dtdmnhanvienMaso;
}
    public DtDmNhanVien getDtdmnhanvienMaso() {
        return dtdmnhanvienMaso;
    }

    public void setDtdmnhanvienMaso(DtDmNhanVien dtdmnhanvienMaso) {
        this.dtdmnhanvienMaso = dtdmnhanvienMaso;
    }

    public DmKhoa getDmkhoaMaso(boolean create) {
if(create && dmkhoaMaso == null) dmkhoaMaso = new DmKhoa();
return dmkhoaMaso;
}
    public DmKhoa getDmkhoaMaso() {
        return dmkhoaMaso;
    }

    public void setDmkhoaMaso(DmKhoa dmkhoaMaso) {
        this.dmkhoaMaso = dmkhoaMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmnhanvienkhoaMaso != null ? dtdmnhanvienkhoaMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmNhanvienKhoa)) {
            return false;
        }
        DtDmNhanvienKhoa other = (DtDmNhanvienKhoa) object;
        if ((this.dtdmnhanvienkhoaMaso == null && other.dtdmnhanvienkhoaMaso != null) || (this.dtdmnhanvienkhoaMaso != null && !this.dtdmnhanvienkhoaMaso.equals(other.dtdmnhanvienkhoaMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmNhanvienKhoa[dtdmnhanvienkhoaMaso=" + dtdmnhanvienkhoaMaso + "]";
    }
   


    public Double getDtdmnhanvienKhoaNgaygiocn() {
        return dtdmnhanvienKhoaNgaygiocn;
    }

    public void setDtdmnhanvienKhoaNgaygiocn(Double dtdmnhanvienKhoaNgaygiocn) {
        this.dtdmnhanvienKhoaNgaygiocn = dtdmnhanvienKhoaNgaygiocn;
    }

    public Boolean getDtdmnhanvienKhoaChon() {
        return dtdmnhanvienKhoaChon;
    }

    public void setDtdmnhanvienKhoaChon(Boolean dtdmnhanvienKhoaChon) {
        this.dtdmnhanvienKhoaChon = dtdmnhanvienKhoaChon;
    }
}


