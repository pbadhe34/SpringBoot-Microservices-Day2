On desktop systems like Docker for Mac and Windows, Docker Compose is included as part of those desktop installs.

For Linux machines follow this process

Step1: Download
Download docker-compose lates from the url
https://github.com/docker/compose/releases/tag/1.22.0-rc1

as

https://github.com/docker/compose/releases/download/1.22.0-rc1/docker-compose-Linux-x86_64


OR use curl to download the latest version by 
using the latest Compose release number in the download command.

sudo curl -L https://github.com/docker/compose/releases/download/1.21.2/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose


OR Use exusting downloaded files
Rename the docker-compose-Linux-x86_64 to docker-compose

Step 2
Apply executable permissions to the binary:

sudo chmod +x ./docker-compose


Step3
Move the binary to the system PATH.

sudo mv ./docker-compose /usr/local/bin/docker-compose
 
Step4
Test the installation.
Docker must be running in background
Run the command

docker-compose version

 as well as 

docker-compose --version
docker-compose version 1.20.1, build 5d8c71b2
docker-py version: 3.1.4
CPython version: 3.6.4
OpenSSL version: OpenSSL 1.0.2k  26 Jan 2017