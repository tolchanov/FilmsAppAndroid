package com.movie.films.di.modules

import com.movie.films.mvp.model.ModelImplementation
import com.movie.films.mvp.model.Model

class ModelModule{
    companion object {
        fun provideModel() : Model {
            return ModelImplementation()
        }
    }
}