package uk.co.servada.login.service;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.servada.login.dao.UserDao;

public class UserService {
@Autowired
private UserDao userDao;

public UserDao getUserDao() {
	return userDao;
}

public void setUserDao(UserDao userDao) {
	this.userDao = userDao;
}


}
