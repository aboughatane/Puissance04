public class Player{
	private String nom;
	private int symbol;


	public Player(String nom, int symbol){
		this.nom = nom;
		this.symbol = symbol;
	}

	public String getNom(){
		return nom;
	}

	public int getSymbol(){
		return symbol;
	}

	//methode implementer par un des joueur
	public void playGame(Game game){}
}