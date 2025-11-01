package com.nicos.material3expressivelist.presentation.module

import android.content.Context
import com.nicos.material3expressivelist.presentation.expressive_list_screen.models.CreateExpressiveListDataModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class ExpressiveDataModelModule {

    @Provides
    fun provideExpressiveDataModelList(@ApplicationContext context: Context): CreateExpressiveListDataModel {
        return CreateExpressiveListDataModel(
            context = context
        )
    }
}