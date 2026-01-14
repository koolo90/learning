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

# The --keep-git-dir flag only supports HTTP(S)/SSH repositories.
ADD --keep-git-dir https://github.com/koolo90/learning-resources.git resources-repo-with-git/

# The --checksum flag only supports HTTP(S) sources.
ADD --checksum=sha256:5fee4953542f30b03938cf7d6ecad96730decc04605d6f7f8daa88d772162f49 https://raw.githubusercontent.com/koolo90/learning-resources/refs/heads/main/wallpapers.tar wallpapers-unpacked-checked.tar

# Requires creation of other user, did not change the owner
ADD --chown=other src-file.txt dest-file-chown.txt

ADD --chmod=777 src-file.txt dest-file-chmod.txt

# I see no difference with --link or not
ADD --link src-file.txt dest-file-link.txt

# Keep container up, opens for inspection
CMD ["sleep", "infinity"]