/**
 * 
 */
package com.ef;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.stream.Stream;

import dto.LogLineDTO;
import dto.ResultDTO;
import persistance.DBOperations;
import util.PropertyUtil;
import util.ValidationUtil;

/**
 * @author buddhini
 *
 */
public class Parser {

	public static void main(String[] args) {
		LogManager.getLogManager().reset();

		System.out.print("Please enter the path to your Database property (db.properties) file:");
		Scanner in = new Scanner(System.in);

		try {
			Parser parser = new Parser();
			Map<String, String> dbProperties = null;
			Map<String, String> argMap = null;

			String s = in.nextLine();
			dbProperties = PropertyUtil.readDbProperties(s);

			Map<String, List<String>> configProperties = PropertyUtil.readConfigProperties();

			try {

				if (args.length > 0) {

					if ((configProperties != null && configProperties.size() > 0)
							&& (dbProperties != null && dbProperties.size() > 0)) {
						
						DBOperations dbOperations = new DBOperations(dbProperties);

						argMap = parser.readCommandLineArguments(args, configProperties);

						if (argMap != null && argMap.size() > 0) {
							String fileName = getArgValue(argMap, "accesslog");

							if (fileName != null) {

								System.out.println("Reading Access Log File............");
								List<LogLineDTO> logLineDTOs = parser.readAccessLogFile(fileName);

								if (logLineDTOs != null && logLineDTOs.size() > 0) {
									System.out.println("Done!");

									dbOperations.cleanupData();

									System.out.println("Loading Log to Database. Please wait............");
									dbOperations.loadLogToDatabase(logLineDTOs);
									System.out.println("Done!");

									String startDate = getArgValue(argMap, "startDate");
									startDate = startDate.replaceAll("\\.", " ");

									String duration = getArgValue(argMap, "duration");
									Integer threshold = Integer.parseInt(getArgValue(argMap, "threshold"));

									System.out.println("Getting the list of bloacked IPs............");
									List<ResultDTO> results = dbOperations.findIpsForThreshold(startDate, duration,
											threshold);

									if (results != null && results.size() > 0) {
										System.out.println("##### Deatils of Blocked IPs ########");
										System.out.format("%1s%30s", "IP", "No. Of Requests" + "\n");
										System.out.println("-------------------------------");
										results.forEach(result -> {
											System.out.format("%1s%5d", result.getIp(), result.getRequests());
											System.out.println();
										});

										System.out.println("Loading Blocked IPs to Database.............");
										dbOperations.loadExceptionalIPsToDatabase(results);
										System.out.println("Done!");

										dbOperations.closeDbConnection();
									} else {
										System.out.println("No IPs found exceeding the given threshold");
									}
								} else {
									System.out.println("No access log entries to process.");
								}

							} else {
								System.out.println("Access Log File not specified.");
							}

						} else {
							System.out.println("Arguments not given correctly. Please recheck.");
						}
					} else {
						System.out.println(
								"Cannot proceed with execution...quitting app. Please check application configurations.");
					}
				} else {
					System.out.println(
							"Required arguments not passed.\nPlease run the application with required arguments --accesslog, --startDate, --duration, --threshold");
				}

			} catch (Exception e) {
				System.out.print(
						"Could not connect to the Database! Please recheck DB connection properties in db.properties.");
			}
		} catch (Exception e) {
			System.out.println("Could not read Database property file path!");
			in.close();
		}

	}

	private Map<String, String> readCommandLineArguments(String[] args, Map<String, List<String>> configProperties) {
		Map<String, String> map = new HashMap<String, String>();
		boolean invalid = false;

		try {

			for (String item : args) {
				String key = item.substring(2).split("=")[0];
				String value = item.substring(2).split("=")[1];
				if (ValidationUtil.validateArguments(key, value, configProperties)) {
					map.put(key, value);
				} else {
					invalid = true;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Something is not right! Please check the following:\n "
					+ "* Check if the command line arguments and/or values have unnecessary spaces.\n "
					+ "* Ensure that the accesslog argument value is enclosed in double quotes if the file path has spaces.");
			invalid = true;
		}

		return invalid ? null : map;
	}

	private List<LogLineDTO> readAccessLogFile(String fileName) {
		List<LogLineDTO> lineDTOs = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.forEach(line -> {
				String[] logLine = line.split("\\|");

				LogLineDTO lineDTO = new LogLineDTO();

				lineDTO.setDate(logLine[0]);

				lineDTO.setIp(logLine[1]);
				lineDTO.setRequest(logLine[2]);
				lineDTO.setStatus(Integer.parseInt(logLine[3]));
				lineDTO.setUserAgent(logLine[4]);

				lineDTOs.add(lineDTO);
			});
		} catch (IOException e) {
			System.out.println("Access log file could not be found or read!");
			return null;
		}

		return lineDTOs;

	}


	private static String getArgValue(Map<String, String> argsMap, String arg) {
		return argsMap.get(arg);
	}

}
