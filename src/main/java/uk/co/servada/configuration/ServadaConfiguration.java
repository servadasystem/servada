package uk.co.servada.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import uk.co.servada.login.dao.UserDao;
import uk.co.servada.login.service.UserService;
import uk.co.servada.login.sql.UserSQL;

@Configuration
public class ServadaConfiguration {

	@Bean
	public UserDao userDao() {
		return new UserSQL();
	}

	@Bean
	public UserService userService() {
		UserService userService = new UserService();
		userService.setUserDao(userDao());
		return userService;
	}
}