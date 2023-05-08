package com.mygdx.battleship.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.battleship.Entities.Cell;
import com.mygdx.battleship.Entities.Ship;

import java.util.ArrayList;
import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;

//create an AI that will automaticlly place ships
public class AIplayer {


    private  ArrayList<Cell> cells;
    private  ArrayList<Cell> removeCells;
    private  ArrayList<Ship> aIShips;
    public int numShip =0;



    public  AIplayer(ArrayList<Cell> cells) {
        this.cells = cells;
        aIShips = new ArrayList<Ship>();
    }

    public void placeShip(){


        while (numShip < 4) {
            int touchX = MathUtils.random(Gdx.graphics.getWidth() / 2 + 100, Gdx.graphics.getWidth());
           // int touchY = MathUtils.random(0, Gdx.graphics.getHeight());
            int touchY = Gdx.graphics.getHeight() - MathUtils.random(Gdx.graphics.getHeight());

//        int touchX = 800;
//        int touchY = 300;

            boolean shipIsHor = MathUtils.randomBoolean(); // Generates a random boolean value (true or false)
            for (Cell cell : cells) {
                if (cell.checkInput(touchX, touchY)) {
                    Ship tempShip = new Ship(cell.getX(), cell.getY(), shipIsHor, numShip);
                    if (canPlaceShip(cell.getX(), cell.getY(), shipIsHor, tempShip.getLength())) {
                        //add ships
                        aIShips.add(tempShip);
                        numShip++;

                        // Set isShip to true for the clicked cell
                        int shipLength = tempShip.getLength();
                        float cellX = cell.getX();
                        float cellY = cell.getY();

                        // Set isShip to true for adjacent cells
                        for (Cell adjacentCell : cells) {
                            float adjacentCellX = adjacentCell.getX();
                            float adjacentCellY = adjacentCell.getY();
                            if (shipIsHor) {
                                if (adjacentCellX >= cellX && adjacentCellX <= cellX + 50 * (shipLength - 1) && adjacentCellY == cellY) {
                                    adjacentCell.setIsShip(true);
                                }
                            } else {
                                if (adjacentCellX == cellX && adjacentCellY >= cellY && adjacentCellY <= cellY + 50 * (shipLength - 1)) {
                                    adjacentCell.setIsShip(true);

                                }
                            }
                        }

                    }
                }
            }
        }
    }

    public boolean selectCell(boolean isPlayerTurn){
        int touchX = MathUtils.random(0,Gdx.graphics.getWidth() / 2 - 100);
        // int touchY = MathUtils.random(0, Gdx.graphics.getHeight());
        int touchY = Gdx.graphics.getHeight() - MathUtils.random(Gdx.graphics.getHeight());

        for (Cell cell : cells) {
            if (cell.checkInput(touchX, touchY)) {
                //removeCells.add(cell);
                if (cell.isShip) //if cell that isShip is clicked
                    cell.setTexture(new Texture("redCell.png"));
                else {
                    isPlayerTurn = true;
                    cell.setTexture(new Texture("CellBlack.jpg"));

                }
            }
        }
        return isPlayerTurn;
    }
//    public Ship getRandomShip(ArrayList<Cell> cells) {
//        Random random = new Random();
//        int length = 0;
//        switch (random.nextInt(4)) {
//            case 0:
//                length = 2;
//                break;
//            case 1:
//                length = 3;
//                break;
//            case 2:
//                length = 3;
//                break;
//            case 3:
//                length = 4;
//                break;
//        }
//        ArrayList<Cell> occupiedCells = new ArrayList<Cell>();
//        boolean isHor = random.nextBoolean();
//        int startX, startY;
//        if (isHor) {
//            startX = random.nextInt(10 - length);
//            startY = random.nextInt(10);
//        } else {
//            startX = random.nextInt(10);
//            startY = random.nextInt(10 - length);
//        }
//        for (int i = 0; i < length; i++) {
//            int x = isHor ? startX + i : startX;
//            int y = isHor ? startY : startY + i;
//            Cell cell = getCell(cells, x, y);
//            if (cell == null || cell.isShip()) {
//                return getRandomShip(cells); // Try again if any cell is already occupied
//            }
//            occupiedCells.add(cell);
//        }
//        return new Ship(startX * 50, startY * 50, isHor, length * 50);
//    }
//
//    private Cell getCell(ArrayList<Cell> cells, int x, int y) {
//        for (Cell cell : cells) {
//            if (cell.getX() == x * 50 && cell.getY() == y * 50) {
//                return cell;
//            }
//        }
//        return null;
//    }



    private boolean canPlaceShip(float x, float y, boolean isHorizontal, int shipLength) {
        if (isHorizontal) {
            if(x + 50*shipLength > Gdx.graphics.getWidth()/2 - 100 ){ //check if ship will be out of panel
                return false;
            }
            for (Cell cell : cells) {
                for (int i = 0; i <= 50*(shipLength-1); i += 50) {
                    if (cell.getX() == x + i && cell.getY() == y && cell.isShip) {//check if the ship will be overlapped
                        return false;
                    }
                }
            }
        } else {
            if(y + 50*shipLength > Gdx.graphics.getHeight()){ // check if ship out of panel
                return false;
            }
            for (Cell cell : cells) {
                for (int i = 0; i <= 50*(shipLength-1); i += 50) {
                    if (cell.getY() == y + i && cell.getX() == x && cell.isShip) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public ArrayList<Ship> getAIShip(){
        return aIShips;
    }
}
