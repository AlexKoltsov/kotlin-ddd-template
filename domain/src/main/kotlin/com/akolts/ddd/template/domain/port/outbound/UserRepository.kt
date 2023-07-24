package com.akolts.ddd.template.domain.port.outbound

import com.akolts.ddd.template.domain.model.User
import java.util.*

interface UserRepository : Repository<User, UUID> {
}