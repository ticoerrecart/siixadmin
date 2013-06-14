package ar.com.admin.struts.actions.forms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ar.com.admin.dto.ItemMenuDTO;
import ar.com.admin.dto.RolDTO;
import ar.com.admin.negocio.ItemMenu;
import ar.com.admin.negocio.Rol;

public class RolForm extends ActionForm {

	private Rol rol;
	private List<ItemMenu> menues;

	private RolDTO rolDTO;
	private List<ItemMenuDTO> menuesDTO;

	public RolForm() {

		rolDTO = new RolDTO();
		menuesDTO = (List<ItemMenuDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(ItemMenuDTO.class));
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		RolDTO rolParam = (RolDTO) request.getSession().getAttribute("rolF");
		if (rolParam != null) {
			rolDTO = rolParam;
		}

		menuesDTO = (List<ItemMenuDTO>) LazyList.decorate(new ArrayList(),
				FactoryUtils.instantiateFactory(ItemMenuDTO.class));
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<ItemMenu> getMenues() {
		return menues;
	}

	public void setMenues(List<ItemMenu> menues) {
		this.menues = menues;
	}

	public RolDTO getRolDTO() {
		return rolDTO;
	}

	public void setRolDTO(RolDTO rolDTO) {
		this.rolDTO = rolDTO;
	}

	public List<ItemMenuDTO> getMenuesDTO() {
		return menuesDTO;
	}

	public void setMenuesDTO(List<ItemMenuDTO> menuesDTO) {
		this.menuesDTO = menuesDTO;
	}

}
