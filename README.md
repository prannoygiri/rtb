# RTB

Upload and Search RTB request

This Project is for uploading RTB request through spring-boot application to elastic-search.
Then search the request based on parameters and get the List<RTBRequest> as a response.
Integrate Kibana to view the logs and the request upload.

This project has three services:
   1. Spring-boot for backend (API)
   2. Elastic Search for storing the RTB request
   3. Kibana for Logs and Dashboard

-----------------------------------------------------------

### Requirements:

You should have Docker and Docker-compose install inorder to run this application.
If you need help installing docker and docker-compose refer to this below link:

   * [Install Docker](https://docs.docker.com/engine/install/)
   * [Install Docker Compose](https://docs.docker.com/compose/install/)

-----------------------------------------------------------

### How to Run the Application:

1. Copy the env_example file to .env
`docker-compose --build up`

That's it. You application should start.

-----------------------------------------------------------
### How to Stop the Applicaton:

`docker-compose down`

That's should bring down all the containers.

-----------------------------------------------------------
### Check Applcation is running

Run command `docker ps`

You should see three container running.

-----------------------------------------------------------

### API Deatils

There are three API:

1. The First API is to check the connection

curl -X GET http://localhost:8080/api/checkconnection

2. The Second API is to upload the RTB Request

curl -X POST -H 'Content-Type: application/json' -d '{ RTB_REQUEST }'


. The Third API is to search for the RTB Request

curl -X POST -H 'Content-Type: application/json' -d '{ RTB_REQUEST_PARAMETERS }'

### Example Request at location

project_location/example_request/


### Kibana Dashboard

http://localhost:5601


