FROM eclipse-temurin:8-jdk-jammy

WORKDIR /build

###
# ADD [OPTIONS] <src> ... [<dest>]
# ADD [OPTIONS] ["<src>", ... "<dest>"]
# Option	Description	Since
# --keep-git-dir	Preserve thes .git directory.	1.1
# --checksum	Verifies the checksum of a remote resource.	1.6
# --chown	Changes owner of a resource.
# --chmod	Changes access/execution rights of a resource.	1.2
# --link	Copies source files into an empty destination directory.	1.4
# --exclude	Specifies a path expression for files to be excluded.	1.19
#
# <src> - local file or directory
# <src> - local *.tar archive
# <src> - URL
# <src> - git repository
#
###

ADD src.txt dest.txt
ADD --keep-git-dir src.txt dest.txt
ADD --checksum src.txt dest.txt
ADD --chown src.txt dest.txt
ADD --chmod src.txt dest.txt
ADD --link src.txt dest.txt
ADD --exclude src.txt dest.txt

CMD ["sleep", "infinity"]