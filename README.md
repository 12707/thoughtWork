# thoughtWork
1. The entry URL is: http://localhost:8848/train/view
2. The content of upload file should be follow this pattern: AB3, BC3, DE4 ...
3. Other URLs for reference:
	 http://localhost:8848/train/api/distance/{route}，It is used to calculate the distance of each point, {route}'s format should be like:　A-B or A-D-C, ...
	 http://localhost:8848/train/api/shortest/distance/{route}, It is used to calculate the shortest distance between TWO points, {route}'s format should be like:　A-B
	 http://localhost:8848/train/api/trips/{trip}/{rule}/{limit}, It is used to calculate the number of stops between TWO points, {trip} should be like: AC, {rule} contains values: ‘maximum’，‘less’, 'exact', case insensitive, {limit} a integer number. 
4. To run this application, find out trains-1.0-SNAPSHOT.jar, execute the command 'java -jar {path}/trains-1.0-SNAPSHOT.jar' in command line, {path} is an absolute path in OS.
5. The thoughtWorkDemo.rar file includes all source codes.
