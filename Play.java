public class Play{
	private Player[] player = new Player[2];
	private Game game;
	private int winner=-1;

	//Constructeur
	public Play(Player player1, Player player2){
		player[0] = player1;
		player[1] = player2;
		game = new Game();
	} 

	public int getWinner(){
  		return winner;
  	}

 	public void playGame() {
    int currentPlayer = 0;

    while (winner==-1 && !game.isFull()) {
      player[currentPlayer].playGame(game);
      if (game.isFull()) {
        winner = -1;
      }

      // Si 4 pions sont alignés, on a un vainqueur
      if (game.alignement4()) {
    	winner = currentPlayer;
      }

      // On change de joueur pour l'itération suivante
      currentPlayer++;
      currentPlayer %= 2;
    }
 
    game.displayGrid();
    System.out.println("\t\t\t La partie est finie.");
    if (winner == -1) {
      System.out.println("\t\t\t Match nul.");
      game.displayGrid();
    } 
    else {
      System.out.println("\t\t\t Le gagnant est -> " + player[winner].getNom());
    }
  }
}