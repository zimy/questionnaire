all: jar
	
clean:
	./gradlew --daemon clean

dock:
	./gradlew --daemon dock

jar:
	./gradlew --daemon assemble
