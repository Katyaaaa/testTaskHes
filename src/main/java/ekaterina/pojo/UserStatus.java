package ekaterina.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class UserStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToMany(mappedBy = "userStatus", fetch = FetchType.EAGER)
	private List<UserAccount> users;
	private StatusType statusType;


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserStatus status = (UserStatus) o;

		if (id != status.id) return false;
		if (users != null ? !users.equals(status.users) : status.users != null) return false;
		return statusType == status.statusType;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (users != null ? users.hashCode() : 0);
		result = 31 * result + (statusType != null ? statusType.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "UserStatus{" +
				"id=" + id +
				", statusType=" + statusType +
				'}';
	}
}
