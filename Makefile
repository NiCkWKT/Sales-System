all: main.class

main.class:
	javac *.java

run:
	java -cp ./mysql-jdbc.jar: Main

clean:
	rm *.class