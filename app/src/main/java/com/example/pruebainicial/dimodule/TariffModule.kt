package com.example.pruebainicial.dimodule

import com.example.domain.repository.TariffRepository
import com.example.infraestructure.dblocal.repository.TariffRepositoryRoom
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class TariffModule {
    @Binds
    abstract fun bindTariffRepository(tariffRepositoryRoom: TariffRepositoryRoom): TariffRepository
}