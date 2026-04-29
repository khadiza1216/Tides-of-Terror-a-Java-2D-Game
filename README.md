# Tides of Terror (Java 2D)

A simple Java 2D underwater survival game built with Swing/AWT. Dodge enemies, survive as long as you can, and use power-ups to keep going.

## Requirements

- Java (JDK) 8+ recommended

## How to run

### Option 1: Run from source (recommended)

From this project folder:

```bash
javac *.java
java Main
```

### Option 2: Run from your IDE

Open the folder in IntelliJ/Eclipse/VS Code and run `Main.java`.

## Controls

Controls are handled by the game window (keyboard). If your build differs, check `UnderwaterSurvival.java` for the current key bindings.

## Project structure

- **Entry point**: `Main.java`
- **Game loop / main panel**: `UnderwaterSurvival.java`
- **Core types**: `GameObject.java`, `Enemy.java`, `SimpleEnemy.java`
- **Entities**: `Diver`, `Shark`, `Squid`, `Piranha`, `JellyFish`, `eel`
- **Assets**: `.png`, `.jpg`, `.gif` files in the repo root


