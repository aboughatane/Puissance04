import java.util.Scanner;

public class Puissance4{
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		// Menu
		System.out.println("\n\n\t\t\t\t\tBienvenue sur **Puissance4** \n");
		System.out.println(" -Les regles sont simples deposez vos jetons dans les colonnes de la grille du jeu en tapant le numéro de la colonne choisie.\n");
		System.out.println(" -Faites une ligne d'au moins quatre jetons soit verticalement, horizontalement ou en diagonale avant votre adversaire.\n");
		
		//mode du jeu
		int choix = 0;
		do{
			System.out.println(" Choisissez le mode du jeu :\n  taper 1 - Joueur contre joueur \n  taper 2 - Joueur contre Ordinateur \n  taper 3 - Ordinateur contre Ordinateur");
				
			while(!scanner.hasNextInt()){
				scanner.nextLine();
				System.out.println("Recommencez");
			}
			choix = scanner.nextInt();
		}while(choix < 1 || choix > 3);

		//Joueur contre joueur
		if(choix == 1){
			Scanner scanner = new Scanner(System.in);
			Scanner sc = new Scanner(System.in);
			System.out.println(" Joueur 1 : [O], entrez votre nom :");
			String nom1 = scanner.nextLine();

			System.out.println(" Joueur 2 : [X], entrer votre nom :");
			String nom2 = scanner.nextLine();

			//Quel joueur commence 
			System.out.println(" Quelle joueur veut commencer ? [1-Joueur1/2-Joueur2]");
			int begin = sc.nextInt();
			if(begin == 1){
				System.out.println(" Bon jeu ! ");
				Play play = new Play(new Human(nom1, Game.O), new Human(nom2, Game.X));
				play.playGame();
			}
			else if(begin == 2){
				System.out.println(" Bon jeu ! ");
				Play play = new Play(new Human(nom2, Game.X), new Human(nom1, Game.O));
				play.playGame();
			}

			//Rejouer la partie
			boolean replayBoolean = true;
			while(replayBoolean == true){	
				System.out.println("Voulez vous rejouer ?  0:non/1:oui");
				int replay = sc.nextInt();	
				if(replay == 1){	
					System.out.println(" Bon jeu ! ");
					Play play2 = new Play(new Human(nom1, Game.O), new Human(nom2, Game.X));
					play2.playGame();
				}
				else{
					System.out.println("OK au revoir !");
					replayBoolean = false;
				}
			}		
		}

		//Joueur contre Ordinateur		
		if(choix == 2){
			Scanner scanner = new Scanner(System.in);
			Scanner sc = new Scanner(System.in);
			System.out.println(" Joueur  => 'O', entrez votre nom :");
			String nom1 = scanner.nextLine();

			System.out.println("Quelle joueur veut commencer ? [1-Joueur/2-Ordinateur]");
			int begin = sc.nextInt();
			if(begin == 1){
				System.out.println(" Bon jeu ! ");
				Play play = new Play(new Human(nom1, Game.O), new Computer(Game.X));
				play.playGame();
			}
			else if(begin == 2){
				System.out.println(" Bon jeu ! ");
				Play play = new Play(new Computer(Game.X), new Human(nom1, Game.O));
				play.playGame();
			}

			//Rejouer la partie
			boolean replayBoolean = true;
			while(replayBoolean == true){	
				System.out.println("Voulez vous rejouer ?  0:non/1:oui");
				int replay = sc.nextInt();	
				if(replay == 1){	
					System.out.println(" Bon jeu ! ");
					Play play2 = new Play(new Human(nom1, Game.O), new Computer(Game.X));
					play2.playGame();
				}
				else{
					System.out.println("OK au revoir !");
					replayBoolean = false;
				}
			}	
		}
		
		//Ordinateur contre Ordinateur
		if(choix == 3){
			Scanner scanner = new Scanner(System.in);
			System.out.println(" Bon jeu ! ");
			Play play = new Play(new Computer(Game.O), new Computer(Game.X));
			play.playGame();
		}
	}
}

