package com.bhs.hexxagonium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Этот класс позволяет хранить все кружки, летающие на заднем плане и управлять ими
 * @author Kanashov Anton
 *
 */
public class Circles implements DrawableEntity {
	
	private final float MAX_RADIUS = 10;
	private float initVx, initVy;
	private ArrayList<Circle> circles;
	private Rectangle area;
	private Color[] colors;
	private float timer, timeToChangeAccel;
	private float maxAccel;
	
	/**
	 * 
	 * @param count Число кружков
	 * @param vx Начальная скорость по х
	 * @param vy Начальная скорость по у
	 * @param w Ширина области
	 * @param h Высота области
	 */
	public Circles(int count, float vx, float vy, int w, int h){	
		timer = 0;
		timeToChangeAccel = 1.0f;
		maxAccel = 1.0f;
		area = new Rectangle((int)-MAX_RADIUS, (int)-MAX_RADIUS, (int)(w+MAX_RADIUS*2), (int)(h+MAX_RADIUS*2));
		initVx = vx;
		initVy = vy;
		circles = new ArrayList<Circle>();
		colors = new Color[]{
				new Color(1.0f, 0.1f, 0.0f, 0.3f),
				new Color(0.9f, 0.9f, 0.0f, 0.3f),
				new Color(1.0f, 0.0f, 0.7f, 0.3f),
				new Color(0.7f, 0.1f, 0.9f, 0.3f),
				new Color(0.0f, 0.9f, 0.1f, 0.3f),
				new Color(0.0f, 0.9f, 0.6f, 0.3f)};
		
		for(int i=0; i<count; i++){
			int col = (int)(Math.random()*colors.length);
			/*circles.add(new Circle((float)(Math.random()*area.getMaxX()+area.getMinX()),
					(float)(Math.random()*area.getMaxY()+area.getMinY()), initVx, initVy, MAX_RADIUS, colors[col]));*/
			circles.add(new Circle((float)(Math.random()*area.getMaxX()+area.getMinX()),
					(float)(Math.random()*area.getMaxY()+area.getMinY()), initVx, initVy, (float)Math.random()*MAX_RADIUS, colors[col]));
		}
	}
	
	@Override
	public void update(float dt){		
		
		for(Circle c : circles){
			updateCircle(c, dt);
		}
	}
	
	/**
	 * Обновляет состояние кружков. Вызывается каждый кадр
	 * @param c Конкретный кружок
	 * @param dt Delta time
	 */
	private void updateCircle(Circle c, float dt){
		if(c.getX() > area.getMaxX() || c.getX() < area.getMinX()){
			c.setX((float)area.getMinX());
			c.setY((float)(Math.random()*area.getMaxY()));
			c.setVx(20);
		}
		if(c.getY() > area.getMaxY() || c.getY() < area.getMinY()){
			c.setY((float)area.getMinY());
			c.setX((float)(Math.random()*area.getMaxY()));
			c.setVy(20);
		}
		
		if(timer > timeToChangeAccel){
			timer = 0;
			c.setAccel((float)Math.random()*maxAccel*2-maxAccel, (float)Math.random()*maxAccel*2-maxAccel);
		}else{
			timer += dt;
		}		
		
		c.update(dt);
	}
	
	@Override
	public void draw(Graphics2D g2d){
		for(Circle c : circles){			
			c.draw(g2d);
		}
	}
	
}
