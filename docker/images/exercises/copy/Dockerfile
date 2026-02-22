FROM eclipse-temurin:8-jdk-jammy

WORKDIR /build

COPY test-file-to-copy.txt file.txt
COPY directory-to-copy/test-file-to-copy.txt directory/
COPY directory-to-copy/test-file-to-move.txt moved.txt
COPY ../resources/file-to-copy.txt outside.txt

CMD ["sleep", "infinity"]