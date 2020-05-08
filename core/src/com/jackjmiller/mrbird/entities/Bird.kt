package com.jackjmiller.mrbird.entities

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.jackjmiller.mrbird.MrBird

class Bird {

    var texture = Texture("bird.png")
    var sprite = Sprite(texture)
    var x = (MrBird.WIDTH - sprite.width) / 2
    var y = -texture.width.toFloat()
    var xSpeed = 1.8f
    var direction = 1
    var ySpeed = 0f
    var rectangle = Rectangle(x, y, 13f, texture.height.toFloat())
    var isAlive = true


    fun update() {

        // update position
        x += xSpeed * direction
        y += ySpeed

        rectangle = Rectangle(if (direction == 1) x else x + 4f, y, 13f, texture.height.toFloat())

        ySpeed += -0.2f

        y = MrBird.limit(y, -texture.height.toFloat(), MrBird.HEIGHT)

    }

    fun jump() {
        ySpeed = 3.3f
    }

    fun turn() {
        direction *= -1
        sprite.flip(true, false)
    }

    fun render(batch: SpriteBatch) {
        batch.draw(sprite, x, y)
    }

    fun kill() {
        if (!isAlive) return

        turn()
        isAlive = false
    }

    fun respawn() {
        x = (MrBird.WIDTH - sprite.width) / 2
        y = (MrBird.HEIGHT - sprite.height) / 2
        direction = 1
        ySpeed = 0f
        rectangle = Rectangle(x, y, 13f, texture.height.toFloat())
        isAlive = true
        sprite = Sprite(texture)
    }

}