package com.movie.films.di

import com.movie.films.di.modules.ApiModule
import com.movie.films.di.modules.ModelModule
import com.movie.films.di.modules.PrefsModule
import com.movie.films.mvp.model.entity.FavoriteMoviesContainer
import com.movie.films.util.ConnectionChecker
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val modelModule = module {
    factory { ApiModule.provideApiInterface() }
    single{ PrefsModule(androidContext()) }
}

val presenterModule = module{
    single { ModelModule.provideModel() }
}

val favoriteMoviesModule = module{
    single { FavoriteMoviesContainer() }
}

val connectionCheckerModule = module{
    single { ConnectionChecker(androidContext()) }

}

val appComponent: List<Module> = listOf(modelModule, presenterModule, favoriteMoviesModule, connectionCheckerModule)