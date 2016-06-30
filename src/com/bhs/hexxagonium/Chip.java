package com.bhs.hexxagonium;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


/**
 * Этот класс служит для отрисовки фишек. Содержит ссылки на изображения и координаты фишки.
 * @author Kanashov Anton
 *
 */
public class Chip implements DrawableEntity {
	
	private int type;
	private float scale;	
	private BufferedImage image1, image2;
	float x, y;
	
	/**
	 * 
	 * @param type Тип фишки: рубин/жемчужина
	 * @param scale Масштаб
	 * @param x
	 * @param y
	 */
	public Chip(int type, float scale, float x, float y){
		this.type = type;
		this.x = x;
		this.y = y;
		this.scale = scale;		
		image1 = Assets.ruby;
		image2 = Assets.pearl;		
	}

	@Override
	public void draw(Graphics2D g2d) {
		BufferedImage img;
		if(type == Cells.CELL_PEARL) img = image2;
		else if(type == Cells.CELL_RUB) img = image1;
		else return;
		g2d.drawImage(img, (int)x, (int)y, (int)(img.getWidth()*scale), (int)(img.getHeight()*scale), null);
	}

	@Override
	public void update(float dt) {
		
	}
	
	/**
	 * Задает позицию
	 * @param x
	 * @param y
	 */
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
}
