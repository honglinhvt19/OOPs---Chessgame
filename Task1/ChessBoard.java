import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ChessBoard extends JPanel {
	
	
    private static final int BOARD_SIZE = 8;
    private static final int SQUARE_SIZE = 90;
    
    private BufferedImage whitePawn, blackPawn, whiteRook, blackRook, whiteKnight, blackKnight, 
                         whiteBishop, blackBishop, whiteQueen, blackQueen, whiteKing, blackKing;

    public ChessBoard() {
    	
    	setPreferredSize(new Dimension(BOARD_SIZE * SQUARE_SIZE, BOARD_SIZE * SQUARE_SIZE));
        setBackground(Color.WHITE);

        // Tải các hình ảnh quân cờ
        try {
            whitePawn = ImageIO.read(getClass().getResource("/images/white_pawn.png"));
            blackPawn = ImageIO.read(getClass().getResource("/images/black_pawn.png"));
            whiteRook = ImageIO.read(getClass().getResource("/images/white_rook.png"));
            blackRook = ImageIO.read(getClass().getResource("/images/black_rook.png"));
            whiteKnight = ImageIO.read(getClass().getResource("/images/white_knight.png"));
            blackKnight = ImageIO.read(getClass().getResource("/images/black_knight.png"));
            whiteBishop = ImageIO.read(getClass().getResource("/images/white_bishop.png"));
            blackBishop = ImageIO.read(getClass().getResource("/images/black_bishop.png"));
            whiteQueen = ImageIO.read(getClass().getResource("/images/white_queen.png"));
            blackQueen = ImageIO.read(getClass().getResource("/images/black_queen.png"));
            whiteKing = ImageIO.read(getClass().getResource("/images/white_king.png"));
            blackKing = ImageIO.read(getClass().getResource("/images/black_king.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Vẽ bàn cờ
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if ((i + j) % 2 == 0) {
                	g.setColor(new Color(180, 212, 255));
                } else {
                	g.setColor(Color.WHITE);
                }
                g.fillRect(j * SQUARE_SIZE, i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }

        // Vẽ các quân cờ
        drawPiece(g, 0, 0, whiteRook);
        drawPiece(g, 0, 1, whiteKnight);
        drawPiece(g, 0, 2, whiteBishop);
        drawPiece(g, 0, 3, whiteQueen);
        drawPiece(g, 0, 4, whiteKing);
        drawPiece(g, 0, 5, whiteBishop);
        drawPiece(g, 0, 6, whiteKnight);
        drawPiece(g, 0, 7, whiteRook);

        for (int i = 0; i < BOARD_SIZE; i++) {
            drawPiece(g, 1, i, whitePawn);
        }

        drawPiece(g, 7, 0, blackRook);
        drawPiece(g, 7, 1, blackKnight);
        drawPiece(g, 7, 2, blackBishop);
        drawPiece(g, 7, 3, blackQueen);
        drawPiece(g, 7, 4, blackKing);
        drawPiece(g, 7, 5, blackBishop);
        drawPiece(g, 7, 6, blackKnight);
        drawPiece(g, 7, 7, blackRook);

        for (int i = 0; i < BOARD_SIZE; i++) {
            drawPiece(g, 6, i, blackPawn);
        }
    }

    private void drawPiece(Graphics g, int row, int col, BufferedImage piece) {
        g.drawImage(piece, col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, null);
    }

    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Chess Board");
        frame.setSize(734, 781);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChessBoard(), BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 4;
        frame.setLocation(x, y);
        
        
        //không thay đổi kích thướt frame
        frame.setResizable(false);
        
        try {
            Image logo = ImageIO.read(ChessBoard.class.getResource("/images/logo.png"));
            frame.setIconImage(logo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        frame.setSize(734, 780);
        
     // menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(241,243,249,255));
        menuBar.setBorderPainted(false);
        
        JMenu newGame = new JMenu("New Game");
        JMenu undo = new JMenu("Undo");
        
        Color newGameForegroundColor = new Color(7, 65, 115);
        newGame.setForeground(newGameForegroundColor);
        Color undoForegroundColor = new Color(7, 65, 115);
        undo.setForeground(undoForegroundColor);
        
        menuBar.add(newGame);
        menuBar.add(undo);
        
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
}