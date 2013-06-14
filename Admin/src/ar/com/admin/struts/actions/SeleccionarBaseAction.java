package ar.com.admin.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ar.com.admin.utils.MyLogger;

public class SeleccionarBaseAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String accion = request.getParameter("accion");
		String titulo = request.getParameter("titulo");
		String[] nombresAplicaciones = MyLogger.getResourceBundle()
				.getString("applications.names").split(",");

		request.setAttribute("aplicaciones", nombresAplicaciones);
		request.setAttribute("accion", accion);
		request.setAttribute("titulo", titulo);
		return mapping.findForward("exitoSeleccionarBase");
	}

}
