package local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jokes")
data class JokeLocalDto(
    @PrimaryKey val id: Int,
    val type: String,
    val setup: String,
    val punchline: String
)