package ar.com.admin.fachada;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import ar.com.admin.interfaces.IReporte;

public interface IReportesFachada {

	public List<IReporte> obtenerReportes();

	public void actualizarReporte(Long idReporte, InputStream is, Class clase)
			throws IOException;
}
