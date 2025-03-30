# UNIPACK 3

Yes

## Building

1. Create a folder "libs" in the project root directory (next to "gradle", "src", etc.)
2. Download Connector Extras: https://modrinth.com/mod/connector-extras/version/1.11.2+1.20.1
3. Open or extract the Connector Extras JAR as a ZIP file
4. Navigate to `<ConnectorExtras.jar>/META-INF/jarjar`
5. Copy "geckolib-fabric-compat-1.11.2+1.20.1.jar" and paste into the "libs" folder created in step 1
6. `./gradlew build`

## Things it does

- adds a /dump_registry command (logs every identifier in the provided registry to "registry.txt")
  - for `minecraft:enchantment`, also logs the max level of each enchantment
- adds a /pvp command (for toggling pvp per-player)
- fixes a bug in Biome Makeover that causes an internal server error
- fixes a bug in Blood Magic that causes client/server entity data desyncs
- fixes a bug in Caupona that causes an internal server error
- fixes an incompatibility in Complex Hex when used with Sinytra Connector that causes a startup crash
- fixes a bug in Corail Backpack when used with Accessories that causes it to not work
- fixes a bug in Create when used with Stacker (bigger stack sizes mod)
- fixes a bug in Destroy that causes a client crash
- disables extraneous logging in Dynamic Surroundings
- disables extra mob aggression when wearing the Ring of the Seven Curses from Enigmatic Legacy (evil mod)
- fixes a bug in GeckoLib when using the Fabric version on Forge with Sinytra Connector
- fixes a bug in Hex Casting when using Botania's Loonium that causes a server crash
- fixes an incompatibility in Hex Casting when used with Sinytra Connector that causes a startup crash
- allows Jade's mod name tooltips to appear even when EMI is installed (since i prefer Jade's implementation)
- fixes a weird server crash with Spectrum and some other unidentified mod
- allows using a hoe to till mycelium and podzol
- fixes a bug in Petrolpark's Library that causes a datapack reload crash
- fixes an incompatibility in Projectile Damage when used with Sinytra Connector that causes a startup crash
- tries to fix something with Your Reputation (Jade addon) but does not do so successfully
- disables all of Spectrum's darkness effects in its dimension as they do not work properly for me and make the dimension innavigable
- fixes MC-122477
- adds a tooltip to the "Shrinking Device" from "Shrink." that says it does not require power
- prints "unilock was here" to the log during startup
