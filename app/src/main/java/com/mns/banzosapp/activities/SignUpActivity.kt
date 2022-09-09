package com.mns.banzosapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.VolleyError
import com.mns.banzosapp.R
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.helper.base.AppBaseActivity
import com.mns.banzosapp.helper.http.FetchItem
import com.mns.banzosapp.model.UserDetails
import com.mns.banzosapp.model.base.ResponseData
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.et_username

class SignUpActivity : AppBaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initializeAllView()
    }
    override fun initializeAllView() {

        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {
        clickListeners()
    }

    override fun clickListeners() {

        tv_login.setOnClickListener(this)
        buttonSignUp.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_login -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.buttonSignUp -> {
                processToSignUp()
            }
        }
    }
    private fun processToSignUp() {

        val param = getParam()
        param["f_name"] = getEditTextData(et_firstName)
        param["l_name"] = getEditTextData(et_lastName)
        param["name"] = getEditTextData(et_username)
        param["email"] = getEditTextData(et_email)
        param["password"] = getEditTextData(et_signup_password)
        param["phone"] = getEditTextData(et_phone)

        FetchItem(object : FetchItem.ListCommunicatorInterface<ResponseData> {
            override fun onError(error: VolleyError) {
                showErrorMessage(error)
            }

            override fun onSuccess(fetchedDetails: ResponseData) {
                dismissProgressDialog()
                showMessage(fetchedDetails.message)

                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                startActivity(intent)
            }

            override fun onFailed(message: String) {
                dismissProgressDialog()
                showMessage(message)
            }
        }).fetchItem(
            URLHelper.REGISTRATION,
            ResponseData::class.java,
            param,
            localClassName
        )

    }
}
