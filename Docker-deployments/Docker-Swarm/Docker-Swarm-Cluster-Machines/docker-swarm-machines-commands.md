docker-machine create --driver virtualbox mc1 

# Create a VM (Mac, Win7, Linux)


docker-machine create --driver virtualbox mc2


# On Win10
docker-machine create -d hyperv --hyperv-virtual-switch "myswitch" myvm1 
 

docker-machine mc1 is swarm manager

docker-machine mc2 is swarm worker

Init swarm on mc1

docker-machine  ip default

docker-machine  start MC1

docker-machine  start mc2
 
docker-machine  ip  MC1

192.168.99.101

docker-machine  ip  mc2

192.168.99.102

 
On windows with Oracle VM you cannot bind SWARM manager 
 manager with physical ip address of the host.

On Bare bare-metal linux hosts you can directly  bind SWARM manager  with physical ip address of the host.

Since yoy are running the terminal with default machine,pass the executing command to docker machiune mc1 through SSH

docker-machine ssh mc1 "docker swarm init --advertise-addr 192.168.99.101"


         docker swarm join --token SWMTKN-1-1cxhaiohdydg6ogoljef7eowmj0b7sz49rtxd5pz3
qyk3cf51g-er77cl035nqgncsz5420vv7p6 192.168.99.101:2377

docker-machine ssh mc2 "docker swarm join --token SWMTKN-1-1cxhaiohdydg6ogoljef7eowmj0b7sz49rtxd5pz3qyk3cf51g-er77cl035nqgncsz5420vv7p6 192.168.99.101:2377"

docker swarm join --token SWMTKN-1-5bx3luu6m14k2lg3kqicek7kuopippfkv3try9ubm9c2jig518-79oqdmul1lvt2kmpwl56losl1 192.168.99.101:2377


docker-machine ssh mc2 "docker swarm join --token SWMTKN-1-5bx3luu6m14k2lg3kqicek7kuopippfkv3try9ubm9c2jig518-79oqdmul1lvt2kmpwl56losl1 192.168.99.101:2377"

docker-machine ssh mc2 "docker swarm join \
--token <token> \
<ip>:2377"  #This node joined a swarm as a worker.

docker-machine ssh mc1 "mkdir ./data"

Run docker node ls on the manager to view the nodes in this swarm:

docker-machine ssh mc1 "docker node ls"


docker-machine ssh mc2 "docker node ls"

$ docker-machine ssh mc1 "docker node ls"
ID                            HOSTNAME            STATUS              AVAILABILI
TY        MANAGER STATUS
zi4zze620vs4921wa4wdksudy *   mc1                 Ready               Active
          Leader
lvit8nrh47ts8kznbg4dbs8y0     mc2                 Ready               Active

Run 
eval $(docker-machine env mc1)
 to make mc1 as active machine

docker-machine env mc1 and connect with 


docker-machine  ls 

The active is MC1

**********************
docker-machine  env default

docker-machine  ls 

The default is active

****************************


Run 'docker-machine env mc1' to get the command to configure shell to talk to mc1.

Run docker-machine ls to verify that mc1 is the active machine as indicated by the asterisk next to it. 

Run the given command to configure the shell to talk to mc1.
docker-machine.exe" env mc1 | Invoke-Expression
 

docker-machine  status  mc2

docker-machine  inspect  mc2


*********///
Deployment
To Deploy the app on the swarm manager on MC1

Connect docker shell with MC1

eval $("C:\Program Files\Docker Toolbox\docker-machine.exe" env mc1)

Verify by 
docker-machine ls

The active mc is mc1

Now deploy the stack
docker stack deploy -c docker-compose-replicas.yml swarm-app
 
 

docker stack ps swarm-app
The container status shoulb be running.If status is preparing..wait.


docker-machine ssh mc1 "docker node ls"

docker-machine ssh mc1 "docker container ls"

docker-machine ssh mc2 "docker container ls"

 

docker stack ls

docker stack ps swarm-app
docker stack services swarm-app
 
docker service ls

docker service ps  swarm-app_web

docker stack services swarm-app

docker service ps  swarm-app_web

docker service ps  swarm-app_redis



Access the application from browser
http://192.168.99.102:5100/
  and
http://192.168.99.101:5100/


Or from terminal
curl http://192.168.99.102:5100/
curl http://192.168.99.101:5100/


To scale the service

docker service scale swarm-app_web=5



Cleanup and reboot
To remove the stack

docker stack rm swarm-stack

At some point later, to remove this swarm if you want to with docker-machine ssh mc2 "docker swarm leave" on the worker and docker-machine ssh mc1 "docker swarm leave --force" on the manager,  

To unset the docker-machine environment variables in the current shell with the following command:

eval $(docker-machine env -u)


docker-machine env mc1          

# List the nodes in the swarm

docker-machine ssh mc1 "docker node ls"   

docker-machine ssh mc2 "docker node ls"     : This mc2 is NOT a manager 
 




bridge': create a network stack on the default Docker bridge
                      'none': no networking
                      'container:<name|id>': reuse another container's network stack
                      'host': use the Docker host network stack



 #To Inspect a node

docker-machine ssh mc1 "docker node inspect zi4zze620vs4921wa4wdksudy"   

 # View join token
docker-machine ssh mc1 "docker swarm join-token -q worker" 
 

# Open an SSH session with the VM; type "exit" to end
docker-machine ssh mc1 
docker node ls
docker container ls
exit

docker-machine ssh mc2
docker container ls
docker logs 30ffc2a38615
exit
  

# Make the worker leave the swarm
docker-machine ssh mc2 "docker swarm leave"  

#Check the down node
docker-machine ssh mc1 "docker node ls"   


# Make master leave, kill swarm
docker-machine ssh mc1 "docker swarm leave -f" 

  # Start a VM that is currently not running

docker-machine start mc1         
 
# Stop all running VMs
docker-machine stop $(docker-machine ls -q)        


 # Copy file to node's home dir

docker-machine scp docker-compose.yml myvm1:    

 

To Restart the machines
docker-machine start mc1
docker-machine start mc2
    

docker-machine rm $(docker-machine ls -q) 
# Delete all VMs and their disk images
