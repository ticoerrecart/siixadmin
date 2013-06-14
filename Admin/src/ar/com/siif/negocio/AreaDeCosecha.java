package ar.com.siif.negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.com.admin.providers.ProviderDTO;
import ar.com.siif.dto.AreaDeCosechaDTO;

@Entity
@DiscriminatorValue("AREADECOSECHA")
public class AreaDeCosecha extends Localizacion {

	public AreaDeCosecha() {
		super();
	}

	public AreaDeCosecha(String reservaForestalArea, String nombreArea,
			String disposicionArea, String expedienteArea, Entidad entidad,
			String tipoTerreno) {
		super();
		this.reservaForestalArea = reservaForestalArea;
		this.nombreArea = nombreArea;
		this.disposicionArea = disposicionArea;
		this.expedienteArea = expedienteArea;
		this.productorForestal = entidad;
		this.tipoTerreno = tipoTerreno;
	}

	private String reservaForestalArea;

	private String nombreArea;

	private String disposicionArea;

	private String expedienteArea;

	private String tipoTerreno;

	public String getReservaForestalArea() {
		return reservaForestalArea;
	}

	public void setReservaForestalArea(String reservaForestalArea) {
		this.reservaForestalArea = reservaForestalArea;
	}

	public String getNombreArea() {
		return nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	public String getDisposicionArea() {
		return disposicionArea;
	}

	public void setDisposicionArea(String disposicionArea) {
		this.disposicionArea = disposicionArea;
	}

	public String getExpedienteArea() {
		return expedienteArea;
	}

	public void setExpedienteArea(String expedienteArea) {
		this.expedienteArea = expedienteArea;
	}

	@Override
	public AreaDeCosechaDTO getLocalizacionDTO() {

		AreaDeCosechaDTO areaDeCosechaDTO = new AreaDeCosechaDTO();
		areaDeCosechaDTO.setId(this.getId());
		areaDeCosechaDTO.setDisposicionArea(this.getDisposicionArea());
		areaDeCosechaDTO.setExpedienteArea(this.getExpedienteArea());
		areaDeCosechaDTO.setNombreArea(this.getNombreArea());
		areaDeCosechaDTO.setReservaForestalArea(this.getReservaForestalArea());
		areaDeCosechaDTO.setProductorForestal(ProviderDTO.getEntidadDTO(this
				.getProductorForestal()));
		areaDeCosechaDTO.setTipoTerreno(this.getTipoTerreno());

		return areaDeCosechaDTO;
	}

	public boolean esAreaDeCosecha() {
		return true;
	}

	@Override
	public boolean estaIncluidoGeograficamenteEn(Localizacion localizacion) {
		return false;
	}

	@Override
	public boolean esParteDeLaLocalizacion(Localizacion localizacion) {
		if (this.getId() == localizacion.getId()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean tieneRodal(Long idRodal) {
		return false;
	}

	@Override
	public boolean tieneMarcacion(Long idMarcacion) {
		return false;
	}

	@Override
	public boolean tieneTranzon(Long idTranzon) {
		return false;
	}

	@Override
	public String getNombreLocalizacion() {
		return " Area :" + this.getNombreArea();
	}

	public String getTipoTerreno() {
		return tipoTerreno;
	}

	public void setTipoTerreno(String tipoTerreno) {
		this.tipoTerreno = tipoTerreno;
	}
}
