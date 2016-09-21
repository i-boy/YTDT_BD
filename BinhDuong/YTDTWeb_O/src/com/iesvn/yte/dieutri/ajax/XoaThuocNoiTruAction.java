package com.iesvn.yte.dieutri.ajax;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ServiceException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;

/**
 * 
 * @author DOXP
 */
public class XoaThuocNoiTruAction extends Action {
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
			NodeList listTNT = xmlDoc.getElementsByTagName("THUOC_NOI_TRU");
			if (listTNT.getLength() > 0) {
				System.out.println("Tiep don count "
						+ listTNT.getLength());
				for (int i = 0; i < listTNT.getLength(); i++) {
					//myBreak = 0;
					Node nodeTNT = listTNT.item(i);
					System.out.println(nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_MAPHIEU").getTextContent());
					String maPhu = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_MAPHU").getTextContent();
					System.out.println(maPhu);
					ThuocNoiTru tnt = null;
					try {
						if (this.getDelThuocNoiTru(nodeTNT) != null) {
							tnt = this.getDelThuocNoiTru(nodeTNT);
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
								
								ThuocNoiTruDelegate tntWS = ThuocNoiTruDelegate.getInstance();
								String tntID = tntWS.delHuyTNT(tnt);
								System.out.println("insert phieu nhap kho "
										+ tntID);
								if (tntID != "")
									okId += maPhu + "---";
								else
									errorId += maPhu + "---";
							
							} catch (Exception ex) {
								System.out.println("Error: " + ex.toString());
							}
						
					}
				}
				xml = okId + ";;;" + errorId;
				System.out.println("xml response: " + xml);
			}
		return xml;
		}

		
	


	public ThuocNoiTru getDelThuocNoiTru(Node nodeTNT) throws ServiceException,
			RemoteException, Exception {
		ThuocNoiTru tnt = new ThuocNoiTru();
		if (nodeTNT != null) {
			String tntmaphieu = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_MAPHIEU").getTextContent();

			if (tntmaphieu != "") {
				tnt.setThuocnoitruMaphieu(tntmaphieu);
			} else {
				return null;
			}
		
		}
		return tnt;
	}
}



