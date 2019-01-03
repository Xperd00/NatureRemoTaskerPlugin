/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.taskerplugin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.fishwaffle.natureremo.controller.NatureRemo
import com.fishwaffle.natureremo.taskerplugin.Appliances
import com.fishwaffle.natureremo.taskerplugin.R
import com.fishwaffle.natureremo.taskerplugin.getToken
import kotlinx.android.synthetic.main.fragment_progress.*
import kotlin.concurrent.thread


class ProgressFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_progress, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         activity!!.intent.action.let {
             if (it.equals("android.intent.action.MAIN",ignoreCase = true)) {
                 progressBar.findNavController().navigate(ProgressFragmentDirections.toSettingFragment())
                 return
             }
         }

        val token = getToken(context!!)
        if (token.isBlank()) {
            //トークン未設定の場合
            //設定画面へ
            progressBar.findNavController().navigate(ProgressFragmentDirections.toSettingFragment().apply {
                edit = true
            })
        } else {
            //トークン設定済みの場合
            thread {
                val appliances = NatureRemo.appliancesGet(token)
                if (appliances != null) {
                    //家電選択画面へ
                    progressBar.findNavController().navigate(ProgressFragmentDirections.toAppliancesFragment(Appliances().apply {
                        addAll(appliances)
                    }))
                } else {
                    Toast.makeText(context!!,"エラー",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}
