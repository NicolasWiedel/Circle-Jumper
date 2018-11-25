package com.jga.jumper.screen.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jga.jumper.config.GameConfig;
import com.jga.util.ViewportUtils;
import com.jga.util.debug.DebugCameraController;

public class GameRenderer implements Disposable {

    // == attributes ==
    private final GameController controller;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private DebugCameraController debugCameraController;

    // == constructor ==


    public GameRenderer(GameController controller) {
        this.controller = controller;
        init();
    }

    // == init ==
    private void init(){
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);
    }

    // == public methods ==
    public void render(float delta){
        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(camera);

        viewport.apply();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        renderer.circle(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y, 3, 30);

        renderer.end();

        renderDebug();
    }

    public void resize(int width, int height){
        viewport.update(width, height, true);
        ViewportUtils.debugPixelsPerUnit(viewport);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    // == private methods ==
    private void renderDebug(){
        ViewportUtils.drawGrid(viewport, renderer, GameConfig.CELL_SIZE);
    }
}
