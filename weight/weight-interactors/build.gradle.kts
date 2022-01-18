apply {
    from("$rootDir/library-build.gradle")
}

dependencies {
    "implementation"(project(":core"))
    "implementation"(project(":weight:weight-datasource"))
    "implementation"(project(":weight:weight-domain"))

    "implementation"("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

}
