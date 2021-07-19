package io.oa.accidentincident.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestParam;

import io.oa.accidentincident.repository.AccidentRepository;
import io.oa.accidentincident.repository.DegatRepository;
import io.oa.accidentincident.repository.VictimeRepository;
import io.oa.accidentincident.response.EstimationPrixDegatParDate;
import io.oa.accidentincident.response.EstimationPrixDegatParSousTypeAccident;
import io.oa.accidentincident.response.EstimationPrixDegatParTypeAccident;
import io.oa.accidentincident.response.NbrAccidentParDate;
import io.oa.accidentincident.response.NbrAccidentParDistrict;
import io.oa.accidentincident.response.NbrAccidentParMois;
import io.oa.accidentincident.response.NbrAccidentParMoyenTransport;
import io.oa.accidentincident.response.NbrAccidentParMoyenTransportDate;
import io.oa.accidentincident.response.NbrAccidentParSousTypeAccident;
import io.oa.accidentincident.response.NbrAccidentParTypeAccident;
import io.oa.accidentincident.response.NbrAccidentVictimeParAnner;
import io.oa.accidentincident.response.NbrBlesserExterne;
import io.oa.accidentincident.response.NbrBlesserInterne;
import io.oa.accidentincident.response.NbrBlesserParMois;
import io.oa.accidentincident.response.NbrBlesserParMoisNiveau;
import io.oa.accidentincident.response.NbrBlesserParNiveauBlessure;
import io.oa.accidentincident.response.NbrMortsParDate;
import io.oa.accidentincident.response.NbrMortsParMois;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvMetadataExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleCsvMetadataReportConfiguration;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class ReportService {

	@Autowired
	DegatRepository drepository;
	
	@Autowired
	AccidentRepository arepository;
	
	@Autowired
	VictimeRepository  vrepository;
	
	@Autowired
	private NbrAccidentVictimeParAnnerService  navservice;  
	
	public String exportReportEstimationprixdegatpardate(String reportFormat) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<EstimationPrixDegatParDate> listEstimationPrixDegatParDate = drepository.listEstimationPrixDegatParDate();
		File file = ResourceUtils.getFile("classpath:estimationprixdegatpardate.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listEstimationPrixDegatParDate);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\estimationprixdegatpardate.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\estimationprixdegatpardate.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\estimationprixdegatpardate.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\estimationprixdegatpardate.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\estimationprixdegatpardate.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();
        	

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportEstimationprixdegatpartypeaccident(String reportFormat) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<EstimationPrixDegatParTypeAccident> listEstimationPrixDegatParTypeAccident = drepository.listEstimationPrixDegatParTypeAccident();
		File file = ResourceUtils.getFile("classpath:estimationprixdegatpartypeaccident.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listEstimationPrixDegatParTypeAccident);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\estimationprixdegatpartypeaccident.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\estimationprixdegatpartypeaccident.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\estimationprixdegatpartypeaccident.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\estimationprixdegatpartypeaccident.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\estimationprixdegatpartypeaccident.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportEstimationprixdegatparsoustypeaccident(String reportFormat) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<EstimationPrixDegatParSousTypeAccident> listEstimationPrixDegatParSousTypeAccident = drepository.listEstimationPrixDegatParSousTypeAccident();
		File file = ResourceUtils.getFile("classpath:estimationprixdegatparsoustypeaccident.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listEstimationPrixDegatParSousTypeAccident);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\estimationprixdegatparsoustypeaccident.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\estimationprixdegatparsoustypeaccident.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\estimationprixdegatparsoustypeaccident.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\estimationprixdegatparsoustypeaccident.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\estimationprixdegatparsoustypeaccident.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportNbrAccidentpardate(String reportFormat) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrAccidentParDate> listNombreAccidentParDate = arepository.listNbrAccidentParDate();
		File file = ResourceUtils.getFile("classpath:nbraccidentpardate.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listNombreAccidentParDate);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\nbraccidentpardate.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\nbraccidentpardate.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\nbraccidentpardate.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\nbraccidentpardate.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\nbraccidentpardate.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportNbrAccidentparmois(String reportFormat, int d1) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrAccidentParMois> listNombreAccidentParMois = arepository.listNbrAccidentParMois(d1);
		File file = ResourceUtils.getFile("classpath:nbraccidentparmois.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listNombreAccidentParMois);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\nbraccidentparmois.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\nbraccidentparmois.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\nbraccidentparmois.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\nbraccidentparmois.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\nbraccidentparmois.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportNbrAccidentpardistrict(String reportFormat) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrAccidentParDistrict> listNombreAccidentParDistrict = arepository.listNbrAccidentParDistrict();
		File file = ResourceUtils.getFile("classpath:nbraccidentpardistrict.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listNombreAccidentParDistrict);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\nbraccidentpardistrict.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\nbraccidentpardistrict.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\nbraccidentpardistrict.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\nbraccidentpardistrict.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\nbraccidentpardistrict.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportNbrAccidentparmoyentransport(String reportFormat) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrAccidentParMoyenTransport> listNombreAccidentParMoyenTransport = arepository.listNbrAccidentParMoyenTransport();
		File file = ResourceUtils.getFile("classpath:nbraccidentparmoyentransport.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listNombreAccidentParMoyenTransport);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\nbraccidentparmoyentransport.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\nbraccidentparmoyentransport.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\nbraccidentparmoyentransport.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\nbraccidentparmoyentransport.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\nbraccidentparmoyentransport.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportNbrAccidentparmoyentransportdate(String reportFormat) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrAccidentParMoyenTransportDate> listNombreAccidentParMoyenTransportDate = arepository.listNbrAccidentParMoyenTransportDate();
		File file = ResourceUtils.getFile("classpath:nbraccidentparmoyentransportdate.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listNombreAccidentParMoyenTransportDate);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\nbraccidentparmoyentransportdate.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\nbraccidentparmoyentransportdate.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\nbraccidentparmoyentransportdate.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\nbraccidentparmoyentransportdate.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\nbraccidentparmoyentransportdate.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportNbrAccidentpartypeaccident(String reportFormat) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrAccidentParTypeAccident> listNombreAccidentParTypeAccident = arepository.listNbrAccidentParTypeAccident();
		File file = ResourceUtils.getFile("classpath:nbraccidentpartypeaccident.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listNombreAccidentParTypeAccident);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\nbraccidentpartypeaccident.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\nbraccidentpartypeaccident.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\nbraccidentpartypeaccident.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\nbraccidentpartypeaccident.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\nbraccidentpartypeaccident.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportNbrAccidentparsoustypeaccident(String reportFormat) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrAccidentParSousTypeAccident> listNombreAccidentParSousTypeAccident = arepository.listNbrAccidentParSousTypeAccident();
		File file = ResourceUtils.getFile("classpath:nbraccidentparsoustypeaccident.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listNombreAccidentParSousTypeAccident);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\nbraccidentparsoustypeaccident.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\nbraccidentparsoustypeaccident.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\nbraccidentparsoustypeaccident.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\nbraccidentparsoustypeaccident.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\nbraccidentparsoustypeaccident.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportNbrBlesserExterne(String reportFormat) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrBlesserExterne> listNombreBlesserExterne = vrepository.listNbrBlesserExterne();
		File file = ResourceUtils.getFile("classpath:nbrblesserexterne.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listNombreBlesserExterne);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\nbrblesserexterne.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\nbrblesserexterne.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\nbrblesserexterne.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\nbrblesserexterne.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\nbrblesserexterne.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportNbrBlesserInterne(String reportFormat) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrBlesserInterne> listNombreBlesserInterne = vrepository.listNbrBlesserInterne();
		File file = ResourceUtils.getFile("classpath:nbrblesserinterne.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listNombreBlesserInterne);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\nbrblesserinterne.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\nbrblesserinterne.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\nbrblesserinterne.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\nbrblesserinterne.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\nbrblesserinterne.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	
	public String exportReportNbrBlesserParNiveauBlessure(String reportFormat) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrBlesserParNiveauBlessure> listNombreBlesserParNiveauBlessure = vrepository.listNbrBlesserParNiveauBlessure();
		File file = ResourceUtils.getFile("classpath:nbrblesserparniveaublessure.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listNombreBlesserParNiveauBlessure);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\nbrblesserparniveaublessure.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\nbrblesserparniveaublessure.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\nbrblesserparniveaublessure.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\nbrblesserparniveaublessure.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\nbrblesserparniveaublessure.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportNbrMortsParMois(String reportFormat, int d1) throws IOException, FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrMortsParMois> listNombreMortsParMois = arepository.listNbrMortsParMois(d1);
		File file = ResourceUtils.getFile("classpath:nbrmortsparmois.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listNombreMortsParMois);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\nbrmortsparmois.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\nbrmortsparmois.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\nbrmortsparmois.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\nbrmortsparmois.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\nbrmortsparmois.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportNbrBlesserParMois(String reportFormat, int d1) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrBlesserParMois> listNombreBlesserParMois  = new ArrayList<>(); 
		NbrBlesserParMois nbrBlesserParMois = new NbrBlesserParMois(); 
		List<NbrBlesserParMoisNiveau> result1 = arepository.listNbrBlesserParMoisNiveau(d1);
		for(int i=0;i<result1.size();i++) {
			NbrBlesserParMoisNiveau nbpmn = new NbrBlesserParMoisNiveau();
			if(i-1>=0)
			 {
			nbpmn.setMois(result1.get(i-1).getMois());
			 }
			if(!result1.get(i).getMois().equals(nbpmn.getMois()) )
			{
			nbrBlesserParMois = new NbrBlesserParMois(); 
			}
			// nbrBlesserParMois.setMois(r.getMois()); 
			if(nbrBlesserParMois.getMois()==null) {
				nbrBlesserParMois.setMois(result1.get(i).getMois()); 
			}
			if(result1.get(i).getNiveauBlessure().equals("dangereux"))
			 {
				nbrBlesserParMois.setNbrBlessureDanger(result1.get(i).getNbrBlesser());	
				
			 }else{
					nbrBlesserParMois.setNbrBlessureMineur(result1.get(i).getNbrBlesser());	
					
				 }
			 nbpmn = new NbrBlesserParMoisNiveau();
			if(i+1<result1.size())
			 {
			nbpmn.setMois(result1.get(i+1).getMois());
			 }
			if(result1.get(i).getMois().equals(nbpmn.getMois()) )
			 {	
				continue;
			 }else
			 {
				 listNombreBlesserParMois.add(nbrBlesserParMois);		
			}
		}
		listNombreBlesserParMois.forEach(r1->{
			if(r1.getNbrBlessureDanger()== null) {
				r1.setNbrBlessureDanger((long) 0);
			}
			if(r1.getNbrBlessureMineur()== null) {
				r1.setNbrBlessureMineur((long) 0);
			}
		});
		
		File file = ResourceUtils.getFile("classpath:nbrblesserparmois.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listNombreBlesserParMois);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\nbrblesserparmois.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\nbrblesserparmois.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\nbrblesserparmois.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\nbrblesserparmois.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\nbrblesserparmois.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportNbrMortsParDate(String reportFormat) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrMortsParDate> listNombreMortsParDate = arepository.listNbrMortsParDate();
		File file = ResourceUtils.getFile("classpath:nbrmortspardate.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listNombreMortsParDate);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\nbrmortspardate.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\nbrmortspardate.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\nbrmortspardate.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\nbrmortspardate.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\nbrmortspardate.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportEvaluationNbrAccidentCollisionBusParAnner(String reportFormat, int d1,int d2) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrAccidentVictimeParAnner> nbrAccidentVictimeParAnner  = navservice.evaluationNbrAccidentCollisionBusParAnner(d1, d2); 
		File file = ResourceUtils.getFile("classpath:nbraccidentvictimeparanner.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(nbrAccidentVictimeParAnner);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\evaluationnbraccidentcollisionbusparanner.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\evaluationnbraccidentcollisionbusparanner.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\evaluationnbraccidentcollisionbusparanner.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\evaluationnbraccidentcollisionbusparanner.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\evaluationnbraccidentcollisionbusparanner.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportEvaluationNbrAccidentRouteBusParAnner(String reportFormat, int d1,int d2) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrAccidentVictimeParAnner> nbrAccidentVictimeParAnner  = navservice.evaluationNbrAccidentRouteBusParAnner(d1, d2); 
		File file = ResourceUtils.getFile("classpath:nbraccidentvictimeparanner.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(nbrAccidentVictimeParAnner);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\evaluationnbraccidentroutebusparanner.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\evaluationnbraccidentroutebusparanner.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\evaluationnbraccidentroutebusparanner.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\evaluationnbraccidentroutebusparanner.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\evaluationnbraccidentroutebusparanner.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportEvaluationNbrAccidentCollisionMetrosParAnner(String reportFormat, int d1,int d2) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrAccidentVictimeParAnner> nbrAccidentVictimeParAnner  = navservice.evaluationNbrAccidentCollisionMetrosParAnner(d1, d2); 
		File file = ResourceUtils.getFile("classpath:nbraccidentvictimeparanner.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(nbrAccidentVictimeParAnner);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\evaluationnbraccidentcollisionmetrosparanner.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\evaluationnbraccidentcollisionmetrosparanner.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\evaluationnbraccidentcollisionmetrosparanner.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\evaluationnbraccidentcollisionmetrosparanner.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\evaluationnbraccidentcollisionmetrosparanner.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportEvaluationNbrAccidentRouteMetrosParAnner(String reportFormat, int d1,int d2) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrAccidentVictimeParAnner> nbrAccidentVictimeParAnner  = navservice.evaluationNbrAccidentRouteMetrosParAnner(d1, d2); 
		File file = ResourceUtils.getFile("classpath:nbraccidentvictimeparanner.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(nbrAccidentVictimeParAnner);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\evaluationnbraccidentroutemetrosparanner.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\evaluationnbraccidentroutemetrosparanner.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\evaluationnbraccidentroutemetrosparanner.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\evaluationnbraccidentroutemetrosparanner.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\evaluationnbraccidentroutemetrosparanner.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportEvaluationNbrAccidentCollisionTgmParAnner(String reportFormat, int d1,int d2) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrAccidentVictimeParAnner> nbrAccidentVictimeParAnner  = navservice.evaluationNbrAccidentTgmCollisionParAnner(d1, d2);
		File file = ResourceUtils.getFile("classpath:nbraccidentvictimeparanner.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(nbrAccidentVictimeParAnner);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\evaluationnbraccidentcollisiontgmparanner.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\evaluationnbraccidentcollisiontgmparanner.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\evaluationnbraccidentcollisiontgmparanner.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\evaluationnbraccidentcollisiontgmparanner.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\evaluationnbraccidentcollisiontgmparanner.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
	
	public String exportReportEvaluationNbrAccidentRouteTgmParAnner(String reportFormat, int d1,int d2) throws FileNotFoundException, JRException{
		String path="C:\\Users\\ThinkPad\\Desktop\\Report";
		List<NbrAccidentVictimeParAnner> nbrAccidentVictimeParAnner  = navservice.evaluationNbrAccidentTgmRouteParAnner(d1, d2);
		File file = ResourceUtils.getFile("classpath:nbraccidentvictimeparanner.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(nbrAccidentVictimeParAnner);
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("createdBy", "transtu");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\evaluationnbraccidentroutetgmparanner.html");
		}
        if(reportFormat.equalsIgnoreCase("pdf")) {
        	JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\evaluationnbraccidentroutetgmparanner.pdf");
		}
        if(reportFormat.equalsIgnoreCase("csv")) {
        	JRCsvExporter exporter = new JRCsvExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(path+"\\evaluationnbraccidentroutetgmparanner.csv")));
        	SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
        	configuration.setWriteBOM(Boolean.TRUE);
        	configuration.setRecordDelimiter("\r\n");
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
		}
        if(reportFormat.equalsIgnoreCase("excel")) {
        	JRXlsxExporter exporter = new JRXlsxExporter();
        	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        	File outputFile = new File(path+"\\evaluationnbraccidentroutetgmparanner.xlsx");
        	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration(); 
        	configuration.setDetectCellType(true);
        	configuration.setCollapseRowSpan(false);
        	exporter.setConfiguration(configuration);
        	exporter.exportReport();
             
             }
        if(reportFormat.equalsIgnoreCase("docx")) {
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(path+"\\evaluationnbraccidentroutetgmparanner.docx")));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();

        	export.setConfiguration(config);            
        	export.exportReport();
            }
		return "report généré dans l'emplacement:" +path;
	}
}
