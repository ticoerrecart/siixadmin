package ar.com.admin.providers;

import java.util.ArrayList;

import ar.com.admin.dto.RolDTO;
import ar.com.admin.dto.UsuarioDTO;
import ar.com.admin.negocio.ItemMenu;
import ar.com.admin.negocio.Rol;
import ar.com.admin.negocio.Usuario;

public abstract class ProviderDominio {

	public static Usuario getUsuario(UsuarioDTO usuarioDTO, Rol rol) {

		Usuario usuario = new Usuario();
		usuario.setHabilitado(usuarioDTO.isHabilitado());
		usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
		usuario.setPassword(usuarioDTO.getPassword());
		usuario.setRol(rol);

		return usuario;
	}

	public static Usuario getUsuario(Usuario usuario, UsuarioDTO usuarioDTO,
			Rol rol) {

		usuario.setHabilitado(usuarioDTO.isHabilitado());
		usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
		usuario.setPassword(usuarioDTO.getPassword());
		usuario.setRol(rol);

		return usuario;
	}

	public static Rol getRol(RolDTO rolDTO) {

		Rol rol = new Rol();
		rol.setRol(rolDTO.getRol());
		rol.setMenues(new ArrayList<ItemMenu>());

		return rol;
	}

}
