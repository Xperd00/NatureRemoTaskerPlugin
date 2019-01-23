/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.taskerplugin.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.fishwaffle.natureremo.controller.models.Signal
import com.fishwaffle.natureremo.taskerplugin.R

class SignalAdapter(private val myDadaist: List<Signal>, private val listener: OnItemClickListener) :
        androidx.recyclerview.widget.RecyclerView.Adapter<SignalAdapter.MyViewHolder>() {

    class MyViewHolder(val linearLayout: LinearLayout, val name: TextView) : androidx.recyclerview.widget.RecyclerView.ViewHolder(linearLayout)
    interface OnItemClickListener {
        fun onClick(signalName: String, id: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {
        val linearLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item_signal, parent, false) as LinearLayout
        return MyViewHolder(linearLayout, linearLayout.findViewById(R.id.name))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val signal = myDadaist[position]
        holder.name.text = signal.name
        holder.linearLayout.setOnClickListener {
            listener.onClick(signal.name!!, signal.id!!)
        }
    }

    override fun getItemCount() = myDadaist.size

}