package com.kt.week15

import java.util.*

class Solution2 {

    // static 변수 및 상수
    companion object{
        const val SIZE = 2500;
        val parents = Array<Int>(SIZE+1, {0});
        val values = Array<String>(SIZE+1, {""});
    }

    // init 함수를 통해 배열을 초기화 해줌.
    private fun init(){
        for( i in 1..SIZE){
            parents?.set(i, i);
            values?.set(i, "");
        }
    }

    // union find 알고리즘
    private fun find(a:Int) : Int {
        return if(parents?.get(a) == a) a
        else {
            parents?.set(a, find(parents?.get(a)));
            parents?.get(a);
        };
    }

    private fun union(a:Int, b:Int):Boolean{
        val rootA = find(a);
        val rootB = find(b);
        if(rootA == rootB) return false;
        parents?.set(rootB, rootA);
        return true;
    }

    // 2차원 좌표를 1차원 배열 인덱스로 컨버팅 해주는 함수
    private fun getCvtIdx(r:Int, c:Int):Int{
        val res = 50 * (r - 1);
        return res + c;
    }

    fun solution(commands: Array<String>): Array<String> {
        init();
        val result = arrayListOf<String>();
        for(cmd in commands){
            val st = StringTokenizer(cmd);
            centralProcess(st, result);
        }
        return result.toTypedArray(); // 코틀린의 문법, 스트림보다도 더 간단하게 한줄로 배열 변환 가능..!
    }

    private fun centralProcess(st:StringTokenizer, list:ArrayList<String>){
        // switch case 문 보다 더 가독성이 좋은 코틀린의 when 절 사용 ^_^
        // 모든 명령어는 동작이 첫 번째 토큰, 즉 헤더 부분에 있음,
        when(st.nextToken()){ // header 라는 변수로 빼지 않고 바로 토큰에서 꺼내어 분기, < IntelliJ에서 권장 >
            "UPDATE" -> {
                if(st.countTokens() == 2){ // update value value
                    val value1 = st.nextToken();
                    val value2 = st.nextToken();
                    updateAll(value1, value2);

                }else { // update r c value
                    val r = st.nextToken().toInt();
                    val c = st.nextToken().toInt();
                    val value = st.nextToken();
                    updateCell(r, c, value);
                }
            }
            "MERGE" -> {
                val r1 = st.nextToken().toInt();
                val c1 = st.nextToken().toInt();
                val r2 = st.nextToken().toInt();
                val c2 = st.nextToken().toInt();
                merge(r1, c1, r2, c2);
            }
            "UNMERGE" -> {
                val r = st.nextToken().toInt();
                val c = st.nextToken().toInt();
                unMerge(r, c);
            }
            "PRINT" ->{
                val r = st.nextToken().toInt();
                val c = st.nextToken().toInt();
                print(r, c, list);
            }
        }
    }

    // 배열 전체를 순회하여, 같은 값이 있는 경우 update만 해준다.
    // merge 작업으로 인해 여러 값이 묶인 경우는 한 개의 값 만 바뀌므로
    // 그냥 배열 전체를 순회하며 값을 바꾸어 주면 된다.
    private fun updateAll(value1: String, value2: String) {
        for(i in 1..SIZE){
            if(value1?.equals(values[i])){
                values[i] = value2;
            }
        }
    }

    // 특정 좌표에 접근하여 값을 바꾸는데,
    // 핵심은 병합된 좌표에 접근할 수 있는데, 접근할 경우 루트로 이동해야하고
    // 루트 값을 바꾸는 로직으로 구현해야 한다는 것! <문제 속 설명!>
    private fun updateCell(r:Int, c:Int, value:String){
        val cIdx = getCvtIdx(r, c); // 조회하고자 하는 좌표를 가져오고
        val root = find(cIdx); // 루트 index를 찾은 다음
        values[root] = value; // 루트의 값을 바꾼다.
    }

    // 두 셀을 병합하는 = union 함수가 사용되는 함수!
    private fun merge(r1:Int, c1:Int, r2:Int, c2:Int){
        val cIdx1 = getCvtIdx(r1, c1);
        val cIdx2 = getCvtIdx(r2, c2);
        val rootA = find(cIdx1);
        val rootB = find(cIdx2);
        if(rootA == rootB) return; // 이미 병합된 것이라면 작업을 종료

        val rootValue = values[rootA].ifEmpty { values[rootB] }; // 코틀린의 문법!

        values[rootA] = ""; // 각각의 루트 값을 비워주고
        values[rootB] = "";
        union(rootA, rootB); // 병합한 후
        values[rootA] = rootValue; // 새로 할당

    }

    // 병합된 셀을 푸는 작업,
    // 병합을 해제하는 경우 해제하고자 하는 좌표를 기준으로 해당 좌표만 값을 보존하고
    // 연결되었던 (루트인 경우도 포함!) 모든 셀들은 초기화 시켜주는 함수
    private fun unMerge(r:Int, c:Int){
        val cIdx = getCvtIdx(r, c);
        val root = find(cIdx); // 루트 인덱스를 탐색 후
        val rootValue = values[root]; // 기존의 루트 값을 보존한다.
        values[root] = "" // 루트를 초기화한 후
        values[cIdx] = rootValue; // 조회하는 곳의 값을 루트 값으로 보존해줌

        // 병합된 셀을 모두 찾고, 루트와 같으면, 리스트에 저장
        // why? for문에서 찾자마자 바꿔버리면 연결이 끊겨서, 모든 연결을 제거하지 못함
        val dList = arrayListOf<Int>();
        for(i in 1..SIZE){
            if(find(i) == root){
                dList.add(i);
            }
        }

        for(idx in dList){
            parents[idx] = idx;
        }
    }

    // 값을 조회하는 함수!
    // 값을 조회할 때 단순히 index에 접근해서 조회하면 "" 를 조회할 수 있음
    // why ? 병합 작업 시 루트 빼고 "" 처리 했으므로
    // 그러므로 좌표를 조회할 때 현재 인덱스를 기준으로 루트 인덱스를 찾고
    // 그 루트 인덱스에 저장된 값을 저장해야함
    private fun print(r:Int, c:Int, list:ArrayList<String>){
        val cIdx = getCvtIdx(r, c);
        val root = find(cIdx);
        val value = values[root].ifEmpty { "EMPTY" };
        list.add(value);
    }

}

fun main() {
    val sol = Solution2();
    val ans = sol.solution(
        arrayOf<String>(
            "UPDATE 1 1 menu",
            "UPDATE 1 2 category",
            "UPDATE 2 1 bibimbap",
            "UPDATE 2 2 korean",
            "UPDATE 2 3 rice",
            "UPDATE 3 1 ramyeon",
            "UPDATE 3 2 korean",
            "UPDATE 3 3 noodle",
            "UPDATE 3 4 instant",
            "UPDATE 4 1 pasta",
            "UPDATE 4 2 italian",
            "UPDATE 4 3 noodle",
            "MERGE 1 2 1 3",
            "MERGE 1 3 1 4",
            "UPDATE korean hansik",
            "UPDATE 1 3 group",
            "UNMERGE 1 4",
            "PRINT 1 3",
            "PRINT 1 4"
        ))

    println(Arrays.toString(ans)); // 출력
}