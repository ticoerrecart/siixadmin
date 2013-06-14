package ar.com.admin.fachada;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import ar.com.admin.dao.UtilDAO;

public class UtilFachada implements IUtilFachada {

	private UtilDAO utilDAO;

	public UtilFachada() {
	}

	public UtilFachada(UtilDAO utilDAO) {
		this.utilDAO = utilDAO;
	}

	public String execute(String sql) {
		String strSelect = "select";
		int select = sql.toLowerCase().indexOf(strSelect);
		int from = sql.toLowerCase().indexOf("from");
		if (sql != null && (select > -1 || from == 0)) {
			sql = sql.replace(strSelect + " *", "");

			List results = utilDAO.executeSelect(sql);
			StringBuffer s = new StringBuffer("");
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();
				try {
					showFields(object, object.getClass(), s);
					s.append("<br/><br/>");
				} catch (Exception e) {
					s.append(" | " + e.toString());
				}
			}
			return s.toString();
		} else {
			return String.valueOf(utilDAO.executeUpdateDelete(sql));
		}
	}

	public void showFields(Object o, Class<?> clazz, StringBuffer s)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		if (!"java.lang.Object".equalsIgnoreCase(clazz.getSuperclass()
				.getName())) {
			showFields(o, clazz.getSuperclass(), s);
		}

		for (Field field : clazz.getDeclaredFields()) {
			s.append(field.getName() + ":'"
					+ PropertyUtils.getProperty(o, field.getName()) + "', ");
		}


	}

}
