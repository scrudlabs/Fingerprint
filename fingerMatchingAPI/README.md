This project contains a FingerPrintMatching-micro-services developped in java/jee using springboot

Build a micro-service using Maven on a terminal : mvn clean install

Launch a micro-service using Java on a terminal : java -jar target/\*-0.0.1-SNAPSHOT.jar

It is also possible to run a packaged micro-service with remote debugging support enabled :
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar target/\*-0.0.1-SNAPSHOT.jar
Launch a micro-service using Maven on a terminal : mvn spring-boot:run

Micro-service fingerMatchingAPI :

Le micro-service est disponible sur le port 8084 au démarrage

Vous pouvez visualiser les informations de l'api via l'interface swagger :
http://localhost:8084/v2/api-docs
Test des services avec Postman :
Enregistrer le json présent à la page http://localhost:8084/v2/api-docs dans un fichier
fingermatching.json
Avec Postman : Import -> Sélectionner le fichier
A gauche dans l'onglet Collections, vous aurez vos ressources rest à consommer

Image docker postgresql

    docker pull postgres

	docker run --name fg_matching_postgresql -p 9111:9111 -e POSTGRES_PASSWORD=password --network="bridge" -d postgres

    Vous pouvez vous connecter avec les infos ci-dessous:

	user : postgres
	password : password
	url : localhost:5432
	
	
	docker exec -it fg_matching_postgresql bash 
    su -  postgres
    psql postgres

	-- Créer la base de données et le user
	CREATE DATABASE fingerMatchingdb;
	CREATE USER fg_matching_user WITH ENCRYPTED PASSWORD 'fg_matching_pwd';
	GRANT ALL PRIVILEGES ON DATABASE fingerMatchingdb TO fg_matching_user;
	
	\dn - Retrieve tables
	\du - Retrieve users
	
	
Image docker fg_matching_api :
	To build the docker Image : ./mvnw -Dmaven.test.skip=true
	docker run --name fg_matching_api -p 8084:8084 --network="bridge" fg_matching_api

Test service :

- storeUserFingerPrintInformations
  Body : JSON
  {
  "fingerPrintImage": "",
  "passportImage": ""
  }
  NB: Les valeurs de tests sont dans les fichiers src/test/resources/\*StringImageBase64Encoding.txt

- getUserInformations
  Body : JSON
  {
  "userFingerPrint": ""
  }
