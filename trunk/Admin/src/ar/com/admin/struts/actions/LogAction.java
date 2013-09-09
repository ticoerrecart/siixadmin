package ar.com.admin.struts.actions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.com.admin.utils.MyLogger;

public class LogAction extends DispatchAction {

	private byte[] getByteArrayFromFile(File file) throws IOException {

		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];

		for (int readNum; (readNum = fis.read(buf)) != -1;) {
			bos.write(buf, 0, readNum); // no doubt here is 0
		}

		return bos.toByteArray();
	}

	private boolean esUnFileProhibido(String fileName) {
		String filesProhibidos = MyLogger.getResourceBundle().getString(
				"files.prohibidos");

		for (String fileProhibido : filesProhibidos.split(",")) {
			if (fileName.indexOf(fileProhibido) > 0) {
				return true;
			}
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public ActionForward verLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {

			ServletOutputStream out = response.getOutputStream();
			String fileName = request.getParameter("file");// "/usr/local/tomcat-7.0.6/logs/catalina.out";
			if (esUnFileProhibido(fileName)) {
				out.write("Prohibido el acceso para ver éste archivo."
						.getBytes());
			} else {
				if (fileName == null || "".equals(fileName)) {
					out.write("Ningún archivo seleccionado para ver."
							.getBytes());
				} else {
					File file = new File(fileName);
					if (!file.exists()) {
						String s = "El archivo '" + fileName + "' no existe.";
						out.write(s.getBytes());
					} else {
						if (file.isDirectory()) {
							String s = "El archivo '" + fileName
									+ "' es un directorio.";
							out.write(s.getBytes());
						} else {
							if (file.length() == 0) {
								String s = "El archivo '" + fileName
										+ "' tiene 0 bytes.";
								out.write(s.getBytes());
							} else {
								byte[] bytes = getByteArrayFromFile(file);

								// Lo muestro en la salida del response
								response.setContentType("text/plain");
								// response.setContentLength(baos.size());
								out.write(bytes, 0, bytes.length);

							}
						}
					}
				}
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			// strForward = "errorLogin";
		}

		return null;
	}

	private boolean esElLogDeUnaAplicacion(String[] nombresLog,
			String nombreFile) {
		for (String nombre : nombresLog) {
			if (nombre.trim().equalsIgnoreCase(nombreFile)) {
				return true;
			}
		}
		return false;
	}

	private void log(String path, HttpServletRequest request) {
		int lastIndex = path.lastIndexOf(File.separator);
		String fileName = path.substring(0, lastIndex + 1);// "/usr/local/tomcat-7.0.6/logs/";
		File file = new File(fileName);

		if (file.exists() && file.isDirectory()) {
			List<File> listFiles = new ArrayList<File>();
			Map<String, String> listFileSize = new HashMap<String, String>();
			Map<String, String> listFileModified = new HashMap<String, String>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			for (File file2 : file.listFiles()) {
				String[] nombresLogs = MyLogger.getResourceBundle()
						.getString("applications.log").split(",");
				if ("catalina.out".equalsIgnoreCase(file2.getName())
						|| esElLogDeUnaAplicacion(nombresLogs, file2.getName())) {
					listFiles.add(0, file2);
				} else {
					listFiles.add(file2);
				}

				// cargo el fileSize
				if (file2.isDirectory()) {
					listFileSize.put(file2.getName(), "DIR");
				} else {
					listFileSize.put(file2.getName(),
							String.valueOf(file2.length()) + " bytes");
				}

				// cargo la fecha de modificaciÃ³n
				listFileModified.put(file2.getName(),
						sdf.format((file2.lastModified())));

			}

			Collections.sort(listFiles, new Comparator<File>() {
				public int compare(File f1, File f2) {
					// return
					// o2.getScores().get(0).compareTo(o1.getScores().get(0));
					long d1 = f1.lastModified();
					long d2 = f2.lastModified();
					if (d1 == d2) {
						return 0;
					} else {
						if (d1 < d2) {
							return 1;
						}
						return -1;
					}
				}
			});
			request.setAttribute("files", listFiles);
			request.setAttribute("filesSize", listFileSize);
			request.setAttribute("filesModified", listFileModified);
			MyLogger.log("Se encontraron " + file.listFiles().length
					+ " archivos.");
		} else {
			request.setAttribute("error", "El directorio '" + path
					+ "' no existe o no es un directorio.");
		}
	}

	public ActionForward log(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String success = "logSuccess";
		String path = MyLogger.getResourceBundle().getString("logger.path");
		log(path, request);
		return mapping.findForward(success);
	}

	public ActionForward logBD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String success = "logSuccess";
		String path = MyLogger.getResourceBundle().getString("bd.backups.path");
		log(path, request);
		return mapping.findForward(success);
	}
}
