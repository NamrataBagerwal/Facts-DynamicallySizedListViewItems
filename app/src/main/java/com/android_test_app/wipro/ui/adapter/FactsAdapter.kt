package com.android_test_app.wipro.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android_test_app.wipro.AppConstants
import com.android_test_app.wipro.R
import com.android_test_app.wipro.repository.remote_repository.webservice.entity.Row
import com.bumptech.glide.Glide

class FactsAdapter: RecyclerView.Adapter<FactsAdapter.FactsViewHolder>() {

    var factsList: List<Row>? = listOf()

    set(value){
        field = value
        notifyDataSetChanged()
    }

    class FactsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val rowTitleTextView: TextView = itemView.findViewById(R.id.rowTitleTextView)
        val rowDescriptionTextView: TextView = itemView.findViewById(R.id.rowDescriptionTextView)
        val rowImageView: ImageView = itemView.findViewById(R.id.rowImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_adapter_facts, parent, false)
        return FactsViewHolder(view)
    }

    override fun getItemCount(): Int = factsList!!.size

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        val row: Row? = factsList?.get(position)

        val title = row?.title?.trim() ?: AppConstants.FACTS_TITLE_DEFAULT_VALUE
        val description = row?.description?.trim() ?: AppConstants.FACTS_DESCRIPTION_DEFAULT_VALUE
        holder.rowTitleTextView.text = title
        holder.rowDescriptionTextView.text = description

        Glide.with(holder.itemView.context)
            .load(row?.imageHref)
            .placeholder(R.drawable.placeholder_image)
            .fallback(R.drawable.placeholder_image)
            .into(holder.rowImageView)
    }
}