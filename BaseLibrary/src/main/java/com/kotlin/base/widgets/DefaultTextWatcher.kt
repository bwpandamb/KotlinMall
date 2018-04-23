package com.kotlin.base.widgets

import android.text.Editable
import android.text.TextWatcher

/*
    默认TextWatcher，空实现(因为一般只是使用onTextChanged，所以这样使用空实现，在实现的时候就可以少很多无用代码)
 */
open class DefaultTextWatcher:TextWatcher{
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}
