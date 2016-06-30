package com.bhs.hexxagonium;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

/**
 * ќсновной поток приложени€, в котором происходит обновление графики и параметров
 * @author Kanashov Anton
 *
 */
public class Game extends Canvas implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int fps;	
	private float deltaTime;
	private long lastTime;
	private Thread thread;
	private boolean running;
	private BufferStrategy bs;
	
	private Circles bgCircles;
	private Board board;
	
	
	public Game(){
		super();
		fps = 60;
		lastTime = 0;
		thread = new Thread(this);
		Assets.init();
		//renderer = new Renderer(this);			
	}
	
	/**
	 * «апускает поток
	 */
	public void start(){
		running = true;	
		thread.start();
	}
	
	/**
	 * »нициализаци€
	 */
	public void init(){
		bgCircles = new Circles(100, 30, 0, getWidth(), getHeight());
		board = new Board(this);
	}
	
	/**
	 * јналог Loop, вызываетс€ каждый кадр в основном цикле программы
	 */
	public void render(){		
		updateDt();
		update();
		int w = getWidth();
		int h = getHeight();
		
		bs = getBufferStrategy(); 
        if (bs == null) {
        	createBufferStrategy(2); //создаем BufferStrategy дл€ нашего холста
        	requestFocus();
            return;
        }        
        Graphics2D g2d = (Graphics2D)bs.getDrawGraphics(); //получаем Graphics из созданной нами BufferStrategy
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, w, h);
		g2d.setColor(Color.BLACK);
		//g2d.drawString(deltaTime+"", 10, 10);
		
		bgCircles.draw(g2d);
		board.draw(g2d);
        
        g2d.dispose();
        bs.show(); //показать
	}	
	
	/**
	 * ќбновление параметров каждый кадр
	 */
	public void update(){
		bgCircles.update(deltaTime);
	}
	
	/**
	 * ќстановка выполнени€ главного потока приложени€
	 */
	public void stop(){
		running = false;
	}
	
	/**
	 * ¬озвращает игровое поле
	 * @return
	 */
	public Board getBoard(){
		return board;
	}
	
	/**
	 * ќбновл€ет Delta time каждый кадр
	 */
	private void updateDt(){
		if(lastTime == 0){
			lastTime = System.nanoTime();
			deltaTime = 1.0f;
		}else{
			deltaTime = (float)(System.nanoTime() - lastTime)/1000000000;
		}
		
		lastTime = System.nanoTime();
	}

	@Override
	public void run() {		
		while(running){
			if(isDisplayable()) render();
			try {
				if(Thread.currentThread() == thread) Thread.sleep((long)(1000/fps)); //устанавливаем число кадров в секунду
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
