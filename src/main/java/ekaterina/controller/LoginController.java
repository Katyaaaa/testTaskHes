package ekaterina.controller;

import ekaterina.pojo.*;
import ekaterina.repository.UserAccountRepository;
import ekaterina.repository.UserRoleRepository;
import ekaterina.repository.UserStatusRepository;
import ekaterina.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.logging.Logger;

@Controller
@RequestMapping("/login")
public class LoginController {

	private static Logger log = Logger.getLogger("LoginController");

	@Autowired
	UserAccountService userAccountService;

	@Autowired
	UserStatusRepository userStatusRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@GetMapping
	public ModelAndView showLoginView(){
		ModelAndView view = new ModelAndView();
		view.setViewName("login");
		UserAccount testUser = userAccountService.findByUserName("admin_admin");
		if (testUser == null) mockUser();
		return view;
	}

	private void mockUser(){
		UserAccount userAccount = new UserAccount();
		UserRole userRole = new UserRole();
		UserStatus status = new UserStatus();
		status.setStatusType(StatusType.ACTIVE);
		userRole.setRoleType(RoleType.ADMIN);
		userAccount.setUserRole(userRole);
		userAccount.setCreatedAt(new Date());
		userAccount.setFirstName("adminFirstName");
		userAccount.setLastName("adminLastName");
		userAccount.setPassword("admin");
		userAccount.setUserStatus(status);
		userAccount.setUserName("admin");
		if (userRoleRepository.findByRoleType(RoleType.ADMIN) == null)
			userRoleRepository.add(userRole);
		if(userStatusRepository.findByStatusType(StatusType.ACTIVE) == null)
		userStatusRepository.add(status);
		log.info("mockUser looking for status ="+ userStatusRepository.findByStatusType(StatusType.ACTIVE));
		userAccountService.add(userAccount);
		log.info("Saved mockUserAccount: "+ userAccount.getUserName());
	}


}
