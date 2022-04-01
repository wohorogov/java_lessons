.DEFAULT_GOAL := build-run
clean:
		rm -rf ./target
compile: clean
		mkdir -p ./target/classes
		javac -d ./target/classes ./src/main/java/games/Slot.java;
run:
		java -jar ./target/games.jar;
build-run: build run
build: clean compile
		jar cfe ./target/games.jar games.Slot -C ./target/classes .