package com.example.exclusivegame

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color

class Enemy(var x: Float, var y: Float) {

    val radius = 50f
    private var dx = 5f
    private var dy = 5f

    fun update() {
        x += dx
        y += dy

        if (x < radius || x > 1080 - radius) dx = -dx
        if (y < radius || y > 1920 - radius) dy = -dy
    }

    fun draw(canvas: Canvas, paint: Paint) {
        paint.color = Color.RED
        canvas.drawCircle(x, y, radius, paint)
    }
}
