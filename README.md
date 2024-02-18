> **GETTING STARTED:** You must start from some combination of the CSV Sprint code that you and your partner ended up with. Please move your code directly into this repository so that the `pom.xml`, `/src` folder, etc, are all at this base directory.

> **IMPORTANT NOTE**: In order to run the server, run `mvn package` in your terminal then `./run` (using Git Bash for Windows users). This will be the same as the first Sprint. Take notice when transferring this run sprint to your Sprint 2 implementation that the path of your Server class matches the path specified in the run script. Currently, it is set to execute Server at `edu/brown/cs/student/main/server/Server`. Running through terminal will save a lot of computer resources (IntelliJ is pretty intensive!) in future sprints.

# Project Details
## Server 
This is a Web API that can load, view, and search CSV files. It can also find the percentage of broadband coverage in a given county through the United States Census Bureau API. \
Team members: Samuel Salseda (ssalseda) and Raymond Kao (rmkao) \
Total estimated time: 23 hours\
repo link: https://github.com/cs0320-s24/server-rmkao-ssalseda\

# Design Choices
Our Server class contains the main method where we set up our endpoints with their respective endpoint handlers: loadcsv, viewcsv, searchcsv, and broadband. Each endpoint handler has its own class which defines the behavior of the endpoint. =

After loading a CSV file, we store it as a global variable, with the data in a List<List<String>>. We use an interface called Datasource in order to use mocked data in our tests. Two classes implement this interface - RealDatasource and MockedDatasource. For each of our endpoints there is a handler class which receives the input if any and 

# Errors/Bugs

# Tests
To run the tests, run 'mvn package'

# How to
To build this program, run 'mvn package' in the terminal. To run it, run './run' in the terminal. This will start a server where you can append the endpoint to the URL. For example, 'localhost:3232/viewcsv'. 

The first three endpoints enable the user to load, view, and search a file local to the server via a file path. We will go over each of these three endpoints:

The first is loadcsv. This handler serves the purposes of loading given a filepath. Requests should be of the following format:
"http://localhost:3232/loadcsv?filepath=**Filepath Goes Here**" \
For example: \
http://localhost:3232/loadcsv?filepath=src/test/postsecondary_education.csv \
The file can be replaced at any time by a new call to the load endpoint. \

The second endpoint is viewcsv, and this handler is in charge of allowing the end user to see the entirety of the data that wasloaded by the API. It is simply accessed by: \
http://localhost:3232/viewcsv \
It will return an error if a file was not loaded before hand.  \

The final endpoint of three is searchcsv.  \
this is likely the most complicated of the link forms. It must follow the format of \
    http://localhost:3232/searchcsv?searchby=    &index=    &header=    &term=   \
    Where searchBy is either "column", "all", or "name". \
    index is a number a header name or "none" \
    header is "true" or "false" indicating the presence of headers. \
    if you are searching by column, your index must be an integer. \
    if you are searching by header, your index must be a string. \
    term is the search term. \
    for example: \
  http://localhost:3232/searchcsv?searchby=all&index=1Race&header=false&term=Asian \
  http://localhost:3232/searchcsv?searchby=column&index=8&header=false&term=men \
  http://localhost:3232/searchcsv?searchby=name&index=sex&header=true&term=men \

  The last endpoint will be of the form "broadband" which uses API calls to convert state name and county name into state code and county code, then finally queries for broadband coverage using those codes. \
  These calls will be of the following form: \
  http://localhost:3232/broadband?state= \
                                  &county= \
  Where state is a state and county is a county. \
  for example: \
  http://localhost:3232/broadband?state=California&county=Los Angeles County \

  Due to the time involved in making this query. It can be cached. To edit caching settings, navigate to the GlobalCache file. \
  The following lines can be edited to change cache size and time limit for purging. \
              .maximumSize(100) \
            .expireAfterWrite(5, TimeUnit.MINUTES) \
  
