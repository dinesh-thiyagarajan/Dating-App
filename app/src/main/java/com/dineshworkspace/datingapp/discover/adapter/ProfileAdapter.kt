package com.dineshworkspace.datingapp.discover.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dineshworkspace.datingapp.R
import com.dineshworkspace.datingapp.base.LikedProfile

class ProfileAdapter : ListAdapter<LikedProfile, ProfileItemViewHolder>(ProfileDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profiles, parent, false)
        return ProfileItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfileItemViewHolder, position: Int) {
        if (position != -1) {
            val profile = getItem(position)
            val context = holder.itemView.context
            profile?.let {
                Glide.with(context)
                    .load(profile.avatar)
                    .placeholder(R.drawable.sample_grl_1)
                    .into(holder.ivProfile)
                holder.tvName.text = profile.firstName
                holder.tvTapToReview.text = context.getString(R.string.tap_to_review, "50")
            }
        }
    }

}

class ProfileItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivProfile: ImageView = itemView.findViewById(R.id.iv_profile_image)
    val tvName: TextView = itemView.findViewById(R.id.tv_profile_name)
    val tvTapToReview: TextView = itemView.findViewById(R.id.tv_tap_to_review)
}

class ProfileDiffUtilCallback : DiffUtil.ItemCallback<LikedProfile>() {
    override fun areItemsTheSame(oldItem: LikedProfile, newItem: LikedProfile): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: LikedProfile, newItem: LikedProfile): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}