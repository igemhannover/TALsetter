.PHONY: test

LIBS = /usr/share/java/hamcrest-core.jar
CLASSPATH = tale/target/classes:tale/target/test-classes:$(LIBS)
CLASSPATH_JUNIT = /usr/share/java/junit.jar
JUNIT = java -cp $(CLASSPATH):$(CLASSPATH_JUNIT) org.junit.runner.JUnitCore

test:
	@$(JUNIT) \
		de.uni_hannover.igem.actions.ExactScanTest \
		de.uni_hannover.igem.actions.NucleaseScanTest \
		de.uni_hannover.igem.util.ConstantsTest
