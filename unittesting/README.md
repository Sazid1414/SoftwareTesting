# Testing Arithmetic Operations

## Purpose of this module

This module is here to let you see how basic arithmetic operations are tested.

## Getting Started

First you need to clone the repository in a folder of your preference. Then through the command line head to the root directory of your project and 
execute the command:
```
mvn clean package jacoco:report
```
This will let you package the project, see how the classes are being tested,  and generate a code coverall report in the folder :
```
target/site/jacoco
```

## Mutation Testing with PIT

This project now includes mutation testing using PIT (PiTest) to evaluate the quality of your unit tests.

### Running Mutation Testing

You can run mutation testing in several ways:

**Option 1: Using the provided script**
```bash
./run-mutation-tests.sh
```

**Option 2: Using Maven directly**
```bash
mvn org.pitest:pitest-maven:mutationCoverage
```

**Option 3: As part of the build process**
```bash
mvn clean test org.pitest:pitest-maven:mutationCoverage
```

### Viewing Mutation Testing Reports

After running mutation testing, reports will be generated in:
- **HTML Report**: `target/pit-reports/index.html` (open in browser for detailed view)
- **XML Report**: `target/pit-reports/mutations.xml` (for CI/CD integration)

### Understanding Mutation Testing

Mutation testing works by:
1. Creating small changes (mutations) in your source code
2. Running your test suite against each mutation
3. Checking if your tests can detect the mutations (kill the mutants)
4. Providing a mutation score indicating test quality

A high mutation score indicates that your tests are effective at detecting bugs.

### PIT Configuration

The mutation testing is configured with:
- **Target Classes**: All classes in `src/main/java`
- **Test Classes**: All classes ending with `Test`
- **Mutators**: Default set of mutators
- **Output**: HTML and XML reports
- **Threads**: 4 parallel threads for faster execution

You can customize the configuration in `pom.xml` under the PIT plugin section or in `pitest.properties`.

### Arithmetic Operations Testing

The current module uses certain dependencies and plugins to test common Arithmetic Operations between numbers or arrays. 
**Mockito Dependency**
```
	<dependency>
		<groupId>org.mockito</groupId>
		<artifactId>mockito-core</artifactId>
		<version>2.27.0</version>
		<scope>test</scope>
	</dependency>
```

**Jacoco plugin**
```
	<plugin>
		<groupId>org.jacoco</groupId>
		<artifactId>jacoco-maven-plugin</artifactId>
		<version>0.8.3</version>
		<executions>
			<execution>
				<id>prepare-agent</id>
				<goals>
					<goal>prepare-agent</goal>
				</goals>
			</execution>
		</executions>
	</plugin>

```

**Coveralls plugin**
```
	<plugin>
		<groupId>org.eluder.coveralls</groupId>
		<artifactId>coveralls-maven-plugin</artifactId>
		<version>4.3.0</version>
	</plugin>
```

In order to see the usage of this module, after building it, you need to will be able to see how the 
classes are tested and the following Jacoco report.

## Built With

* [Mockito](https://mvnrepository.com/artifact/org.mockito/mockito-all) - Repository used for mock testing
* [JUnit 4.12](https://mvnrepository.com/artifact/junit/junit/4.12) - Repository used for testing
* [Maven](https://maven.apache.org/) - Dependency Management
* [PIT (PiTest)](http://pitest.org/) - Mutation testing framework for Java
