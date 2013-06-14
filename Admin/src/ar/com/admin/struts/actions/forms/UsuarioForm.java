package ar.com.admin.struts.actions.forms;

import org.apache.struts.action.ActionForm;

import ar.com.admin.dto.UsuarioDTO;
import ar.com.admin.negocio.Usuario;

public class UsuarioForm extends ActionForm {

	private Usuario usuario;

	private String contrasenia;

	private String idRol;

	private String idEntidad;

	private UsuarioDTO usuarioDTO;

	public UsuarioForm() {
		usuario = new Usuario();
		usuarioDTO = new UsuarioDTO();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getIdRol() {
		return idRol;
	}

	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	public String getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(String idEntidad) {
		this.idEntidad = idEntidad;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

}
