package ar.com.admin.interfaces;

import java.sql.Blob;

public interface IReporte {
	public void setArchivoReporte(Blob archivoReporte);

	public Long getIdReporte();

	public void setIdReporte(Long id);

	public void setNombreReporte(String nombreReporte);

	public void setNombreReportePadre(String nombreReportePadre);
}
