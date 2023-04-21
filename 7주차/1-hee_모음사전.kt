package com.kotlin.week6
class Solution2 {

    val wordList = listOf<String>("", "A", "E", "I", "O", "U")
    lateinit var wordLibrary:MutableList<String>;
    fun solution(word: String): Int {

        wordLibrary = mutableListOf<String>()
        recurtion(5, "")
        wordLibrary.sort()

        return wordLibrary.indexOf(word)
    }

    fun recurtion(n:Int, str:String){
        if(n==0){
            if(!wordLibrary.contains(str)) wordLibrary.add(str)
            return;
        }
        for(i in 0..5){
            recurtion(n-1, "${str}${wordList[i]}")
        }
    }
}

fun main(arg:Array<String>){
    val answer1 = Solution2().solution("AAAAE");
    println(answer1);
}