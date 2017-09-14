
public abstract class Tablero {

	private static int size;
	private static int[][] tablero;
	
	public static int[][] solve(int newSize) {
		size = newSize;
		tablero = new int[size][size];

		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				tablero[i][j] = 0;

		resuelve(0);

		if(checkCorrecto()) {
			System.err.println("Resuelto con éxito:\n");
			print();
		}

		else
			System.err.println("Irresoluble\n");
		
		return tablero;
	}

	private static boolean checkCorrecto() {
		int total = 0;

		for(int i=0;i<size;i++) 
			for(int j=0;j<size;j++) 
				if(tablero[i][j]==1)
					total++;

		return total==size;
	}

	private static void resuelve(int n) {
		if(n>=size)
			return;

		int[][] copia = clonar(tablero);
		
		for(int i=0;i<size;i++) {
			
			if(coloca(1,n,i)) 
				resuelve(n+1);
			
			if(checkCorrecto())
				return;
			
			tablero = clonar(copia);
		}
		
	}

	private static boolean coloca(int n, int x, int y) {
		if(x>=size || y>=size || tablero[x][y]!=0)
			return false;
		
		tablero[x][y] = n;
		fixRecta(x,y);
		fixDiagonal(x,y);
		
		if(!checkFilas(x))
			return false;
		
		return true;
	}
	
	private static boolean checkFilas(int y) {
		
		for(int i=y+1;i<size;i++) {
			int total = 0;
			for(int j=0;j<size;j++) {
				if(tablero[i][j]==-1)
					total++;
			}
			if(total==size)
				return false;
			
		}
		
		return true;
	}

	private static void fixDiagonal(int x, int y) {
		fixDiagUL(x-1,y-1);
		fixDiagUR(x-1,y+1);
		fixDiagBR(x+1,y+1);
		fixDiagBL(x+1,y-1);
	}

	//Diagonal superior izquierda
	private static void fixDiagUL(int x, int y) {
		if(x<0 || y<0)
			return;

		tablero[x][y] = -1;

		fixDiagUL( x-1, y-1 );
	}

	//Diagonal superior derecha
	private static void fixDiagUR(int x, int y) {
		if(x<0 || y>size-1)
			return;

		tablero[x][y] = -1;

		fixDiagUR( x-1, y+1 );
	}

	//Diagonal inferior derecha
	private static void fixDiagBR(int x, int y) {
		if(x>size-1 || y>size-1)
			return;

		tablero[x][y] = -1;

		fixDiagBR( x+1, y+1 );
	}

	//Diagonal inferior izquierda
	private static void fixDiagBL(int x, int y) {
		if(x>size-1 || y<0)
			return;

		tablero[x][y]=-1;

		fixDiagBL( x+1, y-1 );
	}

	private static void fixRecta(int x, int y) {
		for(int i=0;i<size;i++) {
			if(i!=y)
				tablero[x][i]=-1;

			if(i!=x)
				tablero[i][y]=-1;
		}
	}

	private static int[][] clonar(int[][] objetivo){
		int[][] temp = new int[objetivo.length][objetivo[0].length];

		for(int i=0;i<temp.length;i++){
			for(int j=0;j<temp[i].length;j++){
				temp[i][j] = objetivo[i][j];
			}
		}

		return temp;
	}

	private static void print() {
		String data = "";

		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(tablero[i][j]==-1)
					data += "- ";
				
				else
					data += tablero[i][j] + " ";
			}

			data += "\n";
		}

		System.out.println(data);
	}

}
