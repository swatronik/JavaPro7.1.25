# Шаг 0: Создаем кластер
minikube start --driver=docker --container-runtime=docker

# 1. Применяем все ресурсы
kubectl apply -f controllers.yaml

# 2. Проверяем созданные ресурсы
kubectl get namespace quota-lab
kubectl get resourcequota,limitrange,pods -n quota-lab

# 3. Проверяем детали
kubectl describe resourcequota lab-quota -n quota-lab
kubectl describe limitrange lab-limits -n quota-lab
kubectl get pods -n quota-lab -o wide

# 4. Очистка
kubectl delete -f controllers.yaml