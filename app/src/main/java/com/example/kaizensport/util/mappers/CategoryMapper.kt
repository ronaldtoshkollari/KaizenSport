package com.example.kaizensport.util.mappers

import com.example.kaizensport.data.local.entity.CategoryEntity
import com.example.kaizensport.domain.model.Category

object CategoryMapper {


    fun entityToModel(categoryEntity: CategoryEntity): Category{
        return Category(
            id = categoryEntity.id,
            name = categoryEntity.name
        )
    }

}