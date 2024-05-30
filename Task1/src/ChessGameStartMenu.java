import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class ChessGameStartMenu extends JFrame implements ActionListener {


    public ChessGameStartMenu() {
        setTitle("Chess Game Start Menu");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setLayout(new GridLayout(3, 2));

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(Color.BLACK);
        startButton.setFocusable(false); // Add this line
        
        JButton instructionsButton = new JButton("Instructions");
        instructionsButton.setFont(new Font("Arial", Font.BOLD, 16));
        instructionsButton.setForeground(Color.WHITE);
        instructionsButton.setBackground(Color.BLACK);
        instructionsButton.setFocusable(false); // Add this line
        
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.BLACK);
        exitButton.setFocusable(false); // Add this line

        try {
            Image logo = ImageIO.read(ChessBoard.class.getResource("/images/icon.png"));
            setIconImage(logo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        startButton.addActionListener(this);
        instructionsButton.addActionListener(this);
        exitButton.addActionListener(this);

        panel.add(startButton);
        panel.add(instructionsButton);
        panel.add(exitButton);

        JPanel imagePanel = new JPanel();
        // Add the image icon to the imagePanel (Replace "icon.png" with your image file path)
        ImageIcon icon = new ImageIcon("bin/images/chessgame.png");
        JLabel imageLabel = new JLabel(icon);
        imagePanel.add(imageLabel);

        add(panel, BorderLayout.SOUTH);
        add(imagePanel, BorderLayout.CENTER);    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        JButton clickedButton = (JButton) e.getSource();

        switch (command) {
            case "Start Game":
                startGame();
                break;
            case "Instructions":
                displayInstructions();
                break;
            case "Exit":
                System.exit(0);
                break;
        }
        clickedButton.setBorder(null);
    }

    public void startGame() {
        // Code to start the chess game goes here
        System.out.println("Starting the game...");
        ChessGameGUI gui = new ChessGameGUI();

        // Call methods or access fields of ChessGameGUI as needed
        dispose();
    }

    public void displayInstructions() {
        JOptionPane.showMessageDialog(this,
                "Instructions:\n" +
"1. Movement of Pieces:\r\n" + //
        "Pawn: Moves forward one square, captures diagonally forward, and has special moves like en passant and pawn promotion.\r\n" + //
        "Rook: Moves horizontally or vertically any number of squares.\r\n" + //
        "Knight: Moves in an \"L\" shape (two squares in one direction, then one square perpendicular).\r\n" + //
        "Bishop: Moves diagonally any number of squares.\r\n" + //
        "Queen: Combination of rook and bishop (moves horizontally, vertically, or diagonally).\r\n" + //
        "King: Moves one square in any direction.\r\n" + //
        "2. Objective:\r\n" + //
        "The primary objective is to checkmate the opponent's king, which means the king is in a position to be captured (in check)\n" +" and there's no legal move to escape.\r\n" + //
        "3. Turn Structure:\r\n" + //
        "White moves first, then players alternate turns.\r\n" + //
        "On each turn, a player must make one move with one of their pieces, subject to the rules of movement.\r\n" + //
        "4. Special Moves:\r\n" + //
        "Castling: A king can move two squares towards a rook and the rook moves to the square next to the king. Conditions for\n"+" castling: neither piece has moved before, the squares between the king and rook are empty, and the king is not in check.\r\n" + //
        "En passant: A pawn can capture an opponent's pawn that has just moved two squares forward from its starting position.\r\n" + //
        "Pawn promotion: When a pawn reaches the opposite end of the board, it can be promoted to any other piece (except a king).\r\n" + //
        "5. Check and Checkmate:\r\n" + //
        "Check: When the opponent's king is under attack, it is said to be in check.\r\n" + //
        "Checkmate: If the opponent's king is in check and there's no legal move to escape, it's checkmate, and the game ends.\r\n" + //
        "6. Draw:\r\n" + //
        "A draw can occur due to stalemate (when a player has no legal moves and their king is not in check), insufficient material\n" + " (when neither player has enough pieces to force a checkmate), threefold repetition (when the same position occurs \n"+"three times with the same player to move), or the fifty-move rule (when no pawn is moved and no capture is made for fifty consecutive moves).\r\n" + //
        "7. Resignation and Win:\r\n" + //
        "A player may resign if they believe they have no chance of winning.\r\n" + //
        "The game is won by the player who checkmates their opponent's king.",
                "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChessGameStartMenu::new);
        
    }
}