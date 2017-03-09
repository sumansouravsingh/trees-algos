package com.practice.TreeandGraph;
import com.practice.Tree.*;
import com.practice.Graph.*;
import java.util.*;

public class Solution {

	
	public Tree insertNode(Tree root,int data)
	{
		if(root==null)
		{
			Tree node = new Tree(data);
			return node;
		}
		else
			if(data<=root.data)
			{
				root.left=insertNode(root.left,data);
			}
		else
			root.right=insertNode(root.right,data);
		return root;
	}
	
	public void printInorder(Tree root)
	{
		if(root==null)
			return;
		printInorder(root.left);
		System.out.print(root.data+" ");
		printInorder(root.right);
	}
	public void postOrder(Tree root)
	{
		if(root==null)
			return;
		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.data+" ");
	}
	
	public void preOrder(Tree root)
	{
		if(root==null)
			return;
		System.out.print(root.data+" ");
		preOrder(root.left);
		preOrder(root.right);
	}
	
	//Level Order Traversal
	public void printLevelOrder(Tree root)
	{
		Queue<Tree> q = new LinkedList<Tree>();
		if(root==null)
			return;
		q.add(root);
		while(!q.isEmpty())
		{
			Tree temp=q.poll();
			System.out.print(temp.data+" ");
			if(temp.left!=null)q.add(temp.left);
			if(temp.right!=null)q.add(temp.right);
		}
	}
	
	//Get Height of a binary Tree(can also be considered as Max Depth)
	public int getTreeHeight(Tree root)
	{
		if(root==null)
			return -1;
		return 1+Math.max(getTreeHeight(root.right), getTreeHeight(root.left));
	}
	
	//Check if a tree is balanced(length of right and left subtree should always differ by atmost 1)
	public boolean isBalanced(Tree root)
	{
		boolean res=false;
		if(root==null)
			return true;
		int h=getTreeHeight(root.left);
		int h1=getTreeHeight(root.right);
		if(Math.abs(h-h1)>1)
			return false;
		else return isBalanced(root.left)&&isBalanced(root.right);
	}

	//Check if a tree is Balanced using DP
	public boolean isBalancedDP(Tree root)
	{
		return checkHeight(root)!=Integer.MIN_VALUE;
	}
	
	public int checkHeight(Tree root){
		if(root==null)
			return -1;
		int h = checkHeight(root.left);
		if(h==Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		int h1 = checkHeight(root.right);
		if(h1==Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		if(Math.abs(h-h1)>1)
			return Integer.MIN_VALUE;
		else return Math.max(h, h1)+1;
	}
	
	//Check if a tree is BST
	public boolean isProperBST(Tree root, int min, int max)
	{
		boolean res=false;
		if(root==null)
			res= true;
		else if(root.data>=min && root.data<max && isProperBST(root.left,min,root.data) && isProperBST(root.right,root.data,max))
			res=true;
		else
			res=false;
		return res;
	}
	
	private String searchElem(Tree root, int n) {
		if(root==null)
			return "Not Present";
		if(root.data==n)
			return "Present";
		if(root.data>n)
			return searchElem(root.left,n);
		else
			return searchElem(root.right,n);
	}

	//get Min and Max in BST
	private Tree getMinValue(Tree root)
	{
		if(root.left==null)
			return root;
		else
			return getMinValue(root.left);
	}
	
	private Tree getMaxValue(Tree root)
	{
		if(root.right==null)
			return root;
		else
			return getMaxValue(root.right);
	}
	
	//Search Element in BST
	private Tree findElem(Tree root, int n) {
		if(root==null)
			return null;
		if(root.data==n)
			return root;
		if(root.data>n)
			return findElem(root.left,n);
		else
			return findElem(root.right,n);
	}

	//Find Successor of a node in BST in Inorder Traversal
	private Tree getSuccessor(Tree root, int data)
	{
		Solution s =new Solution();
		Tree curr=s.findElem(root,data);
		if(curr.right!=null)
		{
			return getMinValue(curr.right);
		}
		else
		{
			Tree suc=null;
			Tree anc = root;
			while(anc!=curr)
			{
				if(curr.data<anc.data)
				{
					suc = anc;
					anc = anc.left;
				}
				else
					anc = anc.right;
			}
			return suc;
		}
	} 
	
	//Find Predecessor of a node in BST in Inorder Traversal
	private Tree getPredecessor(Tree root, int data)
	{
		Tree curr=findElem(root,data);
		if(curr.left!=null)
		{
			return getMaxValue(curr.left);
		}
		else
		{
			Tree pre=null;
			Tree anc = root;
			while(anc!=curr)
			{
				if(curr.data>anc.data)
				{
					pre = anc;
					anc = anc.right;
				}
				else
					anc = anc.left;
			}
			return pre;
		}
	}
	
	//Delete a Node from BST
	public Tree deleteNode(Tree root, int data)
	{
		if(root==null)
			return null;
		else if(root.data>data)
			root.left=deleteNode(root.left,data);
		else if(root.data<data)
			root.right=deleteNode(root.right,data);
		else
		{
			if(root.left==null && root.right==null)
				return null;
			else if(root.left==null)
				root=root.right;
			else if(root.right==null)
				root=root.left;
			else
			{
				Tree temp=getMinValue(root.right);
				root.data=temp.data;
				root.right=deleteNode(root.right, temp.data);
			}
		}
		return root;
	}
	
	//Chindi TopView
	public void printTopView(Tree root)
	{
		if(root==null)
			return;
		if(root.left!=null)
			printLeft(root.left);
		if(root.right!=null)
			printLeft(root.right);
	}

	public void printLeft(Tree root)
	{
		if(root==null)
			return;
		System.out.print(root.data);
		if(root.left!=null)
			printLeft(root.left);
	}
	
	public void printRight(Tree root)
	{
		if(root==null)
			return;
		System.out.print(root.data);
		if(root.right!=null)
			printLeft(root.right);
	}
	
	//Print LCA of BST
	public Tree LCA(Tree root,int data1, int data2)
	{
		if(root==null)
			return null;
		if(data1>root.data&&data2>root.data)
			return LCA(root.right,data1,data2);
		else if(data1<root.data&&data2<root.data)
			return LCA(root.left,data1,data2);
		else
			return root;
	}
	
	//Find LCA of any binary tree(Check if data is both on right or left. if l!=r. this means both are on opposite side of root. thus root is lca
	public Tree LCABT(Tree root, int data1, int data2)
	{
		if(root==null||root.data==data1|root.data==data2)
			return root;
		boolean l = checkContains(root.left, data1);
		boolean r = checkContains(root.left, data2);
		if(l!=r)
			return root;
		Tree t = l? root.left : root.right;
		return LCABT(t,data1,data2);
	}

	public boolean checkContains(Tree root,int data)
	{
		if(root==null)
			return false;
		if(root.data==data)
			return true;
		else return checkContains(root.left,data)|| checkContains(root.right, data);
	}
	
	//Print all elements in same vertical line
	public Tree verticalLine(Tree root, int level,HashMap<Integer, ArrayList<Integer>> map)
	{
		if(root==null)
			return null;
		
		Tree temp = verticalLine(root.left, --level,map);
		if(temp==null)
			level++;
		if(map.containsKey(level))
		{
			ArrayList<Integer> al=map.get(level);
			al.add(root.data);
			map.put(level, al);
		}
		else
		{
			ArrayList<Integer> al = new ArrayList<>();
			al.add(root.data);
			map.put(level, al);
		}
		return verticalLine(root.right, ++level, map);
	}
	
	public void printResult(HashMap ht) {
		Set<Integer> i = ht.keySet();
		for (int keys : i) {
			System.out.print(ht.get(keys));
		}
	}
	
	//Convert a sorted array to a height based BST;
	public Tree arrToTree(int arr[])
	{
		if(arr.length==0)
			return null;
		if(arr.length<2)
		{
			Tree temp = new Tree(arr[0]);
			return temp;
		}
		int mid=arr.length/2;
		Tree root= new Tree(arr[mid]);
		int larr[]=new int[mid];
		int rarr[]=new int[arr.length-mid-1];
		for(int i=0;i<mid;i++)
			larr[i]=arr[i];
		for(int i=mid+1;i<arr.length;i++)
			rarr[i-mid-1]=arr[i];

		root.left = arrToTree(larr);
		root.right = arrToTree(rarr);
		return root;
	}
	
	//Check if one tree(r) is subtree of another(root)
	public boolean checkSubTree(Tree root, Tree r)
	{
		if(root==null)
			return false;
		else if(root.data==r.data&&compareTrees(root, r))
		{
			return true;
		}
		return checkSubTree(root.left, r)||checkSubTree(root.right, r);
	}
	
	public boolean compareTrees(Tree root, Tree r)
	{
		if(root==null&&r==null)
			return true;
		else if(root==null||r==null)
			return false;
		else if(root.data!=r.data)
			return false;
		else return compareTrees(root.left, r.left)||compareTrees(root.right, r.right) ;
	}
	
	/*
	public String printAllCombo(Tree root)
	{
		return null;
	}*/
	
	//Find the minimum depth in a tree
	public int minDepth(Tree root) {
        if(root == null){
            return 0;
        }
        LinkedList<Tree> nodes = new LinkedList<Tree>();
        LinkedList<Integer> counts = new LinkedList<Integer>();
 
        nodes.add(root);
        counts.add(1);
        while(!nodes.isEmpty()){
            Tree curr = nodes.remove();
            int count = counts.remove();
            if(curr.left == null && curr.right == null){
                return count;
            }
 
            if(curr.left != null){
                nodes.add(curr.left);
                counts.add(count+1);
            }
 
            if(curr.right != null){
                nodes.add(curr.right);
                counts.add(count+1);
            }
        }
        return 0;
    }
	
	//Check whether tree has a path equal to a given variable(sum)
	public boolean hasPath(Tree root, int sum){
		if(root==null)
			return false;
		if(root.data==sum&&root.left==null&&root.right==null)
			return true;
		else return hasPath(root.left,sum-root.data) || hasPath(root.right,sum-root.data);
	}
	
	//Get number of Subtrees with n children.
	public int getNumberofSubTree(Tree root, int n,ArrayList<Integer> list){
		
		if(root==null)
			return 0;
		if(root.left==null&&root.right==null)
			return 1;
		int total = getNumberofSubTree(root.left, n, list) + getNumberofSubTree(root.right, n, list);
		if(n==total)
		{
			list.add(root.data);
		}
		return total;
	}
	
	//Get Top View of a Binary Tree
	public void printTopViews(Tree root){
		HashMap<Integer,Integer> map;
		map = getTopView(root, 0);
		System.out.println("\n\nTop View is:");
		for(Map.Entry<Integer, Integer> m: map.entrySet())
			System.out.print(m.getValue()+" ");
	}
	
	public HashMap<Integer,Integer> getTopView(Tree root, int level)
	{
		HashMap<Integer,Integer> list = new HashMap<Integer,Integer>();
		if(root==null)
		 return null;
		
		Queue<qStruct> q = new LinkedList<qStruct>();
		q.add(new qStruct(level,root));
		while(!q.isEmpty())
		{
			qStruct s =q.remove();
			if(!list.containsKey(s.level))
			{
				list.put(s.level, s.node.data);
			}
			if(s.node.left!=null)
				q.add(new qStruct(s.level-1,s.node.left));
			if(s.node.right!=null)
				q.add(new qStruct(s.level+1,s.node.right));
		}
		return list;		
	}
	
	//Create a Binary Tree from Level Order and Inorder
	public Tree createFromOrder(int level[],int in[], int start, int end)
	{
		if(start>end)
			return null;
		
		int rootval= level[0];
		Tree root = new Tree(rootval);
		if(start==end)
			return root;
		int ind = findEleminArray(in, start, end, rootval);
		int[] left=newLevelOrder(in, level, start, ind-1);
		int[] right=newLevelOrder(in, level, ind+1, end);
		root.left=createFromOrder(left, in, start, ind-1);
		root.right=createFromOrder(right, in, ind+1, end);
		return root;
	}
	
	public int[] newLevelOrder(int[] inorder, int[] levelOrder, int iStart,int iEnd) {
		int[] newlevel = new int[iEnd - iStart + 1];
		int x = 0;
		for (int i = 0; i < levelOrder.length; i++) {
			if (findEleminArray(inorder, iStart, iEnd,levelOrder[i]) != -1) {
				newlevel[x] = levelOrder[i];
				x++;
			}
		}
		return newlevel;
	}
	
	public int findEleminArray(int arr[],int start,int end,int val)
	{
		for(int i=start;i<=end;i++)
			if(arr[i]==val)
				return i;
		
		return -1;
	}
	
	//Invert a tree
	public Tree invertTree(Tree root) {
        if(root==null)
        return null;
        root.left=invertTree(root.left);
        root.right=invertTree(root.right);
        Tree temp = root.left;
        root.left=root.right;
        root.right=temp;
        return root;
    }
	/*---------------------------------------- GRAPH FUNCTIONS ------------------------------------------------*/
	
	public void addEdge(Graph g, int v1,int v2)
	{
		g.list[v1].add(v2);
	}
	//DFS of a graph
	public void DFS(Graph g)
	{
		System.out.println("\n\nDFS: ");
		boolean v[]=new boolean[g.data];
		for(int i=0;i<g.data;i++)
			if(!v[i])
				DFSUtil(i,v,g);
	}
	
	private void DFSUtil(int i, boolean[] v, Graph g) {
		v[i]=true;
		System.out.print(i+" ");
		Iterator<Integer> it = g.list[i].iterator();
		while(it.hasNext()){
			int x= it.next();
			if(!v[x])
				DFSUtil(x, v, g);
		}
	}
	
	//BFS of a graph
	private void BFS(Graph g, int i)
	{
		boolean v[]=new boolean[g.data];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(i);
		v[i]=true;
		System.out.println("\n\nBFS: ");
		while(!q.isEmpty())
		{
			int x = q.poll();
			System.out.print(x+" ");
			Iterator<Integer> it = g.list[x].iterator();
			while(it.hasNext())
			{
				int n = it.next();
				if(!v[n]){
					q.add(n);
					v[n]=true;
				}
			}
		}
	}
	
	//Check if path Exists between 2 given nodes
	public String pathExists(Graph g,int v1, int v2)
	{
		String res="Path Does Not Exists between "+v1+", "+v2;
		LinkedHashMap<Integer, ArrayList<Integer>> m = new LinkedHashMap<Integer, ArrayList<Integer>>();
		LinkedHashMap<Integer, Integer> mi = new LinkedHashMap<Integer, Integer>();
		if(g==null)
			return res;
		Queue<Integer> q = new LinkedList<Integer>();
		boolean v[]= new boolean[g.data];
		q.add(v1);
		v[v1]=true;
		while(!q.isEmpty())
		{
			int x = q.poll();
			Iterator<Integer> it = g.list[x].iterator();
			while(it.hasNext())
			{
				int z = it.next();
				if(!mi.containsKey(z))
				{
					mi.put(z, x);
				}
				if(m.containsKey(x))
				{
					ArrayList<Integer> l = m.get(x);
					l.add(z);
					m.put(x, l);
				}
				else
				{
					ArrayList<Integer> l =new ArrayList<Integer>();
					l.add(z);
					m.put(x, l);
				}
				if(z==v2)
				{
					StringBuilder temp = new StringBuilder("Path Exists between "+v1+", "+v2+x);
					printTry(mi,x);
					printPath(m,x);
					return temp.toString();
				}
				else if(!v[z])
				{
					q.add(z);
				}
			}
		}
		return res;
	}
	
	private void printTry(LinkedHashMap<Integer, Integer> mi, int y) {
		// TODO Auto-generated method stub
		StringBuilder s = new StringBuilder();
		int x=0;
		s.insert(0, y);
		x=y;
		System.out.println("x here is"+x);
		while(x!=3)
		{
			x=mi.get(x);
			s.insert(0, x+"->");
		}
		System.out.println(s);
	}

	public void printPath(LinkedHashMap<Integer, ArrayList<Integer>> m, int x)
	{
		/*
		Set<Integer> s = m.keySet();
		for(int k:s)
		{
			
			if(m.get(k).contains(x)){
				x=k;
			}
		}*/
		//return;
		StringBuilder sb = new StringBuilder();
		int y=0;
		sb.insert(0, x);
		while(x!=3)
		{
			if(y++>5)
				break;
			Set<Integer> i = m.keySet();	
			for(int k:i)
			{
				if(m.get(k).contains(x)){
					x=k;
					sb.insert(0, x+" ");
					break;
				}
			}
		}
		System.out.println(" Path is - >"+sb);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution s =new Solution();
		/*Tree root = null;
		root=s.insertNode(root, 5);
		root=s.insertNode(root, 8);
		root=s.insertNode(root, 3);
		root=s.insertNode(root, 2);
		root=s.insertNode(root, 1);
		root=s.insertNode(root, 0);
		root=s.insertNode(root, 4);
		root=s.insertNode(root, 6);
		root=s.insertNode(root, 7);
		root=s.insertNode(root, 9);
		int n=4;
		
		/*System.out.println("\n\nPreorder Traversal: - ");
		s.preOrder(root);
		System.out.println("\n\nPostorder Traversal: - ");
		s.postOrder(root);
		System.out.println("\n\nHeight of Tree = "+s.getTreeHeight(root));
		System.out.println("\n\nIs ProperBST? Ans= "+s.isProperBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
		System.out.println("\n\nIs "+n+" present in BST? Ans = "+s.searchElem(root,n));
		System.out.println("\n\nMin and Max Value in BST: Min = "+s.getMinValue(root).data+", Max = "+s.getMaxValue(root).data);
		System.out.println("Inorder Traversal: - ");
		s.printInorder(root);
		System.out.println("\n\nInorder successor of "+ n+" = "+s.getSuccessor(root, n).data);
		System.out.println("Inorder predecessor of "+ n+" = "+s.getPredecessor(root, n).data);
		System.out.println("\n\nAfter Deleteing Inorder Traversal = ");s.deleteNode(root, 5);s.printInorder(root);
		System.out.println("\n\nLCA of 2, "+n+" = "+s.LCA(root,2, n).data+","+s.LCABT(root,2, n).data);
		HashMap<Integer, ArrayList<Integer>> map=new HashMap<>();
		s.verticalLine(root, 0, map);
		s.printResult(map);
		System.out.println("\n\nBalanced? "+s.isBalanced(root)+","+s.isBalancedDP(root));
		int sarr[]={1,2,3,4,5,6,7,8,9,10};
		Tree r=null;
		r=s.arrToTree(sarr);
		System.out.println("\n\nArray to Tree: Inorder= ");
		s.printInorder(r);
		System.out.println("\n\n"+s.checkSubTree(root, r.left.left));
		System.out.println("\n\nMin depth= "+s.minDepth(root)+", Has sum: "+s.hasPath(root,23));
		
		s.printTopViews(root);
		ArrayList<Integer> al=new ArrayList<>();
		s.getNumberofSubTree(root,2, al);
		
		int[] level={ 1, 2, 3, 4, 5, 6, 7 };
		int[] in={ 4, 2, 5, 1, 6, 3, 7 };
		Tree temp = s.createFromOrder(level, in, 0, in.length-1);
		System.out.println("\n\for test only");
		s.printInorder(temp);
		System.out.println("\n\nTotal Sub trees with 3 children= "+al.size());
		
		
		System.out.println("In Order: ");
		s.printInorder(root);
		root = s.invertTree(root);
		System.out.println("In Order: ");
		s.printInorder(root);*/
		
		
		Graph g = new Graph(10);
		s.addEdge(g, 0, 1);
		s.addEdge(g, 1, 2);
		s.addEdge(g, 2, 3);
		s.addEdge(g, 4, 5);
		s.addEdge(g, 6, 7);
		s.addEdge(g, 7, 8);
		s.addEdge(g, 8, 9);
		s.addEdge(g, 0, 2);
		s.addEdge(g, 0, 3);
		s.addEdge(g, 1, 3);
		s.addEdge(g, 2, 4);
		s.addEdge(g, 3, 6);
		s.addEdge(g, 5, 7);
		s.addEdge(g, 4, 9);
		s.addEdge(g, 2, 7);
		s.addEdge(g, 6, 9);
		s.addEdge(g, 3, 1);
		//s.DFS(g);
		//s.BFS(g, 0);
		System.out.println("\n\n");
		System.out.println(s.pathExists(g, 3, 8));
	}
}
class path{
	public int src;
	public int dest;
	public path(int a, int b)
	{
		src=a;
		dest=b;
	}
}
class qStruct{
	public int level;
	public Tree node;
	public qStruct(int l, Tree n)
	{
		this.level=l;
		this.node=n;
	}
}