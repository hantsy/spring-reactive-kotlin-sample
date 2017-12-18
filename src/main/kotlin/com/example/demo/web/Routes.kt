package com.example.demo.web

import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

class Routes(val postHandler: PostHandler) {
    val log = LoggerFactory.getLogger(Routes::class.java);

    fun router() = router {
        accept(MediaType.TEXT_HTML).nest {
            GET("/", postHandler::allView)
            //GET("/sse") { ServerResponse.ok().render("sse") }
            //GET("/users", postHandler::findAllView)
        }
        "/api".nest {
            "/posts".nest {
                accept(MediaType.APPLICATION_JSON).nest {
                    GET("", postHandler::all)
                    GET("/{id}", postHandler::get)
                }
                accept(MediaType.APPLICATION_STREAM_JSON).nest {
                    GET("", postHandler::stream)
                }
                accept(MediaType.TEXT_EVENT_STREAM).nest {
                    GET("", postHandler::sse)
                }
                POST("", postHandler::create)
                PUT("{id}", postHandler::update)
                DELETE("/{id}", postHandler::delete)
            }
        }
        resources("/**", ClassPathResource("static/"))
    }
//            .filter { request, next ->
//        next.handle(request).flatMap{
//            if(it.headers().accept==null) it.headers().accept = listOf(MediaType.APPLICATION_JSON)
//            if(it.headers().contentType==null) it.headers().contentType = MediaType.APPLICATION_JSON
//            it.toMono()
//        }
//    }

//            .filter { request, next ->
//        next.handle(request).flatMap {
//            //if (it is RenderingResponse) RenderingResponse.from(it).modelAttributes(attributes(request.locale(), messageSource)).build() else it.toMono()
//        }
//    }

//    private fun attributes(locale: Locale, messageSource: MessageSource) = mutableMapOf<String, Any>(
//            "i18n" to Mustache.Lambda { frag, out ->
//                val tokens = frag.execute().split("|")
//                out.write(messageSource.getMessage(tokens[0], tokens.slice(IntRange(1, tokens.size - 1)).toTypedArray(), locale)) }
//    )


}