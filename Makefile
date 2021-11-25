build:
	cp docker_application.properties src/main/resources/application.properties
	./mvnw install -DskipTests
	docker-compose build

start:
	docker-compose up -d pg
	sleep 5
	docker-compose exec pg psql -c "create database \"accounts\";"
	sleep 2
	docker-compose exec pg psql -c "grant all privileges on database \"accounts\" to root;"
	docker-compose up -d
