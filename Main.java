import java.util.Scanner;

public class Main {

    public static Node textEditor(String text) {
        char[] chars = text.toCharArray();
        Node start = null;
        Node firstHalfNode = null;
        Node secondHalfNode = null;
        Node temp = null;
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '-': {
                    if (firstHalfNode != null) {
                        firstHalfNode = firstHalfNode.preNode;
                        if (firstHalfNode != null) {
                            firstHalfNode.nextNode.preNode = null;
                            firstHalfNode.nextNode = null;
                        }else {
                            start=null;
                        }
                    }
                }
                break;
                case '<': {
                    if (firstHalfNode != null) {
                        temp = firstHalfNode;
                        firstHalfNode = firstHalfNode.preNode;
                        if (firstHalfNode != null)
                            firstHalfNode.nextNode = null;
                        temp.preNode = null;
                        if (secondHalfNode == null) {
                            secondHalfNode = temp;
                        } else {
                            temp.nextNode = secondHalfNode;
                            secondHalfNode.preNode = temp;
                            secondHalfNode = secondHalfNode.preNode;
                        }
                        temp = null;
                        if (firstHalfNode == null)
                            start=null;
                    }
                }
                break;
                case '>': {
                    if (secondHalfNode != null) {
                        temp = secondHalfNode;
                        secondHalfNode = secondHalfNode.nextNode;
                        if (secondHalfNode != null)
                            secondHalfNode.preNode = null;
                        temp.nextNode = null;
                        if (firstHalfNode != null) {
                            firstHalfNode.nextNode = temp;
                            temp.preNode = firstHalfNode;
                            firstHalfNode = firstHalfNode.nextNode;
                        } else {
                            firstHalfNode = temp;
                            start=temp;
                        }
                        temp = null;
                        if (firstHalfNode==null)
                            start=null;
                    }
                }
                break;
                default: {
                    if (firstHalfNode == null) {
                        temp =new Node(chars[i]);
                        start=temp;
                        firstHalfNode=temp;
                        temp=null;
                    } else {
                        temp=new Node(chars[i]);
                        temp.preNode=firstHalfNode;
                        firstHalfNode.nextNode =temp;
                        firstHalfNode =firstHalfNode.nextNode;
                        temp=null;
                    }
                }
                break;
            }
        }
        if (start!=null){
            firstHalfNode.nextNode=secondHalfNode;
            if (secondHalfNode!=null)
                secondHalfNode.preNode=firstHalfNode;
        }else {
            start=secondHalfNode;
        }
        return start;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        Node node = textEditor(text);
        if (node == null)
            System.out.print(-1);
        else {
            while (node != null) {
                System.out.print(node.c);
                node = node.nextNode;
            }
        }
    }
}

class Node {
    char c;
    Node nextNode;
    Node preNode;

    public Node(char c) {
        this.c = c;
        nextNode = null;
        preNode = null;
    }
}