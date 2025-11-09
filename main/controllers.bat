# Шаг 0: Создаем кластер и включаем ingress
minikube start --driver=docker --container-runtime=docker
minikube image load nginx:1.23
minikube image load busybox:1.36
minikube addons enable ingress

# Шаг 1: Применяем все ресурсы
kubectl apply -f controllers.yaml

# Шаг 2: Проверяем создание всех ресурсов
kubectl get cm,secret,pvc,pod,svc,ing

# Шаг 3: Проверка ConfigMap
kubectl exec -it configmap-test-pod -c writer -- sh -c "env | grep APP_"

# Шаг 4: Проверка Secret
kubectl exec -it secret-test-pod -- cat /etc/secrets/DB_PASS

# Шаг 5: Проверка PVC
kubectl exec -it pvc-test-pod -- tail -f /data/log.txt

# Шаг 6: Проверка комплексного Deployment
# Ждем пока Pod запустится
kubectl get pods -l app=demo-web -w
# Проверяем файл index.html
kubectl exec -it $(kubectl get pods -l app=demo-web -o jsonpath='{.items[0].metadata.name}') -c writer -- cat /data/index.html

# Шаг 7: Проверка Ingress
minikube service demo-service --url

# Шаг 8: Очистка
kubectl delete -f controllers.yaml