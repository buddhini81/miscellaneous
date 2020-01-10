This is a simple Command Line application that can parse a web server access log file placed in the local file system (file location is configurable). It reads and loads the log to MySQL and checks if a given IP makes more than a certain number of requests for the given duration (hourly,daily).

Required Configurations
----------------------------------
1. Run the given scripts to create the MySQL schema.
1. Note that this application uses JPA for persistence. The database connection properties have been externalized for portability of the application. Hence it is required to have the "db.properties" file in your file system to run the application.
       - Make sure to change the values in the "db.properties" file as required.
       - Give the path to the "db.properties" file when prompted.
2. A valid path to the access.log file should be given for the --accesslog argument.	

To Run
---------
From the command line, give the following command:
java -cp "parser.jar" com.ef.Parser --accesslog=/path/to/file --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100
