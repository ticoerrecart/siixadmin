package ar.com.admin.providers;

import java.util.ArrayList;
import java.util.List;

import ar.com.admin.dto.ItemMenuDTO;
import ar.com.admin.dto.RolDTO;
import ar.com.admin.dto.UsuarioDTO;
import ar.com.admin.negocio.ItemMenu;
import ar.com.admin.negocio.Rol;
import ar.com.admin.negocio.Usuario;
import ar.com.siif.dto.EntidadDTO;
import ar.com.siif.dto.LocalidadDTO;
import ar.com.siif.dto.PeriodoDTO;
import ar.com.siif.negocio.Entidad;
import ar.com.siif.negocio.Localidad;
import ar.com.siif.negocio.Periodo;

public abstract class ProviderDTO {

	public static EntidadDTO getEntidadDTO(Entidad entidad) {

		EntidadDTO entidadDTO = new EntidadDTO();

		entidadDTO.setId(entidad.getId());
		entidadDTO.setNombre(entidad.getNombre());
		entidadDTO.setDireccion(entidad.getDireccion());
		entidadDTO.setTelefono(entidad.getTelefono());
		entidadDTO.setEmail(entidad.getEmail());
		entidadDTO.setLocalidad(ProviderDTO.getLocalidadDTO(entidad
				.getLocalidad()));
		entidadDTO.setTipoEntidadDesc(entidad.getTipoEntidad());
		entidadDTO.setTipoEntidad(entidad.getIdTipoEntidad());
		entidadDTO.setNroMatricula(entidad.getNroMatricula());
		entidadDTO.setCuit(entidad.getCuit());
		entidadDTO.setCuil(entidad.getCuil());
		entidadDTO.setDni(entidad.getDni());
		entidadDTO.setTipoDocumento(entidad.getTipoDocumento());
		entidadDTO.setCodigoPostal(entidad.getCodigoPostal());

		return entidadDTO;
	}

	public static LocalidadDTO getLocalidadDTO(Localidad localidad) {

		LocalidadDTO localidadDTO = new LocalidadDTO();

		localidadDTO.setId(localidad.getId());
		localidadDTO.setNombre(localidad.getNombre());

		return localidadDTO;
	}

	public static PeriodoDTO getPeriodoDTO(Periodo periodo) {

		PeriodoDTO periodoDTO = new PeriodoDTO();

		periodoDTO.setId(periodo.getId());
		periodoDTO.setPeriodo(periodo.getPeriodo());

		return periodoDTO;
	}

	public static UsuarioDTO getUsuarioDTO(Usuario usuario) {

		UsuarioDTO usuarioDTO = new UsuarioDTO();

		usuarioDTO.setId(usuario.getId());
		usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
		usuarioDTO.setPassword(usuario.getPassword());
		usuarioDTO.setRol(ProviderDTO.getRolDTO(usuario.getRol()));
		usuarioDTO.setHabilitado(usuario.isHabilitado());

		return usuarioDTO;
	}

	public static RolDTO getRolDTO(Rol rol) {

		List<ItemMenuDTO> listaMenuesDTO = new ArrayList<ItemMenuDTO>();
		RolDTO rolDTO = new RolDTO();

		rolDTO.setId(rol.getId());
		rolDTO.setRol(rol.getRol());

		for (ItemMenu menu : rol.getMenues()) {
			listaMenuesDTO.add(ProviderDTO.getItemMenuDTO(menu));
		}
		rolDTO.setMenues(listaMenuesDTO);

		return rolDTO;
	}

	public static ItemMenuDTO getItemMenuDTO(ItemMenu menu) {
		return ProviderDTO.getItemMenuDTO(menu, null);
	}

	private static ItemMenuDTO getItemMenuDTO(ItemMenu menu, ItemMenuDTO padre) {

		if (menu != null) {
			List<ItemMenuDTO> listaMenuDTO = new ArrayList<ItemMenuDTO>();
			ItemMenuDTO menuDTO = new ItemMenuDTO();

			menuDTO.setId(menu.getId());
			menuDTO.setItem(menu.getItem());
			menuDTO.setOrden(menu.getOrden());
			menuDTO.setPadre(padre);
			menuDTO.setUrl(menu.getUrl());

			for (ItemMenu menuHijo : menu.getHijos()) {
				listaMenuDTO.add(ProviderDTO.getItemMenuDTO(menuHijo, menuDTO));
			}
			menuDTO.setHijos(listaMenuDTO);

			return menuDTO;
		} else {
			return null;
		}
	}

}
