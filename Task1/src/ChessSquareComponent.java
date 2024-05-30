import javax.swing.*;
import java.awt.*;

public class ChessSquareComponent extends JButton{
	private int row;
	  private int col;

	  public ChessSquareComponent(int row, int col) {
	      this.row = row;
	      this.col = col;
	      initButton();
	  }

	  private void initButton() {
	      setPreferredSize(new Dimension(64, 64));

	      if ((row + col) % 2 == 0) {
	          setBackground(new Color(119, 153, 85));
	      } else {
	          setBackground(new Color(240,230,205));
	      }

	      setHorizontalAlignment(SwingConstants.CENTER);
	      setVerticalAlignment(SwingConstants.CENTER);
	      setFont(new Font("Monospaced", Font.BOLD, 64));
		  setFocusable(false);
	  }

	  public void setPieceSymbol(String symbol, Color color) {
	      this.setText(symbol);
	      this.setForeground(color);
	  }

	  public void clearPieceSymbol() {
	      this.setText("");
	  }
}
