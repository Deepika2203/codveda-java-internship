import java.util.Scanner;

class TreeNode {
    int data;
    TreeNode left, right;

    TreeNode(int value) {
        data = value;
        left = right = null;
    }
}
// Binary Search Tree class
class BinarySearchTree {
    TreeNode root;

    // Insert node
    TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.data) {
            root.left = insert(root.left, value);
        } else if (value > root.data) {
            root.right = insert(root.right, value);
        }
        return root;
    }

    // Search node
    boolean search(TreeNode root, int value) {
        if (root == null) return false;
        if (root.data == value) return true;
        if (value < root.data)
            return search(root.left, value);
        return search(root.right, value);
    }

    // Find minimum value node
    TreeNode findMin(TreeNode root) {
        while (root.left != null)
            root = root.left;
        return root;
    }

    // Delete node
    TreeNode delete(TreeNode root, int value) {
        if (root == null) return null;

        if (value < root.data) {
            root.left = delete(root.left, value);
        } else if (value > root.data) {
            root.right = delete(root.right, value);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode temp = findMin(root.right);
            root.data = temp.data;
            root.right = delete(root.right, temp.data);
        }
        return root;
    }

    // In-order traversal
    void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    // Pre-order traversal
    void preorder(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    // Post-order traversal
    void postorder(TreeNode root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }
}

public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Binary Search Tree Menu ---");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. In-order Traversal");
            System.out.println("5. Pre-order Traversal");
            System.out.println("6. Post-order Traversal");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    int insertVal = sc.nextInt();
                    bst.root = bst.insert(bst.root, insertVal);
                    break;

                case 2:
                    System.out.print("Enter value to delete: ");
                    int deleteVal = sc.nextInt();
                    bst.root = bst.delete(bst.root, deleteVal);
                    break;

                case 3:
                    System.out.print("Enter value to search: ");
                    int searchVal = sc.nextInt();
                    System.out.println(
                        bst.search(bst.root, searchVal)
                        ? "Value found"
                        : "Value not found"
                    );
                    break;

                case 4:
                    System.out.print("In-order: ");
                    bst.inorder(bst.root);
                    System.out.println();
                    break;

                case 5:
                    System.out.print("Pre-order: ");
                    bst.preorder(bst.root);
                    System.out.println();
                    break;

                case 6:
                    System.out.print("Post-order: ");
                    bst.postorder(bst.root);
                    System.out.println();
                    break;

                case 7:
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
