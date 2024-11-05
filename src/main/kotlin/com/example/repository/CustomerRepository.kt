package com.example.repository

import com.example.model.Gender
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Customers : IntIdTable("customers") {
    val name = varchar("name", 50)
    val age = integer("age").check { it.between(0, 150) }
    val gender = enumeration<Gender>("gender")
}

class CustomerEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object: IntEntityClass<CustomerEntity>(Customers)

    var name by Customers.name
    var age by Customers.age
    var gender by Customers.gender
    var user by UserEntity optionalReferencedOn Users.customer
    var queues by QueueColumnsEntity via QueueColumns
}

class CustomerRepository {
}