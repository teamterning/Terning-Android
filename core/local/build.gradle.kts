import com.terning.build_logic.setNamespace

plugins {
    alias(libs.plugins.terning.library)
}

android {
    setNamespace("core.local")
}