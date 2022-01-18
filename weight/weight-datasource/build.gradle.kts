apply {
    from("$rootDir/library-build.gradle")
}
dependencies {
    "implementation"(project(":weight:weight-domain"))
}
