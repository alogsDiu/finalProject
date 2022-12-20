import hashTableImplementation.MyHashNode;
import hashTableImplementation.MyMap;
import myArrayListImplementation.MyArrayList;
import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class SocialService {
    public static List<User> all = new ArrayList<>(); /// ArrayList<User> all ; ALL Users are Stored here;
    static Scanner scan = new Scanner(System.in);
    static int id = -1;
    static PriorityQueue<Post> topWhatever = new PriorityQueue<>(new ComparatorHomeMade());

    public static void main(String[] args) {
        starter();
    }

    ///its easier this way so
    public static void starter() {
        System.out.println("Hello Welcome to the SoSo.com");
        System.out.println("-----------------------------");
        int ans;
        System.out.println("1) Create new Account ");
        System.out.println("2) Log in ");
        System.out.println("3) Exit ");
        System.out.println("-----------------------------");
        System.out.print("-");
        try {
            ans = Integer.parseInt(scan.nextLine());
        } catch (Exception e) {
            starter();
            return;
        }
        if (ans == 1) {
            createUser();
        } else if (ans == 2) {
            logIn();
        }
        else {
            return;
        }
    }

    public static void createUser() {
        System.out.println();
        System.out.print("Your Name ( ex:Tamerlan ): ");
        String name = scan.nextLine().trim();
        System.out.println();
        System.out.print("Your Surname ( ex:Kartayev ): ");
        String surname = scan.nextLine().trim();
        System.out.println();
        System.out.print("Your age ( ex:69 ): ");
        int age = Integer.parseInt(scan.nextLine().trim());
        System.out.println();
        System.out.print("Your email ( ex:batyr.zhumahanov@email.kz ): ");
        String email = scan.nextLine().trim();
        System.out.println();
        System.out.print("Your gender 1-male , 0-female , anyOtherNumb-youDecide ( ex: 1 ): ");
        int gender = Integer.parseInt(scan.nextLine().trim());
        System.out.println();
        System.out.print("Your number ( ex:87777777777 ): ");
        String phoneNumb = scan.nextLine().trim();
        System.out.println();
        System.out.print("Your Password ATTENTION ! dont forget it haha ( ex:sobaka$vyvyshla@ ): ");
        String pass = scan.nextLine().trim();
        all.add(new User(name, surname, age, email, gender, phoneNumb, pass));
        starter();
    }

    public static void logIn() {
        System.out.println();
        System.out.print("Your number : ");
        String numb = scan.nextLine().trim();
        System.out.println();
        System.out.print("Your pass : ");
        String pass = scan.nextLine().trim();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).checker(pass, numb)) {
                id = i;
                System.out.println("Successfully Loged in !");
                loged();
                return;
            }
        }
        System.out.println("Couldn't LogIn ");
        System.out.println("Try to change number or correct your pass");
        starter();
    }

    public static void loged() {
        System.out.println();
        System.out.println("----WELCOME! " + all.get(id).getName().toUpperCase() + " ----");
        System.out.println("1 - All the users ");
        System.out.println("2 - Subscribers ");
        System.out.println("3 - People who you are subsctibed to...");
        System.out.println("4 - Create a Post");
        System.out.println("5 - Hottest posts A.S.A.P ");
        System.out.println("6  - LogOut");
        System.out.println("-----------------------------------");
        int resp = 0;
        try {
            resp = Integer.parseInt(scan.nextLine().trim());
        } catch (Exception a) {
            loged();
            return;
        }
        switch (resp) {
            case 1:
                posibleFriends();
                break;
            case 2:
                seeSubs();
                break;
            case 3:
                seePeopleWhoYouAreSubedTo();
                break;
            case 4:
                User.cratePost(all.get(id));
                /// It is not working
                topWhatever.add(all.get(id).posts.get(all.get(id).posts.size() - 1));
                loged();
                break;
            case 5:
                hottestPosts();
                break;
            case 6:
                confirmation();
                break;
            default:
                loged();
                break;
        }
    }

    public static void hottestPosts() {
        System.out.println();
        System.out.println("How many of them Do you want to see ?? (digit input pls): ");

        int k = 0;
        try {
            k = Integer.parseInt(scan.nextLine().trim());
        } catch (Exception e) {
            hottestPosts();
            return;
        }

        if (topWhatever.size() == 0) {
            System.out.println();
            System.out.println("---- Apologies apparently there are no Posts  in the system ! ----");
            loged();
            return;
        }

        List<Post> tempCont = new ArrayList<>();
        PriorityQueue<Post> temperary = new PriorityQueue<>(new ComparatorHomeMade());

        int size = topWhatever.size();

        for (int i = 0; k >= tempCont.size() && i < size; i++) {
            int j;
            Post p = topWhatever.poll();
            for (j = 0; j < all.get(id).blockedUsers.size(); j++) {
                if (p.author == all.get(id).blockedUsers.get(j)) {
                    break;
                }
            }
            if (j == all.get(id).blockedUsers.size()) {
                tempCont.add(p);
            }
            temperary.add(p);
        }

        topWhatever = temperary;

        System.out.println("--------------------------------------------------------");


        for (int i = 0; i < tempCont.size(); i++) {
            System.out.println();
            System.out.println("--------------------------------------------------------");
            System.out.println("Post" + (i + 1) + " of: " + tempCont.get(i).author.getName() + "------------");
            System.out.println(tempCont.get(i).printPost());
            System.out.println("****" + tempCont.get(i).likes.size() + "-LIKES***********END*******************");
            System.out.println("****************************************");
        }
        System.out.println();
        System.out.print("CHOSE THE POST WHICH YOU WANNA SEE IN DETAIL (or print any NUM to exit :) )  :");
        int choice = Integer.parseInt(scan.nextLine().trim());
        if (choice > tempCont.size() || choice < 1) {
            loged();
        } else {
            System.out.println();
            System.out.println("--------------------------------------------------------");
            System.out.println("Post" + (choice) + " of: " + tempCont.get(choice - 1).author.getName() + "------------");
            System.out.println(tempCont.get(choice - 1).printPost());
            System.out.println("****" + tempCont.get(choice - 1).likes.size() + "-LIKES");
            System.out.println("****************************************");
            System.out.println("******************END*******************");
            System.out.println("-----------------Comments----------------");
            if (tempCont.get(choice - 1).coments.size() != 0) {
                for (int i = 0; i < tempCont.get(choice - 1).coments.size(); i++) {
                    System.out.println();
                    System.out.println(" " + tempCont.get(choice - 1).coments.get(i).author.getName() + " : " + tempCont.get(choice - 1).coments.get(i).comentItself + " ");
                    System.out.println();
                }
                System.out.println("-----------------THAT WAS ALL THE COMMENTS----------------");
            } else {
                System.out.println("   THE POST DOES NOT HAVE COMMENTS   ");
            }
            System.out.println("-----------------THAT WAS ALL THE COMMENTS----------------");
            System.out.println("WHAT DO YOU WANNA DO WITH THE POST");
            int wtever;
            for (wtever = 0; wtever < tempCont.get(choice - 1).likes.size(); wtever++) {
                if (tempCont.get(choice - 1).likes.get(wtever) == all.get(id)) {
                    System.out.println("1-GET THE LIKE BACK");
                    break;
                }
            }
            if (wtever == tempCont.get(choice - 1).likes.size()) {
                System.out.println("1-LIKE");
            }
            System.out.println("2-COMMENT");
            System.out.println("3-EXIT");
            System.out.print("Your ans :");
            int resp = Integer.parseInt(scan.nextLine().trim());
            switch (resp) {
                case 1:
                    if (wtever == tempCont.get(choice - 1).likes.size())
                        tempCont.get(choice - 1).likes.add(all.get(id));
                    else
                        tempCont.get(choice - 1).likes.remove(wtever);
                    break;
                case 2:
                    System.out.println();
                    System.out.print("GO AHEAD : ");
                    String com = scan.nextLine().trim();
                    Coment c = new Coment(com, all.get(id));
                    tempCont.get(choice - 1).coments.add(c);
                    System.out.println();
                    break;
                case 3:
                    hottestPosts();
                    break;
            }
        }
    }

    public static void posibleFriends() {
        System.out.println();
        System.out.println("---------------------------------");
        System.out.println("---ALL PEOPLE REGISTERED IN THE SYSTEM---");
        int i;
        for (i = 0; i < id; i++) {
            System.out.println("-" + (i + 1) + ") " + all.get(i).getName());
        }
        for (i = id + 1; i < all.size(); i++) {
            System.out.println("-" + i + ") " + all.get(i).getName());
        }

        System.out.println("-" + i + ") EXIT ");
        System.out.println();
        System.out.println("You can pick One Option ( ex: 2 or " + i + " to exit )");
        int ans = 0;
        try {
            ans = Integer.parseInt(scan.nextLine().trim());
        } catch (Exception e) {
            posibleFriends();
            return;
        }
        if (ans <= all.size() && ans > 0) {
            if (ans == all.size()) {
                loged();
            } else {
                if (ans <= id) {
                    ans--;
                }
                int gothrought;
                for (gothrought = 0; gothrought < all.get(ans).blockedUsers.size(); gothrought++) {
                    if (all.get(ans).blockedUsers.get(gothrought) == all.get(id)) {
                        System.out.println("YOU ARE BLOCKED BY THIS USER");
                        break;
                    }
                }
                if (gothrought == all.get(ans).blockedUsers.size()) {
                    subscribe(ans);
                } else {
                    posibleFriends();
                }
            }
        } else {
            posibleFriends();
        }
    }

    public static void confirmation() {
        System.out.println();
        System.out.println("ARE YOU SURE YOU WANT TO PRECEDE. THINK ABOUT IT !!!");
        System.out.println("YES - 1");
        System.out.println("NO - 2");
        String ans = scan.nextLine().trim().toLowerCase();
        if (ans.equals("1") || ans.equals("yes")) {
            id -= 1;
            starter();
        } else if (ans.equals("2") || ans.equals("no")) {
            loged();
        } else {
            confirmation();
        }
        return;
    }

    public static void subscribe(int candidateId) {
        System.out.println();
        System.out.println("--------------------------------------------------------");
        System.out.println("NAME :" + all.get(candidateId).getName());
        System.out.println("AGE :" + all.get(candidateId).getAge());
        System.out.println("GENDER :" + all.get(candidateId).getGender());
        System.out.println("EMAIL :" + all.get(candidateId).getEmail());
        System.out.println("PHONE NUMBER :" + all.get(candidateId).getPhoneNumber());
        System.out.println("--------------------------------------------------------");
        System.out.println();
        if (!all.get(id).isHeSubbed(all.get(candidateId))) {
            System.out.println("1 - Posts");
            System.out.println("2 - to Subscribe");
            if (!all.get(id).blockedUsers.contains(all.get(candidateId)))
                System.out.println("3 - to Block");
            else
                System.out.println("3 - to Unblock");
            System.out.println("4 - Exit");
            System.out.print("-");
            int ans = Integer.parseInt(scan.nextLine().trim());
            if (ans == 1) {
                seePosts(candidateId);
            } else if (ans == 2) {
                all.get(id).subscribe(all.get(candidateId));
                all.get(candidateId).herOrHissubs(all.get(id));
            } else if (ans == 3) {
                if (!all.get(id).blockedUsers.contains(all.get(candidateId))) {
                    all.get(id).blockedUsers.add(all.get(candidateId));
                    System.out.println("USER IS BLOCKED");
                    System.out.println();
                } else {
                    for (int i = 0; i < all.get(id).blockedUsers.size(); i++) {
                        if (all.get(id).blockedUsers.get(i) == all.get(candidateId)) {
                            all.get(id).blockedUsers.remove(i);
                            break;
                        }
                    }
                    System.out.println("USER IS UNBLOCKED");
                    System.out.println();
                }
            } else if (ans != 4) {
                subscribe(candidateId);
                return;
            }
        } else {
            System.out.println("1 - Posts");
            System.out.println("2 - to Unsubscribe");
            if (!all.get(id).blockedUsers.contains(all.get(candidateId)))
                System.out.println("3 - to Block");
            else
                System.out.println("3 - to Unblock");
            System.out.println("4 - Exit");
            System.out.print("-");
            int ans = Integer.parseInt(scan.nextLine().trim());
            if (ans == 1) {
                seePosts(candidateId);
            } else if (ans == 2) {
                all.get(id).unsubscribe(all.get(candidateId));
                all.get(candidateId).traitors(all.get(id));
            } else if (ans == 3) {
                if (!all.get(id).blockedUsers.contains(all.get(candidateId))) {
                    all.get(id).blockedUsers.add(all.get(candidateId));
                    System.out.println("USER IS BLOCKED");
                    System.out.println();
                } else {
                    for (int i = 0; i < all.get(id).blockedUsers.size(); i++) {
                        if (all.get(id).blockedUsers.get(i) == all.get(candidateId)) {
                            all.get(id).blockedUsers.remove(i);
                            break;
                        }
                    }
                    System.out.println("USER IS UNBLOCKED");
                    System.out.println();
                }
            } else if (ans != 4) {
                subscribe(candidateId);
                return;
            }
        }
        posibleFriends();
    }

    public static void seeSubs() {
        all.get(id).getSubscribers();
        loged();
    }

    public static void seePeopleWhoYouAreSubedTo() {
        all.get(id).getWhoIamSubbedTo();
        loged();
    }

    public static void seePosts(int candidateId) {
        System.out.println("Posts of: " + all.get(candidateId).getName() + "------------");
        if (all.get(candidateId).posts.size() == 0) {
            System.out.println();
            System.out.println("--------------------------------------------------------");
            System.out.println("USER HAS NO POSTS");
            System.out.println("--------------------------------------------------------");
            System.out.println();
            subscribe(candidateId);
        }
        System.out.println("Title | Likes" );
        System.out.println(printAllPosts(candidateId, all.get(candidateId).posts));
        all.get(candidateId).posts = (ArrayList<Post>) insertSort(all.get(candidateId).posts);
        for (int i = 0; all.get(candidateId).posts.size() > i; i++) {
            System.out.println();
            System.out.println("**************POST " + (i + 1) + "********************");
            System.out.println(all.get(candidateId).posts.get(i).printPost());
            System.out.println("****" + all.get(candidateId).posts.get(i).likes.size() + "-LIKES***********END*******************");
            System.out.println("****************************************");
            System.out.println();
        }
        System.out.println();
        System.out.print("WHICH POST YOU WANT TO SEE IN DETAIL (JUST PRINT THE NUMBER OR ANYTHING ELSE TO EXIT): ");

        int ans = 0;
        try {
            ans = Integer.parseInt(scan.nextLine().trim());
        } catch (Exception e) {
            seePosts(candidateId);
            return;
        }
        if (ans <= 0 || ans > all.get(candidateId).posts.size()) {
            subscribe(candidateId);
        } else {
            System.out.println();
            System.out.println("**************POST " + (ans) + "********************");
            System.out.println(all.get(candidateId).posts.get(ans - 1).printPost());
            System.out.println("******************END*******************");
            System.out.println("****" + all.get(candidateId).posts.get(ans - 1).likes.size() + "-LIKES*************************");
            System.out.println("-----------------Comments----------------");
            if (all.get(candidateId).posts.get(ans - 1).coments.size() != 0) {
                for (int i = 0; i < all.get(candidateId).posts.get(ans - 1).coments.size(); i++) {
                    System.out.println();
                    System.out.println(" " + all.get(candidateId).posts.get(ans - 1).coments.get(i).author.getName() + " : " + all.get(candidateId).posts.get(ans - 1).coments.get(i).comentItself + " ");
                    System.out.println();
                }
            } else {
                System.out.println("   THE POST DOES NOT HAVE COMMENTS   ");
            }
            System.out.println("-----------------THAT WAS ALL THE COMMENTS----------------");
            System.out.println("WHAT DO YOU WANNA DO WITH THE POST");
            int wtever;
            for (wtever = 0; wtever < all.get(candidateId).posts.get(ans - 1).likes.size(); wtever++) {
                if (all.get(candidateId).posts.get(ans - 1).likes.get(wtever) == all.get(id)) {
                    System.out.println("1-GET THE LIKE BACK");
                    break;
                }
            }
            if (wtever == all.get(candidateId).posts.get(ans - 1).likes.size()) {
                System.out.println("1-LIKE");
            }
            System.out.println("2-COMMENT");
            System.out.println("3-EXIT");
            System.out.print("Your ans :");
            int resp = 0;
            try {
                resp = Integer.parseInt(scan.nextLine().trim());
            } catch (Exception e) {
                seePosts(candidateId);
                return;
            }
            switch (resp) {
                case 1:
                    if (wtever == all.get(candidateId).posts.get(ans - 1).likes.size())
                        all.get(candidateId).posts.get(ans - 1).likes.add(all.get(id));
                    else
                        all.get(candidateId).posts.get(ans - 1).likes.remove(wtever);
                    break;
                case 2:
                    System.out.println();
                    System.out.print("GO AHEAD : ");
                    String com = scan.nextLine().trim();
                    Coment c = new Coment(com, all.get(id));
                    all.get(candidateId).posts.get(ans - 1).coments.add(c);
                    System.out.println();
                    break;
                case 3:
                    seePosts(candidateId);
                    break;
            }
            seePosts(candidateId);
        }
    }

    public static List<Post> insertSort(List<Post> posts) {
        MyArrayList<Integer> likes = new MyArrayList<>();
        for (int i = 0; i < posts.size(); i++) {
            likes.add(posts.get(i).likes.size());
        }
        int n = likes.size();
        for(int i = 0; i < n - 1; i++){
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (likes.get(j) > likes.get(min))
                    min = j;
            }
            int temp = likes.get(min);
            likes.set(min, likes.get(i));
            likes.set(i, temp);
            Collections.swap(posts, min, i);
        }
        return posts;
    }
    public static String printAllPosts(int candidateId, List<Post> posts){
       MyMap<String, Integer> myMap = new MyMap<>();
       String hashTable = "";
       for(int i = 0; i < all.get(candidateId).posts.size(); i++){
           myMap.add(posts.get(i).title, posts.get(i).likes.size());
       }
       for(int i = 0; i < myMap.size(); i++){
           hashTable +=  posts.get(i).title + " | " + myMap.get(posts.get(i).title) + "\n";
       }
       return hashTable;
    }
}

class ComparatorHomeMade implements Comparator<Post>{
    public int compare(Post one,Post two){
        if(one.likes.size()==two.likes.size())
            return 0;
        else if(one.likes.size()>two.likes.size())
            return -1;
        else
            return 1 ;
    }
}

