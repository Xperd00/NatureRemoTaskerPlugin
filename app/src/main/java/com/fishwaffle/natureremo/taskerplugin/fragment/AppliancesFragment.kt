/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.taskerplugin.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import com.fishwaffle.natureremo.controller.models.Appliance
import com.fishwaffle.natureremo.taskerplugin.R
import kotlinx.android.synthetic.main.fragment_appliances.*


class AppliancesFragment : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_appliances, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = LinearLayoutManager(context)
        viewAdapter = ApplianceAdapter(AppliancesFragmentArgs.fromBundle(arguments!!).appliances)
        applianceRecycler.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
            adapter = viewAdapter
            layoutManager = viewManager
        }
    }

    class ApplianceAdapter(private val myDadaist: List<Appliance>) :
            RecyclerView.Adapter<ApplianceAdapter.MyViewHolder>() {

        class MyViewHolder(val linearLayout: LinearLayout, val name: TextView, val type: TextView) : RecyclerView.ViewHolder(linearLayout)

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



