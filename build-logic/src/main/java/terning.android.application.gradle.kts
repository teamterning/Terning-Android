import com.terning.build_logic.convention.configureHiltAndroid
import com.terning.build_logic.convention.configureKotlinAndroid

plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()