import java.util.*;

public class Main {

    public static ArrayList<Integer> addedNodes = new ArrayList<>();
    public static void main(String[] args) {


        Tree tree = new Tree();
        Scanner sc = new Scanner(System.in);
        int userInput = -1;

        while (userInput != 0){
            System.out.println("1. Insert an element into the BST\n" +
                    "2. Search for an element in the BST\n" +
                    "3. Find the maximum element from the BST\n" +
                    "4. Find the minimum element from the BST\n" +
                    "5. Print the elements in the BST in preorder\n" +
                    "6. Print the elements in the BST in postorder\n" +
                    "7. Print the elements in the BST in inorder\n" +
                    "8. Delete an element\n" +
                    "0. To exit the program");

            userInput = sc.nextInt();

            switch (userInput){
                case 1:
                    int userValue = sc.nextInt();
                    tree.addNewNode(userValue);
                    break;
                case 2:
                    int searchValue = sc.nextInt();
                    Node newNoddy = tree.findNode(searchValue);
                    if(newNoddy == null){
                        System.out.println(searchValue+"(0)");
                    }else if(newNoddy.value == searchValue) {
                        System.out.println(newNoddy.value + "(" + Collections.frequency(addedNodes, newNoddy.value) + ")");

                    }
                    break;
                case 3:
                    if (addedNodes.isEmpty()){
                        System.out.println(0 + "("+0+")");
                    }else {
                        tree.findMax();
                    }
                    break;
                case 4:
                    if (addedNodes.isEmpty()){
                        System.out.println(0 + "("+0+")");
                    }else {
                        tree.findMin();
                    }
                    break;
                case 5:
                    tree.preorderBST(tree.root);
                    break;
                case 6:
                    tree.postorderBST(tree.root);
                    break;
                case 7:
                    tree.inorderBST(tree.root);
                    break;
                case 8:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid input try again");
                    break;
            }
        }


    }



    public static class Tree {
        Node root;
        public void addNewNode(int value) {
            Node newNode = new Node(value);
            addedNodes.add(value);

            if (treeIsEmpty()) {
                root = newNode;
            } else {
                Node currentNode = root;
                Node parent;
                while (true) {
                    parent = currentNode;
                    if (value < currentNode.value) {
                        currentNode = currentNode.left;
                        if (currentNode == null) {
                            parent.left = newNode;
                            return;
                        }
                    } else {
                        currentNode = currentNode.right;
                        if (currentNode == null) {
                            parent.right = newNode;
                            return;
                        }
                    }
                }
            }
        }
        public boolean treeIsEmpty(){
            return root == null;
        }

        public Node findNode(int value){
            Node currentNode = root;

            while (currentNode != null && currentNode.value != value){
                if (value < currentNode.value){
                    currentNode = currentNode.left;
                }else {
                    currentNode = currentNode.right;
                }
                if (currentNode == null)
                    return null;
            }
            return currentNode;
        }

        public void findMax(){
            Collections.sort(addedNodes);
            int answer = addedNodes.get(addedNodes.size()-1);
            System.out.println(answer + "(" + Collections.frequency(addedNodes,answer)+")");
        }

        public void findMin(){
            Collections.reverse(addedNodes);
            int answer = addedNodes.get(addedNodes.size()-1);
            System.out.println(answer + "(" + Collections.frequency(addedNodes,answer)+")");
        }

        public void preorderBST(Node currentNode){
            if(currentNode != null){
                System.out.println(currentNode.value + "(" + Collections.frequency(addedNodes,currentNode.value)+")");
                inorderBST(currentNode.left);
                inorderBST(currentNode.right);
            }
        }

        public void postorderBST(Node currentNode){
            if(currentNode != null){
                inorderBST(currentNode.left);
                inorderBST(currentNode.right);
                System.out.println(currentNode.value + "(" + Collections.frequency(addedNodes,currentNode.value)+")");
            }
        }

        public void inorderBST(Node currentNode) {
            if (currentNode != null) {
                inorderBST(currentNode.left);
                System.out.println(currentNode.value + "(" + Collections.frequency(addedNodes,currentNode.value)+")");
                inorderBST(currentNode.right);
            }
        }
    }
    static class Node {
        int value;
        int count;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;
        }
    }


}
