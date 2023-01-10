# RSO: Vsebina microservice

## Prerequisites

```bash
docker run -d --name pg-vsebina -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=vsebina -p 5432:5432 postgres:13
```

## Build and run commands
```bash
mvn clean package
cd api/target
java -jar vsebina-api-1.0.0-SNAPSHOT.jar
```
Available at: localhost:8080/v1/vsebina~~~~

## Run in IntelliJ IDEA~~~~
Add new Run configuration and select the Application type. In the next step, select the module api and for the main class com.kumuluz.ee.EeApplication.

Available at: localhost:8080/v1/vsebina
~~~~
## Docker commands
```bash 
docker build -t vsebina .   ~~~~
docker images
docker run vsebina    
docker tag vsebina gregorzadnik/vsebina   
docker push gregorzadnik/vsebina
docker ps
```

```bash
docker network ls  
docker network rm rso
docker network create rso
docker run -d --name pg-vsebina -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=vsebina -p 5434:5432 --network rso postgres:13
docker inspect pg-vsebina
docker run -p 8082:8082 --network rso -e KUMULUZEE_DATASOURCES0_CONNECTIONURL=jdbc:postgresql://pg-vsebina:5432/vsebina gregorzadnik/vsebina
```

## Consul
```bash
consul agent -dev
```
Available at: localhost:8500

Key: environments/dev/services/vsebina-service/1.0.0/config/rest-properties/maintenance-mode

Value: true or false

## Kubernetes
```bash
kubectl version
kubectl --help
kubectl get nodes
kubectl create -f vsebina-deployment.yaml 
kubectl apply -f vsebina-deployment.yaml 
kubectl get services 
kubectl get deployments
kubectl get pods
kubectl logs vsebina-deployment-6f59c5d96c-rjz46
kubectl delete pod vsebina-deployment-6f59c5d96c-rjz46
```
Secrets: https://kubernetes.io/docs/concepts/configuration/secret/

