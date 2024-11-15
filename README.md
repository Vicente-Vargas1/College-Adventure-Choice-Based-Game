College Adventure Game 🎓
"College Adventure" is a Java-based interactive text adventure game that puts players in the shoes of a college student. Players make choices that affect their character’s journey through college life—navigating Orientation Week, unexpected quizzes, club fairs, and more. Each decision influences the player's health points (HP) and the storyline outcome, offering a dynamic and engaging experience.

Table of Contents:
Features
Design Patterns
Installation
How to Play
Game Structure
Contributing

Features
- Text-Based Adventure: Make choices that shape your character’s story and fate.
- Graphical User Interface (GUI): Built with Java Swing for an interactive and user-friendly display.
- Game States & Choices: Progress through various game states, such as Orientation Week, attending tours, and club fairs, each with unique scenarios and decision points.
- Decorator Pattern for Logging: Optional logging to track game actions, assisting in debugging and game flow tracking.
- Health Points (HP): Choices impact HP, determining win/lose conditions.
- Singleton Pattern: Ensures only one instance of the game, allowing seamless management of game state and GUI.
  
Design Patterns
This game utilizes two primary design patterns:
- Decorator Pattern: Allows adding features, such as logging, without modifying the base code. The LoggingDecorator class wraps game states to log actions for easy debugging.
- Singleton Pattern: Manages game state and interface with a single CollegeAdventure instance, simplifying control and access.
  
Installation
- Clone the repository:

- bash
- git clone https://github.com/yourusername/CollegeAdventure.git
- cd CollegeAdventure
- Compile and run the game:

Ensure you have Java installed (JDK 8 or higher).
- javac CollegeAdventure.java
- java CollegeAdventure
  
How to Play
- Start the Game: Click "START" to begin your adventure.
- Make Choices: At each stage, encounter scenarios and options (e.g., attend a party, study, join a club). Each choice impacts your character’s HP.
- Monitor HP: Track your HP (Health Points) on the screen. The game ends if HP reaches 0 (lose) or 100 (win).
- End of Game: Receive a win/lose message based on your HP. You can then restart or exit.
  
Controls
- Choice Buttons: Click on a choice to proceed in the storyline.
- HP Display: Track current HP at the top of the screen.
  
Game Structure
Main Components:
- CollegeAdventure: Manages the game window, interface components, and core game logic.
- GameState: Interface for different game states, such as OrientationWeekState and AttendTourState.
- LoggingDecorator: Adds logging to game states for debugging.
  
Game States
Each state provides a unique scenario, updating HP based on player choices. The modular state design allows for easy expansion by adding new scenarios.

Contributing
Contributions are welcome! To contribute:

Fork the repository.
Create a feature branch.
Submit a pull request with a description of your changes.
