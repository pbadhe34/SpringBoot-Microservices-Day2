Running multiple apps in one container ..?

To chnage to another sibling/cousin  directory

cd ../

Use link and volume for sharing
Docker netywork create -- with externalk nw drivers

The docker-compose up command : Builds, (re)creates, starts, and attaches to containers for a service.

Unless they are already running, this command also starts any linked services.

The `docker-compose up` command aggregates the output of each container. When the command exits, all containers are stopped. Running `docker-compose up -d`
starts the containers in the background and leaves them running.

If there are existing containers for a service, and the service's configuratin or image was changed after the container's creation, `docker-compose up` pick up the changes by stopping and recreating the containers (preserving mounted
volumes). 
To prevent Compose from picking up changes, use the `--no-recreate` flag.

If you want to force Compose to stop and recreate all containers, use the
`--force-recreate` flag.

Usage: up [options] [--scale SERVICE=NUM...] [SERVICE...]

 
Without compose

docker run -d -p 4000:8090 pbadhe34/my-apps:app1

This Compose file defines two services, web and redis. 
Uses an image that’s built from the Dockerfile in the current directory.
Forwards the exposed port 5000 on the container to port 5000 on the host machine. We use the default port for the Flask web server, 5000.

To build the image locally with default config file
docker-compose build -d

Or

docker-compose up --build -d

Use the default file docker-compose.yml
docker-compose up

To run in detached mode with the default file docker-compose.yml

docker-compose up  -d

View The process

docker-compose ps

docker-compose images


To see what environment variables are available to the web service:

docker-compose run web env

To stop
docker-compose stop

To use different file name and in detached mode

docker-compose -f docker-compose-user.yml up -d 



////*///////

To share the current project directory s reference(Shared mount volume)to the container
Add in compose file for app conatiner
  volumes:
     - .:/code

Add volume to redis container

volumes:
     - /d/data/redis-data:/data


To run with bind volume in detached mode
To use the shared volume so that the data will be saved across re-starts

docker-compose  -f docker-compose-redis-volume.yml up -d 

docker-compose  -f docker-compose-redis-volume.yml down

docker-compose  up -d   (default file is docker-compose.yml)

docker-compose  down


NOTE the COntainer name in the file for redis container

curl http://192.168.99.100:4000/

Check the docker container processes

docker-compose ps

docker-compose  logs

docker-compose  stop

docker-compose  down

#The new volumes key mounts the project directory (current #directory) on the host to /code inside the container, #allowing you to modify the code on the fly, without having to #rebuild the image.


**************************////**//
Scaliong the containers  service


docker-compose  -f docker-compose-replicas.yml up -d 

docker-compose  -f docker-compose.yml up -d 

docker-compose  up -d --scale web=3 --scale redis=2 




Instead use up with --scale option

Even if one redis container is stopped,the applicatiin continues with anotrher redis container alive!


Host Port mapped to the container port.
For multiple SAME host port mapping..to the host for multiuple conayiners  is PORT Conflict.

Scale it up by running

docker-compose -f docker-compose-replicas.yml up -d --scale web=3 --scale redis=2 

Solve by adding the NGINX port forwading agent

Build the nginx custom image

docker build -t my-nginx .


docker-compose -f docker-compose-replicas-no-port.yml up -d 

docker-compose -f docker-compose-replicas-no-port.yml up -d --scale web=3 --scale redis=2 

Now stop and remove one of the contaiuner and se the response

Docker ps

docker-compose ps

docker-compose logs  : combined log
 

To run only nginx server
docker-compose -f  docker-compose-nginx.yml up -d


The docker-compose scale command is depreceated

docker-compose -f docker-compose-scale.yml  scale web=2



***********///*****************

Run the individual contaienr apps

docker-compose  run -d web python app.py
 
Run Manuallay
docker-compose run -d --publish 8080:8090 web python app.py

 
To disable service ports
docker-compose run --service-ports web python app.py 

Not To start linked containers, use the --no-deps flag:

docker-compose run --no-deps web python app.py

Run multiple compose files

docker-compose -f docker-compose.yml -f docker-compose.prod.yml up -d

*******************///********88