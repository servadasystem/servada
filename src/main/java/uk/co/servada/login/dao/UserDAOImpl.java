package uk.co.servada.login.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import uk.co.servada.login.entity.User;

@Repository("userDao")
public class UserDAOImpl extends AbstractDAO implements UserDAO {

 
	// Insert (and update?) operation
    @Override
    public void saveUser(User user) {
        persist(user);
    }
    
    // Select operations
    @Override
    public User findById(int id) {
        return (User) getByKey(id);

    }
    
    @Override
    public List findAllUsers() {
        Criteria criteria = createEntityCriteria();
        return (List) criteria.list();
    }

    @Override
    public User findUserByLoginID(String loginID) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("loginID", loginID));
        return (User) criteria.uniqueResult();
    }
    
    
    // Delete operations
    @Override
    public void deleteUserByLoginID(String loginID) {
        Query query = getSession().createSQLQuery("delete from User where loginID = :loginID");
        query.setString("loginID", loginID);
        query.executeUpdate();
    }
    
    //TODO update?

}
