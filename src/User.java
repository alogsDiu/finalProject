import java.util.*;


public class User{
    private final String pass ;
    ArrayList<User> subedTo=new ArrayList<>() ;//ArrayList<User>
    public ArrayList<Post> posts =new ArrayList<>() ;//ArrayList<Post> for the future 26.09.2022 - (today) --- so its been 12 days and here we are 18.10.2022
    ArrayList<User> mySubs = new ArrayList<>() ;
    public ArrayList<User> blockedUsers = new ArrayList<>();
    String name ;
    String surname ;
    int age ;
    int gender ; /// "ALL" genders :)  0-is male 1-is female  and of course 27-Helicopter Shooter e.t.c
    String email ;
    String phoneNumb ;
    public User(String name, String surname, int age,String email,int gender,  String phoneNumb, String pass){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email=email;
        this.phoneNumb=phoneNumb;
        this.pass=pass;
        this.gender=gender;
    }
    public void subscribe(User a){
        subedTo.add(a);
    }
    public void unsubscribe(User a){
        subedTo.remove(a);
    }
    public void herOrHissubs(User a){
        mySubs.add(a);
    }
    public void traitors(User a){
        mySubs.remove(a);
    }
    public boolean checker(String pass,String numb){
        if(pass.equals(this.pass)&&this.phoneNumb.equals(numb)){
            return true;
        }else{
            return false;
        }
    }
    public String getName(){
        return this.name+" "+this.surname;
    }
    public String getPhoneNumber(){
        return this.phoneNumb;
    }
    public String getGender(){
        return ( gender == 1 ) ? "MALE" : (gender==0)? "FEMALE" : "OTHER/ANYTHING/IDUNNO" ;
    }
    public String getEmail(){
        return email;
    }
    public int getAge(){
        return this.age;
    }
    public boolean isHeSubbed(User a ){
        for(int i=0;i<subedTo.size();i++){
            if(subedTo.get(i)==a){
                return true;
            }
        }
        return false;
    }
    public void getSubscribers(){
        System.out.println();
        System.out.println("----------------------------------------------");
        if(mySubs.size()==0){
            System.out.println("Sorry NO ONE is subbed to you");
        }else{
            for(int i=0;i<mySubs.size();i++){
                System.out.println(" "+(i+1)+") "+mySubs.get(i).getName());
            }
        }
        System.out.println("----------------------------------------------");
        System.out.println("");
    }

    public void getWhoIamSubbedTo(){
        System.out.println("");
        System.out.println("----------------------------------------------");
        if(subedTo.size()==0){
            System.out.println("Jeeez YOU are not subbed to ANYONE");
        }else{
            for(int i=0;i<subedTo.size();i++){
                System.out.println(" "+(i+1)+") "+subedTo.get(i).getName());
            }
        }
        System.out.println("----------------------------------------------");
        System.out.println("");
    }
    public static void cratePost(User a){
        Scanner scan = new Scanner(System.in);
        System.out.println("****Createing Post****");
        System.out.print("Title : ");
        String title = scan.nextLine().trim();
        System.out.print("Body : ");
        String body = scan.nextLine().trim();
        Post pp = new Post(a,title,body);
        a.posting(pp);
        System.out.println("****DONE-DONE-DONE****");
    }
    public void posting(Post a){

        posts.add(a);
    }
}


