package io.dodn.springboot.storage.db.core

import io.dodn.springboot.storage.db.CoreDbContextTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ExampleRepositoryIT(
    val exampleRepository: ExampleRepository,
) : CoreDbContextTest() {
    @Test
    fun testShouldBeSavedAndFound() {
        val saved = exampleRepository.save(ExampleEntity("SPRING_BOOT"))
        assertThat(saved.exampleColumn).isEqualTo("SPRING_BOOT")

        val found = exampleRepository.findById(saved.id!!).get()
        assertThat(found.exampleColumn).isEqualTo("SPRING_BOOT")
    }
}
