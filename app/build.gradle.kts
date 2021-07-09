plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    id("com.google.secrets_gradle_plugin") version "0.6.1"
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId("com.dfl.cleanarchmovieapp")
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode(1)
        versionName("2.0")

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {

    implementation(project(":usecasesmodule"))
    implementation(project(":model"))
    implementation(project(":datamodule"))
    implementation(project(":sharedModule"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
    implementation("androidx.appcompat:appcompat:${Versions.appcompat}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}")
    implementation("androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_ktx}")
    implementation("androidx.activity:activity-ktx:${Versions.activity_ktx}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    //IDep Hilt
    implementation("com.google.dagger:hilt-android:${Versions.hilt}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.hilt}")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.navigation}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.navigation}")
    // Feature module Support
    implementation("androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}")
    implementation ("com.github.omadahealth:swipy:1.2.3@aar")

    //room
    implementation("androidx.room:room-runtime:${Versions.room}")

    //lottie animation
    implementation("com.airbnb.android:lottie:${Versions.lottie}")

    implementation("androidx.paging:paging-runtime-ktx:${Versions.paging}")

    //glide
    implementation("com.github.bumptech.glide:glide:${Versions.glide}")
    kapt("com.github.bumptech.glide:compiler:${Versions.glide}")

    //test
    testImplementation("junit:junit:${Versions.junit}")
    testImplementation("io.mockk:mockk:${Versions.mockk}")
    androidTestImplementation("androidx.test.ext:junit:${Versions.ext_junit}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espresso}")
    testImplementation("org.robolectric:robolectric:4.4")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.0")
    testImplementation("com.google.truth:truth:1.0.1")
    implementation("androidx.arch.core:core-testing:2.1.0")
    debugImplementation("androidx.fragment:fragment-testing:${Versions.fragment}")


    // For local unit tests
    testImplementation("com.google.dagger:hilt-android-testing:2.37")
   // testAnnotationProcessor("com.google.dagger:hilt-compiler:2.37")
}