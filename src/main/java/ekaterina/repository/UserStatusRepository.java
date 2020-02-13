package ekaterina.repository;

import ekaterina.pojo.StatusType;
import ekaterina.pojo.UserRole;
import ekaterina.pojo.UserStatus;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserStatusRepository {

	private static Logger log = Logger.getLogger("UserStatusRepository");

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public boolean add(UserStatus userStatus){
		sessionFactory.getCurrentSession().save(userStatus);
		return true;
	}

	@Transactional
	public UserStatus findByStatusType(StatusType statusType){
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("from UserStatus where statusType like :param", UserStatus.class)
					.setParameter("param", statusType)
					.getSingleResult();
		} catch(Exception e){
			return null;
		}
	}

	@Transactional
	public List<UserStatus> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from UserStatus", UserStatus.class).list();
	}

}
