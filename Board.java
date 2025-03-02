/** 
 * A Board class for concentration
 */
public class Board
{  
  private static String[] tileValues = {"lion", "lion",
                                        "penguin", "penguin",
                                        "dolphin", "dolphin",
                                        "fox", "fox",
                                        "monkey", "monkey",
                                        "turtle", "turtle"}; 
  private Tile[][] gameboard = new Tile[3][4];

  /**  
   * Constructor for the game. Creates the 2D gameboard
   * by populating it with card values
   * 
   */
  public Board()
  {
    int index = 0;
    for (int row = 0; row < gameboard.length; row++) {
      for (int col = 0; col < gameboard[row].length; col++) {
        gameboard[row][col] = new Tile(tileValues[index]);
        index++;
      }
    }
    // For testing only
    // printBoard();
  }

  /** 
   * Returns a string representation of the board, getting the state of
   * each tile. If the tile is showing, displays its value, 
   * otherwise displays it as hidden.
   * 
   * Precondition: gameboard is populated with tiles
   * 
   * @return a string representation of the board
   */
  public String toString()
{
    StringBuilder sb = new StringBuilder();
    for (Tile[] row : gameboard) {
        for (Tile tile : row) {
            if (tile.isShowing()) {
                sb.append(tile.getValue()).append("\t"); // Show the tile's value if it is showing
            } else {
                sb.append("_____\t"); // Show hidden representation as "_____"
            }
        }
        sb.append("\n"); // Move to the next line after each row
    }
    return sb.toString();
}
  
  /** 
   * Determines if the board is full of tiles that have all been matched,
   * indicating the game is over.
   * 
   * Precondition: gameboard is populated with tiles
   * 
   * @return true if all tiles have been matched, false otherwise
   */
  public boolean allTilesMatch()
{
    for (Tile[] row : gameboard) {
        for (Tile tile : row) {
            if (!tile.isMatched()) {
                return false; // If any tile is unmatched, return false
            }
        }
    }
    return true; // All tiles are matched
}

    /** 
   * Sets the tile to show its value (like a playing card face up)
   * 
   * Preconditions:
   *   gameboard is populated with tiles,
   *   row values must be in the range of 0 to gameboard.length,
   *   column values must be in the range of 0 to gameboard[0].length
   * 
   * @param row the row value of Tile
   * @param column the column value of Tile
   */
  public void showValue(int row, int column)
  {
      if (row >= 0 && row < gameboard.length && column >= 0 && column < gameboard[0].length) {
          gameboard[row][column].show(); // Show the tile's value
      }
  }

  /** 
   * Checks if the Tiles in the two locations match.
   * 
   * If Tiles match, show Tiles in matched state and return a "matched" message
   * If Tiles do not match, re-hide Tiles (turn face down).
   * 
   * Preconditions:
   *   gameboard is populated with Tiles,
   *   row values must be in the range of 0 to gameboard.length,
   *   column values must be in the range of 0 to gameboard[0].length
   * 
   * @param row1 the row value of Tile 1
   * @param col1 the column value of Tile 1
   * @param row2 the row value of Tile 2
   * @param col2 the column value of Tile 2
   * @return a message indicating whether or not a match occurred
   */
  public String checkForMatch(int row1, int col1, int row2, int col2)
  {
    String msg = "";
    Tile tile1 = gameboard[row1][col1];
    Tile tile2 = gameboard[row2][col2];

    if (tile1.getValue().equals(tile2.getValue())) {
      tile1.setMatched(true);
      tile2.setMatched(true);
      msg = "Match found!";
    } else {
      tile1.hide();
      tile2.hide();
      msg = "No match. Try again.";
    }
    return msg;
  }

  /** 
   * Checks the provided values fall within the range of the gameboard's dimension
   * and that the tile has not been previously matched
   * 
   * @param row the row value of Tile
   * @param col the column value of Tile
   * @return true if row and col fall on the board and the row,col tile is unmatched, false otherwise
   */
  public boolean validateSelection(int row, int col)
  {
    if (row >= 0 && row < gameboard.length && col >= 0 && col < gameboard[0].length) {
      return !gameboard[row][col].isMatched();
    }
    return false;
  }

  // For testing only
  private void printBoard() {
    for (Tile[] row : gameboard) {
      for (Tile tile : row) {
        System.out.print(tile.getValue() + " ");
      }
      System.out.println();
    }
  }
}
