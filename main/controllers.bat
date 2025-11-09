# Шаг 0: Создаем кластер
minikube start --driver=docker --container-runtime=docker
minikube image load busybox:1.36

# Шаг 1: Запускаем все контроллеры
kubectl apply -f controllers.yaml

# Шаг 2: DaemonSet - проверяем Pod на каждом узле
kubectl get pods -l app=hello-daemon -o wide
kubectl logs -l app=hello-daemon --tail=2

# Шаг 3: StatefulSet - проверяем имена Pod'ов
kubectl get pods -l app=hello-stateful
kubectl delete pod hello-statefulset-1
kubectl get pods -l app=hello-stateful -w

# Шаг 4: Job - проверяем выполнение
kubectl get jobs
kubectl logs -l job-name=hello-job

# Шаг 5: CronJob - проверяем расписание
kubectl get jobs
kubectl logs -l job-name=time-cronjob-<timestamp>

# Шаг 6: Ручной запуск CronJob
kubectl create job --from=cronjob/time-cronjob test-manual-run
kubectl logs -l job-name=test-manual-run

# Шаг 7: Очистка
kubectl delete -f controllers.yaml