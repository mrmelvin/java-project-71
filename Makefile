run-dist:
	./build/install/app/bin/app

test:
    ./gradlew test

build:
    ./gradlew clean build

.PHONY: build