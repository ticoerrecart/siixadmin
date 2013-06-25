package ar.com.admin.fachada;

import java.util.Set;

public interface IUtilFachada {

	public String execute(String sql);

	public Set<String> getAllMappedClasses();
}
