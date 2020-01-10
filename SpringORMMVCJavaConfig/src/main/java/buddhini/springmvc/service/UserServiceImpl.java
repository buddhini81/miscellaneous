package buddhini.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import buddhini.springmvc.dao.UserDao;
import buddhini.springmvc.model.Login;
import buddhini.springmvc.model.User;

@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	public void register(User user) {
	   userDao.register(user);
	}

	public User validateUser(Login login) {
		return userDao.validateUser(login);
	}
	
	
}
