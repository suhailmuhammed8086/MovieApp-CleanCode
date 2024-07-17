package com.app.moviesapp.utils

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.RECEIVER_NOT_EXPORTED
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log

class BroadcastUtils {

    private var mListener: BroadcastListener? = null
    private var mListenerLambda: ((action: String?, result: Intent?) -> Unit)? = null

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    fun attach(
        context: Context?,
        expectedActions: List<String>? = null,
        listener: BroadcastListener,
    ): BroadcastUtils {
        if (context == null) {
            Log.e(TAG, "attachToActivity: failed to attach to context")
            return this
        }
        val intentFilter = if (expectedActions.isNullOrEmpty()) {
            getIntentFilter()
        } else {
            val mFilter = IntentFilter()
            expectedActions.forEach { actions ->
                mFilter.addAction(actions)
            }
            mFilter
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(broadCastReceiver, intentFilter, RECEIVER_NOT_EXPORTED)
        } else {
            context.registerReceiver(broadCastReceiver, intentFilter)

        }
        mListener = listener
        return this
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    fun attach(
        context: Context?,
        expectedActions: List<String>? = null,
        listenerLambda: (action: String?, result: Intent?) -> Unit,
    ): BroadcastUtils {
        if (context == null) {
            Log.e(TAG, "attachToActivity: failed to attach to context")
            return this
        }

        val intentFilter = if (expectedActions.isNullOrEmpty()) {
            getIntentFilter()
        } else {
            val mFilter = IntentFilter()
            expectedActions.forEach { actions -> mFilter.addAction(actions) }
            mFilter
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(broadCastReceiver, intentFilter, RECEIVER_NOT_EXPORTED)
        } else {
            context.registerReceiver(broadCastReceiver, intentFilter)

        }
        mListenerLambda = listenerLambda
        return this
    }

    fun detach(context: Context?) {
        context?.unregisterReceiver(broadCastReceiver)
    }

    private var broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action
            mListener?.onBroadcastReceived(action, intent)
            mListenerLambda?.invoke(action, intent)
        }
    }
    interface BroadcastListener {
        fun onBroadcastReceived(action: String?, result: Intent?)
    }

    companion object {
        private const val TAG = "BroadCastUtils"

        fun send(context: Context?, action: String, data: Bundle? = null) {
            if (context == null) {
                Log.e(TAG, "send: invalid context")
                return
            }
            try {
                val intent = Intent(action)
                intent.setPackage(context.packageName)
                if (data != null) {
                    intent.putExtras(data)
                }
                context.sendBroadcast(intent)
            } catch (e: Exception) {
                Log.e(TAG, "send: failed to send broadcast")
            }
        }

        // REGISTER YOUR ACTIONS HERE
        const val ACTION_EXAMPLE_REFRESH = "action.example.refresh"
    }

    private fun getIntentFilter(): IntentFilter {
        val intentFilter = IntentFilter()
        // add your common broadcast actions here..
        return intentFilter
    }
}