# ADD
ADD has two forms. The latter form is required for paths containing whitespace.

```Dockerfile
ADD [OPTIONS] <src> ... <dest>
```
```Dockerfile
ADD [OPTIONS] ["<src>", ... "<dest>"]
```
The available `[OPTIONS]` are:

| Option           | Description                                              | Since |
|------------------|----------------------------------------------------------|-------|
| `--keep-git-dir` | Preserve thes `.git` directory.                          | 1.1   |
| `--checksum`     | Verifies the checksum of a remote resource.              | 1.6   |
| `--chown`        | Changes owner of a resource.                             |       |
| `--chmod`        | Changes access/execution rights of a resource.           | 1.2   |
| `--link`         | Copies source files into an empty destination directory. | 1.4   |
| `--exclude`      | Specifies a path expression for files to be excluded.    | 1.19  |
| `--unpack`       | Unpacks source if its an archive.                        | ?.??  |

The `ADD` instruction copies new files or directories from `<src>` and adds them to the 
filesystem of the image at the path `<dest>`. Files and directories can be copied from the 
build context, a remote URL, or a Git repository.

The `ADD` and `COPY` instructions are functionally similar, but serve slightly different 
purposes.
[Learn more about the differences between `ADD` and `COPY`](https://docs.docker.com/build/building/best-practices/#add-or-copy).

## Source
You can specify multiple source files or directories with `ADD`. 
The last argument must always be the destination. 
For example, to add two files, `file1.txt` and `file2.txt`, from the build context 
to `/usr/src/things/` in the build container:

```Dockerfile
ADD file1.txt file2.txt /usr/src/things/
```
If you specify multiple source files, either directly or using a wildcard, 
then the destination must be a directory (must end with a slash `/`).

To add files from a remote location, you can specify a `URL` or the address of a Git 
repository 
as the source. For example:
```Dockerfile
ADD https://example.com/archive.zip /usr/src/things/
```
```Dockerfile
ADD git@github.com:user/repo.git /usr/src/things/
```
BuildKit detects the type of <src> and processes it accordingly.
- If `<src>` is a local file or directory, the contents of the directory are copied to the 
specified destination.
**See:** [Adding files from the build context](#Adding files from the build context).
- If `<src>` is a local tar archive, it is decompressed and extracted to the specified 
destination.
  **See:** [Adding local tar archives](#Adding local tar archives).
- If `<src>` is a `URL`, the contents of the `URL` are downloaded and placed at the 
specified destination. 
**See:** [Adding files from a URL](#Adding files from a URL).
- If `<src>` is a Git repository, the repository is cloned to the specified destination.
**See:** [Adding files from a Git repository](#Adding files from a Git repository)

### Adding files from the build context
Any relative or local path that doesn't begin with a `http://`, `https://`, or `git@` protocol prefix is 
considered a local file path. The local file path is relative to the build context. For example, if the 
build context is the current directory, `ADD file.txt /` adds the file at `./file.txt` to the root of 
the filesystem in the build container.

Specifying a source path with a leading slash or one that navigates outside the build context, such as 
`ADD ../something /something`, automatically removes any parent directory navigation (`../`). Trailing 
slashes in the source path are also disregarded, making `ADD something/ /something` equivalent to 
`ADD something /something`.

If the source is a directory, the contents of the directory are copied, including filesystem metadata. 
The directory itself isn't copied, only its contents. If it contains subdirectories, these are also 
copied, and merged with any existing directories at the destination. Any conflicts are resolved in favor 
of the content being added, on a file-by-file basis, except if you're trying to copy a directory onto 
an existing file, in which case an error is raised.

If the source is a file, the file and its metadata are copied to the destination. File permissions are 
preserved. If the source is a file and a directory with the same name exists at the destination, an error 
is raised.

If you pass a Dockerfile through stdin to the build (`docker build - < Dockerfile`), there is no build 
context. In this case, you can only use the `ADD` instruction to copy remote files. You can also pass 
a tar archive through stdin: (`docker build - < archive.tar`), the Dockerfile at the root of the 
archive and the rest of the archive will be used as the context of the build.

### Pattern matching
For local files, each `<src>` may contain wildcards and matching will be done using Go's `filepath.Match` rules.

For example, to add all files and directories in the root of the build context ending with `.png`:

```Dockerfile
ADD *.png /dest/
```
In the following example, `?` is a single-character wildcard, matching e.g. `index.js` and 
`index.ts`.
```Dockerfile
ADD index.?s /dest/
```
When adding files or directories that contain special characters (such as `[ and ]`), you 
need to escape those paths following the Golang rules to prevent them from being treated as 
a matching pattern. For example, to add a file named `arr[0].txt`, use the following;
```Dockerfile
ADD arr[[]0].txt /dest/
```
### Adding local tar archives
When using a local tar archive as the source for `ADD`, and the archive is in a recognized 
compression format (`gzip`, `bzip2` or `xz`, or `uncompressed`), the archive is decompressed 
and extracted into the specified destination. Local tar archives are extracted by default, 
see the [`[ADD --unpack flag]`](???).

When a directory is extracted, it has the same behavior as `tar -x`. The result is the union 
of:

1. Whatever existed at the destination path, and
2. The contents of the source tree, with conflicts resolved in favor of the content being added, on a file-by-file basis.

| ℹ️ Note                                                                                                                                                                                                                                                                                                                                                                         | 
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Whether a file is identified as a recognized compression format or not is done solely based on the contents of the file, not the name of the file. For example, if an empty file happens to end with .tar.gz this isn't recognized as a compressed file and doesn't generate any kind of decompression error message, rather the file will simply be copied to the destination. | 

### Adding files from a URL
In the case where source is a remote file `URL`, the destination will have permissions 
of `600`. If the `HTTP response` contains a `Last-Modified` header, the timestamp from that 
header will be used to set the `mtime` on the destination file. However, like any other 
file processed during an `ADD`, mtime isn't included in the determination of whether or not 
the file has changed and the cache should be updated.

If remote file is a `tar archive`, the archive is not extracted by default. To download 
and extract the archive, use the `[ADD --unpack flag]`.

If the destination ends with a trailing slash, then the filename is inferred from the 
URL path. For example, `ADD http://example.com/foobar /` would create the file `/foobar`. 
The `URL` must have a nontrivial path so that an appropriate filename can be discovered 
(`http://example.com` doesn't work).

If the destination doesn't end with a trailing slash, the destination path becomes the 
filename of the file downloaded from the URL. For example, 
`ADD http://example.com/foo /bar` creates the file `/bar`.

If your URL files are protected using authentication, you need to use `RUN wget`, `RUN curl` 
or use another tool from within the container as the `ADD` instruction doesn't support 
authentication.

### Adding files from a Git repository
To use a Git repository as the source for `ADD`, you can reference the repository's `HTTP` 
or `SSH` address as the `source`. The repository is cloned to the specified destination in 
the image.

```Dockerfile
ADD https://github.com/user/repo.git /mydir/
```
You can use `URL` fragments to specify a specific `branch`, `tag`, `commit`, or 
`subdirectory`. For example, to add the docs directory of the `v0.14.1` tag of 
the `buildkit` repository:

```Dockerfile
ADD git@github.com:moby/buildkit.git#v0.14.1:docs /buildkit-docs
```
For more information about Git URL fragments, see [URL fragments](???).

When adding from a Git repository, the permissions bits for files are `644`. If a file in 
the repository has the executable bit set, it will have permissions set to `755`. 
Directories have permissions set to `755`.

When using a Git repository as the source, the repository must be accessible from the 
build context. To add a repository via `SSH`, whether public or private, you must 
pass an `SSH key` for authentication. For example, given the following Dockerfile:

```Dockerfile
# syntax=docker/dockerfile:1
FROM alpine
ADD git@git.example.com:foo/bar.git /bar
```
To build this `Dockerfile`, pass the `--ssh` flag to the docker build to mount 
the 1 to the build. For example:

```shell
docker build --ssh default .
```
For more information about building with secrets, see [Build secrets](???).

## Destination
If the destination path begins with a forward slash, it's interpreted as an absolute path, and the source files are copied into the specified destination relative to the root of the current build stage.

```Dockerfile
# create /abs/test.txt
ADD test.txt /abs/
```
Trailing slashes are significant. For example, `ADD test.txt /abs` creates a file at `/abs`,
whereas `ADD test.txt /abs/` creates `/abs/test.txt`.

If the destination path doesn't begin with a leading slash, it's interpreted as relative to 
the working directory of the build container.

```Dockerfile
WORKDIR /usr/src/app
# create /usr/src/app/rel/test.txt
ADD test.txt rel/
```
If destination doesn't exist, it's created, along with all missing directories in its path.

If the source is a file, and the destination doesn't end with a trailing slash, the source 
file will be written to the destination path as a file.

## ADD --keep-git-dir
```Dockerfile
ADD [--keep-git-dir=<boolean>] <src> ... <dir>
```
When `<src>` is the `HTTP` or `SSH` address of a remote Git repository, BuildKit adds the 
contents of the Git repository to the image excluding the `.git` directory by default.

The `--keep-git-dir=true` flag lets you preserve the `.git` directory.

```Dockerfile
# syntax=docker/dockerfile:1
FROM alpine
ADD --keep-git-dir=true https://github.com/moby/buildkit.git#v0.10.1 /buildkit
```
## ADD --checksum
```Dockerfile
ADD [--checksum=<hash>] <src> ... <dir>
```
The `--checksum` flag lets you verify the checksum of a remote resource. 
The checksum is formatted as `sha256:<hash>`. `SHA-256` is the only supported hash algorithm.

```Dockerfile
ADD --checksum=sha256:24454f830cdb571e2c4ad15481119c43b3cafd48dd869a9b2945d1036d1dc68d https://mirrors.edge.kernel.org/pub/linux/kernel/Historic/linux-0.01.tar.gz /
```
The `--checksum` flag only supports `HTTP(S)` sources.

## ADD --chown --chmod
See [COPY --chown --chmod](???).

## ADD --link
See [COPY --link](???).

## ADD --exclude
See [COPY --exclude](???).

## ADD --unpack
```Dockerfile
ADD [--unpack=<bool>] <src> ... <dir>
```
The `--unpack` flag controls whether or not to automatically unpack tar archives 
(including compressed formats like `gzip` or `bzip2`) when adding them to the image. Local 
tar archives are unpacked by default, whereas remote tar archives (where `src` is a `URL`) 
are downloaded without unpacking.

```Dockerfile
# syntax=docker/dockerfile:1
FROM alpine
# Download and unpack archive.tar.gz into /download:
ADD --unpack=true https://example.com/archive.tar.gz /download
# Add local tar without unpacking:
ADD --unpack=false my-archive.tar.gz .
```