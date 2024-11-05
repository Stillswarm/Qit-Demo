package com.example.repository

import com.example.model.EventCategory
import com.example.repository.CustomerEntity.Companion.referrersOn
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Events : IntIdTable("events") {
    val openingTime = long("opening_time")
    val closingTime = long("closing_time")
    val _columns = reference("columns", QueueColumns)
    val category = enumeration<EventCategory>("category")
    val title = varchar("title", 50)
    val description = text("description")
    val avgWaitTime = integer("wait_time")
}

class EventEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object: IntEntityClass<EventEntity>(Events)

    val openingTime by Events.openingTime
    val closingTime by Events.closingTime
    val columns by QueueColumnsEntity referrersOn Events._columns
    var category by Events.category
    var title by Events.title
    var description by Events.description
    var avgWaitTime by Events.avgWaitTime
    val business by BusinessEntity referencedOn Businesses.currentEvents
}

class EventsRepository {
}