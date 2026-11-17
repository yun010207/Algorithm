import java.util.*;

class Key {
    int depth;
    String str;

    Key(int d, String s) {
        this.depth = d;
        str = s;
    }
}

class Solution {

    static ArrayList<ArrayList<String>> keys;

    public int solution(String[][] relation) {
        int answer = 0;
        keys = new ArrayList<>();

        for (int i = 0; i < relation.length; i++) {
            keys.add(new ArrayList<>());
            dfs(relation[i], i);
        }

        int rows = relation.length;
        int cols = relation[0].length;
        List<Integer> candidates = new ArrayList<>();

        // j = 컬럼 조합(비트마스크). 0은 빈 조합이라 제외
        for (int j = 1; j < (1 << cols); j++) {

            // 유일성: 모든 행의 j번째 문자열이 서로 달라야 함
            Set<String> seen = new HashSet<>();
            for (int r = 0; r < rows; r++) {
                seen.add(keys.get(r).get(j));
            }
            if (seen.size() != rows) continue;

            // 최소성: 이미 찾은 후보키를 포함하고 있으면 탈락
            boolean minimal = true;
            for (int k : candidates) {
                if ((j & k) == k) {
                    minimal = false;
                    break;
                }
            }

            if (minimal) {
                candidates.add(j);
                answer++;
            }
        }

        return answer;
    }

    public static void dfs(String[] relation, int row) {
        Stack<Key> stack = new Stack<>();
        stack.push(new Key(0, ""));

        while (!stack.isEmpty()) {
            Key k = stack.pop();

            if (k.depth == relation.length) {
                keys.get(row).add(k.str);
                continue;
            }

            stack.push(new Key(k.depth + 1, k.str + "/" + relation[k.depth]));
            stack.push(new Key(k.depth + 1, k.str));
        }
    }
}