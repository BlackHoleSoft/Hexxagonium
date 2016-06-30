package com.bhs.hexxagonium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * ���� ����� ������������ �������������� ������������ � ������� �����, ������������ ��� � ��������� �������� ������ ����
 * 
 * @author Kanashov Anton
 *
 */
public class Board implements DrawableEntity {	
	
	private final int BOARD_HEIGHT = 1024; //px
	private final float BOARD_OFFSET_X = 162;
	private final float BOARD_OFFSET_Y = 80;
	private final float BOARD_GRID_X = 80;
	private final float BOARD_GRID_Y = 100;
	private final float HLIGHT_OFFSET_X = -33;
	private final float HLIGHT_OFFSET_Y = -31;
	private final float SELECTION_RADIUS = 30;
	
	private int state;
	private float scale;
	private BufferedImage image;
	private Game game;
	private Cells cells;
	private ArrayList<Chip> chips;          //�����
	private ArrayList<Highlight> hlights;   //��� ��������� �����
	private float boardX;
	
	private boolean isSelected;             //������� �� ������
	private int[] selectedCell;             //���������� ��������� ������
	private Highlight selectedHlight;
	private ArrayList<Highlight> moveCells; //� ����� ������ ����� ������ ���
	private int[] nextCell;                 //���������� ����. ������
	private int playerChip;                 //��� ����� ������
	
	private AIPlayer ai;
	//private boolean canMove;
	
	/**
	 * �����������
	 * @param game ������ �� ������ ������ Game
	 */
	public Board(Game game){
		this.game = game;
		scale = (float)game.getHeight()/BOARD_HEIGHT;
		image = Assets.field;
		cells = new Cells();
		chips = new ArrayList<Chip>();
		hlights = new ArrayList<Highlight>();
		isSelected = false;
		selectedCell = new int[2];
		nextCell = new int[2];
		playerChip = Cells.CELL_PEARL;
		ai = new AIPlayer(Cells.CELL_RUB, 1);	
		state = -1;                                                    //����� ��� �� �������
		
		boardX = game.getWidth()/2 - (int)(image.getWidth()/2*scale);  //������� ���� �� �
		updateCells();
		updateHlights();
	}

	@Override
	public void draw(Graphics2D g2d) {		
		g2d.drawImage(image, (int)boardX, 0, (int)(image.getWidth()*scale), (int)(image.getHeight()*scale), null);
		drawCells(g2d);
		
		if(state == 0){
    		drawMessage(true, "�����!", g2d);
    	}else if(state == 1){
    		drawMessage(true, "�� ���������!", g2d);
    	}else if(state == 2){
    		drawMessage(true, "�� ��������!", g2d);
    	}
	}

	@Override
	public void update(float dt) {		
		
	}
	
	/**
	 * ��������� ������������ ����� �� ����
	 */
	public void updateCells(){
		chips = new ArrayList<Chip>();
		for(int i=0; i<Cells.WIDTH; i++){
			for(int j=0; j<Cells.WIDTH; j++){
				int cell = cells.getCell(i, j);
				if(cell > 0){
				    	chips.add(new Chip(cell, scale, 
				    			boardX+(BOARD_OFFSET_X+BOARD_GRID_X*i)*scale, (BOARD_OFFSET_Y+BOARD_GRID_Y*j+i%2*BOARD_GRID_Y/2)*scale));				    	
				}
			}
		}
	}
	
	/**
	 * ��������� ������������ ��������� �����
	 */
	public void updateHlights(){
		hlights = new ArrayList<Highlight>();
		for(int i=0; i<Cells.WIDTH; i++){
			for(int j=0; j<Cells.WIDTH; j++){
				//int cell = cells.getCell(i, j);				
				hlights.add(new Highlight(boardX+(BOARD_OFFSET_X+BOARD_GRID_X*i+HLIGHT_OFFSET_X)*scale,
						(BOARD_OFFSET_Y+BOARD_GRID_Y*j+i%2*BOARD_GRID_Y/2+HLIGHT_OFFSET_Y)*scale, scale, 1));				    	
				
			}
		}
	}
	
