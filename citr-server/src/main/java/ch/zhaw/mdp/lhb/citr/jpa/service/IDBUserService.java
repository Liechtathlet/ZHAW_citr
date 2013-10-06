package ch.zhaw.mdp.lhb.citr.jpa.service;

import java.util.List;

import ch.zhaw.mdp.lhb.citr.jpa.entity.UserDVO;


public interface IDBUserService {
	public boolean save(UserDVO person);
	public List<UserDVO> getAll();
	public UserDVO getById(int id);
	public boolean delete(UserDVO person);
	public boolean update(UserDVO person);
	public UserDVO findPerson(UserDVO person);
}
