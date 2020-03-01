/*
 *Dans cette classe je fais une IA en utilisant l'algorithme minmax mais cela n'a pas
  marcher donc j'ai essayer une aure façon dans la classe Computer.java
 */
  
public class Ia extends Player{
	final int MINEVAL = -100000;
	final int MAXEVAL =  100000;
	private Play play;

	public Ia(int symbol){
		super("L'ordinateur", symbol);
	}

	public void playGame(Game game){
		game.displayGrid();
		for(int i = 1; i< game.getNbcolumn(); ++i){
			calculIa(game,3);
		}
	}
	
//La fonction calculIa qui choisi le coup jouer par l'ordinateur
	public void calculIa(Game game, int prof){
		int col,tmp;
		int max = MINEVAL;
		int maxcol =-1;

		//Si la profondeur n'est pas null et la partie n'est pas fini
		if((prof != 0) || (!game.isFull())){
			//on parcourt les colonne
			for (col=0; col<game.getNbcolumn(); col++){
				if(!game.fullColumn(col)){
					//on joue cette colonne
					game.play(col, this.getSymbol());
					//on appele la fonction calculMin pr lancer l'IA
					tmp = calculMin(game, (prof-1));

					if((tmp>max) || (tmp==max)){
						max = tmp;
						maxcol = col;
					}
					//sinon on annule ce coup
					game.cancelMove(col);
				}
			}
		}
		game.play(maxcol, this.getSymbol());
	}


//La fonction calculMin qui gere le calcul de la valeur du noeud Min
	public int calculMin(Game game, int prof){
		int col,tmp;
		int min = MAXEVAL;


		//Si on est a la profondeur voulue, on evalue
		if(prof == 0){
			//fonction d'evalutation
			return eval(game);
		}

		//si la partie est fini on retourne l'eval
		if(game.isFull()){
			return eval(game);
		}

		for (col=0; col < game.getNbcolumn(); ++col){
			if(!game.fullColumn(col)){
				//on joue cette colonne
				game.play(col, this.getSymbol());
				//on appele la fonction calculMax pr lancer l'IA
				tmp = calculMax(game, (prof-1));

				if(tmp<min){
					min = tmp;					
				}
				//sinon on annule ce coup
				game.cancelMove(col);
			}
		}
		return min;
	}


//La fonction calculMax qui gere le calcul de la valeur du noeud Min
	public int calculMax(Game game, int prof){
		int col,tmp;
		int max = MINEVAL;


		//Si on est a la profondeur voulue, on evalue
		if(prof == 0){
			//fonction d'evalutation
			return eval(game);
		}

		//si la partie est fini on retourne l'eval
		if(game.isFull()){
			return eval(game);
		}

		for (col=0; col<game.getNbcolumn(); ++col){
			if(!game.fullColumn(col)){
				//on joue cette colonne
				game.play(col, this.getSymbol());
				//on appele la fonction calculMin pr lancer l'IA
				tmp = calculMin(game, (prof-1));

				if(tmp>max){
					max = tmp;					
				}
				//sinon on annule ce coup
				game.cancelMove(col);
			}
		}
		return max;
	}

//La fonction qui calcul le score
	public int calcScore(int cntpion, int cntplayer){
		//regarder le nombre de pions
		switch(cntpion){
			case 1:
				return 10*cntplayer;
			case 2:
				return 30*cntplayer;
			case 3:
				return 90*cntplayer;
			default :
				return 0;
		}
	}

//La fonction qui calcul le nombre de pions
	public int comptePions(Game game){
		int nbPions = 0;
		for (int col=0; col< game.getNbcolumn();col++){
			for (int line=0;line<game.getNbline();line++){
				if(game.getGrid(line,col) != 0)
					nbPions++;
			}		
		}
		return nbPions;
	}


//La fonction d'evaluation
	public int eval(Game game){
	int cntplayer = 0;
	int cntpion = 0;
	int score =0;
		//Si le jeu est fini
		if(game.isFull()){
			//si l'IA a gagner
			if(play.getWinner() == this.getSymbol()){
				return 1000-comptePions(game);
			}
			else if(play.getWinner() == 0){
				return 0;
			}
			else{
				return -1000+comptePions(game);
			}

		}
		score += calcScore(cntpion, cntplayer);

		//verification verticale
		for (int col=0; col< game.getNbcolumn(); ++col){
			cntplayer = 0;
			for(int line=(game.getNbline()-1); line>=0; --line){
				if(game.getGrid(line,col) != 0){
					cntpion++;
					if(game.getGrid(line,col) == this.getSymbol())
						cntplayer++;
					else
						cntplayer--;
				}
			}			
		}


		//Verification horizontale
		for (int line=0; line< game.getNbline(); ++line){
			cntplayer = 0;
			for(int col=game.getNbcolumn()-1; col>=0; --col){
				if(game.getGrid(line,col) != 0){
					cntpion++;
					if(game.getGrid(line,col) == this.getSymbol())
						cntplayer++;
					else
						cntplayer--;
				}
			}			
		}

		//Verification diagonale haut gauche
		for (int col=0; col< game.getNbcolumn(); ++col){
			for(int line=game.getNbline()-1; line>=0; --line){
				cntplayer =0;
				for(int x=line,y=col; (x < game.getNbline()) && (y < game.getNbcolumn());x++,y++){
					if(game.getGrid(x,y) == this.getSymbol())
						cntplayer++;
					else
						cntplayer--;
				}
			}			
		}

		//Verification diagonale haut droite
		for (int col=0; col< game.getNbcolumn(); ++col){
			for(int line=game.getNbline()-1; line>=0; --line){
				cntplayer =0;
				for(int x=line, y=col; (x<game.getNbline() && y>=0); ++x,y--){
					if(game.getGrid(x,y) == this.getSymbol())
						cntplayer++;
					else
						cntplayer--;
				}
			}			
		}
	return score;
	}
}

