package com.example.calc.calc.bmi

import kotlin.math.pow
import kotlin.math.roundToInt

class CalculatorBmi() {
    fun standardFormula(weight:Double, height: Double): String {
        return (weight/ height.pow(2.0)).toString()
    }
    fun getMeter(unit:String,num:Double):Double{
        return when (unit){
            "cm"-> num / 100
            "m" -> num
            else -> 0.0
        }
    }
    fun getKilogram(unit:String,num:Double):Double{
        return when(unit){
            "kg" -> num
            "lb" -> num / 2.205
            else -> 0.0
        }
    }
}