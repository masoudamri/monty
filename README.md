#**Project Setup**

##Requirements

1.	jdk 1.8.x(amazon corretto preffered)
2.	gradle 5.3.1
3.	eclipse 4.8.x
4.	git 2.7.x

#Setup

1.	mkdir -p ~/workspaces/git/ors/finance/src	
2.	cd ~/workspaces/git/ors/finance/src
3.	git clone <uri> ./
4.	gradle clean cleanInstall cleanEclipse eclipse eclipseLaunchConfig build
5.	Open eclipse. Workspace:"~/workspaces/git/ors/finance"
6.	Right click, click "Import..."
7.	Select "Existing Gradle Project"
8.	When prompted, select "~/workspaces/git/ors/finance/src" as project root, and click through with 'Next', or 'Finish'