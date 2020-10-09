package com.example.calc.calc.bmi.design

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable

object DrawableRounded {
    private const val INVALID_COLOR = -1;
    fun createRoundedRectangleDrawable(fillColor:Int,strokeColor: Int = 0, strokeWidth: Int = 0):Drawable{
        val recDrawable = GradientDrawable()
        recDrawable.shape = GradientDrawable.RECTANGLE
        if (strokeColor != INVALID_COLOR){
            recDrawable.setStroke(strokeWidth,strokeColor)
        }
        recDrawable.setColor(fillColor)
        recDrawable.cornerRadius = 20F
        return recDrawable
    }
}