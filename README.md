# sberdata
Репозиторий для тестового задания

# Запуск проекта
Преподготовка: 
- установить докер
- установить npm

Для того чтобы развернуть проект необходимо
- запуск производить на linux машине (иначе ругнется)
- после запуска сервера в модуле front выполнить команду npm start (запустится дев сервер с 3000 портом, на него все проксируется)

Другой вариант запуска
- собрать приложение gradl'ом, открыть localhost:8080

Третий вариант - приложен jar file, просто в консоле на машине где есть java выполнить java -jar webdb-0.1.0.jar

после того как удалось развернуть проект в ide просто запустить, база данных - embedded (h2) 
можно посмотреть что в ней зайдя на юрл localhost:8080/h2-console

параметры для базы данных указаны в application.properties 

spring.datasource.url=jdbc:h2:mem:sbr;DB_CLOSE_ON_EXIT=FALSE;
spring.datasource.username=user
spring.datasource.password=pass
spring.datasource.driverClassName=org.h2.Driver


