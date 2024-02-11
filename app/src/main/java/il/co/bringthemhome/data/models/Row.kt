package il.co.bringthemhome.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rows")
data class Row(
    val article: String,
    val b: String,
    val c: String,
    val d: String,
    val e: String,
    val f: String,
    val group: String,
    val h: String,
    val i: String,
    val j: String,
    val l: String,
    val status: String,
    val video: String,

    @PrimaryKey
    val id: String,
    )