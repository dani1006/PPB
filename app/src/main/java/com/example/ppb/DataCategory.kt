package com.example.ppb

object DataCategory {
    private val catImage = intArrayOf(
        R.drawable.food,
        R.drawable.drink,
        R.drawable.burger,
        R.drawable.store
    )

    private val catName = arrayOf(
        "Food",
        "Drink",
        "Burger",
        "Outlate"
    )

    private val catDes = arrayOf(
        "Ini adalah isi menu Makanan",
        "Ini adalah isi menu Minuman",
        "Ini adalah isi menu Burger",
        "Ini adalah daftar Outlate kami"
    )

    val listData: ArrayList<Data>
        get() {
            val list = arrayListOf<Data>()
            for (position in catName.indices){
                val data = Data()
                data.name = catName[position]
                data.icon = catImage[position]
                data.des = catDes[position]
                list.add(data)
            }
            return list
        }
}