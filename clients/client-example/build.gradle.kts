dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("io.github.openfeign:feign-hc5")
    implementation("io.github.openfeign:feign-micrometer")

    testImplementation("org.springframework.boot:spring-boot-http-converter")
}
