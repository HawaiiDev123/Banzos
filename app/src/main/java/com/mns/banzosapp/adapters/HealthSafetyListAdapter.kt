package com.mns.banzosapp.adapters

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import kotlinx.android.synthetic.main.row_health_and_safety_list_item.view.*

class HealthSafetyListAdapter(private var context: Context, private var comeFrom: String) :
    RecyclerView.Adapter<HealthSafetyListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_health_and_safety_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private fun openAddToTripPlannerDialog() {
        val addToTripPlannerDialog = Dialog(context)
        addToTripPlannerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        addToTripPlannerDialog.setCancelable(true)
        addToTripPlannerDialog.setCanceledOnTouchOutside(true)
        addToTripPlannerDialog.setContentView(R.layout.dialog_share_profile_popup)
        addToTripPlannerDialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        addToTripPlannerDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        addToTripPlannerDialog.show()
    }
}