package com.bhs.hexxagonium;

import java.awt.Graphics2D;

/**
 * Интерфейс для любого объекта, который может быть отрисован на экране и должен обновляться каждый кадр
 * @author Kanashov Anton
 *
 */
public interface DrawableEntity {
	
	/**
	 * Вызывает перерисовку объекта каждый кадр
	 * @param g2d
	 */
	public void draw(Graphics2D g2d);
	
	/**
	 * Вызывает обновление свойств объекта каждый кадр
	 * @param dt Delta time - сколько секунд прошло за 1 кадр
	 */
	public void update(float dt);
	
}
