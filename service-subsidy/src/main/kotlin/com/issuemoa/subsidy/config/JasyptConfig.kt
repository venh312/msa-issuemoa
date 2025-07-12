package com.issuemoa.subsidy.config

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JasyptConfig {
    @Value("\${Jasypt.key}")
    private lateinit var key: String

    @Bean("jasyptStringEncryptor")
    fun stringEncryptor() =
        PooledPBEStringEncryptor()
        .apply {
            setConfig(SimpleStringPBEConfig().also {
                it.poolSize = 1
                it.password = key
                it.stringOutputType = "base64"
                it.keyObtentionIterations = 1000
                it.providerName = "SunJCE"
                it.algorithm = "PBEWithMD5AndDES"
                it.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator")
            })
        }
}
