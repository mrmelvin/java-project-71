run-dist:
	./build/install/app/bin/app

test:
	./gradlew test

build:
	./gradlew clean build

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

.PHONY: build
