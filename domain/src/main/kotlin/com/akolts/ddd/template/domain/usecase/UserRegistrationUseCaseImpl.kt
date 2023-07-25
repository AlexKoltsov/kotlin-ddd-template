package com.akolts.ddd.template.domain.usecase

import com.akolts.ddd.template.domain.model.User
import com.akolts.ddd.template.domain.port.inboud.EmailUserRegistrationCommand
import com.akolts.ddd.template.domain.port.inboud.PhoneUserRegistrationCommand
import com.akolts.ddd.template.domain.port.inboud.UserRegistrationUseCase
import com.akolts.ddd.template.domain.port.outbound.NotificationMsgFactory
import com.akolts.ddd.template.domain.port.outbound.Notifier
import com.akolts.ddd.template.domain.port.outbound.PasswordEncoder
import com.akolts.ddd.template.domain.port.outbound.UserRepository

class UserRegistrationUseCaseImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val notifier: Notifier,
) : UserRegistrationUseCase {

    override fun registerByEmail(command: EmailUserRegistrationCommand) {
        val user = with(command) {
            User(
                email = email,
                phone = null,
                password = passwordEncoder.encode(password),
                firstName = firstName,
                lastName = lastName,
            )
        }
        notifier.notify(NotificationMsgFactory.userRegisteredByEmail(user.email!!))
        userRepository.save(user)
    }

    override fun registerByPhone(command: PhoneUserRegistrationCommand) {
        val user = with(command) {
            User(
                email = null,
                phone = phone,
                password = passwordEncoder.encode(password),
                firstName = firstName,
                lastName = lastName,
            )
        }
        notifier.notify(NotificationMsgFactory.userRegisteredByPhone(user.phone!!))
        userRepository.save(user)
    }
}