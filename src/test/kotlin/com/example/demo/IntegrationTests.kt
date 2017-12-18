package com.example.demo

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType.*
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.test.test
import java.time.LocalDateTime

class IntegrationTests {

    private val application = Application(8181)
    private val client = WebClient.create("http://localhost:8181")

    @BeforeAll
    fun beforeAll() {
        application.start()
    }

    @Test
    fun `Find all posts on JSON REST endpoint`() {
        client.get().uri("/api/posts")
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux<Post>()
                .test()
                .expectNextMatches { it.title == "Post one" && it.content == "content of Post one" && it.createdDate.isBefore(LocalDateTime.now()) }
                .expectNextMatches { it.title == "Post two" && it.content == "content of Post two" && it.createdDate.isBefore(LocalDateTime.now()) }
                .verifyComplete()
    }

    @Test
    fun `Find all posts on HTML page`() {
        client.get().uri("/")
                .accept(TEXT_HTML)
                .retrieve()
                .bodyToMono<String>()
                .test()
                .expectNextMatches { it.contains("Post one") && it.contains("Post two") }
                .verifyComplete()
    }

    @Test
    fun `Receive a stream of posts via stream json `() {
        client.get().uri("/api/posts")
                .accept(APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux<Post>()
                .test()
                .expectNextMatches { it.title == "Post one" && it.content == "content of Post one" && it.createdDate.isBefore(LocalDateTime.now()) }
                .expectNextMatches { it.title == "Post two" && it.content == "content of Post two" && it.createdDate.isBefore(LocalDateTime.now()) }
                .expectNextMatches { it.title == "Post one" && it.content == "content of Post one" && it.createdDate.isBefore(LocalDateTime.now()) }
                .expectNextMatches { it.title == "Post two" && it.content == "content of Post two" && it.createdDate.isBefore(LocalDateTime.now()) }
                .thenCancel()
                .verify()
    }

    @Test
    fun `Receive a stream of posts via SSE `() {
        client.get().uri("/api/posts")
                .accept(TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux<Post>()
                .test()
                .expectNextMatches { it.title == "Post one" && it.content == "content of Post one" && it.createdDate.isBefore(LocalDateTime.now()) }
                .expectNextMatches { it.title == "Post two" && it.content == "content of Post two" && it.createdDate.isBefore(LocalDateTime.now()) }
                .expectNextMatches { it.title == "Post one" && it.content == "content of Post one" && it.createdDate.isBefore(LocalDateTime.now()) }
                .expectNextMatches { it.title == "Post two" && it.content == "content of Post two" && it.createdDate.isBefore(LocalDateTime.now()) }
                .thenCancel()
                .verify()
    }

    @AfterAll
    fun afterAll() {
        application.stop()
    }
}