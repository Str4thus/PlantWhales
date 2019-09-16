package com.example.plantwhales.gamelogic

import com.example.plantwhales.gameobjects.GameObject
import com.example.plantwhales.proto.Proto

object ProtoManager {
    private val protoList: HashMap<String, Proto<out GameObject>> = HashMap()

    fun getProto(name: String): Proto<out GameObject>? {
        return protoList[name]
    }

    fun instantiateProto(name: String): GameObject? {
        return protoList[name]?.instantiate()
    }

    fun hasProto(name: String): Boolean {
        return protoList.containsKey(name)
    }

    fun addProto(name: String, proto: Proto<out GameObject>) {
        protoList[name] = proto
    }
}