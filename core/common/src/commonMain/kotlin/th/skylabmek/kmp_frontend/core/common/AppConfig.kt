package th.skylabmek.kmp_frontend.core.common

/**
 * AppConfig serves as a high-level accessor for application configuration.
 * It combines static build-time constants from BuildKonfig with runtime
 * logic like platform-specific URL resolution.
 */
object AppConfig {
    val PROFILE_ID: String get() = AppBuildKonfig.PROFILE_ID
    val APP_ID: String get() = AppBuildKonfig.APP_ID
    val IS_PRODUCTION: Boolean get() = AppBuildKonfig.IS_PRODUCTION
    val IS_DEBUG: Boolean get() = AppBuildKonfig.IS_DEBUG

    /**
     * The base URL for API calls, automatically resolved based on the current platform 
     * and environment (Dev/Prod).
     */
    val apiBaseUrl: String get() = AppBuildKonfig.BASE_URL

    /**
     * Whether the app is running in debug mode.
     */
    val isDebug: Boolean get() = IS_DEBUG

    object Features {
        const val ENABLE_ANALYTICS = true
        val ENABLE_LOGGING: Boolean get() = IS_DEBUG
    }
}
