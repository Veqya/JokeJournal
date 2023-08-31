package di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import usecases.DeleteLocalJokeUseCase
import usecases.DeleteLocalJokeUseCaseImpl
import usecases.EditLocalJokeUseCase
import usecases.EditLocalJokeUseCaseImpl
import usecases.GetLocalJokeByIdUseCase
import usecases.GetLocalJokeByIdUseCaseImpl
import usecases.GetLocalJokesUseCase
import usecases.GetLocalJokesUseCaseImpl
import usecases.GetRandomRemoteJokeUseCase
import usecases.GetRandomRemoteJokeUseCaseImpl
import usecases.SaveLocalJokeUseCase
import usecases.SaveLocalJokeUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindDeleteLocalJokeUseCase(useCaseImpl: DeleteLocalJokeUseCaseImpl): DeleteLocalJokeUseCase

    @Binds
    fun bindEditLocalJokeUseCase(useCaseImpl: EditLocalJokeUseCaseImpl): EditLocalJokeUseCase

    @Binds
    fun bindGetLocalJokeByIdUseCase(useCaseImpl: GetLocalJokeByIdUseCaseImpl): GetLocalJokeByIdUseCase

    @Binds
    fun bindGetLocalJokesUseCase(useCaseImpl: GetLocalJokesUseCaseImpl): GetLocalJokesUseCase

    @Binds
    fun bindGetRandomRemoteJokeUseCase(useCaseImpl: GetRandomRemoteJokeUseCaseImpl): GetRandomRemoteJokeUseCase

    @Binds
    fun bindSaveLocalJokeUseCase(useCaseImpl: SaveLocalJokeUseCaseImpl): SaveLocalJokeUseCase
}