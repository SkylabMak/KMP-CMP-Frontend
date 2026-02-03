package th.skylabmek.kmp_frontend.ui.components.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import th.skylabmek.kmp_frontend.core.common.UiState
import th.skylabmek.kmp_frontend.ui.dimens.Dimens

@Composable
fun <T> AppUiStateHandler(
    uiState: UiState<T>,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    loadingContent: @Composable () -> Unit = { DefaultLoadingContent() },
    errorContent: @Composable (onRetry: () -> Unit) -> Unit = { DefaultErrorContent(onRetry) },
    successContent: @Composable (data: T) -> Unit
) {
    Box(modifier = modifier) {
        when (uiState) {
            is UiState.Loading -> loadingContent()
            is UiState.Error -> errorContent(onRetry)
            is UiState.Success -> successContent(uiState.data)
        }
    }
}

@Composable
private fun DefaultLoadingContent() {
    Box(modifier = Modifier.fillMaxWidth().height(Dimens.containerMaxWidth / 15), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun DefaultErrorContent(onRetry: () -> Unit) {
    Button(onClick = onRetry) {
        Text("Retry")
    }
}
