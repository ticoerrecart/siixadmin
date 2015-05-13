package ar.com.admin.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.admin.interfaces.IReporte;

@SuppressWarnings("deprecation")
public class ReportesDAO extends HibernateDaoSupport {

	public List<IReporte> obtenerReportes() {
		Criteria criteria = getSession().createCriteria(IReporte.class);
		criteria.addOrder(Order.asc("nombreReportePadre"));
		criteria.addOrder(Order.asc("nombreReporte"));

		return criteria.list();
	}

	public void actualizarReporte(Long idReporte, InputStream is, Class clase)
			throws IOException {
		IReporte reporte = (IReporte) this.getHibernateTemplate().get(clase,
				idReporte);
		Blob fileBlob = Hibernate.createBlob(is);
		reporte.setArchivoReporte(fileBlob);
		this.getHibernateTemplate().saveOrUpdate(reporte);
	}

	public Long insertarReporte(InputStream is, IReporte reporte,
			String nombreReporte, String nombreReportePadre) throws IOException {

		Blob fileBlob = Hibernate.createBlob(is);
		reporte.setIdReporte(null);
		reporte.setArchivoReporte(fileBlob);
		reporte.setNombreReporte(nombreReporte);
		reporte.setNombreReportePadre(nombreReportePadre);

		this.getHibernateTemplate().save(reporte);
		return reporte.getIdReporte();
	}
}
