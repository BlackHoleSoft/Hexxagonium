package com.bhs.hexxagonium;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Ётот класс загружает все необходимые изображени€ и ресурсы дл€ игры и хранит их.
 * @author Kanashov Anton
 *
 */
public class Assets {
	
	public static BufferedImage field, ruby, pearl, hlight1, hlight2;
	
	/**
	 * ¬ызываетс€ дл€ загрузки изображений и ресурсов
	 */
	public static void init(){		
		try {
		    field = ImageIO.read(new File("images/hexagon_field.png"));
		    ruby = ImageIO.read(new File("images/rubin.png"));
		    pearl = ImageIO.read(new File("images/pearl.png"));
		    hlight1 = ImageIO.read(new File("images/hl1.png"));
		    hlight2 = ImageIO.read(new File("images/hl2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
