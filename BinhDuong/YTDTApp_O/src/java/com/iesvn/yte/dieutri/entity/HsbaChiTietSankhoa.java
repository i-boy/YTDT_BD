/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author halylam
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "HSBA_CHI_TIET_SANKHOA")
@NamedQueries({
    @NamedQuery(name = "HsbaChiTietSankhoa.findAll", query = "SELECT h FROM HsbaChiTietSankhoa h"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaMa", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaMa = :hsbactsankhoaMa"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChuandoanngayde", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChuandoanngayde = :hsbactsankhoaChuandoanngayde"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChuandoanngoithai", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChuandoanngoithai = :hsbactsankhoaChuandoanngoithai"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChuandoancachthucde", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChuandoancachthucde = :hsbactsankhoaChuandoancachthucde"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChuandoankiemsoattucung", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChuandoankiemsoattucung = :hsbactsankhoaChuandoankiemsoattucung"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChuandoanphauthuatcapcuu", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChuandoanphauthuatcapcuu = :hsbactsankhoaChuandoanphauthuatcapcuu"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChuandoanphauthuatchudong", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChuandoanphauthuatchudong = :hsbactsankhoaChuandoanphauthuatchudong"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChuandoandonthai", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChuandoandonthai = :hsbactsankhoaChuandoandonthai"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChuandoandathai", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChuandoandathai = :hsbactsankhoaChuandoandathai"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChuandoantrai", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChuandoantrai = :hsbactsankhoaChuandoantrai"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChuandoangai", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChuandoangai = :hsbactsankhoaChuandoangai"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChuandoansong", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChuandoansong = :hsbactsankhoaChuandoansong"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChuandoanchet", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChuandoanchet = :hsbactsankhoaChuandoanchet"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChuandoanditat", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChuandoanditat = :hsbactsankhoaChuandoanditat"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChuandoancannang", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChuandoancannang = :hsbactsankhoaChuandoancannang"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaNgaythubenh", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaNgaythubenh = :hsbactsankhoaNgaythubenh"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaKinhcuoicungtungay", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaKinhcuoicungtungay = :hsbactsankhoaKinhcuoicungtungay"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaKinhcuoicungdenngay", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaKinhcuoicungdenngay = :hsbactsankhoaKinhcuoicungdenngay"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaLydovaov", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaLydovaov = :hsbactsankhoaLydovaov"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTuoithaituan", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTuoithaituan = :hsbactsankhoaTuoithaituan"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaKhamthaitai", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaKhamthaitai = :hsbactsankhoaKhamthaitai"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTiemphonguonvan", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTiemphonguonvan = :hsbactsankhoaTiemphonguonvan"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaSolantiemuonvan", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaSolantiemuonvan = :hsbactsankhoaSolantiemuonvan"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChuyendaluc", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChuyendaluc = :hsbactsankhoaChuyendaluc"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaDauhieulucdau", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaDauhieulucdau = :hsbactsankhoaDauhieulucdau"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChuyenbien", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChuyenbien = :hsbactsankhoaChuyenbien"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTiensubenhbt", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTiensubenhbt = :hsbactsankhoaTiensubenhbt"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTiensubenhgd", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTiensubenhgd = :hsbactsankhoaTiensubenhgd"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaThaykinhnam", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaThaykinhnam = :hsbactsankhoaThaykinhnam"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaThaykinhtuoi", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaThaykinhtuoi = :hsbactsankhoaThaykinhtuoi"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTinhchatkinhnguyet", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTinhchatkinhnguyet = :hsbactsankhoaTinhchatkinhnguyet"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChukykinh", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChukykinh = :hsbactsankhoaChukykinh"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaLuongkinh", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaLuongkinh = :hsbactsankhoaLuongkinh"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaBenhsankhoadadieutri", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaBenhsankhoadadieutri = :hsbactsankhoaBenhsankhoadadieutri"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTuanhoan", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTuanhoan = :hsbactsankhoaTuanhoan"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaHohap", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaHohap = :hsbactsankhoaHohap"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTieuhoa", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTieuhoa = :hsbactsankhoaTieuhoa"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaThantietnieusinhhoc", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaThantietnieusinhhoc = :hsbactsankhoaThantietnieusinhhoc"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTinhtrang", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTinhtrang = :hsbactsankhoaTinhtrang"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTinhtrangphu", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTinhtrangphu = :hsbactsankhoaTinhtrangphu"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaCoquankhac", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaCoquankhac = :hsbactsankhoaCoquankhac"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaBungcoseophauthuatcu", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaBungcoseophauthuatcu = :hsbactsankhoaBungcoseophauthuatcu"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChieucaotc", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChieucaotc = :hsbactsankhoaChieucaotc"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaVongbung", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaVongbung = :hsbactsankhoaVongbung"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaHinhdangtc", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaHinhdangtc = :hsbactsankhoaHinhdangtc"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTuthetc", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTuthetc = :hsbactsankhoaTuthetc"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaConcotc", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaConcotc = :hsbactsankhoaConcotc"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTimthai", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTimthai = :hsbactsankhoaTimthai"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaVu", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaVu = :hsbactsankhoaVu"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaDkngoaikhungchau", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaDkngoaikhungchau = :hsbactsankhoaDkngoaikhungchau"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaBishop", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaBishop = :hsbactsankhoaBishop"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaAmho", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaAmho = :hsbactsankhoaAmho"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaAmdao", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaAmdao = :hsbactsankhoaAmdao"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTangsinhmon", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTangsinhmon = :hsbactsankhoaTangsinhmon"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaCotucung", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaCotucung = :hsbactsankhoaCotucung"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaPhanphu", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaPhanphu = :hsbactsankhoaPhanphu"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaOiphong", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaOiphong = :hsbactsankhoaOiphong"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaOidet", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaOidet = :hsbactsankhoaOidet"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaOiquale", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaOiquale = :hsbactsankhoaOiquale"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaOivoluc", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaOivoluc = :hsbactsankhoaOivoluc"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaOivotunhien", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaOivotunhien = :hsbactsankhoaOivotunhien"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaOivobamoi", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaOivobamoi = :hsbactsankhoaOivobamoi"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaMausacnuocoi", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaMausacnuocoi = :hsbactsankhoaMausacnuocoi"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaNuocoinhieuhayit", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaNuocoinhieuhayit = :hsbactsankhoaNuocoinhieuhayit"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaNgoi", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaNgoi = :hsbactsankhoaNgoi"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaThe", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaThe = :hsbactsankhoaThe"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaKieuthe", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaKieuthe = :hsbactsankhoaKieuthe"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaDolotcao", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaDolotcao = :hsbactsankhoaDolotcao"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaDolotchuc", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaDolotchuc = :hsbactsankhoaDolotchuc"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaDolotchat", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaDolotchat = :hsbactsankhoaDolotchat"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaDolotlot", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaDolotlot = :hsbactsankhoaDolotlot"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaDknhohave", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaDknhohave = :hsbactsankhoaDknhohave"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaXetnghiemcanlam", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaXetnghiemcanlam = :hsbactsankhoaXetnghiemcanlam"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaHuongdieutri", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaHuongdieutri = :hsbactsankhoaHuongdieutri"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTienluong", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTienluong = :hsbactsankhoaTienluong"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaVaobuongdeluc", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaVaobuongdeluc = :hsbactsankhoaVaobuongdeluc"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTennguoitheodoi", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTennguoitheodoi = :hsbactsankhoaTennguoitheodoi"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChucdanhnguoitheodoi", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChucdanhnguoitheodoi = :hsbactsankhoaChucdanhnguoitheodoi"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaDeluc", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaDeluc = :hsbactsankhoaDeluc"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaApgar1phut", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaApgar1phut = :hsbactsankhoaApgar1phut"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaApgar5phut", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaApgar5phut = :hsbactsankhoaApgar5phut"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaApgar10phut", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaApgar10phut = :hsbactsankhoaApgar10phut"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaCannang", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaCannang = :hsbactsankhoaCannang"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaCao", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaCao = :hsbactsankhoaCao"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaVongdau", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaVongdau = :hsbactsankhoaVongdau"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaDonthaitrai", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaDonthaitrai = :hsbactsankhoaDonthaitrai"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaDonthaigai", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaDonthaigai = :hsbactsankhoaDonthaigai"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaDathaitrai", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaDathaitrai = :hsbactsankhoaDathaitrai"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaDathaigai", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaDathaigai = :hsbactsankhoaDathaigai"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTatbamsinh", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTatbamsinh = :hsbactsankhoaTatbamsinh"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaCohaumon", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaCohaumon = :hsbactsankhoaCohaumon"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaCuthetatbamsinh", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaCuthetatbamsinh = :hsbactsankhoaCuthetatbamsinh"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTtsssaude", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTtsssaude = :hsbactsankhoaTtsssaude"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaXulyketqua", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaXulyketqua = :hsbactsankhoaXulyketqua"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaRauboc", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaRauboc = :hsbactsankhoaRauboc"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaRauso", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaRauso = :hsbactsankhoaRauso"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaRausoluc", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaRausoluc = :hsbactsankhoaRausoluc"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaCachsorau", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaCachsorau = :hsbactsankhoaCachsorau"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaMatmang", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaMatmang = :hsbactsankhoaMatmang"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaMatmui", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaMatmui = :hsbactsankhoaMatmui"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaBanhrau", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaBanhrau = :hsbactsankhoaBanhrau"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaBanhraucannang", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaBanhraucannang = :hsbactsankhoaBanhraucannang"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaRaucuonco", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaRaucuonco = :hsbactsankhoaRaucuonco"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaCuongraudai", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaCuongraudai = :hsbactsankhoaCuongraudai"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaChaymausauso", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaChaymausauso = :hsbactsankhoaChaymausauso"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaLuongmaumat", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaLuongmaumat = :hsbactsankhoaLuongmaumat"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaKiemsoattucung", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaKiemsoattucung = :hsbactsankhoaKiemsoattucung"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaRauXulyketqua", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaRauXulyketqua = :hsbactsankhoaRauXulyketqua"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaDaniemmac", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaDaniemmac = :hsbactsankhoaDaniemmac"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaDethuong", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaDethuong = :hsbactsankhoaDethuong"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaForceps", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaForceps = :hsbactsankhoaForceps"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaGiachut", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaGiachut = :hsbactsankhoaGiachut"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaDephauthuat", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaDephauthuat = :hsbactsankhoaDephauthuat"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaDechihuy", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaDechihuy = :hsbactsankhoaDechihuy"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaDekhac", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaDekhac = :hsbactsankhoaDekhac"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaLydocanthiep", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaLydocanthiep = :hsbactsankhoaLydocanthiep"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTangsinhmonKhongrach", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTangsinhmonKhongrach = :hsbactsankhoaTangsinhmonKhongrach"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTangsinhmonRach", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTangsinhmonRach = :hsbactsankhoaTangsinhmonRach"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTangsinhmonCat", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTangsinhmonCat = :hsbactsankhoaTangsinhmonCat"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaPpkhauvaloaichi", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaPpkhauvaloaichi = :hsbactsankhoaPpkhauvaloaichi"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaCotucungKhongrach", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaCotucungKhongrach = :hsbactsankhoaCotucungKhongrach"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaCotucungRach", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaCotucungRach = :hsbactsankhoaCotucungRach"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaHuongdtCdtt", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaHuongdtCdtt = :hsbactsankhoaHuongdtCdtt"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaSotoxquang", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaSotoxquang = :hsbactsankhoaSotoxquang"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaSotoctscanner", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaSotoctscanner = :hsbactsankhoaSotoctscanner"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaSotosieuam", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaSotosieuam = :hsbactsankhoaSotosieuam"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaSotoxn", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaSotoxn = :hsbactsankhoaSotoxn"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaSotokhac", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaSotokhac = :hsbactsankhoaSotokhac"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaSotoloaikhac", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaSotoloaikhac = :hsbactsankhoaSotoloaikhac"),
    @NamedQuery(name = "HsbaChiTietSankhoa.findByHsbactsankhoaTongsoto", query = "SELECT h FROM HsbaChiTietSankhoa h WHERE h.hsbactsankhoaTongsoto = :hsbactsankhoaTongsoto")})
