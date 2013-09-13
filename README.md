citr
=====

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

Lokaler-Setup
----------------
1. Repository clone erzeugen.
2. Variable 'ANDROID_HOME' definieren (SDK).
3. IDE-Spezifische stubs generieren und Projekt importieren.
    Eclipse: http://edu.panter.ch/MavenEclipse
    Intelij: http://maven.apache.org/plugins/maven-idea-plugin/usage.html
4. Install Android SDK
5. Install IDE-Specific Android-Plugin (Development & Maven-Connector)

Build & Installation
----------------
1. AVD Manager starten (Wird im SDK mitgeliefert)
2. Gerät konfigurieren (falls noch nicht erledigt)
3. mvn android:deploy aufrufen

Tests:
----------------
Im Maven-Test-Package sollten nur Tests implementiert werden, welche nicht auf die Android-API zugreifen.
Wenn Maven die Tests ausführt, steht keine vollwertige Android-Umgebung zur Verfügung (nur eine Dummy-API).
Dies führt dazu das die Tests mit Fehlern abbrechen. Solche Tests müssen unterhalb von src/main/java implementiert werden.
