import java.util.*;
import java.lang.Math;

public class Computer extends Player{
	private Human human = new Human("nom", 1);
	private static int rand;

	public Computer(int symbol){
		super("L'ordinateur", symbol);
	}

/*
 * Dans le cas de l'ordinateur j'ai 3 étapes à faire 
 	1- placer un pion avec un random Ou le placer au milieu.
 	2- ensuite placer le 2eme a coté du 1er et ainsi de suite.. pour obtenir 4 pions allignés
 	3- pour empecher l'adversaire de gagner je fais que l'ordi le bloque dés qu'il a 3pions allignés.
*/

 	//La fonction random
 	public int random(int borneInf, int borneSup){
 		Random random = new Random();
 		int nb = borneInf + random.nextInt(borneSup - borneInf);
 		return nb;
 	}


	//L'ordinateur joue
	public void playGame(Game game) {
		game.displayGrid();
		rand = random(1,7);
		//int firstChoice = (game.getNbcolumn()-1) / 2;		

		for(int col = 0; col< game.getNbcolumn(); col++){
			// si il y a 3 pions bloquer l'adversaire
			if(game.alignement3()){
				System.out.println("\t\t\t Attention il y a 3 alignement");
				//Si c l'alignement de l'IA continuer
				//Si c l'alignement de l'adversaire, bloquer le
				if(game.alignement() == 'v'){
					//gerer le cas en horizontal
					if(game.play(human.getHumanchoice(), this.getSymbol()));
						return;
				}
				else{
				//gerer le cas vertical
					if(game.play(human.getHumanchoice(), this.getSymbol()));
						return;
				}
			}
			//Si il n'ya pas d'alignement jouer en random
			else if(game.play(rand, this.getSymbol()))
				return;
		}
	}
}