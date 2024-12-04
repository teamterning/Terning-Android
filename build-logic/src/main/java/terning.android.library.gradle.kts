import com.terning.build_logic.convention.configureCoroutineAndroid
import com.terning.build_logic.convention.configureHiltAndroid
import com.terning.build_logic.convention.configureKotlinAndroid

plugins {
    id("com.android.library")
}

configureKotlinAndroid()
configureCoroutineAndroid()
configureHiltAndroid()