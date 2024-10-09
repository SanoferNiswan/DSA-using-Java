import java.util.*;

class Node{
    int data;
    Node next;

    public Node(int data){
        this.data=data;
        this.next=null;
    }
}


class Insertion{
    Node head=null;
    Node tail=null;
    public void insert(int data){
        Node nn=new Node(data);
        if(head==null){
            head=nn;
            tail=nn;
            return;
        }
        tail.next=nn;
        tail=nn;
        
    }

    public void display(){
        Node temp=head;
        if(head==null){
            System.out.println("Empty list");
            return;
        }
        while(temp!=null){
            System.out.print(temp.data+"->");
            temp=temp.next;
        }
        System.out.print("null");
    }
}

class InsertAtBeginning{
    static Node head=null;
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        Insertion ins=new Insertion();
        System.out.println("enter size of linkedlist");
        int n=in.nextInt();
        System.out.println("enter the elements");
        for(int i=0;i<n;i++){
            ins.insert(in.nextInt());
        }
        ins.display();
    }

}
