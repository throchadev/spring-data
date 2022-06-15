package br.com.throchadev.springdata.repository;

import br.com.throchadev.springdata.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

  Person findByName(String name);

  @Query("select name from Person where age = :age")
  String searchByAge(int age);

  List<Person> findByNameLike(String nameLike);

  List<Person> findByBirthBetween(Date startDate, Date endDate);
}
