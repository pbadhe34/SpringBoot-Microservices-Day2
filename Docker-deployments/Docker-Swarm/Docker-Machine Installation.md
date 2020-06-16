
On macOS and Windows, Docker-Machine is installed along with other Docker products when you install the Docker for Mac, Docker for Windows, or Docker Toolbox.

Step1 : Download for linux machinesdirectly
install the Machine binaries directly  from
https://github.com/docker/machine/releases/download/v0.15.0/docker-machine-Linux-x86_64


OR
Download with CURL
$ base=https://github.com/docker/machine/releases/download/v0.14.0 && curl -L $base/docker-machine-$(uname -s)-$(uname -m) >/tmp/docker-machine && sudo install /tmp/docker-machine /usr/local/bin/docker-machine

Or 
Use the downloaded file and set the permisions

Rename the docker-machine-Linux-x86_64 to docker-machine

Step 2
Apply executable permissions to the binary:

sudo chmod +x ./docker-machine

Step3
 Move the binary to the system PATH.

sudo mv ./docker-machine /usr/local/bin/docker-machine
 

Step4
Test the installation.
Docker must be running in background
 Run the command

docker-machine version
>docker-machine version 0.14.0, build 89b8332

