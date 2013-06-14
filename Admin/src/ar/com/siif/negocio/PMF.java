package ar.com.siif.negocio;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.com.admin.providers.ProviderDTO;
import ar.com.siif.dto.PMFDTO;

@Entity
@DiscriminatorValue("PMF")
public class PMF extends Localizacion {

	public PMF() {
		super();
	}

	public PMF(String expediente, String nombre, String tipoTerreno,
			Entidad entidad) {
		super();
		this.expedientePMF = expediente;
		this.nombrePMF = nombre;
		this.tipoTerreno = tipoTerreno;
		this.productorForestal = entidad;

	}

	private String expedientePMF;

	private String nombrePMF;

	private String tipoTerreno;

	@OneToMany(mappedBy = "pmf")
	@Cascade(value = { CascadeType.SAVE_UPDATE, CascadeType.DELETE_ORPHAN })
	private List<Tranzon> tranzones;

	public String getExpedientePMF() {
		return expedientePMF;
	}

	public void setExpedientePMF(String expedientePMF) {
		this.expedientePMF = expedientePMF;
	}

	public String getNombrePMF() {
		return nombrePMF;
	}

	public String getNombreExpediente() {
		return this.getNombrePMF() + " - " + this.getExpedientePMF();
	}

	public void setNombrePMF(String nombrePMF) {
		this.nombrePMF = nombrePMF;
	}

	public String getTipoTerreno() {
		return tipoTerreno;
	}

	public void setTipoTerreno(String tipoTerrenoPMF) {
		this.tipoTerreno = tipoTerrenoPMF;
	}

	public List<Tranzon> getTranzones() {
		return tranzones;
	}

	public void setTranzones(List<Tranzon> tranzones) {
		this.tranzones = tranzones;
	}

	public PMFDTO getLocalizacionDTO() {
		PMFDTO pmfDTO = new PMFDTO();
		pmfDTO.setId(this.getId());
		pmfDTO.setExpedientePMF(this.getExpedientePMF());
		pmfDTO.setNombrePMF(this.getNombrePMF());
		pmfDTO.setTipoTerreno(this.getTipoTerreno());
		pmfDTO.setProductorForestal(ProviderDTO.getEntidadDTO(this
				.getProductorForestal()));

		return pmfDTO;
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
		for (Tranzon tranzon : this.getTranzones()) {
			if (tranzon.tieneRodal(idRodal)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean tieneMarcacion(Long idMarcacion) {
		for (Tranzon tranzon : this.getTranzones()) {
			if (tranzon.tieneMarcacion(idMarcacion)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean tieneTranzon(Long idTranzon) {
		for (Tranzon tranzon : this.getTranzones()) {
			if (tranzon.getId() == idTranzon) {
				return true;
			}
		}
		return false;
	}

	public String getNombreLocalizacion() {
		return "PMF:" + this.getNombreExpediente();
	}

}
