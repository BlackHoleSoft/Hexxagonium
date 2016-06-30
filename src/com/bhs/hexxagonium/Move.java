package com.bhs.hexxagonium;

/**
 * Этот класс хранит информацию о ходе: начальную позицию и конечную.
 * @author Kanashov Anton
 *
 */
public class Move {
	
	private Point from;
	private Point to;
	
	/**
	 * 
	 * @param xfrom Координата нач. позиции по х
	 * @param yfrom Координата нач. позиции по у
	 * @param xto Координата кон. позиции по х
	 * @param yto Координата кон. позиции по у
	 */
	public Move(int xfrom, int yfrom, int xto, int yto){
		from = new Point(xfrom, yfrom);
		to = new Point(xto, yto);
	}

	/**
	 * Возвращает позицию from
	 * @return
	 */
	public Point getFrom() {
		return from;
	}

	/**
	 * Возвращает позицию to
	 * @return
	 */
	public Point getTo() {
		return to;
	}
	
	
}
