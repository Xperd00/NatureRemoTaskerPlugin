/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.taskerplugin.fragment


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.fishwaffle.natureremo.controller.models.AirConRangeMode
import com.fishwaffle.natureremo.taskerplugin.R
import com.fishwaffle.natureremo.taskerplugin.createTaskerDataAirConPowerOff
import com.fishwaffle.natureremo.taskerplugin.createTaskerDataAirConSettings
import kotlinx.android.synthetic.main.fragment_air_conditioner.*


/**
 * A simple [Fragment] subclass.
 *
 */
class AirConditionerFragment : androidx.fragment.app.Fragment() {
    private lateinit var mModesArray: ArrayAdapter<String>
    private lateinit var mTempArray: ArrayAdapter<String>
    private lateinit var mVolArray: ArrayAdapter<String>
    private lateinit var mDirArray: ArrayAdapter<String>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_air_conditioner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appliance = AirConditionerFragmentArgs.fromBundle(arguments!!).appliance
        //選択可能範囲を取得
        val modes = appliance.aircon!!.range!!.modes

        //選択可能なモードのListを作成
        val modesList = modes!!.run {
            listOfNotNull(if (cool != null) "cool" else null,
                    if (warm != null) "warm" else null,
                    if (dry != null) "dry" else null,
                    if (blow != null) "blow" else null,
                    if (auto != null) "auto" else null)
        }

        mModesArray = ArrayAdapter(context!!, R.layout.spinner_item, modesList)
        mTempArray = ArrayAdapter(context!!, R.layout.spinner_item)
        mVolArray = ArrayAdapter(context!!, R.layout.spinner_item)
        mDirArray = ArrayAdapter(context!!, R.layout.spinner_item)
        spinnerModes.adapter = mModesArray
        spinnerTemp.adapter = mTempArray
        spinnerVol.adapter = mVolArray
        spinnerDir.adapter = mDirArray

        //モード選択時の処理
        spinnerModes.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                //選択せれた時
                val spinner = parent as Spinner
                when (spinner.selectedItem) {
                    "auto" -> {
                        setArray(modes.auto!!)
                    }
                    "cool" -> {
                        setArray(modes.cool!!)
                    }
                    "dry" -> {
                        setArray(modes.dry!!)
                    }
                    "warm" -> {
                        setArray(modes.warm!!)
                    }
                    "blow" -> {
                        setArray(modes.blow!!)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                //選択されなかった時
                clearArray()
            }
        }
        switchPower.setOnCheckedChangeListener { _, isChecked ->
            spinnerModes.isEnabled = isChecked
            if (isChecked) {
                setSpinnerEnabled()
            } else {
                spinnerTemp.isEnabled = false
                spinnerVol.isEnabled = false
                spinnerDir.isEnabled = false
            }

        }


        buttonOk.setOnClickListener {
            val intent = if (switchPower.isChecked) {
                createTaskerDataAirConSettings(appliance.nickname!!, appliance.id!!,
                        mode = spinnerModes.selectedItem as String,
                        temperature = spinnerTemp.selectedItem as String,
                        volume = spinnerVol.selectedItem as String,
                        direction = spinnerDir.selectedItem as String)
            } else {
                createTaskerDataAirConPowerOff(appliance.nickname!!, appliance.id!!)
            }
            activity!!.setResult(Activity.RESULT_OK, intent)
            activity!!.finish()
        }

    }

    private fun ArrayAdapter<String>.isBlank(): Boolean = !(this.count == 1 && this.getItem(0).isNullOrBlank())

    private fun setArray(mode: AirConRangeMode) {
        clearArray()
        mTempArray.addAll(mode.temp!!.toList())
        mVolArray.addAll(mode.vol!!.toList())
        mDirArray.addAll(mode.dir!!.toList())
        setSpinnerEnabled()
    }

    private fun setSpinnerEnabled() {
        spinnerTemp.isEnabled = mTempArray.isBlank()
        spinnerVol.isEnabled = mVolArray.isBlank()
        spinnerDir.isEnabled = mDirArray.isBlank()
    }

    private fun clearArray() {
        mTempArray.clear()
        mVolArray.clear()
        mDirArray.clear()
    }
}
