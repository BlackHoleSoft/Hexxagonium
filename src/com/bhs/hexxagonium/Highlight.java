package com.bhs.hexxagonium;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * ���� ����� �������� �� ��������� ��������� ������
 * @author Kanashov Anton
 *
 */
public class Highlight implements DrawableEntity {
	
	private int type;
	private boolean enable;
	private float scale;
	private float x, y;
	private BufferedImage image;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param scale �������
	 * @param type ��� ��������� (�������/������)
	 */
	public Highlight(float x, float y, float scale, int type){
		this.x = x;
		this.y = y;
		this.type = type;
		enable = false;
		this.scale = scale;
		if(type == 0){
			image = Assets.hlight1;
		}else{
			image = Assets.hlight2;
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		if(enable) g2d.drawImage(image, (int)x, (int)y, (int)(image.getWidth()*scale), (int)(image.getHeight()*scale), null);
	}

	@Override
	public void update(float dt) {
		
	}
	
	/**
	 * ���������� ��� ���������
	 * @return
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * ���������� ������� ������ ������ � �������� ����������� � ���� �������
	 * @return
	 */
	public float[] getCenterPosition(){
		return new float[]{image.getWidth()*scale/2+x, image.getHeight()*scale/2+y};		
	}
	
	/**
	 * �������� ���������, ������������� ��������� ��� � ��������� �����������
	 * @param type ��� ��������� (�������/������)
	 */
	public void enable(int type){
		this.type = type;
		enable = true;
		if(type == 0){
			image = Assets.hlight1;
		}else{
			image = Assets.hlight2;
		}
	}
	
	/**
	 * ��������� ���������
	 */
	public void disable(){
		enable = false;
	}
	
	/**
	 * ���������, �������� �� ���������
	 * @return
	 */
	public boolean isEnable(){
		return enable;
	}
}
