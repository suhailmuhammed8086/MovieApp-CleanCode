package com.app.moviesapp.network.model.response

import com.google.gson.annotations.SerializedName


data class CreditDetailsResponse (
    val id: Long,
    val castCrewModel: List<CastCrewModel>,
    val crew: List<CastCrewModel>
) {

data class CastCrewModel (
    val adult: Boolean,
    val gender: Long,
    val id: Long,

    @SerializedName("known_for_department")
    val knownForDepartment: Department,

    val name: String,

    @SerializedName("original_name")
    val originalName: String,

    val popularity: Double,

    @SerializedName("profile_path")
    val profilePath: String? = null,

    @SerializedName("cast_id")
    val castID: Long? = null,

    val character: String? = null,

    @SerializedName("credit_id")
    val creditID: String,

    val order: Long? = null,
    val department: Department? = null,
    val job: String? = null
)

enum class Department(val value: String) {
    Acting("Acting"),
    Art("Art"),
    Camera("Camera"),
    CostumeMakeUp("Costume & Make-Up"),
    Crew("Crew"),
    Directing("Directing"),
    Editing("Editing"),
    Lighting("Lighting"),
    Production("Production"),
    Sound("Sound"),
    VisualEffects("Visual Effects"),
    Writing("Writing");

    companion object {
        public fun fromValue(value: String): Department = when (value) {
            "Acting" -> Acting
            "Art" -> Art
            "Camera" -> Camera
            "Costume & Make-Up" -> CostumeMakeUp
            "Crew" -> Crew
            "Directing" -> Directing
            "Editing" -> Editing
            "Lighting" -> Lighting
            "Production" -> Production
            "Sound" -> Sound
            "Visual Effects" -> VisualEffects
            "Writing" -> Writing
            else -> throw IllegalArgumentException()
        }
    }
}
}
