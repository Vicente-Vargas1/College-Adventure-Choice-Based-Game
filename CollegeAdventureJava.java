
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//New GameStateDecorator interface
interface GameStateDecorator extends CollegeAdventure.GameState {
 // Additional methods or properties can be added here
}

//Concrete decorator for adding logging functionality
class LoggingDecorator implements GameStateDecorator {
 private CollegeAdventure.GameState gameState;

 public LoggingDecorator(CollegeAdventure.GameState gameState) {
     this.gameState = gameState;
 }

 @Override
 public void displayState(CollegeAdventure context) {
     // Additional behavior (e.g., logging) before displaying the state
     System.out.println("Logging: Displaying game state");
     gameState.displayState(context);
 }

 @Override
 public void handleChoice(String choice, CollegeAdventure context) {
     // Additional behavior (e.g., logging) before handling the choice
     System.out.println("Logging: Handling player choice");
     gameState.handleChoice(choice, context);
 }
}

public class CollegeAdventure {
    private static volatile CollegeAdventure instance;
    private JFrame window;
    private Container con;
    private JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
    private JLabel titleNameLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName;
    private Font titleFont = new Font("Times New Roman", Font.PLAIN, 32);
    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 20);
    private JButton startButton, choice1, choice2, choice3, choice4;
    private JTextArea mainTextArea;
    private int playerHP = 50;
    private GameState gameState;

    private TitleScreenHandler tsHandler = new TitleScreenHandler();
    private ChoiceHandler choiceHandler = new ChoiceHandler();
	private String position;

    private CollegeAdventure() {
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);

        con = window.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("COLLEGE ADVENTURE");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler);
        startButton.setFocusPainted(false);

        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);

        con.add(titleNamePanel);
        con.add(startButtonPanel);

        window.setVisible(true);
        gameState = new OrientationWeekState();
    }

    public static CollegeAdventure getInstance() {
        if (instance == null) {
            synchronized (CollegeAdventure.class) {
                if (instance == null) {
                    instance = new CollegeAdventure();
                }
            }
        }
        return instance;
    }

    public void createGameScreen() {
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);
        con.add(mainTextPanel);
        mainTextArea = new JTextArea("");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);

        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(25, 350, 750, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));
        con.add(choiceButtonPanel);
        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.white);
        choice1.setFont(normalFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);
        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.white);
        choice2.setFont(normalFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);
        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.white);
        choice3.setFont(normalFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);

        playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 600, 50);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1, 4));
        con.add(playerPanel);
        hpLabel = new JLabel("HP:");
        hpLabel.setFont(normalFont);
        hpLabel.setForeground(Color.white);
        playerPanel.add(hpLabel);
        hpLabelNumber = new JLabel();
        hpLabelNumber.setFont(normalFont);
        hpLabelNumber.setForeground(Color.white);
        playerPanel.add(hpLabelNumber);
        weaponLabel = new JLabel(" ");
        weaponLabel.setFont(normalFont);
        weaponLabel.setForeground(Color.white);
        playerPanel.add(weaponLabel);
        weaponLabelName = new JLabel();
        weaponLabelName.setFont(normalFont);
        weaponLabelName.setForeground(Color.white);
        playerPanel.add(weaponLabelName);
        
        gameState = new LoggingDecorator(gameState);
       
    
        gameState.displayState(this);
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    public JTextArea getMainTextArea() {
        return mainTextArea;
    }

    public JButton getChoice1() {
        return choice1;
    }

    public JButton getChoice2() {
        return choice2;
    }
    public JButton getChoice3() {
        return choice3;
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        gameState.displayState(this);
    }

    public int getPlayerHP() {
        return playerHP;
    }
    

    public void updateStats() {
        hpLabelNumber.setText("" + playerHP);

        if (playerHP <= 0) {
            gameOver("You Lose");
        } else if (playerHP >= 100) {
            gameOver("You Win");
        }
    }

    private void gameOver(String outcome) {
        mainTextArea.setText(outcome + "! Game Over.");

        // Customize further actions based on the outcome.
        if (outcome.equals("You Lose")) {
            showLoseScreen();
            exitGame();
        } else if (outcome.equals("You Win")) {
            showWinScreen();
            exitGame();
        }
    }

    private void showWinScreen() {
        JOptionPane.showMessageDialog(window, "You Won!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        resetGame();
    }

    private void showLoseScreen() {
        JOptionPane.showMessageDialog(window, "You Lost!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        resetGame();
    }

    private void exitGame() {
        // Exit the game
        System.exit(0);
    }

    private void resetGame() {
        // Reset the game state
        playerHP = 50;
        gameState = new OrientationWeekState();
        gameState.displayState(this);
    }

    public class TitleScreenHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            createGameScreen();
        }
    }

    public class ChoiceHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String yourChoice = event.getActionCommand();
            gameState.handleChoice(yourChoice, CollegeAdventure.this);
        }
    }

    // State interface and concrete state classes
    public interface GameState {
        void displayState(CollegeAdventure context);
        void handleChoice(String choice, CollegeAdventure context);
    }

    public class OrientationWeekState implements GameState {
        public void displayState(CollegeAdventure context) {
            context.updateStats();
            context.setPosition("orientationWeek");
            context.getMainTextArea().setText("Welcome to Central Michigan University! ...\n" +
                    "Decision: It's Friday night, and your friends invite you to a wild party \n" +
                    "and you have an exam the next day. What's your move?");
            context.getChoice1().setText("Embrace the party animal within and join the fun!");
            context.getChoice2().setText("Channel your inner grandma and cozy up with a good book");
            context.getChoice3().setText("Study");
        }

        public void handleChoice(String choice, CollegeAdventure context) {
            switch (choice) {
                case "c1":
                    context.setPlayerHP(context.getPlayerHP() - 20);
                    break;
                case "c2":
                    context.setPlayerHP(context.getPlayerHP() - 10);
                    break;
                case "c3":
                    context.setPlayerHP(context.getPlayerHP() + 5);
                    break;
            }
            context.updateStats();
            context.setGameState(new AttendTourState());
        }
    }

    public class AttendTourState implements GameState {
        public void displayState(CollegeAdventure context) {
            context.updateStats();
            context.setPosition("attendTour");
            context.getMainTextArea().setText("\n" +
                    "Decision: The professor announces a surprise quiz. How do you react? \n" +
                    "");
            context.getChoice1().setText("Panic mode activated! Time to flip through those notes");
            context.getChoice2().setText("Embrace the chaos and start drafting your resignation letter to adulthood");
            context.getChoice3().setText("Be so nervous you walk out");
        }

        public void handleChoice(String choice, CollegeAdventure context) {
            switch (choice) {
                case "c1":
                    context.setPlayerHP(context.getPlayerHP() + 5);
                    break;
                case "c2":
                    context.setPlayerHP(context.getPlayerHP() - 10);
                    break;
                case "c3":
                    context.setPlayerHP(context.getPlayerHP() - 20);
                    break;
            }
            context.updateStats();
            context.setGameState(new JoinClubFairState());
        }

    }

    		public class JoinClubFairState implements GameState {
            public void displayState(CollegeAdventure context) {
                context.updateStats();
                context.setPosition("joinClubFair");
                context.getMainTextArea().setText("\n" +
                        "Decision: Your laundry is piling up, and you're out of clean underwear. What's the plan?\n" +
                        "");
                context.getChoice1().setText("Fashion a DIY loincloth and conquer laundry day later ");
                context.getChoice2().setText("Embrace commando life until laundry day becomes unavoidable");
                context.getChoice3().setText(" Do your laundry");
            }

            public void handleChoice(String choice, CollegeAdventure context) {
                switch (choice) {
                    case "c1":
                        context.setPlayerHP(context.getPlayerHP() - 20);
                        break;
                    case "c2":
                        context.setPlayerHP(context.getPlayerHP() - 10);
                        break;
                    case "c3":
                        context.setPlayerHP(context.getPlayerHP() + 5);
                        break;
                }
                context.updateStats();
                context.setGameState(new AttendFirstClassState());
            }
        }

        public class AttendFirstClassState implements GameState {
            public void displayState(CollegeAdventure context) {
                context.updateStats();
                context.setPosition("attendFirstClass");
                context.getMainTextArea().setText("\n" +
                        "Decision:You have a choice between an early morning class and a late-night study session. What's your pick?\n" +
                        "");
                context.getChoice1().setText("Rise and shine for that 8 AM class. Coffee, here I come!");
                context.getChoice2().setText("Burn the midnight oil because you're basically a nocturnal scholar.");
                context.getChoice3().setVisible(false);
            }

            public void handleChoice(String choice, CollegeAdventure context) {
                switch (choice) {
                    case "c1":
                        context.setPlayerHP(context.getPlayerHP() + 5);
                        break;
                    case "c2":
                        context.setPlayerHP(context.getPlayerHP() - 10);
                        break;
                }
                context.updateStats();
                context.setGameState(new GrabCoffeeState());
            }
        }

        	public class GrabCoffeeState implements GameState {
            public void displayState(CollegeAdventure context) {
                context.updateStats();
                context.setPosition("grabCoffee");
                context.getMainTextArea().setText("\n" +
                        "Decision: Your friend suggests joining a quirky club, like the \"Underwater Basket Weaving Enthusiasts.\" Yay or nay?\n" +
                        "");
                context.getChoice1().setText("Dive into the world of aquatic crafts—basket weaving, here I come!");
                context.getChoice2().setText("Politely decline and stick to more mainstream extracurriculars.");
                context.getChoice3().setVisible(false);
            }

            public void handleChoice(String choice, CollegeAdventure context) {
                switch (choice) {
                    case "c1":
                        context.setPlayerHP(context.getPlayerHP() - 5);
                        break;
                    case "c2":
                        context.setPlayerHP(context.getPlayerHP() + 5);
                        break;
                }
                context.updateStats();
                context.setGameState(new ContinueExploringState());
            }
        }

  public class ContinueExploringState implements GameState {
            public void displayState(CollegeAdventure context) {
                context.updateStats();
                context.setPosition("continueExploring");
                context.getMainTextArea().setText("\n" +
                        "Decision: You accidentally walk into the wrong class. What's your game plan?\n" +
                        "");
                context.getChoice1().setText("Commit to the mistaken identity and become a secret agent of knowledge.");
                context.getChoice2().setText("Make a swift exit, blaming it on your twin who totally exists. ");
                context.getChoice3().setVisible(false);
            }

            public void handleChoice(String choice, CollegeAdventure context) {
                switch (choice) {
                    case "c1":
                        context.setPlayerHP(context.getPlayerHP() + 5);
                        break;
                    case "c2":
                        context.setPlayerHP(context.getPlayerHP() - 10);
                        break;
                }
                context.updateStats();
                context.setGameState(new Question7State());
            }
        }

        public class Question7State implements GameState {
            public void displayState(CollegeAdventure context) {
                context.updateStats();
                context.setPosition("question7");
                context.getMainTextArea().setText("\n" +
                        "Decision: Your roommate insists on a dorm room makeover involving fairy lights and inflatable furniture. Yay or nay\n" +
                        "");
                context.getChoice1().setText("Transform your space into a magical wonderland of questionable taste.");
                context.getChoice2().setText("Maintain a minimalist aesthetic—less is more, right? ");
                context.getChoice3().setVisible(false);
            }

            public void handleChoice(String choice, CollegeAdventure context) {
                switch (choice) {
                    case "c1":
                        context.setPlayerHP(context.getPlayerHP() + 5);
                        break;
                    case "c2":
                        context.setPlayerHP(context.getPlayerHP() - 10);
                        break;
                }
                context.updateStats();
                context.setGameState(new Question8State());
            }
        }

