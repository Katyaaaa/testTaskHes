package ekaterina.controller;

import ekaterina.pojo.RoleType;
import ekaterina.pojo.StatusType;
import ekaterina.pojo.UserAccount;
import ekaterina.pojo.UserStatus;
import ekaterina.repository.UserRoleRepository;
import ekaterina.repository.UserStatusRepository;
import ekaterina.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class UserAccountController {

	private static Logger log = Logger.getLogger("UserAccountController");

	@Autowired
	UserAccountService userAccountService;

	@Autowired
	UserRoleRepository userRoleRepository;

	@Autowired
	UserStatusRepository userStatusRepository;

	@GetMapping("/")
	public String userAccountListView(Model model){
		List<UserAccount> allUsers = userAccountService.getAllUser();
		log.info("All userAccounts:" + allUsers);
		model.addAttribute("userAccounts", allUsers);
		if (userStatusRepository.findByStatusType(StatusType.INACTIVE) == null) {
			UserStatus userStatus = new UserStatus();
			userStatus.setStatusType(StatusType.INACTIVE);
			userStatusRepository.add(userStatus);
		}
		return "userAccount";
	}

	@GetMapping("/{id}")
	public String  userAccountDetails(@PathVariable Long id, Model model){
		log.info("Inside the userDetailsMethod");
		model.addAttribute("userAccount", userAccountService.findById(id));
		return "userAccount";
	}

	@GetMapping("/{id}/edit")
	public String  accountChangeDataView(@PathVariable Long id, Model model){
		UserAccount userAccount = userAccountService.findById(id);
		model.addAttribute("userAccount", userAccount);
		model.addAttribute("allRoles", userRoleRepository.findAll());
		model.addAttribute("allStatuses", userStatusRepository.findAll());
		return "edit";
	}

	@PostMapping("/edit")
	public String  userAccountChangeDataFormSubmit(@ModelAttribute UserAccount userAccount, @RequestParam String userRoleType, @RequestParam String userStatusType, Model model){
		if (!userAccountService.updateUser(userAccount, userRoleType, userStatusType)) {
			model.addAttribute("unsuccessful", "Can not update user");
			return null;
		} else {
			List<UserAccount> allUsers = userAccountService.getAllUser();
			log.info("All userAccounts:" + allUsers);
			model.addAttribute("userAccounts", allUsers);
			model.addAttribute("userAccount", null);
		    return "userAccount";
		}
	}

	@GetMapping("/newAccount")
	public String newUserAccountView(Model model){
		return "newAccount";
	}

	@PostMapping("/newAccount")
	public String newUserAccountForm(Model model, @ModelAttribute UserAccount userAccount){

		userAccount.setCreatedAt(new Date());
		if (!userAccountService.add(userAccount)) {
			model.addAttribute("unsuccessful", "User with similar Username already exist");
			return null;
		}
		List<UserAccount> allUsers = userAccountService.getAllUser();
		log.info("All userAccounts:" + allUsers);
		model.addAttribute("userAccounts", allUsers);
		model.addAttribute("userAccount", null);
		return "userAccount";
	}



	@GetMapping("/userAccount/sortByUserName")
	public String sortedByNameView(Model model){
		List<UserAccount> allUsers = userAccountService.getAllUser();
		log.info("All userAccounts:" + allUsers);
		Comparator<UserAccount> compareByUserName = (a1, a2)->
				a1.getUserName().compareTo(a2.getUserName());
		Collections.sort(allUsers, compareByUserName);
		model.addAttribute("userAccounts", allUsers);
		return "userAccount";
	}


	@GetMapping("/userAccount/sortByRole")
	public String sortedByRoleView(Model model){
		List<UserAccount> allUsers = userAccountService.getAllUser();
		log.info("All userAccounts:" + allUsers);
		Comparator<UserAccount> compareByUserName = (a1, a2)->
				a1.getUserRole().getRoleType().compareTo(a2.getUserRole().getRoleType());
		Collections.sort(allUsers, compareByUserName);
		model.addAttribute("userAccounts", allUsers);
		return "userAccount";
	}

}
