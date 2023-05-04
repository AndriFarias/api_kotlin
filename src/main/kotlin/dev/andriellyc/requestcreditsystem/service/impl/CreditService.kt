package dev.andriellyc.requestcreditsystem.service.impl

import dev.andriellyc.requestcreditsystem.entity.Credit
import dev.andriellyc.requestcreditsystem.exception.BusinessException
import dev.andriellyc.requestcreditsystem.repository.CreditRepository
import dev.andriellyc.requestcreditsystem.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
) : ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> =
        this.creditRepository.findAllByCustomerId(customerId)

    override fun findByCreditCode(idCustomer: Long, creditCode: UUID): Credit {
        val credit:Credit = (this.creditRepository.findByCreditCode(creditCode)
            ?: throw BusinessException("Creditcode $creditCode not found"))
        return if (credit.customer?.id == idCustomer) credit else throw IllegalArgumentException("Contact admin")
    }
}