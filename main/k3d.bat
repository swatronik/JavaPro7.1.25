# 0. Создание кластера
minikube start --driver=docker --container-runtime=docker
minikube image load nginx:1.23

# Проверка
kubectl cluster-info
kubectl get nodes

# 1. Создание Pod
kubectl apply -f pod.yaml

# Проверка
kubectl get pods
kubectl describe pod nginx-pod

# 2. Удаление Pod и проверка, что он не восстанавливается
kubectl delete pod nginx-pod
kubectl get pods  # Pod должен исчезнуть навсегда

# 3. Создание Deployment
kubectl apply -f deployment.yaml

# Проверка
kubectl get deployments
kubectl get pods -l app=nginx

# 4. Удаление Pod под управлением Deployment
kubectl get pods -l app=nginx
kubectl delete pod <pod-name>
kubectl get pods -l app=nginx  # Должен создать новый автоматически

# 5. Масштабирование
kubectl scale deployment nginx-deployment --replicas=3
kubectl get pods -l app=nginx  # Должно быть 3 Pod'а

kubectl scale deployment nginx-deployment --replicas=1
kubectl get pods -l app=nginx  # Должен остаться 1 Pod

# 6. Обновление версии
kubectl set image deployment/nginx-deployment nginx=nginx:1.22
kubectl rollout status deployment/nginx-deployment  # Мониторинг обновления
kubectl get pods -l app=nginx  # Проверка новых Pod'ов

# 7. Проверка истории
kubectl rollout history deployment/nginx-deployment