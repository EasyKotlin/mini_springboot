package jason.chen.mini_springboot.restful.service

import com.squareup.kotlinpoet.ClassName
import org.springframework.stereotype.Service

/**
 * Created by jack on 2017/6/3.
 */

@Service
class PrimaryTypeService {
    fun getKotlinPrimaryType(): MutableList<Any> {
        val ANY = ClassName.get("kotlin", "Any")
        val UNIT = ClassName.get(Unit::class)
        val BOOLEAN = ClassName.get("kotlin", "Boolean")
        val BYTE = ClassName.get("kotlin", "Byte")
        val SHORT = ClassName.get("kotlin", "Short")
        val INT = ClassName.get("kotlin", "Int")
        val LONG = ClassName.get("kotlin", "Long")
        val CHAR = ClassName.get("kotlin", "Char")
        val FLOAT = ClassName.get("kotlin", "Float")
        val DOUBLE = ClassName.get("kotlin", "Double")
        val ARRAY = ClassName.get("kotlin", "Array")
        val MutableList = ClassName.get("kotlin.collections", "MutableList")

        val primaryTypeList = mutableListOf<Any>()
        primaryTypeList.add(ANY)
        primaryTypeList.add(UNIT)
        primaryTypeList.add(BOOLEAN)
        primaryTypeList.add(BYTE)
        primaryTypeList.add(SHORT)
        primaryTypeList.add(LONG)
        primaryTypeList.add(CHAR)
        primaryTypeList.add(FLOAT)
        primaryTypeList.add(DOUBLE)
        primaryTypeList.add(ARRAY)
        primaryTypeList.add(MutableList)

        return primaryTypeList
    }
}
