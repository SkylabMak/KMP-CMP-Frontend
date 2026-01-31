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

    /**
     * The base URL for API calls, automatically resolved based on the current platform 
     * and environment (Dev/Prod).
     */
    val apiBaseUrl: String
        get() = if (IS_PRODUCTION) {
            "https://api.skylabmek.net/"
        } else {
            // Android emulator needs a special IP to access localhost of the host machine
            if (currentPlatform == "Android") "http://10.0.2.2:3000"
            else "http://localhost:3000"
        }

    /**
     * Whether the app is running in debug mode.
     */
    val isDebug: Boolean get() = !IS_PRODUCTION

    object Features {
        const val ENABLE_ANALYTICS = true
        const val ENABLE_LOGGING = true
    }
}
