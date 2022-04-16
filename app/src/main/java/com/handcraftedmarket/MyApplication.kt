package com.handcraftedmarket

import android.app.Application
import io.branch.referral.Branch

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Branch.enableTestMode()
        Branch.getAutoInstance(this)

    }
}