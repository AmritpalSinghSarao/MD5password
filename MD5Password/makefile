JFLAGS = -g
JC = javac
JVM= java
FILE=
.SUFFIXES: .java .class
.java.class: ; $(JC) $(JFLAGS) $*.java
CLASSES = \
    FileSystem.java \
    LogginIn.java \
    GenerateHash.java \
    CreationSystem.java \

MAIN = FileSystem

default: classes

classes: $(CLASSES:.java=.class)

run: classes ; $(JVM) $(MAIN)

clean: ; $(RM) *.class