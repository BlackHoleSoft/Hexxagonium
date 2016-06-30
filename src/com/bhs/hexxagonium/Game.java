package com.bhs.hexxagonium;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

/**
 * �������� ����� ����������, � ������� ���������� ���������� ������� � ����������
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
	 * ��������� �����
	 */
	public void start(){
		running = true;	
		thread.start();
	}
	
	/**
	 * �������������
	 */
	public void init(){
		bgCircles = new Circles(100, 30, 0, getWidth(), getHeight());
		board = new Board(this);
	}
	
	/**
	 * ������ Loop, ���������� ������ ���� � �������� ����� ���������
	 */
	public void render(){		
		updateDt();
		update();
		int w = getWidth();
		int h = getHeight();
		
		bs = getBufferStrategy(); 
        if (bs == null) {
        	createBufferStrategy(2); //������� BufferStrategy ��� ������ ������
        	requestFocus();
            return;
        }        
        Graphics2D g2d = (Graphics2D)bs.getDrawGraphics(); //�������� Graphics �� ��������� ���� BufferStrategy
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, w, h);
		g2d.setColor(Color.BLACK);
		//g2d.drawString(deltaTime+"", 10, 10);
		
		bgCircles.draw(g2d);
		board.draw(g2d);
        
        g2d.dispose();
        bs.show(); //��������
	}	
	
	/**
	 * ���������� ���������� ������ ����
	 */
	public void update(){
		bgCircles.update(deltaTime);
	}
	
	/**
	 * ��������� ���������� �������� ������ ����������
	 */
	public void stop(){
		running = false;
	}
	
	/**
	 * ���������� ������� ����
	 * @return
	 */
	public Board getBoard(){
		return board;
	}
	
	/**
	 * ��������� Delta time ������ ����
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
				if(Thread.currentThread() == thread) Thread.sleep((long)(1000/fps)); //������������� ����� ������ � �������
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
