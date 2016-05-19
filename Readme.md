<img src="http://community.neur.io/uploads/default/201/e73a338e371e3192.png" width="350px" alt="Neurio Logo" />

# Automated Web App Selenium Tests

A Selenium based automated test project for the Neurio Web App.

## Installation

Maven needs to be installed to run this project.

The project uses the following dependencies:
* TestNG 6.9.10
* Selenium 2.53.0
* Maven Surefire Plugin 2.18.1

1. Pull the code from GitHub.

## Usage

Go to the project folder in command prompt.
To run the whole test suite:

    mvn integration-test

To run a single test such as "AdminTest":

    mvn -Dtest=AdminTest test
    
    
All test files are located in com.neurio.tests folder.
To change browsers, edit the config.properties file.
To use Chrome Web Driver, use the Chrome Drivers provided 
in the lib folder. Current Version is v2.21.

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