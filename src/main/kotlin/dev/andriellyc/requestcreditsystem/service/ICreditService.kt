package dev.andriellyc.requestcreditsystem.service

import dev.andriellyc.requestcreditsystem.entity.Credit
import java.util.UUID

interface ICreditService {
    fun save(credit: Credit):Credit
    fun findAllByCustomer(CustomerId: Long): List<Credit>
    fun findByCreditCode(idCustomer:Long,creditCode: UUID): Credit
}