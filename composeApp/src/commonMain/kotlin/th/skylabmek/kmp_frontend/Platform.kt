package th.skylabmek.kmp_frontend

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform