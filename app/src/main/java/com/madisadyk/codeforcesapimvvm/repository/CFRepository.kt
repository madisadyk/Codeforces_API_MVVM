package com.madisadyk.codeforcesapimvvm.repository

import com.madisadyk.codeforcesapimvvm.api.RetrofitInstance
import com.madisadyk.codeforcesapimvvm.db.HandlerDatabase
import com.madisadyk.codeforcesapimvvm.models.CFHandler

class CFRepository(
    val db: HandlerDatabase
) {
    suspend fun getContests() =
        RetrofitInstance.api.getContests()

    suspend fun getHandler(handler: String) =
        RetrofitInstance.api.getHandler(handler)

    suspend fun upsert(handler: CFHandler) = db.getHandlerDao().upsert(handler)

    fun getAllHandlers() = db.getHandlerDao().getAllHandlers()

    suspend fun deleteHandler(handler: CFHandler) = db.getHandlerDao().deleteHandler(handler)
}