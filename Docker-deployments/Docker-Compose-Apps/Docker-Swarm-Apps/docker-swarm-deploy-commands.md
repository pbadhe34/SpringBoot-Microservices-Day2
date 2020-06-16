  

 
Run your load-balanced app with swarm network clusetr
 

Init the docker swarm cluster

docker swarm init

docker swarm init  --advertise-addr eth1

docker swarm init  --advertise-addr 192.168.99.100

  (eth1 is  host machine address)

docker swarm init  --advertise-addr 192.168.0.100 (physical address) : Could NOT recognize

 
swarm initialized: current node (pqv3nnfvh3fjlmefp1gdwe82m) is now a manager.
To add a worker to this swarm, run the following command:
    
    docker swarm join --token SWMTKN-1-3vjtjlnjlxiobvxo8u4l5r4eqgoj8g5s8p995bcfmb2p5rx4l9-34boeu8o14k00hv
vlexkm 192.168.99.100:2377

 Add one more manager to this swarm, run 'docker swarm join-token manager' and follow he instructions.
 

On Linux machine : for shared persitent volume
Create a ./redis directory on the manager node
mkdir ./redis

   On windows with Docker ToolBox
 docker-machine ssh  default "mkdir ./redis"

 docker-machine ssh  default "ls"

On windows with Docker ToolBox
volumes:
      - "/home/docker/redis:/data"     

On Linux  
     volumes:
      - "/home/docker/redis:/data"      
     
Deploy with stack name as app
docker stack deploy -c docker-compose-py-redis-app.yml app1

docker stack ls 

docker stack ps app1 

docker stack services app1

docker service ls

docker service ps app1_redis


 
docker service inspect --pretty app1_web
docker service inspect --pretty app1_redis

docker service inspect --pretty app1_visualizer


 PublishedPort = 30003

Chek the output for pyapp-redis service
http://192.168.99.100:30003

Check the output for Viusulaizer serviuce
http://192.168.99.100:30002


Read the replicas

docker service inspect --format='{{.Spec.Mode.Replicated.Replicas}}' app1_web

To read the publshed port
docker service inspect --format='{{.Spec.EndpointSpec.Ports}}' app1_web 
 


Docker swarms run tasks that spawn containers.
 Tasks have state and their own IDs. Let’s list the tasks:

  docker service ps  app1_redis

  docker service ps  app1_web

docker service logs app1_web


docker service logs app1_redis


docker service inspect app1_web
 

docker service inspect app1_redis

docker inspect app1_web  
    
  
 To check the output

 curl http://192.168.99.100:3001/ several times in a row, or go to that URL http://192.168.99.100/in the browser and hit refresh a few times.You should see the host name changed in the outputs

 demonstrating the load-balancing; with each request, one of the 5 replicas is chosen, in a round-robin fashion, to respond. The container IDs will match your output from the previous command (docker container ls -q).

 
docker stack deploy -c docker-multi-app-stack-compose.yml app2

docker run -it -d -p 7070:8080 -v /var/run/docker.sock:/var/run/docker.sock dockersamples/visualizer


to remove the service. 

docker service rm app_web
 
To stop all conatiners

docker stop $(docker ps -a -q)

  
Now list all the containers:

docker container ls -q


//////////////////*****
To Scale the app by service
1. You can scale the app by changing the replicas value
 in docker-compose.yml, saving the change, and re-running 
the docker stack deploy command:

2. Use the docker service scale without redploying the yml

docker service scale <service-name=number of instances>

docker service scale app_web=5

docker service scale app_redis=2

docker container ls -q

docker service scale app_web=2

docker container ls -q
 

docker node ls 

docker ps  

docker inspect <container ID>


docker service ps my-service_web 

docker container ls -q

docker service ps my-service_web
 

**********************/////

Kill one of the container
docker stop id
 
and check the swarm action  

Docker will do an in-place update, no need to tear the stack down first or kill any containers.

Now, re-run docker container ls -q to see the deployed instances reconfigured. If you scaled up the replicas, more tasks, and hence, more containers, are started.
/////////////***

Take down the app and the swarm
Take the app down with docker stack rm:

docker stack rm app_web

docker inspect  app_web

One node is runnimng
docker node ls

Take down the (node)swarm with 
docker swarm leave --force

To remove all conatiners
docker rm $(docker ps -a -q)

*********//
To deploy the java-mysql app

On windows with Docker ToolBox
 docker-machine ssh  default "mkdir ./mysql" 

docker-machine ssh  default "ls "


docker-machine ssh  default "ls mysql"

The source path is : 
localhost:/d/Docker-Swarm-Apps/data/mysqldata/ 

docker-machine scp -r  localhost:/d/Docker-Swarm-Apps/data/mysqldata/ default:/home/docker/mysql/


docker-machine ssh  default "ls/mysql"

On windows with Docker ToolBox
volumes:
       - "/home/docker/mysql:/var/lib/mysql


docker stack deploy -c docker-compose-java-mysql-app.yml app2


docker service ps  app2_web

docker service ps  app2_mysql


docker service logs app2_web


docker service logs app2_mysql


docker service inspect --pretty app2_web

docker service inspect --pretty app2_visualizer

Check for the values of  PublishedPort = 30001
 

docker service inspect app2_mysql

Check the output of java-mysql service in the broser 
http://192.168.99.100:30000/users

Check the output of Visulaizer service
http://192.168.99.100:30001/

 



