# flexion

##What is it?

This is a java standalone application that implements a billing Integration
that connects to the Flexion platform.

##Installation

Donwload the sources and run 'mvn test' from root directory.
This will run tests from the IntegrationTestRunner class and some custom tests.

You can also run 'mvn install -DskipTests', go to the target directory and run
'java -jar integrationteam-0.0.1-SNAPSHOT.jar'. The app then only launches the
tests from the IntegrationTestRunner class.