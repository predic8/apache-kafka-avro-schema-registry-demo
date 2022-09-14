# Setup

## Herunterladen der Confluent Distribution

Download confluent community distribution from:
https://www.confluent.io/installation/


unzip, navigate into the project and run:

    ./bin/schema-registry-start ./etc/schema-registry/schema-registry.properties



## check for schemas

after Messages have been created on kafka currently registered topics can be inspected using

    curl -X GET http://localhost:8081/subjects