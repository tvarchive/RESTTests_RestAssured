Testing REST Services using REST-assured & Jackson
=====================


##Objective##
Intent of this project is to illustrate REST services testing using REST-assured (https://code.google.com/p/rest-assured/) and Jackson (http://wiki.fasterxml.com/JacksonInFiveMinutes) in Java world

###This project illustrate four types of service tests###
* Contract Tests - Tests which would fail when contract changes
* Positive Tests - Tests which assert Json response
* Negative Tests - Tests which check for service response when invalid parameters are passed
* Boundary Tests - Tests which check behaviour of tests at boundary values of parameters


##Stack##
This java project has been created using IntelliJ IDEA community edition. REST-assured and Jackson have been included in project via maven. Tests have been written in JUnit.


##Different test styles##
Using REST-assured and Jackson there are many ways to write tests. The test class 'TestStyles.java' illustrates the same. Depending on your requirement choose appropriate one.

##Tests using JsonPath##
Test class 'JsonPathIllustrationTests.java' illustrates usage of JsonPath to parse through Json to extract portions of respose

##Tests using Jackson mapper##
Test class 'JacksonObjectMapperIllustrationTests.java' illustrates using Jackson object mapper to convert Json string into Java object, then performing various assertions on java object. This approach is handy for contract tests.
