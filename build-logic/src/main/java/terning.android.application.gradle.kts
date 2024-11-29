import com.terning.build_logic.configureHiltAndroid
import com.terning.build_logic.configureKotlinAndroid

plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()