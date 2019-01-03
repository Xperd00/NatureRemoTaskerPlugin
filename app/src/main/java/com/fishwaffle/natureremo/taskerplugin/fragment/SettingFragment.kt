/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.taskerplugin.fragment


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.fishwaffle.natureremo.taskerplugin.R
import com.fishwaffle.natureremo.taskerplugin.getToken
import com.fishwaffle.natureremo.taskerplugin.saveToken
import kotlinx.android.synthetic.main.fragment_setting.*


class SettingFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!SettingFragmentArgs.fromBundle(arguments!!).edit) {
            context?.let {
                tokenEditText.setText(getToken(it))
            }
        }

        generateTokenButton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://home.nature.global")))
        }
        saveButton.setOnClickListener {
            saveToken(context!!, tokenEditText.text.toString())
            Toast.makeText(context!!, getString(R.string.PreferenceSave), Toast.LENGTH_SHORT).show()
            if (SettingFragmentArgs.fromBundle(arguments!!).edit) {
                it.findNavController().navigate(SettingFragmentDirections.toProgressFragment())
            }
        }
    }
}
