/**
 * Copyright (c) 2019 Cotta & Cush Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cottacush.android.androidbaseprojectkt.extensions

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AttrRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import com.cottacush.android.androidbaseprojectkt.R

fun Activity.disableTouch() {
    window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun Activity.enableTouch() {
    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun Activity.hideKeyBoard() {
    val view = currentFocus
    if (view != null) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Activity.viewUrl(url: String) {
    val customTabsIntent = CustomTabsIntent.Builder()
        .setToolbarColor(resolveColor(R.attr.colorPrimary))
        .build()
    try {
        customTabsIntent.launchUrl(this, url.toUri())
    } catch (_: ActivityNotFoundException) {
        val chooser = Intent.createChooser(
            Intent(Intent.ACTION_VIEW)
                .setData(url.toUri()), "View URL"
        )
        startActivity(chooser)
    }
}

private fun Activity.resolveColor(@AttrRes attr: Int): Int {
    val a = theme.obtainStyledAttributes(intArrayOf(attr))
    try {
        return a.getColor(0, 0)
    } finally {
        a.recycle()
    }
}
