package com.go.pushback

data class PushBackTokenPost(val token: Token) {
    data class Token(val value: String)
}
