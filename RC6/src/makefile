all: compile run
compile:
	javac Conversion.java
	javac Encryption.java
	javac Decryption.java
	javac Results.java
	javac KeySchedule.java
	javac Driver.java

clean:
	rm -f Conversion.class Encryption.class Decryption.class Results.class KeySchedule.class Driver.class

run:
	java Driver $(FILE1) $(FILE2)