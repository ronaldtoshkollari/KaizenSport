package com.example.kaizensport.di

import android.content.Context
import androidx.room.Room
import com.example.kaizensport.data.local.KaizenSportDatabase
import com.example.kaizensport.data.local.dao.CategoryDao
import com.example.kaizensport.data.local.dao.MatchEventDao
import com.example.kaizensport.data.remote.KaizenApi
import com.example.kaizensport.data.repository.KaizenSportRepositoryImpl
import com.example.kaizensport.domain.repository.KaizenSportRepository
import com.example.kaizensport.domain.use_case.UpdateMatchFavourite
import com.example.kaizensport.domain.use_case.GetCategories
import com.example.kaizensport.domain.use_case.GetMatches
import com.example.kaizensport.domain.use_case.UpdateEventCountDown
import com.example.kaizensport.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideKaizenApi(): KaizenApi {
        return Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KaizenApi::class.java)
    }

    @Provides
    @Singleton
    fun provideKaizenSportDatabase(@ApplicationContext context: Context): KaizenSportDatabase {
        return Room.databaseBuilder(
            context,
            KaizenSportDatabase::class.java,
            "kaizen_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(db: KaizenSportDatabase): CategoryDao {
        return db.categoryDao()
    }

    @Provides
    @Singleton
    fun provideMatchEventDao(db: KaizenSportDatabase): MatchEventDao {
        return db.matchEventDao()
    }

    @Provides
    @Singleton
    fun provideKaizenSportRepository(
        categoryDao: CategoryDao,
        matchEventDao: MatchEventDao,
        api: KaizenApi
    ): KaizenSportRepository {
        return KaizenSportRepositoryImpl(categoryDao, matchEventDao, api)
    }

    @Provides
    @Singleton
    fun provideGetCategoryUseCase(repository: KaizenSportRepository): GetCategories {
        return GetCategories(repository)
    }

    @Provides
    @Singleton
    fun provideGetMatchesUseCase(repository: KaizenSportRepository): GetMatches {
        return GetMatches(repository)
    }

    @Provides
    @Singleton
    fun provideAddMatchFavourite(): UpdateMatchFavourite {
        return UpdateMatchFavourite()
    }

    @Provides
    @Singleton
    fun provideUpdateEventCountDown(): UpdateEventCountDown {
        return UpdateEventCountDown()
    }


}