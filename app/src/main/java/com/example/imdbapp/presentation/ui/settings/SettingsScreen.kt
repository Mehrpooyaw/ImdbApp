package com.example.imdbapp.presentation.ui.settings

import android.util.Log
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    viewModel : SettingsViewModel,
    toggleTheme : () -> Unit,
    onNavigateToLoginView : (String) -> Unit,
    onNavigateToRegisterWebView : (String) -> Unit
){
    val onLoad = viewModel.onLoad.value
    if (!onLoad) {
        viewModel.onLoad.value = true
        viewModel.getApiKeys()
    }

    val apiKeys = viewModel.apiKeys.value


    val selectedApiKey = viewModel.selectedApiKey.value


    Log.d("appDebug","SettingsScreen : apiKeys are $apiKeys")



    SettingsView(
        apiKeys = apiKeys,
        toggleTheme = toggleTheme,
        addToApiKeys = viewModel::addApiKey,
        removeFromApiKeys = viewModel::removeApiKey,
        setApiKey = viewModel::setApiKey,
        selectedApiKey = selectedApiKey,
        getSelectedApiKey = viewModel::getSelectedApiKey,
        onNavigateToLoginWebView = onNavigateToLoginView,
        onNavigateToRegisterWebView = onNavigateToRegisterWebView
    )

}