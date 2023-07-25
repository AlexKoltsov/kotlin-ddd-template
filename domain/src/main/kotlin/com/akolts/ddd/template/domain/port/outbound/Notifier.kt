package com.akolts.ddd.template.domain.port.outbound

import com.akolts.ddd.template.domain.port.outbound.NotificationTemplate.USER_REGISTERED_BY_EMAIL
import com.akolts.ddd.template.domain.port.outbound.NotificationTemplate.USER_REGISTERED_BY_PHONE
import com.akolts.ddd.template.domain.port.outbound.NotificationTemplatePayload.*

interface Notifier {
    fun notify(msg: NotificationMsg)
}

object NotificationMsgFactory {
    fun userRegisteredByEmail(email: String, verificationCode: String) = NotificationMsg(
        template = USER_REGISTERED_BY_EMAIL,
        payload = mapOf(
            EMAIL to email,
            VERIFICATION_CODE to verificationCode,
        )
    )

    fun userRegisteredByPhone(phone: String, verificationCode: String) = NotificationMsg(
        template = USER_REGISTERED_BY_PHONE,
        payload = mapOf(
            PHONE to phone,
            VERIFICATION_CODE to verificationCode,
        )
    )
}

data class NotificationMsg(
    val template: NotificationTemplate,
    val payload: Map<NotificationTemplatePayload, String>,
)

enum class NotificationTemplate {
    USER_REGISTERED_BY_EMAIL,
    USER_REGISTERED_BY_PHONE,
}

enum class NotificationTemplatePayload {
    EMAIL, PHONE, VERIFICATION_CODE
}