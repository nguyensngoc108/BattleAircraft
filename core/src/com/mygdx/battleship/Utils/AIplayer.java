package com.mygdx.battleship.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.mygdx.battleship.Entities.Cell;
import com.mygdx.battleship.Entities.Ship;

import java.util.ArrayList;
import java.util.Random;

//create an AI that will automaticlly place ships
public class AIplayer {


    private static Cell[] cells;
    private static ArrayList<Cell> removeCells;
    private static ArrayList<Ship> ships;


    public AIplayer() {
        // TODO Auto-generated constructor stub


    }
    private void placeAIShips() {
        AIplayer aiPlayer = new AIplayer();
        ArrayList<Cell> cells = new ArrayList<Cell>();
        ArrayList<Cell> removeCells = new ArrayList<Cell>();
        ArrayList<Ship> ships = new ArrayList<Ship>();
        for (int x = 0; x <= Gdx.graphics.getWidth()/2 - 150; x += 50) {
            for (int y = 0; y <= 450; y += 50) {
                cells.add(new Cell(x, y, new Texture("Cell2.png"), 50, 50));
            }
        }
    }
    public Ship getRandomShip(ArrayList<Cell> cells) {
        Random random = new Random();
        int length = 0;
        switch (random.nextInt(4)) {
            case 0:
                length = 2;
                break;
            case 1:
                length = 3;
                break;
            case 2:
                length = 3;
                break;
            case 3:
                length = 4;
                break;
        }
        ArrayList<Cell> occupiedCells = new ArrayList<Cell>();
        boolean isHor = random.nextBoolean();
        int startX, startY;
        if (isHor) {
            startX = random.nextInt(10 - length);
            startY = random.nextInt(10);
        } else {
            startX = random.nextInt(10);
            startY = random.nextInt(10 - length);
        }
        for (int i = 0; i < length; i++) {
            int x = isHor ? startX + i : startX;
            int y = isHor ? startY : startY + i;
            Cell cell = getCell(cells, x, y);
            if (cell == null || cell.isShip()) {
                return getRandomShip(cells); // Try again if any cell is already occupied
            }
            occupiedCells.add(cell);
        }
        return new Ship(startX * 50, startY * 50, isHor, length * 50);
    }

    private Cell getCell(ArrayList<Cell> cells, int x, int y) {
        for (Cell cell : cells) {
            if (cell.getX() == x * 50 && cell.getY() == y * 50) {
                return cell;
            }
        }
        return null;
    }



    public static boolean canPlaceShipAI(int x, int y, boolean isHorizontal) {
        if (isHorizontal) {
            if (x + 200 > Gdx.graphics.getWidth() / 2 - 100) { //check if ship will be out of panel
                return false;
            }
            for (Cell cell : cells) {
                if (cell.getX() == x + 150 && cell.getY() == y && cell.isShip == true) {//check if the ship will be overlapped
                    return false;
                }
            }
        } else {
            if (y + 200 > Gdx.graphics.getHeight()) { // check if ship out of panel
                return false;
            }
            for (Cell cell : cells) {
                if (cell.getY() == y + 150 && cell.getX() == x && cell.isShip == true) {//check if the ship will be overlapped //&& cell.getY() + 100 >= y
                    return false;
                }
            }
        }
        return true;
    }

}
