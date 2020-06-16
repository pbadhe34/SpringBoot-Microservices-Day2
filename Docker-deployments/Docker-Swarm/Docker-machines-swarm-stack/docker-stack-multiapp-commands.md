The docker machines created in earlier exercise docker-swarm-cluster are reused

boot the manager mc1
docker-machine start mc1 

To boot the manager, followed by 

docker-machine start mc2 

docker-machine ip mc1

docker-machine ip mc2 

docker-machine env mc1

docker-machine  status  mc2

docker-machine  inspect  mc2


To make the mc1 as active.

eval $(docker-machine env mc1)


docker-machine ssh mc1 "docker swarm init --advertise-addr 192.168.99.101"



    docker swarm join --token SWMTKN-1-37g7exb3izexqkfsx9djlmvu4esb60pnxtm3nlhd3
thounrjlb-6dbw9sgph3uofys3cx2jr1uwt 192.168.99.101:2377



docker-machine ssh mc2 "docker swarm join --token SWMTKN-1-37g7exb3izexqkfsx9djlmvu4esb60pnxtm3nlhd3
thounrjlb-6dbw9sgph3uofys3cx2jr1uwt 192.168.99.101:2377"



docker-machine ssh mc1 "docker node ls"


docker stack deploy -c docker-multi-app-stack-compose.yml swarm-stack

Adds visualizer service runs on master node manager port 8080. Get the IP address of one of the nodes by running docker-machine ls. Go to either IP address at port 8080 and you will see the visualizer running: 

docker stack ps swarm-stack
 

docker-machine ssh mc1 "mkdir ./data"

docker-machine ls

re-run 
docker-machine env mc1

eval $(docker-machine env mc1)

docker service ls
docker stack services swarm-stack

docker service ps  swarm-stack_web

docker service logs  swarm-stack_web

docker service ps  swarm-stack_visualizer

docker service ps  swarm-stack_redis


docker service logs  swarm-stack_web

docker service logs  swarm-stack_visualizer


docker service logs  swarm-stack_redis



Access the application py-app from browser
http://192.168.99.102:1800/
  and
http://192.168.99.101:1800/

 To access visualizer
http://192.168.99.102:8080/
  and
http://192.168.99.101:8080/


To access redis
http://192.168.99.102:6379/
  and
http://192.168.99.101:6379/


Or from terminal
curl http://192.168.99.102:1800/
curl http://192.168.99.101:1800/

docker-machine ssh mc1 "docker node ls"

docker-machine ssh mc1 "docker container ls"

docker-machine ssh mc2 "docker container ls"

docker-machine ssh mc2 "docker logs 30ffc2a38615"

To scale the service

docker service scale swarm-stack_web=3

docker service ps  swarm-stack_web


In the end
docker stack rm swarm-stack
docker stack ps swarm-stack

docker-machine ssh mc2 "docker swarm leave " on MC2 


docker-machine ssh mc1 "docker swarm leave --force" on the  MC1

docker service ls
docker stack services swarm-stack


