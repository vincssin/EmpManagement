package com.employe.api.mongo.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import com.employe.api.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId>{

}
