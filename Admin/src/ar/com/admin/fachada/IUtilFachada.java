package ar.com.admin.fachada;

import java.io.UnsupportedEncodingException;
import java.util.Set;

public interface IUtilFachada {

	public String execute(String sql) throws UnsupportedEncodingException;

	public Set<String> getAllMappedClasses();
}
