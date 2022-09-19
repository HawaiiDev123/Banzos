package com.mns.banzosapp.common_util

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import com.mns.banzosapp.common_util.LogUtil.debug2
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun getActivityTabs(): List<ActivityTabs> {
        val list: MutableList<ActivityTabs> = ArrayList()
        list.add(ActivityTabs("all", "All Trips"))
        list.add(ActivityTabs("top rated", "Top Rated"))
        list.add(ActivityTabs("private trips", "Private Trips"))
        list.add(ActivityTabs("show cased", "Showcased"))
        return list
    }

    data class ActivityTabs(
        var key: String,
        var value: String
    )

    fun notNull(text: String?): String {
        return text ?: ""
    }

    fun dateChangeFormat(date: String, oldPattern: String, newPattern: String): String {
        return try {
            val d: Date = SimpleDateFormat(oldPattern, Locale.US).parse(date)!!
            SimpleDateFormat(newPattern, Locale.US).format(d)
        } catch (e: Exception) {
            e.printStackTrace()
            debug2(e.localizedMessage)
            ""
        }
    }

    fun stringToDate(date: String, patternOfStringDate: String): Date? {
        return try {
            SimpleDateFormat(patternOfStringDate, Locale.getDefault()).parse(date)
        } catch (e: Exception) {
            e.printStackTrace()
            debug2(e.localizedMessage)
            null
        }
    }

    fun isTimeGreater(start_time: String, end_time: String, format: String): Boolean {
        val start_timeD: Date
        val end_timeD: Date
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        try {
            start_timeD = sdf.parse(start_time)!!
            end_timeD = sdf.parse(end_time)!!
            return start_timeD.compareTo(end_timeD) <= 0
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return true
    }

    fun checkIfDateIsEqual(firstDate: String, secondDate: String, format: String): Boolean {
        val dfDate = SimpleDateFormat(format, Locale.getDefault())
        var b = false
        try {
            if (dfDate.parse(firstDate).equals(dfDate.parse(secondDate))) {
                b = true // If two dates are equal.
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return b
    }

    fun dateChangeFormat(date: Date, newPattern: String): String {
        return try {
            SimpleDateFormat(newPattern, Locale.getDefault()).format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            debug2(e.localizedMessage)
            ""
        }
    }

    fun preparedLog(error: String?): String? {
        return if (error != null && error.length > 2000) {
            error.substring(0, 2000)
        } else error ?: ""
    }

    fun fromHtml(html: String?): Spanned? {
        return when {
            html == null -> {
                SpannableString("")
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
            }
            else -> {
                Html.fromHtml(html)
            }
        }
    }
    /*fun getMonthName(calander: Calendar): String? {
        val month_date = SimpleDateFormat("MMMM", Locale.getDefault())
        return month_date.format(calander.getTime())
    }

    fun getTimeStampFromString(time: String, pattern: String): Long {
        var time = time
        var pattern = pattern
        val todayDate: String =
            SimpleDateFormat(AppConstants.DisplayFormat, Locale.getDefault()).format(Date())
        time = "$todayDate $time"
        pattern = AppConstant.DisplayFormat.toString() + " " + pattern
        return try {
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            val currentTimeZone: Date = sdf.parse(time)
            val calendar: Calendar = Calendar.getInstance()
            calendar.setTime(currentTimeZone)
            calendar.getTimeInMillis() / 1000
        } catch (e: Exception) {
            e.printStackTrace()
            debug2(e.localizedMessage)
            0
        }
    }

    fun getTimeStampFromStringWithGivenDate(time: String?, pattern: String?): Long {
        return try {
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            val currentTimeZone: Date = sdf.parse(time)
            val calendar: Calendar = Calendar.getInstance()
            calendar.setTime(currentTimeZone)
            calendar.getTimeInMillis() / 1000
        } catch (e: Exception) {
            e.printStackTrace()
            debug2(e.localizedMessage)
            0
        }
    }

    fun getStringFromTimeStamp(timeStamp: String, patternYouWantInReturn: String?): String? {
        var timeStampLong: Long = 0
        try {
            timeStampLong = timeStamp.toLong()
        } catch (e: NumberFormatException) {
            debug2(
                """${e.localizedMessage}
 Please Enter Long Timestamp"""
            )
        }
        return try {
            val calendar: Calendar = Calendar.getInstance()
            calendar.setTimeInMillis(timeStampLong * 1000)
            val sdf = SimpleDateFormat(patternYouWantInReturn, Locale.getDefault())
            val currentTimeZone: Date = calendar.getTime()
            sdf.format(currentTimeZone)
        } catch (e: Exception) {
            debug2(e.localizedMessage)
            ""
        }
    }

    fun getCountOfDays(date: String?, format: String?): Int {
        return try {
            val d: Date = SimpleDateFormat(format, Locale.getDefault()).parse(date)
            val mycal: Calendar = GregorianCalendar(
                SimpleDateFormat("yyyy", Locale.getDefault()).format(d).toInt(),
                SimpleDateFormat("MM", Locale.getDefault()).format(d).toInt() - 1,
                SimpleDateFormat("dd", Locale.getDefault()).format(d).toInt()
            )
            mycal.getActualMaximum(Calendar.DAY_OF_MONTH)
        } catch (e: Exception) {
            e.printStackTrace()
            debug2(e.localizedMessage)
            0
        }
    }

    fun isTimeLesser(start_time: String?, end_time: String?, format: String?): Boolean {
        val start_timeD: Date
        val end_timeD: Date
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        try {
            start_timeD = sdf.parse(start_time)
            end_timeD = sdf.parse(end_time)
            return start_timeD.compareTo(end_timeD) >= 0
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return true
    }

    fun isTimeGreaterButNotEqual(start_time: String?, end_time: String?, format: String?): Boolean {
        val start_timeD: Date
        val end_timeD: Date
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        try {
            start_timeD = sdf.parse(start_time)
            end_timeD = sdf.parse(end_time)
            return start_timeD.compareTo(end_timeD) < 0
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return true
    }

    fun isTimeBetween(
        checkTime: String?,
        startTime: String?,
        endTime: String?,
        format: String?
    ): Boolean {
        val startTimeD: Date
        val endTimeD: Date
        val checkTimeD: Date
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        try {
            startTimeD = sdf.parse(startTime)
            endTimeD = sdf.parse(endTime)
            checkTimeD = sdf.parse(checkTime)
            if (checkTimeD.after(startTimeD) && checkTimeD.before(endTimeD)) {
                return true
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return false
    }

    fun isTimeBetweenAndEqual(
        checkTime: String?,
        startTime: String?,
        endTime: String?,
        format: String?
    ): Boolean {
        val startTimeD: Date
        val endTimeD: Date
        val checkTimeD: Date
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        try {
            startTimeD = sdf.parse(startTime)
            endTimeD = sdf.parse(endTime)
            checkTimeD = sdf.parse(checkTime)
            if (checkTimeD.after(startTimeD) && checkTimeD.before(endTimeD) || checkTimeD.equals(
                    startTimeD
                ) || checkTimeD.equals(endTimeD)
            ) {
                return true
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return false
    }

    fun hashData(editText: EditText): Boolean {
        return editText.text.toString().trim { it <= ' ' }.length > 0
    }

    fun hashData(text: String?): Boolean {
        return text != null && text != ""
    }

    fun hasData(vararg datas: String?): Boolean {
        var hasData = true
        for (data in datas) {
            if (!hasData(data)) {
                hasData = false
                break
            }
        }
        return hasData
    }

    fun <T> checkIsExist(objects: List<T>, `object`: Any): Int {
        var positionAt = -1
        var i = 0
        for (objectCheck in objects) {
            if (objectCheck == `object`) {
                positionAt = i
                break
            }
            i++
        }
        return positionAt
    }

    fun getCropImage(): ActivityBuilder? {
        return CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).setAspectRatio(5, 3)
    }

    fun getRequestOption(): RequestOptions? {
        return RequestOptions().transform(CenterCrop(), RoundedCorners(10))
    }

    fun makeTextViewResizable(tv: TextView, maxLine: Int, expandText: String, viewMore: Boolean) {
        if (tv.tag == null) {
            tv.tag = tv.text
        }
        val vto = tv.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val obs = tv.viewTreeObserver
                obs.removeGlobalOnLayoutListener(this)
                if (maxLine == 0) {
                    val lineEndIndex = tv.layout.getLineEnd(0)
                    val text = tv.text.subSequence(0, lineEndIndex - expandText.length + 1)
                        .toString() + " " + expandText
                    tv.text = text
                    tv.movementMethod = LinkMovementMethod.getInstance()
                    tv.setText(
                        addClickablePartTextViewResizable(
                            Html.fromHtml(tv.text.toString()), tv, maxLine, expandText,
                            viewMore
                        ), TextView.BufferType.SPANNABLE
                    )
                } else if (maxLine > 0 && tv.lineCount >= maxLine) {
                    val lineEndIndex = tv.layout.getLineEnd(maxLine - 1)
                    val text = tv.text.subSequence(0, lineEndIndex - expandText.length + 1)
                        .toString() + " " + expandText
                    tv.text = text
                    tv.movementMethod = LinkMovementMethod.getInstance()
                    tv.setText(
                        addClickablePartTextViewResizable(
                            Html.fromHtml(tv.text.toString()), tv, maxLine, expandText,
                            viewMore
                        ), TextView.BufferType.SPANNABLE
                    )
                } else {
                    val lineEndIndex = tv.layout.getLineEnd(tv.layout.lineCount - 1)
                    val text = tv.text.subSequence(0, lineEndIndex).toString() + " " + expandText
                    tv.text = text
                    tv.movementMethod = LinkMovementMethod.getInstance()
                    tv.setText(
                        addClickablePartTextViewResizable(
                            Html.fromHtml(tv.text.toString()), tv, lineEndIndex, expandText,
                            viewMore
                        ), TextView.BufferType.SPANNABLE
                    )
                }
            }
        })
    }


    fun clearNotification(context: Context, order_id: String) {
        val it = Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
        context.sendBroadcast(it)
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(order_id.toInt())
    }

    private fun addClickablePartTextViewResizable(
        strSpanned: Spanned, tv: TextView,
        maxLine: Int, spannableText: String, viewMore: Boolean
    ): SpannableStringBuilder? {
        val str = strSpanned.toString()
        val ssb = SpannableStringBuilder(strSpanned)
        if (str.contains(spannableText)) {
            ssb.setSpan(object : MySpannable(false) {
                fun onClick(widget: View?) {
                    if (viewMore) {
                        tv.layoutParams = tv.layoutParams
                        tv.setText(tv.tag.toString(), TextView.BufferType.SPANNABLE)
                        tv.invalidate()
                        makeTextViewResizable(tv, -1, "Show Less", false)
                    } else {
                        tv.layoutParams = tv.layoutParams
                        tv.setText(tv.tag.toString(), TextView.BufferType.SPANNABLE)
                        tv.invalidate()
                        makeTextViewResizable(tv, 3, ".. Show More", true)
                    }
                }
            }, str.indexOf(spannableText), str.indexOf(spannableText) + spannableText.length, 0)
        }
        return ssb
    }

    fun getMinutesFromTime(largeTime: String?, smallTime: String?): Int {
        return try {
            val simpleDateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
            val difference: Long =
                simpleDateFormat.parse(largeTime).getTime() - simpleDateFormat.parse(smallTime)
                    .getTime()
            (difference / (1000 * 60)).toInt()
        } catch (e: Exception) {
            debug2(e.localizedMessage)
            0
        }
    }

    fun getNotificationType(type: String?): Int {
        return when (type) {
            AppConstant.KEY_TYPE_NEW_ORDER, AppConstant.KEY_TYPE_ORDER_RESCHEDULE -> 1
            AppConstant.KEY_TYPE_NEW_RATING, AppConstant.KEY_TYPE_RESCHEDULE_ACCEPT, AppConstant.KEY_TYPE_RESCHEDULE_REJECT, AppConstant.KEY_TYPE_GENERAL, AppConstant.KEY_TYPE_NEW_OFFER, AppConstant.KEY_TYPE_INSUFFICIENT_BALANCE -> 2
            else -> 0
        }
    }

    fun findLastBookingDateInMillis(advanceBookingDuration: Int): Long {
        val todayDate = Date()
        val calendar: Calendar = Calendar.getInstance()
        calendar.setTime(todayDate)
        calendar.add(Calendar.DATE, advanceBookingDuration)
        return calendar.getTimeInMillis()
    }*/
}