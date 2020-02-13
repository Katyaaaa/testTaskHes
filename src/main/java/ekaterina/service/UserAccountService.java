package ekaterina.service;

import ekaterina.pojo.*;
import ekaterina.repository.UserAccountRepository;
import ekaterina.repository.UserRoleRepository;
import ekaterina.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UserAccountService {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserAccountRepository userAccountRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@Autowired
	UserStatusRepository userStatusRepository;

	private static Logger log = Logger.getLogger("UserAccountService");


	@Transactional
	public List<UserAccount> getAllUser() {
		return  userAccountRepository.getAll();
	}


	@Transactional
	public boolean add(UserAccount account){
		if (account.getCreatedAt() == null || account.getFirstName() == null
				|| account.getLastName() == null || account.getPassword() == null
				|| account.getUserName() == null || userAccountRepository.findByUserName
				(account.getUserName()) != null) return false;
		UserRole userRole = userRoleRepository.findByRoleType(RoleType.USER);
		if (userRole == null){
			userRole = new UserRole();
			userRole.setRoleType(RoleType.USER);
			userRoleRepository.add(userRole);
		}
		UserStatus userStatus = userStatusRepository.findByStatusType(StatusType.ACTIVE);
		if (userStatus == null){
			userStatus = new UserStatus();
			userStatus.setStatusType(StatusType.ACTIVE);
			userStatusRepository.add(userStatus);
		}
		String encodedPassword = passwordEncoder.encode(account.getPassword());
		account.setPassword(encodedPassword);
		if (account.getUserStatus()== null) account.setUserRole(userRole);
		account.setUserStatus(userStatus);
		return userAccountRepository.add(account);
	}

	@Transactional
	public UserAccount findByUserName(String userName) {
		return userAccountRepository.findByUserName(userName);
	}


	@Transactional
	public UserAccount findById(Long id) {
		return userAccountRepository.findById(id);
	}

	@Transactional
	public boolean updateUser(UserAccount userAccountFromForm, String userRoleType, String userStatusType) {

		UserAccount userAccount = userAccountRepository.findById(userAccountFromForm.getId());
		if (userStatusType.equals("ACTIVE")) {
			userAccount.setUserStatus(userStatusRepository.findByStatusType(StatusType.ACTIVE));
			log.info("Right after changed the statusType="+ userAccount.getUserStatus());
		}
		else {
			userAccount.setUserStatus(userStatusRepository.findByStatusType(StatusType.INACTIVE));
		}
		log.info("update user with roleType="+userRoleType);
		if (userRoleType.equals("ADMIN")) {
			UserRole newUserRole = userRoleRepository.findByRoleType(RoleType.ADMIN);
			log.info("searching with ADMIN role in repo, found="+ newUserRole);
			userAccount.setUserRole(newUserRole);
			log.info("Right after changed the roleType="+ userAccount.getUserRole());
		}
		else {
			userAccount.setUserRole(userRoleRepository.findByRoleType(RoleType.USER));
		}
		userAccount.setUserName(userAccountFromForm.getUserName());
		userAccount.setLastName(userAccountFromForm.getLastName());
		userAccount.setFirstName(userAccountFromForm.getFirstName());
		log.info("Before sending to repo for update userAccount="+userAccount);
		return userAccountRepository.update(userAccount);
	}
}
