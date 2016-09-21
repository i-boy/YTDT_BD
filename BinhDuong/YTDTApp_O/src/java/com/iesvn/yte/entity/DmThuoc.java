/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DM_THUOC")
@NamedQueries({})
public class DmThuoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DM_THUOC")
    @SequenceGenerator(name = "DM_THUOC", sequenceName = "DM_THUOC_DMTHUOC_MASO_SEQ", allocationSize = 1)
    @Column(name = "DMTHUOC_MASO", nullable = false)
    private Integer dmthuocMaso;
    @Column(name = "DMTHUOC_MA", nullable = false)
    private String dmthuocMa;
    @Column(name = "DMTHUOC_TENKH")
    private String dmthuocTenkh;
    @Column(name = "DMTHUOC_MAPHU")
    private String dmthuocMaphu;
    @Column(name = "DMTHUOC_SETS")
    private String dmthuocSets;
    @Column(name = "DMTHUOC_TEN", nullable = false)
    private String dmthuocTen;
    @Column(name = "DMTHUOC_HAMLUONG")
    private String dmthuocHamluong;
    @Column(name = "DMTHUOC_DUNGTICH")
    private Integer dmthuocDungtich;
    @Column(name = "DMTHUOC_CAPPHAT")
    private String dmthuocCapphat;
    @Column(name = "DMTHUOC_NCHU")
    private Boolean dmthuocNchu;
    @Column(name = "DMTHUOC_TONMAX")
    private Double dmthuocTonmax;
    @Column(name = "DMTHUOC_TONMIN")
    private Double dmthuocTonmin;
    @Column(name = "DMTHUOC_THUVO")
    private Boolean dmthuocThuvo;
    @Column(name = "DMTHUOC_YEUCAU")
    private Boolean dmthuocYeucau;
    @Column(name = "DMTHUOC_YCU")
    private Boolean dmthuocYcu;
    @Column(name = "DMTHUOC_HOICHAN")
    private Boolean dmthuocHoichan;
    @Column(name = "DMTHUOC_TRUONGKHOA")
    private Boolean dmthuocTruongkhoa;
    @Column(name = "DMTHUOC_NUOC")
    private String dmthuocNuoc;
    @Column(name = "DMTHUOC_MIEN")
    private Boolean dmthuocMien;
    @Column(name = "DMTHUOC_DAM")
    private Boolean dmthuocDam;
    @Column(name = "DMTHUOC_CORTI")
    private Boolean dmthuocCorti;
    @Column(name = "DMTHUOC_KHONGTHU")
    private Boolean dmthuocKhongthu;
    @Column(name = "DMTHUOC_TYLEBH")
    private Short dmthuocTylebh;
    @Column(name = "DMTHUOC_DUTRU")
    private Boolean dmthuocDutru;
    @Column(name = "DMTHUOC_DONGGOI")
    private Integer dmthuocDonggoi;
    @Column(name = "DMTHUOC_PHATBN")
    private Boolean dmthuocPhatbn;
    @Column(name = "DMTHUOC_DONGIABH")
    private Double dmthuocDongiabh;
    @Column(name = "DMTHUOC_PHANKHOC")
    private String dmthuocPhankhoc;
    @Column(name = "DMTHUOC_PHANKHOL")
    private String dmthuocPhankhol;
    @Column(name = "DMTHUOC_PHANKHOB")
    private String dmthuocPhankhob;
    @Column(name = "DMTHUOC_THUTU")
    private String dmthuocThutu;
    @Column(name = "DMTHUOC_LIEU")
    private Double dmthuocLieu;
    @Column(name = "DMTHUOC_DACBIET")
    private String dmthuocDacbiet;
    @Column(name = "DMTHUOC_MAHANG2")
    private String dmthuocMahang2;
    @Column(name = "DMTHUOC_KETOA")
    private Boolean dmthuocKetoa;
    @Column(name = "DMTHUOC_LINHGOP")
    private Boolean dmthuocLinhgop;
    @Column(name = "DMTHUOC_INDANHMUC")
    private Boolean dmthuocIndanhmuc;
    @Column(name = "DMTHUOC_INPLINH")
    private Boolean dmthuocInplinh;
    @Column(name = "DMTHUOC_INYLENH")
    private Boolean dmthuocInylenh;
    @Column(name = "DMTHUOC_GCHU")
    private String dmthuocGchu;
    @Column(name = "DMTHUOC_NGOAITRU")
    private Boolean dmthuocNgoaitru;
    @Column(name = "DMTHUOC_NOITRU")
    private Boolean dmthuocNoitru;
    @Column(name = "DMTHUOC_TREEM")
    private Boolean dmthuocTreem;
    @Column(name = "DMTHUOC_NGAYBOSUNG")
    @Temporal(TemporalType.DATE)
    private Date dmthuocNgaybosung;
    @Column(name = "DMTHUOC_NGAYGIOCN")
    private Double dmthuocNgaygiocn;
    @Column(name = "DMTHUOC_DT")
    private Boolean dmthuocDt;
    @Column(name = "DMTHUOC_DP")
    private Boolean dmthuocDp;
    @Column(name = "DMTHUOC_QL")
    private Boolean dmthuocQl;
    @Column(name = "DTDMNHANVIEN_CN")
    private String dtdmnhanvienCn;

    @Column(name = "DMTHUOC_DONGGIA_DAUTHAU")
    private Double dmthuocDonGiaDauThau;
    
    @Column(name = "DMTHUOC_SODK")
    private String dmthuocSoDangKy;

    @Column(name = "DMTHUOC_KHONGXUAT")
    private Boolean dmthuocKhongXuat;
    @Column(name = "DMTHUOC_PLBHYT")
    private Short dmthuocPlbhyt;
