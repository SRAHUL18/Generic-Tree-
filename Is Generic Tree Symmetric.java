import java.io.*;
import java.util.*;

public class Main {
  private static class Node {
    int data;
    ArrayList<Node> children = new ArrayList<>();
  }

  public static void display(Node node) {
    String str = node.data + " -> ";
    for (Node child : node.children) {
      str += child.data + ", ";
    }
    str += ".";
    System.out.println(str);

    for (Node child : node.children) {
      display(child);
    }
  }

  public static Node construct(int[] arr) {
    Node root = null;

    Stack<Node> st = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == -1) {
        st.pop();
      } else {
        Node t = new Node();
        t.data = arr[i];

        if (st.size() > 0) {
          st.peek().children.add(t);
        } else {
          root = t;
        }

        st.push(t);
      }
    }

    return root;
  }

  public static int size(Node node) {
    int s = 0;

    for (Node child : node.children) {
      s += size(child);
    }
    s += 1;

    return s;
  }

  public static int max(Node node) {
    int m = Integer.MIN_VALUE;

    for (Node child : node.children) {
      int cm = max(child);
      m = Math.max(m, cm);
    }
    m = Math.max(m, node.data);

    return m;
  }

  public static int height(Node node) {
    int h = -1;

    for (Node child : node.children) {
      int ch = height(child);
      h = Math.max(h, ch);
    }
    h += 1;

    return h;
  }

  public static boolean areMirror(Node n1, Node n2) {
    // write your code here
    if(n1.children.size()!=n2.children.size()){
      return false;
    }
    for(int i=0;i<n1.children.size();i++){
      int j=n2.children.size()-1-i;
      Node c1=n1.children.get(i);
      Node c2=n2.children.get(j);
      boolean flag=areMirror(c1,c2);
      if(!flag){
        return false;
      }
    }
    return true;
  }
  public static boolean isSymmetric(Node n1){
    boolean flag = areMirror(n1,n1);
    return flag;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n1 = Integer.parseInt(br.readLine());
    int[] arr1 = new int[n1];
    String[] values1 = br.readLine().split(" ");
    for (int i = 0; i < n1; i++) {
      arr1[i] = Integer.parseInt(values1[i]);
    }
    Node root1 = construct(arr1);

    

    boolean symmetric = isSymmetric(root1);
    System.out.println(symmetric);
  }

}
