import java.util.*;
public class Post {
    User author ;
    String title ;
    String body ;
    public ArrayList<Coment> coments = new ArrayList<>();
    public ArrayList<User> likes = new ArrayList<>();

    Post(User a , String title , String body ){
        author=a;
        this.title="---------------------------------------\n"+title+"\n---------------------------------------";
        this.body="\n"+body+"\n---------------------------------------";
    }
    public String printPost(){
        return title+body;
    }

}
