package com.mns.banzosapp.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.HomeCategoryListAdapter
import kotlinx.android.synthetic.main.activity_home_category_trial.*

class HomeCategoryActivity : AppCompatActivity(), View.OnClickListener {
    var homeCategoryList: ArrayList<HomeCategoryModel>? = null
    private var homeCategoryModel: HomeCategoryModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_category_trial)

        init()
    }

    private fun init() {
        homeCategoryList = ArrayList()
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "Introduction"
        homeCategoryModel?.categoryImage = R.drawable.ic_introduction
        homeCategoryList?.add(homeCategoryModel!!)
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "General Information"
        homeCategoryModel?.categoryImage = R.drawable.ic_gen_information
        homeCategoryList?.add(homeCategoryModel!!)
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "Cities & Towns"
        homeCategoryModel?.categoryImage = R.drawable.ic_cities_towns
        homeCategoryList?.add(homeCategoryModel!!)
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "Beaches"
        homeCategoryModel?.categoryImage = R.drawable.ic_beaches
        homeCategoryList?.add(homeCategoryModel!!)
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "Adventures"
        homeCategoryModel?.categoryImage = R.drawable.ic_adventures
        homeCategoryList?.add(homeCategoryModel!!)
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "Activities & Tours"
        homeCategoryModel?.categoryImage = R.drawable.ic_activities_tours
        homeCategoryList?.add(homeCategoryModel!!)
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "Combo Trips"
        homeCategoryModel?.categoryImage = R.drawable.ic_combo_trips
        homeCategoryList?.add(homeCategoryModel!!)
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "Discount & Deals"
        homeCategoryModel?.categoryImage = R.drawable.ic_discount_deals
        homeCategoryList?.add(homeCategoryModel!!)
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "Resorts, Hotels, BBQ & Vacation Homes"
        homeCategoryModel?.categoryImage = R.drawable.ic_resorts_hotels
        homeCategoryList?.add(homeCategoryModel!!)
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "Dining & Food"
        homeCategoryModel?.categoryImage = R.drawable.ic_dining_foods
        homeCategoryList?.add(homeCategoryModel!!)
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "Services & Rental"
        homeCategoryModel?.categoryImage = R.drawable.ic_services_rental
        homeCategoryList?.add(homeCategoryModel!!)
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "Points Of Interest"
        homeCategoryModel?.categoryImage = R.drawable.ic_point_of_interest
        homeCategoryList?.add(homeCategoryModel!!)
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "Other Things To Do"
        homeCategoryModel?.categoryImage = R.drawable.ic_other_things_to_do
        homeCategoryList?.add(homeCategoryModel!!)
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "Upcoming Events"
        homeCategoryModel?.categoryImage = R.drawable.ic_upcoming_events
        homeCategoryList?.add(homeCategoryModel!!)
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "Wildlife"
        homeCategoryModel?.categoryImage = R.drawable.ic_wildlife
        homeCategoryList?.add(homeCategoryModel!!)
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "Health & Safety"
        homeCategoryModel?.categoryImage = R.drawable.ic_health_safety
        homeCategoryList?.add(homeCategoryModel!!)
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "Customized Travel"
        homeCategoryModel?.categoryImage = R.drawable.ic_customized_travel
        homeCategoryList?.add(homeCategoryModel!!)
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "Merchandise"
        homeCategoryModel?.categoryImage = R.drawable.ic_merchandise
        homeCategoryList?.add(homeCategoryModel!!)
        homeCategoryModel = HomeCategoryModel()
        homeCategoryModel?.categoryName = "My Hawaii"
        homeCategoryModel?.categoryImage = R.drawable.ic_my_hawaii
        homeCategoryList?.add(homeCategoryModel!!)
        /*val layoutManager = GridLayoutManager(this, 2,
            GridLayoutManager.VERTICAL, false)
        rv_homeCategory.layoutManager = layoutManager*/
        rv_homeCategory.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                /*when (position) {
                    0, 2, 4, 6, 8, 10 -> return 2
                    1, 3, 5, 7, 9, 11 -> return 1
                    else -> return 2
                }*/
                return if (position % 3 == 0) 2 else 1
            }
        }
        rv_homeCategory.layoutManager = layoutManager
        rv_homeCategory.adapter = HomeCategoryListAdapter(this, homeCategoryList!!)
        /* ll_information.setOnClickListener(this)
         ll_generalInfo.setOnClickListener(this)
         ll_citiesTowns.setOnClickListener(this)
         ll_beaches.setOnClickListener(this)
         ll_adventure.setOnClickListener(this)
         ll_activities.setOnClickListener(this)
         ll_combo.setOnClickListener(this)
         ll_diningFood.setOnClickListener(this)
         ll_pointOfInterest.setOnClickListener(this)
         ll_wildlife.setOnClickListener(this)
         ll_lodging.setOnClickListener(this)
         ll_otherThings.setOnClickListener(this)
         ll_myHawaii.setOnClickListener(this)
         ll_favTrips.setOnClickListener(this)
         ll_valueServices.setOnClickListener(this)
         ll_healthSafety.setOnClickListener(this)
         ll_upcomingEvents.setOnClickListener(this)
         ll_recentlyViewed.setOnClickListener(this)
         ll_customisedTravel.setOnClickListener(this)
         ll_vacationPackage.setOnClickListener(this)*/
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            /*R.id.ll_information -> {
                val intent = Intent(this, IntroductionActivity::class.java)
                intent.putExtra(AppConstants.INTENT_FROM_NAME, AppConstants.SCREEN_INTRODUCTION)
                startActivity(intent)
            }
            R.id.ll_generalInfo -> {
                val intent = Intent(this, IntroductionActivity::class.java)
                intent.putExtra(
                    AppConstants.INTENT_FROM_NAME,
                    AppConstants.SCREEN_GENERAL_INFORMATION
                )
                startActivity(intent)
            }
            R.id.ll_citiesTowns -> {
                val intent = Intent(this, CitiesTownActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_beaches -> {
                val intent = Intent(this, BeachListActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_adventure -> {
                val intent = Intent(this, AdventureActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_activities -> {
                val intent = Intent(this, ActivitiesTourSelectionActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_combo -> {
                val intent = Intent(this, ComboMainActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_diningFood -> {
                val intent = Intent(this, DiningAndFoodActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_pointOfInterest -> {
                val intent = Intent(this, PointOfInterestActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_wildlife -> {
                val intent = Intent(this, WildlifeActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_lodging -> {
                val intent = Intent(this, LodgingMainActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_otherThings -> {
                val intent = Intent(this, OtherThingsToDoActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_myHawaii -> {
                val intent = Intent(this, MyHawaiiActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_favTrips -> {
                val intent = Intent(this, FavoriteTripsActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_valueServices -> {
                val intent = Intent(this, ValueServicesActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_healthSafety -> {
                val intent = Intent(this, HealthSafetyActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_upcomingEvents -> {
                val intent = Intent(this, UpcomingEventsActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_recentlyViewed -> {
                val intent = Intent(this, RecentlyViewedActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_customisedTravel -> {
                val intent = Intent(this, CustomizedTravelActivity::class.java)
                startActivity(intent)
            }
            R.id.ll_vacationPackage -> {
                val intent = Intent(this, VacationPackageActivity::class.java)
                startActivity(intent)
            }*/
        }
    }

    class HomeCategoryModel {
        var categoryName: String? = ""
        var categoryImage: Int? = null
    }
}
