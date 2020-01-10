package com.example.webflux.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

@Service
public class LoggingService {
	
	private final Path logFileLocation = Paths.get("log-dir");
	
	private String[] logMessages = {
			"User admin logged in to 192.168.233.82",
			"User peter logged in to 192.168.170.194",
			"User admin logged out from 192.168.233.82",
			"User mary logged in to 192.168.158.178",
			"User olha logged in to 192.168.233.82",
			"User olha logged out from 192.168.233.82",
			"User peter logged out from 192.168.170.194",
			"User admin logged out from 192.168.170.194",
			"User olha logged in to 192.168.158.178",
			"User mary logged out from 192.168.158.178"
	};

	public String log() {

		String insertedLine = null;
		Path path = Paths.get(logFileLocation + "/" + "user-access.log");

		if (Files.exists(path)) {
			try (FileWriter fw = new FileWriter(path.toFile().getAbsolutePath(), true)) {
				try (BufferedWriter bw = new BufferedWriter(fw)) {
					
					System.out.println("Writing to file");
					int idx = getRandomNumberInts(0,9);
					insertedLine = logMessages[idx];
					bw.write(insertedLine);
					bw.newLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			System.out.println("Path does not exist!");
		}

		return insertedLine;
	}

	public void deleteLogDirectoryAndFile() {
		FileSystemUtils.deleteRecursively(logFileLocation.toFile());
	}

	public void initLog() {
		try {
			Files.createDirectory(logFileLocation);
			createLogFile();
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}

	public void createLogFile() throws IOException {
		File file = new File(logFileLocation + "/" + "user-access.log");
		if (file.createNewFile()) {
			System.out.println("user-access.log file created successfully.");
		} else {
			System.out.println("File already exists.");
		}

	}
	
	private int getRandomNumberInts(int min, int max){
	    Random random = new Random();
	    return random.ints(min,(max+1)).findFirst().getAsInt();
	}

}
