run-dist:
	./build/install/app/bin/app

test:
    gradle test

build:
    ./gradlew clean build

.PHONY: build