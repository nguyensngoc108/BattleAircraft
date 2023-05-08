package com.mygdx.battleship.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.battleship.BattleShipMain;
import com.mygdx.battleship.Entities.Cell;
import com.mygdx.battleship.Entities.Ship;
import com.mygdx.battleship.Utils.AIplayer;

import java.util.ArrayList;

public class GameScreen implements Screen {
    GameScreen gameScreen;
    private boolean isPlacingShip = true;
    private boolean shipIsHor = true;
    private boolean isPlayerTurn = true;
    private int numShip=0;
    private Texture background;
    private BattleShipMain game;
    private ArrayList<Ship> ships;
    private ArrayList<Cell> cells;
    private ArrayList<Cell> removeCells;
    private AIplayer aIplayer;
    private ArrayList<Ship> aIShips;

    public GameScreen(BattleShipMain game) {
        this.game = game;
        background = new Texture("whitebg.jpg");
        cells = new ArrayList<Cell>();
        removeCells = new ArrayList<Cell>();
        ships = new ArrayList<Ship>();



        // Create cells in the left panel
        for (int x = 0; x <= Gdx.graphics.getWidth()/2 - 150; x += 50) {
            for (int y = 0; y <= 450; y += 50) {
                cells.add(new Cell(x, y, new Texture("Cell2.png"), 50, 50));
            }
        }

        // Create cells in the right panel
        for (int x = Gdx.graphics.getWidth()/2 + 100 ; x <= Gdx.graphics.getWidth(); x += 50) {
            for (int y = 0; y <= 450; y += 50) {
                cells.add(new Cell(x, y, new Texture("Cell2.png"), 50, 50));
            }
        }

        aIplayer = new AIplayer(this.cells);


    }

    @Override
    public void show() {
        aIplayer.placeShip();
        aIShips = aIplayer.getAIShip();
        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.CONTROL_LEFT || keycode == Input.Keys.CONTROL_RIGHT) {
                    shipIsHor = !shipIsHor; // Toggle ship orientation
                    return true;
                }
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                return false;
            }
        });
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Placing ship session
        if(isPlacingShip) {
            //placing ships
            if (Gdx.input.justTouched()) {
                int touchX = Gdx.input.getX();
                int touchY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Invert Y-axis

                if(touchX <= Gdx.graphics.getWidth() /2 - 100){ //check coordinate for left panel
                    for (Cell cell : cells) {
                        if (cell.checkInput(touchX, touchY)) {
                            Ship tempShip =new Ship(cell.getX(), cell.getY(), shipIsHor,numShip);
                            if ( canPlaceShip(cell.getX(), cell.getY(), shipIsHor,tempShip.getLength())) { //check whether can place ship or not
                                //add ships
                                ships.add(tempShip);
                                numShip++;
                                if (numShip == 4) {
                                    isPlacingShip = false; //placing ship session end
                                }
                               // Set isShip to true for the clicked cell
                                int shipLength = tempShip.getLength();
                                float cellX = cell.getX();
                                float cellY = cell.getY();

                                // Set isShip to true for adjacent cells
                                for (Cell adjacentCell : cells) {
                                    float adjacentCellX = adjacentCell.getX();
                                    float adjacentCellY = adjacentCell.getY();
                                    if(shipIsHor){
                                        if (adjacentCellX >= cellX && adjacentCellX <= cellX + 50*(shipLength-1) && adjacentCellY == cellY ) {
                                            adjacentCell.setIsShip(true);
                                        }
                                    }else{
                                        if (adjacentCellX == cellX && adjacentCellY >= cellY && adjacentCellY <= cellY + 50*(shipLength-1)) {
                                            adjacentCell.setIsShip(true);

                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        
        }else {
            // Select cells
            if(isPlayerTurn) {
                if (Gdx.input.justTouched()) { // Check if screen was touched
                    int touchX = Gdx.input.getX();
                    int touchY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Invert Y-axis

                    if (touchX >= Gdx.graphics.getWidth() / 2 + 100) { //check coordinate for right panel
                        // Loop through the list of cells and check if any cell was clicked
                        for (Cell cell : cells) {
                            if (cell.checkInput(touchX, touchY)) {
                                removeCells.add(cell);
                                if(cell.isShip) //if cell that isShip is clicked
                                    cell.setTexture(new Texture("redCell.png"));
                                else
                                    isPlayerTurn = false;
                                    cell.setTexture(new Texture("CellBlack.jpg"));
                            }
                        }
                    }
                }
            }else{
                aIplayer.selectCell(isPlayerTurn);
            }
        }





        // Render begins
        game.batch.begin();
        game.batch.draw(background, 0, 0, BattleShipMain.WIDTH, BattleShipMain.HEIGHT);
    // render cells
        for (Cell cell : cells) {
            cell.render(game.batch);
        }
    //render ships
        for(Ship ship : ships){
            ship.render(game.batch);
        }

//        for(Ship ship : aIShips){
//            ship.render(game.batch);
//        }
        game.batch.end();
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
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        // Dispose textures
        background.dispose();
        for (Cell cell : cells) {
            cell.getTexture().dispose();
        }
    }






}
