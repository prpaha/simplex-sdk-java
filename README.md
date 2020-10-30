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
<li>Add environment variable <b>SimplexAPIKey</b> string type. API key from Simplex service. Require.</li>
<li>Add environment variable <b>SimplexMainNet</b> boolean type. Default TestName (false).</li>
</ul>

</html>
<br/>
<br/>
#simplex-sdk-java
#spring-boot
#gradle
#simplex
#coin-exchange