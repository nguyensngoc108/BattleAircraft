package com.mygdx.battleship.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.battleship.Entities.Cell;
import com.mygdx.battleship.Entities.Ship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import static com.badlogic.gdx.math.MathUtils.random;
enum Direction {L,R,U,D};

//create an AI that will automaticlly place ships
public class AIplayer {
    private int currentX;
    private int currentY;
    private int times = 1;
    public Direction dir = Direction.R;
    private  ArrayList<Cell> cells;
    private  ArrayList<Cell> removeCells;
    private  ArrayList<Ship> aIShips;

    private List<List<Integer>> selectCell = new ArrayList<List<Integer>>(); //list of coordinate of cell to select

    public int numShip =0;

    //create an array to store the cells in each ships
    private int checkShip[] = {0,0,0,0};

    public  AIplayer(ArrayList<Cell> cells) {
        this.cells = cells;
        aIShips = new ArrayList<Ship>();

        //construct selectCell
        for (int i = 1; i <= 451; i+=50) {
            for (int j = 1; j <= 451; j+=50) {
                List<Integer> innerList = new ArrayList<Integer>();
                innerList.add(i);
                innerList.add(j);
                selectCell.add(innerList);
            }
        }
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

    public boolean selectCell(boolean isPlayerTurn, boolean isNew){

        if(isNew){
            int ran = random.nextInt(4,8);//selectCell.size()
            int touchX = selectCell.get(ran).get(0);
            int touchY = selectCell.get(ran).get(1);
            currentX = touchX;
            currentY = touchY;
            deleteSelectedCell(touchX, touchY); //delete the selected cell out of selectCell list

            for (Cell cell : cells) {
                if (cell.checkInput(touchX, touchY)) {
                    //removeCells.add(cell);
                    if (cell.isShip) { //if cell that isShip is clicked
                        cell.setTexture(new Texture("redCell.png"));
                        isNew = false;
                        for (int i = 0; i < 4; i++) {
                            if (cell.getShipNum()[i] == true) {
                                checkShip[i]++;
                                break;
                            }
                        }
                        aIAlgorithm(isNew);
                    }else{
                        isPlayerTurn = true;
                        cell.setTexture(new Texture("CellBlack.jpg"));
                    }
                    break;
                }
            }
            return isPlayerTurn;
        }else {
            boolean temp = aIAlgorithm(isNew);
            return temp;
        }
    }
    public boolean aIAlgorithm(boolean isNew) {

        int tempX = currentX;
        int tempY = currentY;
        checkSwitchDir();
        switch (dir){
            case R: {
                tempX = currentX + 50 * times;
                tempY = currentY;
                break;
            }
            case L:{
                tempX = currentX - 50 * times;
                tempY = currentY;
                break;
            }
            case U:{
                tempX = currentX ;
                tempY = currentY + 50 * times;
                break;
            }
            case D:{
                tempX = currentX ;
                tempY = currentY - 50 * times;
                break;
            }

        }


        for (Cell cell : cells) {
            if (cell.checkInput(tempX, tempY)) {
                if (cell.isShip) {
                    cell.setTexture(new Texture("redCell.png"));
                    for (int i = 0; i < 4; i++) {
                        if (cell.getShipNum()[i]) {
                            checkShip[i]++;
                            int totalShipCell = 0;
                            this.times++;
                            for (int j = 0; j < 4; j++) {
                                totalShipCell += checkShip[j];
                            }
                            if (totalShipCell == 12) {
                                // end
                            }
                            if (i == 0 && checkShip[i] == 2) {
                                isNew = true;
                                this.dir = Direction.R;
                            } else if (i == 1 && checkShip[i] == 3) {
                                isNew = true;
                                this.dir = Direction.R;
                            } else if (i == 2 && checkShip[i] == 3) {
                                isNew = true;
                                this.dir = Direction.R;
                            } else if (i == 3 && checkShip[i] == 4) {
                                isNew = true;
                                this.dir = Direction.R;
                            }
                            break;
                        }
                    }
                    aIAlgorithm(isNew);
                } else {
                    cell.setTexture(new Texture("CellBlack.jpg"));
                    return false;
                }
                break;
            }
        }
        return true;
    }

    public void checkSwitchDir(){
        if(!checkIfPresent(currentX + 50 * times, currentY) ||
                !checkIfPresent(currentX -50 * times, currentY)||
                !checkIfPresent(currentX , currentY+50*times) ||
                !checkIfPresent(currentX , currentY - 50*times)){
            switch (dir){
                case R:{
                    dir = Direction.L;
                    this.times =1;
                    break;
                }
                case L:{
                    dir = Direction.U;
                    this.times =1;
                    break;
                }
                case U: {
                    dir = Direction.D;
                    this.times =1;
                    break;
                }
                case D: {
                    dir = Direction.R;
                    this.times =1;
                    break;
                }
            }
        }
    }

    private void deleteSelectedCell(int touchX, int touchY) {
        Iterator<List<Integer>> iter = selectCell.iterator();
        while (iter.hasNext()) {
            List<Integer> list = iter.next();
            if (list.get(0) == touchX && list.get(1) == touchY) {
                iter.remove();
            }
        }
    }


    public boolean checkIfPresent(int x, int y) {
        for (List<Integer> pair : selectCell) {
            if (pair.get(0) == x && pair.get(1) == y) {
                return true;
            }
        }
        return false;
    }



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
