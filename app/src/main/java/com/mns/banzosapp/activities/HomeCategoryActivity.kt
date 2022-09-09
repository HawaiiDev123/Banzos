package com.mns.banzosapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.GridLayoutManager
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.HomeCategoryListAdapter
import com.mns.banzosapp.app_utils.AppConstants
import com.mns.banzosapp.helper.base.AppBaseActivity
import kotlinx.android.synthetic.main.activity_home_category_trial.*

class HomeCategoryActivity : AppBaseActivity(), View.OnClickListener {
    var homeCategoryList: ArrayList<HomeCategoryModel>? = null
    private var homeCategoryModel: HomeCategoryModel? = null
    private var islandName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_category_trial)
        islandName = intent.getStringExtra(AppConstants.INTENT_ISLAND_NAME)!!
        initializeAllView()
    }

    override fun initializeAllView() {
        tv_welcome.text = "Welcome to $islandName"
        setListsAndAdapters()
    }

    override fun setListsAndAdapters() {
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
        rv_homeCategory.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    0, 1, 3, 4, 5, 6, 9, 10, 11, 12, 14, 15, 16, 17 -> {
                        1
                    }
                    2, 7, 8, 13, 18 -> {
                        2
                    }
                    else -> {
                        1
                    }
                }
//                return if (position % 3 == 0) 2 else 1
            }
        }
        rv_homeCategory.layoutManager = layoutManager
        rv_homeCategory.adapter = HomeCategoryListAdapter(this, homeCategoryList!!)
        clickListeners()
    }

    override fun clickListeners() {
        imageViewMenu.setOnClickListener(this)
    }

    @SuppressLint("RestrictedApi")
    private fun showPopup(v: View) {
        val wrapper: Context = ContextThemeWrapper(this, R.style.popupMenuStyle)
        val popup = PopupMenu(wrapper, v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.home_screen_menu, popup.menu)
        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_introduction -> {
                    showMessage("Introduction")
                }
            }
            true
        }
        popup.show()
        val menuHelper = MenuPopupHelper(this, (popup.menu as MenuBuilder), v)
        menuHelper.setForceShowIcon(true)
        menuHelper.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_screen_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_introduction -> {
                showMessage("Introduction")
            }
        }
        return true
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.imageViewMenu -> {
                showPopup(view)
            }
        }
    }

    class HomeCategoryModel {
        var categoryName: String? = ""
        var categoryImage: Int? = null
    }
}