	/**
	 * ������������ ������ ������ ��������� ������
	 */
	public void highlightCells(){
		if(isSelected){
			moveCells = new ArrayList<Highlight>();
			for(int i=0; i<hlights.size(); i++){
				float[] cp = hlights.get(i).getCenterPosition();
				float[] cp2 = selectedHlight.getCenterPosition();
				float dist = (float)Math.sqrt(Math.pow(cp2[0]-cp[0], 2) + Math.pow(cp2[1]-cp[1], 2));
				if(dist < BOARD_GRID_X*2 && dist != 0 && cells.getCell(i/Cells.WIDTH, i%Cells.WIDTH) == Cells.CELL_EMPTY){
					hlights.get(i).enable((int)(dist/BOARD_GRID_X));
					moveCells.add(hlights.get(i));
				}
			}
		}else{
			for(int i=0; i<hlights.size(); i++){
				hlights.get(i).disable();
			}
		}
	}	
	
	/**
	 * �������� ��������� ������ (���������� � ����������� ������� ����) � ������ ���
	 */
	public void selectCell(){  //�������� ������ (���������� � ����������� ������� ����) � ������ ���
		Point mp = game.getMousePosition();
		for(int i=0; i<hlights.size(); i++){
			Highlight h = hlights.get(i);
			float[] cp = h.getCenterPosition();
			float dist = (float)Math.sqrt((mp.getX()-cp[0])*(mp.getX()-cp[0])+(mp.getY()-cp[1])*(mp.getY()-cp[1]));
			if(dist <= SELECTION_RADIUS){  //��������, �� ����� ������ �� ������
				int x = i/Cells.WIDTH;
				int y = i%Cells.WIDTH;
				if(h.isEnable() && h == selectedHlight){
					h.disable();
					selectedHlight = null;
					isSelected = false;
				}else if(cells.getCell(x, y) == playerChip && !isSelected){
					h.enable(1);
					selectedCell[1] = y; //y
					selectedCell[0] = x; //x
					selectedHlight = h;
					isSelected = true;
					System.out.println(selectedCell[0]+"; "+selectedCell[1]);
				}
				highlightCells();
			}
		}
		if(isSelected){
			for(int i=0; i<moveCells.size(); i++){		
			    Highlight h = moveCells.get(i);
			    int j = hlights.indexOf(h);
			    float[] cp = h.getCenterPosition();
			    float dist = (float)Math.sqrt((mp.getX()-cp[0])*(mp.getX()-cp[0])+(mp.getY()-cp[1])*(mp.getY()-cp[1]));
			    if(dist <= SELECTION_RADIUS){  //��������, �� ����� ������ �� ������
				    int x = j/Cells.WIDTH;
				    int y = j%Cells.WIDTH;				    
				    nextCell[0] = x;
				    nextCell[1] = y;				    
				    if(state < 0){
				    	cells = cells.getMove(selectedCell[0], selectedCell[1], x, y);   //������ ���
				    }
				    state = cells.getWinPlayer();				    
				    isSelected = false;				    
				    highlightCells();
				    updateCells();	
				    if(state < 0){
				    	cells = ai.move(cells.copy());
				    }
				    state = cells.getWinPlayer();		    
				    updateCells();
			    }
		    }
		}
	}
	
	/**
	 * ���������� ������� ��������� (�������, ��������, �����)
	 * @return
	 */
	public int getState(){
		return state;
	}
	
	/**
	 * ������� ��������� �� ��������� ����
	 * @param draw ���������� ���������
	 * @param text ����� ���������
	 * @param g2d ���� ������ ������ �������
	 */
	private void drawMessage(boolean draw, String text, Graphics2D g2d){
		int width = 600;
		int height = 100;
		g2d.setColor(new Color(0.7f, 0.1f, 0.9f, 0.5f));
		g2d.fillRect(game.getWidth()/2-width/2, game.getHeight()/2-height/2, width, height);
		g2d.setColor(Color.WHITE);
		g2d.drawString(text, game.getWidth()/2-50, game.getHeight()/2-5);
		g2d.setColor(Color.BLACK);
		//game.stop();
	}
	
	/**
	 * ������������ ��� ����� � ���������
	 * @param g2d
	 */
	private void drawCells(Graphics2D g2d){				
		for(Highlight h : hlights){
			h.draw(g2d);
		}
		for(Chip c : chips){
			c.draw(g2d);
		}
	}	
	
}
