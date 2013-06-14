package ar.com.admin.fachada;

import java.util.List;

import ar.com.admin.dto.UsuarioDTO;
import ar.com.admin.negocio.Usuario;

public interface IUsuarioFachada {

	public List<Usuario> getUsuarios();

	public List<UsuarioDTO> getUsuariosDTO();

	public Usuario getUsuario(Long id);

	public UsuarioDTO getUsuarioDTO(Long id);
}
