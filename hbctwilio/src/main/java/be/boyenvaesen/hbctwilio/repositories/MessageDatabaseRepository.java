package be.boyenvaesen.hbctwilio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.boyenvaesen.hbctwilio.models.DatabaseMessage;


@Repository
public interface MessageDatabaseRepository extends JpaRepository<DatabaseMessage, String> {

}
