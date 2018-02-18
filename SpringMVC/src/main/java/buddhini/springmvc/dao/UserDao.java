package buddhini.springmvc.dao;

import buddhini.springmvc.model.Login;
import buddhini.springmvc.model.User;

public interface UserDao {
	void register(User user);

	User validateUser(Login login);
}
