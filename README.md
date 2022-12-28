# RSO: Artikli metadata microservice

## Prerequisites

```bash
docker run -d --name pg-artikli -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=artikli -p 5432:5432 postgres:13
```

## Build and run commands
```bash
mvn clean package
cd api/target
java -jar artikli-api-1.0.0-SNAPSHOT.jar
```
Available at: localhost:8080/v1/artikli~~~~

## Run in IntelliJ IDEA~~~~
Add new Run configuration and select the Application type. In the next step, select the module api and for the main class com.kumuluz.ee.EeApplication.

Available at: localhost:8080/v1/artikli
~~~~
## Docker commands
```bash 
docker build -t artikli .   ~~~~
docker images
docker run artikli    
docker tag artikli fdemsar/artikli   
docker push fdemsar/artikli
docker ps
```

```bash
docker network ls  
docker network rm rso
docker network create rso
docker run -d --name pg-artikli -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=artikli -p 5432:5432 --network rso postgres:13
docker inspect pg-artikli
docker run -p 8080:8080 --network rso -e KUMULUZEE_DATASOURCES0_CONNECTIONURL=jdbc:postgresql://pg-artikli:5432/artikli fdemsar/artikli
```

## Consul
```bash
consul agent -dev
```
Available at: localhost:8500

Key: environments/dev/services/artikli-service/1.0.0/config/rest-properties/maintenance-mode

Value: true or false

## Kubernetes
```bash
kubectl version
kubectl --help
kubectl get nodes
kubectl create -f artikli-deployment.yaml 
kubectl apply -f artikli-deployment.yaml 
kubectl get services 
kubectl get deployments
kubectl get pods
kubectl logs artikli-deployment-6f59c5d96c-rjz46
kubectl delete pod artikli-deployment-6f59c5d96c-rjz46
```
Secrets: https://kubernetes.io/docs/concepts/configuration/secret/

