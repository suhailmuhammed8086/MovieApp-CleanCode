package com.app.moviesapp.data

import okio.IOException

class ValidationErrorException : IOException{
    var errorCode: Int = -1
    constructor(errorCode: Int, ) : super(){
        this.errorCode = errorCode
    }
    constructor(errorCode: Int, message: String?) : super(message){
        this.errorCode = errorCode
    }
}