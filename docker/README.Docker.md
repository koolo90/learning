### Building and running your application

When you're ready, start your application by running:
`docker compose up --build`.

Your application will be available at http://localhost:8080.

### Deploying your application to the cloud

First, build your image, e.g.: `docker build -t myapp .`.
If your cloud uses a different CPU architecture than your development
machine (e.g., you are on a Mac M1 and your cloud provider is amd64),
you'll want to build the image for that platform, e.g.:
`docker build --platform=linux/amd64 -t myapp .`.

Then, push it to your registry, e.g. `docker push myregistry.com/myapp`.

Consult Docker's [getting started](https://docs.docker.com/go/get-started-sharing/)
docs for more detail on building and pushing.

There are several images in this repository, each one will actually execute different command available in coker file. 
This repository of images is made for learning purposes.
All commands available in dockerfile can be found in docker documentation here: https://docs.docker.com/engine/reference/builder/
The images are as follows:
Overview
The Dockerfile supports the following instructions:

Instruction	Description 
- `container-add`: Add local or remote files and directories.
- `container-arg`: Use build-time variables.
- `container-cmd`: Specify default commands.
- `container-copy`: Copy files and directories. 
- `container-entrypoint`: Specify default executable.
- `container-env`: Set environment variables.
- `container-expose`: Describe which ports your application is listening on.
- `container-from`: Create a new build stage from a base image.
- `container-healthchek`: Check a container's health on startup.
- `container-label`: Add metadata to an image.
- `container-maintainer`: Specify the author of an image.
- `container-onbuild`: Specify instructions for when the image is used in a build.
- `container-run`: Execute build commands.
- `container-shell`: Set the default shell of an image.
- `container-stopsignal`: Specify the system call signal for exiting a container.
- `container-user`: Set user and group ID.
- `container-volume`: Create volume mounts.
- `container-workdir`: Change working directory.