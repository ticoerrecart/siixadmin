package ar.com.admin.struts.actions;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.web.context.WebApplicationContext;

import ar.com.admin.fachada.IReportesFachada;
import ar.com.admin.interfaces.IReporte;
import ar.com.admin.struts.actions.forms.ReporteForm;
import ar.com.admin.utils.MyLogger;

public class ReportesAction extends ValidadorAction {

	public ActionForward mostrarReporte(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "exitoMostrarReporte";
		request.setAttribute("paramForward",
				request.getParameter("paramForward"));

		return mapping.findForward(forward);
	}

	public ActionForward obtenerReportes(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = "exitoMostrarReportes";
		try {
			String sistema = request.getParameter("sistema");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesFachada reportesFachada = (IReportesFachada) ctx
					.getBean("reportes" + sistema + "Fachada");
			List<IReporte> reportes = reportesFachada.obtenerReportes();
			request.setAttribute("reportes", reportes);
			request.setAttribute("sistema", sistema);
		} catch (NoSuchBeanDefinitionException nsbe) {
			forward = "error";
			request.setAttribute("error", "No hay reportes para éste sistema.");
		} catch (Exception e) {
			forward = "error";
			request.setAttribute("error", e.getMessage());
		}

		return mapping.findForward(forward);
	}

	public ActionForward actualizarReporte(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String forward = "exitoMostrarReportes";
		WebApplicationContext ctx = getWebApplicationContext();

		ReporteForm reporteForm = (ReporteForm) form;
		if (reporteForm.getFile().getFileSize() == 0) {
			request.setAttribute("detalle", "Seleccione un reporte.");
		} else {
			if (!reporteForm.getFile().getFileName().endsWith(".jasper")) {
				request.setAttribute("detalle",
						"El reporte que está intentando subir no tiene una extensión válida.");
			} else {
				IReportesFachada reportesFachada = (IReportesFachada) ctx
						.getBean("reportes" + reporteForm.getSistema()
								+ "Fachada");

				InputStream is = reporteForm.getFile().getInputStream();
				String strClase = MyLogger.getResourceBundle().getString(
						reporteForm.getSistema() + ".claseReporte");
				if (strClase == null || "".equals(strClase)) {
					forward = "error";
					request.setAttribute(
							"error",
							"No se ha definido en la configuración la clase 'Reporte' para el sistema seleccionado.");
				} else {
					Class clase = Class.forName(strClase);
					reportesFachada.actualizarReporte(
							reporteForm.getIdReporte(), is, clase);
					request.setAttribute("detalle", "El reporte con id: "
							+ reporteForm.getIdReporte()
							+ " fue actualizado con Exito");
				}
			}
		}
		return mapping.findForward(forward);
	}

}
