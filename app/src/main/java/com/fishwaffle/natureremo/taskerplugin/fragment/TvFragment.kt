/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.taskerplugin.fragment


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.fishwaffle.natureremo.controller.models.Tv
import com.fishwaffle.natureremo.taskerplugin.R
import com.fishwaffle.natureremo.taskerplugin.createTaskerDataSignalSend
import com.fishwaffle.natureremo.taskerplugin.createTaskerDataTvSend
import kotlinx.android.synthetic.main.fragment_tv.*


class TvFragment : Fragment() {
    private lateinit var viewAdapterSignal: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManagerSignal: androidx.recyclerview.widget.RecyclerView.LayoutManager
    private lateinit var viewAdapterTv: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManagerTv: androidx.recyclerview.widget.RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = InfraRedFragmentArgs.fromBundle(arguments!!)
        val applianceName = args.appliance.nickname
        val applianceId = args.appliance.id
        //Signalリスト
        viewManagerSignal = androidx.recyclerview.widget.LinearLayoutManager(context)
        viewAdapterSignal = SignalAdapter(args.appliance.signals!!.toList(), object : SignalAdapter.OnItemClickListener {
            override fun onClick(signalName: String, id: String) {
                //リストタップ時
                val intent = createTaskerDataSignalSend(applianceName!!, signalName, id)
                activity!!.setResult(Activity.RESULT_OK, intent)
                activity!!.finish()
            }
        })
        signalRecycler_tv.apply {
            addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(context, androidx.recyclerview.widget.DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
            adapter = viewAdapterSignal
            layoutManager = viewManagerSignal
        }

        //TvButtonリスト
        viewManagerTv = androidx.recyclerview.widget.LinearLayoutManager(context)
        viewAdapterTv = TvAdapter(args.appliance.tv!!.buttons!!, object : TvAdapter.OnItemClickListener {
            override fun onClick(name: String, label: String) {
                //リストタップ時
                val intent = createTaskerDataTvSend(applianceName!!, applianceId!!, name, label)
                activity!!.setResult(Activity.RESULT_OK, intent)
                activity!!.finish()
            }
        })
        tvRecycler.apply {
            addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(context, androidx.recyclerview.widget.DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
            adapter = viewAdapterTv
            layoutManager = viewManagerTv
        }


    }

    class TvAdapter(private val myDadaist: List<Tv.Button>, private val listener: OnItemClickListener) :
            androidx.recyclerview.widget.RecyclerView.Adapter<TvAdapter.MyViewHolder>() {

        class MyViewHolder(val linearLayout: LinearLayout, val name: TextView) : androidx.recyclerview.widget.RecyclerView.ViewHolder(linearLayout)
        interface OnItemClickListener {
            fun onClick(name: String, label: String)
        }

        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): MyViewHolder {
            val linearLayout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_view_item_signal, parent, false) as LinearLayout
            return MyViewHolder(linearLayout, linearLayout.findViewById(R.id.name))
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val button = myDadaist[position]
            holder.name.text = button.label
            holder.linearLayout.setOnClickListener {
                listener.onClick(button.name!!, button.label!!)
            }
        }

        override fun getItemCount() = myDadaist.size
    }

}
