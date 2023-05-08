package dev.andriellyc.requestcreditsystem.service

import dev.andriellyc.requestcreditsystem.entity.Address
import dev.andriellyc.requestcreditsystem.entity.Credit
import dev.andriellyc.requestcreditsystem.entity.Customer
import dev.andriellyc.requestcreditsystem.repository.CreditRepository
import dev.andriellyc.requestcreditsystem.service.impl.CreditService
import io.mockk.MockKStubScope
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CreditServiceTest {
    @MockK lateinit var creditRepository: CreditRepository
    @InjectMockKs lateinit var creditService: CreditService

    @Test
    fun `should create a credit`(){
        val fakeCustomer: Customer = buildCustomer()
        val fakeCredit: Credit = buildCredit(customer = fakeCustomer)
        every { creditRepository.save(any()) } returns fakeCredit

        val actual: Credit = creditService.save(fakeCredit)

        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCredit)
        verify(exactly = 1) { creditRepository.save(fakeCredit) }
    }
    
    @Test
    fun `should find all by id customers`(){
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { creditRepository.findAllByCustomerId(fakeId) } returns Optional.of(fakeCustomer)

        val actual: List<Credit> = creditService.findAllByCustomer(fakeId)

        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        verify(exactly = 1) { creditRepository.findById(fakeId) }
    }

    @Test
    fun `should find credit by credit code`(){
        val creditCodeFake = UUID.fromString("aa547c0f-9a6a-451f-8c89-afddce916a29")
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        val fakeCredit: Credit = buildCredit(customer = fakeCustomer, creditCode = creditCodeFake)
        every { creditRepository.findByCreditCode(creditCodeFake) } returns Optional.of(fakeCredit)

        val actual: Credit = creditService.findByCreditCode(fakeId,creditCodeFake)

        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCredit)
        verify(exactly = 1) { creditRepository.findById(fakeId) }
    }
    private fun buildCredit(
        creditValue: BigDecimal = BigDecimal.valueOf(500.0),
        dayFirstInstallment: LocalDate = LocalDate.of(2023, Month.APRIL, 22),
        numberOfInstallments: Int = 5,
        customer: Customer,
        creditCode: UUID = UUID.randomUUID()
    ): Credit = Credit(
        creditValue = creditValue,
        dayFirstInstallment = dayFirstInstallment,
        numberOfInstallments = numberOfInstallments,
        customer = customer,
        creditCode = creditCode
    )
    private fun buildCustomer(
        firstName: String = "Andri",
        lastName: String = "Rocha",
        cpf: String = "3343454677556",
        email: String = "andri@gmail.com",
        password: String = "12345",
        zipCode: String = "12345",
        street: String = "Rua da Nevoa",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
        id: Long = 1L
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipCode = zipCode,
            street = street,
        ),
        income = income,
        id = id
    )
}

private infix fun <T, B> MockKStubScope<T, B>.returns(of: Optional<Credit>) {

}

private infix fun <T, B> MockKStubScope<T, B>.returns(of: Optional<Customer>) {

}


