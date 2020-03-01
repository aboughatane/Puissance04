public class Game{
	private int nbColumn;
	private int nbLine;
	private int[][] grid;

	public final static int VIDE = 0;
  	public final static int X = 1;
  	public final static int O = 2;

  	//Constructeur
	public Game(int nbColumn, int nbLine){
		newGame(nbColumn, nbLine);
	}

	public Game(){
		newGame(7,7);
	}

	//Getter
	public int getNbcolumn(){
		return nbColumn;
	}

	public int getNbline(){
		return nbLine;
	}

	public int getGrid(int i,int j){
		return grid[i][j];
	}


	//Initialiser la grille
	public void newGame(int nbLine, int nbColumn){
    	this.nbColumn = nbColumn;
    	this.nbLine = nbLine;
    	grid = new int[nbColumn][nbLine];
    	for (int col = 0; col < nbColumn ; col++){
      		for (int line = 0; line < nbLine; line++){
        		grid[col][line] = VIDE;
      		}
    	}
    }

	//Afficher la grille
	public void displayGrid(){
		System.out.println("\n\n\t\t\t\t  Puissance 4");

		System.out.print("\t\t\t");
		for (int col=0; col<nbColumn; ++col){
			System.out.print("____");
		}
		System.out.println();
		for (int line=0; line<nbLine; ++line){
			System.out.print("\t\t\t");
			for (int col=0; col<nbColumn; ++col){
				switch (grid[col][line]) {
        		case VIDE:
          			System.out.print("|_"+' '+"_");
          			break;
        		case X:
          			System.out.print("|_"+'X'+"_");
          			break;
        		case O:
          			System.out.print("|_"+'O'+"_");
          			break;
        	}

			}
		System.out.println();
		}

		//Numeroter les colonne
		System.out.print("\t\t\t");
		for (int col=0; col<nbColumn; ++col){
			System.out.print("  "+ (col+1)+" ");
		}
		System.out.print("\n\n\n");
	}

	//verifier si la grille est pleine
    public boolean isFull() {
    for (int col = 0; col < nbColumn; col++) {
      for (int line = 0; line < nbLine; line++) {
        if(grid[col][line] == VIDE){
          return false;
        }
      }
    }
    return true;
    }

    //verifier si la colonne est pleine
    public boolean fullColumn(int col){
    	for (int line=0; line<nbLine; ++line){
    		if(grid[col][line] == VIDE){
    			return false;
    		}   		
    	}
    return true;
    }

    //Fonction qui annule un coup
    public void cancelMove(int col){
    	for(int line=(nbLine-1); line>=0; line--){
    		if(grid[line][col] != VIDE)
    			grid[line][col] = VIDE;
    	}	
    }

	//Jouer un pion sur la grille
	public boolean play(int col, int symbol){

			if((col < 0 || col >= nbColumn)){
				return false;
			}

		//Trouver une case VIDE dans la colonne
		for(int line=(nbLine-1); line>=0; line--){
			if(grid[col][line] == VIDE){
				grid[col][line] = symbol;
				return true;
			}
		}
		return false;
	}

	//Verifier s'il y a un alignement de 4 dans la grille
	public boolean alignement4(){
		//Verticalement (|)
		for(int col=0; col<nbColumn; ++col){
			if(find4Alignment(col,0,0,1))
				return true;
		}

		//Horizontalemet (---) 
		for (int line=0; line<nbLine; ++line){
			if(find4Alignment(0,line,1,0))
				return true;
		}

		// Diagonales (cherche depuis la ligne du bas)
    	for (int col = 0; col < nbColumn; col++) {
      		// Première diagonale ( / )
      		if (find4Alignment(col, 0, 1, 1)) {
        		return true;
      		}
      		// Deuxième diagonale ( \ )
      		if (find4Alignment(col, 0, -1, 1)) {
        		return true;
      		}
    	}

    	// Diagonales (cherche depuis les colonnes gauches et droites)
    	for (int ligne = 0; ligne < nbLine; ligne++) {
      	// Première diagonale ( / )
      		if (find4Alignment(0, ligne, 1, 1)) {
        		return true;
      		}
      	// Deuxième diagonale ( \ )
      		if (find4Alignment(nbLine - 1, ligne, -1, 1)) {
        	return true;
      		}
    	}

    // On n'a rien trouvé
    return false;
  	}

	//Verification de la victoire en cherchant l'alignement de 4pions
	public boolean find4Alignment(int oCol, int oLine, int dCol, int dLine){
		int compteur = 0;
		int symbol = VIDE;

		int curCol = oCol;
		int curLine = oLine;

		while(((curCol >=0) && (curCol < nbColumn)) && ((curLine >=0) && (curLine < nbLine))){
			if(grid[curLine][curCol] != symbol){
				symbol = grid[curLine][curCol];
				//Si le symbol change on réinitialise la compteur 
				compteur = 1;
			}
			else{
				//sinon on l'incremente
				compteur++;
			}
			//Si le compteur vaut 4 on sort et on retourne true
			if((symbol != VIDE) && (compteur == 4)){
				return true;
			}

			//On passe au suivant
			curCol += dCol;
			curLine += dLine;
		}
	// Pas d'alignement trouvé
	return false;
	}

//IA 
	//Fonction qui verifier quel genre d'alignement vertical/horizontal...
	public char alignement(){
	char res='_';
		if(alignement3()){
			for(int col=0; col<nbColumn; ++col)
				if(find3Alignment(col,0,0,1))
					res ='h';
			for(int line =0; line <nbLine; ++line)
				if(find3Alignment(0,line,1,0))
					res = 'v';
		}
		return res;
	}

	//La fonction qui trouve s'il y a 3 alignement pour l'IA
 	public boolean alignement3(){
		//Verticalement (|)
		for(int col=0; col<nbColumn; ++col){
			if(find3Alignment(col,0,0,1))
				return true;
		}

		//Horizontalemet (----) 
		for (int line=0; line<nbLine; ++line){
			if(find3Alignment(0,line,1,0))
				return true;
		}

		// Diagonales (cherche depuis la ligne du bas)
    	for (int col = 0; col < nbColumn; col++) {
      		// Première diagonale ( / )
      		if (find3Alignment(col, 0, 1, 1)) {
        		return true;
      		}
      		// Deuxième diagonale ( \ )
      		if (find3Alignment(col, 0, -1, 1)) {
        		return true;
      		}
    	}

    	// Diagonales (cherche depuis les colonnes gauches et droites)
    	for (int ligne = 0; ligne < nbLine; ligne++) {
      	// Première diagonale ( / )
      		if (find3Alignment(0, ligne, 1, 1)) {
        		return true;
      		}
      // Deuxième diagonale ( \ )
      		if (find3Alignment(nbLine - 1, ligne, -1, 1)) {
        		return true;
      		}
    	}

    // On n'a rien trouvé
    return false;
  	}

  	//Chercher l'alignement de 3pions
	public boolean find3Alignment(int oCol, int oLine, int dCol, int dLine){
		int compteur = 0;
		int symbol = VIDE;

		int curCol = oCol;
		int curLine = oLine;

		while(((curCol >=0) && (curCol < nbColumn)) && ((curLine >=0) && (curLine < nbLine))){
			if(grid[curLine][curCol] != symbol){
				symbol = grid[curLine][curCol];
				//Si le symbol change on réinitialise la compteur 
				compteur = 1;
			}
			else{
				//sinon on l'incremente
				compteur++;
			}
			//Si le compteur vaut 4 on sort et on retourne true
			if((symbol != VIDE) && (compteur == 3)){
				return true;
			}

			//On passe au suivant
			curCol += dCol;
			curLine += dLine;
		}
	// Pas d'alignement trouvé
	return false;
	}
}
