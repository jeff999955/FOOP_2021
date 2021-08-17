# Design Report
> Please follow the instructions in homework 3 (officially announced version on NTU COOL) to finish the report.

## Software Design

### Main
The entry point of the whole program.

### Utils.Input
The utility class provided by TA.


### FileSystemCLI
The command line interface of the program. Delegate operations by corresponding commands and also maintaining the current working directory.

### File
The abstract class to be inherited by multiple file types (e.g. Directory, Regular File) and can be further extended to different file types (e.g. Symbolic Link).

### Directory
Overrides several methods a directory would do as a file and defined some directory-specific methods.

### RegularFile
Overrides several methods a regular file would do, for example, for `cat`, print the content of the file; for `ls`, raise an exception.

### SymbolicLink
The class that defines all operations would be used by a symbolic link. The `cat` works as a regular file, and `ls` works like a directory.

