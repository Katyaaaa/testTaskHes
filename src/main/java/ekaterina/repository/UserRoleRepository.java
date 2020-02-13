package ekaterina.repository;

import ekaterina.pojo.RoleType;
import ekaterina.pojo.UserAccount;
import ekaterina.pojo.UserRole;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserRoleRepository {

	@Autowired
	SessionFactory sessionFactory;

	private static Logger log = Logger.getLogger("UserRoleRepository");


	@Transactional
	public boolean add(UserRole userRole){
		sessionFactory.getCurrentSession().save(userRole);
		return true;
	}

	@Transactional
	public UserRole findByRoleType(RoleType roleType){
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("from UserRole where roleType like :param", UserRole.class)
					.setParameter("param", roleType)
					.getSingleResult();
		} catch(Exception e){
			return null;
		}
	}

	@Transactional
	public List<UserRole> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from UserRole", UserRole.class).list();
	}

}