//    @OneToMany(mappedBy = "thuocnoitruMathuoc")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection;
//    @OneToMany(mappedBy = "thuocnoitruMathuoc1")
//    private Collection<ThuocNoiTru> thuocNoiTruCollection1;
//    @OneToMany(mappedBy = "dmthuocMaso")
//    private Collection<CtTraKho> ctTraKhoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmthuocMaso")
//    private Collection<DmThuocHoatChat> dmThuocHoatChatCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmthuocMaso")
//    private Collection<CtXuatKho> ctXuatKhoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmthuocMaso")
//    private Collection<CtNhapKho> ctNhapKhoCollection;
    @JoinColumn(name = "DMBAOQUAN_MASO", referencedColumnName = "DMBAOQUANTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBaoQuanThuoc dmbaoquanMaso;
    
    @JoinColumn(name = "DMPHANNHOMTHUOC_MASO", referencedColumnName = "DMPHANNHOMTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmPhanNhomThuoc dmphannhomthuocMaso;
    
    
    @JoinColumn(name = "DMPHANNHOMTHUOC_MASO2", referencedColumnName = "DMPHANNHOMTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmPhanNhomThuoc dmphannhomthuocMaso2;
    
    
    @JoinColumn(name = "DMPHANLOAITHUOC_MASO", referencedColumnName = "DMPHANLOAITHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmPhanLoaiThuoc dmphanloaithuocMaso;

        @JoinColumn(name = "DMPHANLOAITHUOC_MASO2", referencedColumnName = "DMPHANLOAITHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmPhanLoaiThuoc dmphanloaithuocMaso2;
    
    @JoinColumn(name = "DMCACHDUNG_MASO", referencedColumnName = "DMCACHDUNGTHUOC_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmCachDungThuoc dmcachdungMaso;
    @JoinColumn(name = "DMNHASANXUAT_MASO", referencedColumnName = "DMNHASANXUAT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmNhaSanXuat dmnhasanxuatMaso;
    @JoinColumn(name = "DMTHUOC_NUOCDEFA", referencedColumnName = "DMQUOCGIA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmQuocGia dmthuocNuocdefa;
    
    @JoinColumn(name = "DMDONVITINH_MASO", referencedColumnName = "DMDONVITINH_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmDonViTinh dmdonvitinhMaso;
    
     @JoinColumn(name = "DMTHUOC_HOATCHAT", referencedColumnName = "DMHOATCHAT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmHoatChat dmhoatchatMaso;

//    @OneToMany(mappedBy = "dmthuocMaso")
//    private Collection<CtPhieuDt> ctPhieuDtCollection;
//    @OneToMany(mappedBy = "cttoalinhkMathuoc")
//    private Collection<CtToaLinhk> ctToaLinhkCollection;
//    @OneToMany(mappedBy = "cttoalinhkMathuoc1")
//    private Collection<CtToaLinhk> ctToaLinhkCollection1;
//    @OneToMany(mappedBy = "dmthuocMaso")
//    private Collection<CtXuatBh> ctXuatBhCollection;
//    @OneToMany(mappedBy = "thuocphongkhamMathuoc")
//    private Collection<ThuocPhongKham> thuocPhongKhamCollection;
//    @OneToMany(mappedBy = "thuocphongkhamMathuoc1")
//    private Collection<ThuocPhongKham> thuocPhongKhamCollection1;
//    @OneToMany(mappedBy = "madungcu1")
//    private Collection<KetQuaMo> ketQuaMoCollection;
//    @OneToMany(mappedBy = "madungcu2")
//    private Collection<KetQuaMo> ketQuaMoCollection1;
//    @OneToMany(mappedBy = "madungcu11")
//    private Collection<KetQuaMo> ketQuaMoCollection2;
//    @OneToMany(mappedBy = "madungcu21")
//    private Collection<KetQuaMo> ketQuaMoCollection3;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dmthuocMaso")
//    private Collection<TonKho> tonKhoCollection;
//    @OneToMany(mappedBy = "cttoathuockMathuoc")
//    private Collection<CtToaThuock> ctToaThuockCollection;
//    @OneToMany(mappedBy = "cttoathuockMathuoc1")
//    private Collection<CtToaThuock> ctToaThuockCollection1;

    public DmThuoc() {
    }

    public DmThuoc(Integer dmthuocMaso) {
        this.dmthuocMaso = dmthuocMaso;
    }

    public DmThuoc(Integer dmthuocMaso, String dmthuocMa, String dmthuocTen, int dmthuocDungtich) {
        this.dmthuocMaso = dmthuocMaso;
        this.dmthuocMa = dmthuocMa;
        this.dmthuocTen = dmthuocTen;
        this.dmthuocDungtich = dmthuocDungtich;
    }

    public Integer getDmthuocMaso() {
        return dmthuocMaso;
    }

    public void setDmthuocMaso(Integer dmthuocMaso) {
        this.dmthuocMaso = dmthuocMaso;
    }

    public String getDmthuocMa() {
        return dmthuocMa;
    }

    public void setDmthuocMa(String dmthuocMa) {
        this.dmthuocMa = dmthuocMa;
    }

    public String getDmthuocTenkh() {
        return dmthuocTenkh;
    }

    public void setDmthuocTenkh(String dmthuocTenkh) {
        this.dmthuocTenkh = dmthuocTenkh;
    }

    public String getDmthuocMaphu() {
        return dmthuocMaphu;
    }

    public void setDmthuocMaphu(String dmthuocMaphu) {
        this.dmthuocMaphu = dmthuocMaphu;
    }

    public String getDmthuocSets() {
        return dmthuocSets;
    }

    public void setDmthuocSets(String dmthuocSets) {
        this.dmthuocSets = dmthuocSets;
    }

    public String getDmthuocTen() {
        return dmthuocTen;
    }

    public void setDmthuocTen(String dmthuocTen) {
        this.dmthuocTen = dmthuocTen;
    }

    public String getDmthuocHamluong() {
        return dmthuocHamluong;
    }

    public void setDmthuocHamluong(String dmthuocHamluong) {
        this.dmthuocHamluong = dmthuocHamluong;
    }

    public Integer getDmthuocDungtich() {
        return dmthuocDungtich;
    }

    public void setDmthuocDungtich(Integer dmthuocDungtich) {
        this.dmthuocDungtich = dmthuocDungtich;
    }

    public String getDmthuocCapphat() {
        return dmthuocCapphat;
    }

    public void setDmthuocCapphat(String dmthuocCapphat) {
        this.dmthuocCapphat = dmthuocCapphat;
    }

    public Boolean getDmthuocNchu() {
        return dmthuocNchu;
    }

    public void setDmthuocNchu(Boolean dmthuocNchu) {
        this.dmthuocNchu = dmthuocNchu;
    }

    public Double getDmthuocTonmax() {
        return dmthuocTonmax;
    }

    public void setDmthuocTonmax(Double dmthuocTonmax) {
        this.dmthuocTonmax = dmthuocTonmax;
    }

    public Double getDmthuocTonmin() {
        return dmthuocTonmin;
    }

    public void setDmthuocTonmin(Double dmthuocTonmin) {
        this.dmthuocTonmin = dmthuocTonmin;
    }

    public Boolean getDmthuocThuvo() {
        return dmthuocThuvo;
    }

    public void setDmthuocThuvo(Boolean dmthuocThuvo) {
        this.dmthuocThuvo = dmthuocThuvo;
    }

    public Boolean getDmthuocYeucau() {
        return dmthuocYeucau;
    }

    public void setDmthuocYeucau(Boolean dmthuocYeucau) {
        this.dmthuocYeucau = dmthuocYeucau;
    }

    public Boolean getDmthuocYcu() {
        return dmthuocYcu;
    }

    public void setDmthuocYcu(Boolean dmthuocYcu) {
        this.dmthuocYcu = dmthuocYcu;
    }

    public Boolean getDmthuocHoichan() {
        return dmthuocHoichan;
    }

    public void setDmthuocHoichan(Boolean dmthuocHoichan) {
        this.dmthuocHoichan = dmthuocHoichan;
    }

    public Boolean getDmthuocTruongkhoa() {
        return dmthuocTruongkhoa;
    }

    public void setDmthuocTruongkhoa(Boolean dmthuocTruongkhoa) {
        this.dmthuocTruongkhoa = dmthuocTruongkhoa;
    }

    public String getDmthuocNuoc() {
        return dmthuocNuoc;
    }

    public void setDmthuocNuoc(String dmthuocNuoc) {
        this.dmthuocNuoc = dmthuocNuoc;
    }

    public Boolean getDmthuocMien() {
        return dmthuocMien;
    }

    public void setDmthuocMien(Boolean dmthuocMien) {
        this.dmthuocMien = dmthuocMien;
    }

    public Boolean getDmthuocDam() {
        return dmthuocDam;
    }

    public void setDmthuocDam(Boolean dmthuocDam) {
        this.dmthuocDam = dmthuocDam;
    }

    public Boolean getDmthuocCorti() {
        return dmthuocCorti;
    }

    public void setDmthuocCorti(Boolean dmthuocCorti) {
        this.dmthuocCorti = dmthuocCorti;
    }

    public Boolean getDmthuocKhongthu() {
        return dmthuocKhongthu;
    }

    public void setDmthuocKhongthu(Boolean dmthuocKhongthu) {
        this.dmthuocKhongthu = dmthuocKhongthu;
    }

    public Short getDmthuocTylebh() {
        return dmthuocTylebh;
    }

    public void setDmthuocTylebh(Short dmthuocTylebh) {
        this.dmthuocTylebh = dmthuocTylebh;
    }

    public Boolean getDmthuocDutru() {
        return dmthuocDutru;
    }

    public void setDmthuocDutru(Boolean dmthuocDutru) {
        this.dmthuocDutru = dmthuocDutru;
    }

    public Integer getDmthuocDonggoi() {
        return dmthuocDonggoi;
    }

    public void setDmthuocDonggoi(Integer dmthuocDonggoi) {
        this.dmthuocDonggoi = dmthuocDonggoi;
    }

    public Boolean getDmthuocPhatbn() {
        return dmthuocPhatbn;
    }

    public void setDmthuocPhatbn(Boolean dmthuocPhatbn) {
        this.dmthuocPhatbn = dmthuocPhatbn;
    }

    public Double getDmthuocDongiabh() {
        return dmthuocDongiabh;
    }

    public void setDmthuocDongiabh(Double dmthuocDongiabh) {
        this.dmthuocDongiabh = dmthuocDongiabh;
    }

    public String getDmthuocPhankhoc() {
        return dmthuocPhankhoc;
    }

    public void setDmthuocPhankhoc(String dmthuocPhankhoc) {
        this.dmthuocPhankhoc = dmthuocPhankhoc;
    }

    public String getDmthuocPhankhol() {
        return dmthuocPhankhol;
    }

    public void setDmthuocPhankhol(String dmthuocPhankhol) {
        this.dmthuocPhankhol = dmthuocPhankhol;
    }

    public String getDmthuocPhankhob() {
        return dmthuocPhankhob;
    }

    public void setDmthuocPhankhob(String dmthuocPhankhob) {
        this.dmthuocPhankhob = dmthuocPhankhob;
    }

    public String getDmthuocThutu() {
        return dmthuocThutu;
    }

    public void setDmthuocThutu(String dmthuocThutu) {
        this.dmthuocThutu = dmthuocThutu;
    }

    public Double getDmthuocLieu() {
        return dmthuocLieu;
    }

    public void setDmthuocLieu(Double dmthuocLieu) {
        this.dmthuocLieu = dmthuocLieu;
    }

    public String getDmthuocDacbiet() {
        return dmthuocDacbiet;
    }

    public void setDmthuocDacbiet(String dmthuocDacbiet) {
        this.dmthuocDacbiet = dmthuocDacbiet;
    }

    public String getDmthuocMahang2() {
        return dmthuocMahang2;
    }

    public void setDmthuocMahang2(String dmthuocMahang2) {
        this.dmthuocMahang2 = dmthuocMahang2;
    }

    public Boolean getDmthuocKetoa() {
        return dmthuocKetoa;
    }

    public void setDmthuocKetoa(Boolean dmthuocKetoa) {
        this.dmthuocKetoa = dmthuocKetoa;
    }

    public Boolean getDmthuocLinhgop() {
        return dmthuocLinhgop;
    }

    public void setDmthuocLinhgop(Boolean dmthuocLinhgop) {
        this.dmthuocLinhgop = dmthuocLinhgop;
    }

    public Boolean getDmthuocIndanhmuc() {
        return dmthuocIndanhmuc;
    }

    public void setDmthuocIndanhmuc(Boolean dmthuocIndanhmuc) {
        this.dmthuocIndanhmuc = dmthuocIndanhmuc;
    }

    public Boolean getDmthuocInplinh() {
        return dmthuocInplinh;
    }

    public void setDmthuocInplinh(Boolean dmthuocInplinh) {
        this.dmthuocInplinh = dmthuocInplinh;
    }

    public Boolean getDmthuocInylenh() {
        return dmthuocInylenh;
    }

    public void setDmthuocInylenh(Boolean dmthuocInylenh) {
        this.dmthuocInylenh = dmthuocInylenh;
    }

    public String getDmthuocGchu() {
        return dmthuocGchu;
    }

    public void setDmthuocGchu(String dmthuocGchu) {
        this.dmthuocGchu = dmthuocGchu;
    }

    public Boolean getDmthuocNgoaitru() {
        return dmthuocNgoaitru;
    }

    public void setDmthuocNgoaitru(Boolean dmthuocNgoaitru) {
        this.dmthuocNgoaitru = dmthuocNgoaitru;
    }

    public Boolean getDmthuocNoitru() {
        return dmthuocNoitru;
    }

    public void setDmthuocNoitru(Boolean dmthuocNoitru) {
        this.dmthuocNoitru = dmthuocNoitru;
    }

    public Boolean getDmthuocTreem() {
        return dmthuocTreem;
    }

    public void setDmthuocTreem(Boolean dmthuocTreem) {
        this.dmthuocTreem = dmthuocTreem;
    }

    public Date getDmthuocNgaybosung() {
        return dmthuocNgaybosung;
    }

    public void setDmthuocNgaybosung(Date dmthuocNgaybosung) {
        this.dmthuocNgaybosung = dmthuocNgaybosung;
    }

    public Double getDmthuocNgaygiocn() {
        return dmthuocNgaygiocn;
    }

    public void setDmthuocNgaygiocn(Double dmthuocNgaygiocn) {
        this.dmthuocNgaygiocn = dmthuocNgaygiocn;
    }

    public Boolean getDmthuocDt() {
        return dmthuocDt;
    }

    public void setDmthuocDt(Boolean dmthuocDt) {
        this.dmthuocDt = dmthuocDt;
    }

    public Boolean getDmthuocDp() {
        return dmthuocDp;
    }

    public void setDmthuocDp(Boolean dmthuocDp) {
        this.dmthuocDp = dmthuocDp;
    }

    public Boolean getDmthuocQl() {
        return dmthuocQl;
    }

    public void setDmthuocQl(Boolean dmthuocQl) {
        this.dmthuocQl = dmthuocQl;
    }

    public String getDtdmnhanvienCn() {
        return dtdmnhanvienCn;
    }

    public void setDtdmnhanvienCn(String dtdmnhanvienCn) {
        this.dtdmnhanvienCn = dtdmnhanvienCn;
    }

//    public Collection<ThuocNoiTru> getThuocNoiTruCollection() {
//        return thuocNoiTruCollection;
//    }
//
//    public void setThuocNoiTruCollection(Collection<ThuocNoiTru> thuocNoiTruCollection) {
//        this.thuocNoiTruCollection = thuocNoiTruCollection;
//    }

//    public Collection<ThuocNoiTru> getThuocNoiTruCollection1() {
//        return thuocNoiTruCollection1;
//    }
//
//    public void setThuocNoiTruCollection1(Collection<ThuocNoiTru> thuocNoiTruCollection1) {
//        this.thuocNoiTruCollection1 = thuocNoiTruCollection1;
//    }

//    public Collection<CtTraKho> getCtTraKhoCollection() {
//        return ctTraKhoCollection;
//    }
//
//    public void setCtTraKhoCollection(Collection<CtTraKho> ctTraKhoCollection) {
//        this.ctTraKhoCollection = ctTraKhoCollection;
//    }

//    public Collection<DmThuocHoatChat> getDmThuocHoatChatCollection() {
//        return dmThuocHoatChatCollection;
//    }
//
//    public void setDmThuocHoatChatCollection(Collection<DmThuocHoatChat> dmThuocHoatChatCollection) {
//        this.dmThuocHoatChatCollection = dmThuocHoatChatCollection;
//    }

//    public Collection<CtXuatKho> getCtXuatKhoCollection() {
//        return ctXuatKhoCollection;
//    }
//
//    public void setCtXuatKhoCollection(Collection<CtXuatKho> ctXuatKhoCollection) {
//        this.ctXuatKhoCollection = ctXuatKhoCollection;
//    }

//    public Collection<CtNhapKho> getCtNhapKhoCollection() {
//        return ctNhapKhoCollection;
//    }
//
//    public void setCtNhapKhoCollection(Collection<CtNhapKho> ctNhapKhoCollection) {
//        this.ctNhapKhoCollection = ctNhapKhoCollection;
//    }

    public DmBaoQuanThuoc getDmbaoquanMaso(boolean create) {
if(create && dmbaoquanMaso == null) dmbaoquanMaso = new DmBaoQuanThuoc();
return dmbaoquanMaso;
}
    public DmBaoQuanThuoc getDmbaoquanMaso() {
        return dmbaoquanMaso;
    }

    public void setDmbaoquanMaso(DmBaoQuanThuoc dmbaoquanMaso) {
        this.dmbaoquanMaso = dmbaoquanMaso;
    }

    public DmPhanNhomThuoc getDmphannhomthuocMaso2(boolean create) {
if(create && dmphannhomthuocMaso2 == null) dmphannhomthuocMaso2 = new DmPhanNhomThuoc();
return dmphannhomthuocMaso2;
}
    public DmPhanNhomThuoc getDmphannhomthuocMaso2() {
        return dmphannhomthuocMaso2;
    }

    public void setDmphannhomthuocMaso2(DmPhanNhomThuoc dmphannhomthuocMaso2) {
        this.dmphannhomthuocMaso2 = dmphannhomthuocMaso2;
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

    

    public DmCachDungThuoc getDmcachdungMaso(boolean create) {
if(create && dmcachdungMaso == null) dmcachdungMaso = new DmCachDungThuoc();
return dmcachdungMaso;
}
    public DmCachDungThuoc getDmcachdungMaso() {
        return dmcachdungMaso;
    }

    public void setDmcachdungMaso(DmCachDungThuoc dmcachdungMaso) {
        this.dmcachdungMaso = dmcachdungMaso;
    }

    public DmNhaSanXuat getDmnhasanxuatMaso(boolean create) {
if(create && dmnhasanxuatMaso == null) dmnhasanxuatMaso = new DmNhaSanXuat();
return dmnhasanxuatMaso;
}
    public DmNhaSanXuat getDmnhasanxuatMaso() {
        return dmnhasanxuatMaso;
    }

    public void setDmnhasanxuatMaso(DmNhaSanXuat dmnhasanxuatMaso) {
        this.dmnhasanxuatMaso = dmnhasanxuatMaso;
    }

    public DmQuocGia getDmthuocNuocdefa(boolean create) {
if(create && dmthuocNuocdefa == null) dmthuocNuocdefa = new DmQuocGia();
return dmthuocNuocdefa;
}
    public DmQuocGia getDmthuocNuocdefa() {
        return dmthuocNuocdefa;
    }

    public void setDmthuocNuocdefa(DmQuocGia dmthuocNuocdefa) {
        this.dmthuocNuocdefa = dmthuocNuocdefa;
    }

    public DmPhanNhomThuoc getDmphannhomthuocMaso(boolean create) {
if(create && dmphannhomthuocMaso == null) dmphannhomthuocMaso = new DmPhanNhomThuoc();
return dmphannhomthuocMaso;
}
    public DmPhanNhomThuoc getDmphannhomthuocMaso() {
        return dmphannhomthuocMaso;
    }

    public void setDmphannhomthuocMaso(DmPhanNhomThuoc dmphannhomthuocMaso) {
        this.dmphannhomthuocMaso = dmphannhomthuocMaso;
    }

    public DmDonViTinh getDmdonvitinhMaso(boolean create) {
if(create && dmdonvitinhMaso == null) dmdonvitinhMaso = new DmDonViTinh();
return dmdonvitinhMaso;
}
    public DmDonViTinh getDmdonvitinhMaso() {
        return dmdonvitinhMaso;
    }

    public void setDmdonvitinhMaso(DmDonViTinh dmdonvitinhMaso) {
        this.dmdonvitinhMaso = dmdonvitinhMaso;
    }

   


//    public Collection<CtPhieuDt> getCtPhieuDtCollection() {
//        return ctPhieuDtCollection;
//    }
//
//    public void setCtPhieuDtCollection(Collection<CtPhieuDt> ctPhieuDtCollection) {
//        this.ctPhieuDtCollection = ctPhieuDtCollection;
//    }

//    public Collection<CtToaLinhk> getCtToaLinhkCollection() {
//        return ctToaLinhkCollection;
//    }
//
//    public void setCtToaLinhkCollection(Collection<CtToaLinhk> ctToaLinhkCollection) {
//        this.ctToaLinhkCollection = ctToaLinhkCollection;
//    }

//    public Collection<CtToaLinhk> getCtToaLinhkCollection1() {
//        return ctToaLinhkCollection1;
//    }
//
//    public void setCtToaLinhkCollection1(Collection<CtToaLinhk> ctToaLinhkCollection1) {
//        this.ctToaLinhkCollection1 = ctToaLinhkCollection1;
//    }

//    public Collection<CtXuatBh> getCtXuatBhCollection() {
//        return ctXuatBhCollection;
//    }
//
//    public void setCtXuatBhCollection(Collection<CtXuatBh> ctXuatBhCollection) {
//        this.ctXuatBhCollection = ctXuatBhCollection;
//    }

//    public Collection<ThuocPhongKham> getThuocPhongKhamCollection() {
//        return thuocPhongKhamCollection;
//    }
//
//    public void setThuocPhongKhamCollection(Collection<ThuocPhongKham> thuocPhongKhamCollection) {
//        this.thuocPhongKhamCollection = thuocPhongKhamCollection;
//    }

//    public Collection<ThuocPhongKham> getThuocPhongKhamCollection1() {
//        return thuocPhongKhamCollection1;
//    }
//
//    public void setThuocPhongKhamCollection1(Collection<ThuocPhongKham> thuocPhongKhamCollection1) {
//        this.thuocPhongKhamCollection1 = thuocPhongKhamCollection1;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection() {
//        return ketQuaMoCollection;
//    }
//
//    public void setKetQuaMoCollection(Collection<KetQuaMo> ketQuaMoCollection) {
//        this.ketQuaMoCollection = ketQuaMoCollection;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection1() {
//        return ketQuaMoCollection1;
//    }
//
//    public void setKetQuaMoCollection1(Collection<KetQuaMo> ketQuaMoCollection1) {
//        this.ketQuaMoCollection1 = ketQuaMoCollection1;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection2() {
//        return ketQuaMoCollection2;
//    }
//
//    public void setKetQuaMoCollection2(Collection<KetQuaMo> ketQuaMoCollection2) {
//        this.ketQuaMoCollection2 = ketQuaMoCollection2;
//    }

//    public Collection<KetQuaMo> getKetQuaMoCollection3() {
//        return ketQuaMoCollection3;
//    }
//
//    public void setKetQuaMoCollection3(Collection<KetQuaMo> ketQuaMoCollection3) {
//        this.ketQuaMoCollection3 = ketQuaMoCollection3;
//    }

//    public Collection<TonKho> getTonKhoCollection() {
//        return tonKhoCollection;
//    }
//
//    public void setTonKhoCollection(Collection<TonKho> tonKhoCollection) {
//        this.tonKhoCollection = tonKhoCollection;
//    }

//    public Collection<CtToaThuock> getCtToaThuockCollection() {
//        return ctToaThuockCollection;
//    }
//
//    public void setCtToaThuockCollection(Collection<CtToaThuock> ctToaThuockCollection) {
//        this.ctToaThuockCollection = ctToaThuockCollection;
//    }

//    public Collection<CtToaThuock> getCtToaThuockCollection1() {
//        return ctToaThuockCollection1;
//    }
//
//    public void setCtToaThuockCollection1(Collection<CtToaThuock> ctToaThuockCollection1) {
//        this.ctToaThuockCollection1 = ctToaThuockCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dmthuocMaso != null ? dmthuocMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DmThuoc)) {
            return false;
        }
        DmThuoc other = (DmThuoc) object;
        if ((this.dmthuocMaso == null && other.dmthuocMaso != null) || (this.dmthuocMaso != null && !this.dmthuocMaso.equals(other.dmthuocMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DmThuoc[dmthuocMaso=" + dmthuocMaso + "]";
    }

    public void setDmhoatchatMaso(DmHoatChat dmhoatchatMaso) {
        this.dmhoatchatMaso = dmhoatchatMaso;
    }

    public DmPhanLoaiThuoc getDmphanloaithuocMaso2() {
        return dmphanloaithuocMaso2;
    }

    public void setDmphanloaithuocMaso2(DmPhanLoaiThuoc dmphanloaithuocMaso2) {
        this.dmphanloaithuocMaso2 = dmphanloaithuocMaso2;
    }

    /**
     * @return the dmthuocDonGiaDauThau
     */
    public Double getDmthuocDonGiaDauThau() {
        return dmthuocDonGiaDauThau;
    }

    /**
     * @param dmthuocDonGiaDauThau the dmthuocDonGiaDauThau to set
     */
    public void setDmthuocDonGiaDauThau(Double dmthuocDonGiaDauThau) {
        this.dmthuocDonGiaDauThau = dmthuocDonGiaDauThau;
    }

    /**
     * @return the dmthuocSoDangKy
     */
    public String getDmthuocSoDangKy() {
        return dmthuocSoDangKy;
    }

    /**
     * @param dmthuocSoDangKy the dmthuocSoDangKy to set
     */
    public void setDmthuocSoDangKy(String dmthuocSoDangKy) {
        this.dmthuocSoDangKy = dmthuocSoDangKy;
    }

    /**
     * @return the dmthuocKhongXuat
     */
    public Boolean getDmthuocKhongXuat() {
        return dmthuocKhongXuat;
    }

    /**
     * @param dmthuocKhongXuat the dmthuocKhongXuat to set
     */
    public void setDmthuocKhongXuat(Boolean dmthuocKhongXuat) {
        this.dmthuocKhongXuat = dmthuocKhongXuat;
    }
    
    public Short getDmthuocPlbhyt() {
        return dmthuocPlbhyt;
    }

    public void setDmthuocPlbhyt(Short dmthuocPlbhyt) {
        this.dmthuocPlbhyt = dmthuocPlbhyt;
    }
    
}


