package com.example.kaizensport.data.repository

import android.util.Log
import com.example.kaizensport.data.local.dao.CategoryDao
import com.example.kaizensport.data.local.dao.MatchEventDao
import com.example.kaizensport.data.remote.KaizenApi
import com.example.kaizensport.data.remote.dto.SportInfoDto
import com.example.kaizensport.domain.model.Category
import com.example.kaizensport.domain.model.MatchEvent
import com.example.kaizensport.domain.repository.KaizenSportRepository
import com.example.kaizensport.util.Resource
import com.example.kaizensport.util.mappers.CategoryMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class KaizenSportRepositoryImpl constructor(
    private val categoryDao: CategoryDao,
    private val matchEventDao: MatchEventDao,
    private val api: KaizenApi
) : KaizenSportRepository {


    override suspend fun getCategories(): Flow<Resource<List<Category>>> = flow {

        emit(Resource.Loading())

        try {

            val categories = categoryDao.getAllCategories().map { CategoryMapper.entityToModel(it) } //Checking if the data are in room database

            if (categories.isNullOrEmpty()) { //Data are not in room database

                val response = api.getMatchesInfo() //Getting data from api
                if(response.isNullOrEmpty()){



                }

            } else { //Data not in room database

                emit(Resource.Success(categories))

            }

            val response = api.getMatchesInfo()

        } catch (e: Exception) {
            Log.e("RepoImpl", "getCategories: ${e.message}")
            emit(Resource.Error(message = "An error occurred check your internet connection."))
        }

    }

    override suspend fun getMatchEvents(): Flow<Resource<List<MatchEvent>>> = flow {
        TODO("Not yet implemented")
    }

    override suspend fun clearDatabase() {
        categoryDao.deleteAllCategories()
        matchEventDao.deleteAllMatchEvents()
    }


}