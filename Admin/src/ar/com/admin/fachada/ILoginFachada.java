package ar.com.admin.fachada;

import ar.com.admin.dto.UsuarioDTO;
import ar.com.admin.negocio.Usuario;
import ar.com.admin.negocio.exception.NegocioException;

public interface ILoginFachada {

	public UsuarioDTO login(String usuario, String password)
			throws NegocioException;

	public Usuario getUsuario(Long id);

}
