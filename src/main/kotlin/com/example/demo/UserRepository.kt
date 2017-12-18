package com.example.demo

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Query.query

class UserRepository(val template: ReactiveMongoTemplate) {

    fun save(user: User) = template.save(user)

    fun deleteAll() = template.remove(Query(), User::class.java)

    fun findByUsername(username: String) = template.findOne(query(where("username").`is`(username)), User::
    class.java)
}