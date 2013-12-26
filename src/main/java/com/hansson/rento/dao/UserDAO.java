package com.hansson.rento.dao;

import com.hansson.rento.entities.User;

public interface UserDAO {

	User create(User user);

	User getUser(String username);

}
