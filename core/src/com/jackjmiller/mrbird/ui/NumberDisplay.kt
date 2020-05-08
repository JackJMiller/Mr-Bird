package com.jackjmiller.mrbird.ui

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.jackjmiller.mrbird.MrBird

class NumberDisplay {

    val PIXEL_SIZE = 6f
    val WIDTH = PIXEL_SIZE * 3
    val HEIGHT = PIXEL_SIZE * 5
    var textures = Array(10, {i -> Texture(i.toString()+".png")})
    var x = MrBird.WIDTH / 2
    var y = MrBird.HEIGHT / 2 - HEIGHT / 2

    fun render(batch: SpriteBatch, number: Int) {

        val displayValue = number.toString()

        var tempX: Float = x - (displayValue.length * (WIDTH + PIXEL_SIZE) - PIXEL_SIZE) / 2

        for (char in displayValue) {
            batch.draw(textures[char.toInt()-48], tempX, y, WIDTH, HEIGHT)
            tempX += WIDTH + PIXEL_SIZE
        }
    }


}