# UNIPACK 3

Yes

## Building

1. Create a folder "libs" in the project root directory (next to "gradle", "src", etc.)
2. Download Connector Extras: https://modrinth.com/mod/connector-extras/version/1.11.2+1.20.1
3. Open or extract the Connector Extras JAR as a ZIP file
4. Navigate to `<ConnectorExtras.jar>/META-INF/jarjar`
5. Copy "geckolib-fabric-compat-1.11.2+1.20.1.jar" and paste into the "libs" folder created in step 1
6. `./gradlew build`
