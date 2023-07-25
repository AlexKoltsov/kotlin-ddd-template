package com.akolts.ddd.template.adapter.verification.code.provider

import com.akolts.ddd.template.domain.port.outbound.VerificationCodeProvider

class RandomVerificationCodeProvider(private val length: Int = 4) : VerificationCodeProvider {
    override fun invoke(): String {
        val code = (1..length)
            .map { (0..9).random().toString() }
            .joinToString(separator = "")
        if (code.length != length) throw IllegalStateException("Wrong verification code length. Expected: $length, but was: ${code.length}")
        return code
    }
}