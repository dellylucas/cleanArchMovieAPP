plugins {
    id ("com.android.library")
    id ("kotlin-android")
}

android {
    compileSdkVersion (30)

    defaultConfig {
        minSdkVersion (24)
        targetSdkVersion (30)
        versionCode( 1)

        testInstrumentationRunner ("androidx.test.runner.AndroidJUnitRunner")
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
}

dependencies {
    implementation(project(":model"))
    implementation(project(":datamodule"))
    implementation(project(":sharedModule"))

    implementation ("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
    implementation ("androidx.core:core-ktx:1.5.0")
    implementation ("androidx.appcompat:appcompat:1.3.0")
    implementation ("com.google.android.material:material:1.3.0")

    //test
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.2")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.3.0")
}