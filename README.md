<html>
<h1>Simplex SDK for JAVA</h1>
Java SDK for service Simplex (https://www.simplex.com/).<br/>
Implementation of contract https://integrations.simplex.com/wallet-api-integration.

<h3>Integration to your project</h3>
<h4>Make commands in new project workspace:</h4>
<ul>
<li>git clone https://github.com/prpaha/simplex-sdk-java.git</li>
<li>cd simplex-sdk-java</li>
<li>gradle clean</li>
<li>gradle build</li>
<li>gradle publishToMavenLocal</li>
<li>add dependency to your project, for example in gradle: implementation "ru.prpaha.simplex:simplex-sdk-java:0.0.1"</li>
</ul>

<h4>Library Settings:</h4>
<ul>
<li>Add environment variable <b>SIMPLEX_API_KEY</b> string type. API key from Simplex service. Require.</li>
<li>Add environment variable <b>SIMPLEX_MAIN_NET</b> boolean type. Default TestName (false).</li>
<li>Add environment variable <b>SIMPLEX_WALLET_ID</b> string type.</li>
</ul>

<h4>Invoke methods:</h4>
For autoconfiguration import SimplexSDKConfiguration to your Spring (@Import(SimplexSDKConfiguration.class)).
<br/>
Autowired bean ru.prpaha.simplex.service.SimplexService to your code. For example:
<br/><br/>
@Component<br/>
class SomeClass {<br/>
    @Autowired<br/>
    private SimplexService simplexService;<br/>
    <br/>
    public void some method() {<br/>
        simplexService.createQuote(...);
    }<br/>
}
 

</html>
<br/>
<br/>
#simplex-sdk-java
#spring-boot
#gradle
#simplex
#coin-exchange