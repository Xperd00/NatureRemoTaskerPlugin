/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.taskerplugin.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fishwaffle.natureremo.taskerplugin.R
import com.fishwaffle.natureremo.taskerplugin.createTaskerDataSignalSend
import kotlinx.android.synthetic.main.fragment_infra_red.*

class InfraRedFragment : androidx.fragment.app.Fragment() {
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_infra_red, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = InfraRedFragmentArgs.fromBundle(arguments!!)
        val applianceName = args.appliance.nickname
        viewManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        viewAdapter = SignalAdapter(args.appliance.signals!!.toList(), object : SignalAdapter.OnItemClickListener {
            override fun onClick(signalName: String, id: String) {
                //リストタップ時
                val intent = createTaskerDataSignalSend(applianceName!!, signalName, id)
                activity!!.setResult(Activity.RESULT_OK, intent)
                activity!!.finish()
            }
        })
        signalRecycler.apply {
            addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(context, androidx.recyclerview.widget.DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
            adapter = viewAdapter
            layoutManager = viewManager
        }
    }

}