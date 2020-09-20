/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.taskerplugin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.core.app.NotificationManagerCompat
import com.fishwaffle.natureremo.controller.NatureRemo
import com.fishwaffle.natureremo.controller.exception.AuthenticationException
import com.fishwaffle.natureremo.controller.exception.OtherException
import com.fishwaffle.natureremo.controller.exception.RequestLimitException
import kotlin.concurrent.thread

class MyReceiver : BroadcastReceiver() {
    private val handler = Handler(Looper.getMainLooper())
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(FIRE_SETTING, ignoreCase = true)) {

            if (intent.hasExtra(BUNDLE_NOTIFICATION_ID)) {
                with(NotificationManagerCompat.from(context)) {
                    cancel(intent.getIntExtra(BUNDLE_NOTIFICATION_ID, 0))
                }
            }

            val bundle = intent.getBundleExtra(EXTRA_BUNDLE)!!
            val blurb = intent.getStringExtra(EXTRA_BLURB)!!
            val typeStr = bundle.getString(BUNDLE_TYPE) ?: return
            val type = Type.valueOf(typeStr)
            thread {
                try {

                    when (type) {
                        Type.SIGNAL -> {
                            val signal = bundle.getString(BUNDLE_SIGNAL_ID)
                            NatureRemo.signalsSignalSendPost(getToken(context), signal!!)
                        }
                        Type.AirConPowerOff -> {
                            val appliancesId = bundle.getString(BUNDLE_APPLIANCE_ID)
                            NatureRemo.appliancesApplianceAirConSettingsPost(getToken(context), appliancesId!!,
                                    null, null, null, null,
                                    "power-off")

                        }
                        Type.AirConSettings -> {
                            val appliancesId = bundle.getString(BUNDLE_APPLIANCE_ID)
                            val mode = bundle.getString(BUNDLE_MODE)
                            val temperature = if (bundle.containsKey(BUNDLE_TEMPERATURE)) bundle.getString(BUNDLE_TEMPERATURE) else null
                            val volume = if (bundle.containsKey(BUNDLE_AIR_VOLUME)) bundle.getString(BUNDLE_AIR_VOLUME) else null
                            val direction = if (bundle.containsKey(BUNDLE_AIR_DIRECTION)) bundle.getString(BUNDLE_AIR_DIRECTION) else null


                            NatureRemo.appliancesApplianceAirConSettingsPost(getToken(context), appliancesId!!,
                                    temperature, mode, volume, direction,
                                    "")
                        }
                        Type.TV -> {
                            val appliancesId = bundle.getString(BUNDLE_APPLIANCE_ID)
                            val button = bundle.getString(BUNDLE_BUTTON)
                            NatureRemo.appliancesApplianceTvPost(getToken(context), appliancesId!!, button!!)
                        }
                        Type.LIGHT -> {
                            val appliancesId = bundle.getString(BUNDLE_APPLIANCE_ID)
                            val button = bundle.getString(BUNDLE_BUTTON)
                            NatureRemo.appliancesApplianceLightPost(getToken(context), appliancesId!!, button!!)
                        }
                    }
                    //成功した場合
                    handler.post { showSendSuccessNotification(context, blurb) }

                } catch (e: AuthenticationException) {
                    //Token設定画面
                    handler.post { showToSettingNotification(context, blurb) }
                } catch (e: RequestLimitException) {
                    //リクエスト制限
                    handler.post { showSendErrorNotification(context, blurb, intent) }
                } catch (e: OtherException) {
                    //その他
                    handler.post { showSendErrorNotification(context, blurb, intent) }
                }
            }
        }
    }

}
