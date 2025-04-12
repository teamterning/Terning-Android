import com.terning.build_logic.extension.setNamespace

plugins {
    alias(libs.plugins.terning.library)
}

android {
    setNamespace("core.navigator")
}