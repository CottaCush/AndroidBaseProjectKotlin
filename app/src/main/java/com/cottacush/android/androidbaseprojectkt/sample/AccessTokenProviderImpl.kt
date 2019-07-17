package com.cottacush.android.androidbaseprojectkt.sample

import com.cottacush.android.androidbaseprojectkt.PrefsUtils
import com.cottacush.android.androidbaseprojectkt.auth.AccessTokenProvider

class AccessTokenProviderImpl (val exampleAPIAuthService: ExampleAPIAuthService, val prefsUtils: PrefsUtils) :
    AccessTokenProvider {

    override fun token(): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshToken(): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}