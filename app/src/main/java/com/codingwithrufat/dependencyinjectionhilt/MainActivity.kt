package com.codingwithrufat.dependencyinjectionhilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // field injection
    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.getThing())

    }
}

class SomeClass
@Inject
constructor(
    private val someDependency: String
) : SomeInterface {
    override fun getThing(): String {
        return "I got interface"
    }

}

interface SomeInterface {
    fun getThing(): String
}

@InstallIn(ActivityComponent::class)
@Module
abstract class MyModule1 {
    @ActivityScoped
    @Binds
    abstract fun bindSomeDependency(
        someInterface: SomeClass
    ): SomeInterface
}

@InstallIn(ActivityComponent::class)
@Module
class MyModule2 {

    @ActivityScoped
    @Provides
    fun provideSomeString(): String {
        return "some string"
    }

    @Singleton
    @Provides
    fun provideSomeInterface(
        someString: String
    ): SomeInterface{
        return SomeClass(someString)
    }

}