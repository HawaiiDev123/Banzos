package com.mns.banzosapp.utils

import android.util.Log
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

class Validation {
    companion object {

        fun checkEmailValidation(et: EditText, til: TextInputLayout, errorMsg: String) {
            val eml = et.text.toString()
            //String emailPattern = "[a-zA-Z0-9._-]+@[a-z_-]+\\.+[a-z]{2,3}";
            val emailPattern =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            if (eml.isEmpty()) {
                til.error = errorMsg
                et.requestFocus()
            } else {
                if (eml.matches(emailPattern.toRegex())) {
                    til.error = null
                } else {
                    til.error = "Invalid Email"
                    et.requestFocus()
                }
            }
        }

        fun checkTextValidation(et: EditText, til: TextInputLayout, errorMsg: String) {
            val st = et.text.toString().trim { it <= ' ' }
            if (st.isEmpty()) {
                til.error = errorMsg
                et.requestFocus()
            } else {
                til.error = null
            }
        }

        fun checkPassword(
            /* context: Context, */etold: EditText, etNew: EditText, etconfirm: EditText, til_old: TextInputLayout,
                                   til_new: TextInputLayout, til_confirm: TextInputLayout
        ) {
            val oldPwd = etold.text.toString()
            val newPwd = etNew.text.toString()
            val confirmPwd = etconfirm.text.toString()
            /*  val pwdPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{6,}\$"*/
            Log.e(Global.TAG, "New pwd length::" + newPwd.length + " confirm pwd length:" + confirmPwd.length)
            if (oldPwd.isNotEmpty() && newPwd.isNotEmpty() && confirmPwd.isNotEmpty()) {
                if (etconfirm.text.toString() == etNew.text.toString()) {
                    if (newPwd.length >= 5 && confirmPwd.length >= 5) {
                        til_new.error = null
                        til_confirm.error = null
                    } else {
                        til_new.error = "Password must be minimum 5 characters."
                        etNew.requestFocus()
//                        Toast.makeText(context, "Password must be minimum 8 characters.password must contain at least 1 lowercase, 1 Uppercase, 1 numeric and 1 special character.", Toast.LENGTH_LONG).show()
                    }
                } else {
                    til_confirm.error = "New password and confirm password does not match!"
                    etconfirm.requestFocus()
//                    Global.showSnackBar(mActivity.coordinatorLayoutMain, "New password and confirm password does not match!", mActivity)

                }
            } else if (newPwd.isEmpty() && confirmPwd.isNotEmpty()) {
                til_new.error = "Please enter new password!"
                etNew.requestFocus()
//                Global.showSnackBar(mActivity.coordinatorLayoutMain, "Please enter new password!", mActivity)
                etconfirm.text.clear()
                etconfirm.error = null
            } else {

                if (oldPwd.isEmpty() && newPwd.isEmpty() && confirmPwd.isEmpty()) {
//                    Global.showSnackBar(mActivity.coordinatorLayoutMain, "Above fields are required!", mActivity)
                    etold.requestFocus()
                    til_old.error = "Please enter old password."
                    til_new.error = "Please enter new password."
                    til_confirm.error = "Please enter confirm password."
                } else {
                    when {
                        newPwd.isEmpty() -> {
                            //                        Global.showSnackBar(mActivity.coordinatorLayoutMain, "Please enter new password!", mActivity)
                            til_new.error = "Please enter new password."
                            etNew.requestFocus()
                        }
                        confirmPwd.isEmpty() -> {
                            //                       Global.showSnackBar(mActivity.coordinatorLayoutMain, "Please Confirm your password!", mActivity)
                            til_confirm.error = "Please enter confirm password."
                            etconfirm.requestFocus()
                        }
                        oldPwd.isEmpty() -> {
                            //                        Global.showSnackBar(mActivity.coordinatorLayoutMain, "Please enter current password!", mActivity)
                            etold.requestFocus()
                            til_old.error = "Please enter old password."
                        }
                        else -> Log.e(Global.TAG, "Validation Else!!")
                    }
                }
            }
        }
    }
}