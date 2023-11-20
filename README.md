# personal-be-task-2023

# Introduction

Let’s create a multi-module application that manages profiles and their analyses. All the communication is meant to be via REST.

Every profile contains the following information:

`
{
 "name": "Alice",
 "surname": "Willson",
 "budget": "21505.78"
}
`
The system operates with two types of analyses: FIRST and SECOND.

Every analysis type has a price, for example, 1500.15 and 2180.40, respectively (must be provided via config file).

Every analysis contains the following information:

`
{
 "type": "SECOND",
 "owner": "<owner's identifier>",
 "viewers": ["<viewer1's identifier>", "<viewer2's identifier>"],
 "hiddenInfo": "a comment from the owner"
}
`

Every analysis has one owner’s profile and 0 or more view profiles. Every time a profile runs a new analysis, the profile’s budget gets adjusted. When the budget is low, and no analysis is affordable, the system rejects triggering a new analysis.

 ## Scenarios:
Profile can be created, updated, and fetched;
Profile can create an analysis;
Profile can fetch the analysis with the hiddenInfo if he is the owner;
Profile can fetch the analysis without the hiddenInfo if he is the viewer;
Profile can’t fetch an analysis if he is not an owner or viewer;
Profile can list all the analyses where he is an owner or viewer from NOW to PAST order;
Profile can’t trigger a new analysis if his budget is below the price;
Analysis can’t contain links on non-registered profiles.
## Comments and requirements:
No securitization is required within the technical task.
We do not expect to use Spring Security in the task. For simplicity, profileId must be passed in the header of the REST calls to differentiate profiles.
API versioning, logging, tests, and following the code organization principles must be considered in the result implementation.
Code and infrastructure have to be dockerized.
End-to-end tests must be placed in another module in the repo and run in a separate container as a part of the docker-compose run. Thus, containers must be launched in the following order: DB → Application → E2e.
The analyses' prices must be specified in the application.yml file.
The project has to be accompanied by a README.md file.
Every entity in the project can include more fields than specified in JSON examples - these are carrying the fields only required for explaining the task.
## Technologies and tools:
Spring Boot, PostgreSQL/MariaDB (based on your preference), Docker.



## Overview

Spring Boot Application for managing profiles and analyses.

The microservice provides the following:

1. Foundational profile system with ability to store/retrieve/update profile details ( such as name, surname, budget )
   against a profileId
2. Ability to trigger analysis with valid profile
3. Ability to retrieve one or list of analyses by owner/viewer

## Running the application

You can run it as a standalone jar in a few different ways:
you can run the dockerFile (located ` personal-be-task-service/Dockerfile`  ) and then docker-compose.yml file (located in ` personal-be-task-2023/docker` )
`
docker-compose up
`
or
`
mvn spring-boot:run
`

Or you can just run it in IntelliJ as a standard SpringBoot configuration. In that case please run the
docker-compose.yml file located in ` personal-be-task-service/src/docker/compose/docker-compose.yml ` to run the
database.

The application uses postgresql.


## Personal-be-task-service endpoints

#### with localhost examples

NB! header : x-profile-id must be provided (except from the addProfile endpoint)

#### POST

#### ``` /personal-project/v1/profile-management/profiles' ```

#### add new profile

```
curl --location --request POST 'localhost:8080/personal-project/v1/profile-management/profiles' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "test 1",
"surname": "test surname 2",
"budget" : 1000
}'
```


#### GET

#### ``` /personal-project/v1/profile-management/profiles'```

#### get profile info

```
curl --location --request GET 'localhost:8080/personal-project/v1/profile-management/profiles' \
--header 'x-profile-id: 1' \
```


#### PUT

#### ```/personal-project/v1/profile-management/profiles' ```

#### update profile info

```
curl --location --request PUT 'localhost:8080/personal-project/v1/profile-management/profiles' \
--header 'x-profile-id: 1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name" :" test name 2",
    "surname" : "test surname 2",
    "budget" : 1500
}'
```


#### POST

#### ```/personal-project/v1/analysis-management/analyses```

#### trigger analysis

```
curl --location --request POST 'localhost:8080/personal-project/v1/analysis-management/analyses' \
--header 'x-profile-id: 1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "type" :"FIRST",
    "hidden info" : "test hidden info",
    "viewers" : [2,3]
}'
```


#### GET

#### ```/personal-project/v1/analysis-management/analyses/{analysisId}```

#### get analysis

```
curl --location --request GET 'localhost:8080/personal-project/v1/analysis-management/analyses/3' \
--header 'x-profile-id: 1'
```



#### GET

#### ```/personal-project/v1/analysis-management/analyses```

#### get analyses

```
curl --location --request GET 'localhost:8080/personal-project/v1/analysis-management/analyses?page=0&sizePerPage=5' \
--header 'x-profile-id: 1'
```



#### Example of Profile

```
    {
    "id": 1,
    "name": " test name 2",
    "surname": "test surname 2",
    "budget": 1391.286
}
```

#### Example of Analysis

```
   {
   "type": "FIRST",
        "owner": 1,
        "viewers": [
            2,
            3
        ],
        "hiddenInfo": "test hidden info"
    }
```

#### AnalysisType (type) can have the following values

```
  FIRST, SECOND
```
