package com.akolts.ddd.template.domain.usecase

import com.akolts.ddd.template.domain.UserByEmailAlreadyExistsException
import com.akolts.ddd.template.domain.UserByPhoneAlreadyExistsException
import com.akolts.ddd.template.domain.model.User
import com.akolts.ddd.template.domain.model.VerificationCode
import com.akolts.ddd.template.domain.port.inboud.EmailUserRegistrationCommand
import com.akolts.ddd.template.domain.port.inboud.PhoneUserRegistrationCommand
import com.akolts.ddd.template.domain.port.inboud.UserRegistrationUseCase
import com.akolts.ddd.template.domain.port.outbound.*

class UserRegistrationUseCaseImpl(
    private val userRepository: UserRepository,
    private val verificationCodeRepository: VerificationCodeRepository,
    private val passwordEncoder: PasswordEncoder,
    private val notifier: Notifier,
    private val verificationCodeProvider: VerificationCodeProvider,
) : UserRegistrationUseCase {

    override fun registerByEmail(command: EmailUserRegistrationCommand) {
        if (userRepository.existsByEmail(command.email)) throw UserByEmailAlreadyExistsException(command.email)
        val user = with(command) {
            User(
                email = email,
                phone = null,
                password = passwordEncoder.encode(password),
                firstName = firstName,
                lastName = lastName,
            )
        }
            .also { userRepository.save(it) }
        val code = verificationCodeProvider()
            .also {
                val verificationCode = VerificationCode(userId = user.id, code = it)
                verificationCodeRepository.save(verificationCode)
            }
        notifier.notify(NotificationMsgFactory.userRegisteredByEmail(email = user.email!!, verificationCode = code))
    }

    override fun registerByPhone(command: PhoneUserRegistrationCommand) {
        if (userRepository.existsByPhone(command.phone)) throw UserByPhoneAlreadyExistsException(command.phone)
        val user = with(command) {
            User(
                email = null,
                phone = phone,
                password = passwordEncoder.encode(password),
                firstName = firstName,
                lastName = lastName,
            )
        }
            .also { userRepository.save(it) }
        val code = verificationCodeProvider()
            .also {
                val verificationCode = VerificationCode(userId = user.id, code = it)
                verificationCodeRepository.save(verificationCode)
            }
        notifier.notify(NotificationMsgFactory.userRegisteredByPhone(phone = user.phone!!, verificationCode = code))
    }
}