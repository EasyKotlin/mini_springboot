package jason.chen.mini_springboot.restful.service

import jason.chen.mini_springboot.restful.entity.Customer
import org.springframework.data.repository.CrudRepository

interface CustomerService : CrudRepository<Customer, Long> {

    fun findByLastName(lastName: String): Iterable<Customer>
}
