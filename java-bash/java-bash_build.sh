./rsrc.exe -ico java-bash.ico -o java-bash.syso
GOOS=windows GOARCH=amd64 go build
mv golang.exe java-bash.exe