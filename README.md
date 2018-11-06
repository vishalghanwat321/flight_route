Flight-Route

Flight_route APIs
####Docker Setup########## 
##Install docker on your machine, below is the command to execute on your Terminal

brew cask install docker

#####Virtual Box (Optional)####### 
##Install Oracle Virtual Box


############Start Docker ###############

bash /Applications/Docker/Docker\ Quickstart\ Terminal.app/Contents/Resources/Scripts/start.sh

#######Install and Start Postgres############

docker pull postgres:9.6

docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=password -e POSTGRES_DB=postgres -d postgres:9.6

########Download the Spring boot application and set your workspace with InteliJ ######

## Goto Project base path and execute below commands

1.
docker build -t flight_route:latest src/main/docker/

2.
./serve.sh

#### Once application will be started use Swagger URL to access APIs.

http://localhost:8082/swagger-ui.html#/

or

http://<Virtual_box_ip>:8082/swagger-ui.html#/
