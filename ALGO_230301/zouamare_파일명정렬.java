package coding_test.AlgoY2023.M03.D01;

import java.util.Arrays;
/*
* 1) File 이라는 객체를 만들고 이를 정렬한 후 다시 String으로 변환하는 방식 => BEST!!
* - Comparator를 이용함 (이 부분 많이 까먹어서 다시 복기해야 할 것 같다.)
* - 그 외에는 단순한 로직
* */
public class zouamare_파일명정렬 {
    public static void main(String[] args) {
        String[] result = solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"});
        System.out.println(Arrays.toString(result));
    }

    public static String[] solution(String[] files){
        int LENGTH = files.length;
        File[] fileArr = new File[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            fileArr[i] = makeNewFile(files[i], i);
        }
        Arrays.sort(fileArr, (o1, o2) -> {
            if(o1.head.toUpperCase().compareTo(o2.head.toUpperCase()) != 0){
                return o1.head.toUpperCase().compareTo(o2.head.toUpperCase());
            }
            else{   // head가 같으면
                if(o1.number - o2.number != 0){
                    return o1.number - o2.number;
                }
                else{   // number가 같으면
                    return o1.idx - o2.idx;    // 원래의 순서를 고려함
                }
            }
        });

        String[] ans = new String[LENGTH];
        for(int i = 0; i < LENGTH; i++){
            ans[i] = files[fileArr[i].idx];
        }
        return ans;
    }

    private static File makeNewFile(String file, int index) {
        boolean flag = false;
        int headIdx = 0, idx = 0;
        String head = null;
        int number = 0;
        while(idx <= file.length()){
            if(!flag){   // head
                if(Character.isDigit(file.charAt(idx))){    // 숫자 발견
                    head = file.substring(0, idx);
                    headIdx = idx;
                    flag = true;
                    continue;
                }
                idx++;
            }
            else{  // number
                if(idx == file.length() || !Character.isDigit(file.charAt(idx))){
                    number = Integer.parseInt(file.substring(headIdx, idx));
                    break;
                }
                idx++;
            }
        }

        return new File(head, number, index);
    }

    static class File {
        String head;
        int number;
        int idx;

        public File(String head, int number, int idx) {
            this.head = head;
            this.number = number;
            this.idx = idx;
        }
    }
}
