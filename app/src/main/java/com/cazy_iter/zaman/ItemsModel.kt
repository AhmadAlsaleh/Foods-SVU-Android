package com.cazy_iter.zaman

import java.io.Serializable

class ItemsModel(val id: String,
                 val name: String,
                 val image: String,
                 val locationID: String,
                 val content: String,
                 val description: String): Serializable