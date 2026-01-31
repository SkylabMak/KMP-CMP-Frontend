package th.skylabmek.kmp_frontend.di.modules

import org.koin.dsl.module
import th.skylabmek.kmp_frontend.domain.di.domainModuleModules

val repositoryModule = module {
    includes(domainModuleModules)
}
