> **GETTING STARTED:** You must start from some combination of the CSV Sprint code that you and your partner ended up with. Please move your code directly into this repository so that the `pom.xml`, `/src` folder, etc, are all at this base directory.

> **IMPORTANT NOTE**: In order to run the server, run `mvn package` in your terminal then `./run` (using Git Bash for Windows users). This will be the same as the first Sprint. Take notice when transferring this run sprint to your Sprint 2 implementation that the path of your Server class matches the path specified in the run script. Currently, it is set to execute Server at `edu/brown/cs/student/main/server/Server`. Running through terminal will save a lot of computer resources (IntelliJ is pretty intensive!) in future sprints.

# Project Details
## Server 
This is a Web API that can load, view, and search CSV files. It can also find the percentage of broadband coverage in a given county through the United States Census Bureau API. \
Team members: Samuel Salseda (ssalseda) and Raymond Kao (rmkao) \
Total estimated time: \
repo link: https://github.com/cs0320-s24/server-rmkao-ssalseda\

# Design Choices
Our Server class contains the main method where we set up our endpoints with their respective endpoint handlers: loadcsv, viewcsv, searchcsv, and broadband. Each endpoint handler has its own class which defines the behavior of the endpoint. After loading a CSV file, we store it as a global variable, with the data in a List<List<String>>. We use an interface called Datasource in order to use mocked data in our tests. Two classes implement this interface - RealDatasource and MockedDatasource. 

# Errors/Bugs

# Tests

# How to
To build this program, run 'mvn package' in the terminal. To run it, run './run' in the terminal. This will start a server where you can append the endpoint to the URL. For example, 'localhost:3232/viewcsv'. 
