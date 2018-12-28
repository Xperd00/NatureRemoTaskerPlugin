/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.fishwaffle.natureremo.Appliances
import com.fishwaffle.natureremo.R
import com.fishwaffle.natureremo.controller.NatureRemo
import com.fishwaffle.natureremo.getToken
import kotlinx.android.synthetic.main.fragment_progress.*
import kotlin.concurrent.thread


class ProgressFragment : Fragment() {

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

        val token = context?.let { getToken(it) } ?: ""
        if (token.isBlank()) {
            //トークン未設定の場合
            //設定画面へ
            progressBar.findNavController().navigate(ProgressFragmentDirections.toSettingFragment().apply {
                setEdit(true)
            })
        } else {
            //トークン設定済みの場合
            thread {
                val appliances = NatureRemo.Appliances_Get(token)
                if (appliances != null) {
                    //家電選択画面へ
                    progressBar.findNavController().navigate(ProgressFragmentDirections.toAppliancesFragment(Appliances().apply {
                        addAll(appliances)
                    }))
                } else {
                    Log.d("a", "エラー")
                }
            }
        }

    }

}
