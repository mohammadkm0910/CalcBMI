package com.example.calc.calc.bmi

import android.content.Context

class SaveApp(private var context: Context) {
    fun saveUsername(name: String){
        val pref = context.getSharedPreferences("main",Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("name",name)
        editor.apply()
    }
    fun saveFamilyName(family:String){
        val pref = context.getSharedPreferences("main",Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("family",family)
        editor.apply()
    }
    fun getUsernameDialog():String{
        val pref = context.getSharedPreferences("main",Context.MODE_PRIVATE)
        return pref.getString("name","").toString()
    }
    fun getUserFamilyDialog():String{
        val pref = context.getSharedPreferences("main",Context.MODE_PRIVATE)
        return pref.getString("family","").toString()
    }
    fun saveHeightUnitSpinner(position:Int){
        val pref = context.getSharedPreferences("main",Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt("height",position)
        editor.apply()
    }
    fun getHeightUnitSpinner():Int {
        val pref = context.getSharedPreferences("main",Context.MODE_PRIVATE)
        return pref.getInt("height",0)
    }
    fun saveWeightUnitSpinner(position:Int){
        val pref = context.getSharedPreferences("main",Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt("weight",position)
        editor.apply()
    }
    fun getWeightUnitSpinner():Int {
        val pref = context.getSharedPreferences("main",Context.MODE_PRIVATE)
        return pref.getInt("weight",0)
    }
}