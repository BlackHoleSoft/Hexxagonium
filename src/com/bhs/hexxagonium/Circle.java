package com.bhs.hexxagonium;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * ���� ����� ��������� �������� ������� ���� � ��������� ������, ��������, ��������� � ����������
 * @author Kanashov Anton
 *
 */
public class Circle implements DrawableEntity {
	
	private float x, y, vx, vy, ax, ay, radius;
	private Color color;
	
	/**
	 * 
	 * @param x ���������� �� �
	 * @param y ���������� �� �
	 * @param vx �������� �� �
	 * @param vy �������� �� �
	 * @param rad ������ � px
	 * @param col ����
	 */
	public Circle(float x, float y, float vx, float vy, float rad, Color col){
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.radius = rad;
		color = col;
		ax = ay = 0;
	}
	
	@Override
	public void draw(Graphics2D g2d){
		g2d.setColor(color);
		g2d.fillOval((int)(x-radius), (int)(y-radius), (int)radius*2, (int)radius*2);
		g2d.setColor(Color.BLACK);
	}
	
	@Override
	public void update(float dt){
		vx += ax * dt;
		vy += ay * dt;
		x += vx * dt;
		y += vy * dt;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * 
	 * @param x
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * 
	 * @return
	 */
	public float getY() {
		return y;
	}

	/**
	 * 
	 * @param y
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * ���������� �������� �� �
	 * @return
	 */
	public float getVx() {
		return vx;
	}

	/**
	 * ������ �������� �� �
	 * @param vx
	 */
	public void setVx(float vx) {
		this.vx = vx;
	}

	/**
	 * ���������� �������� �� �
	 * @return
	 */
	public float getVy() {
		return vy;
	}

	/**
	 * 
	 * ������ �������� �� �
	 * @param vy
	 */
	public void setVy(float vy) {
		this.vy = vy;
	}

	/**
	 * ������ ��������� �� � � �
	 * @param x
	 * @param y
	 */
	public void setAccel(float x, float y){
		ax = x;
		ay = y;
	}
	
	/**
	 * ���������� ������ �� �, �
	 * @param x
	 * @param y
	 */
	public void move(float x, float y){
		this.x += x;
		this.y += y;
	}
	
}
