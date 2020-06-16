
Display the running processes of a container

docker container  top  ae81c7a7670f

docker container top $(docker ps -aq)

docker ps -a --filter "name=mysql"

docker ps -a --filter "status=running" --filter "name=mysql"
 

docker container top $(docker ps -a --filter "name=mysql")


Stop one or more running containers
docker container stop ae81c7a7670f

Start the container
docker container start ae81c7a7670f

To scale the services
Use the Docker CLI to scale the number of containers in the service afgter deployed. Containers running in a service are called “tasks.”

docker service scale <SERVICE-ID>=<NUMBER-OF-TASKS>
docker service scale app2_web=5
docker service scale app2_mysql=3


Run docker service ps <SERVICE-ID> to see the updated task list

docker service ps app2_web

docker service ps app2_mysql


Docker Stats
Display a live stream of container(s) resource usage statistics

Stats usage for all
docker container stats -a  OR docker container stats --all

For specific container

docker container stats ae81c7a7670f

Disable streaming stats and only pull the first result

docker container stats -a  --no-stream

Fetch the logs of a container
docker container logs 253b5e322ace

////***
Write multiple docker container logs into a single file in Docker Swarm with Fluentd as logging driver

///***

Update configuration of one or more containers
For memory limit of 524MiB

docker container update --memory 128MB --memory-swap 160MB ae81c7a7670f

Monitor the real time events from the docker server

docker events --format '{{json .}}'

docker events --filter 'image=mysql-users' --filter 'event=stop' --format '{{json .}}'

docker events --filter 'container=ae81c7a7670f' --format '{{json .}}'

From another cmd window
docker ps -a --filter "status=running" --filter "name=mysql"

Stop and start the container by id and check the evenbts trace in first cmd window

List port mappings or a specific mapping for the container

docker container port d704f627fbf2

Inspect changes to files or directories on a container’s filesystem

docker container diff 253b5e322ace

Create a new image from a container’s changes and push it to repository

docker container commit -a Prakash -m "New update for mysql"  253b5e322ace pbadhe34/my-apps:mysql-users

docker image ls

Push the new image to Docjker-Hub Repsoitory
docker login
docker push pbadhe34/my-apps:mysql-users

Export a container’s filesystem as a tar archive for mysql container
docker container export ae81c7a7670f -o mysql-users.tar

Check the parent directory contents with 'ls'
Find mysql-users.tar creatd in Docker-swarm-apps dir

List images by name and tag
docker images java
 

docker container ls --format "{{.Names}} :::{{.Image}}:::{{.Status}} =={{.Ports}}"

Display detailed information on one or more container

docker container inspect [OPTIONS] CONTAINER [CONTAINER...]

docker container inspect ae81c7a7670f app1_redis.2.282w6yjoduwcv6r76q2nqg3x7

docker container inspect ae81c7a7670f

Fetch the logs of a container

Usage
docker container logs ae81c7a7670f

docker container stats ae81c7a7670f

//***************
Rolling update with the conyainer in swarm service

docker service inspect --pretty app1_redis

 Now update the container image for redis. The swarm manager applies the update to nodes according to the UpdateConfig policy:

docker service update --image redis:3.0.7 app1_redis



The scheduler applies rolling updates as follows :
The atsk is a container

1.Stop the first task.
2.Schedule update for the stopped task.
3.Start the container for the updated task.
4.If the update to a task returns RUNNING, wait for the specified delay period then start the next task.
5.If, at any time during the update, a task returns FAILED, pause the update.

To check the new image
docker service inspect --pretty app1_redis


The output of service inspect shows if your update paused due to failure

To restart a paused update run 
docker service update app1_redis

Run docker service ps <SERVICE-ID> to watch the rolling update:

docker service ps app1_web

Before the Swarm updates all of the tasks, you can see that some are running redis:3.0.6 while others are running redis:3.0.7. 
The output above shows the state once the rolling updates are done.

Service update flag : --detach , -d
Exit immediately instead of waiting for the service to converge

Force update even if no changes require it: --force
Rollback to previous specification: --rollback
Action on rollback failure : --rollback-failure-action

Action on update failure (pause”|”continue”|”rollback”): --update-failure-action

Update works with

Service command args
Add or update a config file on a service
Remove a configuration file
Add or update a placement constraint for container
Remove a constraint
Add or update a container label
Remove a container label by its key
dd or update/remove a custom DNS server
Add or update/remove a DNS option
Overwrite the default ENTRYPOINT of the image
Add/remove or update an environment variable
Update Container hostname
Update Service image tag
Add/remove or update a service label
Limit CPUs/limit memory
Add or update a mount on a service
Update logging driver for service
Update Logging driver options
Add/remove or update a mount on a service
Add/remove a network
Add/remove or update a published port by its target port on container
Update with Number of tasks/replicas
Update the Working directory inside the container

////*************

Kill one or more running containers
docker container kill -s ae81c7a7670f 

Rename a container 
docker rename app2_mysql.1.yxr6fuo1psrouhpky7ua3csn0 Mysql-Container

List the nodes
docker node ls

docker node inspect self --pretty

List tasks running on one or more nodes, defaults to current node
docker node ps 

docker node ps gz3wvuva46sqtfakcqq9hpm90

Work with another node in the network
Update metadata about a node, such as its availability, labels, or roles.

Donot update on current node

docker node update --availability pause gz3wvuva46sqtfakcqq9hpm90

docker node update --availability active gz3wvuva46sqtfakcqq9hpm90

 
docker node update --role manager gz3wvuva46sqtfakcqq9hpm90



