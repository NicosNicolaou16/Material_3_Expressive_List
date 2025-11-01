package com.nicos.material3expressivelist.presentation.module

import android.content.Context
import com.nicos.material3expressivelist.presentation.expressive_list_screen.CreateExpressiveListDataModel
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
class ExpressiveDataModelModule {
    @Provides
    fun provideExpressiveDataModelList(@ApplicationContext context: Context): CreateExpressiveListDataModel {
        return CreateExpressiveListDataModel(
            context = context
        )
    }
}