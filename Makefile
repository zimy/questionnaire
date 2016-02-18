all: jar

jar:
	./gradlew --daemon assemble
