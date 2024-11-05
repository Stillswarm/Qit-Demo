package com.example.repository

import org.jetbrains.exposed.sql.Table

object QueueCustomers : Table() {
    val queueColumn = reference("queue_column", QueueColumns)
    val customer = reference("customer", Customers)

    init {
        uniqueIndex(queueColumn, customer)
    }

    override val primaryKey = PrimaryKey(queueColumn, customer)
}