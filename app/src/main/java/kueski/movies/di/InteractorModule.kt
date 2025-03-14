package kueski.movies.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kueski.movies.domain.interactor.MovieInteractor
import kueski.movies.domain.interactor.MovieInteractorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InteractorModule {

    @Binds
    @Singleton
    abstract fun bindMovieInteractor(
        movieInteractorImpl: MovieInteractorImpl
    ): MovieInteractor
}