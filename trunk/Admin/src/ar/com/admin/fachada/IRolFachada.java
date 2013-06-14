package ar.com.admin.fachada;

import java.util.List;

import ar.com.admin.dto.ItemMenuDTO;
import ar.com.admin.dto.RolDTO;
import ar.com.admin.negocio.ItemMenu;
import ar.com.admin.negocio.Rol;
import ar.com.admin.negocio.exception.AccesoDenegadoException;

public interface IRolFachada {

	public List<Rol> getRoles();

	public Rol getRol(Long id);

	public List<ItemMenu> recuperarMenues();

	public List<ItemMenuDTO> recuperarMenuesDTO();

	public Rol recuperarRol(long idRol);

	public RolDTO recuperarRolDTO(long idRol);

	public void verificarMenu(String pNombreMenu, Rol pRol)
			throws AccesoDenegadoException;

	public List<RolDTO> getRolesDTO();

	public RolDTO getRolAdministrador();

}
