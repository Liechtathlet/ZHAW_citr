citr
=====
Hinweis: Für die korrekte Darstellung der Settings, bitte im Text-Mode anschauen.

ZHAW - Methoden der Programmierung - Fallstudie "citr"

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

System-Voraussetzungen
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
6. local.properties definieren (gemäss Vorlage)

Google-Cloud-Messaging:
----------------
1. SHA-Key der Developer-Version in der Developer-Console hinterlegen (http://developer.android.com/google/gcm/gs.html)
2. IP-Adresse eintragen: https://cloud.google.com/console?redirected=true#/project/apps~citr-zhaw/apiui/credential
3. Google-Play-Services SDK installieren (http://developer.android.com/google/play-services/setup.html)
5. Libs von "citr\libs\m2Repo" ins lokale Maven-Repository kopieren.

OAuth:
----------------
1. App in Developer Console für OAuth registrieren.
2. 

Build & Installation (Common)
----------------
1. mvn clean install

Build & Installation (Android)
----------------
1. AVD Manager starten (Wird im SDK mitgeliefert)
2. Gerät konfigurieren (falls noch nicht erledigt)
3. mvn clean install android:deploy
4. mvn clean test -P "integration"
5. mvn clean release -P "release" (Release only!)
   http://maven.apache.org/maven-release/maven-release-plugin/examples/prepare-release.html

Build & Installation (Server)
----------------
1. Tomcat-Server & MySQL starten
0. citr-common mit mvn clean install ausführen
2. mvn clean install tomcat7:deploy
3. mvn clean test -P "integration"
4. mvn clean release -P "release" (Release only!)
   http://maven.apache.org/maven-release/maven-release-plugin/examples/prepare-release.html


Die Datenbank wird beim Deployment initial automatisch erzeugt.


Tests:
----------------
Im Maven-Test-Package sollten nur Tests implementiert werden, welche nicht auf die Android-API zugreifen.
Wenn Maven die Tests ausführt, steht keine vollwertige Android-Umgebung zur Verfügung (nur eine Dummy-API).
Dies führt dazu das die Tests mit Fehlern abbrechen. Solche Tests müssen unterhalb von src/main/java implementiert werden.

Ansonsten erfolgt die Implementation der Unit-Tests mit JUnit und Mockito. Die Integrations-Tests sollen so umfassend als möglich gestaltet werden.


Development
----------------
Umgebungsabhängige Properties werden unter src/main/config im entsprechenden Property-File definiert. Diese werden während dem Build-Prozess im entsprechenden Property-File ersetzt.
