package com.iesvn.yte;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;

import com.iesvn.yte.util.IConstantsRes;

public class ReportUtil {
	
	public static String xuatReport(String pathBase,
			String pathTemplateReport, String pathReportResult, String phanHe,
			String tenFileTemplate,
			Map<String, Object> params, String loaiFile, String fileNameExt) throws Exception {
		return xuatReportBenhAnDieuTri(pathBase, pathTemplateReport, pathReportResult, phanHe, tenFileTemplate, params, loaiFile, fileNameExt);
		
	}

	/**
	 * 
	 * @author thanhdo
	 * @param pathBase
	 * @param pathTemplate
	 * @param phanHe
	 * @param tenFileTemplate
	 * @param fileNameExt
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * ex: pathBase = IConstantsRes.PATH_BASE pathTemplateReport =
	 * IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI phanHe = hsba/ reportResult =
	 * IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI loaiFile = HTML
	 * 
	 * fileNameExt = "" or
	 * Date currentDate = new Date();
     * String fileNameExt = String.valueOf(currentDate.getTime()) ;
     * 
	 */
	public static String xuatReportBenhAnDieuTri(String pathBase,
			String pathTemplateReport, String pathReportResult, String phanHe,
			String tenFileTemplate, 
			Map<String, Object> params, String loaiFile, String fileNameExt) throws Exception {

		String tenFile = "";
		JasperPrint jasperPrint = null;
		
		try {

			String pathTemplate = pathBase + pathTemplateReport + phanHe
					+ tenFileTemplate + ".jrxml";

			JasperReport jasperReport = JasperCompileManager
					.compileReport(pathTemplate);

			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection(
					IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER,
					IConstantsRes.DATABASE_PASS);

			jasperPrint = JasperFillManager.fillReport(jasperReport, params,
					conn);
			tenFile = xuatFile(pathBase, pathReportResult, phanHe, jasperPrint,
					loaiFile, fileNameExt + tenFileTemplate);
			// tenFile = aaa.doc
			// tenFile ==aaa.pdf
			// ....

			conn.close();

		} catch (JRException ex) {
			ex.printStackTrace();
		}
		// reportFinished = position + pathExport;
		return tenFile;

	}

	/**
	 * 
	 * @param pathBase
	 * @param reportResult
	 * @param jasperPrint
	 * @param loaiFile
	 * @param tenFile
	 * @param phanHe
	 * @return : tenfile + . + loaifile , for example: aaaa.html, aaaa.pdf
	 * 
	 * ex: pathBase = IConstantsRes.PATH_BASE reportResult =
	 * IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI phanHe = hsba/
	 * 
	 * loaiFile = HTML, DOC, EXCEL, PDF
	 * 
	 * @throws Exception
	 */
	private static String xuatFile(String pathBase, String pathReportResult,
			String phanHe, JasperPrint jasperPrint, String loaiFile,
			String tenFile) throws Exception {

		String fileName = "";

		String pathExport = pathBase + pathReportResult + phanHe ;

		if (loaiFile.compareToIgnoreCase("HTML") == 0) {

			JRHtmlExporter htmlExporter = new JRHtmlExporter();
			htmlExporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
			htmlExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,"UTF-8");
			htmlExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,pathExport + tenFile + ".html");
			htmlExporter.exportReport();
			fileName = tenFile + ".html";
		} 
		else if (loaiFile.compareToIgnoreCase("DOC") == 0) {
			JRRtfExporter rtfExporter = new JRRtfExporter();
			rtfExporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
			rtfExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,"UTF-8");
			rtfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, pathExport + tenFile + ".doc");
			rtfExporter.exportReport();
			fileName = tenFile + ".doc";

		}
		else if (loaiFile.compareToIgnoreCase("EXCEL") == 0) {
			JExcelApiExporter xlsExporter = new JExcelApiExporter();
			xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			xlsExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
			xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, pathExport + tenFile + ".xls");
			xlsExporter.exportReport();
			fileName = tenFile + ".xls";

		} else if (loaiFile.compareToIgnoreCase("PDF") == 0) {
			JRPdfExporter pdfExporter = new JRPdfExporter();
			pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			pdfExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
			pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, pathExport + tenFile + ".pdf");
			pdfExporter.exportReport();
			fileName = tenFile + ".pdf";
		}
		else if (loaiFile.compareToIgnoreCase("XML") == 0) {
            JRXmlExporter xmlExporter = new JRXmlExporter();
            xmlExporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
            xmlExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,"UTF-8");
            xmlExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, pathExport + tenFile + ".xml");
            fileName = tenFile + ".xml";
            xmlExporter.exportReport();
		}
		else {
			return "";
		}
		return fileName;
	}

	

}


