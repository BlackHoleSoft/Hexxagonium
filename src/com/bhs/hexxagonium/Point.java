package com.bhs.hexxagonium;

/**
 * ����������� �� java.awt.Point � ��������� ���������� ���������� �� �������� ������� � ������� (�� ���������� ������� ���� ��� � ������� ���������)
 * @author Kanashov Anton
 *
 */
public class Point extends java.awt.Point {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Point(int x, int y){
		super(x, y);
	}
	
	/**
	 * ���������� �������� ���������� ����� �������� (�� � �������� �����������, � � ��������)
	 * @param to �� ����� ����� ������� ���������
	 * @return
	 */
	public float getRealDist(Point to){
		float[] coords1 = toCoords(this);
		float[] coords2 = toCoords(to);
		return (float)Math.sqrt(Math.pow(coords1[0]-coords2[0], 2) + Math.pow(coords1[1]-coords2[1], 2));
	}
	
	/**
	 * ��������� ���������� �� �������� ������� � ������� (�� ���������� ������� ���� ��� � ������� ���������)
	 * @param p ����� � ������� �����������
	 * @return
	 */
	public float[] toCoords(Point p){
		return new float[]{(float)p.x*1.0f, (float)p.y*1 + (float)p.x%2*0.5f};
	}
}
