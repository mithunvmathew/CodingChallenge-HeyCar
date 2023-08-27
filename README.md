# Getting Started

##### Requirements
JDK 8  
Maven  
Docker

##### Starting App
From the project root location, run the below commands  
mvn clean package  
docker-compose up

App running in the port 18001 (http://localhost:18001/)  
Swagger Api Documentation available here  
http://localhost:18001/swagger-ui.html 

##### Running automated tests  
mvn clean integration-test  
Tests include the dealer upload(CSV & JSON) and Search Operation.

##### Implementation and challenges  
Architecture: Upload operation and search operations separated.There is a lot of chances that dealers have differant csv schemas.
For ex: 'make' change with 'brand'  etc.. So this architecture has the capability of adding new csvs with minimum 
implementation.Implement generic CsvFileToObjectMapper inetrface provide a possibilities of extension.
(Only need to add one dto file and mapper and no need to touch the existing code).
Decied to keep a good separtion between the input data and persistant data.
input data -->dtos-->mapper-->entity -->repository.
So the changes in the input will adjust with dtos and mapper.

Tried to stick maximum in SOLID principle.
Separate controllers for upload and search.
Used h2 database for prototyping bcs of the easy configuration.
As per our requirement data (dealer_id + code) is a unique parameter and it used as composite primary key.
It reduces the number of  database calls.


For search operation Spring data Specification used with a generic approach.
If you need to add a new param for search api or we need to remove a param for search api , only need to add/remove few lines code in the 
CarListingSearchController only.

Challenges: In my view, csv upload will become a challenge. The csv may or maynot be in correct schema or very big chance of corrupt the data.
So our app must intelligent enough to verify the data before persisting.  
A lot of search operations make database slow.especially at the time of uploading. 
We can think about NoSQL database and improve search by caching with Hazlecast etc..

##### Like to implement
Improve the data verification of the uploaded data.  
Implement some caching implementation to improve search.  
Adding some metrics and monitor the performance of the Apis and DB.  
Adding more unit test and integration test to improve the code quality.
