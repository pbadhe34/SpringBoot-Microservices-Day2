  

Docker’s support for swarms is built using a project called SwarmKit.  

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


Create a ./data directory on the manager:
docker-machine ssh default "mkdir ./data"

docker-machine ssh mc1 "mkdir ./data" 

Copy the data dir to defualt machine for MySql volume

docker-machine scp -r -d data/ default:/home/docker/data/


docker-machine scp -r data/ default:/tmp
docker-machine ssh deafult "sudo cp -r /tmp/data" /path/to/actual/destination

docker-machine ssh default "ls data" 


docker-machine ssh default "ls"


Deploy with stack name as app
docker stack deploy -c docker-compose.yml app

 
  


docker stack ls 

docker stack ps app 

docker stack services app

docker service ls

docker service logs app_web


docker service logs app_redis


docker service inspect app_web
 

docker service inspect app_redis

docker inspect app_web

  Docker swarms run tasks that spawn containers.
  Tasks have state and their own IDs. Let’s list the tasks:

    docker service ps  app_redis

    docker service ps  app_web
    
  
 To check the output

 curl http://192.168.99.100:1490/ several times in a row, or go to that URL http://192.168.99.100/in the browser and hit refresh a few times.You should see the host name changed in the outputs

 demonstrating the load-balancing; with each request, one of the 5 replicas is chosen, in a round-robin fashion, to respond. The container IDs will match your output from the previous command (docker container ls -q).

 

to remove the service. 

docker service rm app_web
 
To stop all conatiners

docker stop $(docker ps -a -q)

  
Now list all the containers:

docker container ls -q

**********************/////

Kill one of the container
docker stop id
 
and check the swarm action  

Docker will do an in-place update, no need to tear the stack down first or kill any containers.

Now, re-run docker container ls -q to see the deployed instances reconfigured. If you scaled up the replicas, more tasks, and hence, more containers, are started.


********************///*******


To Scale the app by service
1. You can scale the app by changing the replicas value
 in docker-compose.yml, saving the change, and re-running 
the docker stack deploy command:

2. Use the docker service scale without redploying the yml

docker service scale <service-name=number of instances>

docker service scale app_web=5

docker service scale app_redis=1

docker container ls -q

docker service scale app_web=2

docker container ls -q
 

docker node ls 

docker ps  

docker inspect <container ID>


docker service ps my-service_web 

docker container ls -q

docker service ps my-service_web

************///******
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



 
