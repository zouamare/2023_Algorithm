package com.kotlin.week6

class Solution3 {
    lateinit var merchandise:HashMap<String, Int>;

    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        merchandise = HashMap<String, Int>();

        for(i in 0 .. want.size-1){
            merchandise.put(want[i], number[i]);
        }

        var cnt:Int = 0;

        for(i in 0..discount.size-10){
            var copyMap:HashMap<String, Int> = HashMap<String, Int>();
            copyMap.putAll(merchandise)
            var isOk:Boolean = true;
            for(j in i..i+9){
                val product = discount[j];
                if(copyMap.get(product)==null) {
                    isOk = false;
                    break
                };
                if(copyMap.get(product)?.minus(1) ?: -1 < 0){
                    isOk = false;
                    break;
                }else {
                    copyMap.replace(product, copyMap.get(product)?.minus(1)?:-1)
                }
            }
            if(isOk) ++cnt;

        }
        return cnt;
    }
}

fun main(arg:Array<String>){

    val want = arrayOf("banana", "apple", "rice", "pork", "pot")
    val cnt = intArrayOf(3, 2, 2, 2, 1)
    val discount = arrayOf(
        "chicken",
        "apple",
        "apple",
        "banana",
        "rice",
        "apple",
        "pork",
        "banana",
        "pork",
        "rice",
        "pot",
        "banana",
        "apple",
        "banana"
    )

    val ans = Solution3().solution(want, cnt, discount);
    println(ans);
}
