package com.bhs.hexxagonium;

/**
 * Ётот класс хранит состо€ние игрового пол€
 * @author Kanashov Anton
 *
 */
public class Cells {
	
	public static final int CELL_NONE = -1;
	public static final int CELL_EMPTY = 0;
	public static final int CELL_RUB = 1;
	public static final int CELL_PEARL = 2;
	public static final int WIDTH = 9;
	//public static final float MIN_RADIUS = 2.35f;
	public static final float MIN_RADIUS = 1.3f;
	
	private int[] cells;
	
	public Cells(){
		cells = new int[]{
			-1, -1, -1, 0, 2, 0, -1, -1, -1,
			-1, 0, 0, 0, 0, 0, 0, 0, -1,
			1, 0, 0, 0, 0, 0, 0, 0, 1,
			0, 0, 0, 0, -1, 0, 0, 0, 0,
			0, 0, 0, -1, 0, -1, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0,
			2, 0, 0, 0, 0, 0, 0, 0, 2,
			-1, -1, 0, 0, 0, 0, 0, -1, -1,
			-1, -1, -1, -1, 1, -1, -1, -1, -1
		};
	}
	
	/**
	 * —оздает копию состо€ни€
	 * @param cells исходное состо€ние
	 */
	public Cells(int[] cells){
		this.cells = cells.clone();
	}
	
	/**
	 * ƒелает ход и возвращает полученное состо€ние игрового пол€
	 * @param xfrom ’ координата исходной €чейки
	 * @param yfrom Y координата исходной €чейки
	 * @param xto ’ координата целевой €чейки
	 * @param yto Y координата целевой €чейки
	 * @return
	 */
	public Cells getMove(int xfrom, int yfrom, int xto, int yto){
		if(getCell(xfrom, yfrom) <= 0){
			System.out.println("\n\nERROR!!!\n\n");
			return this.copy();
		}
		float minRadius = MIN_RADIUS;
		Cells temp = this.copy();
		float[] from = toCoords(xfrom, yfrom);                    //переводим в координаты €чеек размером 1 дл€ удобства рассчетов
		float[] to = toCoords(xto, yto);
		/*from[0] = (float)xfrom*1.5f;                              
		from[1] = (float)yfrom*2 + (float)xfrom%2*0.5f;
		to[0] = (float)xto*1.5f;
		to[1] = (float)yto*2 + (float)xto%2*0.5f;*/
		float dist = (float)Math.sqrt(Math.pow(from[0]-to[0], 2) + Math.pow(from[1]-to[1], 2));
		System.out.println(dist+"");
		int pl = temp.getCell(xfrom, yfrom);                      //фишка игрока, который делает ход
		if(dist <= minRadius && dist > 0){
			temp.setCell(xto, yto, temp.getCell(xfrom, yfrom));   //копируем фишку
		}else if(dist <= minRadius*2){
			temp.setCell(xto, yto, temp.getCell(xfrom, yfrom));   //перемещаем фишку
			temp.setCell(xfrom, yfrom, Cells.CELL_EMPTY);
		}else{
			System.out.println("\n\nERROR!!! Too long distance!\n\n");
		}
		for(int i=0; i<Cells.WIDTH*Cells.WIDTH; i++){             //захватываем €чейки
			int x = i%Cells.WIDTH;
			int y = i/Cells.WIDTH;
			float[] coords = toCoords(x, y);
			dist = (float)Math.sqrt(Math.pow(to[0]-coords[0], 2) + Math.pow(to[1]-coords[1], 2));
			if(dist <= minRadius && dist > 0){
				int c = temp.getCell(x, y);
				if(c != pl && c > 0){
					temp.setCell(x, y, pl);
				}				
			}
		}
		
		return temp;
	}
	
	/**
	 *  опирует состо€ние
	 * @return 
	 */
	public Cells copy(){		
		return new Cells(cells);
	}
	
	/**
	 * ¬озвращает значение €чейки по координатам (значением €вл€етс€ число, определ€ющее кака€ фишка стоит на данной €чейке или пуста€/не используема€ €чейка)
	 * @param x
	 * @param y
	 * @return
	 */
	public int getCell(int x, int y){
		return cells[Cells.WIDTH*y + x];
	}
	
