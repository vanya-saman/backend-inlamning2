package se.yrgo.inl2.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.yrgo.inl2.dto.VehicleBrandProjection;
import se.yrgo.inl2.entity.Vehicle;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAllByModel(String model);

    List<Vehicle> findAllByBrand(String brand);

    @Transactional
    @Modifying
    @Query("UPDATE Vehicle v SET v.brand = :brand WHERE v.id = :id")
    int updateBrandNameById(@Param("id") Long id, @Param("brand") String brand);

    @Query("""
                SELECT v.registrationNumber AS registrationNumber,
                       v.brand AS brand
                FROM Vehicle v
            """)
    List<VehicleBrandProjection> findAllRegsAndBrands();

}
