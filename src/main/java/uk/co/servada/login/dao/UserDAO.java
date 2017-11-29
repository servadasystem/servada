package uk.co.servada.login.dao;


import java.util.List;
import uk.co.servada.login.entity.User;

public interface UserDAO {
	
	//insert (and update?) operations
    void saveUser(User user);

    //select operations
    User findById(int id);
    User findUserByLoginID(String loginID);
    List findAllUsers();
    
    //delete operations
    void deleteUserByLoginID(String loginID);
}
