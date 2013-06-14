package ar.com.admin.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.admin.dao.UsuarioDAO;
import ar.com.admin.dto.UsuarioDTO;
import ar.com.admin.negocio.Usuario;
import ar.com.admin.providers.ProviderDTO;

public class UsuarioFachada implements IUsuarioFachada {

	private UsuarioDAO usuarioDAO;
	private IRolFachada rolFachada;

	public UsuarioFachada() {
	}

	public UsuarioFachada(UsuarioDAO usuarioDAO, IRolFachada pRolFachada) {
		this.usuarioDAO = usuarioDAO;
		this.rolFachada = pRolFachada;
	}

	public List<Usuario> getUsuarios() {
		return usuarioDAO.getUsuarios();
	}

	public List<UsuarioDTO> getUsuariosDTO() {
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
		List<Usuario> usuarios = usuarioDAO.getUsuarios();
		for (Usuario usuario : usuarios) {
			usuariosDTO.add(ProviderDTO.getUsuarioDTO(usuario));
		}
		return usuariosDTO;
	}

	public Usuario getUsuario(Long id) {
		return usuarioDAO.getUsuario(id);
	}

	public UsuarioDTO getUsuarioDTO(Long id) {
		Usuario usuario = usuarioDAO.getUsuario(id);
		return ProviderDTO.getUsuarioDTO(usuario);
	}
}
