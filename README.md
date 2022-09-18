# Konfi - java properties library
[![build](https://github.com/nirro01/konfi/actions/workflows/push-on-main-branch.yml/badge.svg?branch=main)](https://github.com/nirro01/konfi/actions/workflows/push-on-main-branch.yml)
[![codecov](https://codecov.io/github/nirro01/konfi/branch/main/graph/badge.svg?token=0R98PT13JR)](https://codecov.io/github/nirro01/konfi)

## Main features
- Clean type safe interface based properties
- Optional values supported
- Set and List collections supported
- Refresh properties without restarting your app
- Properties sources  
    - OS environment variables
    - System properties
    - File packaged inside your jar (attached resources)
    - File outside your Jar
    - Hard coded Map
    - Custom source by implementing the Source interface

### Getting started  
1. Define your properties definitions using the @KonfiProperty annotation
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
2. Define your properties sources
``` java
  var environmentVariablesSource = PropertiesSources.newEnvironmentVariablesSource();
  var systemPropertiesSource = PropertiesSources.newSystemPropertiesSource();
  var externalFileSource = PropertiesSources.newExternalFileSource("app.properties");
  var internalFileSource = PropertiesSources.newInternalFileSource("app.properties");
  var sources = List.of(environmentVariablesSource, systemPropertiesSource, externalFileSource, internalFileSource)
```
3. Build instance of your interface
``` java
  AmusementParkProperties amusementParkProperties = Konfi
          .builder(AmusementParkProperties.class)
          .sources(sources)
          .build();
  String name = amusementParkProperties.name();
  System.out.println(name);
```
5. Refresh properties
``` java
  Konfi.refresh(amusementParkProperties);
  System.out.println(amusementParkProperties.name());
```
