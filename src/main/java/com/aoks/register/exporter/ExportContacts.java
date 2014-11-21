package com.aoks.register.exporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.aoks.enterprise.control.bean.IndividualPartnerBean;
import com.aoks.enterprise.control.bean.LegalPartnerBean;
import com.aoks.register.control.bean.IndividualRegisterBean;
import com.aoks.register.control.bean.LegalRegisterBean;
import com.aoks.register.model.IndividualRegister;
import com.aoks.register.model.LegalRegister;
import com.aoks.utils.date.DateUtils;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public class ExportContacts {
	
	private String contentType;

	public StreamedContent exportIndividualBeans(List<IndividualPartnerBean> individualBeans){

		if(individualBeans.isEmpty())
			return null;
			
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet sheet = workBook.createSheet("Exportados - Contatos Pessoa Fisica");
		
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(new File("\\export.xls"));
			
			Locale defaultLocaleHeader = new Locale("pt", "BR");
			ResourceBundle bundle = ResourceBundle.getBundle("META-INF/MessageBundle", defaultLocaleHeader);
			
			HSSFRow rowHeader = sheet.createRow(0);
			rowHeader.createCell(0).setCellValue(bundle.getString("model_name"));
			rowHeader.createCell(1).setCellValue(bundle.getString("model_genre"));
			rowHeader.createCell(2).setCellValue(bundle.getString("model_birth"));
			rowHeader.createCell(3).setCellValue(bundle.getString("model_cpf"));
			rowHeader.createCell(4).setCellValue(bundle.getString("model_rg"));
			rowHeader.createCell(5).setCellValue(bundle.getString("model_marital_status"));
			rowHeader.createCell(6).setCellValue(bundle.getString("model_naturality"));
			rowHeader.createCell(7).setCellValue(bundle.getString("model_phone_number"));
			rowHeader.createCell(8).setCellValue(bundle.getString("model_fathername"));
			rowHeader.createCell(9).setCellValue(bundle.getString("model_mothername"));
			rowHeader.createCell(10).setCellValue(bundle.getString("model_email"));
			rowHeader.createCell(11).setCellValue(bundle.getString("model_facebook"));
			rowHeader.createCell(12).setCellValue(bundle.getString("model_twitter"));
			
			int i = 1;
			for(IndividualPartnerBean bean : individualBeans){
				HSSFRow row = sheet.createRow(i);
				
				IndividualRegisterBean individualRegisterBean = (IndividualRegisterBean) new IndividualRegisterBean().load((IndividualRegister) bean.getModel().getBehavior().getRegister());

				row.createCell(0).setCellValue(individualRegisterBean.getName());
				
				String genre = individualRegisterBean.getGenre();
				row.createCell(1).setCellValue((genre != null) ? bundle.getString(genre) : "");
				
				row.createCell(2).setCellValue(DateUtils.format8(individualRegisterBean.getBirth()));
				row.createCell(3).setCellValue(individualRegisterBean.getCpf());
				row.createCell(4).setCellValue(individualRegisterBean.getRg());
				
				String maritalStatus = individualRegisterBean.getMaritalStatus();
				row.createCell(5).setCellValue((maritalStatus != null) ? bundle.getString(maritalStatus) : "");
				
				row.createCell(6).setCellValue(individualRegisterBean.getNaturality());
				row.createCell(7).setCellValue(individualRegisterBean.getPhone().getPhoneNumber());
				row.createCell(8).setCellValue(individualRegisterBean.getFatherName());
				row.createCell(9).setCellValue(individualRegisterBean.getMotherName());
				row.createCell(10).setCellValue(individualRegisterBean.getEmail());
				row.createCell(11).setCellValue(individualRegisterBean.getFacebook());
				row.createCell(12).setCellValue(individualRegisterBean.getTwitter());
				
				i++;
			}

			workBook.write(fos);
			contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType("\\export.xls");
			return new DefaultStreamedContent(new FileInputStream("\\export.xls"), contentType, "contatos-exportados.xlsx");

		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Erro ao Exportar arquivo!");
		}finally{
			try{
				fos.flush();
				fos.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		return null;
	}
	
	public StreamedContent exportLegalBeans(List<LegalPartnerBean> legalBeans){
		
		if(legalBeans.isEmpty())
			return null;
		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet sheet = workBook.createSheet("Exportados - Contatos Pessoa Juridica");
		
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(new File("\\export.xls"));
			
			Locale defaultLocaleHeader = new Locale("pt", "BR");
			ResourceBundle bundle = ResourceBundle.getBundle("META-INF/MessageBundle", defaultLocaleHeader);
			
			HSSFRow rowHeader = sheet.createRow(0);
			rowHeader.createCell(0).setCellValue(bundle.getString("model_area_actuation"));
			rowHeader.createCell(1).setCellValue(bundle.getString("model_company_name"));
			rowHeader.createCell(2).setCellValue(bundle.getString("model_fancy_name"));
			rowHeader.createCell(3).setCellValue(bundle.getString("model_cnpj"));
			rowHeader.createCell(4).setCellValue(bundle.getString("model_inscr_estadual"));
			rowHeader.createCell(5).setCellValue(bundle.getString("model_inscr_municipal"));
			rowHeader.createCell(9).setCellValue(bundle.getString("model_phone_number"));
			rowHeader.createCell(6).setCellValue(bundle.getString("model_site"));
			rowHeader.createCell(7).setCellValue(bundle.getString("model_facebook"));
			rowHeader.createCell(8).setCellValue(bundle.getString("model_twitter"));
			
			int i = 1;
			for(LegalPartnerBean bean : legalBeans){
				HSSFRow row = sheet.createRow(i);
				
				LegalRegisterBean legalBean = (LegalRegisterBean) new LegalRegisterBean().load((LegalRegister) bean.getModel().getBehavior().getRegister());
				
				row.createCell(0).setCellValue(legalBean.getAreaActuation().getAreaActuation());
				row.createCell(1).setCellValue(legalBean.getCompanyName());
				row.createCell(2).setCellValue(legalBean.getFancyName());
				row.createCell(3).setCellValue(legalBean.getCnpj());
				row.createCell(4).setCellValue(legalBean.getInscrEstadual());
				row.createCell(5).setCellValue(legalBean.getInscrMunicipal());
				row.createCell(6).setCellValue(legalBean.getSite());
				row.createCell(7).setCellValue(legalBean.getFacebook());
				row.createCell(8).setCellValue(legalBean.getTwitter());
				row.createCell(9).setCellValue(legalBean.getPhone().getPhoneNumber());
				
				i++;
			}

			workBook.write(fos);
			contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType("\\export.xls");
			return new DefaultStreamedContent(new FileInputStream("\\export.xls"), contentType, "contatos-exportados.xlsx");
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Erro ao Exportar arquivo!");
		}finally{
			try{
				fos.flush();
				fos.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		return null;
	}
	
}
