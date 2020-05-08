package com.jackjmiller.mrbird

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.jackjmiller.mrbird.states.Game

class MrBird : ApplicationAdapter() {

    private var game: Game? = null
    var batch: SpriteBatch? = null

    companion object {
        fun limit(value: Float, min: Float, max: Float): Float {
            if (value < min) return min
            else if (value > max) return max
            else return value
        }

        const val WIDTH = 130f
        const val HEIGHT = 200f
    }

    override fun create() {
        batch = SpriteBatch()
        game = Game()
    }

    override fun render() {
        game!!.update()
        batch!!.begin()
        game!!.render(batch!!)
        batch!!.end()
    }


}