rm -r cobertura-report/
rm -r temp
rm cobertura.ser

java -cp lib/cobertura-2.0.3.jar:lib/asm-4.1.jar:lib/asm-tree-4.1.jar:lib/asm-analysis-4.1.jar:lib/asm-commons-4.1.jar:lib/log4j-1.2.9.jar:lib/oro-2.0.8.jar:lib/asm-util-4.1.jar:./temp net.sourceforge.cobertura.instrument.Main --auxClasspath ./temp --destination ./temp "/Users/jallen/Documents/Eclipse-workspace/ScheduleMaker/Unit Testing/bin/Storage"
java -cp lib/cobertura-2.0.3.jar:lib/asm-4.1.jar:lib/asm-tree-4.1.jar:lib/asm-analysis-4.1.jar:lib/asm-commons-4.1.jar:lib/log4j-1.2.9.jar:lib/oro-2.0.8.jar:lib/asm-util-4.1.jar:./temp net.sourceforge.cobertura.instrument.Main --auxClasspath ./temp --destination ./temp "/Users/jallen/Documents/Eclipse-workspace/ScheduleMaker/Unit Testing/bin/ApplicationLogic"

java -cp lib/cobertura-2.0.3.jar:lib/asm-4.1.jar:lib/asm-tree-4.1.jar:lib/asm-analysis-4.1.jar:lib/asm-commons-4.1.jar:lib/log4j-1.2.9.jar:lib/oro-2.0.8.jar:lib/asm-util-4.1.jar:lib/junit-4.11.jar:lib/hamcrest-core-1.3.jar:./temp org.junit.runner.JUnitCore ApplicationLogic.ScheduleMakerControllerDriver

java -cp lib/cobertura-2.0.3.jar:lib/asm-4.1.jar:lib/asm-tree-4.1.jar:lib/asm-analysis-4.1.jar:lib/asm-commons-4.1.jar:lib/log4j-1.2.9.jar:lib/oro-2.0.8.jar:lib/asm-util-4.1.jar:lib/junit-4.11.jar:lib/hamcrest-core-1.3.jar:./temp net.sourceforge.cobertura.reporting.Main --destination ./cobertura-report