package com.cdnsol.androidtest.model.usecases

import io.reactivex.Observable

abstract class BaseUseCase<PARAM,RESULT>
{
    fun execute(param: PARAM): Observable<RESULT> {
        return createUsesCase(param)
    }
    fun execute(): Observable<RESULT> {
        return createUsesCase(null)
    }
    abstract fun createUsesCase(param: PARAM?): Observable<RESULT>


}