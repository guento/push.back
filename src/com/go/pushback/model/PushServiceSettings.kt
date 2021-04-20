package com.go.pushback.model

data class PushServiceSettings(
    val id: String,
    val projectId: String,
    val name: String
)

fun testData(): List<PushServiceSettings> {
    return listOf(
        PushServiceSettings("1", "hms-test-push-service", "HMS Service"),
        PushServiceSettings("2", "fcm-test-push-service-1", "FCM Service 1"),
        PushServiceSettings("3", "fcm-test-push-service-2", "FCM Service 2"),
    )
}
