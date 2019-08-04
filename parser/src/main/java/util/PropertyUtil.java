/**
 * 
 */
package util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * @author buddhini
 *
 */
public class PropertyUtil {

	public static Map<String, List<String>> readConfigProperties() {

		Map<String, List<String>> configProperties = new HashMap<String, List<String>>();

		try (InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream("config.properties")) {

			Properties prop = new Properties();

			if (input == null) {
				System.out.println("config.properties Not Found!");
				return null;
			}

			prop.load(input);

			String arguments = prop.getProperty("cmd.arguments");
			configProperties.put("cmd.arguments", Arrays.asList(arguments.split(",")));

			String durations = prop.getProperty("cmd.arguments.durations");
			configProperties.put("cmd.arguments.durations", Arrays.asList(durations.split(",")));

		} catch (IOException ex) {
			System.out.println("Could not read config properties!");
			return null;
		}

		return configProperties;
	}
	
	public static Map<String, String> readDbProperties(String fileName) {

		Map<String, String> properties = new HashMap<>();

		fileName = fileName.replace("\\", "/");

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.forEach(line -> {

				String[] property = line.split("=");
				String key = property[0];
				String value = property[1];
				if (key.equals("javax.persistence.jdbc.url")) {
					value += "?useSSL=false";
				}
				properties.put(key, value);

			});
		} catch (IOException e) {
			System.out.println("Could not read db.properties!");
			return null;
		}

		return properties;

	}

}
