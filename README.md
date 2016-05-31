<img src="http://community.neur.io/uploads/default/201/e73a338e371e3192.png" width="350px" alt="Neurio Logo" />

# Automated Web App Selenium Tests

A Selenium based automated test project for the Neurio Web App.

## Installation

Maven, Python and a web browser such as Mozilla Firefox needs to be 
installed to run this project.

The qa project(https://github.com/neurio/qa) must also be installed 
on your system.


The project uses the following dependencies:
* TestNG 6.9.10
* Selenium 2.53.0
* Maven Surefire Plugin 2.18.1

1. Pull the code from GitHub and put it in the same directory as 
the qa project.
2. Set a System Variable on your system called "PROJECT_PATH", where
the selenium-web and qa projects are located, such as, 
"C:\Users\Me\Projects".
3. Check the config.properties and make sure the settings are correct 
such as Operating System (os).

## Usage

Go to the project folder in command prompt.
To run the whole test suite:

    mvn integration-test

To run a single test such as "AdminTest":

    mvn -Dtest=AdminTest test
    
    
All test files are located in com.neurio.tests folder.
To change browsers, edit the config.properties file.
After a test run, the HTML Test Report will be located in 
/target/surefire-reports/emailable-report.html.
Screenshots will be saved in the /target/web-failure-screenshots folder.
Test Failure Screenshots will be saved in the /target/web-screenshots
folder


## Contributing

Fork it!
Create your feature branch: git checkout -b my-new-feature
Commit your changes: git commit -am 'Add some feature'
Push to the branch: git push origin my-new-feature
Submit a pull request

## History

Version 1.0 - May 18, 2016

## Credits

Robert T.

## License

Copyright 2016 Robert T. robert@neur.io

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
with the License. You may obtain a copy of the License at: http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is
distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and limitations under the License.