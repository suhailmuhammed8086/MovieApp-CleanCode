package com.app.moviesapp.data

import okio.IOException

class NoNetworkException: IOException() {
    companion object {
        const val NO_NETWORK_CONNECTION_ERROR_CODE = -500
    }
}