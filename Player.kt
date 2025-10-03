package com.example.exclusivegame

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color

class Player(var x: Float, var y: Float) {

    private val radius = 50f

    fun update() {
        // For now, player only moves with touch events
    }

    fun draw(canvas: Canvas, paint: Paint) {
        paint.color = Color.GREEN
        canvas.drawCircle(x, y, radius, paint)
    }

    fun checkCollision(enemy: Enemy): Boolean {
        val dx = x - enemy.x
        val dy = y - enemy.y
        val distance = Math.sqrt((dx * dx + dy * dy).toDouble()).toFloat()
        return distance < radius + enemy.radius
    }
}
