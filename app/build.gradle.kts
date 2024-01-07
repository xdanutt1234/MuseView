plugins {
    id("com.android.application")
}

android {
    namespace = "com.myapp.museview"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.myapp.museview"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

   packaging{
        resources.pickFirsts.add("LICENSE-2.0.txt")
        resources.pickFirsts.add("META-INF/NOTICE.md")
       resources.pickFirsts.add("META-INF/LICENSE.md")
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    /*implementation ("org.mariadb.jdbc:mariadb-java-client:2.7.4")*/
    implementation ("androidx.sqlite:sqlite:2.4.0")
    implementation ("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("com.journeyapps:zxing-android-embedded:4.1.0")
    //implementation("com.sun.mail:android-mail:1.6.7")
    //implementation("com.sun.mail:android-activation:1.6.7")
    //implementation("com.sun.mail:javax.mail:1.6.6")
    //implementation("javax.activation:activation:1.1.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}