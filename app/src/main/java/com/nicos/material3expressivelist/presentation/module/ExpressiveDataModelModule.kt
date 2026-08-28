package com.nicos.material3expressivelist.presentation.module

import com.nicos.material3expressivelist.presentation.expressive_list_screen.models.CreateExpressiveListDataModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ExpressiveDataModelModule {

    @Provides
    fun provideExpressiveDataModelList(): CreateExpressiveListDataModel {
        return CreateExpressiveListDataModel()
    }
}