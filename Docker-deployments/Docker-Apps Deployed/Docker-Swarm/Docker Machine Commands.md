
Use 'docker-machine' to manage and maintain the machine instances.

With Toolbox on Mac, Toolbox on older Windows systems without Hyper-V, or Docker for Mac, the default machine is alreday created.

To se the list of mcs
 
docker-machine ls

The docker-machine active 

See which machine is “active” (a machine is considered active if the DOCKER_HOST environment variable points to it). 

docker-machine inspect default

Start and stop machines
If you are finished using a host for the time being, you can stop it with docker-machine stop and later start it again with docker-machine start.

      docker-machine stop MC1
      docker-machine start MC1

docker-machine url MC1

-->tcp://192.168.99.100:2376

This renders information about a machine as JSON. If a format is specified, the given template is executed for each result


docker-machine status  (default)


Run the commands for specific machine

docker-machine ip default
192.168.99.100

For win10 mc
docker-machine create -d hyperv --hyperv-virtual- virtualswitch "myswitch"  vm1

****************************************
Communication with the "default" Docker Machine

Operate on machines without specifying the name (default machine)
Some docker-machine commands assume that the given operation should be run on a machine named default (if it exists) if no machine name is specified. Because using a local VM named default is such a common pattern, this allows you to save some typing on the most frequently used Machine commands.

docker-machine stop
      Stopping "default"....
      Machine "default" was stopped.

Commands that follow this style are:

    - `docker-machine config`
    - `docker-machine env`
    - `docker-machine inspect`
    - `docker-machine ip`
    - `docker-machine kill`
    - `docker-machine provision`
    - `docker-machine regenerate-certs`
    - `docker-machine restart`
    - `docker-machine ssh`
    - `docker-machine start`
    - `docker-machine status`
    - `docker-machine stop`
    - `docker-machine upgrade`
    - `docker-machine url`

or machine names other than default, and commands other than those listed above, you must always specify the name explicitly as an argument.

docker-machine ip default
 192.168.99.100 

docker-machine url default
 192.168.99.100 

******************************
Create a new DOCKER machine
Run the docker-machine create command, pass the appropriate driver to the --driver flag and provide a machine name. 
If this is your first machine, name it default as shown in the example. If you already have a “default” machine, choose another name for this new machine.

You can create and manage as many local VMs running Docker as your local resources permit; just run docker-machine create
 
docker-machine create --driver virtualbox MC1

This command downloads a lightweight Linux distribution (boot2docker) with the Docker daemon installed, and creates and starts a VirtualBox VM with Docker running.

Verify with 
docker-machine ls
docker-machine status MC1

docker-machine start MC1

docker-machine ip MC1
192.168.99.101

docker-machine stop MC1
docker images --format "table {{.ID}}\t{{.Repository}}\t{{.Tag}}"


****************///*********

Running the commands   with Docker Machine 

SHELL  CONFIG
docker images  on default machine


To tell the Docker to talk to the particular machine, set the command with the docker-machine env command.

docker-machine env default | docker pull hello-world

Remove the image from default machine
docker rmi hello-world

///On MC1 ************

docker-machine env MC1|docker pull hello-world

docker-machine env MC1|docker run -d -p 8000:80 nginx


docker-machine env MC1 | docker image ls

Connect the current shell to the new machine.

 $ eval "$(docker-machine env MC1)"  

Any exposed ports are available on the Docker host’s IP address, which you can get using the docker-machine ip command:

Run a Nginx webserver in a container with the following command:

 $ docker run -d -p 8000:80 nginx

This is running on Machine MC1 ip address and reacable on http://192.168.99.101:8000/

docker-machine ip  MC1 
192.168.99.101

***************************///***************
Docker Machine with SSH
Wrapp Docker commands in docker-machine ssh to talk to the VM ctreated from console

docker-machine ssh MC1 "docker image ls"

docker-machine --native-ssh ssh MC1 "docker image ls"


docker-machine ssh MC1 "docker run hello-world"

docker-machine ssh MC1 "docker run -d -p 8000:80 nginx"
 

docker-machine --native-ssh ssh MC1 "docker run hello-world"

************************///*************
 
