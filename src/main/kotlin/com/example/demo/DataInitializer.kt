package com.example.demo

import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import reactor.core.publisher.Flux
import javax.annotation.PostConstruct


class DataInitializr(val posts: PostRepository, val users: UserRepository, val passwordEncoder:PasswordEncoder ) {
    private val log = LoggerFactory.getLogger(DataInitializr::class.java);

    fun initData() {
        log.info("start data initialization ...")
        this.posts
                .deleteAll()
                .thenMany(
                        Flux
                                .just("Post one", "Post two")
                                .flatMap { it -> this.posts.save(Post(title = it, content = "content of " + it)) }
                )
                .log()
                .subscribe(
                        null,
                        null,
                        { log.info("done post initialization...") }
                )

        this.users
                .deleteAll()
                .thenMany(
                        Flux
                                .just("user", "admin")
                                .flatMap { it ->
                                    if (it == "user") this.users.save(User(username = it, password = "password", roles = listOf("ROLE_USER")))
                                    else this.users.save(User(username = it, password = passwordEncoder.encode("password"), roles = listOf("ROLE_USER", "ROLE_ADMIN")))
                                }
                )
                .log()
                .subscribe(
                        null,
                        null,
                        { log.info("done users initialization...") }
                )
    }
}