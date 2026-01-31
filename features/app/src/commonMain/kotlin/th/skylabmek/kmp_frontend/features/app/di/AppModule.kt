package th.skylabmek.kmp_frontend.features.app.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import th.skylabmek.kmp_frontend.features.app.presentation.viewmodel.AppViewModel

val appFeatureModule = module {
    viewModelOf(::AppViewModel)
}
