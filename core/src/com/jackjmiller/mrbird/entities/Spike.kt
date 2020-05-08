package com.jackjmiller.mrbird.entities

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.jackjmiller.mrbird.MrBird

class Spike(var y: Int, private var isOnLeftWall: Boolean) {

    var sprite: Sprite
    var x: Float
    var xShown: Float
    var xHidden: Float
    var isActive = false
    var isMoving = false
    var xDirection = 0
    var speed = 1
    var rectangle: Rectangle

    init {
        var texture = Texture("spike.png")
        sprite = Sprite(texture)
        if (isOnLeftWall) {
            sprite.flip(true, false)
            xShown = 0f
            xHidden = -texture.width.toFloat() - 5
        }
        else {
            xShown = MrBird.WIDTH - texture.width
            xHidden = MrBird.WIDTH + 5
        }
        x = xHidden
        rectangle = Rectangle(x, y.toFloat(), sprite.width.toFloat(), sprite.height.toFloat())
    }

    fun update() {
        if (isMoving) {
            x += xDirection * speed
            if (isActive && x == xShown || !isActive && x == xHidden) isMoving = false
            rectangle = Rectangle(x, y.toFloat(), sprite.width.toFloat(), sprite.height.toFloat())
        }
    }

    fun render(batch: SpriteBatch?) {
        batch!!.draw(sprite, x, y.toFloat())
    }

    fun show() {
        isActive = true
        if (x != xShown) {
            isMoving = true
            xDirection = if (isOnLeftWall) 1 else -1
        } else {
            isMoving = false
        }
    }

    fun hide() {
        isActive = false
        if (x != xHidden) {
            isMoving = true
            xDirection = if (isOnLeftWall) -1 else 1
        }
        else {
            isMoving = false
        }
    }

    fun respawn() {
        isActive = false
        isMoving = false
        x = xHidden
        xDirection = 0
        rectangle = Rectangle(x, y.toFloat(), sprite.width.toFloat(), sprite.height.toFloat())
    }


}