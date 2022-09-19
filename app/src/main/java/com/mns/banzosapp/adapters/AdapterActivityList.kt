package com.mns.banzosapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mns.banzosapp.R
import com.mns.banzosapp.adapters.base.BaseAdapterLoadingAndSearch
import com.mns.banzosapp.app_utils.URLHelper
import com.mns.banzosapp.model.ActivityDetails

class AdapterActivityList(
    activityDetailsList: MutableList<ActivityDetails>,
    val mainTitle: String
) :
    BaseAdapterLoadingAndSearch<ActivityDetails>(activityDetailsList) {
    private lateinit var activityClickListener: ActivityClickListener
    fun setListener(activityClickListener: ActivityClickListener) {
        this.activityClickListener = activityClickListener
    }

    override fun setViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ActivityViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_activities_list_item, parent, false)
        )
    }

    override fun onBindData(holderBase: RecyclerView.ViewHolder, baseValue: ActivityDetails) {
        val holder = holderBase as ActivityViewHolder
        holder.textViewActivityMainName.text = mainTitle
        holder.textViewActivityName.text = baseValue.nameofactivity
        holder.textViewOfferPrice.text =
            String.format(context.getString(R.string.dollar_some_money), baseValue.discount_price)
        holder.textViewOriginalPrice.text =
            String.format(context.getString(R.string.dollar_some_money), baseValue.original_price)
        holder.textViewDescription.text = baseValue.tripsummary
        holder.textViewReviews.text = "${baseValue.review} Reviews"
        holder.textViewDuration.text = baseValue.duration
        holder.imageViewTopRated.visibility =
            if (baseValue.topratedactivity == "1") View.VISIBLE else View.GONE
        if (baseValue.slider.isNotEmpty()) {
            Glide.with(context).load(URLHelper.ISLAND_IMAGE_URL + baseValue.slider[0].image)
                .placeholder(R.drawable.ic_sample3).into(holder.iv_activityImage)
        }
    }

    inner class ActivityViewHolder(iView: View) : RecyclerView.ViewHolder(iView) {
        val iv_activityImage: ImageView = iView.findViewById(R.id.iv_activityImage)
        val imageViewTopRated: ImageView = iView.findViewById(R.id.imageViewTopRated)
        val textViewActivityMainName: TextView = iView.findViewById(R.id.textViewActivityMainName)
        val textViewActivityName: TextView = iView.findViewById(R.id.textViewActivityName)
        val textViewOfferPrice: TextView = iView.findViewById(R.id.textViewOfferPrice)
        val textViewOriginalPrice: TextView = iView.findViewById(R.id.textViewOriginalPrice)
        val textViewLocation: TextView = iView.findViewById(R.id.textViewLocation)
        val textViewDuration: TextView = iView.findViewById(R.id.textViewDuration)
        val textViewReviews: TextView = iView.findViewById(R.id.textViewReviews)
        val textViewDescription: TextView = iView.findViewById(R.id.textViewDescription)
        val textViewViewDetails: TextView = iView.findViewById(R.id.textViewViewDetails)
        val tv_addToCart: TextView = iView.findViewById(R.id.tv_addToCart)
        val tv_giveAsGift: TextView = iView.findViewById(R.id.tv_giveAsGift)

        init {
            textViewViewDetails.setOnClickListener {
                activityClickListener.onView(getItem(adapterPosition))
            }
            tv_addToCart.setOnClickListener {
                activityClickListener.onAddToCart(getItem(adapterPosition))
            }
            tv_giveAsGift.setOnClickListener {
                activityClickListener.onGiveAsGift(getItem(adapterPosition))
            }
        }
    }

    interface ActivityClickListener {
        fun onView(activityDetails: ActivityDetails)
        fun onAddToCart(activityDetails: ActivityDetails)
        fun onGiveAsGift(activityDetails: ActivityDetails)
    }
}
