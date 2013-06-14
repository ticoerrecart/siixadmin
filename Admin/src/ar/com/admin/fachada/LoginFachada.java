package ar.com.admin.fachada;

import ar.com.admin.dao.LoginDAO;
import ar.com.admin.dto.UsuarioDTO;
import ar.com.admin.negocio.Usuario;
import ar.com.admin.negocio.exception.NegocioException;
import ar.com.admin.providers.ProviderDTO;

public class LoginFachada implements ILoginFachada {

	private LoginDAO loginDAO;

	public LoginFachada() {
	}

	public LoginFachada(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public UsuarioDTO login(String usuario, String password)
			throws NegocioException {

		Usuario usr = loginDAO.login(usuario, password);
		UsuarioDTO usrDTO = ProviderDTO.getUsuarioDTO(usr);

		return usrDTO;
	}

	public Usuario getUsuario(Long id) {

		return loginDAO.getUsuario(id);
	}
}
