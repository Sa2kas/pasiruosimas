package com.example.egzaminui

import android.content.Context
import android.view.View
import kotlin.random.Random

class DrawFigure{
    val presents = listOf("Candy", "Balloon", "Ball")
    // a random partition, the result may be different every time
    val alicePresents = presents.partition { Random.nextBoolean() }

    //println("Alice receives $alicePresents")
    //println("Bob receives $bobPresents")

    /*fun kazkas(){
        ivBall.setOnClickListener(View.OnClickListener {

            //Animate using XML
            // val rotateAnimation = AnimationUtils.loadAnimation(activity, R.anim.rotate_indefinitely)

            //OR using Code
            val rotateAnimation = RotateAnimation(
                0f, 359f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f

            )
            rotateAnimation.duration = 300
            rotateAnimation.repeatCount = 2

            //Either way you can add Listener like this
            rotateAnimation.setAnimationListener(object : Animation.AnimationListener {

                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {

                    val rand = Random()
                    val ballHit = rand.nextInt(50) + 1
                    Toast.makeText(context, "ballHit : " + ballHit, Toast.LENGTH_SHORT).show()
                }
            })

            ivBall.startAnimation(rotateAnimation)
        })
    }*/

    /*fun ImageView.rotate(){
        val rotateAnimation = RotateAnimation(
            0f, 359f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f

        )
        rotateAnimation.duration = 300
        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {}
        })
        this.startAnimation(rotateAnimation)*/

}