package ekaterina.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToMany(mappedBy = "userRole", fetch = FetchType.EAGER)
	private List<UserAccount> users;
	private RoleType roleType;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserRole userRole = (UserRole) o;

		if (id != userRole.id) return false;
		if (users != null ? !users.equals(userRole.users) : userRole.users != null) return false;
		return roleType == userRole.roleType;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (users != null ? users.hashCode() : 0);
		result = 31 * result + (roleType != null ? roleType.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "UserRole{" +
				"id=" + id +
				", roleType=" + roleType +
				'}';
	}
}