public class Question8State implements GameState {
        public void displayState(CollegeAdventure context) {
            context.updateStats();
            context.setPosition("question8");
            context.getMainTextArea().setText("\n" +
                    "Decision: You have a choice between a group project and a solo endeavor. What's your strategy?\n" +
                    "");
            context.getChoice1().setText("Team up with the Avengers of academia for a legendary group project");
            context.getChoice2().setText("Embrace the lone wolf persona and navigate the academic wilderness solo ");
            context.getChoice3().setVisible(false);
        }

        public void handleChoice(String choice, CollegeAdventure context) {
            switch (choice) {
                case "c1":
                    context.setPlayerHP(context.getPlayerHP() + 10);
                    break;
                case "c2":
                    context.setPlayerHP(context.getPlayerHP() - 20);
                    break;
            }
            context.updateStats();
            context.setGameState(new Question9State());
        }
    }

    public class Question9State implements GameState {
        public void displayState(CollegeAdventure context) {
            context.updateStats();
            context.setPosition("question9");
            context.getMainTextArea().setText("\n" +
                    "Decision: Your crush invites you to a poetry slam. How do you respond?\n" +
                    "");
            context.getChoice1().setText("Channel your inner Shakespeare and deliver a poetic masterpiece.");
            context.getChoice2().setText("Attend with the enthusiasm of a caffeinated sloth—enthusiasm levels: questionable ");
            context.getChoice3().setVisible(false);
        }

        public void handleChoice(String choice, CollegeAdventure context) {
            switch (choice) {
                case "c1":
                    context.setPlayerHP(context.getPlayerHP() + 5);
                    break;
                case "c2":
                    context.setPlayerHP(context.getPlayerHP() - 20);
                    break;
            }
            context.updateStats();
            context.setGameState(new Question10State());
        }
    }

    public class Question10State implements GameState {
        public void displayState(CollegeAdventure context) {
            context.updateStats();
            context.setPosition("question10");
            context.getMainTextArea().setText("\n" +
                    "Decision: Your laptop crashes the night before a major assignment is due. What's your next move?\n" +
                    "");
            context.getChoice1().setText("Enter crisis mode and attempt a tech exorcism to revive your laptop");
            context.getChoice2().setText("Accept defeat, and blame it on cosmic forces");
            context.getChoice3().setVisible(false);
        }

        public void handleChoice(String choice, CollegeAdventure context) {
            switch (choice) {
                case "c1":
                    context.setPlayerHP(context.getPlayerHP() + 10);
                    break;
                case "c2":
                    context.setPlayerHP(context.getPlayerHP() - 30);
                    break;
            }
            context.updateStats();
            context.setGameState(new Question11State());
        }
    }

    public class Question11State implements GameState {
        public void displayState(CollegeAdventure context) {
            context.updateStats();
            context.setPosition("question11");
            context.getMainTextArea().setText("\n" +
                    "Decision: You discover a secret shortcut to class that involves jumping over hedges. Yay or nay?\n" +
                    "");
            context.getChoice1().setText("Embrace the urban jungle and leap over hedges like a campus ninja");
            context.getChoice2().setText("Stick to the normal path and avoid the risk of becoming a campus legend");
            context.getChoice3().setVisible(false);
        }

        public void handleChoice(String choice, CollegeAdventure context) {
            switch (choice) {
                case "c1":
                    context.setPlayerHP(context.getPlayerHP() + 10);
                    break;
                case "c2":
                    context.setPlayerHP(context.getPlayerHP() - 30);
                    break;
            }
            context.updateStats();
            context.setGameState(new Question12State());
        }
    }

    public class Question12State implements GameState {
        public void displayState(CollegeAdventure context) {
            context.updateStats();
            context.setPosition("question12");
            context.getMainTextArea().setText("\n" +
                    "Decision: You finish the semester, what do you plan on doing next?\n" +
                    "");
            context.getChoice1().setText("Drop out.");
            context.getChoice2().setText("Stay for a 5th year ");
            context.getChoice3().setVisible(false);
        }

        public void handleChoice(String choice, CollegeAdventure context) {
            switch (choice) {
                case "c1":
                    context.setPlayerHP(context.getPlayerHP() - 100);
                    break;
                case "c2":
                    context.setPlayerHP(context.getPlayerHP() + 100);
                    break;
            }
            context.updateStats();
            // You may implement further actions or transitions here based on the outcome.
            context.gameOver(context.getPlayerHP() <= 0 ? "You Lose" : "You Win");
        }
    }

    public static void main(String[] args) {
        CollegeAdventure game = CollegeAdventure.getInstance();
    }
}

