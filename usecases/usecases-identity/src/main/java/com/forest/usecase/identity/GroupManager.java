package com.forest.usecase.identity;

import java.util.List;

import com.forest.model.Group;

public interface GroupManager {

	public abstract int count();

	public abstract List<Group> getAll();

	public abstract List<Group> getAllInRange(int... range);

	public abstract void createGroup(Group group);

	public abstract void updateGroup(Group group);

	public abstract void removeGroup(Group group);

	public abstract Group getGroup(Integer id);

}