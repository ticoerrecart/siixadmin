package ar.com.siim.negocio;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ar.com.admin.interfaces.IReporte;

@Entity
public class Reporte implements IReporte {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long idReporte;

	@Column(nullable = false)
	public String nombreReporte;

	@Column(nullable = false)
	public Blob archivoReporte;

	public String nombreReportePadre;

	public Long getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(Long idReporte) {
		this.idReporte = idReporte;
	}

	public String getNombreReporte() {
		return nombreReporte;
	}

	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	public Blob getArchivoReporte() {
		return archivoReporte;
	}

	public void setArchivoReporte(Blob archivoReporte) {
		this.archivoReporte = archivoReporte;
	}

	public String getNombreReportePadre() {
		return nombreReportePadre;
	}

	public void setNombreReportePadre(String nombreReportePadre) {
		this.nombreReportePadre = nombreReportePadre;
	}

}
