package BST;

import java.util.*;

public class BST {
    
    class Node{
        int data;
        Node left,right;

        public Node(int data){
            this.data=data;
            left=null;
            right=null;
        }
    }

    public Node root=null;

    public BST(){
        root=null;
    }

    void insert(int data){
        Node nn=new Node(data);
        if(root==null){
            root=nn;
            return;
        }
        Node current=root,parent=null;
        while(true){
            parent=current;
            if(data<current.data){
                current=current.left;
                if(current==null){
                    parent.left=nn;
                    return;
                }
            }
            else if(data>current.data){
                current=current.right;
                if(current==null){
                    parent.right=nn;
                    return;
                }
            }
        }
    }

    boolean search(Node root,int key){
      if(root==null || root.data==key){
        return root!=null;
      }  
      
      if(key<root.data){
        return search(root.left,key);
      }

      return search(root.right,key);
    }

    Node delete(Node root,int key){
        if(root==null) return root;
        if(key<root.data){
            root.left=delete(root.left,key);
        }
        else if(key>root.data){
            root.right=delete(root.right,key);
        }
        else{
            //node with only right child
            if(root.left==null){
                root=root.right;
            }
            //node with only left child
            else if(root.right==null){
                root=root.left;
            }
            //node with two children
            else{
                root.data=findmin(root.right).data;
                root.right=delete(root.right,root.data);
            }
        }
        return root;
    }

    void inOrderTraversal(Node root){
        if(root==null){
            System.out.println("Tree is empty");
            return;
        }
        if(root.left!=null){
            inOrderTraversal(root.left);
        }
        System.out.print(root.data+"->");
        if(root.right!=null){
            inOrderTraversal(root.right);
        }
    }

    void preOrderTraversal(Node root){
        if(root==null){
            System.out.println("Tree is empty");
            return;
        }
        System.out.print(root.data+"->");
        if(root.left!=null){
            preOrderTraversal(root.left);
        }
        if(root.right!=null){
            preOrderTraversal(root.right);
        }
    }

    void postOrderTraversal(Node root){
        if(root==null){
            System.out.println("Tree is empty");
            return;
        }
        if(root.left!=null){
            postOrderTraversal(root.left);
        }
        if(root.right!=null){
            postOrderTraversal(root.right);
        }
        System.out.print(root.data+"->");
    }

    //it is nothing but bfs
    void levelOrderTraversal(Node root){
        if(root==null ){
            System.out.println("Tree is empty");
            return;
        }
        Queue<Node> q=new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            Node current=q.poll();
            System.out.print(current.data+"->");
            if(current.left!=null) q.add(current.left);
            if(current.right!=null) q.add(current.right);
        }
    }

    //vertical order traversal
    void verticalOrderTraversal(Node root){

        if(root==null){
            System.out.println("Tree is empty");
            return;
        }
        Map<Integer,List<Integer>> map=new TreeMap<>();
        getOrderVertical(root,0,map);
        for (Map.Entry<Integer,List<Integer>> entry : map.entrySet()) {
            for(int val:entry.getValue()){
                System.out.print(val+"->");
            }
        }

    }

    void getOrderVertical(Node root,int hd,Map<Integer,List<Integer>> map){
        if(root==null) return;

        map.computeIfAbsent(hd,k->new ArrayList<>()).add(root.data);
        getOrderVertical(root.left,hd-1,map);
        getOrderVertical(root.right,hd+1,map);
    }

    Node findmin(Node root){
        if(root.left!=null){
            return findmin(root.left);
        }
        return root;
    }

    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        System.out.println("Enter size of tree");
        BST bst=new BST();

        
        //insertion
        int n=in.nextInt();
        System.out.println("Enter node values");
        for(int i=0;i<n;i++){
            bst.insert(in.nextInt());
        }

        //traversal
        System.out.println("\nIn order traversal");
        bst.inOrderTraversal(bst.root);
        System.out.println("null\n\npre order traversal");
        bst.preOrderTraversal(bst.root);
        System.out.println("null\n\npost order traversal");
        bst.postOrderTraversal(bst.root);
        System.out.println("null\n\nlevel order traversal");
        bst.levelOrderTraversal(bst.root);
        System.out.print("null\n\nVertical order traversal\n");
        bst.verticalOrderTraversal(bst.root);
        System.out.print("null\n");

        //finding minimum node --> left most node is minimum
        System.out.println("\nMinimum element of the bst is : "+bst.findmin(bst.root).data);
        
        //deletion
        System.out.println("\nEnter node to delete :");
        int deleteKey=in.nextInt();
        bst.delete(bst.root,deleteKey);
        bst.inOrderTraversal(bst.root);
        System.out.println("null\n");


        //search
        System.out.println("Enter node value to search : ");
        int key=in.nextInt();
        System.out.println(bst.search(bst.root,key));
    }
}