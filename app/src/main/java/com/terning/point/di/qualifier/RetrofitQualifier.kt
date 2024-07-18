package com.terning.point.di.qualifier


import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class JWT

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class REISSUE