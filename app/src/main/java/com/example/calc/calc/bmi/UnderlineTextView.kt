package com.example.calc.calc.bmi

import android.content.Context
import android.os.Build
import android.text.Html
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class UnderlineTextView : AppCompatTextView {
    constructor(context: Context?) : this(context!!,null) {
        initUnderline()
    }
    constructor(context: Context?, attrs: AttributeSet?) : this(context!!, attrs,R.attr.enableUnderline) {
        initUnderline()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr) {
        initUnderline()
    }
    private fun initUnderline() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            this.text = Html.fromHtml("<u>${this.text}</u>",Html.FROM_HTML_MODE_COMPACT)
        } else {
            @Suppress("DEPRECATION")
            this.text = Html.fromHtml("<u>${this.text}</u>")
        }
    }
}