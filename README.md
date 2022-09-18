# Konfi - java properties library

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
  var environmentVariablesRepo = Repositories.newRepository(Sources.newEnvironmentVariablesSource());
  var systemPropertiesRepo = Repositories.newRepository(Sources.newEnvironmentVariablesSource());
  var defaultProperties = Repositories.newRepository(Sources.newResourcesSource("app.properties"));
  var repos = List.of(environmentVariablesRepo, systemPropertiesRepo, defaultProperties)
```
3. Build instance of your interface
``` java
  AmusementParkProperties amusementParkProperties = Konfi
          .builder(AmusementParkProperties.class)
          .repositories(repos)
          .build();
  String name = amusementParkProperties.name();
  System.out.println(name);
```
4. Refresh a single repository
``` java
  environmentVariablesRepo.refresh();
  System.out.println(amusementParkProperties.name());
```
5. Refresh all the repositories
``` java
  Konfi.refreshAll(amusementParkProperties);
  System.out.println(amusementParkProperties.name());
```