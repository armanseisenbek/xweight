plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.onepercent.xweight"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.onepercent.xweight"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0-beta"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":constants"))
    implementation(project(":weight:weight-domain"))
    implementation(project(":weight:weight-datasource"))
    implementation(project(":weight:weight-interactors"))

//    implementation("androidx.core:core-ktx:1.7.0")
//    implementation("androidx.appcompat:appcompat:1.3.1")
//    implementation("com.google.android.material:material:1.4.0")
//
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")

    // General
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.startup)
    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.collections.immutable)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Pluto
    debugImplementation(libs.pluto)
    debugImplementation(libs.pluto.plugins)
    releaseImplementation(libs.pluto.no.op)
    releaseImplementation(libs.pluto.plugins.no.op)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    testImplementation(libs.androidx.room.testing)
    ksp(libs.androidx.room.compiler)

    implementation(libs.androidx.datastore.preferences)

    // Timber
    implementation(libs.timber)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

//    implementation("androidx.compose.ui:ui:$compose_version")
//    implementation("androidx.compose.material:material:$compose_version")
//    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
//    implementation("androidx.compose.ui:ui-tooling:$compose_version")

//    implementation("androidx.compose.material:material-icons-extended:$compose_version")

//    implementation("androidx.navigation:navigation-compose:2.4.0-beta02")
//    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-beta01")
//
//    // navigation animation
//    val nav_animation_version = "0.22.0-rc"
//    implementation("com.google.accompanist:accompanist-navigation-animation:$nav_animation_version")
//
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
//    implementation("androidx.activity:activity-compose:1.4.0")
//
//    // Room
//    val room_version = "2.3.0"
//    implementation("androidx.room:room-runtime:$room_version")
//    kapt("androidx.room:room-compiler:$room_version")
//    implementation("androidx.room:room-ktx:$room_version")
//
//    // Kotlin Coroutines
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
//
//    // Hilt
//    val hilt_version = "2.37"
//    implementation("com.google.dagger:hilt-android:$hilt_version")
//    kapt("com.google.dagger:hilt-compiler:$hilt_version")
//
//
//    testImplementation("junit:junit:4.+")
//    androidTestImplementation("androidx.test.ext:junit:1.1.3")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
//    debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")
}