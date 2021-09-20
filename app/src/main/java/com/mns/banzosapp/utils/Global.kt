package com.mns.banzosapp.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.database.Cursor
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.text.format.DateFormat
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ListView
import com.mns.therkc.utils.ComplexPreferences
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

class Global {

    companion object {

        var TAG: String? = "Banzo App"
        var IMAGEBASEURL: String? = "imagebaseurl";
        /*fun showProgressBar(context: Context, progressDialog: Dialog?) {
            *//*val pd = Dialog(context, android.R.style.Theme_Black)*//*
            val view = LayoutInflater.from(context).inflate(R.layout.my_progress, null)
            progressDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            progressDialog?.window?.setBackgroundDrawableResource(R.color.transparent)
            progressDialog?.setContentView(view)
            progressDialog?.show()
        }*/

        fun hideProgressBar(context: Context, pd: Dialog) {
            pd.dismiss()
        }

        fun getNumberOfColumns(context: Context, layout: Int): Int {
            val view = View.inflate(context, layout, null)
            view.measure(
                View.MeasureSpec.UNSPECIFIED,
                View.MeasureSpec.UNSPECIFIED
            )
            val width = view.measuredWidth
            var count: Int = context.resources.displayMetrics.widthPixels / width
            val remaining: Int = context.resources.displayMetrics.widthPixels - width * count
            if (remaining > width - 15) count++
            return count
        }

        fun hideKeyboard(activity: Activity) {
            activity.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
            val view = activity.currentFocus
            if (view != null) {
                val imm =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        fun checkInternetConnection(context: Context): Boolean {
            val connMgr =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetworkInfo = connMgr.activeNetworkInfo

            if (activeNetworkInfo != null) { // connected to the internet
                Log.e(TAG, "connection type====" + activeNetworkInfo.typeName)

                if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                    return true
                } else if (activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                    // connected to the mobile provider's data plan
                    return true
                }
            }
            return false
        }

        fun isSDCARDPResent(): Boolean {
            return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        }

        fun skipTag(txt: String): String? {
            var txt = txt
            txt = txt.trim { it <= ' ' }
            if (txt.startsWith("<p>")) {
                txt = txt.substring(3)
                if (txt.endsWith("</p>")) {
                    txt = txt.substring(0, txt.length - 4)
                }
            }
            return txt
        }

        fun escapeExtraChar(str: String): String? {
            return str.replace("'", "&#39;")
                .replace("\\", "\\\\")
                .replace("\r\n", "")
        }

        fun roundUpFunction(value: Double?, places: Int): Double {
            if (places < 0) throw IllegalArgumentException()

            var bd = BigDecimal(value!!)
            bd = bd.setScale(places, RoundingMode.HALF_UP)
            return bd.toDouble()
        }

        fun getCurrentDate(format: String?): String {
            val cal = Calendar.getInstance()
            val sdf = SimpleDateFormat(format, Locale.ENGLISH)
            return sdf.format(cal.time)
        }

        fun getRealPathFromURI(context: Context, contentUri: Uri): String {
            var cursor: Cursor? = null
            return try {
                val proj = arrayOf(MediaStore.Images.Media.DATA)
                cursor = context.contentResolver.query(contentUri, proj, null, null, null)
                val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                cursor.moveToFirst()
                cursor.getString(columnIndex)
            } catch (e: Exception) {
                Log.e(TAG, "getRealPathFromURI Exception : $e")
                ""
            } finally {
                cursor?.close()
            }
        }

        fun convertStringToCalendar(formate: String, dateStr: String): Calendar {
            val df = SimpleDateFormat(formate, Locale.ENGLISH)
            val cal = Calendar.getInstance()
            cal.time = df.parse(dateStr)
            return cal
        }

        fun convertStringToCal(
            date: String?,
            pattern: String?
        ): Calendar? {
            val sdf = SimpleDateFormat(pattern)
            var dt = Calendar.getInstance()
            try {
                dt.time = sdf.parse(date)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                dt = null
            }
            return dt
        }

        fun getFirstDayOfCurrentMonth(formatDate: String): String {
            val c = Calendar.getInstance()   // this takes current date
            c.set(Calendar.DAY_OF_MONTH, 1)
            println(c.time)       // this returns java.util.Date

            return DateFormat.format(formatDate, c.time).toString()

            /* var dt = Date()
             try {
                 val sdf = SimpleDateFormat(formatDate, Locale.ENGLISH)
                 dt = sdf.parse(c.time)
             } catch (e: Exception) {
                 e.printStackTrace()
             }
             return DateFormat.format(formatDate, dt).toString()*/
        }

        fun getFirstDayOfSelectedMonth(calendar: Calendar, formatDate: String): String {

            calendar.set(Calendar.DAY_OF_MONTH, 1)
            println(calendar.time)       // this returns java.util.Date

            return DateFormat.format(formatDate, calendar.time).toString()
        }

        fun convertDateToFormat(
            date: String?,
            format_date: String?,
            format_return: String?
        ): String? {

            var dt = Date()
            try {
                val sdf = SimpleDateFormat(format_date, Locale.ENGLISH)
                dt = sdf.parse(date)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return DateFormat.format(format_return, dt).toString()
        }

        fun checkAndroidId(context: Context): String? {
            var uniqueId: String? = null
            val androidId =
                Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            if (!androidId.isNullOrEmpty()) {
                uniqueId = androidId
            } else {
                if (androidId.isNullOrEmpty()) {
                    uniqueId = Build.SERIAL

                    if (Build.SERIAL.isNullOrEmpty()) {
                        uniqueId = Build.MODEL.plus(Build.PRODUCT)
                    }
                }
            }
            Log.e(TAG, "unique id::$uniqueId")
            val complexPreferences = ComplexPreferences(context)
            complexPreferences.setPref("device_id", uniqueId)
            return uniqueId
        }

        fun makeListViewHeight(lv: ListView) {
            val listAdapter = lv.adapter
            var totalHeight = 0
            for (i in 0 until listAdapter.count) {
                val listItem = listAdapter.getView(i, null, lv)
                listItem.measure(0, 0)
                totalHeight += listItem.measuredHeight
                Log.e("Mheihg", listItem.measuredHeight.toString())
            }

            Log.e("height", totalHeight.toString())

            val params = lv.layoutParams
            params.height = totalHeight + lv.dividerHeight * (listAdapter.count - 1)
            lv.layoutParams = params
            lv.requestLayout()

        }

    }
}