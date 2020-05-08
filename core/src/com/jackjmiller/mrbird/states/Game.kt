package com.jackjmiller.mrbird.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.jackjmiller.mrbird.MrBird
import com.jackjmiller.mrbird.ui.Menu
import com.jackjmiller.mrbird.ui.NumberDisplay
import com.jackjmiller.mrbird.entities.Bird
import com.jackjmiller.mrbird.entities.Spike
import kotlin.random.Random

class Game {

    val SPIKE_ROW_SIZE = 8

    var score = 0
    var difficulty = 1
    var bird = Bird()
    var leftHandSpikes: Array<Spike> = Array(SPIKE_ROW_SIZE, { i -> Spike(MrBird.HEIGHT.toInt() - (i + 1) * 25, true) })
    var rightHandSpikes: Array<Spike> = Array(SPIKE_ROW_SIZE, { i -> Spike(MrBird.HEIGHT.toInt() - (i + 1) * 25, false) })
    var scoreDisplay = NumberDisplay()
    var menu = Menu()
    var cam = OrthographicCamera()

    init {
        cam.setToOrtho(false, 130f, 200f)
        endGame()
    }

    fun update() {

        handleInput()

        // update entities
        bird.update()
        for (spike in leftHandSpikes) spike.update()
        for (spike in rightHandSpikes) spike.update()

        if (bird.isAlive) {

            // bounce off walls and create new spike arrangement
            if (bird.x <= 0 || bird.x + bird.texture.width > 130) {
                placeSpikes(if (bird.x <= 0) leftHandSpikes else rightHandSpikes)
                bird.turn()
                if (++score % 7 == 0 && difficulty < 7) difficulty++
            }

            checkForCollisions()

        }

        menu.update(bird.isAlive)
    }

    fun render(batch: SpriteBatch?) {

        batch!!.setProjectionMatrix(cam.combined)
        Gdx.gl.glClearColor(1f, 0f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        for (spike in leftHandSpikes) {
            spike.render(batch!!)
        }
        for (spike in rightHandSpikes) {
            spike.render(batch!!)
        }

        scoreDisplay.render(batch!!, score)

        bird.render(batch!!)

        if (!bird.isAlive) {
            menu.render(batch!!)
        }
    }

    fun checkForCollisions() {
        // end game if bird touches top or bottom of screen
        if (bird.y <= -bird.texture.height || bird.y >= MrBird.HEIGHT) {
            endGame(); return
        }
        // end game if bird touches any spikes on left-hand wall
        for (spike in leftHandSpikes) {
            if (spike.rectangle.overlaps(bird.rectangle)) {
                endGame(); return
            }
        }
        // end game if bird touches any spikes on right-hand wall
        for (spike in rightHandSpikes) {
            if (spike.rectangle.overlaps(bird.rectangle)) {
                endGame(); return
            }
        }
    }

    fun placeSpikes(spikes: Array<Spike>) {

        for (spike in spikes) {
            spike.hide()
        }
        for (counter in 0.. difficulty) {
            var i = Random.nextInt(SPIKE_ROW_SIZE)
            spikes[i].show()
        }
    }

    fun restart() {
        for (spike in leftHandSpikes) {
            spike.respawn()
        }
        for (spike in rightHandSpikes) {
            spike.respawn()
        }
        bird.respawn()
        score = 0
        difficulty = 1
    }

    fun endGame() {
        bird.kill()
        menu.reset()
    }

    fun handleInput() {

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (bird.isAlive) {
                bird.jump()
            }
            else {
                restart()
            }
        }

    }

}