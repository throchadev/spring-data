package br.com.throchadev.springdata;

import br.com.throchadev.springdata.model.Person;
import br.com.throchadev.springdata.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@Log4j2
public class SpringDataApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Autowired
	private PersonRepository personRepository;

	@Override
	public void run(String... args) throws Exception {

		Person person = new Person();
		person.setName("Thiago");
		person.setAge(15);
		person.setBirth(new Date());
		log.info("saving the first person");
		personRepository.save(person);

		Person person2 = new Person();
		person2.setName("Maria");
		person2.setAge(16);
		person2.setBirth(new Date());
		log.info("saving the second person");
		personRepository.save(person2);

		log.info("returning all people");
		List<Person> personList = personRepository.findAll();
		log.info(personList);

		log.info("querying a person by id");
		Optional<Person> personById = personRepository.findById(personList.get(0).getId());
		log.info(personById);

		log.info("querying a person by name");
		Optional<Person> personByName = Optional.ofNullable(personRepository.findByName(personList.get(0).getName()));
		log.info(personByName);

		log.info("searching name by age");
		String name = personRepository.searchByAge(personList.get(0).getAge());
		log.info(name);

		log.info("searching name by like");
		List<Person> nameLike = personRepository.findByNameLike("Th%");
		log.info(nameLike);

		log.info("searching name by date");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateInString = "2022-06-14";
		Date startDate = formatter.parse(dateInString);
		String dateInString2 = "2022-06-16";
		Date endDate = formatter.parse(dateInString2);
		List<Person> nameBirthBetween = personRepository.findByBirthBetween(startDate, endDate);
		log.info(nameBirthBetween);

	}

}
