/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.fragment


import android.app.Activity
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
import com.fishwaffle.natureremo.R
import com.fishwaffle.natureremo.controller.models.Signal
import com.fishwaffle.natureremo.createTaskerDataSignalSend
import kotlinx.android.synthetic.main.fragment_infra_red.*

class InfraRedFragment : Fragment() {
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_infra_red, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = InfraRedFragmentArgs.fromBundle(arguments)
        val applianceName = args.appliance.nickname
        viewManager = LinearLayoutManager(context)
        viewAdapter = InfraRedFragment.SignalAdapter(args.appliance.signals.toList(), object : SignalAdapter.OnItemClickListener {
            override fun onClick(signalName: String, id: String) {
                val intent = createTaskerDataSignalSend(applianceName, signalName, id)
                activity!!.setResult(Activity.RESULT_OK,intent)
                activity!!.finish()
            }
        })
        signalRecycler.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
            adapter = viewAdapter
            layoutManager = viewManager
        }
    }

    class SignalAdapter(private val myDadaist: List<Signal>, private val listener: OnItemClickListener) :
            RecyclerView.Adapter<SignalAdapter.MyViewHolder>() {

        class MyViewHolder(val linearLayout: LinearLayout, val name: TextView) : RecyclerView.ViewHolder(linearLayout)
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
                listener.onClick(signal.name, signal.id)
            }
        }

        override fun getItemCount() = myDadaist.size
    }
}
