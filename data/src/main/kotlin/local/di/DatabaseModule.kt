//package local.di
//
//import android.content.Context
//import androidx.room.Room
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import local.JokesDatabase
//import local.dao.JokesDao
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//class DatabaseModule {
//    @Provides
//    @Singleton
//    fun provideAppDatabase(@ApplicationContext context: Context): JokesDatabase =
//        Room.inMemoryDatabaseBuilder(context, JokesDatabase::class.java).build()
//
//    @Provides
//    @Singleton
//    fun provideUserDao(appDatabase: JokesDatabase): JokesDao = appDatabase.jokesDao()
//}