/*
 * Copyright (c) 2018 FishWaffle.
 */

package com.fishwaffle.natureremo.fragment


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.fishwaffle.natureremo.R
import com.fishwaffle.natureremo.getToken
import com.fishwaffle.natureremo.saveToken
import kotlinx.android.synthetic.main.fragment_setting.*


class SettingFragment : Fragment() {

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
        saveButton.setOnClickListener { view ->
            saveToken(context!!, tokenEditText.text.toString())
            Toast.makeText(context!!, getString(R.string.PreferenceSave), Toast.LENGTH_SHORT).show()
            if (SettingFragmentArgs.fromBundle(arguments!!).edit) {
                view.findNavController().navigate(SettingFragmentDirections.toProgressFragment())
            }
        }
    }
}
