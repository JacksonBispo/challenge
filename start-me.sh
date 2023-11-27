docker-compose down

docker build -t challenge:latest ../challenge

docker-compose up --build --force-recreate --remove-orphans