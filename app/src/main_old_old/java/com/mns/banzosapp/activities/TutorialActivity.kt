package com.mns.banzosapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.TutorialListAdapter
import com.mns.banzosapp.model.TutorialListModel
import kotlinx.android.synthetic.main.activity_tutorial.*

class TutorialActivity : AppCompatActivity(), View.OnClickListener {

    private var tutorialList: ArrayList<TutorialListModel>? = null
    private var tutorialListModel: TutorialListModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        init()
    }

    private fun init() {

        tutorialList = ArrayList()

        populateTutorial()

        TabLayoutMediator(tl_tutorial, vp_tutorial) { tab, position ->

        }.attach()

        vp_tutorial.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (vp_tutorial.currentItem == tutorialList?.size!! - 1) {
                    btn_next.text = "Get Started"
                } else {
                    btn_next.text = "Next"
                }
            }
        })

        btn_next.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_next -> {
                if (vp_tutorial.currentItem == tutorialList?.size!! - 1) {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    vp_tutorial.currentItem = vp_tutorial.currentItem + 1
                }
            }
        }
    }

    private fun populateTutorial() {

        tutorialListModel = TutorialListModel()
        tutorialListModel?.image = R.drawable.ic_tutorial1
        tutorialListModel?.desc =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."
        tutorialList?.add(tutorialListModel!!)

        tutorialListModel = TutorialListModel()
        tutorialListModel?.image = R.drawable.ic_tutorial2
        tutorialListModel?.desc =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type "
        tutorialList?.add(tutorialListModel!!)

        tutorialListModel = TutorialListModel()
        tutorialListModel?.image = R.drawable.ic_tutorial3
        tutorialListModel?.desc =
            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book"
        tutorialList?.add(tutorialListModel!!)

        tutorialListModel = TutorialListModel()
        tutorialListModel?.image = R.drawable.ic_tutorial4
        tutorialListModel?.desc =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry and has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book"
        tutorialList?.add(tutorialListModel!!)

        tutorialListModel = TutorialListModel()
        tutorialListModel?.image = R.drawable.ic_tutorial5
        tutorialListModel?.desc =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
        tutorialList?.add(tutorialListModel!!)

        tutorialListModel = TutorialListModel()
        tutorialListModel?.image = R.drawable.ic_tutorial6
        tutorialListModel?.desc =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book"
        tutorialList?.add(tutorialListModel!!)

        tutorialListModel = TutorialListModel()
        tutorialListModel?.image = R.drawable.ic_tutorial7
        tutorialListModel?.desc =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book"
        tutorialList?.add(tutorialListModel!!)

        tutorialListModel = TutorialListModel()
        tutorialListModel?.image = R.drawable.ic_tutorial8
        tutorialListModel?.desc =
            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book"
        tutorialList?.add(tutorialListModel!!)

        tutorialListModel = TutorialListModel()
        tutorialListModel?.image = R.drawable.ic_tutorial9
        tutorialListModel?.desc =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry, when an unknown printer took a galley of type and scrambled it to make a type specimen book"
        tutorialList?.add(tutorialListModel!!)

        tutorialListModel = TutorialListModel()
        tutorialListModel?.image = R.drawable.ic_tutorial10
        tutorialListModel?.desc =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book"
        tutorialList?.add(tutorialListModel!!)

        tutorialListModel = TutorialListModel()
        tutorialListModel?.image = R.drawable.ic_tutorial11
        tutorialListModel?.desc =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book"
        tutorialList?.add(tutorialListModel!!)

        tutorialListModel = TutorialListModel()
        tutorialListModel?.image = R.drawable.ic_tutorial12
        tutorialListModel?.desc =
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
        tutorialList?.add(tutorialListModel!!)

        val tutorialListAdapter = TutorialListAdapter(this, tutorialList!!)
        vp_tutorial.adapter = tutorialListAdapter

    }

}
