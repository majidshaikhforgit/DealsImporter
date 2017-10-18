# CSVImporter Project Setup Guide

* Server port and context : localhost:8082/DealsImporter

* Create a schema named as "deals" (MYSQL DB used for connection)

* Setup the username and the password of the database in "application.properties" file

* Go to the project directory and enter command "mvn clean install"

* run the project switch to /target folder and enter command "java -jar demo-0.0.1-SNAPSHOT.jar"

# Web Endpoints

* To upload a csv file hit url in browser "http://localhost:8082/DealsImporter/importdeals"
  It gives the view page to upload a csv which will be persisted in the database.
  NOTE : A demo csv @ folder "/DealsImporter/uploads/deals.csv" is present with the input for import expected

* To enquire about the valid and invalid records count by file name hit url in browser "http://localhost:8082/DealsImporter/enquirefile"
