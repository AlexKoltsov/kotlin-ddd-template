package com.akolts.ddd.template.domain.port.outbound

import com.akolts.ddd.template.domain.User
import java.util.*

interface UserRepository : Repository<User, UUID> {
}