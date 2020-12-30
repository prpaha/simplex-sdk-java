Simplex SDK for JAVA
======

Java SDK for service [Simplex](https://www.simplex.com/).
Implementation of [contract](https://integrations.simplex.com/wallet-api-integration)

## Integration to your project
### Make commands in new project workspace:
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

### Library Settings:
Add properties to your project
```yaml
simplex:
  mainNet: false
  apiKey: 'apiKey'
  walletId: 'walletId'
  partnerName: 'partnerName'
  partnerUrl: 'partnerUrl'
  successOperationUrl: 'successUrl'
  failOperationUrl: 'failUrl'
```
In properties `successOperationUrl` and `failOperationUrl` you can add the placeholder `{placeholder}` and the SDK will insert the paymentId there.

### Invoke methods:
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