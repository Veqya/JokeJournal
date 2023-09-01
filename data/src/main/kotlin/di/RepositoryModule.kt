package di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import repositories.JokesRepository
import repository.JokesRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun bindJokesRepository(repositoryImpl: JokesRepositoryImpl): JokesRepository
}