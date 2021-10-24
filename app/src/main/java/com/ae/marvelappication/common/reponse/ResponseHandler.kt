package com.ae.marvelappication.common.reponse

interface ResponseHandler {

    fun <T : Any> handleSuccess(data: T): Resource<T>

    fun <T : Any> handleException(e: Exception): Resource<T>
}