package sid.example.bankclientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sid.example.bankclientservice.entities.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
