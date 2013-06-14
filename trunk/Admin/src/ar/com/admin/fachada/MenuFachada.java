package ar.com.admin.fachada;

import java.util.List;

import ar.com.admin.dao.MenuDAO;
import ar.com.admin.negocio.ItemMenu;

public class MenuFachada implements IMenuFachada {

	private MenuDAO menuDAO;

	/**
	 * Constructor por defecto.
	 * 
	 */
	public MenuFachada() {
	}

	public MenuFachada(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	public List<ItemMenu> getItemsMenu(String pRol) {
		return menuDAO.getItemsMenu(pRol);

	}

}
