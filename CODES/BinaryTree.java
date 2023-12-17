import java.util.Scanner;
import java.util.InputMismatchException;

public class BinaryTree {
    private TreeNode root;
    Scanner scan = new Scanner(System.in);
    
    private class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private int data;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    public void createBinaryTree(Scanner scan) {
        while (true) {
        	System.out.print("Enter a number to be added to the tree(type (exit) to stop): ");
            String input = scan.nextLine();
            
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            
            try {
                int value = Integer.parseInt(input);
                root = insert(root, value);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a valid integer or 'exit' to finish.");
            }
        }
    }

    public void insert(int value) {
        root = insert(root, value);
        System.out.println("Inserted value: " + value);
    }

    public TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }
        if (value < root.data) {
            root.left = insert(root.left, value);
        } else if (value > root.data) {
            root.right = insert(root.right, value);
        } else {
            System.out.println("Value " + value + " already exists in the tree.");
        }
        
        return root;
    }

    public void delete(int value){
        if (!search(root, value)) {
            System.out.println("Value " + value + " not found in the tree. Nothing deleted.");
            return;
        }
        root = delete(root, value);
        System.out.println("Deleted value: " + value);
    }

    public TreeNode delete(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }
        if (value < root.data) {
            root.left = delete(root.left, value);
        } else if (value > root.data) {
            root.right = delete(root.right, value);
        } else {
            if(root.left == null)
               return root.right;            
            else if(root.right == null)
               return root.left;  
            root.data = minValue(root.right);
            root.right = delete(root.right, root.data);
        }
        
        return root;
    }
    
    static int minValue(TreeNode root){
        int minVal = root.data;
        while(root.left != null){
            minVal = root.left.data;
            root = root.left;
        }
        return minVal;
    }

    public boolean search(int value) {
        return search(root, value);
    }

    private boolean search(TreeNode root, int value) {
        if (root == null) {
            System.out.println("Value not found in the tree.");
            return false;
        }

        if (value == root.data) {
            System.out.println("Value found in the binary tree.");
            return true;
        } else if (value < root.data) {
            return search(root.left, value);
        } else {
            return search(root.right, value);
        }
    }

    public void inOrder() {
        inOrder(root);
    }

    public void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public void preOrder() {
        preOrder(root);
    }

    public void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    public void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    public static void main(String[] args) {
        BinaryTree obj = new BinaryTree();
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        obj.createBinaryTree(scan);

        while (!exit) {
            try {
                System.out.println("\n- - -  BINARY TREE MENU - - -");
                System.out.println("1.Traversal\n2.Insert\n3.Delete\n4.Search\n5.Exit");
                System.out.print("Enter your choice: ");
                int option = scan.nextInt();
                
                switch (option) {
                    case 1:
                        System.out.println("\n- - - TRAVERSAL - - - ");
                        System.out.println("INORDER: ");
                        obj.inOrder();

                        System.out.println("\nPREORDER: ");
                        obj.preOrder();

                        System.out.println("\nPOSTORDER: ");
                        obj.postOrder();
                        break;
                    case 2:
                        System.out.println("\n- - - INSERT - - -");
                        System.out.print("Enter a value to insert: ");
                        int insertValue = scan.nextInt();
                        obj.insert(insertValue);
                        break;
                    case 3:
                        System.out.println("\n- - - DELETE - - -");
                        System.out.print("Enter a value to delete: ");
                        int deleteValue = scan.nextInt();
                        obj.delete(deleteValue);
                        break;
                    case 4:
                        System.out.println("\n- - - SEARCH - - -");
                        System.out.print("Enter a value to search: ");
                        int searchValue = scan.nextInt();
                        boolean found = obj.search(searchValue);
                        System.out.println("Value " + searchValue + " found: " + found);
                        break;
                    case 5:
                        System.out.println("\nThank you for using. Have a nice day");
                        exit = true;
                        break;
                    default:
                        System.out.println("\nInvalid Input");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter an integer. Try Again.");
                scan.next();
            }
        }
    }
}