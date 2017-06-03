Match scheduling using round robin. This application is build considering below criterias.

Accept N number of teams

Each team must play against every other team once home and away

Maximum 2 matches per day are allowed

No team should play on consecutive days

NOTE : it works only for EVEN NUMBER OF TEAMS.

### Build

$ mvn clean package

$ java -jar target/round-robin-0.0.1-SNAPSHOT.jar

### Usage

- Go on http://localhost:8080/teams/4 - Should send list of matches 
- Go on http://localhost:8080/teams/html/4 - Should send list of matches in tabular format
