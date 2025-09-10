# Pente

A JavaFX implementation of the classic board game Pente.

## Features

- Play Pente with a graphical user interface.
- Follows standard Pente rules: win by getting 5 in a row or capturing 5 pairs.
- Multiple screens: main menu, game board, and how-to-play instructions.
- Custom styling using a Caspian-based CSS theme.

## Project Structure

```
pente/
├── pom.xml
├── README.md
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── sc/
│       │           ├── App.java
│       │           ├── Main.java
│       │           ├── gameController.java
│       │           └── htpController.java
│       └── resources/
│           └── com/
│               └── sc/
│                   ├── caspian.css
│                   ├── game.fxml
│                   ├── main-menu.fxml
│                   └── howtoplay.fxml
```

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven

### Build and Run

To build and run the project:

```sh
mvn clean javafx:run
```

The main class is [`com.sc.Main`](src/main/java/com/sc/Main.java).

## Controls

- Use the mouse to interact with the game board and menus.
- See the "How to Play" screen for rules and instructions.

## Customization

- UI styling is defined in [`caspian.css`](src/main/resources/com/sc/caspian.css).
- FXML layouts are in the `resources/com/sc/` directory.

## License

This project is based on JavaFX and uses the Caspian stylesheet, which is distributed under the GPL v2 with Classpath Exception.

---

*For more details, see the source files and comments in the code.*