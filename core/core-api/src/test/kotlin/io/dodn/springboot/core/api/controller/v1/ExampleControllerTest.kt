package io.dodn.springboot.core.api.controller.v1

import io.dodn.springboot.core.api.controller.v1.request.ExampleRequestDto
import io.dodn.springboot.core.api.domain.ExampleResult
import io.dodn.springboot.core.api.domain.ExampleService
import io.dodn.springboot.test.api.RestDocsTest
import io.dodn.springboot.test.api.RestDocsUtils.requestPreprocessor
import io.dodn.springboot.test.api.RestDocsUtils.responsePreprocessor
import io.mockk.every
import io.mockk.mockk
import io.restassured.http.ContentType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.queryParameters

class ExampleControllerTest : RestDocsTest() {
    private lateinit var exampleService: ExampleService
    private lateinit var controller: ExampleController

    @BeforeEach
    fun setUp() {
        exampleService = mockk()
        controller = ExampleController(exampleService)
        mockMvc = mockController(controller)
    }

    @Test
    fun exampleGet() {
        every { exampleService.processExample(any()) } returns ExampleResult("BYE")

        given()
            .contentType(ContentType.JSON)
            .queryParam("exampleParam", "HELLO_PARAM")
            .get("/get/{exampleValue}", "HELLO_PATH")
            .then()
            .status(HttpStatus.OK)
            .apply(
                document(
                    "exampleGet",
                    requestPreprocessor(),
                    responsePreprocessor(),
                    RequestDocumentation.pathParameters(
                        parameterWithName("exampleValue").description("ExampleValue"),
                    ),
                    queryParameters(
                        parameterWithName("exampleParam").description("ExampleParam"),
                    ),
                    responseFields(
                        fieldWithPath("result").type(JsonFieldType.STRING).description("ResultType"),
                        fieldWithPath("data.result").type(JsonFieldType.STRING).description("Result Date"),
                        fieldWithPath("error").type(JsonFieldType.NULL).ignored(),
                    ),
                ),
            )
    }

    @Test
    fun examplePost() {
        every { exampleService.processExample(any()) } returns ExampleResult("BYE")

        given()
            .contentType(ContentType.JSON)
            .body(ExampleRequestDto("HELLO_BODY"))
            .post("/post")
            .then()
            .status(HttpStatus.OK)
            .apply(
                document(
                    "examplePost",
                    requestPreprocessor(),
                    responsePreprocessor(),
                    requestFields(
                        fieldWithPath("data").type(JsonFieldType.STRING).description("ExampleBody Data Field"),
                    ),
                    responseFields(
                        fieldWithPath("result").type(JsonFieldType.STRING).description("ResultType"),
                        fieldWithPath("data.result").type(JsonFieldType.STRING).description("Result Date"),
                        fieldWithPath("error").type(JsonFieldType.STRING).ignored(),
                    ),
                ),
            )
    }
}
