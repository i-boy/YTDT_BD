package com.iesvn.yte.dieutri.ajax;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.jboss.seam.faces.FacesMessages;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.util.IConstantsRes;

/**
 * 
 * @author DOXP
 */
public class HuyKhamAction extends Action {
	
	private static Logger log = Logger.getLogger(HuyKhamAction.class);
	
	@Override
	public String performAction(String request) {
		
		String okId = "";
		String errorId = "";
		String xml = "";
		Document xmlDoc = null;
		//int myBreak = 0;
		System.out.println("xml " + request);
		try {
			xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new ByteArrayInputStream(request.getBytes("UTF-8")));
		} catch (ParserConfigurationException ex) {
			System.out.println("Error: " + ex.toString());
		} catch (SAXException ex) {
			System.out.println("Error: " + ex.toString());
		} catch (IOException ex) {
			System.out.println("Error: " + ex.toString());
		}

		if (xmlDoc != null) {
			NodeList listTD = xmlDoc.getElementsByTagName("TIEP_DON");
			if (listTD.getLength() > 0) {
				System.out.println("Tiep don count " + listTD.getLength());
				for (int i = 0; i < listTD.getLength(); i++) {
					//myBreak = 0;
					Node nodeTD = listTD.item(i);
					System.out.println(nodeTD.getAttributes().getNamedItem("TIEPDON_MA").getTextContent());
					String maPhu = nodeTD.getAttributes().getNamedItem("TIEP_DON_MAPHU").getTextContent();
					System.out.println(maPhu);
					TiepDon td = null;
					try {
						if (this.getDelTiepDon(nodeTD) != null) {
							td = this.getDelTiepDon(nodeTD);
						} else {
							errorId += maPhu + "---";
							System.out.println("errorId " + errorId);
							continue;
						}

					} catch (Exception ex) {
						ex.printStackTrace();
					}
					System.out.println("-----------");
							try {
								
								//kiem tra co ho can lam sang hay ko - neu co thi ko cho xoa
								ClsKhamDelegate ClsKhamDel = ClsKhamDelegate.getInstance();
								List<ClsKham> list = ClsKhamDel.findByTiepdonMaVaLoaiClsKham(td.getTiepdonMa());
								if(list!=null && list.size()>0)
								{
									FacesMessages.instance().add(IConstantsRes.TIEPDON_KHONG_THE_XOA_BENH_NHAN_CO_KB);
									errorId += maPhu + "---";
									break;
								}
								
								//kiem tra co ho so benh an hay ko - neu co thi ko cho xoa
								HsbaDelegate hsbaDel = HsbaDelegate.getInstance();
								Hsba objHSBA = hsbaDel.findByTiepDonMa(td.getTiepdonMa());
								if(objHSBA!=null)
								{
									FacesMessages.instance().add(IConstantsRes.TIEPDON_KHONG_THE_XOA_BENH_NHAN_CO_KB);
									errorId += maPhu + "---";
									break;
								}
								
								TiepDonDelegate tdDelegate = TiepDonDelegate.getInstance();
								String tdID = tdDelegate.delHuyKham(td);
								System.out.println("insert phieu nhap kho "	+ tdID);
								if (tdID != "")
									okId += maPhu + ",,," + tdID + "---";
								else
									errorId += maPhu + "---";
								log.info("*****Ma tiep don da xoa:" + tdID);
								log.info("*****Ma okId:" + okId);
								log.info("*****Ma errorId:" + errorId);
							} catch (Exception ex) {
								System.out.println("Error: " + ex.toString());
								errorId += maPhu + "---";
							}
						
					}
				}
				xml = okId + ";;;" + errorId;
				System.out.println("xml response: " + xml);
			}
		return String.format("<result>%s</result>", xml);
		}

		
	


	public TiepDon getDelTiepDon(Node nodePN) throws ServiceException,
			RemoteException, Exception {
		TiepDon td = new TiepDon();
		if (nodePN != null) {
			String tiepdonma = nodePN.getAttributes().getNamedItem("TIEPDON_MA").getTextContent();

			if (tiepdonma != "") {
				td.setTiepdonMa(tiepdonma);
			} else {
				return null;
			}
		
		}
		return td;
	}
}



