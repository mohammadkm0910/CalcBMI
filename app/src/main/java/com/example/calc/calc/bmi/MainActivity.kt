package com.example.calc.calc.bmi

import android.app.AlertDialog
import android.app.Dialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.calc.calc.bmi.design.DrawableRounded
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt


@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class MainActivity : AppCompatActivity() {
    private lateinit var calculatorBmi: CalculatorBmi
    private var unitHeight = "cm"
    private var unitWeight = "kg"
    private lateinit var saveApp:SaveApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(actionbarTop)
        saveApp = SaveApp(applicationContext)
        calculatorBmi = CalculatorBmi()
        containerBmiScore.background = DrawableRounded.createRoundedRectangleDrawable(ContextCompat.getColor(this,R.color.orange800))
        dialogUserName()
        initSpinner()
        seekBarBmi.setMaxPosition(100)
        btnCalcBMI.setOnClickListener {
            initCalcAnimationClick()
            try {
                val height = edtHeight.text.toString().toDouble()
                val weight = edtWeight.text.toString().toDouble()
                val standardFormula = calculatorBmi.standardFormula(calculatorBmi.getKilogram(unitWeight,weight),calculatorBmi.getMeter(unitHeight,height))
                val result = standardFormula.toDouble().roundToInt()
                when (result) {
                    in 0..18 -> {
                        containerBmiColor.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,R.color.blue500));
                        containerBmiScore.background = DrawableRounded.createRoundedRectangleDrawable(ContextCompat.getColor(this,R.color.blue500Alpha))
                        textMsgInfoWeight.text = getString(R.string.under_weight)
                        textDescriptionBmi.text = getString(R.string.description_under_weight)
                    }
                    in 18..25 -> {
                        containerBmiColor.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,R.color.greenLight500))
                        containerBmiScore.background = DrawableRounded.createRoundedRectangleDrawable(ContextCompat.getColor(this,R.color.greenLight500Alpha))
                        textMsgInfoWeight.text = getString(R.string.healthy_weight)
                        textDescriptionBmi.text = getString(R.string.description_healthy_weight)
                    }
                    in 25..30 -> {
                        containerBmiColor.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,R.color.yellow500))
                        containerBmiScore.background = DrawableRounded.createRoundedRectangleDrawable(ContextCompat.getColor(this,R.color.yellow500Alpha))
                        textMsgInfoWeight.text = getString(R.string.pre_obesity_weight)
                        textDescriptionBmi.text = getString(R.string.description_pre_obesity_weight)
                    }
                    in 30..35 ->{
                        containerBmiColor.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,R.color.red500))
                        containerBmiScore.background = DrawableRounded.createRoundedRectangleDrawable(ContextCompat.getColor(this,R.color.red500Alpha))
                        textMsgInfoWeight.text = getString(R.string.obesity_weight_class_one)
                        textDescriptionBmi.text = getString(R.string.description_obesity_weight)
                    }
                    in 35..40 -> {
                        containerBmiColor.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,R.color.red500))
                        containerBmiScore.background = DrawableRounded.createRoundedRectangleDrawable(ContextCompat.getColor(this,R.color.red500Alpha))
                        textMsgInfoWeight.text = getString(R.string.obesity_weight_class_two)
                        textDescriptionBmi.text = getString(R.string.description_obesity_weight)
                    }
                    else -> {
                        containerBmiColor.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,R.color.red500))
                        containerBmiScore.background = DrawableRounded.createRoundedRectangleDrawable(ContextCompat.getColor(this,R.color.red500Alpha))
                        textMsgInfoWeight.text = getString(R.string.obesity_weight_class_three)
                        textDescriptionBmi.text = getString(R.string.description_obesity_weight)
                    }
                }
                initContainerLayout()
                containerBmiScore.text = result.toString()
                seekBarBmi.colorBarPosition = result
                textHealthyWeightRangeNumber.text = ((calculatorBmi.getMeter(unitHeight,height)*100) - 100).toString()
                if (result >= 99){
                    containerBmiScore.text = String.format("%s %s",">","99")
                }
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }
        btnInfoBmiApp.setOnClickListener {
            val builder = AlertDialog.Builder(this,R.style.MyDialogTheme)
            val webView = WebView(this)
            webView.settings.setSupportZoom(false)
            webView.settings.builtInZoomControls = false
            webView.settings.displayZoomControls = false
            webView.webViewClient = object : WebViewClient(){}
            webView.loadUrl("file:///android_asset/bmi/index.html")
            builder.setView(webView)
            builder.setTitle("درباره Bmi")
            builder.setNegativeButton(getString(R.string.confirmation)) { dialog, which -> dialog.dismiss() }
            builder.show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.refreshApp){
            edtHeight.text?.clear()
            edtWeight.text?.clear()
            containerBmiColor.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this,R.color.colorAccent))
            containerBmiScore.background = DrawableRounded.createRoundedRectangleDrawable(ContextCompat.getColor(this,R.color.orange800))
            containerBmiScore.text = getString(R.string.zero)
            seekBarBmi.colorBarPosition = 0
            textMsgInfoWeight.text = getString(R.string.healthy_weight)
            textDescriptionBmi.text = getString(R.string.description_healthy_weight)
            textHealthyWeightRangeNumber.text = ""
        }
        return super.onOptionsItemSelected(item)
    }
    private fun dialogUserName(){
        val dialog = Dialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_name_user, findViewById(R.id.dialogNameUser))
        val edtName = view.findViewById<EditText>(R.id.edtName)
        val edtFamily = view.findViewById<EditText>(R.id.edtFamily)
        val btnSetUser = view.findViewById<Button>(R.id.btnSetUser)
        btnSetUser.setOnClickListener {
            val editNameStr = edtName.text.toString().trim()
            val editFamilyStr = edtFamily.text.toString().trim()
            if (editNameStr.isEmpty() && editFamilyStr.isEmpty()){
                Toast.makeText(this, "چیزی وارد نشده!!", Toast.LENGTH_LONG).show()
            } else {
                textName.text = String.format("%s %s", getString(R.string.name), editNameStr)
                textFamily.text = String.format("%s %s", getString(R.string.family), editFamilyStr)
                dialog.dismiss()
            }
            saveApp.saveUsername(editNameStr)
            saveApp.saveFamilyName(editFamilyStr)
        }
        edtName.setText(saveApp.getUsernameDialog())
        edtFamily.setText(saveApp.getUserFamilyDialog())
        dialog.setContentView(view)
        dialog.setCancelable(false)
        dialog.show()
    }
    private fun initSpinner(){
        val arrayHeight = arrayOf(getString(R.string.centimeter),getString(R.string.meter))
        val arrayWeight = arrayOf(getString(R.string.kg),getString(R.string.lb))
        val adapterHeight = ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayHeight)
        val adapterWeight = ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayWeight)
        spinnerHeight.adapter = adapterHeight
        spinnerWeight.adapter = adapterWeight
        spinnerHeight.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0-> unitHeight = "cm"
                    1-> unitHeight = "m"
                }
                saveApp.saveHeightUnitSpinner(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        spinnerWeight.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0-> unitWeight = "kg"
                    1-> unitWeight = "lb"
                }
                saveApp.saveWeightUnitSpinner(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        spinnerHeight.setSelection(saveApp.getHeightUnitSpinner())
        spinnerWeight.setSelection(saveApp.getWeightUnitSpinner())
    }
    private fun initContainerLayout(){
        val moveTop = AnimationUtils.loadAnimation(this,R.anim.move_top)
        layoutContainerBmi.startAnimation(moveTop)
    }
    private fun initCalcAnimationClick(){
        val animCalc = AnimatedVectorDrawableCompat.create(this,R.drawable.calc_anim)
        btnCalcBMI.setImageDrawable(animCalc)
        animCalc!!.start()
        val ages = arrayListOf(20,25,90,70,69)

    }
}