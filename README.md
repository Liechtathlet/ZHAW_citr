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

Lokaler-Setup:
----------------
1. Repository clone erzeugen.
2. IDE-Spezifische stubs generieren und Projekt importieren.
    Eclipse: http://edu.panter.ch/MavenEclipse
    Intelij: http://maven.apache.org/plugins/maven-idea-plugin/usage.html
3. Install Android SDK
4. Install IDE-Specific Android-Plugin (Development & Maven-Connector)
