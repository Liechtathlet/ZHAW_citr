package ch.zhaw.mdp.lhb.citr.service;

import java.util.List;

import ch.zhaw.mdp.lhb.citr.entity.Person;


public interface PersonService {
	public boolean save(Person person);
	public List<Person> getAll();
	public Person getById(int id);
	public boolean delete(Person person);
	public boolean update(Person person);
	public Person findPerson(Person person);
}
