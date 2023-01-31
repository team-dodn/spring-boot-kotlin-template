package io.dodn.springboot.client.example

import io.dodn.springboot.client.example.model.ExampleClientResult

internal data class ExampleResponseDto(
    val exampleResponseValue: String
) {
    fun toResult(): ExampleClientResult {
        return ExampleClientResult(exampleResponseValue)
    }
}
