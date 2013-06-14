package ar.com.admin.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.admin.negocio.Usuario;

public class UsuarioDAO extends HibernateDaoSupport {

	public List<Usuario> getUsuarios() {
		Criteria criteria = getSession().createCriteria(Usuario.class);
		criteria.createAlias("rol", "r");

		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.ne("r.id", 1L));

		criteria.add(conj);

		List<Usuario> usuarios = criteria.list();
		return usuarios;
	}

	public Usuario getUsuario(Long id) {
		return (Usuario) this.getHibernateTemplate().get(Usuario.class, id);

	}

}
