package com.namsaeng.sociallogin.entities

enum class MessageType {
    CHAT, JOIN, LEAVE
}
class ChatMessage (
        var type: MessageType,
        var content: String = "",
        var sender: String
) {

}