	/**
	 * ¬озвращает значени€ €чейки напр€мую из массива
	 * @param i
	 * @return
	 */
	public int getCell(int i){
		return cells[i];
	}
	
	/**
	 * ”станавливает значени€ €чейки по ЄЄ координатам
	 * @param x
	 * @param y
	 * @param val
	 */
	public void setCell(int x, int y, int val){
		cells[Cells.WIDTH*y + x] = val;
	}
	
	/**
	 * ¬озвращает количество фишек игрока и соперника в виде массива
	 * 0 - рубины
	 * 1 - жемчужины
	 * @return
	 */
	public int[] getChipsCount(){
		int p1 = 0;
		int p2 = 0;
		for(int i=0; i<cells.length; i++){
			if(cells[i] == Cells.CELL_RUB) p1++;
			if(cells[i] == Cells.CELL_PEARL) p2++;
		}
		return new int[]{p1, p2};
	}
	
	/**
	 * ѕровер€ет, кто из игроков выиграл или сыграл в ничью, и возвращает значение
	 * 0 - ничь€
	 * 1 - победа соперника
	 * 2 - победа игрока
	 * @return
	 */
	public byte getWinPlayer(){		
			
		int[] counts = getChipsCount();
		if(getMovesCount(Cells.CELL_RUB) == 0 || getMovesCount(Cells.CELL_PEARL) == 0){
			System.out.println("Nothing moves");
		}
		System.out.println("Rubies = "+counts[0]);
		System.out.println("Pearls = "+counts[1]);
		
		if(((counts[1] - counts[0]) == 0 && getEmptyCount() == 0) 
				|| (getMovesCount(Cells.CELL_RUB) == 0 && getMovesCount(Cells.CELL_PEARL) == 0 && getEmptyCount() > 0)){
			return 0; //ничь€			
		}else if((counts[1] - counts[0] < 0 && getEmptyCount() == 0) || (getMovesCount(Cells.CELL_PEARL) == 0 || counts[1] == 0)){
			return 1; //победа соперника
		}else if((counts[1] - counts[0] > 0 && getEmptyCount() == 0) || getMovesCount(Cells.CELL_RUB) == 0 || counts[0] == 0){
			return 2; //победа игрока
		}else return -1;			
		
	}
	
	/**
	 * ¬озвращает число возможных ходов
	 * @param pl “екущий игрок, дл€ которого провести проверку
	 * @return
	 */
	public int getMovesCount(int pl){
		int result = 0;
		
		for(int i=0; i<Cells.WIDTH*Cells.WIDTH; i++){ //ищем свою €чейку
			Point from = new Point(i%Cells.WIDTH, i/Cells.WIDTH);			
			if(getCell(from.x, from.y) == pl){
				for(int j=0; j<Cells.WIDTH*Cells.WIDTH; j++){ //ќѕ“»ћ»«»–ќ¬ј“№!!! //проходим все варианты ходов
					Point to = new Point(j%Cells.WIDTH, j/Cells.WIDTH);
					if(getCell(to.x, to.y) == Cells.CELL_EMPTY){
						
						float dist = from.getRealDist(to);						
						if(dist <= Cells.MIN_RADIUS*2 && dist > 0)							
							result++;												
						
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * ¬озвращает число пустых €чеек
	 * @return
	 */
	public int getEmptyCount(){
		int result = 0;
		for(int i=0; i<cells.length; i++){
			if(cells[i] == Cells.CELL_EMPTY) result++;			
		}
		return result;
	}
	
	/*private float[] toCoords(int x, int y){
		return new float[]{(float)x*1.5f, (float)y*2 + (float)x%2*0.5f};
	}*/
	
	/**
	 * переводит координаты €чейки из массива в реальные координаты сот (€чеек, расположенных в сотовом виде)
	 * @param x
	 * @param y
	 * @return
	 */
	public float[] toCoords(int x, int y){
		return new float[]{(float)x*1.0f, (float)y*1 + (float)x%2*0.5f};
	}
}
