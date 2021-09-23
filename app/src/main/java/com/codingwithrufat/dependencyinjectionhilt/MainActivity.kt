package com.codingwithrufat.dependencyinjectionhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
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

        println(someClass.doSomething())
        println(someClass.doOtherThing())

    }
}

@AndroidEntryPoint
class MyFragment: Fragment(){

    @Inject
    lateinit var someClass: SomeClass

}


@Singleton
class SomeClass
@Inject
constructor(private val otherSomeClass: OtherSomeClass){
    fun doSomething(): String {
        return "I do a thing"
    }

    fun doOtherThing(): String {
        return otherSomeClass.doSomething()
    }

}

class OtherSomeClass
@Inject
constructor(){

    fun doSomething(): String{
        return "I do another thing"
    }

}