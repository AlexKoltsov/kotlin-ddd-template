package com.akolts.ddd.template.domain.port.inboud

import java.util.*

interface UserLogoutUseCase {
    fun logout(id: UUID)
}