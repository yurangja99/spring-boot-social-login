package com.namsaeng.sociallogin.services

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.IOException
import java.io.InputStream
import javax.annotation.PostConstruct


@Service
class FCMInitializer {
    private val log: Log = LogFactory.getLog("FCMInitializerLogger")

    @PostConstruct
    fun initialize() {
        try {
            // firebase 초기화. 한 번만 해야 오류가 나지 않음
            val serviceAccount: InputStream = ClassPathResource("valid-amplifier-289205-firebase-adminsdk-bq7tc-26c30c9c21.json")
                    .inputStream
            val options: FirebaseOptions = FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://valid-amplifier-289205.firebaseio.com")
                    .build()
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options)
                log.info("Firebase application has been initialized")
            }
        } catch (e: IOException) {
            log.error(e.message)
        }
    }
}