import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ChessGameGUI extends JFrame{
	private final ChessSquareComponent[][] squares = new ChessSquareComponent[8][8];
	  private final ChessGame game = new ChessGame();
	  private Clip backgroundMusic;

	  private final Map<Class<? extends Piece>, String> pieceUnicodeMap = new HashMap<>() {
	      {
	          put(Pawn.class, "\u265F");
	          put(Rook.class, "\u265C");
	          put(Knight.class, "\u265E");
	          put(Bishop.class, "\u265D");
	          put(Queen.class, "\u265B");
	          put(King.class, "\u265A");
	      }
	  };


	  public ChessGameGUI() {
	      setTitle("Chess Board");
		  setSize(1920, 1200);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setLayout(new GridLayout(8, 8));
	      initializeBoard();
	      addGameResetOption();
	      pack();
	      setVisible(true);
		  setExtendedState(JFrame.MAXIMIZED_BOTH); 
		  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		  int x = (screenSize.width - getWidth()) / 2;
		  int y = (screenSize.height - getHeight()) / 4;
		  setLocation(x, y);
		  setLocationRelativeTo(null);

		  try {
            Image logo = ImageIO.read(ChessBoard.class.getResource("/images/icon.png"));
            setIconImage(logo);}
		  catch (IOException e) {
            e.printStackTrace();
        }

		  UIManager UI=new UIManager();
		  UI.put("OptionPane.background",new ColorUIResource(211,211,211));
		  UI.put("Panel.background",new ColorUIResource(205,211,201));
		 JOptionPane.showMessageDialog(null,"White go first!","Start",JOptionPane.INFORMATION_MESSAGE);
		  try {
			  File musicFile = new File("D:\\Task1\\src\\startsound.wav"); // Update the path to your music file
			  AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
			  backgroundMusic = AudioSystem.getClip();
			  backgroundMusic.open(audioInputStream);
			  backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
		  } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			  e.printStackTrace();
		  }
	  }

	  private void initializeBoard() {
	      for (int row = 0; row < squares.length; row++) {
	          for (int col = 0; col < squares[row].length; col++) {
	              final int finalRow = row;
	              final int finalCol = col;
	              ChessSquareComponent square = new ChessSquareComponent(row, col);
	              square.addMouseListener(new MouseAdapter() {
	                  @Override
	                  public void mouseClicked(MouseEvent e) {
	                      handleSquareClick(finalRow, finalCol);
	                  }
	              });
	              add(square);
	              squares[row][col] = square;
	          }
	      }
	      refreshBoard();
	  }

	  private void refreshBoard() {
	      ChessBoard board = game.getBoard();
	      for (int row = 0; row < 8; row++) {
	          for (int col = 0; col < 8; col++) {
	              Piece piece = board.getPiece(row, col);
	              if (piece != null) {
	                  // If using Unicode symbols:
	                  String symbol = pieceUnicodeMap.get(piece.getClass());
	                  Color color = (piece.getColor() == PieceColor.WHITE) ? Color.WHITE : Color.BLACK;
	                  squares[row][col].setPieceSymbol(symbol, color);
	              } else {
	                  squares[row][col].clearPieceSymbol();
	              }
	          }
	      }
	  }

	  private void handleSquareClick(int row, int col) {
	      boolean moveResult = game.handleSquareSelection(row, col);
	      clearHighlights();
	      if (moveResult) {
	          refreshBoard();
	          checkGameState();
	          checkGameOver();
	      } else if (game.isPieceSelected()) {
	          highlightLegalMoves(new Position(row, col));
	      }
	      refreshBoard();
	  }

	  private void checkGameState() {
	      PieceColor currentPlayer = game.getCurrentPlayerColor();
	      boolean inCheck = game.isInCheck(currentPlayer);
	      if (inCheck) {
	          JOptionPane.showMessageDialog(this, currentPlayer + " is in check!");
	      }
	  }

	  private void highlightLegalMoves(Position position) {
	      List<Position> legalMoves = game.getLegalMovesForPieceAt(position);
	      for (Position move : legalMoves) {
	          squares[move.getRow()][move.getColumn()].setBackground(new Color(144, 238, 144));
	      }
	  }

	  private void clearHighlights() {
	      for (int row = 0; row < 8; row++) {
	          for (int col = 0; col < 8; col++) {
	              squares[row][col].setBackground((row + col) % 2 == 0 ? new Color(119,153,85) : new Color(240,230,205));
	          }
	      }
	  }

	  private void addGameResetOption() {
	      JMenuBar menuBar = new JMenuBar();
	      JMenu gameMenu = new JMenu("Game");
	      JMenuItem resetItem = new JMenuItem("Reset");
	      resetItem.addActionListener(e -> resetGame());
	      gameMenu.add(resetItem);
	      menuBar.add(gameMenu);
	      setJMenuBar(menuBar);
	  }

	  private void resetGame() {
	      game.resetGame();
	      refreshBoard();
	  }

	  private void checkGameOver() {
	      if (game.isCheckmate(game.getCurrentPlayerColor())) {
	          int response = JOptionPane.showConfirmDialog(this, "Checkmate! Would you like to play again?", "Game Over",
	                  JOptionPane.YES_NO_OPTION);
	          if (response == JOptionPane.YES_OPTION) {
	              resetGame();
	          } else {
	              System.exit(0);
	          }
	      }
	  }


	
}
