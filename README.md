# Konfi - java properties library
[![build](https://github.com/nirro01/konfi/actions/workflows/push-on-main-branch.yml/badge.svg?branch=main)](https://github.com/nirro01/konfi/actions/workflows/push-on-main-branch.yml)
[![codecov](https://codecov.io/github/nirro01/konfi/branch/main/graph/badge.svg?token=0R98PT13JR)](https://codecov.io/github/nirro01/konfi)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.nirro01/konfi-core.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.nirro01%22%20AND%20a:%22konfi-core%22)
[![javadoc](https://javadoc.io/badge2/io.github.nirro01/konfi-core/javadoc.svg)](https://javadoc.io/doc/io.github.nirro01/konfi-core)
![java](https://img.shields.io/badge/java-%3E%3D17-brightgreen)


## Main features
- Clean type safe interface based properties
- Optional values
- Set and List collections
- Refresh properties without restarting your app
- Properties sources  
    - OS environment variables
    - System properties
    - File packaged inside your jar (attached resources)
    - File outside your Jar
    - Custom source by implementing the Source interface

### Getting started  
1. Add a dependency 
###### Apache Maven
```xml
  <dependency>
    <groupId>io.github.nirro01</groupId>
    <artifactId>konfi-core</artifactId>
    <version>0.0.1</version>
  </dependency>
```
###### Gradle Groovy DSL
```groovy
implementation 'io.github.nirro01:konfi-core:0.0.1'
```
###### Gradle Kotlin DSL
```kotlin
implementation("io.github.nirro01:konfi-core:0.0.1")
```

2. Define your properties definitions using the @KonfiProperty annotation
```java
public interface AmusementParkProperties {

    @KonfiProperty(key = "amusement.park.name", description = "Amusement park name")
    String name();
    
    @KonfiProperty(key = "amusement.park.phone-numbers", description = "Amusement park phone numbers")
    Set<String> phoneNumbers();
    
    @KonfiProperty(key = "amusement.park.email", description = "Amusement park email address")
    Optional<String> email();
    
    @KonfiProperty(key = "amusement.park.roller-coaster.minimum-height-cm", description = "Minimum height in centimeters for using the roller coaster")
    
    Integer rollerCoasterMinimumHeight();
    @KonfiProperty(key = "amusement.park.roller-coaster.active", description = "Roller coaster active")
    Boolean rollerCoasterActive();
}
```
3. Define ordered list of properties sources
``` java
  var environmentVariablesSource = PropertiesSources.newEnvironmentVariablesSource();
  var systemPropertiesSource = PropertiesSources.newSystemPropertiesSource();
  var externalFileSource = PropertiesSources.newExternalFileSource("app.properties");
  var internalFileSource = PropertiesSources.newInternalFileSource("app.properties");
  var sources = List.of(environmentVariablesSource, systemPropertiesSource, externalFileSource, internalFileSource)
```
4. Build instance of your interface
``` java
  AmusementParkProperties amusementParkProperties = Konfi
          .builder(AmusementParkProperties.class)
          .sources(sources)
          .build();
  String name = amusementParkProperties.name();
  System.out.println(name);
```
5. Refresh properties on demand
``` java
  Konfi.refresh(amusementParkProperties);
  System.out.println(amusementParkProperties.name());
```
