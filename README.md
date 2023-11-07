# cybercube-be-task-2023

## Overview

Spring Boot Application for managing profiles and analyses.

The microservice provides the following:

1. Foundational profile system with ability to store/retrieve/update profile details ( such as name, surname, budget )
   against a profileId
2. Ability to trigger analysis with valid profile
3. Ability to retrieve one or list of analyses by owner/viewer

## Running the application

You can run it as a standalone jar in a few different ways:
you can run the docker-compose.yml file located in cybercube-be-task-2023/docker
`
docker-compose up
`
or
`
mvn spring-boot:run
`

Or you can just run it in IntelliJ as a standard SpringBoot configuration. In that case please run the
docker-compose.yml file located in ` cybercube-be-task-service/src/docker/compose/docker-compose.yml ` to run the
database.

The application uses postgresql.


## Cybercube-be-task-service endpoints

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
