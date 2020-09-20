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
import androidx.core.view.ViewCompat
import com.fishwaffle.natureremo.controller.models.Command
import com.fishwaffle.natureremo.taskerplugin.R
import com.fishwaffle.natureremo.taskerplugin.Type
import com.fishwaffle.natureremo.taskerplugin.createTaskerDataSend
import kotlinx.android.synthetic.main.fragment_commands.*

class CommandsFragment : androidx.fragment.app.Fragment() {
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_commands, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTranslationZ(view, 100f)
        val args = CommandsFragmentArgs.fromBundle(arguments!!)
        val commands: MutableList<Command> = mutableListOf()
        if (args.appliance.type == Type.TV.toString()) args.appliance.tv!!.buttons!!.forEach{ c -> commands.add(c)}
        if (args.appliance.type == Type.LIGHT.toString()) args.appliance.light!!.buttons!!.forEach{c -> commands.add(c)}
        args.appliance.signals!!.forEach{c -> commands.add(c)}
        viewManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        viewAdapter = CommandAdapter(commands.toList(), object : CommandAdapter.OnItemClickListener {
            override fun onClick(command: Command) {
                //リストタップ時
                val intent = createTaskerDataSend(args.appliance, command)
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


    class CommandAdapter(private val commandList: List<Command>, private val listener: OnItemClickListener) :
            androidx.recyclerview.widget.RecyclerView.Adapter<CommandAdapter.MyViewHolder>() {

        class MyViewHolder(val linearLayout: LinearLayout, val name: TextView) : androidx.recyclerview.widget.RecyclerView.ViewHolder(linearLayout)
        interface OnItemClickListener {
            fun onClick(command: Command)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val linearLayout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_view_item_command, parent, false) as LinearLayout
            return MyViewHolder(linearLayout, linearLayout.findViewById(R.id.name))
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val command = commandList[position]
            holder.name.text = command.getTitle()
            holder.linearLayout.setOnClickListener {
                listener.onClick(command)
            }
        }

        override fun getItemCount() = commandList.size

    }
}