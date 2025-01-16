function git_pull_subdirs() {
    for i in $(ls) 
	    do
				cd $i;
				printf "\n--------\ngit pulling on $i\n--------\n";
				git pull;
				cd ..;
	    done
		
	unset i
}
