import java.io.*;
import java.util.*;

public class Main {
	
	static ArrayList<Integer> parent;
	static Map<String, Integer> m;
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
        	
        	int F = Integer.parseInt(br.readLine());
        	
        	parent = new ArrayList<>();
        	parent.add(-1);
        	m = new HashMap<>();
        	
        	for(int i = 0; i < F; i++) {
        		StringTokenizer st = new StringTokenizer(br.readLine());
        		String a = st.nextToken();
        		String b = st.nextToken();
        		
        		if(!m.containsKey(a)) {
        			m.put(a, m.size() + 1);
        			parent.add(-1);
        		}
        		if(!m.containsKey(b)) {
        			m.put(b, m.size() + 1);
        			parent.add(-1);
        		}
        		
        		
        		union(m.get(a), m.get(b));
        		
        		sb.append(size(m.get(a))).append("\n");
        	}
        	
        	
        }
        System.out.println(sb);
    	
    }
    
    public static int find(int a) {
    	if(parent.get(a) < 0) return a;
    	int root = find(parent.get(a));
    	parent.set(a,  root);
    	return root;
    }
    
    public static boolean union(int a, int b) {
    	a = find(a);
    	b = find(b);
    	
    	if(a == b) return false;
    	
    	
    	if(a < b) {
    		parent.set(a, parent.get(a) + parent.get(b));
    		parent.set(b, a);
    		
    	}
    	else {
    		parent.set(b, parent.get(a) + parent.get(b));
    		parent.set(a, b);
    	}
    	return true;
    }
    
    public static int size(int a) {
    	return -parent.get(find(a));
    }
}
