/**
 * 
 */
package persistance;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author buddhini
 *
 */
public class PersistenceManager {

	private static PersistenceManager persistenceManagerInstance;
	private EntityManagerFactory emFactory;

	private PersistenceManager(Map<String, String> properties) throws Exception {
		try {
			emFactory = Persistence.createEntityManagerFactory("parser-unit", properties);
		} catch (Exception ex) {
			throw new Exception();
		}
	}

	public EntityManager getEntityManager() throws Exception {
		return emFactory.createEntityManager();
	}

	public static PersistenceManager getPersistenceManagerInstance(Map<String, String> properties) throws Exception {
		if (persistenceManagerInstance == null) {
			persistenceManagerInstance = new PersistenceManager(properties);
		} else if (!persistenceManagerInstance.emFactory.isOpen()) {
			persistenceManagerInstance = new PersistenceManager(properties);
		}

		return persistenceManagerInstance;
	}

	public void close() throws Exception {
		emFactory.close();
	}

}
