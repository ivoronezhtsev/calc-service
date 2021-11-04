### Build and deploy to Kubernetes
```
mvn clean package
docker build -t ivoronezhtsev/calc-service .
kubectl apply -f .\deployment\
kubectl port-forward svc/calc-service 8080:8080
```
