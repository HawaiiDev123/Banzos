package com.mns.banzosapp.adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mns.banzosapp.R
import com.mns.banzosapp.activities.*
import com.mns.banzosapp.activities.activitiesAndTours.ActivitiesTourSelectionActivity
import com.mns.banzosapp.activities.adventure.AdventureActivity
import com.mns.banzosapp.activities.beach.BeachListActivity
import com.mns.banzosapp.activities.citiesTown.CitiesTownActivity
import com.mns.banzosapp.activities.combo.ComboMainActivity
import com.mns.banzosapp.activities.diningFood.DiningAndFoodActivity
import com.mns.banzosapp.activities.healthSafety.HealthSafetyActivity
import com.mns.banzosapp.activities.lodging.LodgingMainActivity
import com.mns.banzosapp.activities.otherThingsToDo.OtherThingsToDoActivity
import com.mns.banzosapp.activities.pointOfInterest.PointOfInterestActivity
import com.mns.banzosapp.activities.servicesAndRental.ServicesAndRentalMainActivity
import com.mns.banzosapp.activities.wildlife.WildlifeActivity
import com.mns.banzosapp.app_utils.AppConstants
import kotlinx.android.synthetic.main.row_home_category_list_item.view.*

class HomeCategoryListAdapter(
    private var context: Context, private var homeCategoryList:
    ArrayList<HomeCategoryActivity.HomeCategoryModel>, private var layoutManager: GridLayoutManager
) : RecyclerView.Adapter<HomeCategoryListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_home_category_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return homeCategoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.tv_categoryName.text = homeCategoryList[position].categoryName
        holder.itemView.iv_categoryImage.setImageResource(homeCategoryList[position].categoryImage!!)

        holder.itemView.rl_homeCategory.setOnClickListener {
            when (position) {
                0 -> {
                    val intent = Intent(context, IntroductionActivity::class.java)
                    intent.putExtra(AppConstants.INTENT_FROM_NAME, AppConstants.SCREEN_INTRODUCTION)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
                1 -> {
                    val intent = Intent(context, IntroductionActivity::class.java)
                    intent.putExtra(
                        AppConstants.INTENT_FROM_NAME,
                        AppConstants.SCREEN_GENERAL_INFORMATION
                    )
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
                2 -> {
                    val intent = Intent(context, CitiesTownActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
                3 -> {
                    val intent = Intent(context, BeachListActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
                4 -> {
                    val intent = Intent(context, AdventureActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
                5 -> {
                    val intent = Intent(context, ActivitiesTourSelectionActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
                6 -> {
                    val intent = Intent(context, ComboMainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
                //Deals And Discount
                7 -> {

                }
                8 -> {
                    val intent = Intent(context, LodgingMainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
                9 -> {
                    val intent = Intent(context, DiningAndFoodActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
                10 -> {
                    val intent = Intent(context, ServicesAndRentalMainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
                11 -> {
                    val intent = Intent(context, PointOfInterestActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
                12 -> {
                    val intent = Intent(context, OtherThingsToDoActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
                13 -> {
                    val intent = Intent(context, UpcomingEventsActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
                14 -> {
                    val intent = Intent(context, WildlifeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
                15 -> {
                    val intent = Intent(context, HealthSafetyActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
                16 -> {
                    val intent = Intent(context, CustomizedTravelActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
                //Merchandise
                17 -> {

                }
                18 -> {
                    val intent = Intent(context, MyHawaiiActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
            }
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}