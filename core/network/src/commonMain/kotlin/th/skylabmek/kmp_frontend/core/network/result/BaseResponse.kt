package th.skylabmek.kmp_frontend.core.network.result

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val error: String? = null
)
