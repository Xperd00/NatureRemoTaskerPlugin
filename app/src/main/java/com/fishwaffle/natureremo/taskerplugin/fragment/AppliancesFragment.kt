/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.taskerplugin.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import com.fishwaffle.natureremo.controller.models.Appliance
import com.fishwaffle.natureremo.taskerplugin.R
import kotlinx.android.synthetic.main.fragment_appliances.*


class AppliancesFragment : androidx.fragment.app.Fragment() {

    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_appliances, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        viewAdapter = ApplianceAdapter(AppliancesFragmentArgs.fromBundle(arguments!!).appliances)
        applianceRecycler.apply {
            addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(context, androidx.recyclerview.widget.DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
            adapter = viewAdapter
            layoutManager = viewManager
        }
    }

    class ApplianceAdapter(private val myDadaist: List<Appliance>) :
            androidx.recyclerview.widget.RecyclerView.Adapter<ApplianceAdapter.MyViewHolder>() {

        class MyViewHolder(val linearLayout: LinearLayout, val name: TextView, val type: TextView) : androidx.recyclerview.widget.RecyclerView.ViewHolder(linearLayout)

        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): MyViewHolder {
            val linearLayout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_view_item_appliance, parent, false) as LinearLayout
            return MyViewHolder(linearLayout, linearLayout.findViewById(R.id.name), linearLayout.findViewById(R.id.type))
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val appliance = myDadaist[position]
            holder.name.text = appliance.nickname
            holder.type.text = appliance.type
            holder.linearLayout.setOnClickListener {
                when (appliance.type) {
                    "AC" -> {
                        Navigation.findNavController(it).navigate(AppliancesFragmentDirections.toAirConditionerFragment(appliance))
                    }
                    //todo TVのプリセットを叩くAPIが公開されるまでコメントアウト
//                    "TV" -> {
//
//                    }
                    else -> {
                        Navigation.findNavController(it).navigate(AppliancesFragmentDirections.toInfraRedFragment(appliance))
                    }
                }
            }
        }

        override fun getItemCount() = myDadaist.size
    }
}



