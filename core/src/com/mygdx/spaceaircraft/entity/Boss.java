package com.mygdx.spaceaircraft.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.spaceaircraft.setting.React;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

enum Direction {N, NE, E, SE, S, SW, W, NW}

public class Boss implements Entity {

    public static final int SPEED = 90;
    public static final int WIDTH = 105;
    public static final int HEIGHT = 135;

    private static final Direction[] DIRECTIONS = Direction.values();
    private Direction dir = Direction.NE;

    public static Texture[] texture = new Texture[2];

    private float x, y;
    private double bossHealth = 1;
    private React react;

    private List<BigAsteroid> spawnedAsteroids;
    private boolean isSpawningAsteroids;
    private float asteroidSpawnTimer;
    private float asteroidSpawnInterval = 1.0f; // Adjust as needed
    private float asteroidSpeed = 100.0f; // Adjust as needed

    public Boss(Texture texture0, Texture texture1) {
        this.x = 200;
        this.y = 580;
        this.react = new React(x, y, WIDTH, HEIGHT);

        texture[0] = texture0;
        texture[1] = texture1;

        spawnedAsteroids = new ArrayList<>();
        isSpawningAsteroids = false;
        asteroidSpawnTimer = 0.0f;
    }

    @Override
    public void update(float deltaTime) {
        checkHitWall();

        switch (this.dir) {
            case N:
                y += SPEED * deltaTime;
                break;
            case NE:
                x += SPEED * deltaTime;
                y += SPEED * deltaTime;
                break;
            case E:
                x += SPEED * deltaTime;
                break;
            case SE:
                x += SPEED * deltaTime;
                y -= SPEED * deltaTime;
                break;
            case S:
                y -= SPEED * deltaTime;
                break;
            case SW:
                x -= SPEED * deltaTime;
                y -= SPEED * deltaTime;
                break;
            case W:
                x -= SPEED * deltaTime;
                break;
            case NW:
                x -= SPEED * deltaTime;
                y += SPEED * deltaTime;
                break;
        }

        react.move(x, y);



    }



    public void startSpawningAsteroids() {
        isSpawningAsteroids = true;
        asteroidSpawnTimer = 0.1f;
    }

    public void stopSpawningAsteroids() {
        isSpawningAsteroids = false;
        asteroidSpawnTimer = 0.1f;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (x < 50) {
            batch.draw(texture[1], x, y, WIDTH, HEIGHT);
        } else if (x >= 50 && x <= 100) {
            batch.draw(texture[0], x, y, WIDTH, HEIGHT);
        } else if (x > 100 && x <= 200) {
            batch.draw(texture[1], x, y, WIDTH, HEIGHT);
        } else if (x > 200 && x <= 300) {
            batch.draw(texture[0], x, y, WIDTH, HEIGHT);
        } else {
            batch.draw(texture[1], x, y, WIDTH, HEIGHT);
        }
    }

    @Override
    public React getReact() {
        return react;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public double getBossHealth() {
        return bossHealth;
    }

    public void decreaseBossHealth(double health) {
        bossHealth -= health;
    }

    public void setBossTexture(Texture texture1, Texture texture2) {
        texture[0] = texture1;
        texture[1] = texture2;
    }

    private void checkHitWall() {
        if (y <= Gdx.graphics.getHeight() / 1.5 || y >= Gdx.graphics.getHeight() - HEIGHT) {
            switch (dir) {
                case N:
                    dir = Direction.S;
                    break;
                case S:
                    dir = Direction.N;
                    break;
                case NE:
                    dir = Direction.SE;
                    break;
                case SE:
                    dir = Direction.NE;
                    break;
                case SW:
                    dir = Direction.NW;
                    break;
                case NW:
                    dir = Direction.SW;
                    break;
                case E:
                    break;
                case W:
                    break;
                default:
                    break;
            }
        }

        if (x <= -0 || x >= Gdx.graphics.getWidth() - WIDTH) {
            switch (dir) {
                case E:
                    dir = Direction.W;
                    break;
                case W:
                    dir = Direction.E;
                    break;
                case NE:
                    dir = Direction.NW;
                    break;
                case SE:
                    dir = Direction.SW;
                    break;
                case SW:
                    dir = Direction.SE;
                    break;
                case NW:
                    dir = Direction.NE;
                    break;
                case N:
                    break;
                case S:
                    break;
                default:
                    break;
            }
        }
    }

    public List<BigAsteroid> getSpawnedAsteroids() {
        return spawnedAsteroids;
    }
}
