package buddhini.springmvc.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import buddhini.springmvc.model.Login;
import buddhini.springmvc.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
    protected SessionFactory sessionFactory;

	public void register(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@SuppressWarnings("unchecked")
	public User validateUser(Login login) {
		List<User> users = sessionFactory.getCurrentSession().createQuery("from User where username='" + login.getUsername() + "' and password='"
				+ login.getPassword() + "'").list();
		return users.size() > 0 ? users.get(0) : null;
	}
}

