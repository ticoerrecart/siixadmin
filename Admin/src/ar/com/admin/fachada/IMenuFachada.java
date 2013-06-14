package ar.com.admin.fachada;

import java.util.List;

import ar.com.admin.negocio.ItemMenu;
import ar.com.admin.negocio.exception.NegocioException;

public interface IMenuFachada {

	public List<ItemMenu> getItemsMenu(String rol) throws NegocioException;
}
