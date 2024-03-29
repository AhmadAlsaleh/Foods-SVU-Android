package com.cazy_iter.zaman

interface APIs {
    companion object {
        val BASE = "https://foods-svu.herokuapp.com/"

        val CATEGORIES = BASE + "categories/"
        val MAIN = CATEGORIES + "main/"
        val FIND_MEAL = CATEGORIES + "findMeal/"

        val LOCATIONS = BASE + "locations/"
    }
}
