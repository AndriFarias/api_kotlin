package dev.andriellyc.requestcreditsystem.service

import dev.andriellyc.requestcreditsystem.entity.Customer

interface ICustomerService {
    fun save(customer: Customer):Customer
    fun findById(id: Long):Customer
    fun delete(id:Long)
}