package ar.com.admin.struts.actions.forms;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class ReporteForm extends ActionForm implements Serializable {

	private FormFile file;

	private Long idReporte;

	private String sistema;

	private String nombreReporte;

	private String nombreReportePadre;

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public Long getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(Long idReporte) {
		this.idReporte = idReporte;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getNombreReporte() {
		return nombreReporte;
	}

	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	public String getNombreReportePadre() {
		return nombreReportePadre;
	}

	public void setNombreReportePadre(String nombreReportePadre) {
		this.nombreReportePadre = nombreReportePadre;
	}

}
