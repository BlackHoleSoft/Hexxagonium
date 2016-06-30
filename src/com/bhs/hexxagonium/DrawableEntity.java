package com.bhs.hexxagonium;

import java.awt.Graphics2D;

/**
 * ��������� ��� ������ �������, ������� ����� ���� ��������� �� ������ � ������ ����������� ������ ����
 * @author Kanashov Anton
 *
 */
public interface DrawableEntity {
	
	/**
	 * �������� ����������� ������� ������ ����
	 * @param g2d
	 */
	public void draw(Graphics2D g2d);
	
	/**
	 * �������� ���������� ������� ������� ������ ����
	 * @param dt Delta time - ������� ������ ������ �� 1 ����
	 */
	public void update(float dt);
	
}
