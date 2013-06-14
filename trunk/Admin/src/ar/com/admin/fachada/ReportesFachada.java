package ar.com.admin.fachada;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import ar.com.admin.dao.ReportesDAO;
import ar.com.admin.interfaces.IReporte;

public class ReportesFachada implements IReportesFachada {

	private ReportesDAO reportesDAO;

	public ReportesFachada() {
	}

	public ReportesFachada(ReportesDAO pReportesDAO) {

		this.reportesDAO = pReportesDAO;
	}

	public List<IReporte> obtenerReportes() {
		return reportesDAO.obtenerReportes();
	}

	public void actualizarReporte(Long idReporte, InputStream is, Class clase)
			throws IOException {
		reportesDAO.actualizarReporte(idReporte, is, clase);
	}
}
