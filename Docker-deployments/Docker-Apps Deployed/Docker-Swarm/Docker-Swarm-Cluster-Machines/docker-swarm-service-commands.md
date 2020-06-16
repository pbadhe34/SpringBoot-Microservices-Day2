  

Docker’s support for swarms is built using a project called SwarmKit. SwarmKit tasks do not need to be containers, but Docker swarm tasks are defined to spawn them.
Let’s inspect one of these tasks, and limit the output to container ID:

Run your load-balanced app with swarm ntwork clusetr
Before we can use the docker stack deploy command we’ll first run:

docker swarm init

docker swarm init  --advertise-addr eth1

docker swarm init  --advertise-addr 192.168.99.100

  (eth1 is  host machine address)

docker swarm init  --advertise-addr 192.168.0.100 (physical address) : Could NOT recognize

 
swarm initialized: current node (pqv3nnfvh3fjlmefp1gdwe82m) is now a manager.
To add a worker to this swarm, run the following command:

    
    docker swarm join --token SWMTKN-1-1gly6t9kycsbaz8m5c323csakc5sxcf769r2fbxll
5wmpwnujl-80jxvxw6s8wujap3r8f8pg5cz 192.168.99.100:2377

 add a manager to this swarm, run 'docker swarm join-token manager' and follow he instructions. 

Deploy with stack name as app
docker stack deploy -c docker-compose.yml app
docker stack deploy -c docker-compose-local.yml app

 Creating network app_webnet
 Creating service app_web


docker stack ls

$ docker stack ls
NAME                SERVICES
app          1

docker stack ps app

docker stack services app

docker service ls

 
docker inspect app_web

  Docker swarms run tasks that spawn containers.
  Tasks have state and their own IDs. Let’s list the tasks:

    docker service ps <service>

    docker service ps  app_web

    
  
 Now list all the containers:

docker container ls -q

Kill one of the container
docker stop 

 To check the output

  curl http://192.168.99.100:1490/ several times in a row, or go to that URL http://192.168.99.100/in the browser and hit refresh a few times.You should see the host name changed in the outputs

 demonstrating the load-balancing; with each request, one of the 5 replicas is chosen, in a round-robin fashion, to respond. The container IDs will match your output from the previous command (docker container ls -q).

docker service ps app_web

to remove the service. 

docker service rm app_web
 
To stop all conatiners

docker stop $(docker ps -a -q)

  
To remove all conatiners
docker rm $(docker ps -a -q)

Scale the app
You can scale the app by changing the replicas value
 in docker-compose.yml, saving the change, and re-running 
the docker stack deploy command:
  

docker service ps my-service_web

 

docker container ls -q


docker service ps my-service_web


docker service scale <service-name=number of instances>

docker service scale app_web=5
docker container ls -q


docker service scale app_web=2

docker container ls -q
 
 
Docker will do an in-place update, no need to tear the stack down first or kill any containers.

docker node ls


docker node ls
 

docker ps 
 

docker inspect <container ID>


Now, re-run docker container ls -q to see the deployed instances reconfigured. If you scaled up the replicas, more tasks, and hence, more containers, are started.

Take down the app and the swarm
Take the app down with docker stack rm:

docker stack rm app_web

docker inspect  app_web

One node is runnimng
docker node ls

Take down the (node)swarm with 
docker swarm leave --force


 
