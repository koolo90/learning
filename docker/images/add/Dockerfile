FROM eclipse-temurin:8-jdk-jammy

WORKDIR /build

ADD src-file.txt dest-file-flat.txt
ADD resources-dir/src-file-1.txt resources-dir/src-file-2.txt dest-file-overwrite.txt
ADD resources-dir/src-file-1.txt resources-dir/src-file-2.txt dest-dir-asembled/
ADD resources-dir/ dest-dir-recusively/
ADD resources-dir/wallpapers.tar dest-dir-unpacked/
ADD https://raw.githubusercontent.com/koolo90/learning-resources/refs/heads/main/src.txt dest-file-from-internet.txt
ADD https://raw.githubusercontent.com/koolo90/learning-resources/refs/heads/main/wallpapers.tar wallpapers-unpacked.tar
ADD https://github.com/koolo90/learning-resources.git resources-repo-without-git/
#root@804758983ceb:/build# ls -la resources-repo-without-git/
#total 6312
#drwxr-xr-x 3 root root    4096 Jan 14 18:43 .
#drwxr-xr-x 1 root root    4096 Jan 14 18:45 ..
#-rw-r--r-- 1 root root     115 Jan 14 18:43 src.txt
#drwxr-xr-x 2 root root    4096 Jan 14 18:43 wallpapers
#-rw-r--r-- 1 root root 6443520 Jan 14 18:43 wallpapers.tar
ADD --keep-git-dir https://github.com/koolo90/learning-resources.git resources-repo-with-git/
#root@804758983ceb:/build# ls -la resources-repo-with-git/
#total 6316
#drwxr-xr-x 4 root root    4096 Jan 14 18:45 .
#drwxr-xr-x 1 root root    4096 Jan 14 18:45 ..
#drwxr-xr-x 8 root root    4096 Jan 14 18:45 .git
#-rw-r--r-- 1 root root     115 Jan 14 18:45 src.txt
#drwxr-xr-x 2 root root    4096 Jan 14 18:45 wallpapers
#-rw-r--r-- 1 root root 6443520 Jan 14 18:45 wallpapers.tar

###
# ADD [OPTIONS] <src> ... [<dest>]
# ADD [OPTIONS] ["<src>", ... "<dest>"]
# Option	Description	Since
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

#ADD --checksum src.txt dest.txt
#ADD --chown src.txt dest.txt
#ADD --chmod src.txt dest.txt
#ADD --link src.txt dest.txt
#ADD --exclude src.txt dest.txt

CMD ["sleep", "infinity"]