package dev.andriellyc.requestcreditsystem.dto

import dev.andriellyc.requestcreditsystem.entity.Address
import dev.andriellyc.requestcreditsystem.entity.Customer
import java.math.BigDecimal

data class CustomerUpdateDto(
    val firstName:String,
    val lastName: String,
    val income: BigDecimal,
    val zipCode: String,
    val street: String
) {

    fun toEndity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.zipCode = this.zipCode
        customer.address.street = this.street
        return customer
    }
}
