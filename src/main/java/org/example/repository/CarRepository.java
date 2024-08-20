package org.example.repository;

import org.example.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {

    @Query(value = "select SUM(p.price)\n" +
            "from car as c\n" +
            "join car_parts as cp on c.id=cp.car_id\n" +
            "join part as p on cp.parts_id=p.id\n" +
            "where c.owner_id= :ownerId", nativeQuery = true)
    int findSumOfUserCarsParts(@Param("ownerId") UUID ownerId);
}
