package com.example.repository

import com.example.model.User
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object Users : IntIdTable("users") {
    val email = varchar("email", 100)
    val password = varchar("password", 500)
    val phoneNo = long("phone_no")
    val business = reference("business", Businesses).uniqueIndex().nullable()
    val customer = reference("customer", Customers).uniqueIndex().nullable()
}

class UserEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserEntity>(Users)

    var email by Users.email
    var password by Users.password
    var phoneNo by Users.phoneNo
    var business by BusinessEntity optionalReferencedOn Users.business
    var customer by CustomerEntity optionalReferencedOn Users.customer
}

fun UserEntity.mapToUser(): User {
    return User(
        email = this.email,
        password = this.password,
        phoneNo = this.phoneNo,
        businessId = this.business?.id?.value,
        customerId = this.customer?.id?.value,
    )
}

class UserRepository {

    suspend fun createUser(user: User): Int = dbQuery {
        UserEntity.new {
            this.email = user.email
            this.password = user.password
            this.phoneNo = user.phoneNo
        }
    }.id.value

    suspend fun deleteUser(userId: Int) = dbQuery {
        UserEntity.findById(userId)?.delete()
    }

    suspend fun getUserDetails(userId: Int): User? = dbQuery {
        UserEntity.findById(userId)?.mapToUser()
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}