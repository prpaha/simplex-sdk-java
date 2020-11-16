<h1>Simplex SDK for JAVA</h1>
Java SDK for service Simplex (https://www.simplex.com/).<br/>
Implementation of contract https://integrations.simplex.com/wallet-api-integration.

<h2>Integration to your project</h2>
<h3>Make commands in new project workspace:</h3>
```shell script
git clone https://github.com/prpaha/simplex-sdk-java.git
cd simplex-sdk-java
gradle clean
gradle build
gradle publishToMavenLocal
```
Add dependency to your project, for example in gradle: 
```yaml
implementation "ru.prpaha.simplex:simplex-sdk-java:0.0.1"
```

<h3>Library Settings:</h3>
Add properties to your project
```yaml
simplex:
  mainNet: false
  apiKey: 'apiKey'
  walletId: 'walletId'
```

<h3>Invoke methods:</h3>
For autoconfiguration import SimplexSDKConfiguration to your Spring:
```java
@Import(SimplexSDKConfiguration.class)
```
Autowired bean ru.prpaha.simplex.service.SimplexService to your code. For example:

```java
@Component
class SomeClass {
    @Autowired
    private SimplexService simplexService;
    
    public void someMethod() {
        simplexService.createQuote(...);
    }
}
```

<br/>
<br/>
#simplex-sdk-java
#spring-boot
#gradle
#simplex
#coin-exchange