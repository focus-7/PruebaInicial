package com.example.pruebainicial.dimodule

import android.content.Context
import androidx.room.Room
import com.example.infraestructure.dblocal.AppDatabase
import com.example.infraestructure.dblocal.dao.TariffDao
import com.example.infraestructure.dblocal.util.AppConstants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideTariffDao(db: AppDatabase): TariffDao = db.tariffDao()
}