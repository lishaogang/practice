
enum Queen{
	FORBD, WHITE, BLACK, EMPTY
}


class Board{
	Queen board[][];
	int size;
	public Board(int n){
		size = n;
		board = new Queen[n][n];
		for(int i = 0; i < size; i++){
			for(int j = 0; j <size; j++){
				this.board[i][j] = Queen.EMPTY;
			}
		}
	}

	public Board(Board b){
		this.size = b.size;
		this.board = new Queen[this.size][this.size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j <size; j++){
				this.board[i][j] = b.board[i][j];
			}
		}
	}

	public void show(){
		System.out.println("-----------");
		for(int i = 0; i < size; i++){
			for(int j = 0; j <size; j++){
				System.out.print(this.board[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("-----------");

	}


	public void cleanForbidden(){
		for(int i = 0; i < size; i++){
			for(int j = 0; j <size; j++){
				this.board[i][j] = this.board[i][j] == Queen.FORBD ? Queen.EMPTY : this.board[i][j];
			}
		}
	}

	public boolean tryPut(Queen queen,int x,int y){
		if(x > this.size || y > this.size || x*y < 0){
			return false;
		}
		//(x,y) may be FORBD or already has been put a Queen, return false in that case
		if(board[x][y] != Queen.EMPTY)
			return false;
		if(check(queen, x, y))
			return true;
		else
			return false;
	}

	private boolean check(Queen queen,int x,int y){
		//check row
		for(int i = 0; i < this.size; i++){
			if (queen == board[x][i])
				return false;
		}
		//check column
		for (int i = 0; i < this.size; i++) {
			if (queen == board[i][y])
				return false;
		}
		//check diagonal
		///*
		for (int i = x, j = y; i >= 0 && j >= 0; i--,j--) {
			if(queen == board[i][j])
				return false;
		}
		for (int i = x, j = y; i < this.size && j < this.size; i++,j++) {
			if(queen == board[i][j])
				return false;
		}
		for (int i = x, j = y; i < this.size && j >= 0; i++,j--) {
			if(queen == board[i][j])
				return false;
		}
		for (int i = x, j = y; i >= 0 && j < this.size; i--,j++) {
			if(queen == board[i][j])
				return false;
		}
		//*/
		board[x][y] = queen;
		return true;
	}
}



public class slowQueen {
	public static int NQueen(Board board, Queen queen, int n){
		//how many status can we find in such a board as b to put n queens?
		if (n == 0) {
			//board.show();
			return 1;
		}
		Board b = new Board(board);
		int status = 0;
		for (int i = 0; i < b.size; i++) {
			for (int j = 0; j < b.size; j++) {
				if (!b.tryPut(queen, i,j)) {
					continue;
				}
				
				status += NQueen(b, queen, n-1);
				//the next two line must execute in this order
				board.board[i][j] = Queen.FORBD;
				b = new Board(board);
			}
			
		}
		
		return status;
		
	}


	public static int NNQueen(Board board, Queen queen, int wqueen, int bqueen){
		Board b = new Board(board);
		int status = 0;

		if(wqueen == 0){
			board.cleanForbidden();
			return NQueen(board,Queen.BLACK,bqueen);
		}
		if(bqueen == 0){
			board.cleanForbidden();
			return NQueen(board,Queen.WHITE,wqueen);
		}

		for (int i = 0; i < b.size; i++) {
			for (int j = 0; j < b.size; j++) {
				if(!b.tryPut(queen,i,j)){
					continue;
				}
				status += NNQueen(b, queen, wqueen-1, bqueen);
				//the next two line must execute in this order 
				board.board[i][j] = Queen.FORBD;
				b = new Board(board);
			}
		}
		return status;
	}
	
	public static void main(String[] args) {
		int N = 8;
		Board b = new Board(N);
		long start = System.currentTimeMillis();
		
		//int answer = NQueen(b,Queen.WHITE,N);
		int answer = NNQueen(b,Queen.WHITE,N,N);

		long end = System.currentTimeMillis();
		System.out.printf("answer:%d time:%dms\n",answer,(end - start));
		
	}

}