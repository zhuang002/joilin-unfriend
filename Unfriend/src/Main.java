import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	
	public static Node[] nodes=null;
	static Set<Integer> setOfSets = new HashSet<>();
	
	public static void main(String[] argv) {
		 Scanner sc =new Scanner(System.in);
		 int n=sc.nextInt();
		 
		 nodes = new Node[n];
		 for (int i=0;i<n;i++) {
			 nodes[i] = new Node(i);
		 }
		 
		 for (int i=0;i<n-1;i++) {
			 int parent = sc.nextInt()-1;
			 Node node = nodes[parent];
			 node.addChild(nodes[i]);
		 }
		 
		 for (int i=0;i<Math.pow(2, n-1);i++) {
			 int set = getWholeSet(i);
			 setOfSets.add(set);
		 }
		 
		 System.out.println(setOfSets.size());
	}
	
	
	private static int getWholeSet(int i) {
		// TODO Auto-generated method stub
		Set<Node> nodeSet = convertToNodeSet(i);
		Set<Node> resultSet = new HashSet<>();
		resultSet.addAll(nodeSet);
		for (Node node:nodeSet) {
			Set<Node> subNodes=node.getSubnodes();
			resultSet.addAll(subNodes);
		}
		
		return convertToInteger(resultSet);
	}
	
	
	static int convertToInteger(Set<Node> nodeSet) {
		// TODO Auto-generated method stub
		int result = 0;
		for (int i=nodes.length-1;i>=0;i--) {
			Node node = nodes[i];
			if (nodeSet.contains(node)) {
				result |= 1;
			}
			result <<= 1;
		}
		return result >> 1;
	}

	static Set<Node> convertToNodeSet(int iSet) {
		// TODO Auto-generated method stub
		Set<Node> result = new HashSet<>();
		
		for (int i=0;i<nodes.length;i++) {
			if ((iSet & 0x01) !=0) {
				Node node=nodes[i];
				result.add(node);
			}
			iSet >>= 1;
		}
		return result;
	}
}

class Node {
	int id=0;
	Set<Node> children=new HashSet<>();
	public Node(int i) {
		id=i;
	}
	
	

	public Set<Node> getSubnodes() {
		// TODO Auto-generated method stub
		Set<Node> result = new HashSet<>();
		if (this.children.isEmpty()) 
			return result;
		
		result.addAll(this.children);
		for (Node node:this.children) {
			result.addAll(node.getSubnodes());
		}
		return result;
	}

	public void addChild(Node node) {
		this.children.add(node);
	}
}
