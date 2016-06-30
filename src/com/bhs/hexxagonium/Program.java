package com.bhs.hexxagonium;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * Основной класс программы. Инициализирует окна, графику и вызывает основной игровой поток
 * @author Kanashov Anton
 *
 */
public class Program {
	
	private static final String version = "1.0";	
	private static JFrame frame;	
	private static Game game;
	private static int width, height;

	public static void main(String[] args) {
		
		width = 1280;
		height = 720;
		
		String title = "Hexxagonium v"+version;			
		frame = new JFrame(title);
		frame.setBounds(10, 10, width, height);			
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		game = new Game();		                  
		game.setPreferredSize(new Dimension(width, height));
		frame.add(game);		
		frame.pack();                                            //устанавливаем размеры фрейма по внутреннему компоненту		
		
		frame.addWindowListener(new WindowAdapter(){             //обработка закрытия фрейма

			@Override
			public void windowClosing(WindowEvent e) {
				game.stop();
				System.exit(0);
			}
			
		});
		
		game.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				game.getBoard().selectCell();				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		frame.setVisible(true);
		game.init();
		game.start();
	}

}
