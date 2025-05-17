import javax.swing.JOptionPane;
import java.util.Random;

public class Game {

    static final String[] OPTIONS = {"rock", "paper", "scissors"};

    public static void main(String[] args) {
        int playerScore = 0;
        int computerScore = 0;
        int tieScore = 0;

        for (int round = 1; round <= 10; round++) {
            // Player input
            String input = JOptionPane.showInputDialog(null, "Round " + round + " - Enter Rock, Paper, or Scissors:");
            if (input == null) {
                JOptionPane.showMessageDialog(null, "Game was Terminated.");
                return;
            }

            String playerMove = interpretInput(input);

            // redo for invalid inputs
            while (playerMove == null) {
                input = JOptionPane.showInputDialog(null, "Invalid input. TRy again (Rock, Paper, or Scissors):");
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Game was terminated.");
                    return;
                }
                playerMove = interpretInput(input);
            }

            // Computer choice
            String computerMove = getRandomChoice();

            // Scores
            String winner = determineWinner(playerMove, computerMove);
            if (winner.equals("player")) {
                playerScore++;
            } else if (winner.equals("computer")) {
                computerScore++;
            } else {
                tieScore++;
            }

            // Round result message
            StringBuilder roundResult = new StringBuilder();
            roundResult.append("Round ").append(round).append(" Results:\n");

            roundResult.append("You chose: ").append(playerMove).append("\n");

            roundResult.append("Computer chose: ").append(computerMove).append("\n");

            if (winner.equals("player")) {


                roundResult.append(" You won ");
            } else if (winner.equals("computer")) {



                roundResult.append(" Computer Won.");
            } else {
                roundResult.append("DRAW");

            }


            JOptionPane.showMessageDialog(null, roundResult.toString());
        }

        // Final scores
        StringBuilder finalScore = new StringBuilder();
        finalScore.append(" Game Over - Final Scores:\n");
        finalScore.append("your Wins ").append(playerScore).append("\n");
        finalScore.append("Computer Wins ").append(computerScore).append("\n");
        finalScore.append("Draws ").append(tieScore).append("\n");

        if (playerScore > computerScore) {
            finalScore.append("Yay u won ");
        } else if (computerScore > playerScore) {
            finalScore.append("    The computer wins");
        } else {
            finalScore.append(" DRAW");
        }

        JOptionPane.showMessageDialog(null, finalScore.toString());
    }

    // Interpret and validate player input
    public static String interpretInput(String input) {
        if (input == null || input.length() < 2) return null;
        String cleaned = input.trim().toLowerCase();
        String prefix = cleaned.substring(0, 2);
        if (prefix.equals("ro")) return "rock";
        if (prefix.equals("pa")) return "paper";
        if (prefix.equals("sc")) return "scissors";
        return null;
    }

    // Random choice
    public static String getRandomChoice() {
        Random rand = new Random();
        return OPTIONS[rand.nextInt(OPTIONS.length)];
    }

    //  declare winner
    public static String determineWinner(String player, String computer) {
        if (player.equals(computer)) return "draw";

        if ((player.equals("rock") && computer.equals("scissors")) ||
                (player.equals("paper") && computer.equals("rock")) ||
                (player.equals("scissors") && computer.equals("paper"))) {
            return "player";
        }

        return "computer";
    }
}
