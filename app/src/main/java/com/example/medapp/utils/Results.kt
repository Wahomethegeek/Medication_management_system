package com.example.medapp.utils

data class Results<out T>(val status: ResultStatus, val data: T?, val message: String?) {
    companion object {
        // in case of success
        fun <T> success(data: T?): Results<T & Any> {
            return Results(ResultStatus.SUCCESS, data, null)
        }

        //in case of failure
        fun <T> error(msg: String): Results<T> {
            return Results(ResultStatus.ERROR, null, msg)
        }

        //loading
        fun <T> loading(): Results<T> {
            return Results(ResultStatus.LOADING, null, null)
        }
    }
}
