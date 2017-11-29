package uk.co.servada.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import uk.co.servada.login.entity.User;
import java.util.List;

/*public class UserService {
	@Autowired
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}*/

public interface UserService {

    User findById(int id);
    void saveUser(User uer);
    void updateUser(User user);
    void deleteUserByLoginID(String loginID);

    List findAllUser();
    User findUserByLoginID(String loginID);
	boolean isLoginIDUnique(int id, String loginID);

}
