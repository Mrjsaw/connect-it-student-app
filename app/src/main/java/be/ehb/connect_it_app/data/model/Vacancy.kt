package be.ehb.connect_it_app.data.model

data class Vacancy(
    val id: Long = counter++,
    val name: String,
    val city: String
) {
    companion object {
        private var counter = 0L
    }
}
