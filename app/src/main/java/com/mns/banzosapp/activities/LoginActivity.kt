package com.mns.banzosapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.android.volley.VolleyError
import com.mns.banzosapp.R
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.helper.http.FetchItem
import com.mns.banzosapp.model.UserDetails
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppBaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initializeAllView()

    }

    override fun initializeAllView() {
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {
        clickListeners()
    }

    override fun clickListeners() {
        tv_signUp.setOnClickListener(this)
        btn_login.setOnClickListener(this)
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_login -> {
                processToCheckLogin()
            }
            R.id.tv_signUp -> {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun processToCheckLogin() {

        showProgressDialog()
        val param = getParam()
        param["email"] = et_username.text.toString()
        param["password"] = et_password.text.toString()

        FetchItem(object : FetchItem.ListCommunicatorInterface<UserDetails> {
            override fun onError(error: VolleyError) {
                showErrorMessage(error)
            }

            override fun onSuccess(userDetails: UserDetails) {
                dismissProgressDialog()
                processToSetLoginDetails(userDetails)
            }

            override fun onFailed(message: String) {
                dismissProgressDialog()
                showMessage(message)
            }
        }).fetchItem(
            URLHelper.LOGIN,
            UserDetails::class.java,
            param,
            localClassName
        )

    }

    private fun processToSetLoginDetails(userDetails: UserDetails) {

        prefs.setIsLogin(1)
        prefs.setUserId(userDetails.u_id)
        prefs.setUserToken(userDetails.token)
        prefs.setName(userDetails.name)
        prefs.setEmail(userDetails.email)

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
