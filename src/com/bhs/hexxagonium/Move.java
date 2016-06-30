package com.bhs.hexxagonium;

/**
 * ���� ����� ������ ���������� � ����: ��������� ������� � ��������.
 * @author Kanashov Anton
 *
 */
public class Move {
	
	private Point from;
	private Point to;
	
	/**
	 * 
	 * @param xfrom ���������� ���. ������� �� �
	 * @param yfrom ���������� ���. ������� �� �
	 * @param xto ���������� ���. ������� �� �
	 * @param yto ���������� ���. ������� �� �
	 */
	public Move(int xfrom, int yfrom, int xto, int yto){
		from = new Point(xfrom, yfrom);
		to = new Point(xto, yto);
	}

	/**
	 * ���������� ������� from
	 * @return
	 */
	public Point getFrom() {
		return from;
	}

	/**
	 * ���������� ������� to
	 * @return
	 */
	public Point getTo() {
		return to;
	}
	
	
}