public class HsbaChiTietSankhoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HSBA_CHI_TIET_SANKHOA")
    @SequenceGenerator(name = "HSBA_CHI_TIET_SANKHOA", sequenceName = "HSBA_CHI_TIET_SANKHOA_HSBACTSA", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "HSBACTSANKHOA_MA")
    private Integer hsbactsankhoaMa;
    @Column(name = "HSBACTSANKHOA_CHUANDOANNGAYDE")
    @Temporal(TemporalType.DATE)
    private Date hsbactsankhoaChuandoanngayde;
    @Column(name = "HSBACTSANKHOA_CDOANNGOITHAI")
    private String hsbactsankhoaChuandoanngoithai;
    @Column(name = "HSBACTSANKHOA_CDOANCACHTHUCDE")
    private String hsbactsankhoaChuandoancachthucde;
    @Column(name = "HSBACTSANKHOA_CDOANKSOATTUCUNG")
    private String hsbactsankhoaChuandoankiemsoattucung;
    @Column(name = "HSBACTSANKHOA_CDOANPTCAPCUU")
    private Boolean hsbactsankhoaChuandoanphauthuatcapcuu;
    @Column(name = "HSBACTSANKHOA_CDOANPTCHUDONG")
    private Boolean hsbactsankhoaChuandoanphauthuatchudong;
    @Column(name = "HSBACTSANKHOA_CHUANDOANDONTHAI")
    private Boolean hsbactsankhoaChuandoandonthai;
    @Column(name = "HSBACTSANKHOA_CHUANDOANDATHAI")
    private Boolean hsbactsankhoaChuandoandathai;
    @Column(name = "HSBACTSANKHOA_CHUANDOANTRAI")
    private Boolean hsbactsankhoaChuandoantrai;
    @Column(name = "HSBACTSANKHOA_CHUANDOANGAI")
    private Boolean hsbactsankhoaChuandoangai;
    @Column(name = "HSBACTSANKHOA_CHUANDOANSONG")
    private Boolean hsbactsankhoaChuandoansong;
    @Column(name = "HSBACTSANKHOA_CHUANDOANCHET")
    private Boolean hsbactsankhoaChuandoanchet;
    @Column(name = "HSBACTSANKHOA_CHUANDOANDITAT")
    private String hsbactsankhoaChuandoanditat;
    @Column(name = "HSBACTSANKHOA_CHUANDOANCANNANG")
    private String hsbactsankhoaChuandoancannang;
    @Column(name = "HSBACTSANKHOA_NGAYTHUBENH")
    private String hsbactsankhoaNgaythubenh;
    @Column(name = "HSBACTSANKHOA_KINHCUOITUNGAY")
    @Temporal(TemporalType.DATE)
    private Date hsbactsankhoaKinhcuoicungtungay;
    @Column(name = "HSBACTSANKHOA_KINHCUOIDENNGAY")
    @Temporal(TemporalType.DATE)
    private Date hsbactsankhoaKinhcuoicungdenngay;
    @Column(name = "HSBACTSANKHOA_LYDOVAOV")
    private String hsbactsankhoaLydovaov;
    @Column(name = "HSBACTSANKHOA_TUOITHAITUAN")
    private String hsbactsankhoaTuoithaituan;
    @Column(name = "HSBACTSANKHOA_KHAMTHAITAI")
    private String hsbactsankhoaKhamthaitai;
    @Column(name = "HSBACTSANKHOA_TIEMPHONGUONVAN")
    private Boolean hsbactsankhoaTiemphonguonvan;
    @Column(name = "HSBACTSANKHOA_SOLANTIEMUONVAN")
    private String hsbactsankhoaSolantiemuonvan;
    @Column(name = "HSBACTSANKHOA_CHUYENDALUC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbactsankhoaChuyendaluc;
    @Column(name = "HSBACTSANKHOA_DAUHIEULUCDAU")
    private String hsbactsankhoaDauhieulucdau;
    @Column(name = "HSBACTSANKHOA_CHUYENBIEN")
    private String hsbactsankhoaChuyenbien;
    @Column(name = "HSBACTSANKHOA_TIENSUBENHBT")
    private String hsbactsankhoaTiensubenhbt;
    @Column(name = "HSBACTSANKHOA_TIENSUBENHGD")
    private String hsbactsankhoaTiensubenhgd;
    @Column(name = "HSBACTSANKHOA_THAYKINHNAM")
    private String hsbactsankhoaThaykinhnam;
    @Column(name = "HSBACTSANKHOA_THAYKINHTUOI")
    private String hsbactsankhoaThaykinhtuoi;
    @Column(name = "HSBACTSANKHOA_TCHATKINHNGUYET")
    private String hsbactsankhoaTinhchatkinhnguyet;
    @Column(name = "HSBACTSANKHOA_CHUKYKINH")
    private String hsbactsankhoaChukykinh;
    @Column(name = "HSBACTSANKHOA_LUONGKINH")
    private String hsbactsankhoaLuongkinh;
    @Column(name = "HSBACTSANKHOA_BPKHOADADIEUTRI")
    private String hsbactsankhoaBenhsankhoadadieutri;
    @Column(name = "HSBACTSANKHOA_LAYCHONGNAM")
    private String hsbactsankhoaLaychongnam;
    @Column(name = "HSBACTSANKHOA_LAYCHONGTUOI")
    private String hsbactsankhoaLaychongtuoi;
    @Column(name = "HSBACTSANKHOA_TUANHOAN")
    private String hsbactsankhoaTuanhoan;
    @Column(name = "HSBACTSANKHOA_HOHAP")
    private String hsbactsankhoaHohap;
    @Column(name = "HSBACTSANKHOA_TIEUHOA")
    private String hsbactsankhoaTieuhoa;
    @Column(name = "HSBACTSANKHOA_THANTIETNIEUSHOC")
    private String hsbactsankhoaThantietnieusinhhoc;
    @Column(name = "HSBACTSANKHOA_TINHTRANG")
    private String hsbactsankhoaTinhtrang;
    @Column(name = "HSBACTSANKHOA_TINHTRANGPHU")
    private Boolean hsbactsankhoaTinhtrangphu;
    @Column(name = "HSBACTSANKHOA_COQUANKHAC")
    private String hsbactsankhoaCoquankhac;
    @Column(name = "HSBACTSANKHOA_BUNGCOSEOPTCU")
    private Boolean hsbactsankhoaBungcoseophauthuatcu;
    @Column(name = "HSBACTSANKHOA_CHIEUCAOTC")
    private String hsbactsankhoaChieucaotc;
    @Column(name = "HSBACTSANKHOA_VONGBUNG")
    private String hsbactsankhoaVongbung;
    @Column(name = "HSBACTSANKHOA_HINHDANGTC")
    private String hsbactsankhoaHinhdangtc;
    @Column(name = "HSBACTSANKHOA_TUTHETC")
    private String hsbactsankhoaTuthetc;
    @Column(name = "HSBACTSANKHOA_CONCOTC")
    private String hsbactsankhoaConcotc;
    @Column(name = "HSBACTSANKHOA_TIMTHAI")
    private String hsbactsankhoaTimthai;
    @Column(name = "HSBACTSANKHOA_VU")
    private String hsbactsankhoaVu;
    @Column(name = "HSBACTSANKHOA_DKNGOAIKHUNGCHAU")
    private String hsbactsankhoaDkngoaikhungchau;
    @Column(name = "HSBACTSANKHOA_BISHOP")
    private String hsbactsankhoaBishop;
    @Column(name = "HSBACTSANKHOA_AMHO")
    private String hsbactsankhoaAmho;
    @Column(name = "HSBACTSANKHOA_AMDAO")
    private String hsbactsankhoaAmdao;
    @Column(name = "HSBACTSANKHOA_TANGSINHMON")
    private String hsbactsankhoaTangsinhmon;
    @Column(name = "HSBACTSANKHOA_COTUCUNG")
    private String hsbactsankhoaCotucung;
    @Column(name = "HSBACTSANKHOA_PHANPHU")
    private String hsbactsankhoaPhanphu;
    @Column(name = "HSBACTSANKHOA_OIPHONG")
    private Boolean hsbactsankhoaOiphong;
    @Column(name = "HSBACTSANKHOA_OIDET")
    private Boolean hsbactsankhoaOidet;
    @Column(name = "HSBACTSANKHOA_OIQUALE")
    private Boolean hsbactsankhoaOiquale;
    @Column(name = "HSBACTSANKHOA_OIVOLUC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbactsankhoaOivoluc;
    @Column(name = "HSBACTSANKHOA_OIVOTUNHIEN")
    private Boolean hsbactsankhoaOivotunhien;
    @Column(name = "HSBACTSANKHOA_OIVOBAMOI")
    private Boolean hsbactsankhoaOivobamoi;
    @Column(name = "HSBACTSANKHOA_MAUSACNUOCOI")
    private String hsbactsankhoaMausacnuocoi;
    @Column(name = "HSBACTSANKHOA_NUOCOINHIEUHAYIT")
    private String hsbactsankhoaNuocoinhieuhayit;
    @Column(name = "HSBACTSANKHOA_NGOI")
    private String hsbactsankhoaNgoi;
    @Column(name = "HSBACTSANKHOA_THE")
    private String hsbactsankhoaThe;
    @Column(name = "HSBACTSANKHOA_KIEUTHE")
    private String hsbactsankhoaKieuthe;
    @Column(name = "HSBACTSANKHOA_DOLOTCAO")
    private Boolean hsbactsankhoaDolotcao;
    @Column(name = "HSBACTSANKHOA_DOLOTCHUC")
    private Boolean hsbactsankhoaDolotchuc;
    @Column(name = "HSBACTSANKHOA_DOLOTCHAT")
    private Boolean hsbactsankhoaDolotchat;
    @Column(name = "HSBACTSANKHOA_DOLOTLOT")
    private Boolean hsbactsankhoaDolotlot;
    @Column(name = "HSBACTSANKHOA_DKNHOHAVE")
    private String hsbactsankhoaDknhohave;
    @Column(name = "HSBACTSANKHOA_XETNGHIEMCANLAM")
    private String hsbactsankhoaXetnghiemcanlam;
    @Column(name = "HSBACTSANKHOA_HUONGDIEUTRI")
    private String hsbactsankhoaHuongdieutri;
    @Column(name = "HSBACTSANKHOA_TIENLUONG")
    private String hsbactsankhoaTienluong;
    @Column(name = "HSBACTSANKHOA_VAOBUONGDELUC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbactsankhoaVaobuongdeluc;
    @Column(name = "HSBACTSANKHOA_TENNGUOITHEODOI")
    private String hsbactsankhoaTennguoitheodoi;
    @Column(name = "HSBACTSANKHOA_CDNGUOITHEODOI")
    private String hsbactsankhoaChucdanhnguoitheodoi;
    @Column(name = "HSBACTSANKHOA_DELUC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbactsankhoaDeluc;
    @Column(name = "HSBACTSANKHOA_APGAR1PHUT")
    private String hsbactsankhoaApgar1phut;
    @Column(name = "HSBACTSANKHOA_APGAR5PHUT")
    private String hsbactsankhoaApgar5phut;
    @Column(name = "HSBACTSANKHOA_APGAR10PHUT")
    private String hsbactsankhoaApgar10phut;
    @Column(name = "HSBACTSANKHOA_CANNANG")
    private String hsbactsankhoaCannang;
    @Column(name = "HSBACTSANKHOA_CAO")
    private String hsbactsankhoaCao;
    @Column(name = "HSBACTSANKHOA_VONGDAU")
    private String hsbactsankhoaVongdau;
    @Column(name = "HSBACTSANKHOA_DONTHAITRAI")
    private Boolean hsbactsankhoaDonthaitrai;
    @Column(name = "HSBACTSANKHOA_DONTHAIGAI")
    private Boolean hsbactsankhoaDonthaigai;
    @Column(name = "HSBACTSANKHOA_DATHAITRAI")
    private Boolean hsbactsankhoaDathaitrai;
    @Column(name = "HSBACTSANKHOA_DATHAIGAI")
    private Boolean hsbactsankhoaDathaigai;
    @Column(name = "HSBACTSANKHOA_TATBAMSINH")
    private Boolean hsbactsankhoaTatbamsinh;
    @Column(name = "HSBACTSANKHOA_COHAUMON")
    private Boolean hsbactsankhoaCohaumon;
    @Column(name = "HSBACTSANKHOA_CUTHETATBAMSINH")
    private String hsbactsankhoaCuthetatbamsinh;
    @Column(name = "HSBACTSANKHOA_TTSSSAUDE")
    private String hsbactsankhoaTtsssaude;
    @Column(name = "HSBACTSANKHOA_XULYKETQUA")
    private String hsbactsankhoaXulyketqua;
    @Column(name = "HSBACTSANKHOA_RAUBOC")
    private Boolean hsbactsankhoaRauboc;
    @Column(name = "HSBACTSANKHOA_RAUSO")
    private Boolean hsbactsankhoaRauso;
    @Column(name = "HSBACTSANKHOA_RAUSOLUC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsbactsankhoaRausoluc;
    @Column(name = "HSBACTSANKHOA_CACHSORAU")
    private String hsbactsankhoaCachsorau;
    @Column(name = "HSBACTSANKHOA_MATMANG")
    private String hsbactsankhoaMatmang;
    @Column(name = "HSBACTSANKHOA_MATMUI")
    private String hsbactsankhoaMatmui;
    @Column(name = "HSBACTSANKHOA_BANHRAU")
    private String hsbactsankhoaBanhrau;
    @Column(name = "HSBACTSANKHOA_BANHRAUCANNANG")
    private String hsbactsankhoaBanhraucannang;
    @Column(name = "HSBACTSANKHOA_RAUCUONCO")
    private Boolean hsbactsankhoaRaucuonco;
    @Column(name = "HSBACTSANKHOA_CUONGRAUDAI")
    private String hsbactsankhoaCuongraudai;
    @Column(name = "HSBACTSANKHOA_CHAYMAUSAUSO")
    private Boolean hsbactsankhoaChaymausauso;
    @Column(name = "HSBACTSANKHOA_LUONGMAUMAT")
    private String hsbactsankhoaLuongmaumat;
    @Column(name = "HSBACTSANKHOA_KIEMSOATTUCUNG")
    private Boolean hsbactsankhoaKiemsoattucung;
    @Column(name = "HSBACTSANKHOA_RAU_XULYKETQUA")
    private String hsbactsankhoaRauXulyketqua;
    @Column(name = "HSBACTSANKHOA_DANIEMMAC")
    private String hsbactsankhoaDaniemmac;
    @Column(name = "HSBACTSANKHOA_DETHUONG")
    private Boolean hsbactsankhoaDethuong;
    @Column(name = "HSBACTSANKHOA_FORCEPS")
    private Boolean hsbactsankhoaForceps;
    @Column(name = "HSBACTSANKHOA_GIACHUT")
    private Boolean hsbactsankhoaGiachut;
    @Column(name = "HSBACTSANKHOA_DEPHAUTHUAT")
    private Boolean hsbactsankhoaDephauthuat;
    @Column(name = "HSBACTSANKHOA_DECHIHUY")
    private Boolean hsbactsankhoaDechihuy;
    @Column(name = "HSBACTSANKHOA_DEKHAC")
    private Boolean hsbactsankhoaDekhac;
    @Column(name = "HSBACTSANKHOA_LYDOCANTHIEP")
    private String hsbactsankhoaLydocanthiep;
    @Column(name = "HSBACTSANKHOA_TSMON_KHONGRACH")
    private Boolean hsbactsankhoaTangsinhmonKhongrach;
    @Column(name = "HSBACTSANKHOA_TANGSINHMON_RACH")
    private Boolean hsbactsankhoaTangsinhmonRach;
    @Column(name = "HSBACTSANKHOA_TANGSINHMON_CAT")
    private Boolean hsbactsankhoaTangsinhmonCat;
    @Column(name = "HSBACTSANKHOA_PPKHAUVALOAICHI")
    private String hsbactsankhoaPpkhauvaloaichi;
    @Column(name = "HSBACTSANKHOA_COTC_KHONGRACH")
    private Boolean hsbactsankhoaCotucungKhongrach;
    @Column(name = "HSBACTSANKHOA_COTUCUNG_RACH")
    private Boolean hsbactsankhoaCotucungRach;
    @Column(name = "HSBACTSANKHOA_HUONGDT_CDTT")
    private String hsbactsankhoaHuongdtCdtt;
    @Column(name = "HSBACTSANKHOA_SOMUIKHAU")
    private String hsbactsankhoaSomuikhau;
    @Column(name = "HSBACTSANKHOA_SOTOXQUANG")
    private Integer hsbactsankhoaSotoxquang;
    @Column(name = "HSBACTSANKHOA_SOTOCTSCANNER")
    private Integer hsbactsankhoaSotoctscanner;
    @Column(name = "HSBACTSANKHOA_SOTOSIEUAM")
    private Integer hsbactsankhoaSotosieuam;
    @Column(name = "HSBACTSANKHOA_SOTOXN")
    private Integer hsbactsankhoaSotoxn;
    @Column(name = "HSBACTSANKHOA_SOTOKHAC")
    private Integer hsbactsankhoaSotokhac;
    @Column(name = "HSBACTSANKHOA_SOTOLOAIKHAC")
    private String hsbactsankhoaSotoloaikhac;
    @Column(name = "HSBACTSANKHOA_TONGSOTO")
    private Integer hsbactsankhoaTongsoto;
    @JoinColumn(name = "HSBACM_MA", referencedColumnName = "HSBACM_MA")
    @ManyToOne (fetch=FetchType.LAZY,optional = false)
    private HsbaChuyenMon hsbacmMa;
    @JoinColumn(name = "HSBACTSANKHOA_BSLAMBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactsankhoaBslamba;
    @JoinColumn(name = "HSBACTSANKHOA_NGUOIGIAOBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactsankhoaNguoigiaoba;
    @JoinColumn(name = "HSBACTSANKHOA_NGUOINHANBA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactsankhoaNguoinhanba;
    @JoinColumn(name = "HSBACTSANKHOA_BSDIEUTRI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien hsbactsankhoaBsdieutri;

    public HsbaChiTietSankhoa() {
    }

    public HsbaChiTietSankhoa(Integer hsbactsankhoaMa) {
        this.hsbactsankhoaMa = hsbactsankhoaMa;
    }

    public Integer getHsbactsankhoaMa() {
        return hsbactsankhoaMa;
    }

    public void setHsbactsankhoaMa(Integer hsbactsankhoaMa) {
        this.hsbactsankhoaMa = hsbactsankhoaMa;
    }

    public Date getHsbactsankhoaChuandoanngayde() {
        return hsbactsankhoaChuandoanngayde;
    }

    public void setHsbactsankhoaChuandoanngayde(Date hsbactsankhoaChuandoanngayde) {
        this.hsbactsankhoaChuandoanngayde = hsbactsankhoaChuandoanngayde;
    }

    public String getHsbactsankhoaChuandoanngoithai() {
        return hsbactsankhoaChuandoanngoithai;
    }

    public void setHsbactsankhoaChuandoanngoithai(String hsbactsankhoaChuandoanngoithai) {
        this.hsbactsankhoaChuandoanngoithai = hsbactsankhoaChuandoanngoithai;
    }

    public String getHsbactsankhoaChuandoancachthucde() {
        return hsbactsankhoaChuandoancachthucde;
    }

    public void setHsbactsankhoaChuandoancachthucde(String hsbactsankhoaChuandoancachthucde) {
        this.hsbactsankhoaChuandoancachthucde = hsbactsankhoaChuandoancachthucde;
    }

    public String getHsbactsankhoaChuandoankiemsoattucung() {
        return hsbactsankhoaChuandoankiemsoattucung;
    }

    public void setHsbactsankhoaChuandoankiemsoattucung(String hsbactsankhoaChuandoankiemsoattucung) {
        this.hsbactsankhoaChuandoankiemsoattucung = hsbactsankhoaChuandoankiemsoattucung;
    }

    public Boolean getHsbactsankhoaChuandoanphauthuatcapcuu() {
        return hsbactsankhoaChuandoanphauthuatcapcuu;
    }

    public void setHsbactsankhoaChuandoanphauthuatcapcuu(Boolean hsbactsankhoaChuandoanphauthuatcapcuu) {
        this.hsbactsankhoaChuandoanphauthuatcapcuu = hsbactsankhoaChuandoanphauthuatcapcuu;
    }

    public Boolean getHsbactsankhoaChuandoanphauthuatchudong() {
        return hsbactsankhoaChuandoanphauthuatchudong;
    }

    public void setHsbactsankhoaChuandoanphauthuatchudong(Boolean hsbactsankhoaChuandoanphauthuatchudong) {
        this.hsbactsankhoaChuandoanphauthuatchudong = hsbactsankhoaChuandoanphauthuatchudong;
    }

    public Boolean getHsbactsankhoaChuandoandonthai() {
        return hsbactsankhoaChuandoandonthai;
    }

    public void setHsbactsankhoaChuandoandonthai(Boolean hsbactsankhoaChuandoandonthai) {
        this.hsbactsankhoaChuandoandonthai = hsbactsankhoaChuandoandonthai;
    }

    public Boolean getHsbactsankhoaChuandoandathai() {
        return hsbactsankhoaChuandoandathai;
    }

    public void setHsbactsankhoaChuandoandathai(Boolean hsbactsankhoaChuandoandathai) {
        this.hsbactsankhoaChuandoandathai = hsbactsankhoaChuandoandathai;
    }

    public Boolean getHsbactsankhoaChuandoantrai() {
        return hsbactsankhoaChuandoantrai;
    }

    public void setHsbactsankhoaChuandoantrai(Boolean hsbactsankhoaChuandoantrai) {
        this.hsbactsankhoaChuandoantrai = hsbactsankhoaChuandoantrai;
    }

    public Boolean getHsbactsankhoaChuandoangai() {
        return hsbactsankhoaChuandoangai;
    }

    public void setHsbactsankhoaChuandoangai(Boolean hsbactsankhoaChuandoangai) {
        this.hsbactsankhoaChuandoangai = hsbactsankhoaChuandoangai;
    }

    public Boolean getHsbactsankhoaChuandoansong() {
        return hsbactsankhoaChuandoansong;
    }

    public void setHsbactsankhoaChuandoansong(Boolean hsbactsankhoaChuandoansong) {
        this.hsbactsankhoaChuandoansong = hsbactsankhoaChuandoansong;
    }

    public Boolean getHsbactsankhoaChuandoanchet() {
        return hsbactsankhoaChuandoanchet;
    }

    public void setHsbactsankhoaChuandoanchet(Boolean hsbactsankhoaChuandoanchet) {
        this.hsbactsankhoaChuandoanchet = hsbactsankhoaChuandoanchet;
    }

    public String getHsbactsankhoaChuandoanditat() {
        return hsbactsankhoaChuandoanditat;
    }

    public void setHsbactsankhoaChuandoanditat(String hsbactsankhoaChuandoanditat) {
        this.hsbactsankhoaChuandoanditat = hsbactsankhoaChuandoanditat;
    }

    public String getHsbactsankhoaChuandoancannang() {
        return hsbactsankhoaChuandoancannang;
    }

    public void setHsbactsankhoaChuandoancannang(String hsbactsankhoaChuandoancannang) {
        this.hsbactsankhoaChuandoancannang = hsbactsankhoaChuandoancannang;
    }

    public String getHsbactsankhoaNgaythubenh() {
        return hsbactsankhoaNgaythubenh;
    }

    public void setHsbactsankhoaNgaythubenh(String hsbactsankhoaNgaythubenh) {
        this.hsbactsankhoaNgaythubenh = hsbactsankhoaNgaythubenh;
    }

    public Date getHsbactsankhoaKinhcuoicungtungay() {
        return hsbactsankhoaKinhcuoicungtungay;
    }

    public void setHsbactsankhoaKinhcuoicungtungay(Date hsbactsankhoaKinhcuoicungtungay) {
        this.hsbactsankhoaKinhcuoicungtungay = hsbactsankhoaKinhcuoicungtungay;
    }

    public Date getHsbactsankhoaKinhcuoicungdenngay() {
        return hsbactsankhoaKinhcuoicungdenngay;
    }

    public void setHsbactsankhoaKinhcuoicungdenngay(Date hsbactsankhoaKinhcuoicungdenngay) {
        this.hsbactsankhoaKinhcuoicungdenngay = hsbactsankhoaKinhcuoicungdenngay;
    }

    public String getHsbactsankhoaLydovaov() {
        return hsbactsankhoaLydovaov;
    }

    public void setHsbactsankhoaLydovaov(String hsbactsankhoaLydovaov) {
        this.hsbactsankhoaLydovaov = hsbactsankhoaLydovaov;
    }

    public String getHsbactsankhoaTuoithaituan() {
        return hsbactsankhoaTuoithaituan;
    }

    public void setHsbactsankhoaTuoithaituan(String hsbactsankhoaTuoithaituan) {
        this.hsbactsankhoaTuoithaituan = hsbactsankhoaTuoithaituan;
    }

    public String getHsbactsankhoaKhamthaitai() {
        return hsbactsankhoaKhamthaitai;
    }

    public void setHsbactsankhoaKhamthaitai(String hsbactsankhoaKhamthaitai) {
        this.hsbactsankhoaKhamthaitai = hsbactsankhoaKhamthaitai;
    }

    public Boolean getHsbactsankhoaTiemphonguonvan() {
        return hsbactsankhoaTiemphonguonvan;
    }

    public void setHsbactsankhoaTiemphonguonvan(Boolean hsbactsankhoaTiemphonguonvan) {
        this.hsbactsankhoaTiemphonguonvan = hsbactsankhoaTiemphonguonvan;
    }

    public String getHsbactsankhoaSolantiemuonvan() {
        return hsbactsankhoaSolantiemuonvan;
    }

    public void setHsbactsankhoaSolantiemuonvan(String hsbactsankhoaSolantiemuonvan) {
        this.hsbactsankhoaSolantiemuonvan = hsbactsankhoaSolantiemuonvan;
    }

    public Date getHsbactsankhoaChuyendaluc() {
        return hsbactsankhoaChuyendaluc;
    }

    public void setHsbactsankhoaChuyendaluc(Date hsbactsankhoaChuyendaluc) {
        this.hsbactsankhoaChuyendaluc = hsbactsankhoaChuyendaluc;
    }

    public String getHsbactsankhoaDauhieulucdau() {
        return hsbactsankhoaDauhieulucdau;
    }

    public void setHsbactsankhoaDauhieulucdau(String hsbactsankhoaDauhieulucdau) {
        this.hsbactsankhoaDauhieulucdau = hsbactsankhoaDauhieulucdau;
    }

    public String getHsbactsankhoaChuyenbien() {
        return hsbactsankhoaChuyenbien;
    }

    public void setHsbactsankhoaChuyenbien(String hsbactsankhoaChuyenbien) {
        this.hsbactsankhoaChuyenbien = hsbactsankhoaChuyenbien;
    }

    public String getHsbactsankhoaTiensubenhbt() {
        return hsbactsankhoaTiensubenhbt;
    }

    public void setHsbactsankhoaTiensubenhbt(String hsbactsankhoaTiensubenhbt) {
        this.hsbactsankhoaTiensubenhbt = hsbactsankhoaTiensubenhbt;
    }

    public String getHsbactsankhoaTiensubenhgd() {
        return hsbactsankhoaTiensubenhgd;
    }

    public void setHsbactsankhoaTiensubenhgd(String hsbactsankhoaTiensubenhgd) {
        this.hsbactsankhoaTiensubenhgd = hsbactsankhoaTiensubenhgd;
    }

    public String getHsbactsankhoaThaykinhnam() {
        return hsbactsankhoaThaykinhnam;
    }

    public void setHsbactsankhoaThaykinhnam(String hsbactsankhoaThaykinhnam) {
        this.hsbactsankhoaThaykinhnam = hsbactsankhoaThaykinhnam;
    }

    public String getHsbactsankhoaThaykinhtuoi() {
        return hsbactsankhoaThaykinhtuoi;
    }

    public void setHsbactsankhoaThaykinhtuoi(String hsbactsankhoaThaykinhtuoi) {
        this.hsbactsankhoaThaykinhtuoi = hsbactsankhoaThaykinhtuoi;
    }

    public String getHsbactsankhoaTinhchatkinhnguyet() {
        return hsbactsankhoaTinhchatkinhnguyet;
    }

    public void setHsbactsankhoaTinhchatkinhnguyet(String hsbactsankhoaTinhchatkinhnguyet) {
        this.hsbactsankhoaTinhchatkinhnguyet = hsbactsankhoaTinhchatkinhnguyet;
    }

    public String getHsbactsankhoaChukykinh() {
        return hsbactsankhoaChukykinh;
    }

    public void setHsbactsankhoaChukykinh(String hsbactsankhoaChukykinh) {
        this.hsbactsankhoaChukykinh = hsbactsankhoaChukykinh;
    }

    public String getHsbactsankhoaLuongkinh() {
        return hsbactsankhoaLuongkinh;
    }

    public void setHsbactsankhoaLuongkinh(String hsbactsankhoaLuongkinh) {
        this.hsbactsankhoaLuongkinh = hsbactsankhoaLuongkinh;
    }

    public String getHsbactsankhoaBenhsankhoadadieutri() {
        return hsbactsankhoaBenhsankhoadadieutri;
    }

    public void setHsbactsankhoaBenhsankhoadadieutri(String hsbactsankhoaBenhsankhoadadieutri) {
        this.hsbactsankhoaBenhsankhoadadieutri = hsbactsankhoaBenhsankhoadadieutri;
    }

    public String getHsbactsankhoaTuanhoan() {
        return hsbactsankhoaTuanhoan;
    }

    public void setHsbactsankhoaTuanhoan(String hsbactsankhoaTuanhoan) {
        this.hsbactsankhoaTuanhoan = hsbactsankhoaTuanhoan;
    }

    public String getHsbactsankhoaHohap() {
        return hsbactsankhoaHohap;
    }

    public void setHsbactsankhoaHohap(String hsbactsankhoaHohap) {
        this.hsbactsankhoaHohap = hsbactsankhoaHohap;
    }

    public String getHsbactsankhoaTieuhoa() {
        return hsbactsankhoaTieuhoa;
    }

    public void setHsbactsankhoaTieuhoa(String hsbactsankhoaTieuhoa) {
        this.hsbactsankhoaTieuhoa = hsbactsankhoaTieuhoa;
    }

    public String getHsbactsankhoaThantietnieusinhhoc() {
        return hsbactsankhoaThantietnieusinhhoc;
    }

    public void setHsbactsankhoaThantietnieusinhhoc(String hsbactsankhoaThantietnieusinhhoc) {
        this.hsbactsankhoaThantietnieusinhhoc = hsbactsankhoaThantietnieusinhhoc;
    }

    public String getHsbactsankhoaTinhtrang() {
        return hsbactsankhoaTinhtrang;
    }

    public void setHsbactsankhoaTinhtrang(String hsbactsankhoaTinhtrang) {
        this.hsbactsankhoaTinhtrang = hsbactsankhoaTinhtrang;
    }

    public Boolean getHsbactsankhoaTinhtrangphu() {
        return hsbactsankhoaTinhtrangphu;
    }

    public void setHsbactsankhoaTinhtrangphu(Boolean hsbactsankhoaTinhtrangphu) {
        this.hsbactsankhoaTinhtrangphu = hsbactsankhoaTinhtrangphu;
    }

    public String getHsbactsankhoaCoquankhac() {
        return hsbactsankhoaCoquankhac;
    }

    public void setHsbactsankhoaCoquankhac(String hsbactsankhoaCoquankhac) {
        this.hsbactsankhoaCoquankhac = hsbactsankhoaCoquankhac;
    }

    public Boolean getHsbactsankhoaBungcoseophauthuatcu() {
        return hsbactsankhoaBungcoseophauthuatcu;
    }

    public void setHsbactsankhoaBungcoseophauthuatcu(Boolean hsbactsankhoaBungcoseophauthuatcu) {
        this.hsbactsankhoaBungcoseophauthuatcu = hsbactsankhoaBungcoseophauthuatcu;
    }

    public String getHsbactsankhoaChieucaotc() {
        return hsbactsankhoaChieucaotc;
    }

    public void setHsbactsankhoaChieucaotc(String hsbactsankhoaChieucaotc) {
        this.hsbactsankhoaChieucaotc = hsbactsankhoaChieucaotc;
    }

    public String getHsbactsankhoaVongbung() {
        return hsbactsankhoaVongbung;
    }

    public void setHsbactsankhoaVongbung(String hsbactsankhoaVongbung) {
        this.hsbactsankhoaVongbung = hsbactsankhoaVongbung;
    }

    public String getHsbactsankhoaHinhdangtc() {
        return hsbactsankhoaHinhdangtc;
    }

    public void setHsbactsankhoaHinhdangtc(String hsbactsankhoaHinhdangtc) {
        this.hsbactsankhoaHinhdangtc = hsbactsankhoaHinhdangtc;
    }

    public String getHsbactsankhoaTuthetc() {
        return hsbactsankhoaTuthetc;
    }

    public void setHsbactsankhoaTuthetc(String hsbactsankhoaTuthetc) {
        this.hsbactsankhoaTuthetc = hsbactsankhoaTuthetc;
    }

    public String getHsbactsankhoaConcotc() {
        return hsbactsankhoaConcotc;
    }

    public void setHsbactsankhoaConcotc(String hsbactsankhoaConcotc) {
        this.hsbactsankhoaConcotc = hsbactsankhoaConcotc;
    }

    public String getHsbactsankhoaTimthai() {
        return hsbactsankhoaTimthai;
    }

    public void setHsbactsankhoaTimthai(String hsbactsankhoaTimthai) {
        this.hsbactsankhoaTimthai = hsbactsankhoaTimthai;
    }

    public String getHsbactsankhoaVu() {
        return hsbactsankhoaVu;
    }

    public void setHsbactsankhoaVu(String hsbactsankhoaVu) {
        this.hsbactsankhoaVu = hsbactsankhoaVu;
    }

    public String getHsbactsankhoaDkngoaikhungchau() {
        return hsbactsankhoaDkngoaikhungchau;
    }

    public void setHsbactsankhoaDkngoaikhungchau(String hsbactsankhoaDkngoaikhungchau) {
        this.hsbactsankhoaDkngoaikhungchau = hsbactsankhoaDkngoaikhungchau;
    }

    public String getHsbactsankhoaBishop() {
        return hsbactsankhoaBishop;
    }

    public void setHsbactsankhoaBishop(String hsbactsankhoaBishop) {
        this.hsbactsankhoaBishop = hsbactsankhoaBishop;
    }

    public String getHsbactsankhoaAmho() {
        return hsbactsankhoaAmho;
    }

    public void setHsbactsankhoaAmho(String hsbactsankhoaAmho) {
        this.hsbactsankhoaAmho = hsbactsankhoaAmho;
    }

    public String getHsbactsankhoaAmdao() {
        return hsbactsankhoaAmdao;
    }

    public void setHsbactsankhoaAmdao(String hsbactsankhoaAmdao) {
        this.hsbactsankhoaAmdao = hsbactsankhoaAmdao;
    }

    public String getHsbactsankhoaTangsinhmon() {
        return hsbactsankhoaTangsinhmon;
    }

    public void setHsbactsankhoaTangsinhmon(String hsbactsankhoaTangsinhmon) {
        this.hsbactsankhoaTangsinhmon = hsbactsankhoaTangsinhmon;
    }

    public String getHsbactsankhoaCotucung() {
        return hsbactsankhoaCotucung;
    }

    public void setHsbactsankhoaCotucung(String hsbactsankhoaCotucung) {
        this.hsbactsankhoaCotucung = hsbactsankhoaCotucung;
    }

    public String getHsbactsankhoaPhanphu() {
        return hsbactsankhoaPhanphu;
    }

    public void setHsbactsankhoaPhanphu(String hsbactsankhoaPhanphu) {
        this.hsbactsankhoaPhanphu = hsbactsankhoaPhanphu;
    }

    public Boolean getHsbactsankhoaOiphong() {
        return hsbactsankhoaOiphong;
    }

    public void setHsbactsankhoaOiphong(Boolean hsbactsankhoaOiphong) {
        this.hsbactsankhoaOiphong = hsbactsankhoaOiphong;
    }

    public Boolean getHsbactsankhoaOidet() {
        return hsbactsankhoaOidet;
    }

    public void setHsbactsankhoaOidet(Boolean hsbactsankhoaOidet) {
        this.hsbactsankhoaOidet = hsbactsankhoaOidet;
    }

    public Boolean getHsbactsankhoaOiquale() {
        return hsbactsankhoaOiquale;
    }

    public void setHsbactsankhoaOiquale(Boolean hsbactsankhoaOiquale) {
        this.hsbactsankhoaOiquale = hsbactsankhoaOiquale;
    }

    public Date getHsbactsankhoaOivoluc() {
        return hsbactsankhoaOivoluc;
    }

    public void setHsbactsankhoaOivoluc(Date hsbactsankhoaOivoluc) {
        this.hsbactsankhoaOivoluc = hsbactsankhoaOivoluc;
    }

    public Boolean getHsbactsankhoaOivotunhien() {
        return hsbactsankhoaOivotunhien;
    }

    public void setHsbactsankhoaOivotunhien(Boolean hsbactsankhoaOivotunhien) {
        this.hsbactsankhoaOivotunhien = hsbactsankhoaOivotunhien;
    }

    public Boolean getHsbactsankhoaOivobamoi() {
        return hsbactsankhoaOivobamoi;
    }

    public void setHsbactsankhoaOivobamoi(Boolean hsbactsankhoaOivobamoi) {
        this.hsbactsankhoaOivobamoi = hsbactsankhoaOivobamoi;
    }

    public String getHsbactsankhoaMausacnuocoi() {
        return hsbactsankhoaMausacnuocoi;
    }

    public void setHsbactsankhoaMausacnuocoi(String hsbactsankhoaMausacnuocoi) {
        this.hsbactsankhoaMausacnuocoi = hsbactsankhoaMausacnuocoi;
    }

    public String getHsbactsankhoaNuocoinhieuhayit() {
        return hsbactsankhoaNuocoinhieuhayit;
    }

    public void setHsbactsankhoaNuocoinhieuhayit(String hsbactsankhoaNuocoinhieuhayit) {
        this.hsbactsankhoaNuocoinhieuhayit = hsbactsankhoaNuocoinhieuhayit;
    }

    public String getHsbactsankhoaNgoi() {
        return hsbactsankhoaNgoi;
    }

    public void setHsbactsankhoaNgoi(String hsbactsankhoaNgoi) {
        this.hsbactsankhoaNgoi = hsbactsankhoaNgoi;
    }

    public String getHsbactsankhoaThe() {
        return hsbactsankhoaThe;
    }

    public void setHsbactsankhoaThe(String hsbactsankhoaThe) {
        this.hsbactsankhoaThe = hsbactsankhoaThe;
    }

    public String getHsbactsankhoaKieuthe() {
        return hsbactsankhoaKieuthe;
    }

    public void setHsbactsankhoaKieuthe(String hsbactsankhoaKieuthe) {
        this.hsbactsankhoaKieuthe = hsbactsankhoaKieuthe;
    }

    public Boolean getHsbactsankhoaDolotcao() {
        return hsbactsankhoaDolotcao;
    }

    public void setHsbactsankhoaDolotcao(Boolean hsbactsankhoaDolotcao) {
        this.hsbactsankhoaDolotcao = hsbactsankhoaDolotcao;
    }

    public Boolean getHsbactsankhoaDolotchuc() {
        return hsbactsankhoaDolotchuc;
    }

    public void setHsbactsankhoaDolotchuc(Boolean hsbactsankhoaDolotchuc) {
        this.hsbactsankhoaDolotchuc = hsbactsankhoaDolotchuc;
    }

    public Boolean getHsbactsankhoaDolotchat() {
        return hsbactsankhoaDolotchat;
    }

    public void setHsbactsankhoaDolotchat(Boolean hsbactsankhoaDolotchat) {
        this.hsbactsankhoaDolotchat = hsbactsankhoaDolotchat;
    }

    public Boolean getHsbactsankhoaDolotlot() {
        return hsbactsankhoaDolotlot;
    }

    public void setHsbactsankhoaDolotlot(Boolean hsbactsankhoaDolotlot) {
        this.hsbactsankhoaDolotlot = hsbactsankhoaDolotlot;
    }

    public String getHsbactsankhoaDknhohave() {
        return hsbactsankhoaDknhohave;
    }

    public void setHsbactsankhoaDknhohave(String hsbactsankhoaDknhohave) {
        this.hsbactsankhoaDknhohave = hsbactsankhoaDknhohave;
    }

    public String getHsbactsankhoaXetnghiemcanlam() {
        return hsbactsankhoaXetnghiemcanlam;
    }

    public void setHsbactsankhoaXetnghiemcanlam(String hsbactsankhoaXetnghiemcanlam) {
        this.hsbactsankhoaXetnghiemcanlam = hsbactsankhoaXetnghiemcanlam;
    }

    public String getHsbactsankhoaHuongdieutri() {
        return hsbactsankhoaHuongdieutri;
    }

    public void setHsbactsankhoaHuongdieutri(String hsbactsankhoaHuongdieutri) {
        this.hsbactsankhoaHuongdieutri = hsbactsankhoaHuongdieutri;
    }

    public String getHsbactsankhoaTienluong() {
        return hsbactsankhoaTienluong;
    }

    public void setHsbactsankhoaTienluong(String hsbactsankhoaTienluong) {
        this.hsbactsankhoaTienluong = hsbactsankhoaTienluong;
    }

    public Date getHsbactsankhoaVaobuongdeluc() {
        return hsbactsankhoaVaobuongdeluc;
    }

    public void setHsbactsankhoaVaobuongdeluc(Date hsbactsankhoaVaobuongdeluc) {
        this.hsbactsankhoaVaobuongdeluc = hsbactsankhoaVaobuongdeluc;
    }

    public String getHsbactsankhoaTennguoitheodoi() {
        return hsbactsankhoaTennguoitheodoi;
    }

    public void setHsbactsankhoaTennguoitheodoi(String hsbactsankhoaTennguoitheodoi) {
        this.hsbactsankhoaTennguoitheodoi = hsbactsankhoaTennguoitheodoi;
    }

    public String getHsbactsankhoaChucdanhnguoitheodoi() {
        return hsbactsankhoaChucdanhnguoitheodoi;
    }

    public void setHsbactsankhoaChucdanhnguoitheodoi(String hsbactsankhoaChucdanhnguoitheodoi) {
        this.hsbactsankhoaChucdanhnguoitheodoi = hsbactsankhoaChucdanhnguoitheodoi;
    }

    public Date getHsbactsankhoaDeluc() {
        return hsbactsankhoaDeluc;
    }

    public void setHsbactsankhoaDeluc(Date hsbactsankhoaDeluc) {
        this.hsbactsankhoaDeluc = hsbactsankhoaDeluc;
    }

    public String getHsbactsankhoaApgar1phut() {
        return hsbactsankhoaApgar1phut;
    }

    public void setHsbactsankhoaApgar1phut(String hsbactsankhoaApgar1phut) {
        this.hsbactsankhoaApgar1phut = hsbactsankhoaApgar1phut;
    }

    public String getHsbactsankhoaApgar5phut() {
        return hsbactsankhoaApgar5phut;
    }

    public void setHsbactsankhoaApgar5phut(String hsbactsankhoaApgar5phut) {
        this.hsbactsankhoaApgar5phut = hsbactsankhoaApgar5phut;
    }

    public String getHsbactsankhoaApgar10phut() {
        return hsbactsankhoaApgar10phut;
    }

    public void setHsbactsankhoaApgar10phut(String hsbactsankhoaApgar10phut) {
        this.hsbactsankhoaApgar10phut = hsbactsankhoaApgar10phut;
    }

    public String getHsbactsankhoaCannang() {
        return hsbactsankhoaCannang;
    }

    public void setHsbactsankhoaCannang(String hsbactsankhoaCannang) {
        this.hsbactsankhoaCannang = hsbactsankhoaCannang;
    }

    public String getHsbactsankhoaCao() {
        return hsbactsankhoaCao;
    }

    public void setHsbactsankhoaCao(String hsbactsankhoaCao) {
        this.hsbactsankhoaCao = hsbactsankhoaCao;
    }

    public String getHsbactsankhoaVongdau() {
        return hsbactsankhoaVongdau;
    }

    public void setHsbactsankhoaVongdau(String hsbactsankhoaVongdau) {
        this.hsbactsankhoaVongdau = hsbactsankhoaVongdau;
    }

    public Boolean getHsbactsankhoaDonthaitrai() {
        return hsbactsankhoaDonthaitrai;
    }

    public void setHsbactsankhoaDonthaitrai(Boolean hsbactsankhoaDonthaitrai) {
        this.hsbactsankhoaDonthaitrai = hsbactsankhoaDonthaitrai;
    }

    public Boolean getHsbactsankhoaDonthaigai() {
        return hsbactsankhoaDonthaigai;
    }

    public void setHsbactsankhoaDonthaigai(Boolean hsbactsankhoaDonthaigai) {
        this.hsbactsankhoaDonthaigai = hsbactsankhoaDonthaigai;
    }

    public Boolean getHsbactsankhoaDathaitrai() {
        return hsbactsankhoaDathaitrai;
    }

    public void setHsbactsankhoaDathaitrai(Boolean hsbactsankhoaDathaitrai) {
        this.hsbactsankhoaDathaitrai = hsbactsankhoaDathaitrai;
    }

    public Boolean getHsbactsankhoaDathaigai() {
        return hsbactsankhoaDathaigai;
    }

    public void setHsbactsankhoaDathaigai(Boolean hsbactsankhoaDathaigai) {
        this.hsbactsankhoaDathaigai = hsbactsankhoaDathaigai;
    }

    public Boolean getHsbactsankhoaTatbamsinh() {
        return hsbactsankhoaTatbamsinh;
    }

    public void setHsbactsankhoaTatbamsinh(Boolean hsbactsankhoaTatbamsinh) {
        this.hsbactsankhoaTatbamsinh = hsbactsankhoaTatbamsinh;
    }

    public Boolean getHsbactsankhoaCohaumon() {
        return hsbactsankhoaCohaumon;
    }

    public void setHsbactsankhoaCohaumon(Boolean hsbactsankhoaCohaumon) {
        this.hsbactsankhoaCohaumon = hsbactsankhoaCohaumon;
    }

    public String getHsbactsankhoaCuthetatbamsinh() {
        return hsbactsankhoaCuthetatbamsinh;
    }

    public void setHsbactsankhoaCuthetatbamsinh(String hsbactsankhoaCuthetatbamsinh) {
        this.hsbactsankhoaCuthetatbamsinh = hsbactsankhoaCuthetatbamsinh;
    }

    public String getHsbactsankhoaTtsssaude() {
        return hsbactsankhoaTtsssaude;
    }

    public void setHsbactsankhoaTtsssaude(String hsbactsankhoaTtsssaude) {
        this.hsbactsankhoaTtsssaude = hsbactsankhoaTtsssaude;
    }

    public String getHsbactsankhoaXulyketqua() {
        return hsbactsankhoaXulyketqua;
    }

    public void setHsbactsankhoaXulyketqua(String hsbactsankhoaXulyketqua) {
        this.hsbactsankhoaXulyketqua = hsbactsankhoaXulyketqua;
    }

    public Boolean getHsbactsankhoaRauboc() {
        return hsbactsankhoaRauboc;
    }

    public void setHsbactsankhoaRauboc(Boolean hsbactsankhoaRauboc) {
        this.hsbactsankhoaRauboc = hsbactsankhoaRauboc;
    }

    public Boolean getHsbactsankhoaRauso() {
        return hsbactsankhoaRauso;
    }

    public void setHsbactsankhoaRauso(Boolean hsbactsankhoaRauso) {
        this.hsbactsankhoaRauso = hsbactsankhoaRauso;
    }

    public Date getHsbactsankhoaRausoluc() {
        return hsbactsankhoaRausoluc;
    }

    public void setHsbactsankhoaRausoluc(Date hsbactsankhoaRausoluc) {
        this.hsbactsankhoaRausoluc = hsbactsankhoaRausoluc;
    }

    public String getHsbactsankhoaCachsorau() {
        return hsbactsankhoaCachsorau;
    }

    public void setHsbactsankhoaCachsorau(String hsbactsankhoaCachsorau) {
        this.hsbactsankhoaCachsorau = hsbactsankhoaCachsorau;
    }

    public String getHsbactsankhoaMatmang() {
        return hsbactsankhoaMatmang;
    }

    public void setHsbactsankhoaMatmang(String hsbactsankhoaMatmang) {
        this.hsbactsankhoaMatmang = hsbactsankhoaMatmang;
    }

    public String getHsbactsankhoaMatmui() {
        return hsbactsankhoaMatmui;
    }

    public void setHsbactsankhoaMatmui(String hsbactsankhoaMatmui) {
        this.hsbactsankhoaMatmui = hsbactsankhoaMatmui;
    }

    public String getHsbactsankhoaBanhrau() {
        return hsbactsankhoaBanhrau;
    }

    public void setHsbactsankhoaBanhrau(String hsbactsankhoaBanhrau) {
        this.hsbactsankhoaBanhrau = hsbactsankhoaBanhrau;
    }

    public String getHsbactsankhoaBanhraucannang() {
        return hsbactsankhoaBanhraucannang;
    }

    public void setHsbactsankhoaBanhraucannang(String hsbactsankhoaBanhraucannang) {
        this.hsbactsankhoaBanhraucannang = hsbactsankhoaBanhraucannang;
    }

    public Boolean getHsbactsankhoaRaucuonco() {
        return hsbactsankhoaRaucuonco;
    }

    public void setHsbactsankhoaRaucuonco(Boolean hsbactsankhoaRaucuonco) {
        this.hsbactsankhoaRaucuonco = hsbactsankhoaRaucuonco;
    }

    public String getHsbactsankhoaCuongraudai() {
        return hsbactsankhoaCuongraudai;
    }

    public void setHsbactsankhoaCuongraudai(String hsbactsankhoaCuongraudai) {
        this.hsbactsankhoaCuongraudai = hsbactsankhoaCuongraudai;
    }

    public Boolean getHsbactsankhoaChaymausauso() {
        return hsbactsankhoaChaymausauso;
    }

    public void setHsbactsankhoaChaymausauso(Boolean hsbactsankhoaChaymausauso) {
        this.hsbactsankhoaChaymausauso = hsbactsankhoaChaymausauso;
    }

    public String getHsbactsankhoaLuongmaumat() {
        return hsbactsankhoaLuongmaumat;
    }

    public void setHsbactsankhoaLuongmaumat(String hsbactsankhoaLuongmaumat) {
        this.hsbactsankhoaLuongmaumat = hsbactsankhoaLuongmaumat;
    }

    public Boolean getHsbactsankhoaKiemsoattucung() {
        return hsbactsankhoaKiemsoattucung;
    }

    public void setHsbactsankhoaKiemsoattucung(Boolean hsbactsankhoaKiemsoattucung) {
        this.hsbactsankhoaKiemsoattucung = hsbactsankhoaKiemsoattucung;
    }

    public String getHsbactsankhoaRauXulyketqua() {
        return hsbactsankhoaRauXulyketqua;
    }

    public void setHsbactsankhoaRauXulyketqua(String hsbactsankhoaRauXulyketqua) {
        this.hsbactsankhoaRauXulyketqua = hsbactsankhoaRauXulyketqua;
    }

    public String getHsbactsankhoaDaniemmac() {
        return hsbactsankhoaDaniemmac;
    }

    public void setHsbactsankhoaDaniemmac(String hsbactsankhoaDaniemmac) {
        this.hsbactsankhoaDaniemmac = hsbactsankhoaDaniemmac;
    }

    public Boolean getHsbactsankhoaDethuong() {
        return hsbactsankhoaDethuong;
    }

    public void setHsbactsankhoaDethuong(Boolean hsbactsankhoaDethuong) {
        this.hsbactsankhoaDethuong = hsbactsankhoaDethuong;
    }

    public Boolean getHsbactsankhoaForceps() {
        return hsbactsankhoaForceps;
    }

    public void setHsbactsankhoaForceps(Boolean hsbactsankhoaForceps) {
        this.hsbactsankhoaForceps = hsbactsankhoaForceps;
    }

    public Boolean getHsbactsankhoaGiachut() {
        return hsbactsankhoaGiachut;
    }

    public void setHsbactsankhoaGiachut(Boolean hsbactsankhoaGiachut) {
        this.hsbactsankhoaGiachut = hsbactsankhoaGiachut;
    }

    public Boolean getHsbactsankhoaDephauthuat() {
        return hsbactsankhoaDephauthuat;
    }

    public void setHsbactsankhoaDephauthuat(Boolean hsbactsankhoaDephauthuat) {
        this.hsbactsankhoaDephauthuat = hsbactsankhoaDephauthuat;
    }

    public Boolean getHsbactsankhoaDechihuy() {
        return hsbactsankhoaDechihuy;
    }

    public void setHsbactsankhoaDechihuy(Boolean hsbactsankhoaDechihuy) {
        this.hsbactsankhoaDechihuy = hsbactsankhoaDechihuy;
    }

    public Boolean getHsbactsankhoaDekhac() {
        return hsbactsankhoaDekhac;
    }

    public void setHsbactsankhoaDekhac(Boolean hsbactsankhoaDekhac) {
        this.hsbactsankhoaDekhac = hsbactsankhoaDekhac;
    }

    public String getHsbactsankhoaLydocanthiep() {
        return hsbactsankhoaLydocanthiep;
    }

    public void setHsbactsankhoaLydocanthiep(String hsbactsankhoaLydocanthiep) {
        this.hsbactsankhoaLydocanthiep = hsbactsankhoaLydocanthiep;
    }

    public Boolean getHsbactsankhoaTangsinhmonKhongrach() {
        return hsbactsankhoaTangsinhmonKhongrach;
    }

    public void setHsbactsankhoaTangsinhmonKhongrach(Boolean hsbactsankhoaTangsinhmonKhongrach) {
        this.hsbactsankhoaTangsinhmonKhongrach = hsbactsankhoaTangsinhmonKhongrach;
    }

    public Boolean getHsbactsankhoaTangsinhmonRach() {
        return hsbactsankhoaTangsinhmonRach;
    }

    public void setHsbactsankhoaTangsinhmonRach(Boolean hsbactsankhoaTangsinhmonRach) {
        this.hsbactsankhoaTangsinhmonRach = hsbactsankhoaTangsinhmonRach;
    }

    public Boolean getHsbactsankhoaTangsinhmonCat() {
        return hsbactsankhoaTangsinhmonCat;
    }

    public void setHsbactsankhoaTangsinhmonCat(Boolean hsbactsankhoaTangsinhmonCat) {
        this.hsbactsankhoaTangsinhmonCat = hsbactsankhoaTangsinhmonCat;
    }

    public String getHsbactsankhoaPpkhauvaloaichi() {
        return hsbactsankhoaPpkhauvaloaichi;
    }

    public void setHsbactsankhoaPpkhauvaloaichi(String hsbactsankhoaPpkhauvaloaichi) {
        this.hsbactsankhoaPpkhauvaloaichi = hsbactsankhoaPpkhauvaloaichi;
    }

    public Boolean getHsbactsankhoaCotucungKhongrach() {
        return hsbactsankhoaCotucungKhongrach;
    }

    public void setHsbactsankhoaCotucungKhongrach(Boolean hsbactsankhoaCotucungKhongrach) {
        this.hsbactsankhoaCotucungKhongrach = hsbactsankhoaCotucungKhongrach;
    }

    public Boolean getHsbactsankhoaCotucungRach() {
        return hsbactsankhoaCotucungRach;
    }

    public void setHsbactsankhoaCotucungRach(Boolean hsbactsankhoaCotucungRach) {
        this.hsbactsankhoaCotucungRach = hsbactsankhoaCotucungRach;
    }

    public String getHsbactsankhoaHuongdtCdtt() {
        return hsbactsankhoaHuongdtCdtt;
    }

    public void setHsbactsankhoaHuongdtCdtt(String hsbactsankhoaHuongdtCdtt) {
        this.hsbactsankhoaHuongdtCdtt = hsbactsankhoaHuongdtCdtt;
    }

    public Integer getHsbactsankhoaSotoxquang() {
        return hsbactsankhoaSotoxquang;
    }

    public void setHsbactsankhoaSotoxquang(Integer hsbactsankhoaSotoxquang) {
        this.hsbactsankhoaSotoxquang = hsbactsankhoaSotoxquang;
    }

    public Integer getHsbactsankhoaSotoctscanner() {
        return hsbactsankhoaSotoctscanner;
    }

    public void setHsbactsankhoaSotoctscanner(Integer hsbactsankhoaSotoctscanner) {
        this.hsbactsankhoaSotoctscanner = hsbactsankhoaSotoctscanner;
    }

    public Integer getHsbactsankhoaSotosieuam() {
        return hsbactsankhoaSotosieuam;
    }

    public void setHsbactsankhoaSotosieuam(Integer hsbactsankhoaSotosieuam) {
        this.hsbactsankhoaSotosieuam = hsbactsankhoaSotosieuam;
    }

    public Integer getHsbactsankhoaSotoxn() {
        return hsbactsankhoaSotoxn;
    }

    public void setHsbactsankhoaSotoxn(Integer hsbactsankhoaSotoxn) {
        this.hsbactsankhoaSotoxn = hsbactsankhoaSotoxn;
    }

    public Integer getHsbactsankhoaSotokhac() {
        return hsbactsankhoaSotokhac;
    }

    public void setHsbactsankhoaSotokhac(Integer hsbactsankhoaSotokhac) {
        this.hsbactsankhoaSotokhac = hsbactsankhoaSotokhac;
    }

    public String getHsbactsankhoaSotoloaikhac() {
        return hsbactsankhoaSotoloaikhac;
    }

    public void setHsbactsankhoaSotoloaikhac(String hsbactsankhoaSotoloaikhac) {
        this.hsbactsankhoaSotoloaikhac = hsbactsankhoaSotoloaikhac;
    }

    public Integer getHsbactsankhoaTongsoto() {
        return hsbactsankhoaTongsoto;
    }

    public void setHsbactsankhoaTongsoto(Integer hsbactsankhoaTongsoto) {
        this.hsbactsankhoaTongsoto = hsbactsankhoaTongsoto;
    }

    public HsbaChuyenMon getHsbacmMa() {
        return hsbacmMa;
    }

    public void setHsbacmMa(HsbaChuyenMon hsbacmMa) {
        this.hsbacmMa = hsbacmMa;
    }

    public DtDmNhanVien getHsbactsankhoaBslamba() {
        return hsbactsankhoaBslamba;
    }

    public void setHsbactsankhoaBslamba(DtDmNhanVien hsbactsankhoaBslamba) {
        this.hsbactsankhoaBslamba = hsbactsankhoaBslamba;
    }

    public DtDmNhanVien getHsbactsankhoaNguoigiaoba() {
        return hsbactsankhoaNguoigiaoba;
    }

    public void setHsbactsankhoaNguoigiaoba(DtDmNhanVien hsbactsankhoaNguoigiaoba) {
        this.hsbactsankhoaNguoigiaoba = hsbactsankhoaNguoigiaoba;
    }

    public DtDmNhanVien getHsbactsankhoaNguoinhanba() {
        return hsbactsankhoaNguoinhanba;
    }

    public void setHsbactsankhoaNguoinhanba(DtDmNhanVien hsbactsankhoaNguoinhanba) {
        this.hsbactsankhoaNguoinhanba = hsbactsankhoaNguoinhanba;
    }

    public DtDmNhanVien getHsbactsankhoaBsdieutri() {
        return hsbactsankhoaBsdieutri;
    }

    public void setHsbactsankhoaBsdieutri(DtDmNhanVien hsbactsankhoaBsdieutri) {
        this.hsbactsankhoaBsdieutri = hsbactsankhoaBsdieutri;
    }

    public String getHsbactsankhoaLaychongnam() {
        return hsbactsankhoaLaychongnam;
    }

    public void setHsbactsankhoaLaychongnam(String hsbactsankhoaLaychongnam) {
        this.hsbactsankhoaLaychongnam = hsbactsankhoaLaychongnam;
    }

    public String getHsbactsankhoaLaychongtuoi() {
        return hsbactsankhoaLaychongtuoi;
    }

    public void setHsbactsankhoaLaychongtuoi(String hsbactsankhoaLaychongtuoi) {
        this.hsbactsankhoaLaychongtuoi = hsbactsankhoaLaychongtuoi;
    }

    public String getHsbactsankhoaSomuikhau() {
        return hsbactsankhoaSomuikhau;
    }

    public void setHsbactsankhoaSomuikhau(String hsbactsankhoaSomuikhau) {
        this.hsbactsankhoaSomuikhau = hsbactsankhoaSomuikhau;
    }
    

    public DtDmNhanVien getHsbactsankhoaBslamba(boolean create) {
        if (create && hsbactsankhoaBslamba == null) {
            hsbactsankhoaBslamba = new DtDmNhanVien();
        }
        return hsbactsankhoaBslamba;
    }

    public DtDmNhanVien getHsbactsankhoaNguoigiaoba(boolean create) {
        if (create && hsbactsankhoaNguoigiaoba == null) {
            hsbactsankhoaNguoigiaoba = new DtDmNhanVien();
        }
        return hsbactsankhoaNguoigiaoba;
    }

    public DtDmNhanVien getHsbactsankhoaNguoinhanba(boolean create) {
        if (create && hsbactsankhoaNguoinhanba == null) {
            hsbactsankhoaNguoinhanba = new DtDmNhanVien();
        }
        return hsbactsankhoaNguoinhanba;
    }

    public DtDmNhanVien getHsbactsankhoaBsdieutri(boolean create) {
        if (create && hsbactsankhoaBsdieutri == null) {
            hsbactsankhoaBsdieutri = new DtDmNhanVien();
        }
        return hsbactsankhoaBsdieutri;
    }

    public HsbaChuyenMon getHsbacmMa(boolean create) {
        if (create && getHsbacmMa() == null) {
            setHsbacmMa(new HsbaChuyenMon());
        }
        return getHsbacmMa();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hsbactsankhoaMa != null ? hsbactsankhoaMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HsbaChiTietSankhoa)) {
            return false;
        }
        HsbaChiTietSankhoa other = (HsbaChiTietSankhoa) object;
        if ((this.hsbactsankhoaMa == null && other.hsbactsankhoaMa != null) || (this.hsbactsankhoaMa != null && !this.hsbactsankhoaMa.equals(other.hsbactsankhoaMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.test.HsbaChiTietSankhoa[hsbactsankhoaMa=" + hsbactsankhoaMa + "]";
    }

}
