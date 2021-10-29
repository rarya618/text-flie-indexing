Welcome to text-file-indexing.

Some basic information:
1. The list of commands can be accessed through the 'help' command.

This file is accessible through the readme command.

Input:
help

Output:
Commands:
quit: quits application
add: adds files or directories for indexing
rm: removes files or directories from indexing
read: displays contents of a given file or directory in the index
ls: displays the files and directories available in the index
search: searches for a word or phrase in the index

NOTE: Commands are case-sensitive.

2. The add, rm, read, and search commands can either be written as a single line:
add FILE_NAME

or in two separate lines:
add
Enter file name to add: FILE_NAME

3. The base folder is text-file-indexing. If your file or directory is not present there, you would need to specify an exact path to the file.
If it is located in the folder, you can simply specify the path with text-file-indexing as the base.

Example:
add test
This would simply open the test folder located in text-file-indexing.

However, if you do this:
add subdir1
For this directory, the base is test. So you would be required to use test/subdir1 instead.

NOTE: This command can be accessed with the 'readme' command.