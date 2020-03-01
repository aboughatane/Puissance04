public class Human extends Player{
	//Prendre le dernier choix de l'humain
	private static int humanChoice;

	public Human(String nom, int symbol){
		super(nom, symbol);
	}

	public int getHumanchoice(){
		return humanChoice;
	}

	public void playGame(Game game){
		game.displayGrid();
		boolean valid =false;
		int col;
		do{
			System.out.println(" "+ getNom() + ", choisissez le numero de la colonne entre 1 et "+ game.getNbcolumn()+" :");
			
			//Ceci permet de controler l'entrée de l'utilisateur pour eviter l'erreur d'exception
			while(!Puissance4.scanner.hasNextInt()){
				Puissance4.scanner.nextLine();
				System.out.println(" Coup non autorisé, recommencez");
			}

			col = Puissance4.scanner.nextInt();
			col--;	
			valid = game.play(col, this.getSymbol());
				if(valid == false){
					System.out.println(" Coup non autorisé, recommencez");	
				}
			humanChoice = col;
		}while(valid == false);
		humanChoice = col;
	}
}