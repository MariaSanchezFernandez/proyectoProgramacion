plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.proyectomovie_api"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.proyectomovie_api"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    viewBinding{
        enable = true
    }
}

dependencies {
    // AndroidX Core
    implementation (libs.androidx.core.ktx)

            // AndroidX AppCompat
            implementation (libs.androidx.appcompat)

            // Material Design
            implementation (libs.material)

            // AndroidX Activity
            implementation (libs.androidx.activity)

            // ConstraintLayout
            implementation (libs.androidx.constraintlayout)

            // Navigation Fragment
            implementation (libs.androidx.navigation.fragment.ktx)

            // Navigation UI
            implementation (libs.androidx.navigation.ui.ktx)

            // AndroidX Leanback
            implementation(libs.androidx.leanback)

            // Testing
            testImplementation (libs.junit)
            androidTestImplementation (libs.androidx.junit)
            androidTestImplementation (libs.androidx.espresso.core)

            // Compose UI
            implementation ("androidx.compose.ui:ui-android:1.6.7") {
                exclude(module = "ui-desktop")
            }

            // Fragment KTX
            implementation (libs.androidx.fragment.ktx)

            // Activity KTX
            implementation (libs.androidx.activity.ktx)

            // ViewModel KTX
            implementation (libs.androidx.lifecycle.viewmodel.ktx)

            // LiveData KTX
            implementation (libs.androidx.lifecycle.livedata.ktx)

            // Retrofit
            implementation (libs.retrofit)

            // Gson Converter
            implementation (libs.converter.gson)

            // Glide
            implementation("com.github.bumptech.glide:glide:4.16.0")


            // ViewPager2
            implementation ("androidx.viewpager2:viewpager2:1.0.0")

            // Duplications removed
            implementation ("androidx.appcompat:appcompat:1.6.1")
            implementation ("com.google.android.material:material:1.9.0")
            implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
            androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
            implementation ("com.github.bumptech.glide:glide:4.16.0")

//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//    implementation(libs.androidx.activity)
//    implementation(libs.androidx.constraintlayout)
//    implementation(libs.androidx.navigation.fragment.ktx)
//    implementation(libs.androidx.navigation.ui.ktx)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//
//    // Fragment
//    implementation (libs.androidx.fragment.ktx)
//    // Activity
//    implementation (libs.androidx.activity.ktx)
//    // ViewModel
//    implementation (libs.androidx.lifecycle.viewmodel.ktx)
//    // LiveData
//    implementation (libs.androidx.lifecycle.livedata.ktx)
//    // retrofit
//    implementation (libs.retrofit)
//    // gson converter
//    implementation (libs.converter.gson)
//    //Glide
//    implementation (libs.glide)
//    //ViewPager
//    implementation ("androidx.viewpager2:viewpager2:1.0.0")
}
