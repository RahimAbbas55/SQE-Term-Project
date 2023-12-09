# SQE-Term-Project
#Addition of new files

Steps to run the SpringBoot Application: -

•	Choose the appropriate SDK.
•	Changed “execute” to “executequery” on line 240 in file “AdminController.java”.
•	Edited the configurations and change the working directory to $MODULE_WORKING_DIR$ macro.
•	Edited Configuration
•	Installed mysql, installed dbeaver, tested connection, created new    database with name “ecommjava”.
•	Imported the “basedata.sql” script in dbeaver.
•	Removed all the modes except “IGNORE_SPACE” from 2nd line of the script, because they are not allowed in mysql 8.0.
•	Open the local host to run.


### Tests
Steps to run all test cases or a specific one through Maven Commands
1) Open the Maven Portal in Intellij
   ![image](https://github.com/Funkydude6103/Assets/blob/main/i1.png)
2) CLick on Execute Maven Goal
   ![image](https://github.com/Funkydude6103/Assets/blob/main/i2.png)
3) Then you can type the following commands in the opened portal to run all Tests or some Specific ones.
4) Following are the commands:

Run all the unit test classes.
$ mvn test
Run a single test class.
mvn -Dtest=TestApp1 test
Run multiple test classes.
$ mvn -Dtest=TestApp1,TestApp2 test
Run a single test method from a test class.
$ mvn -Dtest=TestApp1#methodname test
Run all test methods that match pattern 'testHello*' from a test class.
$ mvn -Dtest=TestApp1#testHello* test
Run all test methods match pattern 'testHello*' and 'testMagic*' from a test class.
$ mvn -Dtest=TestApp1#testHello*+testMagic* test


#added maven file
