package th.skylabmek.kmp_frontend.core.network.di

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named

sealed class NetworkQualifier(val qualifier: Qualifier) {
    data object Default : NetworkQualifier(named("network-default"))
    data object Logged : NetworkQualifier(named("network-logged"))
    data object Stateless : NetworkQualifier(named("network-stateless"))
    data object Authenticated : NetworkQualifier(named("network-Authenticated"))
    data object Anonymous : NetworkQualifier(named("network-Anonymous"))
}