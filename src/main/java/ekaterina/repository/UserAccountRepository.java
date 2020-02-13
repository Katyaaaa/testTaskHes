package ekaterina.repository;

import ekaterina.pojo.UserAccount;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserAccountRepository {

	@Autowired
	private SessionFactory sessionFactory;


	public UserAccount findByUserName(String userName) {
		List<UserAccount> accounts = sessionFactory.getCurrentSession()
				.createQuery("from UserAccount where userName='" + userName + "'",
						UserAccount.class).list();
		if (accounts.size()==1) return accounts.get(0);
		else return null;
	}

	public List<UserAccount> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from UserAccount", UserAccount.class).list();
	}

	public boolean add(UserAccount account) {
		sessionFactory.getCurrentSession().save(account);
		return true;
	}

	public UserAccount findById(Long userAccountId) {
		return sessionFactory.getCurrentSession()
				.get(UserAccount.class, userAccountId);
	}

	public boolean update(UserAccount userAccount) {
		sessionFactory.getCurrentSession().update(userAccount);
		return true;
	}
}
