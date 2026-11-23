package se.yrgo.inl2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.yrgo.inl2.entity.Customer;
import se.yrgo.inl2.entity.Vehicle;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findFirstByName(String name);

    @Query("SELECT c.vehicles FROM Customer c WHERE c.id = :id")
    List<Vehicle> findAllVehiclesBelongingToCustomer(@Param("id") Long id);

    @Query("SELECT DISTINCT c FROM Customer c LEFT JOIN FETCH c.vehicles")
    List<Customer> findAllCustomersAndTheirVehicles();



}
