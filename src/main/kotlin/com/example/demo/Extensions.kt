package com.example.demo


fun String.slugify(): String = this.toLowerCase()
                .replace("\r\n".toRegex(), "")
                .replace("\n".toRegex(), "")
                .replace("\r".toRegex(), "")
                .replace("[\\s]+".toRegex(), "-")

