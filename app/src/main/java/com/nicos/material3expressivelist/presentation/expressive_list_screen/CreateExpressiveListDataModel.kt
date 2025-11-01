package com.nicos.material3expressivelist.presentation.expressive_list_screen

import android.content.Context
import com.nicos.material3expressivelist.R
import com.nicos.material3expressivelist.presentation.navigation.ScreenRoutes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CreateExpressiveListDataModel(
    private val context: Context
) {

    fun createExpressiveListDataModel(): Flow<MutableList<ExpressiveListDataModel>> {
        return flow {
            val list = mutableListOf<ExpressiveListDataModel>()

            list.add(
                ExpressiveListDataModel(
                    context.getString(R.string.button_groups),
                    screenRoutes = ScreenRoutes.ButtonGroupsScreen
                )
            )

            list.add(
                ExpressiveListDataModel(
                    context.getString(R.string.split_buttons),
                    screenRoutes = ScreenRoutes.SplitButtonsScreen
                )
            )

            list.add(
                ExpressiveListDataModel(
                    "",
                    screenRoutes = ScreenRoutes.LoadingProgressIndicatorScreen
                )
            )

            list.add(
                ExpressiveListDataModel(
                    "",
                    screenRoutes = ScreenRoutes.FabMenuWithListScreen
                )
            )

            list.add(
                ExpressiveListDataModel(
                    "",
                    screenRoutes = ScreenRoutes.FabMenuScreen
                )
            )

            list.add(
                ExpressiveListDataModel(
                    "",
                    screenRoutes = ScreenRoutes.FloatingToolBarWithListScreen
                )
            )

            list.add(
                ExpressiveListDataModel(
                    "",
                    screenRoutes = ScreenRoutes.FloatingToolBarScreen
                )
            )

            emit(list)
        }
    }
}