/*
 * © 2021-2021 SorrowBlue.
 */

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(kotlin("gradle-plugin", "1.4.30"))
    implementation("com.android.tools.build:gradle:7.0.0-alpha05")
}
