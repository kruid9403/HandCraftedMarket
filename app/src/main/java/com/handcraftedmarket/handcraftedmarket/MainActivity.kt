package com.handcraftedmarket.handcraftedmarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.RuntimeException
import com.handcraftedmarket.handcraftedmarket.R
import io.branch.referral.Branch
import io.branch.referral.BranchError
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

//    private lateinit var navController: NavController
//    private lateinit var bottomNav: BottomNavigationView

    // latest

    object branchListener : Branch.BranchReferralInitListener {
        override fun onInitFinished(referringParams: JSONObject?, error: BranchError?) {
            if (error == null) {
                Log.e("BRANCH SDK", referringParams.toString())
                Log.e("MainAct", "${referringParams?.get("Key1")} ;alkdjf")
                // Retrieve deeplink keys from 'referringParams' and evaluate the values to determine where to route the user
                // Check '+clicked_branch_link' before deciding whether to use your Branch routing logic
            } else {
                Log.e("BRANCH SDK", error.message)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        initViews()
//
//        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
//
//        bottomNav.setupWithNavController(navController)
    }


    override fun onStart() {
        super.onStart()
        // Branch init
        Branch.sessionBuilder(this).withCallback(branchListener).withData(this.intent.data).init()
        val sessionParams = Branch.getInstance().latestReferringParams

        // first
        val installParams = Branch.getInstance().firstReferringParams

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        this.intent = intent

        // if activity is in foreground (or in backstack but partially visible) launch the same
        // activity will skip onStart, handle this case with reInit
        if (intent != null &&
            intent.hasExtra("branch_force_new_session") &&
            intent.getBooleanExtra("branch_force_new_session", false)
        ) {
            Branch.sessionBuilder(this).withCallback(branchListener).reInit()

        }
    }
}