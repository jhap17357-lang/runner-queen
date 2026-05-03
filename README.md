# Runner Queen - 3D Endless Runner Game

A 3D endless runner game built with Java and LibGDX, inspired by Subway Surfers.

## Features
- 3D endless runner gameplay
- Obstacle avoidance
- Coin collection
- Power-ups
- Increasing difficulty
- High score tracking
- Smooth animations

## Requirements
- Java 11+
- LibGDX 1.12.1
- Gradle

## Build & Run

```bash
# Build the project
./gradlew build

# Run the desktop version
./gradlew run
```

## Game Controls
- **Left/Right Arrow Keys** or **A/D** - Move left/right
- **Space** - Jump
- **ESC** - Pause game

## Project Structure
```
src/main/java/com/runnerqueen/
├── RunnerQueenGame.java          # Main game class
├── screens/
│   ├── GameScreen.java           # Main gameplay screen
│   ├── MenuScreen.java           # Main menu
│   └── GameOverScreen.java       # Game over screen
├── entities/
│   ├── Player.java               # Player character
│   ├── Obstacle.java             # Obstacles
│   ├── Coin.java                 # Collectible coins
│   └── PowerUp.java              # Power-ups
├── managers/
│   ├── GameManager.java          # Game state management
│   ├── CollisionManager.java     # Collision detection
│   └── ParticleManager.java      # Particle effects
└── utils/
    ├── Constants.java            # Game constants
    └── AssetManager.java         # Asset loading
```

## License
MIT License
