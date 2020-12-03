package com.go.pushback

data class PushBackTokenPost(val token: PushBackTokenPost.Token) {
    data class Token(val value: String)
}
