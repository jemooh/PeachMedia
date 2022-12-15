plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.tech.peachmedia"
    compileSdk = 32

    defaultConfig {
        applicationId = "com.tech.peachmedia"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            versionNameSuffix = " - debug"
        }

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.activity:activity-ktx:1.4.0")

    // Koin MVVM
    implementation("io.insert-koin:koin-core:3.1.2")
    implementation("io.insert-koin:koin-android:3.1.2")
    implementation("io.insert-koin:koin-androidx-compose:3.1.2")
    // Koin testing tools
    testImplementation("io.insert-koin:koin-test:3.1.2")
    // Needed JUnit version
    testImplementation("io.insert-koin:koin-test-junit4:3.1.2")

    // Room
    implementation("androidx.room:room-runtime:2.4.2")
    kapt("androidx.room:room-compiler:2.4.2")

    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.4.2")

    implementation("com.jakewharton.timber:timber:5.0.0")

    // Compose dependencies
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0-alpha06")
    implementation("androidx.compose.material:material-icons-extended:1.1.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.compose.runtime:runtime:1.1.1")
    implementation("androidx.compose.ui:ui:1.1.1")
    implementation("androidx.compose.foundation:foundation:1.1.1")
    implementation("androidx.compose.foundation:foundation-layout:1.1.1")
    implementation("androidx.compose.material:material:1.1.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.1.1")
    implementation("androidx.compose.ui:ui-tooling:1.1.1")
    implementation("com.google.android.material:compose-theme-adapter:1.1.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.1.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.1.1")

    // RoomDB Test
    testImplementation("androidx.room:room-testing:2.4.0-alpha04")
    testImplementation("androidx.test:core:1.4.1-alpha01")
    testImplementation("androidx.test:runner:1.4.0")
}