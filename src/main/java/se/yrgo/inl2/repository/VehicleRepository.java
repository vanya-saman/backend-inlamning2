package se.yrgo.inl2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.yrgo.inl2.entity.Vehicle;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAllByModel(String model);

    List<Vehicle> findAllByBrand(String brand);

    @Modifying
    @Query("UPDATE Vehicle v SET v.brand = :model WHERE v.id = :id")
    Vehicle updateBrandNameById(@Param("id") Long id, @Param("brand") String brand);

}
