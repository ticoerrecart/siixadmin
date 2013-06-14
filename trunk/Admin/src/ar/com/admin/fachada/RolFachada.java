package ar.com.admin.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.com.admin.dao.RolDAO;
import ar.com.admin.dao.UsuarioDAO;
import ar.com.admin.dto.ItemMenuDTO;
import ar.com.admin.dto.RolDTO;
import ar.com.admin.negocio.ItemMenu;
import ar.com.admin.negocio.Rol;
import ar.com.admin.negocio.exception.AccesoDenegadoException;
import ar.com.admin.providers.ProviderDTO;

public class RolFachada implements IRolFachada {

	private RolDAO rolDAO;

	private UsuarioDAO usuarioDAO;

	public RolFachada() {
	}

	public RolFachada(RolDAO elRolDAO, UsuarioDAO elUsuarioDAO) {
		this.rolDAO = elRolDAO;
		this.usuarioDAO = elUsuarioDAO;
	}

	public List<Rol> getRoles() {
		return rolDAO.getRoles();

	}

	public Rol getRol(Long id) {
		return rolDAO.getRol(id);

	}

	public List<ItemMenu> recuperarMenues() {
		return rolDAO.recuperarMenues();

	}

	public List<ItemMenuDTO> recuperarMenuesDTO() {
		List<ItemMenuDTO> menuesDTO = new ArrayList<ItemMenuDTO>();
		List<ItemMenu> menues = rolDAO.recuperarMenues();
		for (ItemMenu itemMenu : menues) {
			menuesDTO.add(ProviderDTO.getItemMenuDTO(itemMenu));
		}
		return menuesDTO;

	}

	public Rol recuperarRol(long idRol) {
		return rolDAO.recuperarRol(idRol);

	}

	public RolDTO recuperarRolDTO(long idRol) {
		Rol rol = rolDAO.recuperarRol(idRol);

		return ProviderDTO.getRolDTO(rol);
	}

	private void getItemsMenues(Rol rol, List<ItemMenuDTO> menues) {

		HashMap<Long, String> hashMenu = new HashMap<Long, String>();

		for (ItemMenuDTO itemMenu : menues) {
			if (itemMenu != null) {
				ItemMenu item = rolDAO.getItemMenu(itemMenu.getId());
				rol.getMenues().add(item);

				ItemMenu itemPadre = item.getPadre();

				while (itemPadre != null) {

					if (hashMenu.get(itemPadre.getId()) == null) {
						rol.getMenues().add(itemPadre);
						hashMenu.put(itemPadre.getId(), "S");
						itemPadre = itemPadre.getPadre();
					} else {
						itemPadre = null;
					}
				}
			}
		}

	}

	public void verificarMenu(String pNombreMenu, Rol pRol)
			throws AccesoDenegadoException {

		rolDAO.verificarMenu(pNombreMenu, pRol);
	}

	public List<RolDTO> getRolesDTO() {
		List<RolDTO> listaRolesDTO = new ArrayList<RolDTO>();
		List<Rol> listaRoles = rolDAO.getRoles();

		for (Rol rol : listaRoles) {
			listaRolesDTO.add(ProviderDTO.getRolDTO(rol));
		}

		return listaRolesDTO;

	}

	public RolDTO getRolAdministrador() {
		Rol rolAdministrador = rolDAO.getRolAdministrador();
		return ProviderDTO.getRolDTO(rolAdministrador);

	}
}
