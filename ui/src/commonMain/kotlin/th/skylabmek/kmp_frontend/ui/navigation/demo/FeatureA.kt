package th.skylabmek.kmp_frontend.ui.navigation.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.EntryProviderScope
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.PolymorphicModuleBuilder
import th.skylabmek.kmp_frontend.navigation.tools.FeatureNavProvider
import th.skylabmek.kmp_frontend.navigation.tools.NavKey
import th.skylabmek.kmp_frontend.navigation.tools.NavigatorAccessor as Navigator

@Serializable
sealed class FeatureANavKey : NavKey {
    @Serializable
    data object Page1 : FeatureANavKey()
    @Serializable
    data object Page2 : FeatureANavKey()
    @Serializable
    data object Page3 : FeatureANavKey()
    @Serializable
    data object Page4 : FeatureANavKey()
}

class FeatureANavProvider : FeatureNavProvider {
    override fun EntryProviderScope<NavKey>.navigationBuilder() {
        entry<FeatureANavKey.Page1> { Page1Screen() }
        entry<FeatureANavKey.Page2> { Page2Screen() }
        entry<FeatureANavKey.Page3> { Page3Screen() }
        entry<FeatureANavKey.Page4> { Page4Screen() }
    }

    override fun registerSerializers(polymorphicModuleBuilder: PolymorphicModuleBuilder<NavKey>) {
        polymorphicModuleBuilder.subclass(FeatureANavKey.Page1::class, FeatureANavKey.Page1.serializer())
        polymorphicModuleBuilder.subclass(FeatureANavKey.Page2::class, FeatureANavKey.Page2.serializer())
        polymorphicModuleBuilder.subclass(FeatureANavKey.Page3::class, FeatureANavKey.Page3.serializer())
        polymorphicModuleBuilder.subclass(FeatureANavKey.Page4::class, FeatureANavKey.Page4.serializer())
    }

    override fun mapUriToNavKey(uri: String): NavKey? {
        return when {
            uri.contains("/page1") -> FeatureANavKey.Page1
            uri.contains("/page2") -> FeatureANavKey.Page2
            uri.contains("/page3") -> FeatureANavKey.Page3
            uri.contains("/page4") -> FeatureANavKey.Page4
            else -> null
        }
    }
}

@Composable
fun Page1Screen() {
    val navigator = Navigator.current
    Scaffold { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Text("Page 1: Welcome", style = MaterialTheme.typography.headlineMedium)
            Button(onClick = { navigator.navigate(FeatureANavKey.Page2) }) {
                Text("Go to Page 2")
            }
            Button(onClick = { navigator.navigate(FeatureANavKey.Page3) }) {
                Text("Go to Page 3")
            }
            Button(onClick = { navigator.navigate(FeatureANavKey.Page4) }) {
                Text("Go to Page 4")
            }
        }
    }
}

@Composable
fun Page2Screen() {
    val navigator = Navigator.current
    Scaffold { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Text("Page 2: Details", style = MaterialTheme.typography.headlineMedium)
            Button(onClick = { navigator.navigate(FeatureANavKey.Page3) }) {
                Text("Go to Page 3")
            }
            Button(onClick = { navigator.back() }) {
                Text("Back")
            }
        }
    }
}

@Composable
fun Page3Screen() {
    val navigator = Navigator.current
    Scaffold { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Text("Page 3: More Info", style = MaterialTheme.typography.headlineMedium)
            Button(onClick = { navigator.navigate(FeatureANavKey.Page4) }) {
                Text("Go to Page 4")
            }
            Button(onClick = { navigator.back() }) {
                Text("Back")
            }
        }
    }
}

@Composable
fun Page4Screen() {
    val navigator = Navigator.current
    Scaffold { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Text("Page 4: Settings", style = MaterialTheme.typography.headlineMedium)
            Button(onClick = { navigator.setRoot(FeatureANavKey.Page1) }) {
                Text("Reset to Page 1")
            }
            Button(onClick = { navigator.back() }) {
                Text("Back")
            }
        }
    }
}
