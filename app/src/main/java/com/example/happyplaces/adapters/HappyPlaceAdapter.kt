package com.example.happyplaces.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.happyplaces.R
import com.example.happyplaces.databinding.ItemHappyPlaceBinding
import com.example.happyplaces.models.HappyPlaceModel

open class HappyPlaceAdapter(
    private val context: Context,
    private var list: ArrayList<HappyPlaceModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var binding: ItemHappyPlaceBinding
    private var listener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_happy_place,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

        if (holder is MyViewHolder) {
            binding = ItemHappyPlaceBinding.bind(holder.itemView)
            binding.ivPlaceImage.setImageURI(Uri.parse(model.image))
            binding.tvTitle.text = model.title
            binding.tvDescription.text = model.description

            holder.itemView.setOnClickListener{
                listener?.onClick(position, model)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnClickListener(listener: OnClickListener){
        this.listener = listener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: HappyPlaceModel)
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}