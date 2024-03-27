package mk.ukim.finki.prva_aud_veb.repository.jpa;

import mk.ukim.finki.prva_aud_veb.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
