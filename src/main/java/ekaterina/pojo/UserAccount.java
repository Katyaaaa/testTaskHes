package ekaterina.pojo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class UserAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String userName;

	private String password;

	private String firstName;

	private String lastName;

	@ManyToOne
	private UserRole userRole;
	@ManyToOne
	private UserStatus userStatus;
	private Date createdAt;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserAccount that = (UserAccount) o;

		if (id != that.id) return false;
		if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
		if (password != null ? !password.equals(that.password) : that.password != null) return false;
		if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
		if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
		if (userRole != null ? !userRole.equals(that.userRole) : that.userRole != null) return false;
		if (userStatus != null ? !userStatus.equals(that.userStatus) : that.userStatus != null) return false;
		return createdAt != null ? createdAt.equals(that.createdAt) : that.createdAt == null;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (userName != null ? userName.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
		result = 31 * result + (userStatus != null ? userStatus.hashCode() : 0);
		result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
		return result;
	}


}
