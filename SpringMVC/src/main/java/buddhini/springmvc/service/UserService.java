package buddhini.springmvc.service;

import buddhini.springmvc.model.Login;
import buddhini.springmvc.model.User;

public interface UserService {
	public void register(User user);

	public User validateUser(Login login);
}
