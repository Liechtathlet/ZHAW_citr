citr
=====
Hinweis: Für die korrekte Darstellung der Settings, bitte im Text-Mode anschauen.

ZHAW - Methoden der Programmierung - Fallstudie "citr"

Offene Punkte (Server)
----------------
Maven: 
-Site-Generation (BBwM: Chapter 6.2)
-Testing (BBwM: Chapter 4.13)
-Release (BBwM: Chapter 7.8)
-Replace-Properties ?
Diverses:
-Logging with Log4J
-Spring: ApplicationContext, Spring-Security
-JPA: Persistence.xml
-Package structure (inkl. Test)
-Initial Jersey configuration

Offene Punkte (App)
----------------
Maven:
-Site-Generation
-Testing
-Release
-Replace-Properties?
Diveres:
-Android configuration
-Logging
-Connection to Server
-Package structure

Initialer Setup
----------------
1. Android-App
mvn archetype:generate \
  -DarchetypeArtifactId=android-quickstart \
  -DarchetypeGroupId=de.akquinet.android.archetypes \
  -DarchetypeVersion=1.0.11 \
  -DgroupId=ch.zhaw.mdp.lhb.citr \
  -DartifactId=citr-app
2. Java-Server
http://persistentdesigns.com/wp/jersey-spring-and-jpa/

Voraussetzungen
----------------
- Maven 
- Android SDK 
- Tomcat-Server  (V. 7, auf Standard-Port 8080)
- MySQL-Server

Lokaler-Setup
----------------
1. Repository clone erzeugen.
2. Variable 'ANDROID_HOME' definieren (SDK).
3. IDE-Spezifische stubs generieren und Projekt importieren.
    Eclipse: http://edu.panter.ch/MavenEclipse
    Intelij: http://maven.apache.org/plugins/maven-idea-plugin/usage.html
4. Tomcat: tomcat-users.xml (hinzufügen):
   <role rolename="manager-script"/>
   <user username="USERNAME" password="PASSWORD" roles="manager-script"/>
5. Maven: settings.xml (hinzufügen):
    <servers>
     <server>
      <id>tomcat-local</id>
      <username>USERNAME</username>
      <password>PASSWORD</password>
    </server>  
  </servers>

Build & Installation (Android)
----------------
1. AVD Manager starten (Wird im SDK mitgeliefert)
2. Gerät konfigurieren (falls noch nicht erledigt)
3. mvn android:deploy

Build & Installation (Server)
----------------
1. Tomcat-Server & MySQL starten
2. mvn tomcat7:deploy

Tests:
----------------
Im Maven-Test-Package sollten nur Tests implementiert werden, welche nicht auf die Android-API zugreifen.
Wenn Maven die Tests ausführt, steht keine vollwertige Android-Umgebung zur Verfügung (nur eine Dummy-API).
Dies führt dazu das die Tests mit Fehlern abbrechen. Solche Tests müssen unterhalb von src/main/java implementiert werden.
