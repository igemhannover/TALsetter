.PHONY: test

TESTDIR = tale/target/test-classes
CLASSDIR = tale/target/classes

LIBS = /usr/share/java/hamcrest-core.jar
CLASSPATH = $(CLASSDIR):$(TESTDIR):$(LIBS)
CLASSPATH_JUNIT = /usr/share/java/junit.jar
JUNIT = java -cp $(CLASSPATH):$(CLASSPATH_JUNIT) org.junit.runner.JUnitCore

test:
	$(JUNIT) $(shell cd $(TESTDIR); find de -type f | sed 's:/:.:g;s:\.class$$::' )
