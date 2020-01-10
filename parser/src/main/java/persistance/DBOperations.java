/**
 * 
 */
package persistance;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;

import domain.LogException;
import domain.ServerLog;
import dto.LogLineDTO;
import dto.ResultDTO;

/**
 * @author buddhini
 *
 */
public class DBOperations {

	PersistenceManager pm = null;
	EntityManager em = null;

	public DBOperations(Map<String, String> dbProperties) throws Exception {
			pm = PersistenceManager.getPersistenceManagerInstance(dbProperties);
			em = pm.getEntityManager();
	}

	public void cleanupData() {
		try {

			em.getTransaction().begin();

			Query query1 = em.createQuery("DELETE FROM ServerLog");
			Query query2 = em.createQuery("DELETE FROM LogException");

			query1.executeUpdate();
			query2.executeUpdate();

			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Could not clean up data!");
			closeDbConnection();
		}
	}

	public void loadLogToDatabase(List<LogLineDTO> dtos) {
		List<ServerLog> rows = createServerLogEntities(dtos);

		try {
			em.getTransaction().begin();

			int batchSize = 50;
			for (int i = 0; i < rows.size(); i++) {
				ServerLog row = rows.get(i);
				em.persist(row);

				if (i == rows.size() / 10) {
					System.out.print("10%");
					System.out.print("\b\b\b");
				}

				if (i == rows.size() / 4) {
					System.out.print("25%");
					System.out.print("\b\b\b");
				}

				if (i == rows.size() / 2) {
					System.out.print("50%");
					System.out.print("\b\b\b");
				}

				if (i == (rows.size() / 4) * 3) {
					System.out.print("75%");
					System.out.print("\b\b\b");
				}

				if (i == rows.size() - 1) {
					System.out.println("100%");
				}

				if (i % batchSize == 0) {

					em.flush();
					em.clear();
				}

			}

			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Could not load log data to Database!");
			closeDbConnection();
		}
	}

	public void loadExceptionalIPsToDatabase(List<ResultDTO> dtos) {

		List<LogException> rows = createLogExceptionEntities(dtos);

		try {
			em.getTransaction().begin();

			rows.forEach(row -> {

				em.persist(row);

				em.flush();
				em.clear();
			});

			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Could not load exception IPs to Database!");
			closeDbConnection();
		}
	}

	public List<ResultDTO> findIpsForThreshold(String startDate, String duration, Integer threshold) {
		List<ResultDTO> resultDTOs = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Timestamp start = null;
		Timestamp end = null;

		try {
			try {
				Date date = sdf.parse(startDate);
				start = new Timestamp(date.getTime());
				if (duration.equals("hourly")) {

					Calendar cal = Calendar.getInstance();
					cal.setTimeInMillis(date.getTime());
					cal.add(Calendar.HOUR, 1);
					end = new Timestamp(cal.getTime().getTime());

				} else if (duration.equals("daily")) {
					Calendar cal = Calendar.getInstance();
					cal.setTimeInMillis(date.getTime());
					cal.add(Calendar.DAY_OF_MONTH, 1);
					end = new Timestamp(cal.getTime().getTime());
				}
			} catch (ParseException pe) {
				System.out.println("Error in parsing date!");
			}

			em.getTransaction().begin();

			@SuppressWarnings("unchecked")
			List<Tuple> logInfo = em.createNativeQuery("SELECT a.IP AS ip, a.REQUESTS AS reqCnt FROM"
					+ "(SELECT l.ip AS IP, count(l.ip) AS REQUESTS FROM ServerLog l WHERE l.date >= :start AND l.date <= :end "
					+ "GROUP BY l.ip) a WHERE a.REQUESTS > :threshold", Tuple.class).setParameter("start", start)
					.setParameter("end", end).setParameter("threshold", threshold).getResultList();

			if (logInfo != null && logInfo.size() > 0) {
				resultDTOs = new ArrayList<ResultDTO>();
				for (Tuple dto : logInfo) {
					String ip = String.valueOf(dto.get("ip"));
					Integer requestCount = Integer.valueOf(String.valueOf(dto.get("reqCnt")));
					resultDTOs.add(new ResultDTO(ip, requestCount));
				}
			}

			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Could not find IPs for threshold!");
			closeDbConnection();
		}

		return resultDTOs;
	}

	private List<ServerLog> createServerLogEntities(List<LogLineDTO> lineDTOs) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		List<ServerLog> rows = new ArrayList<>();

		lineDTOs.forEach(line -> {

			ServerLog row = new ServerLog();

			try {
				Date date = sdf.parse(line.getDate());

				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(date.getTime());

				row.setDate(new Timestamp(cal.getTime().getTime()));
			} catch (ParseException pe) {
				System.out.println("Error in parsing date!");
			}

			row.setIp(line.getIp());
			row.setRequest(line.getRequest());
			row.setStatus(line.getStatus());
			row.setUserAgent(line.getUserAgent());

			rows.add(row);
		});

		return rows;
	}

	private List<LogException> createLogExceptionEntities(List<ResultDTO> resultDTOs) {

		List<LogException> rows = new ArrayList<>();

		resultDTOs.forEach(dto -> {
			LogException row = new LogException();

			row.setIp(dto.getIp());
			row.setRequestCount(dto.getRequests());
			String reason = "IP is blocked due to making " + dto.getRequests() + " number of requests";
			row.setReason(reason);

			rows.add(row);
		});

		return rows;
	}

	public void closeDbConnection() {
		try {
			em.close();
			pm.close();
		} catch (Exception ex) {
			System.out.println("Could not close connection to the Database!");
		}
	}

}
