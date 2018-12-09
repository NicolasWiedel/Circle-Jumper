package com.jga.jumper.screen.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jga.jumper.assets.AssetDescriptors;
import com.jga.jumper.assets.RegionNames;
import com.jga.jumper.common.GameManager;
import com.jga.jumper.config.GameConfig;
import com.jga.jumper.entity.Coin;
import com.jga.jumper.entity.Obstacle;
import com.jga.jumper.entity.Planet;
import com.jga.jumper.entity.Monster;
import com.jga.util.ViewportUtils;
import com.jga.util.debug.DebugCameraController;

public class GameRenderer implements Disposable {

    // == attributes ==
    private final GameController controller;
    private final SpriteBatch batch;
    private final AssetManager assetManger;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private Viewport hudViewport;
    private BitmapFont font;

    private final GlyphLayout layout = new GlyphLayout();

    private DebugCameraController debugCameraController;

    private TextureRegion backgroundRegion;
    private TextureRegion planetRegion;
    private TextureRegion obstacleRegion;
    private TextureRegion coinRegion;
    private TextureRegion monsterRegion;

    // == constructor ==
    public GameRenderer(GameController controller, SpriteBatch batch, AssetManager assetManger) {
        this.controller = controller;
        this.batch = batch;
        this.assetManger = assetManger;
        init();
    }

    // == init ==
    private void init() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        hudViewport = new FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT);

        font = assetManger.get(AssetDescriptors.FONT);

        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);

        TextureAtlas gamePlayAtlas = assetManger.get(AssetDescriptors.GAME_PLAY);

        backgroundRegion = gamePlayAtlas.findRegion(RegionNames.BACKGROUND);
        planetRegion = gamePlayAtlas.findRegion(RegionNames.PLANET);

        obstacleRegion = gamePlayAtlas.findRegions(RegionNames.OBSTACLE).first();
        coinRegion = gamePlayAtlas.findRegions(RegionNames.COIN).first();
        monsterRegion = gamePlayAtlas.findRegions(RegionNames.PLAYER).first();
    }

    // == public methods ==
    public void render(float delta) {
        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(camera);

        renderGamePlay(delta);
        renderDebug();
        renderHud();
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
        hudViewport.update(width, height, true);
        ViewportUtils.debugPixelsPerUnit(viewport);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    // == private methods ==
    private void renderGamePlay(float delta){
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        drawGamePlay(delta);

        batch.end();
    }

    private void drawGamePlay(float delta){
        // background
        batch.draw(backgroundRegion,
                0, 0,
                GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);

        // planet
        Planet planet = controller.getPlanet();
        batch.draw(planetRegion,
                planet.getX(), planet.getY(),
                planet.getWidth(), planet.getHeight());

        // obstacles
        Array<Obstacle> obstacles = controller.getObstacles();
        for(Obstacle obstacle : obstacles){
            batch.draw(obstacleRegion,
                obstacle.getX(), obstacle.getY(),
                0, 0,
                obstacle.getWidth(), obstacle.getHeight(),
                1.0f, 1.0f,
                GameConfig.START_ANGLE - obstacle.getAngleDeg()
            );

        }
        // coins
        Array<Coin> coins = controller.getCoins();
        for(Coin coin : coins){
            batch.draw(coinRegion,
                    coin.getX(), coin.getY(),
                    0, 0,
                    coin.getWidth(), coin.getHeight(),
                    1.0f, 1.0f,
                    GameConfig.START_ANGLE - coin.getAngleDeg()
            );

        }
        // monster
        Monster monster = controller.getMonster();
        batch.draw(monsterRegion,
                monster.getX(), monster.getY(),
                0, 0,
                monster.getWidth(), monster.getHeight(),
               1.0f, 1.0f,
               GameConfig.START_ANGLE - monster.getAngleDeg()
        );
    }

    private void renderDebug() {

        ViewportUtils.drawGrid(viewport, renderer, GameConfig.CELL_SIZE);

        viewport.apply();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        drawDebug();

        renderer.end();
    }

    private void drawDebug() {
        // planet
        renderer.setColor(Color.RED);
        Planet planet = controller.getPlanet();
        Circle planetBounds = planet.getBounds();
        renderer.circle(planetBounds.x, planetBounds.y, planetBounds.radius, 30);

        // monster
        renderer.setColor(Color.BLUE);
        Monster monster = controller.getMonster();
        Rectangle monsterBounds = monster.getBounds();
        renderer.rect(
                monsterBounds.x, monsterBounds.y,
                0, 0,
                monsterBounds.width, monsterBounds.height,
                1, 1,
                GameConfig.START_ANGLE - monster.getAngleDeg()
        );

        // coins
        renderer.setColor(Color.YELLOW);
        for (Coin coin : controller.getCoins()) {
            Rectangle coinBounds = coin.getBounds();
            renderer.rect(
                    coinBounds.x, coinBounds.y,
                    0, 0,
                    coinBounds.width, coinBounds.height,
                    1, 1,
                    GameConfig.START_ANGLE - coin.getAngleDeg()
            );
        }

        // obstcles
        for(Obstacle obstacle : controller.getObstacles()){
            renderer.setColor(Color.GREEN);
            Rectangle obstacleBound = obstacle.getBounds();
            renderer.rect(
                    obstacleBound.x, obstacleBound.y,
                    0, 0,
                    obstacleBound.width, obstacleBound.height,
                    1, 1,
                    GameConfig.START_ANGLE - obstacle.getAngleDeg()
            );

            // sensor
            renderer.setColor(Color.WHITE);
            Rectangle sensorBounds = obstacle.getSensor();
            renderer.rect(
                    sensorBounds.x, sensorBounds.y,
                    0, 0,
                    sensorBounds.width, sensorBounds.height,
                    1, 1,
                    GameConfig.START_ANGLE -obstacle.getAngleDeg()
            );
        }
    }

    private void renderHud() {
        hudViewport.apply();

        batch.setProjectionMatrix(hudViewport.getCamera().combined);
        batch.begin();

        drawHud();

        batch.end();
    }

    private void drawHud() {

        float padding = 20f;

        // high score
        String highScoreString = "HIGH SCORE: " + GameManager.INSTANCE.getDisplayHighScore();
        layout.setText(font, highScoreString);
        font.draw(batch, layout, padding, GameConfig.HUD_HEIGHT - layout.height);

        // score
        String scoreString = "SCORE: " + GameManager.INSTANCE.getDisplayScore();
        layout.setText(font, scoreString);
        font.draw(batch, layout,
                GameConfig.HUD_WIDTH - layout.width - padding,
                GameConfig.HUD_HEIGHT - layout.height);

        float startWaitTimer = controller.getStartWaitTimer();
        if(startWaitTimer >= 0){
            int waitTime = (int)startWaitTimer;
            String waitTimeString = waitTime == 0 ? "GO" : "" + waitTime;
            layout.setText(font, waitTimeString);
            font.draw(batch, layout,
                    (GameConfig.HUD_WIDTH - layout.width) / 2f,
                    (GameConfig.HUD_HEIGHT + layout.height) / 2f);
        }
    }
}
