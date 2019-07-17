package com.cottacush.android.androidbaseprojectkt

class AccessTokenDataSource(private val prefsUtils: PrefsUtils) {

    private var _authToken: String? = prefsUtils.getString(PrefKeys.ACCESS_TOKEN, null)

    var authToken: String? = _authToken
        set(value) {
            prefsUtils.putString(PrefKeys.ACCESS_TOKEN, value)
            field = value
        }

    fun clearData() {
        authToken = null
    }

}