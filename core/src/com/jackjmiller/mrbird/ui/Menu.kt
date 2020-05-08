package com.jackjmiller.mrbird.ui

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.jackjmiller.mrbird.MrBird

class Menu {

    var logo = Texture("mrbird.png")
    var logoPosition = Vector2((MrBird.WIDTH - logo.width)/2, MrBird.HEIGHT)
    var logoVelocity = 0f
    var isVisible = false

    fun update(birdIsAlive: Boolean) {
        if (!birdIsAlive && logoPosition.y > MrBird.HEIGHT*0.65) {
            logoPosition.y += logoVelocity
            logoVelocity -= 0.1f
        }
    }

    fun render(batch: SpriteBatch?) {
        if (!isVisible) return

        batch!!.draw(logo, logoPosition.x, logoPosition.y)
    }

    fun reset() {
        logoPosition = Vector2((MrBird.WIDTH - logo.width)/2, MrBird.HEIGHT)
        logoVelocity = 0f
        isVisible = true
    }

}