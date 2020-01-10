package buddhini.mongo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMongoTest extends TestCase {
	
	@Autowired
	UserMongoRepository userMongoRepository;
	
	@Before
	public void setup() {
		User user1= new User("Alice", 23);
        User user2= new User("Bob", 38);
        //save product, verify has ID value after save
        assertNull(user1.getId());
        assertNull(user2.getId());//null before save
        this.userMongoRepository.save(user1);
        this.userMongoRepository.save(user2);
        assertNotNull(user1.getId());
        assertNotNull(user2.getId());
	}
	
	@Test
	public void getData() {
		User user1 = userMongoRepository.findByName("Alice");
		assertNotNull(user1);
		assertEquals(23,user1.getAge());
	}

}
