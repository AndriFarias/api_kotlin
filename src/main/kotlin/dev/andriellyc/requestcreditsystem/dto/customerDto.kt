package dev.andriellyc.requestcreditsystem.dto

import dev.andriellyc.requestcreditsystem.entity.Address
import dev.andriellyc.requestcreditsystem.entity.Customer
import java.math.BigDecimal

data class customerDto(
    val firstName:String,
    val lastName: String,
    val cpf: String,
    val income: BigDecimal,
    val email: String,
    val password: String,
    val zipCode: String,
    val street: String
) {
    fun toEndity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )
    )
}
