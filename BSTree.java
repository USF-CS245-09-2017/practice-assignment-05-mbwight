public class BSTree{
	//inner class that has all of the components for the node
	class BSTNode implements Comparable<BSTNode>{
		private Comparable data;
		private BSTNode left;
		private BSTNode right;
		//setter and getter
		public BSTNode(Comparable value){
			this.data = value;
		}
		public Comparable getNode(){
			return this.data;
		}
		
		public void setNode(Comparable value){
			this.data = value;
		}
		//compareTo which compares the numbers to one another of the nodes 
		@Override
		public int compareTo(BSTNode o) {
			if(this.data == o.data)
				return 0;
			else if(this.data.compareTo(o.data) == 1)
				return 1;
			else
				return -1;
		}
		
	}
	private BSTNode root;
	
	//initial find that starts at root
	public boolean find(Comparable value){
		return find(value,root);
	}
	//starts at root, compares node in find to node at position
	//traverses the tree until either the node is found in the position it should be or 
	//until we run out of nodes in the path
	public boolean find(Comparable value, BSTNode node){
		if(node==null)
			return false;
		else if(node.data.compareTo(value)==0)
			return true;
		else if(node.data.compareTo(value)>0)
			return find(value,node.left);
		else{
			return find(value,node.right);
		}
	}
	public String toStringInOrder(){
		return toStringInOrder(root).trim();
	}
	//inorder prints the nodes in their numerical order. Traverses the most left of the tree, prints the left
	//then prints the parent, then prints the right of the parent
	public String toStringInOrder(BSTNode node){
		String toString = "";
		if(node==null)
			return "";
		toString += toStringInOrder(node.left);
		toString += node.data + " ";
		toString += toStringInOrder(node.right);
		
		return toString;
	}
	//preOrder has the same idea but prints the parent, then the children
	public String toStringPreOrder(){
		return toStringPreOrder(root).trim();
	}
	
	public String toStringPreOrder(BSTNode node){
		String toString = "";
		if(node==null)
			return "";
		toString += node.data + " ";
		toString += toStringPreOrder(node.left);
		toString += toStringPreOrder(node.right);
		
		return toString;
	}
	
	public BSTNode insert(Comparable value){
		root = insert(value,root);
		return root;
	}
	//insert takes the same logic of find, but instead adds in a new node
	//handles duplicates going to the left child
	public BSTNode insert(Comparable value, BSTNode node){
		if(node==null){
			node = new BSTNode(value);
			return node;
		}
		else if(node.data.compareTo(value)>=0){
			node.left = insert(value,node.left);
			return node;
		}
		else {
			node.right = insert(value,node.right);
			return node;
		}
	}
	
	public BSTNode delete(Comparable value){
		root = delete(value,root);
		return root;
	}
	//finds a node if in the tree, removes it by checking if that node has children
	//searches for the smallest node under the right branch of the to be deleted node if applicable
	//if no children can replace the deleted node, then it will be replaced by null
	public BSTNode delete(Comparable value, BSTNode node){
		if(node==null)
			return null;
		if(node.data.compareTo(value)==0){
			if(node.left==null)
				return node.right;
			else if(node.right==null)
				return node.left;
			else{
				if(node.right.right==null){
					node.data = node.right.data;
					node.right = node.right.right;
					return node;
				}
				else{
					node.data = removeSmallest(node.right);
					return node;
				}
			}
		}
		else if(value.compareTo(node.data)>0)
			node.left = delete(value,node.left);
		return node;
	}
	//removeSmallest recursively checks for the smallest value in a given branch
	//needed to replace the deleted node 
	Comparable removeSmallest(BSTNode node){
		Comparable smallest;
		if(node.left.left==null){
			smallest = node.left.data;
			node.left = node.left.right;
			return smallest;
		}
		else{
			return removeSmallest(node.left);
		}
	}
	
}