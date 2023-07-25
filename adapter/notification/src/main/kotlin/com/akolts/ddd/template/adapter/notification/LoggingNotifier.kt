package com.akolts.ddd.template.adapter.notification

import com.akolts.ddd.template.domain.port.outbound.NotificationMsg
import com.akolts.ddd.template.domain.port.outbound.Notifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LoggingNotifier : Notifier {

    private val logger: Logger = LoggerFactory.getLogger(LoggingNotifier::class.java)

    override fun notify(msg: NotificationMsg) {
        logger.info(msg.toString())
    }
}