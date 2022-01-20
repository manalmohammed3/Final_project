package com.example.final_project.data

import com.example.final_project.planmodel.Trip


object Argument {
        const val INDEX = "itemPosition"
    }

    val allPlans: MutableList<Trip> =
        mutableListOf(
            Trip("travel to jeddah", "take off at 6:45 / arrival at 7:30 /" +
                    "                              hotel check out at 1 pm"+" /places to go visit aunt sara go to redsea mall" , "2022-7-5", "2022-6-20", false, false),
            Trip("book a trip on airplane", "", "2022-4-15", "2022-12-1", false, false),
            Trip("places to go in riyadh ", "", "2022-12-25", "2022-10-1", false, false),
            Trip("Hotels  bill ", "", "2022-12-25", "2022-10-1", false, false),
            Trip("travel to dammam ", "", "2022-12-25", "2022-10-1", false, false)


        )



