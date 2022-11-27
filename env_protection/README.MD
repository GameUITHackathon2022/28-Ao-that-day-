##1. Docker compose: 
    docker compose up -d
##2. Run services
    - eureka-server
    - master
    - storage
    - authentication
    - event
    - posting
    - notification
    - apigw
##2. Insert roles data
    INSERT INTO role(id, name) VALUES (1, 'OrdinaryUser');
    INSERT INTO role(id, name) VALUES (2, 'Publisher');

##3. Call API through port 8090 (APIGateway)
##4. To run on Docker
    Go to the docker-compose.yml. 
    Uncomment the lines inside "Uncommen this part to run the service on docker"