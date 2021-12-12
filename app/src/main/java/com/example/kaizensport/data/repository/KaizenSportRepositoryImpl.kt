package com.example.kaizensport.data.repository

import android.util.Log
import com.example.kaizensport.data.local.dao.CategoryDao
import com.example.kaizensport.data.local.dao.MatchEventDao
import com.example.kaizensport.data.local.entity.MatchEventEntity
import com.example.kaizensport.data.remote.KaizenApi
import com.example.kaizensport.domain.model.Category
import com.example.kaizensport.domain.model.MatchEvent
import com.example.kaizensport.domain.repository.KaizenSportRepository
import com.example.kaizensport.util.Resource
import com.example.kaizensport.util.mappers.CategoryMapper
import com.example.kaizensport.util.mappers.MatchEventMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class KaizenSportRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val matchEventDao: MatchEventDao,
    private val api: KaizenApi
) : KaizenSportRepository {


    override  fun getCategories(): Flow<Resource<List<Category>>> = flow {


        emit(Resource.Loading<List<Category>>())

        try {

            val categories = categoryDao.getAllCategories().map { CategoryMapper.entityToModel(it) } //Checking if the data are in room database

            if (categories.isNullOrEmpty()) { //Data are not in room database

                val response = api.getMatchesInfo() //Getting data from api
                if(response.isNullOrEmpty()){
                    throw Exception("Error fetching data from API.")
                }else{

                    categoryDao.deleteAllCategories() //Cleaning room database
                    response.forEach {
                        val category = CategoryMapper.dtoToEntity(it)
                        categoryDao.insertSportCategory(category) //Adding each category to room database
                    }

                    val categoriesList = categoryDao.getAllCategories().map { CategoryMapper.entityToModel(it) } //Getting categories from database
                    emit(Resource.Success(categoriesList)) //Returning categories

                }

            } else { //Data not in room database

                emit(Resource.Success(categories))

            }

        } catch (e: Exception) {
            Log.e("RepoImpl", "getCategories: ${e.message}")
            emit(Resource.Error<List<Category>>(message = "An error occurred check your internet connection."))
        }

    }

    override  fun getMatchEvents(): Flow<Resource<List<MatchEvent>>> = flow {

        emit(Resource.Loading<List<MatchEvent>>())

        try {

            val events = matchEventDao.getAllMatchEvents().map { MatchEventMapper.entityToModel(it) } //Checking if events are in our room database

            if(events.isNullOrEmpty()){ //If events do not exist in our database


                val response = api.getMatchesInfo() //Getting data from API

                if(response.isNullOrEmpty()){

                    throw Exception("Error fetching data from API.")

                } else {

                    //For every category, add all matches to our database
                    matchEventDao.deleteAllMatchEvents()
                    response.forEach {
                        val match = it.events

                        match.forEach {
                            matchEventDao.addMatchEvent(MatchEventMapper.dtoToEntity(it))
                        }
                    }

                    val eventList = matchEventDao.getAllMatchEvents().map { MatchEventMapper.entityToModel(it) }
                    emit(Resource.Success(eventList))

                }


            }else{ //Events exist in our database

                emit(Resource.Success(events))

            }


        } catch (e: Exception) {
            Log.e("RepoImpl", "getMatchEvents: ${e.message}")
            emit(Resource.Error<List<MatchEvent>>(message = "An error occurred check your internet connection."))
        }
    }

    override suspend fun clearDatabase() {
        categoryDao.deleteAllCategories()
        matchEventDao.deleteAllMatchEvents()
    }



}