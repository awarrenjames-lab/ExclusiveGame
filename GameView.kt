package com.example.exclusivegame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.MotionEvent

class GameView(context: Context) : SurfaceView(context), Runnable {

    private val thread: Thread = Thread(this)
    private var playing = false
    private val paint: Paint = Paint()

    private val player: Player = Player(100f, 100f)
    private val enemy: Enemy = Enemy(400f, 400f)
    private val gameState: GameState = GameState()

    private val surfaceHolder: SurfaceHolder = holder

    init {
        paint.textSize = 48f
        paint.color = Color.WHITE
    }

    override fun run() {
        while (playing) {
            update()
            draw()
            control()
        }
    }

    private fun update() {
        player.update()
        enemy.update()
        if (player.checkCollision(enemy)) {
            gameState.isGameOver = true
        }
    }

    private fun draw() {
        if (surfaceHolder.surface.isValid) {
            val canvas: Canvas = surfaceHolder.lockCanvas()
            canvas.drawColor(Color.BLACK)

            player.draw(canvas, paint)
            enemy.draw(canvas, paint)

            if (gameState.isGameOver) {
                paint.color = Color.RED
                canvas.drawText("Game Over", width/2f - 100, height/2f, paint)
            }

            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    private fun control() {
        try {
            Thread.sleep(17) // ~60fps
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun pause() {
        playing = false
        try {
            thread.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun resume() {
        playing = true
        if (!thread.isAlive) {
            Thread(this).start()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!gameState.isGameOver) {
            when (event.action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                    player.x = event.x
                    player.y = event.y
                }
            }
        }
        return true
    }
}
