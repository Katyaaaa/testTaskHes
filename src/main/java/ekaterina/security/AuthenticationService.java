package ekaterina.security;

import ekaterina.pojo.StatusType;
import ekaterina.pojo.UserAccount;
import ekaterina.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.logging.Logger;

@Service("authService")
public class AuthenticationService implements UserDetailsService {

	private static Logger log = Logger.getLogger("AuthenticationService");

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) {
		UserAccount userAccount = userAccountRepository.findByUserName(userName);
		if (userAccount == null || userAccount.getUserStatus().getStatusType().equals(StatusType.INACTIVE)) {
			throw new UsernameNotFoundException(userName);
		}
		User user = new User(userAccount.getUserName(), userAccount.getPassword(),
				Arrays.asList(new SimpleGrantedAuthority("ROLE_" + userAccount.getUserRole().getRoleType())));
		log.info("Loaded user from repo:"+ user);

		return user;
	}



}
