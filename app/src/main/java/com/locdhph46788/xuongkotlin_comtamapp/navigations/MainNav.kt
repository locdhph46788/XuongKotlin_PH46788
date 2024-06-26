package com.locdhph46788.xuongkotlin_comtamapp.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.locdhph46788.xuongkotlin_comtamapp.screens.LoginScreen
import com.locdhph46788.xuongkotlin_comtamapp.screens.OrderDetailScreen
import com.locdhph46788.xuongkotlin_comtamapp.screens.SignupScreen
import com.locdhph46788.xuongkotlin_comtamapp.screens.WelcomeScreen
import com.locdhph46788.xuongkotlin_comtamapp.viewmodels.AuthViewModel

enum class ROUTE_MAIN_NAV {
    WELCOME,
    LOGIN,
    SIGNUP,
    HOME,
}

@Composable
fun AppNavHost(viewModel: AuthViewModel) {
    var navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ROUTE_MAIN_NAV.WELCOME.name,
    ) {
        composable(ROUTE_MAIN_NAV.WELCOME.name) { WelcomeScreen(navController) }
        composable(ROUTE_MAIN_NAV.LOGIN.name) { LoginScreen(navController, viewModel) }
        composable(ROUTE_MAIN_NAV.SIGNUP.name) { SignupScreen(navController, viewModel) }
        composable(ROUTE_MAIN_NAV.HOME.name) { BottomNavHost(navController) }


    }

}

