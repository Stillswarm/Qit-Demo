package com.example.repository

import com.example.model.Business
import com.example.model.EventCategory
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object Businesses : IntIdTable("businesses") {
    val name = varchar("name", 100)
    val address = varchar("address", 1000)
    val category = enumeration<EventCategory>("category")
    var currentEvents = reference("current_events", Events)
    var lastClosedEvent = reference("last_closed_event", Events)
}

class BusinessEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object: IntEntityClass<BusinessEntity>(Businesses)

    var name by Businesses.name
    var address by Businesses.address
    var category by Businesses.category
    var user by UserEntity optionalReferencedOn Users.business
    var currentEvents by EventEntity referencedOn Businesses.currentEvents
    var lastClosedEvent by EventEntity referencedOn Businesses.lastClosedEvent
}

class BusinessRepository {

    suspend fun registerBusiness(business: Business) {
        dbQuery {
            BusinessEntity.new {
                this.name = name
                this.address = address
                this.category = category
            }
        }
    }

    suspend fun deleteBusiness(business: Business) {

    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}