package com.tech.peachmedia.di

import androidx.room.Room
import com.tech.peachmedia.feature_media_posts.data.datasource.local.PeachDatabase
import com.tech.peachmedia.feature_media_posts.data.datasource.remote.api.CommonApiService
import com.tech.peachmedia.feature_media_posts.data.repository.PostsRepository
import com.tech.peachmedia.feature_media_posts.domain.repository.PostsRepositoryImpl
import com.tech.peachmedia.feature_media_posts.domain.utils.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

const val baseUrl: String = Constants.BASE_URL

val appModule = module {
    single { createNetworkClient(baseUrl) }
    single { (get() as? Retrofit)?.create(CommonApiService::class.java) }

    single {
        Room.databaseBuilder(
            androidContext(),
            PeachDatabase::class.java,
            PeachDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    single {
        get<PeachDatabase>().commentsDao
    }

    single {
        get<PeachDatabase>().postsDao
    }

    single {
        get<PeachDatabase>().usersDao
    }

    single {

    }

    factory<PostsRepository> {
        PostsRepositoryImpl(
            commonApiService = get(), usersDao = get(), commentsDao = get(), postsDao = get()
        )
    }
}
