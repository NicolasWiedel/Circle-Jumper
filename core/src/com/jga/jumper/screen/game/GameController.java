package com.jga.jumper.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.jga.jumper.common.GameManager;
import com.jga.jumper.config.GameConfig;
import com.jga.jumper.entity.Coin;
import com.jga.jumper.entity.Monster;
import com.jga.jumper.entity.Obstacle;
import com.jga.jumper.entity.Planet;

public class GameController {

    // == constants ==
    private final Logger log = new Logger(GameController.class.getName(), Logger.DEBUG);

    // == attributes ==
    private Planet planet;
    private Monster monster;

    private float monsterStartX;
    private float monsterStartY;

    private final Array<Coin> coins = new Array<Coin>();
    private final Pool<Coin> coinPool = Pools.get(Coin.class, 10);
    private float coinTimer;

    private final Array<Obstacle> obstacles = new Array<Obstacle>();
    private final Pool<Obstacle> obstaclePool = Pools.get(Obstacle.class, 10);
    private float obstacleTimer;

    // == constructor ==
    public GameController() {
        init();
    }

    // == init ==
    private void init() {
        planet = new Planet();
        planet.setPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);

        monsterStartX = GameConfig.WORLD_CENTER_X - GameConfig.MONSTER_HALF_SIZE;
        monsterStartY = GameConfig.WORLD_CENTER_Y + GameConfig.PLANET_HALF_SIZE;

        monster = new Monster();
        monster.setPosition(
                monsterStartX, monsterStartY);
    }

    // == public methods ==
    public void update(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE ) && monster.isWalking()){
            monster.jump();
        }

        monster.update(delta);

        spawnObstacles(delta);
        spawnCoin(delta);

        checkCollision();
    }

    public Planet getPlanet() {
        return planet;
    }

    public Monster getMonster() {
        return monster;
    }

    public Array<Coin> getCoins() {
        return coins;
    }

    public Array<Obstacle> getObstacles() {
        return obstacles;
    }

    // == private methods ==
    private void spawnCoin(float delta){
        coinTimer += delta;

        // ony maxcoins allowed
        if(coins.size >= GameConfig.MAX_COINS){
            coinTimer= 0;
            return;
        }

        if(coinTimer >= GameConfig.COIN_SPAWN_TIME){
            coinTimer = 0;
            Coin coin = coinPool.obtain();
            float randomAngle = MathUtils.random(360);
            coin.setAngleDeg(randomAngle);
            coins.add(coin);
        }
    }

    private void spawnObstacles(float delta){
        obstacleTimer += delta;

        if(obstacles.size >= GameConfig.MAX_OBSTACLES){
            obstacleTimer =0;
            return;
        }

        if(obstacleTimer >= GameConfig.OBSTACLE_SPAWN_TIME){
            obstacleTimer = 0;
            Obstacle obstacle = obstaclePool.obtain();
            float randomAngle = MathUtils.random(360);
            obstacle.setAngleDeg(randomAngle);
            obstacles.add(obstacle);
        }
    }

    private void checkCollision(){
        // player <==> coins
        for(int i = 0; i < coins.size; i++){
            Coin coin = coins.get(i);

            if(Intersector.overlaps(monster.getBounds(), coin.getBounds())){
                GameManager.INSTANCE.addScore(GameConfig.COIN_SCORE);
                coinPool.free(coin);
                coins.removeIndex(i);
            }
        }
        // player <==> obstacle
        for(int i = 0; i < obstacles.size; i++){
            Obstacle obstacle = obstacles.get(i);

            if(Intersector.overlaps(monster.getBounds(), obstacle.getSensor())){
                GameManager.INSTANCE.addScore(GameConfig.OBSTACLE_SCORE);
                obstaclePool.free(obstacle);
                obstacles.removeIndex(i);
            } else if(Intersector.overlaps(monster.getBounds(), obstacle.getBounds())){
                restart();
            }
        }
    }

    private void restart(){
        coinPool.freeAll(coins);
        coins.clear();

        obstaclePool.freeAll(obstacles);
        obstacles.clear();

        GameManager.INSTANCE.reset();

        monster.reset();
        monster.setPosition(monsterStartX, monsterStartY);
    }
}
