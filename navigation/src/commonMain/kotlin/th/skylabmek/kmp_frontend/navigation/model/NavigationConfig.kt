package th.skylabmek.kmp_frontend.navigation.model

import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import th.skylabmek.kmp_frontend.navigation.tools.FeatureNavProvider
import th.skylabmek.kmp_frontend.navigation.tools.NavKey

/**
 * Creates a [SavedStateConfiguration] by aggregating serializers from all provided [FeatureNavProvider]s.
 * This allows the navigation backstack to be serialized and restored (e.g., after process death).
 */
fun createNavigationConfig(providers: List<FeatureNavProvider>): SavedStateConfiguration {
    return SavedStateConfiguration {
        serializersModule = SerializersModule {
            polymorphic(NavKey::class) {
                providers.forEach { it.registerSerializers(this) }
            }
        }
    }
}
