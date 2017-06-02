Class Orchestration REST API
=========================


## Run application
To run application, call:
```
sbt run
```

## Create Docker container
You can create a docker container with the api with:
```
sbt docker:publishLocal
```


## Run the API as a service

You will need docker-composer` to run the API.
As a prerequisite you need to create the database first with your credentials.
See [create_database.sql](/create_database.sql).
Be sure to change the default credentials.

```
docker-compose run db web
```

## Run tests
To run tests, call:
```
sbt test
```
