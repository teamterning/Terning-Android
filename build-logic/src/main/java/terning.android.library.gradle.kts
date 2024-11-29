import com.terning.build_logic.configureCoroutineAndroid
import com.terning.build_logic.configureHiltAndroid
import com.terning.build_logic.configureKotlinAndroid

plugins {
    id("com.android.library")
}

configureKotlinAndroid()
configureCoroutineAndroid()
configureHiltAndroid()