package suanfa.linklist;

/**
 * @version v1.0
 * @ClassName DoublyLinkListApp
 * @Description TODO
 * @Author wangheng
 * @Date 2019/11/18 0018 ���� 9:32
 */
class Link {
    public long dData;         //�ڵ�洢������
    public Link next;          //ָ����һ�ڵ��ָ��
    public Link previous;      //ָ��ǰһ�ڵ��ָ��

    public Link(long d) {
        this.dData = d;
    }

    public void displayLink() {
        System.out.print(dData + " ");
    }
}

class DoublyLinkList {
    private Link first;

    public DoublyLinkList() {
        this.first = null;
    }

    public boolean isEmpty() {
        return (this.first == null);
    }

    public void insertFirst(long dd) {
        Link newLink = new Link(dd);                //�½��ڵ�
        if (!isEmpty())
            this.first.previous = newLink;          //��һ��
        newLink.next = this.first;              //�ڶ���
        this.first = newLink;                        //������
    }

    public Link deleteFirst() {
        Link temp = this.first;               //����ɾ���Ľڵ�
        this.first.next.previous = null;      //��һ��
        this.first = this.first.next;         //�ڶ���
        return temp;
    }

    public boolean insertAfter(long key, long dd) {
        //����������ҽڵ�key
        Link current = first;
        while (current.dData != key) {
            current = current.next;
            if (current == null)
                return false;                  //û�ҵ�����false
        }
        //��current������½ڵ�
        Link newLink = new Link(dd);

        if (current.next != null)
            current.next.previous = newLink;   //��һ��
        newLink.next = current.next;           //�ڶ���
        newLink.previous = current;            //������
        current.next = newLink;                //���Ĳ�
        return true;
    }

    public Link deleteKey(long key) {
        //����������ҽڵ�
        Link current = this.first;
        while (current.dData != key) {
            current = current.next;
            if (current == null)
                return null;
        }
        //�����ͷ�ڵ㣬����ɾ��ͷ�ڵ�ķ�ʽɾ���ڵ�
        if (current == this.first)
            this.first = this.first.next;
            //����
        else {
            current.previous.next = current.next;          //��һ��
            if (current.next != null)
                current.next.previous = current.previous;  //�ڶ���
        }
        return current;
    }

    public void displayForward() {
        Link current = this.first;
        System.out.print("List (first-->last): ");
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }
}

public class DoublyLinkListApp {
    public static void main(String[] args) {
        DoublyLinkList theList = new DoublyLinkList();

        theList.insertFirst(11);
        theList.insertFirst(33);
        theList.insertFirst(55);

        theList.displayForward();

        theList.insertAfter(11, 22);
        theList.insertAfter(33, 44);

        theList.displayForward();

        theList.deleteFirst();
        theList.deleteKey(22);

        theList.displayForward();
    }
}

