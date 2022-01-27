package com.example.egzaminui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent

class DrawView : androidx.appcompat.widget.AppCompatImageView {
    private var color: Int = Color.parseColor("#0000FF")
    private var width = 4f
    private val holderList: MutableList<Holder> = ArrayList()
    private var fillFlag = false



    private inner class Holder(color: Int, width: Float) {
        var path: Path
        var paint: Paint

        init {
            path = Path()
            paint = Paint()
            paint.isAntiAlias = true
            paint.strokeWidth = width
            paint.color = color
            paint.style = Paint.Style.STROKE
            paint.strokeJoin = Paint.Join.ROUND
            paint.strokeCap = Paint.Cap.ROUND
        }
    }

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!,
        attrs,
        defStyle
    ) {
        init()
    }

    private fun init() {
        holderList.add(Holder(color, width))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (holder in holderList) {
            canvas.drawPath(holder.path, holder.paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val eventX = event.x
        val eventY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                holderList.add(Holder(color, width))
                holderList[holderList.size - 1].path.moveTo(eventX, eventY)
                return true
            }
            MotionEvent.ACTION_MOVE -> holderList[holderList.size - 1].path.lineTo(eventX, eventY)
            MotionEvent.ACTION_UP -> {
            }
            else -> return false
        }
        invalidate()
        return true
    }

    fun resetPaths() {
        for (holder in holderList) {
            holder.path.reset()
        }
        invalidate()
    }

    fun setBrushColor(color: Int) {
        this.color = color
    }

    fun setWidth(width: Float) {
        this.width = width
    }

}