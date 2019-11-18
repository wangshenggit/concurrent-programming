package suanfa.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ����ڵ�
class Node {
    public int iData;                    //�ڵ�����
    public Node leftChild;                //��ָ��
    public Node rightChild;                //��ָ��

    public void displayNode() {            //��ӡ�ڵ���Ϣ
        System.out.print("{" + iData + "}");
    }
}

class BinaryTree {
    public Node root;                    //����������ĸ��ڵ�

    BinaryTree() {
        root = null;
    }

    // ���ҽڵ�
    public Node find(int key) {
        Node current = root;
        while (current != null) {
            if (current.iData == key)
                break;
            else if (current.iData > key)
                current = current.leftChild;
            else
                current = current.rightChild;
        }
        return current;
    }

    // ����ڵ�
    public void insert(int key) {
        Node newNode = new Node();
        newNode.iData = key;
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent = root;
            while (true) {
                parent = current;
                if (key < current.iData) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    // ɾ���ڵ�
    public boolean delete(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;                //ʹ�ñ�ǣ��ж�ɾ���ڵ��Ƿ��Ǹ��ڵ�����ӽڵ�

        // �����ҵ���Ҫɾ���Ľڵ�
        while (current.iData != key) {
            parent = current;
            if (key < current.iData) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null)
                return false;
        }

        // ��û�����ӽڵ���û�����ӽڵ�����
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.leftChild = null;
            else
                parent.rightChild = null;
        }

        // ֻ�����ӽڵ�����
        else if (current.rightChild == null) {
            if (current == root)
                root = current.leftChild;
            else if (isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;
        }

        // ֻ�����ӽڵ�����
        else if (current.leftChild == null) {
            if (current == root)
                root = current.rightChild;
            else if (isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;
        }

        // �������ӽڵ��������ӽڵ�����
        else {
            Node successor = getSuccessor(current);        //Ѱ�Һ�̽ڵ�
            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.leftChild = successor;
            else
                parent.rightChild = successor;
            successor.leftChild = current.leftChild;
        }
        return true;
    }

    // ���ɾ���ڵ�ĺ�̽ڵ�
    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode.rightChild;
        Node current = successor;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        if (successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }

    // ������
    public void traverse(int traversType) {
        switch (traversType) {
            case 1:
                System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2:
                System.out.print("\nInorder traversal: ");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println('\n');
    }

    // ǰ�����
    public void preOrder(Node localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.iData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    // �������
    public void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.iData + " ");
            inOrder(localRoot.rightChild);
        }
    }

    // �������
    public void postOrder(Node localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.iData + " ");
        }
    }
}

// ������ȷ��
public class BinaryTreeApp {
    public static void main(String[] args) throws IOException {
        BinaryTree theTree = new BinaryTree();
        theTree.insert(50);
        theTree.insert(25);
        theTree.insert(75);
        theTree.insert(12);
        theTree.insert(37);
        theTree.insert(43);
        theTree.insert(30);
        theTree.insert(83);
        theTree.insert(93);
        theTree.insert(97);

        while (true) {
            System.out.print("Enter first letter of show, ");
            System.out.print("insert, find, delete, or traverse: ");
            char choice = getChar();
            int value;
            switch (choice) {
                case 's':
                    System.out.println("Nothing happend");
                    break;
                case 'i':
                    System.out.print("Enter value to insert: ");
                    value = getInt();
                    theTree.insert(value);
                    break;
                case 'f':
                    System.out.print("Enter value to find: ");
                    value = getInt();
                    Node found = theTree.find(value);
                    if (found != null) {
                        System.out.print("Found: ");
                        found.displayNode();
                        System.out.print("\n");
                    } else
                        System.out.println("Could not find ");
                    break;
                case 'd':
                    System.out.print("Enter value to delte: ");
                    value = getInt();
                    boolean didDelete = theTree.delete(value);
                    if (didDelete)
                        System.out.println("Deleted " + value);
                    else
                        System.out.println("Could not delete");
                    break;
                case 't':
                    System.out.print("Enter type 1,2 or 3: ");
                    value = getInt();
                    theTree.traverse(value);
                    break;
                default:
                    System.out.println("Invalid entry");
            }
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}
