package com.forest.usecase.identity.persistence;

import java.util.List;

import com.forest.model.Group;

public interface GroupPersistence {

	public void createGroup(Group group);

	public void updateGroup(Group group);

	public void removeGroup(Group group);

	public Group getGroup(int id);

	public Group findGroupByName(String name);

	public int count();

	public List<Group> findAll();

	public List<Group> findRange(int... range);

}
