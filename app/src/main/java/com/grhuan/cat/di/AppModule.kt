package com.grhuan.cat.di

import com.grhuan.cat.data.local.CatDatabase
import com.grhuan.cat.data.remote.ApiKeyInterceptor
import com.grhuan.cat.data.repository.CatRepository
import com.grhuan.cat.data.repository.LocalCatRepository
import com.grhuan.cat.data.service.CatService
import com.grhuan.cat.presentation.gallery.GalleryViewModel
import com.grhuan.cat.presentation.newcat.NewCatViewModel
import com.grhuan.cat.utils.PreferencesUtils
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val daoModule = module {
    single { CatDatabase.getInstance(androidContext()).catDao }
}

val repositoryModule = module {
    single { CatRepository(get()) }
    single { LocalCatRepository(get()) }
}

val viewModelModule = module {
    viewModel { NewCatViewModel(get(), get()) }
    viewModel { GalleryViewModel(get()) }
}

val networkModule = module {
    single { ApiKeyInterceptor(get()) }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<ApiKeyInterceptor>())
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    single<CatService> { get<Retrofit>().create(CatService::class.java) }
}

val utilsModule = module {
    single{ PreferencesUtils(get()) }
}


