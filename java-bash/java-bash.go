package main

import (
	"fmt"
	"os"
	"os/exec"
)

func main() {
	os.Setenv("CHERE_INVOKING", "1")
	os.Setenv("MSYSTEM", "MINGW64")
	uprofile := os.Getenv("USERPROFILE")
	javahome := uprofile + `\oithomes\java`
        os.Chdir(javahome)
	cmd := exec.Command(`C:\oit\PortableGit-2.19.1-64\usr\bin\mintty.exe`,`C:\oit\PortableGit-2.19.1-64\usr\bin\bash.exe`, `--login`, `-i`)
	cmd.Stdin = os.Stdin
	cmd.Stdout = os.Stdout
	cmd.Stderr = os.Stderr
	if err := cmd.Run(); err != nil {
		fmt.Fprintln(os.Stderr, err)
		os.Exit(1)
	}
        fmt.Printf("exec command")
}