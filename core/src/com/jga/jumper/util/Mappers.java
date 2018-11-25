package com.jga.jumper.util;

import com.badlogic.ashley.core.ComponentMapper;
import com.jga.snake.component.BodyPartComponent;
import com.jga.snake.component.BoundsComponent;
import com.jga.snake.component.CoinComponent;
import com.jga.snake.component.DimensionComponent;
import com.jga.snake.component.DirectionComponent;
import com.jga.snake.component.MovementComponent;
import com.jga.snake.component.PositionComponent;
import com.jga.snake.component.SnakeComponent;
import com.jga.snake.component.TextureComponent;
import com.jga.snake.component.ZOrderComponent;

/**
 * Created by goran on 8/02/2017.
 */

public final class Mappers {

    // == constants ==
    public static final ComponentMapper<BoundsComponent> BOUNDS =
            ComponentMapper.getFor(BoundsComponent.class);

    public static final ComponentMapper<DimensionComponent> DIMENSION =
            ComponentMapper.getFor(DimensionComponent.class);

    public static final ComponentMapper<PositionComponent> POSITION =
            ComponentMapper.getFor(PositionComponent.class);

    public static final ComponentMapper<SnakeComponent> SNAKE =
            ComponentMapper.getFor(SnakeComponent.class);

    public static final ComponentMapper<MovementComponent> MOVEMENT =
            ComponentMapper.getFor(MovementComponent.class);

    public static final ComponentMapper<DirectionComponent> DIRECTION =
            ComponentMapper.getFor(DirectionComponent.class);

    public static final ComponentMapper<CoinComponent> COIN =
            ComponentMapper.getFor(CoinComponent.class);

    public static final ComponentMapper<BodyPartComponent> BODY_PART =
            ComponentMapper.getFor(BodyPartComponent.class);

    public static final ComponentMapper<TextureComponent> TEXTURE =
            ComponentMapper.getFor(TextureComponent.class);

    public static final ComponentMapper<ZOrderComponent> Z_ORDER =
            ComponentMapper.getFor(ZOrderComponent.class);

    // == constructors ==
    private Mappers() {
    }
}
