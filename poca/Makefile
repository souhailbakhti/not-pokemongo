.PHONY: client server run-client run-server

all: client server

install:
	mkdir -p install
	mkdir -p install/client
	mkdir -p install/server

client: install
	sbt "project client" assembly
	rm -rf install/client/data/
	cp -r client/src/main/resources/data/ install/client/data/
	cp client/target/scala*/poca-client.jar install/client

server: install
	sbt "project server" assembly
	cp server/target/scala*/poca-server.jar install/server

run-client: client
	java -jar install/client/poca-client.jar

run-server: server
	java -jar install/server/poca-server.jar
