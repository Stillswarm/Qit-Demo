package com.example.repository

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object QueueColumns : IntIdTable("queue_columns") {
    val title = varchar("title", 100)
    val maxLimit = integer("max_limit")
    val customers = reference("customers", Customers)
}

class QueueColumnsEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object: IntEntityClass<QueueColumnsEntity>(QueueColumns)

    var title by QueueColumns.title
    var maxLimit by QueueColumns.maxLimit
    var customers by CustomerEntity via QueueCustomers
    var event by EventEntity referencedOn Events._columns
}

class EventColumnsRepository {
}