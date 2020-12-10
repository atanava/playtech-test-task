Playtech.com Test Task
===============================
#### Task description
Create automated test for form at https://docs.google.com/forms/d/e/1FAIpQLScVG7idLWR8sxNQygSnLuhehUNVFti0FnVviWCSjDh-JNhsMA/viewform?usp=sf_link
Use Selenium java library.
 
Create automated test to cover all features of this form. 
Test negative cases for field validations, incomplete form submition etc.
Test should output human readable report file

#### Fulfillment
Prepared 2 classes for tests:
- FormElementsTest.java for testing the behavior of page elements
- FormSubmitTest.java for testing page logic
#### Technologies used
- Java 11
- [Selenium WebDriver](https://www.selenium.dev/documentation/en/)
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)
- [JUnit 4](https://mvnrepository.com/artifact/junit/junit)
- [SLF4J API Module](https://mvnrepository.com/artifact/org.slf4j/slf4j-api)
- [Logback Classic Module](https://mvnrepository.com/artifact/ch.qos.logback/logback-classic)
#### Run
Before running, create a file log/playtech.log in the root directory to record the report.

