package com.akolts.ddd.template.domain.port.outbound

interface PasswordEncoder {
    fun encode(password: String): String

}
