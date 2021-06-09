package com.dicoding.foodcounter

object FoodData {
    private val foodNames = arrayOf(
        "Cheesecake",
        "Chicken Wings",
        "Donut",
        "French Fries",
        "Hamburger",
        "Ice Cream",
        "Omelette",
        "Pizza",
        "Ramen",
        "Sushi"
    )

    private val foodDetails = arrayOf(
        "Cheesecake Calorie: 468 calories/slice",
        "Chicken Wings Calorie: 86 calories/pcs",
        "Donut Calorie: 250 calories/pcs",
        "French Fries Calorie: 427 calories/serving",
        "Hamburger Calorie: 330 calories/pcs",
        "Ice Cream Calorie: 267 calories/cup",
        "Omelette Calorie: 93 calories/serving",
        "Pizza Calorie: 265 calories/pcs",
        "Ramen Calorie: 300 calories/serving",
        "Sushi Calorie: 200 calories/roll"
    )

    private val foodImages = intArrayOf(
        R.drawable.cheesecake,
        R.drawable.chicken_wings,
        R.drawable.donuts,
        R.drawable.french_fries,
        R.drawable.hamburger,
        R.drawable.ice_cream,
        R.drawable.omelette,
        R.drawable.pizza,
        R.drawable.ramen,
        R.drawable.sushi
    )

    val listData: ArrayList<Food>
        get() {
            val list = arrayListOf<Food>()
            for (position in foodNames.indices) {
                val food = Food()
                food.name = foodNames[position]
                food.detail = foodDetails[position]
                food.photo = foodImages[position]
                list.add(food)
            }
            return list
        }
}